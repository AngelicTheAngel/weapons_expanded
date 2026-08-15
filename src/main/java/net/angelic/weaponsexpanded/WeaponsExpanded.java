package net.angelic.weaponsexpanded;

import com.mojang.blaze3d.platform.InputConstants;
import net.angelic.weaponsexpanded.client.ModModelPredicates;
import net.angelic.weaponsexpanded.client.render.ExplosiveArrowEntityRenderer;
import net.angelic.weaponsexpanded.client.render.HeavyArrowEntityRenderer;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.angelic.weaponsexpanded.entity.ModEntities;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.angelic.weaponsexpanded.network.ModPackets;
import net.angelic.weaponsexpanded.network.ToggleBastardSwordModePayload;
import net.angelic.weaponsexpanded.network.ToggleWarhammerModePayload;
import net.angelic.weaponsexpanded.potion.ModPotions;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.angelic.weaponsexpanded.util.ModLootTableModifiers;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Mod(WeaponsExpanded.MOD_ID)
public class WeaponsExpanded {

  public static final String MOD_ID = "weaponsexpanded";

  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  // Everything in this tag can be used as furnace fuel.
  public static final TagKey<Item> WOODEN_FUEL =
          TagKey.create(
                  Registries.ITEM,
                  id("wooden_fuel")
          );

  public static final String VERSION =
          ModList.get()
                  .getModContainerById(MOD_ID)
                  .map(container -> container
                          .getModInfo()
                          .getVersion()
                          .toString())
                  .orElse("Unknown");

  public WeaponsExpanded(
          IEventBus modEventBus,
          ModContainer modContainer,
          Dist dist
  ) {
    ModItems.register(modEventBus);
    ModEffects.registerEffects(modEventBus);
    ModEntities.register(modEventBus);
    ModSounds.register(modEventBus);
    ModPotions.registerPotions(modEventBus);

    modContainer.registerConfig(
            ModConfig.Type.COMMON,
            WeaponsExpandedConfig.SPEC,
            "weaponsexpanded-common.toml"
    );

    NeoForge.EVENT_BUS.addListener(
            this::weaponsexpanded$setFuelBurnTime
    );

    NeoForge.EVENT_BUS.addListener(
            this::weaponsexpanded$addWeaponsmithTrades
    );

    NeoForge.EVENT_BUS.addListener(
            this::weaponsexpanded$registerBrewingRecipes
    );

    if (dist == Dist.CLIENT) {
      WeaponsExpandedConfigScreen.registerConfigScreen(
              modContainer
      );
    }

    modEventBus.addListener(ModPackets::register);
    modEventBus.addListener(WeaponsExpandedDataGenerator::gatherData);

    ModLootTableModifiers.modifyLootTables();

    LOGGER.info(
            "Initializing {} version {}",
            MOD_ID,
            VERSION
    );
  }

  private void weaponsexpanded$setFuelBurnTime(
          FurnaceFuelBurnTimeEvent event
  ) {
    if (event.getItemStack().is(WOODEN_FUEL)) {
      // Vanilla wooden tools generally burn for 200 ticks.
      event.setBurnTime(200);
    }
  }

  private void weaponsexpanded$registerBrewingRecipes(
          RegisterBrewingRecipesEvent event
  ) {
    event.getBuilder().addMix(
            Potions.AWKWARD,
            Items.BLUE_ICE,
            ModPotions.FROSTBITE_POTION
    );

    event.getBuilder().addMix(
            ModPotions.FROSTBITE_POTION,
            Items.REDSTONE,
            ModPotions.LONG_FROSTBITE_POTION
    );
  }

  private void weaponsexpanded$addWeaponsmithTrades(
          VillagerTradesEvent event
  ) {
    if (event.getType() != VillagerProfession.WEAPONSMITH) {
      return;
    }

    if (!WeaponsExpandedConfig.ENABLE_WEAPONSMITH_TRADES.get()) {
      return;
    }

    event.getTrades().get(4).add(
            (entity, random) ->
                    weaponsexpanded$createEnchantedTrade(
                            ModItems.DIAMOND_HAMMER.get(),
                            17,
                            3,
                            15,
                            entity,
                            random
                    )
    );

    event.getTrades().get(4).add(
            (entity, random) ->
                    weaponsexpanded$createEnchantedTrade(
                            ModItems.DIAMOND_HATCHET.get(),
                            17,
                            3,
                            15,
                            entity,
                            random
                    )
    );

    event.getTrades().get(4).add(
            (entity, random) ->
                    weaponsexpanded$createEnchantedTrade(
                            ModItems.DIAMOND_BATTLEAXE.get(),
                            17,
                            3,
                            15,
                            entity,
                            random
                    )
    );

    event.getTrades().get(5).add(
            (entity, random) ->
                    weaponsexpanded$createEnchantedTrade(
                            ModItems.DIAMOND_BROADSWORD.get(),
                            13,
                            3,
                            30,
                            entity,
                            random
                    )
    );

    event.getTrades().get(5).add(
            (entity, random) ->
                    weaponsexpanded$createEnchantedTrade(
                            ModItems.DIAMOND_SICKLE.get(),
                            13,
                            3,
                            30,
                            entity,
                            random
                    )
    );

    event.getTrades().get(5).add(
            (entity, random) ->
                    weaponsexpanded$createEnchantedTrade(
                            ModItems.DIAMOND_SCYTHE.get(),
                            13,
                            3,
                            30,
                            entity,
                            random
                    )
    );

    event.getTrades().get(5).add(
            (entity, random) ->
                    weaponsexpanded$createEnchantedTrade(
                            ModItems.DIAMOND_LONGSWORD.get(),
                            13,
                            3,
                            30,
                            entity,
                            random
                    )
    );

    event.getTrades().get(5).add(
            (entity, random) ->
                    weaponsexpanded$createEnchantedTrade(
                            ModItems.DIAMOND_KATANA.get(),
                            13,
                            3,
                            30,
                            entity,
                            random
                    )
    );

    event.getTrades().get(5).add(
            (entity, random) ->
                    weaponsexpanded$createEnchantedTrade(
                            ModItems.DIAMOND_GREATSWORD.get(),
                            13,
                            3,
                            30,
                            entity,
                            random
                    )
    );
  }

