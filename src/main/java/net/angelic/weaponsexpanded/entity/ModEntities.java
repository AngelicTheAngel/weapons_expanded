package net.angelic.weaponsexpanded.entity;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {

    private static final RegistryKey<EntityType<?>> HEAVY_ARROW_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(WeaponsExpanded.MOD_ID, "heavy_arrow"));

    public static final EntityType<HeavyArrowEntity> HEAVY_ARROW = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(WeaponsExpanded.MOD_ID, "heavy_arrow"),
            EntityType.Builder.<HeavyArrowEntity>create(HeavyArrowEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f)
                    .trackingTickInterval(1)
                    .maxTrackingRange(64)
                    .build(HEAVY_ARROW_KEY)
    );

    public static void registerEntities() {
        WeaponsExpanded.LOGGER.info("Registering entities for " + WeaponsExpanded.MOD_ID);
    }
}
