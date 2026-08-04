package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public final class ProjectileEnchantmentApplier {
    private ProjectileEnchantmentApplier() {}

    public static void applyFreezeAndFlame(
            World world,
            ItemStack weaponStack,
            PersistentProjectileEntity projectile
    ) {
        if (world.isClient) {
            return;
        }

        applyFreeze(world, weaponStack, projectile);
        applyFlame(world, weaponStack, projectile);
    }

    public static void applyPowerAndPunchForHeavyArrow(
            World world,
            ItemStack weaponStack,
            HeavyArrowEntity arrow
    ) {
        if (world.isClient) {
            return;
        }

        int powerLevel = EnchantmentHelper.getLevel(
                Enchantments.POWER,
                weaponStack
        );

        int punchLevel = EnchantmentHelper.getLevel(
                Enchantments.PUNCH,
                weaponStack
        );

        if (powerLevel > 0) {
            double bonusDamage = powerLevel * 0.5D + 0.5D;
            arrow.setDamage(
                    HeavyArrowEntity.BASE_DAMAGE + bonusDamage
            );
        } else {
            arrow.setDamage(HeavyArrowEntity.BASE_DAMAGE);
        }

        arrow.weaponsexpanded$setPunchLevel(punchLevel);
    }

    public static void applyFreeze(
            World world,
            ItemStack weaponStack,
            PersistentProjectileEntity projectile
    ) {
        if (world.isClient || weaponStack.isEmpty()) {
            return;
        }

        int level = EnchantmentHelper.getLevel(
                ModEnchantments.FREEZE,
                weaponStack
        );

        if (level <= 0) {
            return;
        }

        String tag = "weaponsexpanded.freeze.level." + level;

        if (!projectile.getCommandTags().contains(tag)) {
            projectile.addCommandTag(tag);
        }
    }

    private static void applyFlame(
            World world,
            ItemStack weaponStack,
            PersistentProjectileEntity projectile
    ) {
        if (world.isClient || weaponStack.isEmpty()) {
            return;
        }

        int level = EnchantmentHelper.getLevel(
                Enchantments.FLAME,
                weaponStack
        );

        if (level > 0) {
            projectile.setOnFireFor(5);
        }
    }
}