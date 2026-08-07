package net.angelic.weaponsexpanded.potion;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModPotions {

    public static final RegistryEntry<Potion> FROSTBITE_POTION = registerPotion("frostbite_potion",
            new Potion("frostbite", new StatusEffectInstance(ModEffects.FROSTBITE, 600, 0)));

    public static final RegistryEntry<Potion> LONG_FROSTBITE_POTION = registerPotion("long_frostbite_potion",
            new Potion("frostbite", new StatusEffectInstance(ModEffects.FROSTBITE, 1200, 0)));

    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(WeaponsExpanded.MOD_ID, name), potion);
    }

    public static void registerPotions() {

    }
}
