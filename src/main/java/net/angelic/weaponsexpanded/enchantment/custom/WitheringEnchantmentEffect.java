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

public record WitheringEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<WitheringEnchantmentEffect> CODEC = MapCodec.unit(WitheringEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity user, Vec3 pos) {
        if (user instanceof LivingEntity) {
            MobEffectInstance witherEffectL1 = new MobEffectInstance(MobEffects.WITHER, 100, 1);
            MobEffectInstance witherEffectL2 = new MobEffectInstance(MobEffects.WITHER, 160, 1);
            if (level == 1) {
                ((LivingEntity) user).addEffect(witherEffectL1);
                ((LivingEntity) user).removeEffect(MobEffects.POISON);
            }
            if (level == 2) {
                ((LivingEntity) user).addEffect(witherEffectL2);
                ((LivingEntity) user).removeEffect(MobEffects.POISON);

            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}