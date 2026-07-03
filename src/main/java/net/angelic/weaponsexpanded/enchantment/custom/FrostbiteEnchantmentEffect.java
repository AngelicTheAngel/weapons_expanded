package net.angelic.weaponsexpanded.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public record FrostbiteEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<FrostbiteEnchantmentEffect> CODEC = MapCodec.unit(FrostbiteEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity user, Vec3 pos) {
        if (user instanceof LivingEntity living && level > 0) {
            living.addEffect(new MobEffectInstance(ModEffects.frostbiteHolder(), 40 + 40 * level, 0));
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
