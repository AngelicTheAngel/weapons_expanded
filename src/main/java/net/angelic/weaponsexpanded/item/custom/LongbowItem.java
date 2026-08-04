package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.util.ProjectileEnchantmentApplier;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;

public class LongbowItem extends BowItem {
    // Vanilla bows reach full power after 20 ticks.
    private static final int FULL_DRAW_TICKS = 32;

    // Vanilla bow projectile velocity is 3.0F.
    private static final float VELOCITY_MULTIPLIER = 4.0F;

    public LongbowItem(Settings settings) {
        super(settings);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    private static float getLongbowPullProgress(int useTicks) {
        float progress = (float) useTicks / FULL_DRAW_TICKS;
        progress = (progress * progress + progress * 2.0F) / 3.0F;

        return Math.min(progress, 1.0F);
    }

    private static boolean hasInfinity(ItemStack bowStack) {
        return EnchantmentHelper.getLevel(
                Enchantments.INFINITY,
                bowStack
        ) > 0;
    }

    private static boolean isNormalArrow(ItemStack ammo) {
        return ammo.isOf(Items.ARROW);
    }

    @Override
    public void onStoppedUsing(
            ItemStack stack,
            World world,
            LivingEntity user,
            int remainingUseTicks
    ) {
        if (!(user instanceof PlayerEntity player)) {
            return;
        }

        ItemStack ammo = player.getProjectileType(stack);

        boolean infinity = hasInfinity(stack);

        // Infinity only supplies and preserves normal arrows.
        boolean infinityCoversShot =
                infinity && (ammo.isEmpty() || isNormalArrow(ammo));

        boolean canShootWithoutAmmo =
                player.getAbilities().creativeMode
                        || infinityCoversShot;

        int usedTicks =
                this.getMaxUseTime(stack) - remainingUseTicks;

        float pullProgress =
                getLongbowPullProgress(usedTicks);

        if (pullProgress < 0.1F) {
            return;
        }

        if (ammo.isEmpty()) {
            if (!canShootWithoutAmmo) {
                return;
            }

            ammo = new ItemStack(Items.ARROW);
        }

        if (!(ammo.getItem() instanceof ArrowItem arrowItem)) {
            return;
        }

        boolean infinityFreeNormalArrow =
                infinity && isNormalArrow(ammo);

        if (!world.isClient) {
            PersistentProjectileEntity projectile;
            boolean heavyArrow =
                    arrowItem instanceof HeavyArrowItem;

            if (arrowItem instanceof HeavyArrowItem heavyArrowItem) {
                /*
                 * This calls the custom overload that receives the
                 * firing weapon stack. HeavyArrowItem applies Power
                 * and Punch itself.
                 */
                projectile = heavyArrowItem.createArrow(
                        world,
                        ammo,
                        player,
                        stack
                );
            } else {
                /*
                 * Vanilla 1.20.1 ArrowItem only has this
                 * three-argument method.
                 */
                projectile = arrowItem.createArrow(
                        world,
                        ammo,
                        player
                );
            }

            if (pullProgress >= 1.0F) {
                projectile.setCritical(true);
            }

            /*
             * HeavyArrowItem applies these itself because it uses a
             * different base-damage calculation.
             */
            if (!heavyArrow) {
                applyPowerAndPunch(stack, projectile);
            }

            // Applies the custom Freeze enchantment and vanilla Flame.
            ProjectileEnchantmentApplier.applyFreezeAndFlame(
                    world,
                    stack,
                    projectile
            );

            if (player.getAbilities().creativeMode) {
                projectile.pickupType =
                        PersistentProjectileEntity
                                .PickupPermission
                                .CREATIVE_ONLY;
            } else if (infinityFreeNormalArrow) {
                projectile.pickupType =
                        PersistentProjectileEntity
                                .PickupPermission
                                .CREATIVE_ONLY;
            } else {
                projectile.pickupType =
                        PersistentProjectileEntity
                                .PickupPermission
                                .ALLOWED;
            }

            projectile.setVelocity(
                    player,
                    player.getPitch(),
                    player.getYaw(),
                    0.0F,
                    pullProgress * VELOCITY_MULTIPLIER,
                    1.0F
            );

            stack.damage(
                    1,
                    player,
                    entity -> entity.sendToolBreakStatus(
                            player.getActiveHand()
                    )
            );

            world.spawnEntity(projectile);
        }

        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ENTITY_ARROW_SHOOT,
                SoundCategory.PLAYERS,
                1.0F,
                1.0F / (
                        world.getRandom().nextFloat() * 0.4F
                                + 1.2F
                ) + pullProgress * 0.5F
        );

        player.incrementStat(
                Stats.USED.getOrCreateStat(this)
        );

        /*
         * Creative never consumes ammunition.
         * Infinity only preserves normal arrows.
         */
        if (!player.getAbilities().creativeMode
                && !infinityFreeNormalArrow) {
            ammo.decrement(1);

            if (ammo.isEmpty()) {
                player.getInventory().removeOne(ammo);
            }
        }
    }

    private static void applyPowerAndPunch(
            ItemStack weaponStack,
            PersistentProjectileEntity projectile
    ) {
        int powerLevel = EnchantmentHelper.getLevel(
                Enchantments.POWER,
                weaponStack
        );

        if (powerLevel > 0) {
            projectile.setDamage(
                    projectile.getDamage()
                            + powerLevel * 0.5D
                            + 0.5D
            );
        }

        int punchLevel = EnchantmentHelper.getLevel(
                Enchantments.PUNCH,
                weaponStack
        );

        if (punchLevel > 0) {
            projectile.setPunch(punchLevel);
        }
    }

    public static int getFullDrawTicks() {
        return FULL_DRAW_TICKS;
    }
}