package net.angelic.weaponsexpanded.registries;

import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public final class ModFuels {
    private static final Set<RegistryObject<? extends Item>> WOODEN_WEAPONS = Set.of(
            ModItems.WOODEN_BROADSWORD,
            ModItems.WOODEN_SICKLE,
            ModItems.WOODEN_SCYTHE,
            ModItems.WOODEN_LONGSWORD,
            ModItems.WOODEN_KATANA,
            ModItems.WOODEN_GREATSWORD,
            ModItems.WOODEN_HATCHET,
            ModItems.WOODEN_HAMMER,
            ModItems.WOODEN_BATTLEAXE,
            ModItems.WOODEN_WARHAMMER
    );

    public static void registerFuels() {
        FurnaceFuelBurnTimeEvent.BUS.addListener(ModFuels::onFuelBurnTime);
    }

    private static void onFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        for (RegistryObject<? extends Item> item : WOODEN_WEAPONS) {
            if (event.getItemStack().is(item.get())) {
                event.setBurnTime(200);
                return;
            }
        }
    }

    private ModFuels() {
    }
}
