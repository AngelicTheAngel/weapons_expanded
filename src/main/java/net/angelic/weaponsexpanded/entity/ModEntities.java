package net.angelic.weaponsexpanded.entity;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.entity.projectile.ExplosiveArrowEntity;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {

    private static final ResourceKey<EntityType<?>> HEAVY_ARROW_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "heavy_arrow"));

    public static final EntityType<HeavyArrowEntity> HEAVY_ARROW = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "heavy_arrow"),
            EntityType.Builder.<HeavyArrowEntity>of(HeavyArrowEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .updateInterval(1)
                    .clientTrackingRange(64)
                    .build(HEAVY_ARROW_KEY)
    );

    private static final ResourceKey<EntityType<?>> EXPLOSIVE_ARROW_KEY =
            ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "explosive_arrow"));

    public static final EntityType<ExplosiveArrowEntity> EXPLOSIVE_ARROW = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "explosive_arrow"),
            EntityType.Builder.<ExplosiveArrowEntity>of(ExplosiveArrowEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .updateInterval(1)
                    .clientTrackingRange(64)
                    .build(EXPLOSIVE_ARROW_KEY)
    );

    public static void registerEntities() {}
}
