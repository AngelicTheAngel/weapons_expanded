package net.angelic.weaponsexpanded.effect.custom;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.datagen.ModDamageTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
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
    public boolean applyEffectTick(ServerLevel serverLevel, LivingEntity mob, int amplification) {
        if (mob.getAttribute(Attributes.MAX_HEALTH).getValue() < 2) {
            mob.hurtServer(serverLevel, ModDamageTypes.create(serverLevel, ModDamageTypes.DEGRADATION), 999999);
            return false;
        }

        return super.applyEffectTick(serverLevel, mob, amplification);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {return true;}
}