package net.angelic.weaponsexpanded.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;

public record FreezeEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<FreezeEnchantmentEffect> CODEC = MapCodec.unit(FreezeEnchantmentEffect::new);

    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {
        // Tag the projectile with the enchantment level so it persists during flight
        user.addCommandTag("weaponsexpanded.freeze.level." + level);
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}