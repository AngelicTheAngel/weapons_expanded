package net.angelic.weaponsexpanded.registries;

import net.angelic.weaponsexpanded.item.ModItems;
import net.fabricmc.fabric.api.registry.FuelValueEvents;

public class ModFuels {
    public static void registerFuels() {
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ModItems.WOODEN_BROADSWORD, 200);
            builder.add(ModItems.WOODEN_SICKLE, 200);
            builder.add(ModItems.WOODEN_SCYTHE, 200);
            builder.add(ModItems.WOODEN_LONGSWORD, 200);
            builder.add(ModItems.WOODEN_KATANA, 200);
            builder.add(ModItems.WOODEN_GREATSWORD, 200);
            builder.add(ModItems.WOODEN_HATCHET, 200);
            builder.add(ModItems.WOODEN_HAMMER, 200);
            builder.add(ModItems.WOODEN_BATTLEAXE, 200);
            builder.add(ModItems.WOODEN_WARHAMMER, 200);
        });
    }
}
