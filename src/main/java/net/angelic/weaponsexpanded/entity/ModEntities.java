package net.angelic.weaponsexpanded.entity;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.entity.projectile.ExplosiveArrowEntity;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModEntities {

    private static final Identifier HEAVY_ARROW_ID =
            new Identifier(WeaponsExpanded.MOD_ID, "heavy_arrow");

    private static final Identifier EXPLOSIVE_ARROW_ID =
            new Identifier(WeaponsExpanded.MOD_ID, "explosive_arrow");

    public static final EntityType<HeavyArrowEntity> HEAVY_ARROW =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    HEAVY_ARROW_ID,
                    FabricEntityTypeBuilder
                            .<HeavyArrowEntity>create(
                                    SpawnGroup.MISC,
                                    HeavyArrowEntity::new
                            )
                            .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(1)
                            .forceTrackedVelocityUpdates(true)
                            .build()
            );

    public static final EntityType<ExplosiveArrowEntity> EXPLOSIVE_ARROW =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    EXPLOSIVE_ARROW_ID,
                    FabricEntityTypeBuilder
                            .<ExplosiveArrowEntity>create(
                                    SpawnGroup.MISC,
                                    ExplosiveArrowEntity::new
                            )
                            .dimensions(EntityDimensions.fixed(0.5F, 0.5F))
                            .trackRangeBlocks(64)
                            .trackedUpdateRate(1)
                            .forceTrackedVelocityUpdates(true)
                            .build()
            );

    public static void registerEntities() {
        WeaponsExpanded.LOGGER.info(
                "Registering entities for {}",
                WeaponsExpanded.MOD_ID
        );
    }

    private ModEntities() {
    }
}