package net.angelic.weaponsexpanded.enchantment;

import com.mojang.serialization.MapCodec;
import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.enchantment.custom.FreezeEnchantmentEffect;
import net.angelic.weaponsexpanded.enchantment.custom.FrostbiteEnchantmentEffect;
import net.angelic.weaponsexpanded.enchantment.custom.LeechEnchantmentEffect;
import net.angelic.weaponsexpanded.enchantment.custom.PollutingEnchantmentEffect;
import net.angelic.weaponsexpanded.enchantment.custom.WitheringEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ModEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENCHANTMENT_ENTITY_EFFECT_TYPES =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, WeaponsExpanded.MODID);

    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> WITHERING =
            ENCHANTMENT_ENTITY_EFFECT_TYPES.register("withering", () -> WitheringEnchantmentEffect.CODEC);

    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> POLLUTING =
            ENCHANTMENT_ENTITY_EFFECT_TYPES.register("polluting", () -> PollutingEnchantmentEffect.CODEC);

    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> FROSTBITE =
            ENCHANTMENT_ENTITY_EFFECT_TYPES.register("frostbite", () -> FrostbiteEnchantmentEffect.CODEC);

    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> FREEZE =
            ENCHANTMENT_ENTITY_EFFECT_TYPES.register("freeze", () -> FreezeEnchantmentEffect.CODEC);

    public static final RegistryObject<MapCodec<? extends EnchantmentEntityEffect>> LEECH =
            ENCHANTMENT_ENTITY_EFFECT_TYPES.register("leech", () -> LeechEnchantmentEffect.CODEC);

    public static void register(BusGroup modBusGroup) {
        ENCHANTMENT_ENTITY_EFFECT_TYPES.register(modBusGroup);
    }

    private ModEnchantmentEffects() {
    }
}
