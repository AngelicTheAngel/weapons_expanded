package net.angelic.weaponsexpanded.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record FrostbiteEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<FrostbiteEnchantmentEffect> CODEC = MapCodec.unit(FrostbiteEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof LivingEntity) {
            if (level > 0) {
                ((LivingEntity) user).addStatusEffect(new StatusEffectInstance(ModEffects.FROSTBITE, 40 + 40 * level, 0));
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}