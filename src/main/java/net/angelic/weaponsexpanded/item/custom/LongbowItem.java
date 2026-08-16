package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.util.ProjectileEnchantmentApplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class LongbowItem extends BowItem {
    // Vanilla bows reach full power after 20 ticks.
    private static final int FULL_DRAW_TICKS = 32;

    // Vanilla bow projectile velocity is 3.0F.
    private static final float VELOCITY_MULTIPLIER = 4.0F;

    public LongbowItem(Item.Properties properties) {
        super(properties);
    }

    private static float getLongbowPullProgress(int useTicks) {
        float progress = (float) useTicks / FULL_DRAW_TICKS;
        progress = (progress * progress + progress * 2.0F) / 3.0F;

        return Math.min(progress, 1.0F);
    }

    private static int getEnchantmentLevel(
            Level level,
            ItemStack stack,
            ResourceKey<Enchantment> enchantment
    ) {
        return stack.getEnchantmentLevel(
                level.registryAccess()
                        .lookupOrThrow(Registries.ENCHANTMENT)
                        .getOrThrow(enchantment)
        );
    }

    private static boolean hasInfinity(
            Level level,
            ItemStack bowStack
    ) {
        return getEnchantmentLevel(
                level,
                bowStack,
                Enchantments.INFINITY
        ) > 0;
    }

    private static boolean isNormalArrow(ItemStack ammo) {
        return ammo.is(Items.ARROW);
    }

    @Override
    public boolean releaseUsing(
            ItemStack stack,
            Level level,
            LivingEntity user,
            int remainingUseTicks
    ) {
        if (!(user instanceof Player player)) {
            return false;
        }

        ItemStack ammo = player.getProjectile(stack);
        boolean infinity = hasInfinity(level, stack);

        // Infinity only supplies and preserves normal arrows.
        boolean infinityCoversShot =
                infinity && (ammo.isEmpty() || isNormalArrow(ammo));

        boolean canShootWithoutAmmo =
                player.getAbilities().instabuild
                        || infinityCoversShot;

        int usedTicks =
                this.getUseDuration(stack, user) - remainingUseTicks;

        float pullProgress =
                getLongbowPullProgress(usedTicks);

        if (pullProgress < 0.1F) {
            return infinity;
        }

        if (ammo.isEmpty()) {
            if (!canShootWithoutAmmo) {
                return infinity;
            }

            ammo = new ItemStack(Items.ARROW);
        }

        if (!(ammo.getItem() instanceof ArrowItem arrowItem)) {
            return infinity;
        }

        boolean infinityFreeNormalArrow =
                infinity && isNormalArrow(ammo);

        if (!level.isClientSide()) {
            AbstractArrow projectile;
            boolean heavyArrow =
                    arrowItem instanceof HeavyArrowItem;

            if (arrowItem instanceof HeavyArrowItem heavyArrowItem) {
                /*
                 * This calls the custom overload that receives the
                 * firing weapon stack. HeavyArrowItem applies Power
                 * and Punch itself.
                 */
                projectile = heavyArrowItem.createArrow(
                        level,
                        ammo,
                        player,
                        stack
                );
            } else {
                projectile = arrowItem.createArrow(
                        level,
                        ammo,
                        player,
                        stack
                );
            }

            if (pullProgress >= 1.0F) {
                projectile.setCritArrow(true);
            }

            /*
             * HeavyArrowItem applies these itself because it uses a
             * different base-damage calculation.
             */
            if (!heavyArrow && level instanceof ServerLevel serverLevel) {
                EnchantmentHelper.onProjectileSpawned(
                        serverLevel,
                        stack,
                        projectile,
                        brokenItem -> player.onEquippedItemBroken(
                                brokenItem,
                                EquipmentSlot.MAINHAND
                        )
                );
            }

            // Applies the custom Freeze enchantment and vanilla Flame.
            ProjectileEnchantmentApplier.applyFreezeAndFlame(
                    level,
                    stack,
                    projectile
            );

            if (player.getAbilities().instabuild
                    || infinityFreeNormalArrow) {
                projectile.pickup =
                        AbstractArrow.Pickup.CREATIVE_ONLY;
            } else {
                projectile.pickup =
                        AbstractArrow.Pickup.ALLOWED;
            }

            projectile.shootFromRotation(
                    player,
                    player.getXRot(),
                    player.getYRot(),
                    0.0F,
                    pullProgress * VELOCITY_MULTIPLIER,
                    1.0F
            );

            stack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);

            level.addFreshEntity(projectile);
        }

        level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ARROW_SHOOT,
                SoundSource.PLAYERS,
                1.0F,
                1.0F / (
                        level.getRandom().nextFloat() * 0.4F
                                + 1.2F
                ) + pullProgress * 0.5F
        );

        player.awardStat(
                Stats.ITEM_USED.get(this)
        );

        /*
         * Creative never consumes ammunition.
         * Infinity only preserves normal arrows.
         */
        if (!player.getAbilities().instabuild
                && !infinityFreeNormalArrow) {
            ammo.shrink(1);

            if (ammo.isEmpty()) {
                player.getInventory().removeItem(ammo);
            }
        }
        return infinity;
    }

    public static int getFullDrawTicks() {
        return FULL_DRAW_TICKS;
    }
}