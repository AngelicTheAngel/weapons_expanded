package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

public final class ModEnchantmentTags {
    public static final TagKey<Enchantment> POST_ATTACK_EXCLUSIVE_SET =
            TagKey.create(
                    Registries.ENCHANTMENT,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "exclusive_set/post_attack")
            );

    public static final TagKey<Enchantment> BOW_EFFECTS_EXCLUSIVE_SET =
            TagKey.create(
                    Registries.ENCHANTMENT,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "exclusive_set/bow_effects")
            );

    private ModEnchantmentTags() {
    }
}