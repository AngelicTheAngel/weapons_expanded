package net.angelic.weaponsexpanded.util;

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

    private ModItemTags() {
    }
}