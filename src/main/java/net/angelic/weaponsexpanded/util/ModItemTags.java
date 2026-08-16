package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class ModItemTags {

    public static final TagKey<Item> DIAMOND_WEAPONS =
            create("diamond_weapons");

    public static final TagKey<Item> NETHERITE_WEAPONS =
            create("netherite_weapons");

    public static final TagKey<Item> LEECH_ENCHANTABLE =
            create("leech_enchantable");

    public static final TagKey<Item> CLEAVING_ENCHANTABLE =
            create("cleaving_enchantable");

    private static TagKey<Item> create(String path) {
        return TagKey.create(
                Registries.ITEM,
                Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, path)
        );
    }

    private ModItemTags() {
    }
}