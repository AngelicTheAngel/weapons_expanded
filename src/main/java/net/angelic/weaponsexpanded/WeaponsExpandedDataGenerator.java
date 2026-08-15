package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.datagen.*;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public final class WeaponsExpandedDataGenerator {

	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();

		ExistingFileHelper existingFileHelper =
				event.getExistingFileHelper();

		RegistrySetBuilder registryBuilder =
				new RegistrySetBuilder()
						.add(
								Registries.ENCHANTMENT,
								ModEnchantments::bootstrap
						);

		/*
		 * Construct this separately. Passing a lambda directly to
		 * DataGenerator#addProvider causes an ambiguous overload.
		 */
		DatapackBuiltinEntriesProvider datapackProvider =
				new DatapackBuiltinEntriesProvider(
						output,
						event.getLookupProvider(),
						registryBuilder,
						Set.of(WeaponsExpanded.MOD_ID)
				);

		generator.addProvider(
				event.includeServer(),
				datapackProvider
		);

		/*
		 * This lookup includes the enchantments generated above.
		 */
		CompletableFuture<HolderLookup.Provider> registryProvider =
				datapackProvider.getRegistryProvider();

		generator.addProvider(
				event.includeServer(),
				new ModItemTagProvider(
						output,
						registryProvider,
						existingFileHelper
				)
		);

		generator.addProvider(
				event.includeServer(),
				new ModEnchantmentTagProvider(
						output,
						registryProvider,
						existingFileHelper
				)
		);

		generator.addProvider(
				event.includeServer(),
				new ModRecipeProvider(output, registryProvider)
		);

		generator.addProvider(
				event.includeClient(),
				new ModModelProvider(
						output,
						existingFileHelper
				)
		);

		generator.addProvider(
				event.includeServer(),
				new ModAdvancementProvider(
						output,
						event.getLookupProvider(),
						existingFileHelper
				)
		);
	}

	private WeaponsExpandedDataGenerator() {
	}
}