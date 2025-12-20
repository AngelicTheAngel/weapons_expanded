package net.angelic.weaponsexpanded.effect;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModEffects {
    public static final RegistryEntry<StatusEffect> FROSTBITE = registerStatusEffect("frostbite", new FrostbiteEffect(StatusEffectCategory.HARMFUL, 0x32e3ff));

    private static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(WeaponsExpanded.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {
        WeaponsExpanded.LOGGER.info("Registering effects for " + WeaponsExpanded.MOD_ID);
    }
}
