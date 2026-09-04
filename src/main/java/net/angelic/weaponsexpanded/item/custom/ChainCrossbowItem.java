package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.List;

import net.angelic.weaponsexpanded.item.ModItems;

public class ChainCrossbowItem extends CrossbowItem {

    private static final String WEAPONSEXPANDED$QUEUE_KEY = "weaponsexpanded:chain_crossbow_queue";
    private static final String WEAPONSEXPANDED$SAVED_CHAMBER_KEY = "weaponsexpanded:chain_crossbow_saved_chamber";
    private static final String WEAPONSEXPANDED$MAX_TOTAL_SHOTS_KEY = "weaponsexpanded:chain_crossbow_max_total_shots";

    // Use float slot 0 in CustomModelDataComponent to drive item model selection
    private static final float WEAPONSEXPANDED$CMD_EXPLOSIVE_LOADED = 1.0F;

    public void setMaxShots(ItemStack stack, int level) {
        int maxTotalShots = Math.max(1, WeaponsExpandedConfig.get().chainCrossbowMagazineSize + (level * WeaponsExpandedConfig.get().chainCrossbowExtraSizePerCapacityLevel));
        CompoundTag root = weaponsexpanded$getOrCreateCustomNbt(stack);

        if (root.getInt(WEAPONSEXPANDED$MAX_TOTAL_SHOTS_KEY).orElse(-1) == maxTotalShots) {
            return;
        }

        root.putInt(WEAPONSEXPANDED$MAX_TOTAL_SHOTS_KEY, maxTotalShots);
        weaponsexpanded$setCustomNbt(stack, root);
    }

    private static int weaponsexpanded$getMaxTotalShots(ItemStack stack) {
        CompoundTag root = weaponsexpanded$getOrCreateCustomNbt(stack);
        return Math.max(1, root.getInt(WEAPONSEXPANDED$MAX_TOTAL_SHOTS_KEY)
                .orElse(WeaponsExpandedConfig.get().chainCrossbowMagazineSize));
    }

    private static void weaponsexpanded$updateLoadedVisual(ItemStack stack) {
        ChargedProjectiles charged =
                stack.getOrDefault(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);

        boolean hasExplosive = charged.items().stream()
                .anyMatch(s -> s.item().value() == ModItems.EXPLOSIVE_ARROW);

        if (hasExplosive) {
            stack.set(
                    DataComponents.CUSTOM_MODEL_DATA,
                    new CustomModelData(List.of(WEAPONSEXPANDED$CMD_EXPLOSIVE_LOADED), List.of(), List.of(), List.of())
            );
        } else {
            stack.remove(DataComponents.CUSTOM_MODEL_DATA);
        }
    }

    /**
     * Public hook for mixins/other code paths (auto-reload, restore, etc.)
     * to refresh the item model state after CHARGED_PROJECTILES changes.
     */
    public static void weaponsexpanded$refreshLoadedVisual(ItemStack stack) {
        weaponsexpanded$updateLoadedVisual(stack);
    }

    public ChainCrossbowItem(Properties settings) {
        super(settings);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent,
                                Consumer<Component> textConsumer, TooltipFlag type) {
        // queued shots (not counting the current chamber)
        int queued = weaponsexpanded$getQueuedChambers(stack);

        // current-or-saved chamber counts as one "loaded" shot for display
        CompoundTag root = weaponsexpanded$getOrCreateCustomNbt(stack);
        boolean hasSaved = root.contains(WEAPONSEXPANDED$SAVED_CHAMBER_KEY);
        boolean isCharged = CrossbowItem.isCharged(stack);
        int currentOrSaved = (isCharged || hasSaved) ? 1 : 0;

        int maxTotalShots = weaponsexpanded$getMaxTotalShots(stack);
        int total = Math.min(maxTotalShots, currentOrSaved + queued);

        textConsumer.accept(Component.translatable("tooltip.weaponsexpanded.chain_crossbow_shots", total, maxTotalShots));

        super.appendHoverText(stack, context, displayComponent, textConsumer, type);
    }

