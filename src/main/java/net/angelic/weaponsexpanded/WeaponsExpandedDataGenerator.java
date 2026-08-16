package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.datagen.*;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.advancements.AdvancementProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;

public final class WeaponsExpandedDataGenerator {

	public static void gatherData(GatherDataEvent.Client event) {
		RegistrySetBuilder registryBuilder = new RegistrySetBuilder().add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);

		event.createDatapackRegistryObjects(registryBuilder);

		event.createProvider(
				(output, registries) ->
						new ModItemTagProvider(output, registries)
		);

		event.createProvider(
				(output, registries) ->
						new ModEnchantmentTagProvider(output, registries)
		);

		event.createProvider(ModRecipeProvider.Runner::new);

		event.createProvider(ModModelProvider::new);

		event.createProvider(
				(output, registries) ->
						new AdvancementProvider(output, registries, List.of(new ModAdvancementProvider()))
		);
	}

	private WeaponsExpandedDataGenerator() {
	}
}