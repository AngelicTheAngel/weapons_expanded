package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Optional;

public final class ProjectileEnchantmentApplier {
    private ProjectileEnchantmentApplier() {
    }

    public static void applyFreezeAndFlame(World world, ItemStack weaponStack, PersistentProjectileEntity projectile) {
        if (world.isClient()) return;

        applyFreeze(world, weaponStack, projectile);
        applyFlame(world, weaponStack, projectile);
    }

    private static void applyFreeze(World world, ItemStack weaponStack, PersistentProjectileEntity projectile) {
        RegistryKey<Enchantment> freezeKey =
                RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(WeaponsExpanded.MOD_ID, "freeze"));

        Optional<RegistryEntry.Reference<Enchantment>> freezeOpt =
                world.getRegistryManager()
                        .getOrThrow(RegistryKeys.ENCHANTMENT)
                        .getOptional(freezeKey);

        if (freezeOpt.isEmpty()) return;

        RegistryEntry<Enchantment> freeze = freezeOpt.get();
        int level = EnchantmentHelper.getLevel(freeze, weaponStack);

        if (level <= 0) return;

        String tag = "weaponsexpanded.freeze.level." + level;
        if (!projectile.getCommandTags().contains(tag)) {
            projectile.addCommandTag(tag);
        }
    }

    private static void applyFlame(World world, ItemStack weaponStack, PersistentProjectileEntity projectile) {
        RegistryKey<Enchantment> flameKey =
                RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.ofVanilla("flame"));

        Optional<RegistryEntry.Reference<Enchantment>> flameOpt =
                world.getRegistryManager()
                        .getOrThrow(RegistryKeys.ENCHANTMENT)
                        .getOptional(flameKey);

        if (flameOpt.isEmpty()) return;

        RegistryEntry<Enchantment> flame = flameOpt.get();
        int level = EnchantmentHelper.getLevel(flame, weaponStack);

        if (level > 0) {
            projectile.setOnFireFor(5);
        }
    }
}
