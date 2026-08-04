package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.angelic.weaponsexpanded.entity.ModEntities;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.network.ModPackets;
import net.angelic.weaponsexpanded.potion.ModPotions;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.angelic.weaponsexpanded.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeaponsExpanded implements ModInitializer {

	public static final String MOD_ID = "weaponsexpanded";

	public static final Logger LOGGER =
			LoggerFactory.getLogger(MOD_ID);

	// Everything in this tag can be used as furnace fuel.
	public static final TagKey<Item> WOODEN_FUEL = TagKey.of(
			RegistryKeys.ITEM,
			new Identifier(MOD_ID, "wooden_fuel")
	);

	public static final String VERSION = FabricLoader.getInstance()
			.getModContainer(MOD_ID)
			.map(container -> container
					.getMetadata()
					.getVersion()
					.getFriendlyString())
			.orElse("Unknown");

	@Override
	public void onInitialize() {
		LOGGER.info(
				"Initializing {} version {}",
				MOD_ID,
				VERSION
		);

		WeaponsExpandedConfig.get();

		ModItems.registerModItems();
		ModEnchantments.registerEnchantments();
		ModEffects.registerEffects();
		ModEntities.registerEntities();
		ModSounds.register();
		ModPotions.registerPotions();
		ModPackets.register();

		// Vanilla wooden tools generally burn for 200 ticks.
		FuelRegistry.INSTANCE.add(WOODEN_FUEL, 200);

		ModLootTableModifiers.modifyLootTables();

		weaponsexpanded$registerBrewingRecipes();

		if (WeaponsExpandedConfig.get().enableWeaponsmithTrades) {
			weaponsexpanded$registerWeaponsmithTrades();
		}
	}

	private static void weaponsexpanded$registerBrewingRecipes() {
		FabricBrewingRecipeRegistry.registerPotionRecipe(
				Potions.AWKWARD,
				Ingredient.ofItems(Items.BLUE_ICE),
				ModPotions.FROSTBITE_POTION.value()
		);

		FabricBrewingRecipeRegistry.registerPotionRecipe(
				ModPotions.FROSTBITE_POTION.value(),
				Ingredient.ofItems(Items.REDSTONE),
				ModPotions.LONG_FROSTBITE_POTION.value()
		);
	}

	private static void weaponsexpanded$registerWeaponsmithTrades() {
		TradeOfferHelper.registerVillagerOffers(
				VillagerProfession.WEAPONSMITH,
				4,
				factories -> {
					factories.add((entity, random) ->
							weaponsexpanded$createEnchantedTrade(
									ModItems.DIAMOND_HAMMER,
									17,
									3,
									15,
									random
							)
					);

					factories.add((entity, random) ->
							weaponsexpanded$createEnchantedTrade(
									ModItems.DIAMOND_HATCHET,
									17,
									3,
									15,
									random
							)
					);

					factories.add((entity, random) ->
							weaponsexpanded$createEnchantedTrade(
									ModItems.DIAMOND_BATTLEAXE,
									17,
									3,
									15,
									random
							)
					);
				}
		);

		TradeOfferHelper.registerVillagerOffers(
				VillagerProfession.WEAPONSMITH,
				5,
				factories -> {
					factories.add((entity, random) ->
							weaponsexpanded$createEnchantedTrade(
									ModItems.DIAMOND_BROADSWORD,
									13,
									3,
									30,
									random
							)
					);

					factories.add((entity, random) ->
							weaponsexpanded$createEnchantedTrade(
									ModItems.DIAMOND_SICKLE,
									13,
									3,
									30,
									random
							)
					);

					factories.add((entity, random) ->
							weaponsexpanded$createEnchantedTrade(
									ModItems.DIAMOND_SCYTHE,
									13,
									3,
									30,
									random
							)
					);

					factories.add((entity, random) ->
							weaponsexpanded$createEnchantedTrade(
									ModItems.DIAMOND_LONGSWORD,
									13,
									3,
									30,
									random
							)
					);

					factories.add((entity, random) ->
							weaponsexpanded$createEnchantedTrade(
									ModItems.DIAMOND_KATANA,
									13,
									3,
									30,
									random
							)
					);

					factories.add((entity, random) ->
							weaponsexpanded$createEnchantedTrade(
									ModItems.DIAMOND_GREATSWORD,
									13,
									3,
									30,
									random
							)
					);
				}
		);
	}

	private static TradeOffer weaponsexpanded$createEnchantedTrade(
			Item item,
			int baseEmeraldCost,
			int maxUses,
			int merchantExperience,
			Random random
	) {
		int enchantmentLevel = random.nextBetween(5, 19);

		ItemStack weapon = EnchantmentHelper.enchant(
				random,
				new ItemStack(item),
				enchantmentLevel,
				false
		);

		int emeraldCost =
				baseEmeraldCost + enchantmentLevel;

		return new TradeOffer(
				new ItemStack(Items.EMERALD, emeraldCost),
				weapon,
				maxUses,
				merchantExperience,
				0.2F
		);
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}