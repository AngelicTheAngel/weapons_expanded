package net.angelic.weaponsexpanded.util.conditions;

import com.mojang.serialization.MapCodec;
import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.resources.RegistryOps;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

public class FletcherTradesCondition implements ResourceCondition {

    public static final FletcherTradesCondition INSTANCE =
            new FletcherTradesCondition();

    public static final MapCodec<FletcherTradesCondition> CODEC =
            MapCodec.unit(INSTANCE);

    @Override
    public boolean test(
            RegistryOps.@Nullable RegistryInfoLookup registryInfo
    ) {
        return WeaponsExpandedConfig.get().enableFletcherTrades;
    }

    @Override
    public @NonNull ResourceConditionType<?> getType() {
        return ModResourceConditions.FLETCHER_TRADES;
    }
}