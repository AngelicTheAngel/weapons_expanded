package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
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

    public ChainCrossbowItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(
            ItemStack stack,
            @Nullable World world,
            List<Text> tooltip,
            TooltipContext context
    ) {
        int queued = weaponsexpanded$getQueuedChambers(stack);

        NbtCompound root = stack.getNbt();

        boolean hasSavedChamber =
                root != null
                        && root.contains(
                        WEAPONSEXPANDED$SAVED_CHAMBER_KEY,
                        NbtElement.COMPOUND_TYPE
                );

        boolean isCharged = CrossbowItem.isCharged(stack);
        int currentOrSaved = isCharged || hasSavedChamber ? 1 : 0;

        int total = Math.min(
                WEAPONSEXPANDED$MAX_TOTAL_SHOTS,
                currentOrSaved + queued
        );

        tooltip.add(
                Text.translatable(
                        "tooltip.weaponsexpanded.chain_crossbow_shots",
                        total,
                        WEAPONSEXPANDED$MAX_TOTAL_SHOTS
                )
        );

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public TypedActionResult<ItemStack> use(
            World world,
            PlayerEntity user,
            Hand hand
    ) {
        ItemStack crossbow = user.getStackInHand(hand);
        NbtCompound root = crossbow.getOrCreateNbt();

        weaponsexpanded$trimQueueToMax(root);

        boolean hasSavedChamber = root.contains(
                WEAPONSEXPANDED$SAVED_CHAMBER_KEY,
                NbtElement.COMPOUND_TYPE
        );

        boolean isChargedNow = CrossbowItem.isCharged(crossbow);

        int queued = root.getList(
                WEAPONSEXPANDED$QUEUE_KEY,
                NbtElement.COMPOUND_TYPE
        ).size();

        int currentOrSaved =
                isChargedNow || hasSavedChamber ? 1 : 0;

        int total = currentOrSaved + queued;

        /*
         * The weapon already contains all four shots.
         */
        if (total >= WEAPONSEXPANDED$MAX_TOTAL_SHOTS) {
            if (!world.isClient) {
                world.playSound(
                        null,
                        user.getX(),
                        user.getY(),
                        user.getZ(),
                        ModSounds.CHAIN_CROSSBOW_FULL,
                        SoundCategory.PLAYERS,
                        1.0F,
                        1.0F
                );

                weaponsexpanded$refreshLoadedVisual(crossbow);
            }

            return TypedActionResult.fail(crossbow);
        }

        /*
         * Let vanilla handle the client-side use animation.
         */
        if (world.isClient) {
            return super.use(world, user, hand);
        }

        /*
         * Recover a chamber if the crossbow's Charged flag was lost.
         */
        if (!isChargedNow) {
            /*
             * Restore the temporarily saved current chamber first.
             */
            if (hasSavedChamber) {
                NbtCompound saved = root
                        .getCompound(WEAPONSEXPANDED$SAVED_CHAMBER_KEY)
                        .copy();

                root.remove(WEAPONSEXPANDED$SAVED_CHAMBER_KEY);

                weaponsexpanded$applyChamberToCrossbow(
                        crossbow,
                        saved
                );

                weaponsexpanded$syncPlayerInventory(user);
                return TypedActionResult.consume(crossbow);
            }

            /*
             * Otherwise load the next queued chamber.
             */
            if (queued > 0) {
                List<ItemStack> next =
                        weaponsexpanded$popNextChamber(world, crossbow);

                if (!next.isEmpty()) {
                    weaponsexpanded$setChargedProjectiles(
                            crossbow,
                            next
                    );

                    weaponsexpanded$syncPlayerInventory(user);
                    return TypedActionResult.consume(crossbow);
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

            TypedActionResult<ItemStack> vanillaResult =
                    super.use(world, user, hand);

            /*
             * If vanilla cannot begin loading, restore the chamber.
             */
            if (vanillaResult.getResult() == ActionResult.FAIL) {
                NbtCompound restoreRoot = crossbow.getOrCreateNbt();

                if (restoreRoot.contains(
                        WEAPONSEXPANDED$SAVED_CHAMBER_KEY,
                        NbtElement.COMPOUND_TYPE
                )) {
                    NbtCompound saved = restoreRoot
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

        return super.use(world, user, hand);
    }

    @Override
    public void onStoppedUsing(
            ItemStack crossbow,
            World world,
            LivingEntity user,
            int remainingUseTicks
    ) {
        super.onStoppedUsing(
                crossbow,
                world,
                user,
                remainingUseTicks
        );

        if (world.isClient) {
            return;
        }

        NbtCompound root = crossbow.getOrCreateNbt();
        weaponsexpanded$trimQueueToMax(root);

        boolean toppingUp = root.contains(
                WEAPONSEXPANDED$SAVED_CHAMBER_KEY,
                NbtElement.COMPOUND_TYPE
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
            NbtCompound saved = root
                    .getCompound(
                            WEAPONSEXPANDED$SAVED_CHAMBER_KEY
                    )
                    .copy();

            root.remove(WEAPONSEXPANDED$SAVED_CHAMBER_KEY);

            weaponsexpanded$applyChamberToCrossbow(
                    crossbow,
                    saved
            );
        }

        weaponsexpanded$refreshLoadedVisual(crossbow);

        if (user instanceof ServerPlayerEntity serverPlayer) {
            serverPlayer.currentScreenHandler.syncState();
        }
    }

    /*
     * shootAll is static in Minecraft 1.20.1 and therefore cannot be
     * overridden. This tick refresh removes CustomModelData after the
     * vanilla static shootAll method clears the charged projectiles.
     */
    @Override
    public void inventoryTick(
            ItemStack stack,
            World world,
            Entity entity,
            int slot,
            boolean selected
    ) {
        super.inventoryTick(
                stack,
                world,
                entity,
                slot,
                selected
        );

        if (world.isClient) {
            return;
        }

        boolean repaired =
                weaponsexpanded$repairInvalidLoadedState(
                        stack,
                        entity
                );

        weaponsexpanded$updateLoadedVisual(stack);

        if (repaired
                && entity instanceof ServerPlayerEntity serverPlayer) {
            serverPlayer.currentScreenHandler.syncState();
        }
    }

    private static boolean weaponsexpanded$repairInvalidLoadedState(
            ItemStack stack,
            Entity holder
    ) {
        NbtCompound root = stack.getNbt();

        if (root == null) {
            return false;
        }

        boolean activelyLoading =
                holder instanceof LivingEntity livingEntity
                        && livingEntity.isUsingItem()
                        && livingEntity.getActiveItem() == stack;

        /*
         * A saved chamber is valid while the player is loading another
         * round. If loading has ended but the chamber remains saved,
         * restore it.
         */
        if (!activelyLoading
                && root.contains(
                WEAPONSEXPANDED$SAVED_CHAMBER_KEY,
                NbtElement.COMPOUND_TYPE
        )) {
            /*
             * If loading completed before the state became stuck, preserve
             * that newly loaded chamber by moving it into the queue.
             */
            if (CrossbowItem.isCharged(stack)
                    && !weaponsexpanded$getChargedProjectiles(stack)
                    .isEmpty()) {
                weaponsexpanded$appendCurrentChamberToQueue(
                        stack,
                        root
                );
            }

            NbtCompound savedChamber = root
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
         * Repair the impossible state where the Charged flag is true but
         * ChargedProjectiles contains no projectile.
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
                    holder.getWorld(),
                    stack
            );

            return true;
        }

        return false;
    }

    public static List<ItemStack> weaponsexpanded$popNextChamber(
            World world,
            ItemStack crossbow
    ) {
        NbtCompound root = crossbow.getOrCreateNbt();

        NbtList queue = root.getList(
                WEAPONSEXPANDED$QUEUE_KEY,
                NbtElement.COMPOUND_TYPE
        );

        if (queue.isEmpty()) {
            return List.of();
        }

        NbtCompound chamber = queue.getCompound(0).copy();
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
            World world,
            ItemStack crossbow
    ) {
        List<ItemStack> next =
                weaponsexpanded$popNextChamber(world, crossbow);

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
        NbtCompound root = crossbow.getNbt();

        if (root == null) {
            return 0;
        }

        return root.getList(
                WEAPONSEXPANDED$QUEUE_KEY,
                NbtElement.COMPOUND_TYPE
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
        boolean hasExplosive = CrossbowItem.hasProjectile(
                stack,
                ModItems.EXPLOSIVE_ARROW
        );

        if (hasExplosive) {
            stack.getOrCreateNbt().putInt(
                    WEAPONSEXPANDED$CUSTOM_MODEL_DATA_KEY,
                    WEAPONSEXPANDED$CMD_EXPLOSIVE_LOADED
            );
            return;
        }

        NbtCompound root = stack.getNbt();

        if (root != null) {
            root.remove(WEAPONSEXPANDED$CUSTOM_MODEL_DATA_KEY);

            if (root.isEmpty()) {
                stack.setNbt(null);
            }
        }
    }

    private static void weaponsexpanded$trimQueueToMax(
            NbtCompound root
    ) {
        NbtList queue = root.getList(
                WEAPONSEXPANDED$QUEUE_KEY,
                NbtElement.COMPOUND_TYPE
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
            NbtCompound root
    ) {
        NbtList queue = root.getList(
                WEAPONSEXPANDED$QUEUE_KEY,
                NbtElement.COMPOUND_TYPE
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

    private static NbtCompound weaponsexpanded$encodeChamber(
            ItemStack crossbow
    ) {
        NbtCompound chamber = new NbtCompound();
        NbtList serializedProjectiles = new NbtList();

        for (ItemStack projectile :
                weaponsexpanded$getChargedProjectiles(crossbow)) {
            ItemStack oneProjectile = projectile.copy();
            oneProjectile.setCount(1);

            serializedProjectiles.add(
                    oneProjectile.writeNbt(new NbtCompound())
            );
        }

        chamber.put(
                WEAPONSEXPANDED$CHAMBER_PROJECTILES_KEY,
                serializedProjectiles
        );

        return chamber;
    }

    private static List<ItemStack> weaponsexpanded$decodeChamber(
            NbtCompound chamber
    ) {
        List<ItemStack> projectiles = new ArrayList<>();

        NbtList serializedProjectiles = chamber.getList(
                WEAPONSEXPANDED$CHAMBER_PROJECTILES_KEY,
                NbtElement.COMPOUND_TYPE
        );

        for (int index = 0;
             index < serializedProjectiles.size();
             index++) {
            ItemStack projectile = ItemStack.fromNbt(
                    serializedProjectiles.getCompound(index)
            );

            if (!projectile.isEmpty()) {
                projectile.setCount(1);
                projectiles.add(projectile);
            }
        }

        return projectiles;
    }

    private static List<ItemStack> weaponsexpanded$getChargedProjectiles(
            ItemStack crossbow
    ) {
        List<ItemStack> projectiles = new ArrayList<>();
        NbtCompound root = crossbow.getNbt();

        if (root == null) {
            return projectiles;
        }

        NbtList serializedProjectiles = root.getList(
                WEAPONSEXPANDED$CHARGED_PROJECTILES_KEY,
                NbtElement.COMPOUND_TYPE
        );

        for (int index = 0;
             index < serializedProjectiles.size();
             index++) {
            ItemStack projectile = ItemStack.fromNbt(
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
            NbtCompound chamber
    ) {
        weaponsexpanded$setChargedProjectiles(
                crossbow,
                weaponsexpanded$decodeChamber(chamber)
        );
    }

    /**
     * 1.20.1 replacement for setting CHARGED_PROJECTILES.
     */
    public static void weaponsexpanded$setChargedProjectiles(
            ItemStack crossbow,
            List<ItemStack> projectiles
    ) {
        NbtList serializedProjectiles = new NbtList();

        for (ItemStack projectile : projectiles) {
            if (projectile.isEmpty()) {
                continue;
            }

            ItemStack oneProjectile = projectile.copy();
            oneProjectile.setCount(1);

            serializedProjectiles.add(
                    oneProjectile.writeNbt(new NbtCompound())
            );
        }

        crossbow.getOrCreateNbt().put(
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
            PlayerEntity player
    ) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            serverPlayer.currentScreenHandler.syncState();
        }
    }
}