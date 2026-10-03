package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.util.tags.ModEntityTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModEntityTypeTagProvider extends FabricTagsProvider.EntityTypeTagsProvider {
    public ModEntityTypeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    private static ResourceKey<EntityType<?>> key(EntityType<?> entityType) {
        return BuiltInRegistries.ENTITY_TYPE.getResourceKey(entityType).orElseThrow();
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        builder(ModEntityTypeTags.SENSITIVE_TO_ENDS_BANE)
                .forceAddTag(ModEntityTypeTags.END_MOBS);

        builder(ModEntityTypeTags.SENSITIVE_TO_NETHERS_SCOURGE)
                .forceAddTag(ModEntityTypeTags.NETHER_MOBS);

        builder(ModEntityTypeTags.END_MOBS)
                .add(key(EntityType.ENDER_DRAGON))
                .add(key(EntityType.ENDERMAN))
                .add(key(EntityType.ENDERMITE))
                .add(key(EntityType.SHULKER));

        builder(ModEntityTypeTags.NETHER_MOBS)
                .add(key(EntityType.PIGLIN))
                .add(key(EntityType.PIGLIN_BRUTE))
                .add(key(EntityType.HOGLIN))
                .add(key(EntityType.BLAZE))
                .add(key(EntityType.GHAST))
                .add(key(EntityType.MAGMA_CUBE))
                .add(key(EntityType.STRIDER));
    }
}
