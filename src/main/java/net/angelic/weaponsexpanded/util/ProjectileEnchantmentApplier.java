package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import java.util.Optional;

public final class ProjectileEnchantmentApplier {
    private ProjectileEnchantmentApplier() {
    }

    public static void applyFreezeAndFlame(Level world, ItemStack weaponStack, AbstractArrow projectile) {
        if (world.isClientSide()) return;

        applyFreeze(world, weaponStack, projectile);
        applyFlame(world, weaponStack, projectile);
    }

    /**
     * Heavy-arrow specific: apply Power (damage) + Punch (knockback) in a way that works for custom projectiles.
     */
    public static void applyPowerAndPunchForHeavyArrow(Level world, ItemStack weaponStack, HeavyArrowEntity arrow) {
        if (world.isClientSide()) return;

        int powerLevel = getLevel(world, weaponStack, Identifier.withDefaultNamespace("power"));
        int punchLevel = getLevel(world, weaponStack, Identifier.withDefaultNamespace("punch"));

        // Power: set base projectile damage directly (no getDamage() needed)
        if (powerLevel > 0) {
            double bonusDamage = powerLevel * 0.5D + 0.5D; // vanilla-ish
            arrow.setBaseDamage(HeavyArrowEntity.BASE_DAMAGE + bonusDamage);
        } else {
            arrow.setBaseDamage(HeavyArrowEntity.BASE_DAMAGE);
        }

        // Punch: store level on entity; entity applies extra knockback on hit
        arrow.weaponsexpanded$setPunchLevel(punchLevel);
    }

    private static int getLevel(Level world, ItemStack weaponStack, Identifier enchantId) {
        ResourceKey<Enchantment> key = ResourceKey.create(Registries.ENCHANTMENT, enchantId);
        Optional<Holder.Reference<Enchantment>> opt =
                world.registryAccess()
                        .lookupOrThrow(Registries.ENCHANTMENT)
                        .get(key);

        return opt.map(entry -> EnchantmentHelper.getItemEnchantmentLevel(entry, weaponStack)).orElse(0);
    }

    private static void applyFreeze(Level world, ItemStack weaponStack, AbstractArrow projectile) {
        ResourceKey<Enchantment> freezeKey =
                ResourceKey.create(Registries.ENCHANTMENT, Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "freeze"));

        Optional<Holder.Reference<Enchantment>> freezeOpt =
                world.registryAccess()
                        .lookupOrThrow(Registries.ENCHANTMENT)
                        .get(freezeKey);

        if (freezeOpt.isEmpty()) return;

        Holder<Enchantment> freeze = freezeOpt.get();
        int level = EnchantmentHelper.getItemEnchantmentLevel(freeze, weaponStack);

        if (level <= 0) return;

        String tag = "weaponsexpanded.freeze.level." + level;
        if (!projectile.getTags().contains(tag)) {
            projectile.addTag(tag);
        }
    }

    private static void applyFlame(Level world, ItemStack weaponStack, AbstractArrow projectile) {
        ResourceKey<Enchantment> flameKey =
                ResourceKey.create(Registries.ENCHANTMENT, Identifier.withDefaultNamespace("flame"));

        Optional<Holder.Reference<Enchantment>> flameOpt =
                world.registryAccess()
                        .lookupOrThrow(Registries.ENCHANTMENT)
                        .get(flameKey);

        if (flameOpt.isEmpty()) return;

        Holder<Enchantment> flame = flameOpt.get();
        int level = EnchantmentHelper.getItemEnchantmentLevel(flame, weaponStack);

        if (level > 0) {
            projectile.igniteForSeconds(5);
        }
    }
}
