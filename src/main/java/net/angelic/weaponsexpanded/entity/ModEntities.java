package net.angelic.weaponsexpanded.entity;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.entity.projectile.ExplosiveArrowEntity;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, WeaponsExpanded.MODID);

    public static final RegistryObject<EntityType<HeavyArrowEntity>> HEAVY_ARROW =
            ENTITY_TYPES.register("heavy_arrow", () -> {
                Identifier id = Identifier.fromNamespaceAndPath(WeaponsExpanded.MODID, "heavy_arrow");
                ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, id);

                return EntityType.Builder
                        .<HeavyArrowEntity>of(HeavyArrowEntity::new, MobCategory.MISC)
                        .sized(0.5F, 0.5F)
                        .clientTrackingRange(4)
                        .updateInterval(20)
                        .build(key);
            });

    public static final RegistryObject<EntityType<ExplosiveArrowEntity>> EXPLOSIVE_ARROW =
            ENTITY_TYPES.register("explosive_arrow", () -> {
                Identifier id = Identifier.fromNamespaceAndPath(WeaponsExpanded.MODID, "explosive_arrow");
                ResourceKey<EntityType<?>> key = ResourceKey.create(Registries.ENTITY_TYPE, id);

                return EntityType.Builder
                        .<ExplosiveArrowEntity>of(ExplosiveArrowEntity::new, MobCategory.MISC)
                        .sized(0.5F, 0.5F)
                        .clientTrackingRange(4)
                        .updateInterval(20)
                        .build(key);
            });

    public static void register(BusGroup modBusGroup) {
        ENTITY_TYPES.register(modBusGroup);
    }

    private ModEntities() {
    }
}