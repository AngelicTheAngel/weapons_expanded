package net.angelic.weaponsexpanded.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record WitheringEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<WitheringEnchantmentEffect> CODEC = MapCodec.unit(WitheringEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof LivingEntity) {
            StatusEffectInstance witherEffectL1 = new StatusEffectInstance(StatusEffects.WITHER, 100, 1);
            StatusEffectInstance witherEffectL2 = new StatusEffectInstance(StatusEffects.WITHER, 160, 1);
            if (level == 1) {
                ((LivingEntity) user).addStatusEffect(witherEffectL1);
                ((LivingEntity) user).removeStatusEffect(StatusEffects.POISON);
            }
            if (level == 2) {
                ((LivingEntity) user).addStatusEffect(witherEffectL2);
                ((LivingEntity) user).removeStatusEffect(StatusEffects.POISON);

            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}