  private static MerchantOffer weaponsexpanded$createEnchantedTrade(
          Item item,
          int baseEmeraldCost,
          int maxUses,
          int merchantExperience,
          Entity merchant,
          RandomSource random
  ) {
    int enchantmentLevel =
            random.nextIntBetweenInclusive(5, 19);

    ItemStack weapon = EnchantmentHelper.enchantItem(
            random,
            new ItemStack(item),
            enchantmentLevel,
            merchant.level().registryAccess(),
            Optional.empty()
    );

    int emeraldCost =
            baseEmeraldCost + enchantmentLevel;

    return new MerchantOffer(
            new ItemCost(
                    Items.EMERALD,
                    emeraldCost
            ),
            weapon,
            maxUses,
            merchantExperience,
            0.2F
    );
  }

  public static ResourceLocation id(String path) {
    return ResourceLocation.fromNamespaceAndPath(
            MOD_ID,
            path
    );
  }

  /*
   * These events run only on the physical client. Keeping them in a
   * nested class prevents their handlers from being registered on a
   * dedicated server.
   */
  @EventBusSubscriber(
          modid = WeaponsExpanded.MOD_ID,
          value = Dist.CLIENT
  )
  public static final class ClientModEvents {

    private static final String
            WEAPONSEXPANDED$KEY_CATEGORY =
            "key.category.weaponsexpanded.general";

    private static final String
            WEAPONSEXPANDED$KEY_TOGGLE_WEAPON_MODE =
            "key.weaponsexpanded.toggle_bastard_sword_mode";

    private static final KeyMapping
            WEAPONSEXPANDED$TOGGLE_WEAPON_MODE_KEY =
            new KeyMapping(
                    WEAPONSEXPANDED$KEY_TOGGLE_WEAPON_MODE,
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_V,
                    WEAPONSEXPANDED$KEY_CATEGORY
            );

    private ClientModEvents() {
    }

    @SubscribeEvent
    public static void onClientSetup(
            FMLClientSetupEvent event
    ) {
      event.enqueueWork(ModModelPredicates::register);
    }

    @SubscribeEvent
    public static void registerKeyMappings(
            RegisterKeyMappingsEvent event
    ) {
      event.register(
              WEAPONSEXPANDED$TOGGLE_WEAPON_MODE_KEY
      );
    }

    @SubscribeEvent
    public static void registerEntityRenderers(
            EntityRenderersEvent.RegisterRenderers event
    ) {
      event.registerEntityRenderer(
              ModEntities.HEAVY_ARROW.get(),
              HeavyArrowEntityRenderer::new
      );

      event.registerEntityRenderer(
              ModEntities.EXPLOSIVE_ARROW.get(),
              ExplosiveArrowEntityRenderer::new
      );
    }
  }

  @EventBusSubscriber(
          modid = MOD_ID,
          value = Dist.CLIENT
  )
  public static final class ClientGameEvents {

    private ClientGameEvents() {
    }

    @SubscribeEvent
    public static void onClientTick(
            ClientTickEvent.Post event
    ) {
      Minecraft client = Minecraft.getInstance();

      if (client.player == null) {
        return;
      }

      while (ClientModEvents
              .WEAPONSEXPANDED$TOGGLE_WEAPON_MODE_KEY
              .consumeClick()) {
        ItemStack stack =
                client.player.getMainHandItem();

        if (stack.getItem() instanceof BastardSwordItem) {
          ModPackets.sendToServer(
                  new ToggleBastardSwordModePayload()
          );

          client.player.resetAttackStrengthTicker();
        } else if (stack.getItem() instanceof WarhammerItem) {
          ModPackets.sendToServer(
                  new ToggleWarhammerModePayload()
          );

          client.player.resetAttackStrengthTicker();
        }
      }
    }
  }
}