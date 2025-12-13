package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Optional;

public class HeavyArrowItem extends ArrowItem {
    public HeavyArrowItem(net.minecraft.item.Item.Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, ItemStack weaponStack) {
        HeavyArrowEntity arrow = new HeavyArrowEntity(world, shooter, stack.copy(), weaponStack);

        if (!world.isClient()) {
            RegistryKey<Enchantment> freezeKey =
                    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(WeaponsExpanded.MOD_ID, "freeze"));

            Optional<RegistryEntry.Reference<Enchantment>> freezeOpt =
                    world.getRegistryManager()
                            .getOrThrow(RegistryKeys.ENCHANTMENT)
                            .getOptional(freezeKey);

            if (freezeOpt.isPresent()) {
                RegistryEntry<Enchantment> freeze = freezeOpt.get();
                int level = EnchantmentHelper.getLevel(freeze, weaponStack);

                if (level > 0) {
                    arrow.addCommandTag("weaponsexpanded.freeze.level." + level);
                }
            }

            RegistryKey<Enchantment> flameKey =
                    RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.ofVanilla("flame"));

            Optional<RegistryEntry.Reference<Enchantment>> flameOpt =
                    world.getRegistryManager()
                            .getOrThrow(RegistryKeys.ENCHANTMENT)
                            .getOptional(flameKey);

            if (flameOpt.isPresent()) {
                RegistryEntry<Enchantment> flame = flameOpt.get();
                int level = EnchantmentHelper.getLevel(flame, weaponStack);

                if (level > 0) {
                    arrow.setOnFireFor(5);
                }
            }
        }

        return arrow;
    }
}