    @Override
    public InteractionResult use(Level world, Player user, InteractionHand hand) {
        ItemStack crossbow = user.getItemInHand(hand);

        // Compute fullness on BOTH sides so the client doesn't run vanilla "shootAll" when full.
        CompoundTag root = weaponsexpanded$getOrCreateCustomNbt(crossbow);
        int maxTotalShots = weaponsexpanded$getMaxTotalShots(crossbow);
        weaponsexpanded$trimQueueToMax(root, maxTotalShots);

        boolean hasSavedChamber = root.contains(WEAPONSEXPANDED$SAVED_CHAMBER_KEY);
        boolean isChargedNow = CrossbowItem.isCharged(crossbow);
        int queued = root.getListOrEmpty(WEAPONSEXPANDED$QUEUE_KEY).size();

        int currentOrSaved = (isChargedNow || hasSavedChamber) ? 1 : 0;
        int total = currentOrSaved + queued;

        if (total >= maxTotalShots) {
            // Server plays the sound; client just returns FAIL to avoid clearing CHARGED_PROJECTILES.
            if (!world.isClientSide()) {
                user.level().playSound(
                        null,
                        user.getX(), user.getY(), user.getZ(),
                        ModSounds.CHAIN_CROSSBOW_FULL,
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );
                weaponsexpanded$setCustomNbt(crossbow, root);

                // Keep visuals consistent in case anything changed earlier.
                weaponsexpanded$refreshLoadedVisual(crossbow);
            }

            return InteractionResult.FAIL;
        }

        if (isChargedNow && user.getProjectile(crossbow).isEmpty()) {
            weaponsexpanded$refreshLoadedVisual(crossbow);
            return InteractionResult.FAIL;
        }

        // If client and not full, let vanilla handle hand animation, etc.
        if (world.isClientSide()) {
            return super.use(world, user, hand);
        }

        // Force-recovery logic:
        // If vanilla says "not charged", but custom NBT indicates we still have a shot,
        // force-load it into CHARGED_PROJECTILES.
        if (!isChargedNow) {
            // 1) Restore from saved chamber first (this represents the "current" shot)
            if (hasSavedChamber) {
                CompoundTag saved = root.getCompound(WEAPONSEXPANDED$SAVED_CHAMBER_KEY).orElse(null);
                root.remove(WEAPONSEXPANDED$SAVED_CHAMBER_KEY);
                weaponsexpanded$setCustomNbt(crossbow, root);

                if (saved != null) {
                    weaponsexpanded$applyChamberToCrossbow(world, crossbow, saved);
                    if (user instanceof ServerPlayer serverPlayer) {
                        serverPlayer.containerMenu.sendAllDataToRemote();
                    }
                    return InteractionResult.CONSUME;
                }
            }

            // 2) Otherwise, pull from queued chambers
            if (queued > 0) {
                List<ItemStackTemplate> next = weaponsexpanded$popNextChamber(world, crossbow);
                if (!next.isEmpty()) {
                    crossbow.set(DataComponents.CHARGED_PROJECTILES, new ChargedProjectiles(next));
                    weaponsexpanded$refreshLoadedVisual(crossbow);
                    if (user instanceof ServerPlayer serverPlayer) {
                        serverPlayer.containerMenu.sendAllDataToRemote();
                    }
                    return InteractionResult.CONSUME;
                }
            }
        }

        // Topping up while already charged:
        // Save the current chamber, clear it, then let vanilla load another (consumes ammo).
        if (isChargedNow) {
            root.put(WEAPONSEXPANDED$SAVED_CHAMBER_KEY, weaponsexpanded$encodeChamber(world, crossbow));
            weaponsexpanded$setCustomNbt(crossbow, root);

            crossbow.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);

            InteractionResult vanillaResult = super.use(world, user, hand);

            // If vanilla couldn't start loading (e.g., no ammo), restore the saved chamber immediately.
            if (vanillaResult == InteractionResult.FAIL) {
                CompoundTag restoreRoot = weaponsexpanded$getOrCreateCustomNbt(crossbow);
                CompoundTag saved = restoreRoot.getCompound(WEAPONSEXPANDED$SAVED_CHAMBER_KEY).orElse(null);
                restoreRoot.remove(WEAPONSEXPANDED$SAVED_CHAMBER_KEY);
                weaponsexpanded$setCustomNbt(crossbow, restoreRoot);

                if (saved != null) {
                    weaponsexpanded$applyChamberToCrossbow(world, crossbow, saved);
                    weaponsexpanded$refreshLoadedVisual(crossbow);
                }
            }

            return vanillaResult;
        }

