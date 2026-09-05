package net.angelic.weaponsexpanded.util.tags;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class ModItemTags {
    public static final TagKey<Item> CLEAVING_ENCHANTABLE =
            TagKey.create(
                    Registries.ITEM,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "enchantable/cleaving")
            );

    public static final TagKey<Item> LEECH_ENCHANTABLE =
            TagKey.create(
                    Registries.ITEM,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "enchantable/leech")
            );

    public static final TagKey<Item> CAPACITY_ENCHANTABLE =
            TagKey.create(
                    Registries.ITEM,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "enchantable/capacity")
            );

    public static final TagKey<Item> BLUNT =
            TagKey.create(
                    Registries.ITEM,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "blunt")
            );

    public static final TagKey<Item> PIERCE =
            TagKey.create(
                    Registries.ITEM,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "pierce")
            );

    public static final TagKey<Item> SCYTHE =
            TagKey.create(
                    Registries.ITEM,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "scythe")
            );

    public static final TagKey<Item> TWOHANDED =
            TagKey.create(
                    Registries.ITEM,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "two_handed")
            );

    public static final TagKey<Item> WOODEN_WEAPON_FUEL =
            TagKey.create(
                    Registries.ITEM,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "wooden_weapon_fuel")
            );

    public static final TagKey<Item> DIAMOND_WEAPON =
            TagKey.create(
                    Registries.ITEM,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "diamond_weapons")
            );

    public static final TagKey<Item> NETHERITE_WEAPON =
            TagKey.create(
                    Registries.ITEM,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "netherite_weapons")
            );

    private ModItemTags() {
    }
}