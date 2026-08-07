package net.angelic.weaponsexpanded.entity;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.entity.projectile.ExplosiveArrowEntity;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final EntityType<HeavyArrowEntity> HEAVY_ARROW = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(WeaponsExpanded.MOD_ID, "heavy_arrow"),
            EntityType.Builder.<HeavyArrowEntity>create(HeavyArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .trackingTickInterval(1)
                    .maxTrackingRange(64)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(WeaponsExpanded.MOD_ID, "heavy_arrow")))
    );

    public static final EntityType<ExplosiveArrowEntity> EXPLOSIVE_ARROW = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(WeaponsExpanded.MOD_ID, "explosive_arrow"),
            EntityType.Builder.<ExplosiveArrowEntity>create(ExplosiveArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .trackingTickInterval(1)
                    .maxTrackingRange(64)
                    .build(RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(WeaponsExpanded.MOD_ID, "explosive_arrow")))
    );

    public static void registerEntities() {}
}