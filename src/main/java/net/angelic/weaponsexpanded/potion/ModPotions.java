package net.angelic.weaponsexpanded.potion;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;

public class ModPotions {

    public static final Holder<Potion> FROSTBITE_POTION = registerPotion("frostbite_potion",
            new Potion("frostbite", new MobEffectInstance(ModEffects.FROSTBITE, 600, 0)));

    public static final Holder<Potion> LONG_FROSTBITE_POTION = registerPotion("long_frostbite_potion",
            new Potion("frostbite", new MobEffectInstance(ModEffects.FROSTBITE, 1200, 0)));

    private static Holder<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerForHolder(BuiltInRegistries.POTION, Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, name), potion);
    }

    public static void registerPotions() {

    }
}
