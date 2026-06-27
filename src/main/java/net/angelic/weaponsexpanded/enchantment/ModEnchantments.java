package net.angelic.weaponsexpanded.enchantment;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments {
    public static final ResourceKey<Enchantment> LEECH =
            ResourceKey.create(Registries.ENCHANTMENT, Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "leech"));

    public static final ResourceKey<Enchantment> CLEAVING =
            ResourceKey.create(Registries.ENCHANTMENT, Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "cleaving"));
}
