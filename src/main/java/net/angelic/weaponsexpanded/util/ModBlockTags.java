package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public final class ModBlockTags {
    public static final TagKey<Block> BLUNT_MINEABLE =
            TagKey.create(
                    Registries.BLOCK,
                    Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "mineable/blunt")
            );

    private ModBlockTags() {
    }
}