package net.angelic.weaponsexpanded.entity;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.entity.projectile.ExplosiveArrowEntity;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(
                    Registries.ENTITY_TYPE,
                    WeaponsExpanded.MOD_ID
            );

    public static final DeferredHolder<EntityType<?>, EntityType<HeavyArrowEntity>>
            HEAVY_ARROW = ENTITY_TYPES.register(
            "heavy_arrow",
            () -> EntityType.Builder
                    .<HeavyArrowEntity>of(
                            HeavyArrowEntity::new,
                            MobCategory.MISC
                    )
                    .sized(0.5F, 0.5F)
                    .setTrackingRange(64)
                    .setUpdateInterval(1)
                    .setShouldReceiveVelocityUpdates(true)
                    .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    Identifier.fromNamespaceAndPath(
                                            WeaponsExpanded.MOD_ID,
                                            "heavy_arrow"
                                    )
                            )
                    )
    );

    public static final DeferredHolder<EntityType<?>, EntityType<ExplosiveArrowEntity>>
            EXPLOSIVE_ARROW = ENTITY_TYPES.register(
            "explosive_arrow",
            () -> EntityType.Builder
                    .<ExplosiveArrowEntity>of(
                            ExplosiveArrowEntity::new,
                            MobCategory.MISC
                    )
                    .sized(0.5F, 0.5F)
                    .setTrackingRange(64)
                    .setUpdateInterval(1)
                    .setShouldReceiveVelocityUpdates(true)
                    .build(ResourceKey.create(
                                    Registries.ENTITY_TYPE,
                                    Identifier.fromNamespaceAndPath(
                                            WeaponsExpanded.MOD_ID,
                                            "explosive_arrow"
                                    )
                            )
                    )
    );

    private ModEntities() {
    }

    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);

        WeaponsExpanded.LOGGER.info(
                "Registering entities for {}",
                WeaponsExpanded.MOD_ID
        );
    }
}