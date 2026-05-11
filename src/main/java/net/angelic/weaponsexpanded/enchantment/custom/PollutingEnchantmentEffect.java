package net.angelic.weaponsexpanded.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record PollutingEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<PollutingEnchantmentEffect> CODEC = MapCodec.unit(PollutingEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity user, Vec3 pos) {
        if (user instanceof LivingEntity) {
            MobEffectInstance pollutingEffectL1 = new MobEffectInstance(MobEffects.POISON, 160, 0);
            MobEffectInstance pollutingEffectL2 = new MobEffectInstance(MobEffects.POISON, 300, 0);
            if (level == 1) {
                ((LivingEntity) user).addEffect(pollutingEffectL1);
            }
            if (level == 2) {
                ((LivingEntity) user).addEffect(pollutingEffectL2);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}