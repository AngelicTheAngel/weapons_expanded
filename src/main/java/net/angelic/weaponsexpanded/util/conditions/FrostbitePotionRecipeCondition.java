package net.angelic.weaponsexpanded.util.conditions;

import com.mojang.serialization.MapCodec;
import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.resources.RegistryOps;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class FrostbitePotionRecipeCondition implements ResourceCondition {

    public static final FrostbitePotionRecipeCondition INSTANCE =
            new FrostbitePotionRecipeCondition();

    public static final MapCodec<FrostbitePotionRecipeCondition> CODEC =
            MapCodec.unit(INSTANCE);

    @Override
    public boolean test(
            RegistryOps.@Nullable RegistryInfoLookup registryInfo
    ) {
        return WeaponsExpandedConfig.get().frostbitePotionRecipe;
    }

    @Override
    public @NonNull ResourceConditionType<?> getType() {
        return ModResourceConditions.FROSTBITE_POTION;
    }
}