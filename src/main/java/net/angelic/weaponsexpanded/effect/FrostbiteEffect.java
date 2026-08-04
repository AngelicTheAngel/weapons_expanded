package net.angelic.weaponsexpanded.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FrostbiteEffect extends StatusEffect {
    public FrostbiteEffect(StatusEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.isOnFire()) {
            entity.setFrozenTicks(160);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {return true;}
}