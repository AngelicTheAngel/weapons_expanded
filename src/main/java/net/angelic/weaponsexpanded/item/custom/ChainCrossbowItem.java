package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ChainCrossbowItem extends CrossbowItem {
    private static final String QUEUE_KEY =
            "weaponsexpanded:chain_crossbow_queue";
    private static final String SAVED_CHAMBER_KEY =
            "weaponsexpanded:chain_crossbow_saved_chamber";
    private static final String CHAMBER_PROJECTILES_KEY =
            "projectiles";

    private static final int MAX_TOTAL_SHOTS = 4;
    private static final int CMD_EXPLOSIVE_LOADED = 1;

    public ChainCrossbowItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        CompoundTag root = readCustomData(stack);
        boolean hasSavedChamber = root.contains(SAVED_CHAMBER_KEY);
        int currentOrSaved = CrossbowItem.isCharged(stack) || hasSavedChamber ? 1 : 0;
        int total = Math.min(MAX_TOTAL_SHOTS, currentOrSaved + getQueuedChambers(stack));

        tooltipAdder.accept(
                Component.translatable(
                        "tooltip.weaponsexpanded.chain_crossbow_shots", total, MAX_TOTAL_SHOTS)
        );

        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }

    @Override
    public InteractionResult use(Level level, Player user, InteractionHand hand) {
        ItemStack crossbow = user.getItemInHand(hand);
        CompoundTag root = readCustomData(crossbow);

        trimQueueToMax(root);
        writeCustomData(crossbow, root);

        boolean hasSavedChamber =
                root.contains(SAVED_CHAMBER_KEY);

        boolean charged = CrossbowItem.isCharged(crossbow);

        int queued = getQueue(root).size();

        int total = (charged || hasSavedChamber ? 1 : 0) + queued;

        if (total >= MAX_TOTAL_SHOTS) {
            if (!level.isClientSide()) {
                level.playSound(
                        null,
                        user.getX(),
                        user.getY(),
                        user.getZ(),
                        ModSounds.CHAIN_CROSSBOW_FULL.get(),
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );
            }

            refreshLoadedVisual(crossbow);
            return InteractionResult.FAIL;
        }

        /*
         * Recover a stored or queued chamber before attempting
         * to load ammunition from the player's inventory.
         */
        if (!charged) {
            if (hasSavedChamber) {
                CompoundTag saved =
                        root.getCompoundOrEmpty(
                                SAVED_CHAMBER_KEY
                        ).copy();

                root.remove(SAVED_CHAMBER_KEY);
                writeCustomData(crossbow, root);
                applyChamber(level, crossbow, saved);
                syncPlayerInventory(user);

                return InteractionResult.CONSUME;
            }

            if (queued > 0
                    && loadNextChamber(level, crossbow)) {
                syncPlayerInventory(user);

                return InteractionResult.CONSUME;
            }

            return super.use(level, user, hand);
        }

        /*
         * Temporarily remove the current chamber so vanilla sees an
         * uncharged crossbow and begins another loading cycle.
         *
         * This must happen on both the client and server. Previously
         * the client called super.use() while still charged, causing
         * it to fire instead of entering the loading animation.
         */
        root.put(
                SAVED_CHAMBER_KEY,
                encodeChamber(level, crossbow)
        );

        writeCustomData(crossbow, root);
        setChargedProjectiles(crossbow, List.of());

        InteractionResult result = super.use(level, user, hand);

        if (result == InteractionResult.FAIL) {
            restoreSavedChamber(level, crossbow);
        }

        return result;
    }

    @Override
    public boolean releaseUsing(ItemStack crossbow, Level level, LivingEntity user, int remainingUseTicks) {
        boolean result = super.releaseUsing(crossbow, level, user, remainingUseTicks);

        if (level.isClientSide()) {
            return result;
        }

        CompoundTag root = readCustomData(crossbow);
        trimQueueToMax(root);

        boolean toppingUp = root.contains(SAVED_CHAMBER_KEY);

        if (result && toppingUp && CrossbowItem.isCharged(crossbow)) {
                appendCurrentChamberToQueue(level, crossbow, root);
        }

        if (toppingUp) {
            CompoundTag saved = root.getCompoundOrEmpty(SAVED_CHAMBER_KEY).copy();
            root.remove(SAVED_CHAMBER_KEY);

            writeCustomData(crossbow, root);
            applyChamber(level, crossbow, saved);
        } else {
            writeCustomData(crossbow, root);
        }

        refreshLoadedVisual(crossbow);

        if (user instanceof ServerPlayer serverPlayer) {
            serverPlayer.containerMenu.broadcastChanges();
        }
        return result;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity entity, @Nullable EquipmentSlot slot) {
        super.inventoryTick(stack, level, entity, slot);

        if (level.isClientSide()) {
            return;
        }

        boolean changed = repairLoadedState(level, stack, entity);
        updateLoadedVisual(stack);

        if (changed && entity instanceof ServerPlayer serverPlayer) {
            serverPlayer.containerMenu.broadcastChanges();
        }
    }

    private static boolean repairLoadedState(
            Level level,
            ItemStack stack,
            Entity holder
    ) {
        CompoundTag root = readCustomData(stack);

        /*
         * Do not restore the saved chamber while the entity is using a
         * chain crossbow. Comparing ItemStack references with == is not
         * reliable after data-component updates and inventory syncing.
         */
        boolean activelyLoading =
                holder instanceof LivingEntity livingEntity
                        && livingEntity.isUsingItem()
                        && livingEntity.getUseItem().getItem()
                        instanceof ChainCrossbowItem;

        if (root.contains(SAVED_CHAMBER_KEY)) {
            if (activelyLoading) {
                return false;
            }

            /*
             * Loading was interrupted. Restore the chamber
             * that was temporarily removed by use().
             */
            if (CrossbowItem.isCharged(stack)) {
                appendCurrentChamberToQueue(level, stack, root);
            }

            CompoundTag saved =
                    root.getCompoundOrEmpty(
                            SAVED_CHAMBER_KEY
                    ).copy();

            root.remove(SAVED_CHAMBER_KEY);
            writeCustomData(stack, root);
            applyChamber(level, stack, saved);

            return true;
        }

        /*
         * After firing, automatically move the next queued chamber
         * into the active chamber.
         */
        if (!CrossbowItem.isCharged(stack)
                && !getQueue(root).isEmpty()) {
            return loadNextChamber(level, stack);
        }

        return false;
    }

    private static void restoreSavedChamber(
            Level level,
            ItemStack crossbow
    ) {
        CompoundTag root = readCustomData(crossbow);

        if (!root.contains(SAVED_CHAMBER_KEY)) {
            return;
        }

        CompoundTag saved = root.getCompoundOrEmpty(SAVED_CHAMBER_KEY).copy();
        root.remove(SAVED_CHAMBER_KEY);
        writeCustomData(crossbow, root);
        applyChamber(level, crossbow, saved);
    }

    public static boolean loadNextChamber(
            Level level,
            ItemStack crossbow
    ) {
        List<ItemStack> next = popNextChamber(level, crossbow);
        setChargedProjectiles(crossbow, next);
        return !next.isEmpty();
    }

    public static boolean weaponsexpanded$loadNextChamber(
            Level level,
            ItemStack crossbow
    ) {
        return loadNextChamber(level, crossbow);
    }

    public static int getQueuedChambers(ItemStack crossbow) {
        return getQueue(readCustomData(crossbow)).size();
    }

    public static int weaponsexpanded$getQueuedChambers(
            ItemStack crossbow
    ) {
        return getQueuedChambers(crossbow);
    }

    public static void refreshLoadedVisual(ItemStack stack) {
        updateLoadedVisual(stack);
    }

    public static void weaponsexpanded$refreshLoadedVisual(
            ItemStack stack
    ) {
        refreshLoadedVisual(stack);
    }

    private static List<ItemStack> popNextChamber(
            Level level,
            ItemStack crossbow
    ) {
        CompoundTag root = readCustomData(crossbow);
        ListTag queue = getQueue(root);

        if (queue.isEmpty()) {
            return List.of();
        }

        CompoundTag chamber = queue.getCompoundOrEmpty(0).copy();
        queue.remove(0);

        if (queue.isEmpty()) {
            root.remove(QUEUE_KEY);
        } else {
            root.put(QUEUE_KEY, queue);
        }

        writeCustomData(crossbow, root);
        return decodeChamber(level, chamber);
    }

    public static List<ItemStack> weaponsexpanded$popNextChamber(
            Level level,
            ItemStack crossbow
    ) {
        return popNextChamber(level, crossbow);
    }

    private static void appendCurrentChamberToQueue(
            Level level,
            ItemStack crossbow,
            CompoundTag root
    ) {
        ListTag queue = getQueue(root);

        if (queue.size() >= MAX_TOTAL_SHOTS - 1) {
            return;
        }

        queue.add(encodeChamber(level, crossbow));
        root.put(QUEUE_KEY, queue);
        writeCustomData(crossbow, root);
    }

    private static CompoundTag encodeChamber(Level level, ItemStack crossbow) {
        CompoundTag chamber = new CompoundTag();
        ListTag serializedProjectiles = new ListTag();

        var ops = level.registryAccess()
                .createSerializationContext(NbtOps.INSTANCE);

        for (ItemStack projectile : getChargedProjectiles(crossbow)) {
            ItemStack.CODEC
                    .encodeStart(ops, projectile.copyWithCount(1))
                    .result()
                    .filter(CompoundTag.class::isInstance)
                    .map(CompoundTag.class::cast)
                    .ifPresent(serializedProjectiles::add);
        }

        chamber.put(CHAMBER_PROJECTILES_KEY, serializedProjectiles);

        return chamber;
    }

    private static List<ItemStack> decodeChamber(Level level, CompoundTag chamber) {
        List<ItemStack> projectiles = new ArrayList<>();

        ListTag serializedProjectiles = chamber.getListOrEmpty(CHAMBER_PROJECTILES_KEY);

        var ops = level.registryAccess().createSerializationContext(NbtOps.INSTANCE);

        for (int index = 0;
             index < serializedProjectiles.size();
             index++) {

            CompoundTag projectileTag = serializedProjectiles.getCompoundOrEmpty(index);

            ItemStack projectile = ItemStack.CODEC
                    .parse(ops, projectileTag)
                    .result()
                    .orElse(ItemStack.EMPTY);

            if (!projectile.isEmpty()) {
                projectiles.add(projectile);
            }
        }

        return projectiles;
    }

    private static void applyChamber(
            Level level,
            ItemStack crossbow,
            CompoundTag chamber
    ) {
        setChargedProjectiles(
                crossbow,
                decodeChamber(level, chamber)
        );
    }

    public static void setChargedProjectiles(
            ItemStack crossbow,
            List<ItemStack> projectiles
    ) {
        List<ItemStack> copies = projectiles.stream()
                .filter(projectile -> !projectile.isEmpty())
                .map(projectile -> projectile.copyWithCount(1))
                .toList();

        crossbow.set(
                DataComponents.CHARGED_PROJECTILES,
                copies.isEmpty()
                        ? ChargedProjectiles.EMPTY
                        : ChargedProjectiles.of(copies)
        );

        refreshLoadedVisual(crossbow);
    }

    public static void weaponsexpanded$setChargedProjectiles(
            ItemStack crossbow,
            List<ItemStack> projectiles
    ) {
        setChargedProjectiles(crossbow, projectiles);
    }

    private static List<ItemStack> getChargedProjectiles(
            ItemStack crossbow
    ) {
        return crossbow.getOrDefault(
                DataComponents.CHARGED_PROJECTILES,
                ChargedProjectiles.EMPTY
        ).getItems();
    }

    private static void updateLoadedVisual(ItemStack stack) {
        boolean hasExplosive = stack.getOrDefault(
                DataComponents.CHARGED_PROJECTILES,
                ChargedProjectiles.EMPTY
        ).contains(ModItems.EXPLOSIVE_ARROW.get());

        if (hasExplosive) {
            stack.set(
                    DataComponents.CUSTOM_MODEL_DATA,
                    new CustomModelData(List.of((float) CMD_EXPLOSIVE_LOADED), List.of(), List.of(), List.of())
            );
        } else {
            stack.remove(DataComponents.CUSTOM_MODEL_DATA);
        }
    }

    private static void trimQueueToMax(CompoundTag root) {
        ListTag queue = getQueue(root);

        while (queue.size() > MAX_TOTAL_SHOTS - 1) {
            queue.remove(queue.size() - 1);
        }

        if (queue.isEmpty()) {
            root.remove(QUEUE_KEY);
        } else {
            root.put(QUEUE_KEY, queue);
        }
    }

    private static ListTag getQueue(CompoundTag root) {
        return root.getListOrEmpty(QUEUE_KEY).copy();
    }

    private static CompoundTag readCustomData(ItemStack stack) {
        return stack.getOrDefault(
                DataComponents.CUSTOM_DATA,
                CustomData.EMPTY
        ).copyTag();
    }

    private static void writeCustomData(
            ItemStack stack,
            CompoundTag root
    ) {
        if (root.isEmpty()) {
            stack.remove(DataComponents.CUSTOM_DATA);
        } else {
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(root));
        }
    }

    private static void syncPlayerInventory(Player player) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.containerMenu.broadcastChanges();
        }
    }
}