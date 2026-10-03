package net.angelic.weaponsexpanded.registries;

import net.angelic.weaponsexpanded.util.tags.ModItemTags;
import net.fabricmc.fabric.api.registry.FuelValueEvents;

public class ModFuels {
    public static void registerFuels() {
        FuelValueEvents.BUILD.register((builder, context) -> {
            builder.add(ModItemTags.WOODEN_WEAPON_FUEL, 200);
        });
    }
}
