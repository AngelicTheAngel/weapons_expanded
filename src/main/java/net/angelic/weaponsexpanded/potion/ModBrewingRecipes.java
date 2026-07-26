package net.angelic.weaponsexpanded.potion;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

public final class ModBrewingRecipes {
    private ModBrewingRecipes() {
    }

    public static void register() {
        NeoForge.EVENT_BUS.addListener(
                ModBrewingRecipes::registerBrewingRecipes
        );
    }

    private static void registerBrewingRecipes(
            RegisterBrewingRecipesEvent event
    ) {
        PotionBrewing.Builder builder = event.getBuilder();

        builder.addMix(
                Potions.AWKWARD,
                Items.BLUE_ICE,
                ModPotions.FROSTBITE_POTION
        );

        builder.addMix(
                ModPotions.FROSTBITE_POTION,
                Items.REDSTONE,
                ModPotions.LONG_FROSTBITE_POTION
        );
    }
}