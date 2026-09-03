package net.angelic.weaponsexpanded.util.conditions;

import com.mojang.serialization.MapCodec;
import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.resources.RegistryOps;
import org.jetbrains.annotations.Nullable;

public class WeaponsmithTradesCondition implements ResourceCondition {

    public static final WeaponsmithTradesCondition INSTANCE =
            new WeaponsmithTradesCondition();

    public static final MapCodec<WeaponsmithTradesCondition> CODEC =
            MapCodec.unit(INSTANCE);

    @Override
    public boolean test(
            RegistryOps.@Nullable RegistryInfoLookup registryInfo
    ) {
        return WeaponsExpandedConfig.get().enableWeaponsmithTrades;
    }

    @Override
    public ResourceConditionType<?> getType() {
        return ModResourceConditions.WEAPONSMITH_TRADES;
    }
}