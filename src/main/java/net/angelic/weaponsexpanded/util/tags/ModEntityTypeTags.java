package net.angelic.weaponsexpanded.util.tags;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public final class ModEntityTypeTags {
    public static final TagKey<EntityType<?>> NETHER_MOBS =
            TagKey.create(
                    Registries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "nether")
            );

    public static final TagKey<EntityType<?>> END_MOBS =
            TagKey.create(
                    Registries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "end")
            );

    public static final TagKey<EntityType<?>> SENSITIVE_TO_NETHERS_SCOURGE =
            TagKey.create(
                    Registries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "sensitive_to_nethers_scourge")
            );

    public static final TagKey<EntityType<?>> SENSITIVE_TO_ENDS_BANE =
            TagKey.create(
                    Registries.ENTITY_TYPE,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "sensitive_to_ends_bane")
            );

    private ModEntityTypeTags() {
    }
}