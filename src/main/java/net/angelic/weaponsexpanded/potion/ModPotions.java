package net.angelic.weaponsexpanded.potion;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModPotions {

    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(
                    Registries.POTION,
                    WeaponsExpanded.MODID
            );

    public static final DeferredHolder<Potion, Potion> FROSTBITE_POTION =
            POTIONS.register(
                    "frostbite_potion",
                    () -> new Potion(
                            "frostbite",
                            new MobEffectInstance(
                                    ModEffects.frostbiteHolder(),
                                    600,
                                    0
                            )
                    )
            );

    public static final DeferredHolder<Potion, Potion> LONG_FROSTBITE_POTION =
            POTIONS.register(
                    "long_frostbite_potion",
                    () -> new Potion(
                            "frostbite",
                            new MobEffectInstance(
                                    ModEffects.frostbiteHolder(),
                                    1200,
                                    0
                            )
                    )
            );

    public static Holder<Potion> frostbitePotionHolder() {
        return FROSTBITE_POTION;
    }

    public static Holder<Potion> longFrostbitePotionHolder() {
        return LONG_FROSTBITE_POTION;
    }

    public static void register(IEventBus modEventBus) {
        POTIONS.register(modEventBus);

        NeoForge.EVENT_BUS.addListener(
                ModPotions::registerBrewingRecipes
        );
    }

    private static void registerBrewingRecipes(
            RegisterBrewingRecipesEvent event
    ) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(
                Potions.AWKWARD,
                Items.BLUE_ICE,
                FROSTBITE_POTION
        );

        builder.addMix(
                FROSTBITE_POTION,
                Items.REDSTONE,
                LONG_FROSTBITE_POTION
        );
    }

    private ModPotions() {
    }
}