package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;

public class ModDamageTypes {

    public static final ResourceKey<DamageType> RITUAL = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "ritual"));

    public static final ResourceKey<DamageType> DEGRADATION = ResourceKey.create(Registries.DAMAGE_TYPE,
            Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "degradation"));

    public static void bootstrap(BootstrapContext<DamageType> context) {
        context.register(RITUAL, new DamageType("ritual", 0.1f, DamageEffects.HURT));
        context.register(DEGRADATION, new DamageType("degradation", 0.1f, DamageEffects.HURT));
    }

    public static DamageSource create(Level level, ResourceKey<DamageType> key) {
        return new DamageSource(level.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE).getOrThrow(key));
    }
}
