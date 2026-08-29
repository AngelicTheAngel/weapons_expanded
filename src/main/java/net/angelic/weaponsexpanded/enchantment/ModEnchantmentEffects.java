package net.angelic.weaponsexpanded.enchantment;

import com.mojang.serialization.MapCodec;
import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.enchantment.effect.*;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;

public class ModEnchantmentEffects {
    public static final MapCodec<? extends EnchantmentEntityEffect> WITHERING =
            registerEntityEffect("withering", WitheringEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> POLLUTING =
            registerEntityEffect("polluting", PollutingEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> FROSTBITE =
            registerEntityEffect("frostbite", FrostbiteEnchantmentEffect.CODEC);
    public static final MapCodec<? extends EnchantmentEntityEffect> FREEZE =
            registerEntityEffect("freeze", FreezeEnchantmentEffect.CODEC);

    private static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, name), codec);
    }

    public static void registerEnchantmentEffects() {}
}
