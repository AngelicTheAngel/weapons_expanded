package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.util.tags.ModEntityTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityTypeIds;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagProvider extends FabricTagsProvider.EntityTypeTagsProvider {
    public ModEntityTypeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        builder(ModEntityTypeTags.SENSITIVE_TO_ENDS_BANE)
                .forceAddTag(ModEntityTypeTags.END_MOBS);

        builder(ModEntityTypeTags.SENSITIVE_TO_NETHERS_SCOURGE)
                .forceAddTag(ModEntityTypeTags.NETHER_MOBS);

        builder(ModEntityTypeTags.END_MOBS)
                .add(EntityTypeIds.ENDER_DRAGON)
                .add(EntityTypeIds.ENDERMAN)
                .add(EntityTypeIds.ENDERMITE)
                .add(EntityTypeIds.SHULKER);

        builder(ModEntityTypeTags.NETHER_MOBS)
                .add(EntityTypeIds.PIGLIN)
                .add(EntityTypeIds.PIGLIN_BRUTE)
                .add(EntityTypeIds.HOGLIN)
                .add(EntityTypeIds.BLAZE)
                .add(EntityTypeIds.GHAST)
                .add(EntityTypeIds.MAGMA_CUBE)
                .add(EntityTypeIds.STRIDER);
    }
}
