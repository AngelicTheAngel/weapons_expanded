package net.angelic.weaponsexpanded.effect;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(
                    BuiltInRegistries.MOB_EFFECT,
                    WeaponsExpanded.MODID
            );

    public static final DeferredHolder<MobEffect, FrostbiteEffect> FROSTBITE =
            MOB_EFFECTS.register(
                    "frostbite",
                    () -> new FrostbiteEffect(
                            MobEffectCategory.HARMFUL,
                            0x32E3FF
                    )
            );

    public static Holder<MobEffect> frostbiteHolder() {
        return FROSTBITE;
    }

    public static void register(IEventBus modEventBus) {
        MOB_EFFECTS.register(modEventBus);
    }

    private ModEffects() {
    }
}