package net.angelic.weaponsexpanded.effect;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, WeaponsExpanded.MODID);

    public static final RegistryObject<MobEffect> FROSTBITE = MOB_EFFECTS.register(
            "frostbite",
            () -> new FrostbiteEffect(MobEffectCategory.HARMFUL, 0x32e3ff)
    );

    public static Holder<MobEffect> frostbiteHolder() {
        return FROSTBITE.getHolder().orElseThrow();
    }

    public static void register(BusGroup modBusGroup) {
        MOB_EFFECTS.register(modBusGroup);
    }

    private ModEffects() {
    }
}
