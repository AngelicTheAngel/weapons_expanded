package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
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

    /**
     * Heavy-arrow specific: apply Power (damage) + Punch (knockback) in a way that works for custom projectiles.
     */
    public static void applyPowerAndPunchForHeavyArrow(World world, ItemStack weaponStack, HeavyArrowEntity arrow) {
        if (world.isClient()) return;

        int powerLevel = getLevel(world, weaponStack, Identifier.ofVanilla("power"));
        int punchLevel = getLevel(world, weaponStack, Identifier.ofVanilla("punch"));

        // Power: set base projectile damage directly (no getDamage() needed)
        if (powerLevel > 0) {
            double bonusDamage = powerLevel * 0.5D + 0.5D; // vanilla-ish
            arrow.setDamage(HeavyArrowEntity.BASE_DAMAGE + bonusDamage);
        } else {
            arrow.setDamage(HeavyArrowEntity.BASE_DAMAGE);
        }

        // Punch: store level on entity; entity applies extra knockback on hit
        arrow.weaponsexpanded$setPunchLevel(punchLevel);
    }

    private static int getLevel(World world, ItemStack weaponStack, Identifier enchantId) {
        RegistryKey<Enchantment> key = RegistryKey.of(RegistryKeys.ENCHANTMENT, enchantId);
        Optional<RegistryEntry.Reference<Enchantment>> opt =
                world.getRegistryManager()
                        .getOrThrow(RegistryKeys.ENCHANTMENT)
                        .getOptional(key);

        return opt.map(entry -> EnchantmentHelper.getLevel(entry, weaponStack)).orElse(0);
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
