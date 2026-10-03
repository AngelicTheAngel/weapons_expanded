package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.datagen.*;
import net.angelic.weaponsexpanded.datagen.enchantment.ModEnchantmentProvider;
import net.angelic.weaponsexpanded.datagen.enchantment.ModEnchantmentTagProvider;
import net.angelic.weaponsexpanded.datagen.villager.ModVillagerTradeTags;
import net.angelic.weaponsexpanded.datagen.villager.ModVillagerTrades;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class WeaponsExpandedDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModAdvancementProvider::new);
        pack.addProvider(ModEnchantmentProvider::new);
        pack.addProvider(ModEnchantmentTagProvider::new);
        pack.addProvider(ModSoundsProvider::new);
        pack.addProvider(ModVillagerTradeTags::new);
        pack.addProvider(ModRegistryDataProvider::new);
        pack.addProvider(ModEntityTypeTagProvider::new);
    }

    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.ENCHANTMENT, ModEnchantmentProvider::bootstrap);
        registryBuilder.add(Registries.VILLAGER_TRADE, ModVillagerTrades::bootstrap);
        registryBuilder.add(Registries.DAMAGE_TYPE, ModDamageTypes::bootstrap);
    }
}
