package net.angelic.weaponsexpanded.effect;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.effect.custom.DegradationEffect;
import net.angelic.weaponsexpanded.effect.custom.FrostbiteEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModEffects {
    public static final Holder<MobEffect> FROSTBITE = registerStatusEffect("frostbite", new FrostbiteEffect(MobEffectCategory.HARMFUL, 0x32e3ff));
    public static final Holder<MobEffect> DEGRADATION = registerStatusEffect("degradation", new DegradationEffect(MobEffectCategory.HARMFUL, 0x32e3ff));

    private static Holder<MobEffect> registerStatusEffect(String name, MobEffect statusEffect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, name), statusEffect);
    }

    public static void registerEffects() {}
}
