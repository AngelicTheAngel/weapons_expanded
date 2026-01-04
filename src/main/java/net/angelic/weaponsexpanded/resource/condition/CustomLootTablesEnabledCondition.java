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

public final class CustomLootTablesEnabledCondition implements ResourceCondition {
    public static final Identifier ID = Identifier.of(WeaponsExpanded.MOD_ID, "custom_loot_tables_enabled");

    public static final CustomLootTablesEnabledCondition INSTANCE = new CustomLootTablesEnabledCondition();

    public static final ResourceConditionType<CustomLootTablesEnabledCondition> TYPE =
            ResourceConditionType.create(ID, MapCodec.unit(INSTANCE));

    private CustomLootTablesEnabledCondition() {}

    @Override
    public boolean test(RegistryOps.@Nullable RegistryInfoGetter registryInfo) {
        return WeaponsExpandedConfig.get().enableCustomLootTables;
    }

    @Override
    public @NonNull ResourceConditionType<?> getType() {
        return TYPE;
    }
}
