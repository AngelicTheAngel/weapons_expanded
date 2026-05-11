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
        if (user instanceof LivingEntity) {
            MobEffectInstance frostbiteEffectL1 = new MobEffectInstance(ModEffects.FROSTBITE, 80, 0);
            MobEffectInstance frostbiteEffectL2 = new MobEffectInstance(ModEffects.FROSTBITE, 120, 0);
            if (level == 1) {
                ((LivingEntity) user).addEffect(frostbiteEffectL1);
            }
            if (level == 2) {
                ((LivingEntity) user).addEffect(frostbiteEffectL2);
            }
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}