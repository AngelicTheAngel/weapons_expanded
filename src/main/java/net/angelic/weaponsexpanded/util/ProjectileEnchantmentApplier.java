package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public final class ProjectileEnchantmentApplier {
    private ProjectileEnchantmentApplier() {
    }

    public static void applyFreezeAndFlame(
            Level level,
            ItemStack weaponStack,
            AbstractArrow projectile
    ) {
        if (level.isClientSide()) {
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
        if (level.isClientSide() || weaponStack.isEmpty()) {
            return;
        }

        int powerLevel = getEnchantmentLevel(
                level,
                weaponStack,
                Enchantments.POWER
        );

        int punchLevel = getEnchantmentLevel(
                level,
                weaponStack,
                Enchantments.PUNCH
        );

        double bonusDamage = powerLevel > 0
                ? powerLevel * 0.5D + 0.5D
                : 0.0D;

        arrow.setBaseDamage(
                HeavyArrowEntity.BASE_DAMAGE + bonusDamage
        );

        arrow.weaponsexpanded$setPunchLevel(punchLevel);
    }

    public static void applyFreeze(
            Level level,
            ItemStack weaponStack,
            AbstractArrow projectile
    ) {
        if (level.isClientSide() || weaponStack.isEmpty()) {
            return;
        }

        int enchantmentLevel = getEnchantmentLevel(
                level,
                weaponStack,
                ModEnchantments.FREEZE
        );

        if (enchantmentLevel <= 0) {
            return;
        }

        String tag =
                "weaponsexpanded.freeze.level."
                        + enchantmentLevel;

        projectile.addTag(tag);
    }

    private static void applyFlame(
            Level level,
            ItemStack weaponStack,
            AbstractArrow projectile
    ) {
        if (level.isClientSide() || weaponStack.isEmpty()) {
            return;
        }

        int enchantmentLevel = getEnchantmentLevel(
                level,
                weaponStack,
                Enchantments.FLAME
        );

        if (enchantmentLevel > 0) {
            projectile.igniteForSeconds(5.0F);
        }
    }

    private static int getEnchantmentLevel(
            Level level,
            ItemStack stack,
            ResourceKey<Enchantment> enchantmentKey
    ) {
        Holder<Enchantment> enchantment = level.registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(enchantmentKey);

        return stack.getEnchantmentLevel(enchantment);
    }
}