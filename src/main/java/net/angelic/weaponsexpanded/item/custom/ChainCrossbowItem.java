package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.sound.ModSounds;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtOps;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.List;

import net.angelic.weaponsexpanded.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class ChainCrossbowItem extends CrossbowItem {

    private static final String WEAPONSEXPANDED$QUEUE_KEY = "weaponsexpanded:chain_crossbow_queue";
    private static final String WEAPONSEXPANDED$SAVED_CHAMBER_KEY = "weaponsexpanded:chain_crossbow_saved_chamber";

    private static final int WEAPONSEXPANDED$MAX_TOTAL_SHOTS = 4;

    // Use float slot 0 in CustomModelDataComponent to drive item model selection
    private static final float WEAPONSEXPANDED$CMD_EXPLOSIVE_LOADED = 1.0F;

    private static void weaponsexpanded$updateLoadedVisual(ItemStack stack) {
        ChargedProjectilesComponent charged =
                stack.getOrDefault(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);

        boolean hasExplosive = charged.getProjectiles().stream()
                .anyMatch(s -> !s.isEmpty() && s.getItem() == ModItems.EXPLOSIVE_ARROW);

        if (hasExplosive) {
            stack.set(
                    DataComponentTypes.CUSTOM_MODEL_DATA,
                    new CustomModelDataComponent(List.of(WEAPONSEXPANDED$CMD_EXPLOSIVE_LOADED), List.of(), List.of(), List.of())
            );
        } else {
            stack.remove(DataComponentTypes.CUSTOM_MODEL_DATA);
        }
    }

    /**
     * Public hook for mixins/other code paths (auto-reload, restore, etc.)
     * to refresh the item model state after CHARGED_PROJECTILES changes.
     */
    public static void weaponsexpanded$refreshLoadedVisual(ItemStack stack) {
        weaponsexpanded$updateLoadedVisual(stack);
    }

    public ChainCrossbowItem(Settings settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        // queued shots (not counting the current chamber)
        int queued = weaponsexpanded$getQueuedChambers(stack);

        // current-or-saved chamber counts as one "loaded" shot for display
        NbtCompound root = weaponsexpanded$getOrCreateCustomNbt(stack);
        boolean hasSaved = root.contains(WEAPONSEXPANDED$SAVED_CHAMBER_KEY);
        boolean isCharged = CrossbowItem.isCharged(stack);
        int currentOrSaved = (isCharged || hasSaved) ? 1 : 0;

        int total = Math.min(WEAPONSEXPANDED$MAX_TOTAL_SHOTS, currentOrSaved + queued);

        tooltip.add(Text.translatable("tooltip.weaponsexpanded.chain_crossbow_shots", total, WEAPONSEXPANDED$MAX_TOTAL_SHOTS));

        super.appendTooltip(stack, world, tooltip, context);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack crossbow = user.getStackInHand(hand);

        // Compute fullness on BOTH sides so the client doesn't run vanilla "shootAll" when full.
        NbtCompound root = weaponsexpanded$getOrCreateCustomNbt(crossbow);
        weaponsexpanded$trimQueueToMax(root);

        boolean hasSavedChamber = root.contains(WEAPONSEXPANDED$SAVED_CHAMBER_KEY);
        boolean isChargedNow = CrossbowItem.isCharged(crossbow);
        int queued = root.getListOrEmpty(WEAPONSEXPANDED$QUEUE_KEY).size();

        int currentOrSaved = (isChargedNow || hasSavedChamber) ? 1 : 0;
        int total = currentOrSaved + queued;

        if (total >= WEAPONSEXPANDED$MAX_TOTAL_SHOTS) {
            // Server plays the sound; client just returns FAIL to avoid clearing CHARGED_PROJECTILES.
            if (!world.isClient()) {
                user.getEntityWorld().playSound(
                        null,
                        user.getX(), user.getY(), user.getZ(),
                        ModSounds.CHAIN_CROSSBOW_FULL,
                        SoundCategory.PLAYERS,
                        1.0F,
                        1.0F
                );
                weaponsexpanded$setCustomNbt(crossbow, root);

                // Keep visuals consistent in case anything changed earlier.
                weaponsexpanded$refreshLoadedVisual(crossbow);
            }

            return ActionResult.FAIL;
        }

        // If client and not full, let vanilla handle hand animation, etc.
        if (world.isClient()) {
            return super.use(world, user, hand);
        }

        // Force-recovery logic:
        // If vanilla says "not charged", but our custom NBT indicates we still have a shot,
        // force-load it into CHARGED_PROJECTILES.
        if (!isChargedNow) {
            // 1) Restore from saved chamber first (this represents the "current" shot)
            if (hasSavedChamber) {
                NbtCompound saved = root.getCompound(WEAPONSEXPANDED$SAVED_CHAMBER_KEY).orElse(null);
                root.remove(WEAPONSEXPANDED$SAVED_CHAMBER_KEY);
                weaponsexpanded$setCustomNbt(crossbow, root);

                if (saved != null) {
                    weaponsexpanded$applyChamberToCrossbow(world, crossbow, saved);
                    if (user instanceof ServerPlayerEntity serverPlayer) {
                        serverPlayer.currentScreenHandler.syncState();
                    }
                    return ActionResult.CONSUME;
                }
            }

            // 2) Otherwise, pull from queued chambers
            if (queued > 0) {
                List<ItemStack> next = weaponsexpanded$popNextChamber(world, crossbow);
                if (!next.isEmpty()) {
                    crossbow.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.of(next));
                    if (user instanceof ServerPlayerEntity serverPlayer) {
                        serverPlayer.currentScreenHandler.syncState();
                    }
                    return ActionResult.CONSUME;
                }
            }
        }

        // Topping up while already charged:
        // Save the current chamber, clear it, then let vanilla load another (consumes ammo).
        if (isChargedNow) {
            root.put(WEAPONSEXPANDED$SAVED_CHAMBER_KEY, weaponsexpanded$encodeChamber(world, crossbow));
            weaponsexpanded$setCustomNbt(crossbow, root);

            crossbow.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);

            ActionResult vanillaResult = super.use(world, user, hand);

            // If vanilla couldn't start loading (e.g., no ammo), restore the saved chamber immediately.
            if (vanillaResult == ActionResult.FAIL) {
                NbtCompound restoreRoot = weaponsexpanded$getOrCreateCustomNbt(crossbow);
                NbtCompound saved = restoreRoot.getCompound(WEAPONSEXPANDED$SAVED_CHAMBER_KEY).orElse(null);
                restoreRoot.remove(WEAPONSEXPANDED$SAVED_CHAMBER_KEY);
                weaponsexpanded$setCustomNbt(crossbow, restoreRoot);

                if (saved != null) {
                    weaponsexpanded$applyChamberToCrossbow(world, crossbow, saved);
                }
            }

            return vanillaResult;
        }

        weaponsexpanded$setCustomNbt(crossbow, root);
        return super.use(world, user, hand);
    }

    @Override
    public void shootAll(World world, LivingEntity shooter, Hand hand, ItemStack stack, float speed, float divergence, LivingEntity target) {
        // We’re about to consume the charged projectiles; clear the flag so the model returns to normal.
        stack.remove(DataComponentTypes.CUSTOM_MODEL_DATA);

        super.shootAll(world, shooter, hand, stack, speed, divergence, target);
    }

    @Override
    public boolean onStoppedUsing(ItemStack crossbow, World world, LivingEntity user, int remainingUseTicks) {
        boolean result = super.onStoppedUsing(crossbow, world, user, remainingUseTicks);

        if (world.isClient()) return result;

        NbtCompound root = weaponsexpanded$getOrCreateCustomNbt(crossbow);
        weaponsexpanded$trimQueueToMax(root);

        boolean toppingUp = root.contains(WEAPONSEXPANDED$SAVED_CHAMBER_KEY);

        if (result && CrossbowItem.isCharged(crossbow) && toppingUp) {
            weaponsexpanded$appendCurrentChamberToQueue(world, crossbow, root);
        }

        if (toppingUp) {
            NbtCompound saved = root.getCompound(WEAPONSEXPANDED$SAVED_CHAMBER_KEY).orElse(null);
            root.remove(WEAPONSEXPANDED$SAVED_CHAMBER_KEY);

            if (saved != null) {
                weaponsexpanded$applyChamberToCrossbow(world, crossbow, saved);
            }
        }

        weaponsexpanded$setCustomNbt(crossbow, root);

        // Refresh model flag after charging/restoring chambers
        weaponsexpanded$updateLoadedVisual(crossbow);

        return result;
    }

    public static List<ItemStack> weaponsexpanded$popNextChamber(World world, ItemStack crossbow) {
        NbtCompound root = weaponsexpanded$getOrCreateCustomNbt(crossbow);
        NbtList queue = root.getListOrEmpty(WEAPONSEXPANDED$QUEUE_KEY);
        if (queue.isEmpty()) return List.of();

        NbtCompound chamber = queue.getFirst().asCompound().orElse(null);
        queue.removeFirst();

        if (queue.isEmpty()) {
            root.remove(WEAPONSEXPANDED$QUEUE_KEY);
        } else {
            root.put(WEAPONSEXPANDED$QUEUE_KEY, queue);
        }
        weaponsexpanded$setCustomNbt(crossbow, root);

        if (chamber == null) return List.of();
        return weaponsexpanded$decodeChamber(world, chamber);
    }

    public static int weaponsexpanded$getQueuedChambers(ItemStack crossbow) {
        NbtCompound root = weaponsexpanded$getOrCreateCustomNbt(crossbow);
        return root.getListOrEmpty(WEAPONSEXPANDED$QUEUE_KEY).size();
    }

    private static void weaponsexpanded$trimQueueToMax(NbtCompound root) {
        NbtList queue = root.getListOrEmpty(WEAPONSEXPANDED$QUEUE_KEY);
        int queuedMax = WEAPONSEXPANDED$MAX_TOTAL_SHOTS - 1;

        if (queue.size() <= queuedMax) return;

        while (queue.size() > queuedMax) {
            queue.removeLast();
        }

        if (queue.isEmpty()) {
            root.remove(WEAPONSEXPANDED$QUEUE_KEY);
        } else {
            root.put(WEAPONSEXPANDED$QUEUE_KEY, queue);
        }
    }

    private static void weaponsexpanded$appendCurrentChamberToQueue(World world, ItemStack crossbow, NbtCompound root) {
        NbtList queue = root.getListOrEmpty(WEAPONSEXPANDED$QUEUE_KEY);
        int queuedMax = WEAPONSEXPANDED$MAX_TOTAL_SHOTS - 1;
        if (queue.size() >= queuedMax) return;

        queue.add(weaponsexpanded$encodeChamber(world, crossbow));
        root.put(WEAPONSEXPANDED$QUEUE_KEY, queue);
    }

    private static NbtCompound weaponsexpanded$encodeChamber(World world, ItemStack crossbow) {
        ChargedProjectilesComponent charged =
                crossbow.getOrDefault(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);

        NbtCompound chamber = new NbtCompound();
        NbtList projectiles = new NbtList();

        for (ItemStack p : charged.getProjectiles()) {
            ItemStack one = p.copy();
            one.setCount(1);
            projectiles.add(weaponsexpanded$encodeStack(world, one));
        }

        chamber.put("projectiles", projectiles);
        return chamber;
    }

    private static List<ItemStack> weaponsexpanded$decodeChamber(World world, NbtCompound chamber) {
        List<ItemStack> out = new ArrayList<>();
        NbtList list = chamber.getListOrEmpty("projectiles");

        for (NbtElement nbtElement : list) {
            NbtCompound stackTag = nbtElement.asCompound().orElse(null);
            if (stackTag == null) continue;

            ItemStack decoded = weaponsexpanded$decodeStack(world, stackTag);
            if (!decoded.isEmpty()) {
                decoded.setCount(1);
                out.add(decoded);
            }
        }

        return out;
    }

    private static void weaponsexpanded$applyChamberToCrossbow(World world, ItemStack crossbow, NbtCompound chamber) {
        List<ItemStack> projectiles = weaponsexpanded$decodeChamber(world, chamber);
        if (projectiles.isEmpty()) {
            crossbow.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);
        } else {
            crossbow.set(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.of(projectiles));
        }
    }

    private static NbtCompound weaponsexpanded$getOrCreateCustomNbt(ItemStack stack) {
        NbtComponent custom = stack.get(DataComponentTypes.CUSTOM_DATA);
        return (custom != null) ? custom.copyNbt() : new NbtCompound();
    }

    private static void weaponsexpanded$setCustomNbt(ItemStack stack, NbtCompound nbt) {
        if (nbt.isEmpty()) {
            stack.remove(DataComponentTypes.CUSTOM_DATA);
        } else {
            stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
        }
    }

    private static NbtCompound weaponsexpanded$encodeStack(World world, ItemStack stack) {
        var ops = world.getRegistryManager().getOps(NbtOps.INSTANCE);
        NbtElement elem = ItemStack.CODEC.encodeStart(ops, stack).getOrThrow();
        return elem.asCompound().orElseGet(NbtCompound::new);
    }

    private static ItemStack weaponsexpanded$decodeStack(World world, NbtCompound nbt) {
        var ops = world.getRegistryManager().getOps(NbtOps.INSTANCE);
        return ItemStack.CODEC.parse(ops, nbt).result().orElse(ItemStack.EMPTY);
    }
}