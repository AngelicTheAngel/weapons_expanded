package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.datagen.ModItemTagProvider;
import net.angelic.weaponsexpanded.datagen.ModModelProvider;
import net.angelic.weaponsexpanded.datagen.ModRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
		modid = WeaponsExpanded.MOD_ID,
		bus = Mod.EventBusSubscriber.Bus.MOD
)
public class WeaponsExpandedDataGenerator {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();

		ExistingFileHelper existingFileHelper =
				event.getExistingFileHelper();

		generator.addProvider(
				event.includeServer(),
				new ModItemTagProvider(
						output,
						event.getLookupProvider(),
						existingFileHelper
				)
		);

		generator.addProvider(
				event.includeServer(),
				new ModRecipeProvider(output)
		);

		generator.addProvider(
				event.includeClient(),
				new ModModelProvider(
						output,
						existingFileHelper
				)
		);
	}
}