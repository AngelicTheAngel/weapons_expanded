package net.angelic.weaponsexpanded.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class FrostbiteEffect extends MobEffect {
    public FrostbiteEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.isOnFire()) {
            entity.setTicksFrozen(160);
            return true;
        }
        return false;
    }
}