package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModItemTags {

    public static final TagKey<Item> CLEAVING_ENCHANTABLE =
            create("enchantable/cleaving");

    public static final TagKey<Item> DIAMOND_WEAPONS =
            create("diamond_weapons");

    public static final TagKey<Item> NETHERITE_WEAPONS =
            create("netherite_weapons");

    private static TagKey<Item> create(String path) {
        return TagKey.of(
                RegistryKeys.ITEM,
                Identifier.of(WeaponsExpanded.MOD_ID, path)
        );
    }

    private ModItemTags() {
    }
}