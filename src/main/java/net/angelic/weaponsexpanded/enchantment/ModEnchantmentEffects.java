package net.angelic.weaponsexpanded.enchantment;

import com.mojang.serialization.MapCodec;
import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.enchantment.custom.*;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> WITHERING =
            registerEntityEffect("withering", WitheringEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> POLLUTING =
            registerEntityEffect("polluting", PollutingEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> FROSTBITE =
            registerEntityEffect("frostbite", FrostbiteEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> FREEZE =
            registerEntityEffect("freeze", FreezeEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> LEECH =
            registerEntityEffect("leech", LeechEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(WeaponsExpanded.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {}
}
