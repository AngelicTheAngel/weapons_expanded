package net.angelic.weaponsexpanded.resource.condition;

import com.mojang.serialization.MapCodec;
import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.minecraft.registry.RegistryOps;
import net.minecraft.util.Identifier;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public final class TrialChamberMeleeEquipmentEnabledCondition implements ResourceCondition {
    public static final Identifier ID = Identifier.of(WeaponsExpanded.MOD_ID, "trial_chamber_melee_equipment_loot_enabled");

    public static final TrialChamberMeleeEquipmentEnabledCondition INSTANCE =
            new TrialChamberMeleeEquipmentEnabledCondition();

    public static final ResourceConditionType<TrialChamberMeleeEquipmentEnabledCondition> TYPE =
            ResourceConditionType.create(ID, MapCodec.unit(INSTANCE));

    private TrialChamberMeleeEquipmentEnabledCondition() {}

    @Override
    public boolean test(RegistryOps.@Nullable RegistryInfoGetter registryInfo) {
        return WeaponsExpandedConfig.get().enableTrialChamberMeleeEquipment;
    }

    @Override
    public @NonNull ResourceConditionType<?> getType() {
        return TYPE;
    }
}
