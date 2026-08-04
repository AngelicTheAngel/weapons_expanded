package net.angelic.weaponsexpanded.potion;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {

    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, WeaponsExpanded.MOD_ID);

    public static final RegistryObject<Potion> FROSTBITE_POTION =
            POTIONS.register("frostbite_potion",
                    () -> new Potion("frostbite",
                            new MobEffectInstance(ModEffects.FROSTBITE.get(), 600, 0)));

    public static final RegistryObject<Potion> LONG_FROSTBITE_POTION =
            POTIONS.register("long_frostbite_potion",
                    () -> new Potion("frostbite",
                            new MobEffectInstance(ModEffects.FROSTBITE.get(), 1200, 0)));

    public static void registerPotions(IEventBus modEventBus) {
        POTIONS.register(modEventBus);
    }
}