        weaponsexpanded$setCustomNbt(crossbow, root);
        return super.use(world, user, hand);
    }

    @Override
    public void performShooting(Level world, LivingEntity shooter, InteractionHand hand, ItemStack stack, float speed, float divergence, LivingEntity target) {
        super.performShooting(world, shooter, hand, stack, speed, divergence, target);
    }

    @Override
    public boolean releaseUsing(ItemStack crossbow, Level world, LivingEntity user, int remainingUseTicks) {
        boolean result = super.releaseUsing(crossbow, world, user, remainingUseTicks);

        if (world.isClientSide()) return result;

        CompoundTag root = weaponsexpanded$getOrCreateCustomNbt(crossbow);
        int maxTotalShots = weaponsexpanded$getMaxTotalShots(crossbow);
        weaponsexpanded$trimQueueToMax(root, maxTotalShots);

        boolean toppingUp = root.contains(WEAPONSEXPANDED$SAVED_CHAMBER_KEY);

        if (result && CrossbowItem.isCharged(crossbow) && toppingUp) {
            weaponsexpanded$appendCurrentChamberToQueue(world, crossbow, root, maxTotalShots);
        }

        if (toppingUp) {
            CompoundTag saved = root.getCompound(WEAPONSEXPANDED$SAVED_CHAMBER_KEY).orElse(null);
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

    public static List<ItemStackTemplate> weaponsexpanded$popNextChamber(Level world, ItemStack crossbow) {
        CompoundTag root = weaponsexpanded$getOrCreateCustomNbt(crossbow);
        ListTag queue = root.getListOrEmpty(WEAPONSEXPANDED$QUEUE_KEY);
        if (queue.isEmpty()) return List.of();

        CompoundTag chamber = queue.getFirst().asCompound().orElse(null);
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
        CompoundTag root = weaponsexpanded$getOrCreateCustomNbt(crossbow);
        return root.getListOrEmpty(WEAPONSEXPANDED$QUEUE_KEY).size();
    }

    private static void weaponsexpanded$trimQueueToMax(CompoundTag root, int maxTotalShots) {
        ListTag queue = root.getListOrEmpty(WEAPONSEXPANDED$QUEUE_KEY);
        int queuedMax = maxTotalShots - 1;

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

    private static void weaponsexpanded$appendCurrentChamberToQueue(Level world, ItemStack crossbow, CompoundTag root, int maxTotalShots) {
        ListTag queue = root.getListOrEmpty(WEAPONSEXPANDED$QUEUE_KEY);
        int queuedMax = maxTotalShots - 1;
        if (queue.size() >= queuedMax) return;

        queue.add(weaponsexpanded$encodeChamber(world, crossbow));
        root.put(WEAPONSEXPANDED$QUEUE_KEY, queue);
    }

    private static CompoundTag weaponsexpanded$encodeChamber(Level world, ItemStack crossbow) {
        ChargedProjectiles charged =
                crossbow.getOrDefault(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);

        CompoundTag chamber = new CompoundTag();
        ListTag projectiles = new ListTag();

        for (ItemStackTemplate template : charged.items()) {
            projectiles.add(weaponsexpanded$encodeTemplate(world, template));
        }

        chamber.put("projectiles", projectiles);
        return chamber;
    }

    private static List<ItemStackTemplate> weaponsexpanded$decodeChamber(Level world, CompoundTag chamber) {
        List<ItemStackTemplate> out = new ArrayList<>();
        ListTag list = chamber.getListOrEmpty("projectiles");

        for (Tag nbtElement : list) {
            CompoundTag templateTag = nbtElement.asCompound().orElse(null);
            if (templateTag == null) continue;

            ItemStackTemplate decoded = weaponsexpanded$decodeTemplate(world, templateTag);
            if (decoded != null) {
                out.add(decoded);
            }
        }

        return out;
    }

    private static void weaponsexpanded$applyChamberToCrossbow(Level world, ItemStack crossbow, CompoundTag chamber) {
        List<ItemStackTemplate> projectiles = weaponsexpanded$decodeChamber(world, chamber);
        if (projectiles.isEmpty()) {
            crossbow.set(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);
        } else {
            crossbow.set(DataComponents.CHARGED_PROJECTILES, new ChargedProjectiles(projectiles));
        }
        weaponsexpanded$refreshLoadedVisual(crossbow);
    }

    private static CompoundTag weaponsexpanded$getOrCreateCustomNbt(ItemStack stack) {
        CustomData custom = stack.get(DataComponents.CUSTOM_DATA);
        return (custom != null) ? custom.copyTag() : new CompoundTag();
    }

    private static void weaponsexpanded$setCustomNbt(ItemStack stack, CompoundTag nbt) {
        if (nbt.isEmpty()) {
            stack.remove(DataComponents.CUSTOM_DATA);
        } else {
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(nbt));
        }
    }

    private static CompoundTag weaponsexpanded$encodeTemplate(Level world, ItemStackTemplate template) {
        var ops = world.registryAccess().createSerializationContext(NbtOps.INSTANCE);
        Tag elem = ItemStackTemplate.CODEC.encodeStart(ops, template).getOrThrow();
        return elem.asCompound().orElseGet(CompoundTag::new);
    }

    private static ItemStackTemplate weaponsexpanded$decodeTemplate(Level world, CompoundTag nbt) {
        var ops = world.registryAccess().createSerializationContext(NbtOps.INSTANCE);
        return ItemStackTemplate.CODEC.parse(ops, nbt).result().orElse(null);
    }
}