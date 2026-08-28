package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.datagen.ModAdvancementProvider;
import net.angelic.weaponsexpanded.datagen.ModItemTagProvider;
import net.angelic.weaponsexpanded.datagen.ModModelProvider;
import net.angelic.weaponsexpanded.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class WeaponsExpandedDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModAdvancementProvider::new);
    }
}
