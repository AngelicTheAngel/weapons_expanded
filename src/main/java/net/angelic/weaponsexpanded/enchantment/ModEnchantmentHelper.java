package net.angelic.weaponsexpanded.enchantment;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

public final class ModEnchantmentHelper {
    private ModEnchantmentHelper() {
    }

    public static int getLevel(Level level, ItemStack stack, ResourceKey<Enchantment> enchantmentKey) {
        Holder.Reference<Enchantment> enchantmentEntry = level
                .registryAccess()
                .lookupOrThrow(Registries.ENCHANTMENT)
                .getOrThrow(enchantmentKey);

        return EnchantmentHelper.getItemEnchantmentLevel(enchantmentEntry, stack);
    }
}
