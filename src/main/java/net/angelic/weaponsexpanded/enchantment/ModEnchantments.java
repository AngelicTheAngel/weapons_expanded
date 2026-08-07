package net.angelic.weaponsexpanded.enchantment;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEnchantments {
    public static final RegistryKey<Enchantment> LEECH =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(WeaponsExpanded.MOD_ID, "leech"));
    public static final RegistryKey<Enchantment> CLEAVING =
            RegistryKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(WeaponsExpanded.MOD_ID, "cleaving"));
}
