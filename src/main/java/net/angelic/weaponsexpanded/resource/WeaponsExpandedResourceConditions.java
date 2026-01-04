package net.angelic.weaponsexpanded.resource;

import net.angelic.weaponsexpanded.resource.condition.CustomLootTablesEnabledCondition;
import net.angelic.weaponsexpanded.resource.condition.TrialChamberMeleeEquipmentLootEnabledCondition;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;

/**
 * Registers Fabric resource conditions used by JSON resources (loot tables).
 */
public final class WeaponsExpandedResourceConditions {
    private WeaponsExpandedResourceConditions() {}

    public static void init() {
        ResourceConditions.register(CustomLootTablesEnabledCondition.TYPE);
        ResourceConditions.register(TrialChamberMeleeEquipmentLootEnabledCondition.TYPE);
    }
}
