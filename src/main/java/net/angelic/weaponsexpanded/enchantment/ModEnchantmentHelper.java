package net.angelic.weaponsexpanded.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

public final class ModEnchantmentHelper {
    private ModEnchantmentHelper() {
    }

    public static int getLevel(World world, ItemStack stack, RegistryKey<Enchantment> enchantmentKey) {
        Registry<Enchantment> enchantmentRegistry =
                world.getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT);

        RegistryEntry.Reference<Enchantment> enchantmentEntry =
                enchantmentRegistry.getOrThrow(enchantmentKey);

        return EnchantmentHelper.getLevel(enchantmentEntry, stack);
    }
}