package net.angelic.weaponsexpanded.potion;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModPotions {

    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(
                    Registries.POTION,
                    WeaponsExpanded.MOD_ID
            );

    public static final DeferredHolder<Potion, Potion>
            FROSTBITE_POTION =
            POTIONS.register(
                    "frostbite_potion",
                    () -> new Potion(
                            "frostbite",
                            new MobEffectInstance(
                                    ModEffects.FROSTBITE,
                                    600,
                                    0
                            )
                    )
            );

    public static final DeferredHolder<Potion, Potion>
            LONG_FROSTBITE_POTION =
            POTIONS.register(
                    "long_frostbite_potion",
                    () -> new Potion(
                            "frostbite",
                            new MobEffectInstance(
                                    ModEffects.FROSTBITE,
                                    1200,
                                    0
                            )
                    )
            );

    public static void registerPotions(
            IEventBus modEventBus
    ) {
        POTIONS.register(modEventBus);
    }

    private ModPotions() {
    }
}