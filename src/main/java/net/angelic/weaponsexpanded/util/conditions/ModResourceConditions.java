package net.angelic.weaponsexpanded.util.conditions;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditionType;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceConditions;
import net.minecraft.resources.Identifier;

public final class ModResourceConditions {
    public static final ResourceConditionType<WeaponsmithTradesCondition>
            WEAPONSMITH_TRADES = ResourceConditionType.create(
            Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "weaponsmith_trades"),
            WeaponsmithTradesCondition.CODEC
    );

    public static void register() {
        ResourceConditions.register(WEAPONSMITH_TRADES);
    }
}