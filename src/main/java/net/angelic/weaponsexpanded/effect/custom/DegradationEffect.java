package net.angelic.weaponsexpanded.effect.custom;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class DegradationEffect extends MobEffect {
    public DegradationEffect(MobEffectCategory category, int color) {
        super(category, color);
        addAttributeModifier(
                Attributes.MAX_HEALTH,
                Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "degradation_max_health"),
                -2.0F,
                AttributeModifier.Operation.ADD_VALUE);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {return true;}
}