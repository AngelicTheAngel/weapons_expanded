package net.angelic.weaponsexpanded.effect;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, WeaponsExpanded.MOD_ID);

    public static final RegistryObject<MobEffect> FROSTBITE =
            EFFECTS.register("frostbite", () -> new FrostbiteEffect(MobEffectCategory.HARMFUL, 0x32e3ff));

    public static void registerEffects(IEventBus modEventBus) {
        EFFECTS.register(modEventBus);
    }
}