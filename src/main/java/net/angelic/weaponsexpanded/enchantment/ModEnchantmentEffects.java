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
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEnchantmentEffects {

    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>>
            ENCHANTMENT_ENTITY_EFFECT_TYPES =
            DeferredRegister.create(
                    Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE,
                    WeaponsExpanded.MODID
            );

    public static final DeferredHolder<
            MapCodec<? extends EnchantmentEntityEffect>,
            MapCodec<WitheringEnchantmentEffect>
            > WITHERING = register(
            "withering",
            WitheringEnchantmentEffect.CODEC
    );

    public static final DeferredHolder<
            MapCodec<? extends EnchantmentEntityEffect>,
            MapCodec<PollutingEnchantmentEffect>
            > POLLUTING = register(
            "polluting",
            PollutingEnchantmentEffect.CODEC
    );

    public static final DeferredHolder<
            MapCodec<? extends EnchantmentEntityEffect>,
            MapCodec<FrostbiteEnchantmentEffect>
            > FROSTBITE = register(
            "frostbite",
            FrostbiteEnchantmentEffect.CODEC
    );

    public static final DeferredHolder<
            MapCodec<? extends EnchantmentEntityEffect>,
            MapCodec<FreezeEnchantmentEffect>
            > FREEZE = register(
            "freeze",
            FreezeEnchantmentEffect.CODEC
    );

    public static final DeferredHolder<
            MapCodec<? extends EnchantmentEntityEffect>,
            MapCodec<LeechEnchantmentEffect>
            > LEECH = register(
            "leech",
            LeechEnchantmentEffect.CODEC
    );

    private static <T extends EnchantmentEntityEffect>
    DeferredHolder<
            MapCodec<? extends EnchantmentEntityEffect>,
            MapCodec<T>
            > register(String name, MapCodec<T> codec) {
        return ENCHANTMENT_ENTITY_EFFECT_TYPES.register(
                name,
                () -> codec
        );
    }

    public static void register(IEventBus modEventBus) {
        ENCHANTMENT_ENTITY_EFFECT_TYPES.register(modEventBus);
    }

    private ModEnchantmentEffects() {
    }
}