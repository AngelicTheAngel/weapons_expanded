package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public final class ProjectileEnchantmentApplier {
    private ProjectileEnchantmentApplier() {}

    public static void applyFreezeAndFlame(
            Level level,
            ItemStack weaponStack,
            AbstractArrow projectile
    ) {
        if (level.isClientSide) {
            return;
        }

        applyFreeze(level, weaponStack, projectile);
        applyFlame(level, weaponStack, projectile);
    }

    public static void applyPowerAndPunchForHeavyArrow(
            Level level,
            ItemStack weaponStack,
            HeavyArrowEntity arrow
    ) {
        if (level.isClientSide) {
            return;
        }

        int powerLevel =
                EnchantmentHelper.getTagEnchantmentLevel(
                        Enchantments.POWER_ARROWS,
                        weaponStack
                );

        int punchLevel =
                EnchantmentHelper.getTagEnchantmentLevel(
                        Enchantments.PUNCH_ARROWS,
                        weaponStack
                );

        if (powerLevel > 0) {
            double bonusDamage =
                    powerLevel * 0.5D + 0.5D;

            arrow.setBaseDamage(
                    HeavyArrowEntity.BASE_DAMAGE + bonusDamage
            );
        } else {
            arrow.setBaseDamage(
                    HeavyArrowEntity.BASE_DAMAGE
            );
        }

        arrow.weaponsexpanded$setPunchLevel(punchLevel);
    }

    public static void applyFreeze(
            Level level,
            ItemStack weaponStack,
            AbstractArrow projectile
    ) {
        if (level.isClientSide || weaponStack.isEmpty()) {
            return;
        }

        int enchantmentLevel =
                EnchantmentHelper.getTagEnchantmentLevel(
                        ModEnchantments.FREEZE.get(),
                        weaponStack
                );

        if (enchantmentLevel <= 0) {
            return;
        }

        String tag =
                "weaponsexpanded.freeze.level."
                        + enchantmentLevel;

        if (!projectile.getTags().contains(tag)) {
            projectile.addTag(tag);
        }
    }

    private static void applyFlame(
            Level level,
            ItemStack weaponStack,
            AbstractArrow projectile
    ) {
        if (level.isClientSide || weaponStack.isEmpty()) {
            return;
        }

        int enchantmentLevel =
                EnchantmentHelper.getTagEnchantmentLevel(
                        Enchantments.FLAMING_ARROWS,
                        weaponStack
                );

        if (enchantmentLevel > 0) {
            projectile.setSecondsOnFire(5);
        }
    }
}