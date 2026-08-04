package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ChainCrossbowItem extends CrossbowItem {
    private static final String WEAPONSEXPANDED$QUEUE_KEY =
            "weaponsexpanded:chain_crossbow_queue";

    private static final String WEAPONSEXPANDED$SAVED_CHAMBER_KEY =
            "weaponsexpanded:chain_crossbow_saved_chamber";

    /*
     * Vanilla 1.20.1 crossbow NBT keys.
     */
    private static final String WEAPONSEXPANDED$CHARGED_PROJECTILES_KEY =
            "ChargedProjectiles";

    private static final String WEAPONSEXPANDED$CUSTOM_MODEL_DATA_KEY =
            "CustomModelData";

    private static final String WEAPONSEXPANDED$CHAMBER_PROJECTILES_KEY =
            "projectiles";

    private static final int WEAPONSEXPANDED$MAX_TOTAL_SHOTS = 4;
    private static final int WEAPONSEXPANDED$CMD_EXPLOSIVE_LOADED = 1;

    public ChainCrossbowItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            @Nullable Level level,
            List<Component> tooltip,
            TooltipFlag flag
    ) {
        int queued = weaponsexpanded$getQueuedChambers(stack);

        CompoundTag root = stack.getTag();

        boolean hasSavedChamber =
                root != null
                        && root.contains(
                        WEAPONSEXPANDED$SAVED_CHAMBER_KEY,
                        Tag.TAG_COMPOUND
                );

        boolean isCharged = CrossbowItem.isCharged(stack);
        int currentOrSaved = isCharged || hasSavedChamber ? 1 : 0;

        int total = Math.min(
                WEAPONSEXPANDED$MAX_TOTAL_SHOTS,
                currentOrSaved + queued
        );

        tooltip.add(
                Component.translatable(
                        "tooltip.weaponsexpanded.chain_crossbow_shots",
                        total,
                        WEAPONSEXPANDED$MAX_TOTAL_SHOTS
                )
        );

        super.appendHoverText(stack, level, tooltip, flag);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level,
            Player user,
            InteractionHand hand
    ) {
        ItemStack crossbow = user.getItemInHand(hand);
        CompoundTag root = crossbow.getOrCreateTag();

        weaponsexpanded$trimQueueToMax(root);

        boolean hasSavedChamber = root.contains(
                WEAPONSEXPANDED$SAVED_CHAMBER_KEY,
                Tag.TAG_COMPOUND
        );

        boolean isChargedNow = CrossbowItem.isCharged(crossbow);

        int queued = root.getList(
                WEAPONSEXPANDED$QUEUE_KEY,
                Tag.TAG_COMPOUND
        ).size();

        int currentOrSaved =
                isChargedNow || hasSavedChamber ? 1 : 0;

        int total = currentOrSaved + queued;

        /*
         * The weapon already contains all four shots.
         */
        if (total >= WEAPONSEXPANDED$MAX_TOTAL_SHOTS) {
            if (!level.isClientSide) {
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

                weaponsexpanded$refreshLoadedVisual(crossbow);
            }

            return InteractionResultHolder.fail(crossbow);
        }

        /*
         * Let vanilla handle the client-side use animation.
         */
        if (level.isClientSide) {
            return super.use(level, user, hand);
        }

        /*
         * Recover a chamber if the crossbow's Charged flag was lost.
         */
        if (!isChargedNow) {
            /*
             * Restore the temporarily saved current chamber first.
             */
            if (hasSavedChamber) {
                CompoundTag saved = root
                        .getCompound(
                                WEAPONSEXPANDED$SAVED_CHAMBER_KEY
                        )
                        .copy();

                root.remove(
                        WEAPONSEXPANDED$SAVED_CHAMBER_KEY
                );

                weaponsexpanded$applyChamberToCrossbow(
                        crossbow,
                        saved
                );

                weaponsexpanded$syncPlayerInventory(user);

                return InteractionResultHolder.consume(crossbow);
            }

            /*
             * Otherwise load the next queued chamber.
             */
            if (queued > 0) {
                List<ItemStack> next =
                        weaponsexpanded$popNextChamber(
                                level,
                                crossbow
                        );

                if (!next.isEmpty()) {
                    weaponsexpanded$setChargedProjectiles(
                            crossbow,
                            next
                    );

                    weaponsexpanded$syncPlayerInventory(user);

                    return InteractionResultHolder.consume(crossbow);
                }
            }
        }

        /*
         * The current chamber is loaded, but the queue is not full.
         *
         * Save the current chamber, clear it, and let vanilla begin
         * loading another chamber from the player's inventory.
         */
        if (isChargedNow) {
            root.put(
                    WEAPONSEXPANDED$SAVED_CHAMBER_KEY,
                    weaponsexpanded$encodeChamber(crossbow)
            );

            weaponsexpanded$setChargedProjectiles(
                    crossbow,
                    List.of()
            );

            InteractionResultHolder<ItemStack> vanillaResult =
                    super.use(level, user, hand);

            /*
             * If vanilla cannot begin loading, restore the chamber.
             */
            if (vanillaResult.getResult() == InteractionResult.FAIL) {
                CompoundTag restoreRoot =
                        crossbow.getOrCreateTag();

                if (restoreRoot.contains(
                        WEAPONSEXPANDED$SAVED_CHAMBER_KEY,
                        Tag.TAG_COMPOUND
                )) {
                    CompoundTag saved = restoreRoot
                            .getCompound(
                                    WEAPONSEXPANDED$SAVED_CHAMBER_KEY
                            )
                            .copy();

                    restoreRoot.remove(
                            WEAPONSEXPANDED$SAVED_CHAMBER_KEY
                    );

                    weaponsexpanded$applyChamberToCrossbow(
                            crossbow,
                            saved
                    );
                }
            }

            return vanillaResult;
        }

        return super.use(level, user, hand);
    }

    @Override
    public void releaseUsing(
            ItemStack crossbow,
            Level level,
            LivingEntity user,
            int remainingUseTicks
    ) {
        super.releaseUsing(
                crossbow,
                level,
                user,
                remainingUseTicks
        );

        if (level.isClientSide) {
            return;
        }

        CompoundTag root = crossbow.getOrCreateTag();
        weaponsexpanded$trimQueueToMax(root);

        boolean toppingUp = root.contains(
                WEAPONSEXPANDED$SAVED_CHAMBER_KEY,
                Tag.TAG_COMPOUND
        );

        /*
         * If vanilla successfully loaded the new chamber, move it
         * into our queued chamber list.
         */
        if (toppingUp && CrossbowItem.isCharged(crossbow)) {
            weaponsexpanded$appendCurrentChamberToQueue(
                    crossbow,
                    root
            );
        }

        /*
         * Restore the original chamber as the active chamber.
         */
        if (toppingUp) {
            CompoundTag saved = root
                    .getCompound(
                            WEAPONSEXPANDED$SAVED_CHAMBER_KEY
                    )
                    .copy();

            root.remove(
                    WEAPONSEXPANDED$SAVED_CHAMBER_KEY
            );

            weaponsexpanded$applyChamberToCrossbow(
                    crossbow,
                    saved
            );
        }

        weaponsexpanded$refreshLoadedVisual(crossbow);

        if (user instanceof ServerPlayer serverPlayer) {
            serverPlayer.containerMenu.broadcastChanges();
        }
    }

    /*
     * CrossbowItem.performShooting is static in Minecraft 1.20.1
     * and therefore cannot be overridden. This tick refresh removes
     * CustomModelData after vanilla clears the charged projectiles.
     */
    @Override
    public void inventoryTick(
            ItemStack stack,
            Level level,
            Entity entity,
            int slot,
            boolean selected
    ) {
        super.inventoryTick(
                stack,
                level,
                entity,
                slot,
                selected
        );

        if (level.isClientSide) {
            return;
        }

        boolean repaired =
                weaponsexpanded$repairInvalidLoadedState(
                        stack,
                        entity
                );

        weaponsexpanded$updateLoadedVisual(stack);

        if (repaired
                && entity instanceof ServerPlayer serverPlayer) {
            serverPlayer.containerMenu.broadcastChanges();
        }
    }

    private static boolean weaponsexpanded$repairInvalidLoadedState(
            ItemStack stack,
            Entity holder
    ) {
        CompoundTag root = stack.getTag();

        if (root == null) {
            return false;
        }

        boolean activelyLoading =
                holder instanceof LivingEntity livingEntity
                        && livingEntity.isUsingItem()
                        && livingEntity.getUseItem() == stack;

        /*
         * A saved chamber is valid while the player is loading another
         * round. If loading has ended but the chamber remains saved,
         * restore it.
         */
        if (!activelyLoading
                && root.contains(
                WEAPONSEXPANDED$SAVED_CHAMBER_KEY,
                Tag.TAG_COMPOUND
        )) {
            /*
             * If loading completed before the state became stuck,
             * preserve the newly loaded chamber by moving it into
             * the queue.
             */
            if (CrossbowItem.isCharged(stack)
                    && !weaponsexpanded$getChargedProjectiles(stack)
                    .isEmpty()) {
                weaponsexpanded$appendCurrentChamberToQueue(
                        stack,
                        root
                );
            }

            CompoundTag savedChamber = root
                    .getCompound(
                            WEAPONSEXPANDED$SAVED_CHAMBER_KEY
                    )
                    .copy();

            root.remove(
                    WEAPONSEXPANDED$SAVED_CHAMBER_KEY
            );

            weaponsexpanded$applyChamberToCrossbow(
                    stack,
                    savedChamber
            );

            return true;
        }

        /*
         * Repair the impossible state where the Charged flag is true
         * but ChargedProjectiles contains no projectile.
         */
        if (CrossbowItem.isCharged(stack)
                && weaponsexpanded$getChargedProjectiles(stack)
                .isEmpty()) {
            CrossbowItem.setCharged(stack, false);

            root.remove(
                    WEAPONSEXPANDED$CHARGED_PROJECTILES_KEY
            );

            /*
             * Recover the next valid queued chamber, if one exists.
             * Otherwise the crossbow becomes normally loadable again.
             */
            weaponsexpanded$loadNextChamber(
                    holder.level(),
                    stack
            );

            return true;
        }

        return false;
    }

    public static List<ItemStack> weaponsexpanded$popNextChamber(
            Level level,
            ItemStack crossbow
    ) {
        CompoundTag root = crossbow.getOrCreateTag();

        ListTag queue = root.getList(
                WEAPONSEXPANDED$QUEUE_KEY,
                Tag.TAG_COMPOUND
        );

        if (queue.isEmpty()) {
            return List.of();
        }

        CompoundTag chamber = queue.getCompound(0).copy();
        queue.remove(0);

        if (queue.isEmpty()) {
            root.remove(WEAPONSEXPANDED$QUEUE_KEY);
        } else {
            root.put(WEAPONSEXPANDED$QUEUE_KEY, queue);
        }

        return weaponsexpanded$decodeChamber(chamber);
    }

    /**
     * Convenience method for the server packet handler after firing.
     *
     * @return true if another chamber was loaded
     */
    public static boolean weaponsexpanded$loadNextChamber(
            Level level,
            ItemStack crossbow
    ) {
        List<ItemStack> next =
                weaponsexpanded$popNextChamber(
                        level,
                        crossbow
                );

        if (next.isEmpty()) {
            weaponsexpanded$setChargedProjectiles(
                    crossbow,
                    List.of()
            );

            return false;
        }

        weaponsexpanded$setChargedProjectiles(
                crossbow,
                next
        );

        return true;
    }

    public static int weaponsexpanded$getQueuedChambers(
            ItemStack crossbow
    ) {
        CompoundTag root = crossbow.getTag();

        if (root == null) {
            return 0;
        }

        return root.getList(
                WEAPONSEXPANDED$QUEUE_KEY,
                Tag.TAG_COMPOUND
        ).size();
    }

    public static void weaponsexpanded$refreshLoadedVisual(
            ItemStack stack
    ) {
        weaponsexpanded$updateLoadedVisual(stack);
    }

    private static void weaponsexpanded$updateLoadedVisual(
            ItemStack stack
    ) {
        boolean hasExplosive =
                CrossbowItem.containsChargedProjectile(
                        stack,
                        ModItems.EXPLOSIVE_ARROW.get()
                );

        if (hasExplosive) {
            stack.getOrCreateTag().putInt(
                    WEAPONSEXPANDED$CUSTOM_MODEL_DATA_KEY,
                    WEAPONSEXPANDED$CMD_EXPLOSIVE_LOADED
            );

            return;
        }

        CompoundTag root = stack.getTag();

        if (root != null) {
            root.remove(
                    WEAPONSEXPANDED$CUSTOM_MODEL_DATA_KEY
            );

            if (root.isEmpty()) {
                stack.setTag(null);
            }
        }
    }

    private static void weaponsexpanded$trimQueueToMax(
            CompoundTag root
    ) {
        ListTag queue = root.getList(
                WEAPONSEXPANDED$QUEUE_KEY,
                Tag.TAG_COMPOUND
        );

        int maximumQueued =
                WEAPONSEXPANDED$MAX_TOTAL_SHOTS - 1;

        while (queue.size() > maximumQueued) {
            queue.remove(queue.size() - 1);
        }

        if (queue.isEmpty()) {
            root.remove(WEAPONSEXPANDED$QUEUE_KEY);
        } else {
            root.put(WEAPONSEXPANDED$QUEUE_KEY, queue);
        }
    }

    private static void weaponsexpanded$appendCurrentChamberToQueue(
            ItemStack crossbow,
            CompoundTag root
    ) {
        ListTag queue = root.getList(
                WEAPONSEXPANDED$QUEUE_KEY,
                Tag.TAG_COMPOUND
        );

        int maximumQueued =
                WEAPONSEXPANDED$MAX_TOTAL_SHOTS - 1;

        if (queue.size() >= maximumQueued) {
            return;
        }

        queue.add(
                weaponsexpanded$encodeChamber(crossbow)
        );

        root.put(WEAPONSEXPANDED$QUEUE_KEY, queue);
    }

    private static CompoundTag weaponsexpanded$encodeChamber(
            ItemStack crossbow
    ) {
        CompoundTag chamber = new CompoundTag();
        ListTag serializedProjectiles = new ListTag();

        for (ItemStack projectile :
                weaponsexpanded$getChargedProjectiles(crossbow)) {
            ItemStack oneProjectile = projectile.copy();
            oneProjectile.setCount(1);

            serializedProjectiles.add(
                    oneProjectile.save(new CompoundTag())
            );
        }

        chamber.put(
                WEAPONSEXPANDED$CHAMBER_PROJECTILES_KEY,
                serializedProjectiles
        );

        return chamber;
    }

    private static List<ItemStack> weaponsexpanded$decodeChamber(
            CompoundTag chamber
    ) {
        List<ItemStack> projectiles = new ArrayList<>();

        ListTag serializedProjectiles = chamber.getList(
                WEAPONSEXPANDED$CHAMBER_PROJECTILES_KEY,
                Tag.TAG_COMPOUND
        );

        for (int index = 0;
             index < serializedProjectiles.size();
             index++) {
            ItemStack projectile = ItemStack.of(
                    serializedProjectiles.getCompound(index)
            );

            if (!projectile.isEmpty()) {
                projectile.setCount(1);
                projectiles.add(projectile);
            }
        }

        return projectiles;
    }

    private static List<ItemStack>
    weaponsexpanded$getChargedProjectiles(
            ItemStack crossbow
    ) {
        List<ItemStack> projectiles = new ArrayList<>();
        CompoundTag root = crossbow.getTag();

        if (root == null) {
            return projectiles;
        }

        ListTag serializedProjectiles = root.getList(
                WEAPONSEXPANDED$CHARGED_PROJECTILES_KEY,
                Tag.TAG_COMPOUND
        );

        for (int index = 0;
             index < serializedProjectiles.size();
             index++) {
            ItemStack projectile = ItemStack.of(
                    serializedProjectiles.getCompound(index)
            );

            if (!projectile.isEmpty()) {
                projectiles.add(projectile);
            }
        }

        return projectiles;
    }

    private static void weaponsexpanded$applyChamberToCrossbow(
            ItemStack crossbow,
            CompoundTag chamber
    ) {
        weaponsexpanded$setChargedProjectiles(
                crossbow,
                weaponsexpanded$decodeChamber(chamber)
        );
    }

    /**
     * Sets the vanilla 1.20.1 ChargedProjectiles NBT list.
     */
    public static void weaponsexpanded$setChargedProjectiles(
            ItemStack crossbow,
            List<ItemStack> projectiles
    ) {
        ListTag serializedProjectiles = new ListTag();

        for (ItemStack projectile : projectiles) {
            if (projectile.isEmpty()) {
                continue;
            }

            ItemStack oneProjectile = projectile.copy();
            oneProjectile.setCount(1);

            serializedProjectiles.add(
                    oneProjectile.save(new CompoundTag())
            );
        }

        crossbow.getOrCreateTag().put(
                WEAPONSEXPANDED$CHARGED_PROJECTILES_KEY,
                serializedProjectiles
        );

        CrossbowItem.setCharged(
                crossbow,
                !serializedProjectiles.isEmpty()
        );

        weaponsexpanded$refreshLoadedVisual(crossbow);
    }

    private static void weaponsexpanded$syncPlayerInventory(
            Player player
    ) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.containerMenu.broadcastChanges();
        }
    }
}