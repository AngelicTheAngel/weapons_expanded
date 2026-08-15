package net.angelic.weaponsexpanded.effect;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(
                    Registries.MOB_EFFECT,
                    WeaponsExpanded.MOD_ID
            );

    public static final DeferredHolder<MobEffect, MobEffect> FROSTBITE =
            EFFECTS.register(
                    "frostbite",
                    () -> new FrostbiteEffect(
                            MobEffectCategory.HARMFUL,
                            0x32E3FF
                    )
            );

    private ModEffects() {
    }

    public static void registerEffects(IEventBus modEventBus) {
        EFFECTS.register(modEventBus);
    }
}