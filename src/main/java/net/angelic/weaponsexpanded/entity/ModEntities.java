package net.angelic.weaponsexpanded.entity;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.entity.projectile.ExplosiveArrowEntity;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEntities {

    public static final DeferredRegister.Entities ENTITY_TYPES =
            DeferredRegister.createEntities(WeaponsExpanded.MODID);

    public static final DeferredHolder<
            EntityType<?>,
            EntityType<HeavyArrowEntity>
            > HEAVY_ARROW = ENTITY_TYPES.registerEntityType(
            "heavy_arrow",
            HeavyArrowEntity::new,
            MobCategory.MISC,
            builder -> builder
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
    );

    public static final DeferredHolder<
            EntityType<?>,
            EntityType<ExplosiveArrowEntity>
            > EXPLOSIVE_ARROW = ENTITY_TYPES.registerEntityType(
            "explosive_arrow",
            ExplosiveArrowEntity::new,
            MobCategory.MISC,
            builder -> builder
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
    );

    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
    }

    private ModEntities() {
    }
}