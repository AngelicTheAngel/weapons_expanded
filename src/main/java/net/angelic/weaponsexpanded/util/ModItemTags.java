package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class ModItemTags {

    public static final TagKey<Item> IRON_TOOL_MATERIALS =
            create("iron_tool_materials");

    public static final TagKey<Item> GOLD_TOOL_MATERIALS =
            create("gold_tool_materials");

    public static final TagKey<Item> DIAMOND_TOOL_MATERIALS =
            create("diamond_tool_materials");

    private static TagKey<Item> create(String path) {
        return TagKey.create(
                Registries.ITEM,
                new ResourceLocation(WeaponsExpanded.MOD_ID, path)
        );
    }

    private ModItemTags() {
    }
}