package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModItemTags {

    public static final TagKey<Item> IRON_TOOL_MATERIALS =
            create("iron_tool_materials");

    public static final TagKey<Item> GOLD_TOOL_MATERIALS =
            create("gold_tool_materials");

    public static final TagKey<Item> DIAMOND_TOOL_MATERIALS =
            create("diamond_tool_materials");

    private static TagKey<Item> create(String path) {
        return TagKey.of(
                RegistryKeys.ITEM,
                new Identifier(WeaponsExpanded.MOD_ID, path)
        );
    }

    private ModItemTags() {
    }
}