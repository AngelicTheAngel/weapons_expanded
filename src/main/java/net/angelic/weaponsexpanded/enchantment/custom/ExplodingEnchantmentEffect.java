package net.angelic.weaponsexpanded.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public record ExplodingEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<ExplodingEnchantmentEffect> CODEC = MapCodec.unit(ExplodingEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        if (user instanceof LivingEntity) {
            world.createExplosion(user, user.getX(), user.getY() + 1, user.getZ(), 1.0F, World.ExplosionSourceType.NONE);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}