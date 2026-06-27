package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class ModItemTags {
    public static final TagKey<Item> CLEAVING_ENCHANTABLE =
            TagKey.of(
                    RegistryKeys.ITEM,
                    Identifier.of(WeaponsExpanded.MOD_ID, "enchantable/cleaving")
            );

    private ModItemTags() {
    }
}