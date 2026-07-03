package net.angelic.weaponsexpanded.potion;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;

public final class ModBrewingRecipes {
    private ModBrewingRecipes() {
    }

    public static void register() {
        BrewingRecipeRegisterEvent.BUS.addListener(ModBrewingRecipes::registerBrewingRecipes);
    }

    private static void registerBrewingRecipes(BrewingRecipeRegisterEvent event) {
        event.getBuilder().addMix(
                Potions.AWKWARD,
                Items.BLUE_ICE,
                ModPotions.FROSTBITE_POTION.getHolder().orElseThrow()
        );

        event.getBuilder().addMix(
                ModPotions.FROSTBITE_POTION.getHolder().orElseThrow(),
                Items.REDSTONE,
                ModPotions.LONG_FROSTBITE_POTION.getHolder().orElseThrow()
        );
    }
}