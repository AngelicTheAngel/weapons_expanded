package net.angelic.weaponsexpanded;

import com.mojang.blaze3d.platform.InputConstants;
import net.angelic.weaponsexpanded.client.ModModelPredicates;
import net.angelic.weaponsexpanded.client.render.ExplosiveArrowEntityRenderer;
import net.angelic.weaponsexpanded.client.render.HeavyArrowEntityRenderer;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
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
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(WeaponsExpanded.MOD_ID)
public class WeaponsExpanded {

  public static final String MOD_ID =
          "weaponsexpanded";

  public static final Logger LOGGER =
          LoggerFactory.getLogger(MOD_ID);

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

  public WeaponsExpanded() {
    IEventBus modEventBus =
            FMLJavaModLoadingContext.get()
                    .getModEventBus();

    ModItems.register(modEventBus);
    ModEnchantments.registerEnchantments(modEventBus);
    ModEffects.registerEffects(modEventBus);
    ModEntities.register(modEventBus);
    ModSounds.register(modEventBus);
    ModPotions.registerPotions(modEventBus);

    modEventBus.addListener(this::commonSetup);

    ModLoadingContext.get().registerConfig(
            ModConfig.Type.COMMON,
            WeaponsExpandedConfig.SPEC,
            "weaponsexpanded-common.toml"
    );

    ModPackets.register();
    ModLootTableModifiers.modifyLootTables();

    MinecraftForge.EVENT_BUS.addListener(
            this::weaponsexpanded$setFuelBurnTime
    );

    MinecraftForge.EVENT_BUS.addListener(
            this::weaponsexpanded$addWeaponsmithTrades
    );

    LOGGER.info(
            "Initializing {} version {}",
            MOD_ID,
            VERSION
    );
  }

  private void commonSetup(
          final FMLCommonSetupEvent event
  ) {
    /*
     * BrewingRecipeRegistry is not thread-safe, so Forge
     * requires brewing recipes to be added through enqueueWork.
     */
    event.enqueueWork(
            WeaponsExpanded::
                    weaponsexpanded$registerBrewingRecipes
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

  private static void weaponsexpanded$registerBrewingRecipes() {
    BrewingRecipeRegistry.addRecipe(
            new PotionMixBrewingRecipe(
                    Potions.AWKWARD,
                    Ingredient.of(Items.BLUE_ICE),
                    ModPotions.FROSTBITE_POTION.get()
            )
    );

    BrewingRecipeRegistry.addRecipe(
            new PotionMixBrewingRecipe(
                    ModPotions.FROSTBITE_POTION.get(),
                    Ingredient.of(Items.REDSTONE),
                    ModPotions.LONG_FROSTBITE_POTION.get()
            )
    );
  }

  private void weaponsexpanded$addWeaponsmithTrades(
          VillagerTradesEvent event
  ) {
    if (event.getType()
            != VillagerProfession.WEAPONSMITH) {
      return;
    }

    if (!WeaponsExpandedConfig
            .ENABLE_WEAPONSMITH_TRADES
            .get()) {
      return;
    }

    event.getTrades().get(4).add(
            (entity, random) ->
                    weaponsexpanded$createEnchantedTrade(
                            ModItems.DIAMOND_HAMMER.get(),
                            17,
                            3,
                            15,
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
                            random
                    )
    );
  }

  private static MerchantOffer
  weaponsexpanded$createEnchantedTrade(
          Item item,
          int baseEmeraldCost,
          int maxUses,
          int merchantExperience,
          RandomSource random
  ) {
    int enchantmentLevel =
            random.nextIntBetweenInclusive(5, 19);

    ItemStack weapon =
            EnchantmentHelper.enchantItem(
                    random,
                    new ItemStack(item),
                    enchantmentLevel,
                    false
            );

    int emeraldCost =
            baseEmeraldCost + enchantmentLevel;

    return new MerchantOffer(
            new ItemStack(
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
    return new ResourceLocation(MOD_ID, path);
  }

  /**
   * Forge does not expose a direct equivalent of Fabric's
   * registerPotionRecipe method.
   * This recipe preserves the original potion container, allowing
   * normal, splash, and lingering potions to be transformed.
   */
  private static final class PotionMixBrewingRecipe
          implements IBrewingRecipe {

    private final Potion inputPotion;
    private final Ingredient ingredient;
    private final Potion outputPotion;

    private PotionMixBrewingRecipe(
            Potion inputPotion,
            Ingredient ingredient,
            Potion outputPotion
    ) {
      this.inputPotion = inputPotion;
      this.ingredient = ingredient;
      this.outputPotion = outputPotion;
    }

    @Override
    public boolean isInput(ItemStack stack) {
      return weaponsexpanded$isPotionContainer(stack)
              && PotionUtils.getPotion(stack)
              == this.inputPotion;
    }

    @Override
    public boolean isIngredient(ItemStack stack) {
      return this.ingredient.test(stack);
    }

    @Override
    public ItemStack getOutput(
            ItemStack input,
            ItemStack ingredient
    ) {
      if (!isInput(input)
              || !isIngredient(ingredient)) {
        return ItemStack.EMPTY;
      }

      ItemStack output = input.copy();
      output.setCount(1);

      return PotionUtils.setPotion(
              output,
              this.outputPotion
      );
    }

    private static boolean
    weaponsexpanded$isPotionContainer(
            ItemStack stack
    ) {
      return stack.is(Items.POTION)
              || stack.is(Items.SPLASH_POTION)
              || stack.is(Items.LINGERING_POTION);
    }
  }

  /*
   * These events run only on the physical client. Keeping them in a
   * nested class prevents client-only classes from loading on a
   * dedicated server.
   */
  @Mod.EventBusSubscriber(
          modid = MOD_ID,
          bus = Mod.EventBusSubscriber.Bus.MOD,
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
      event.enqueueWork(() -> {
        ModModelPredicates.register();

        WeaponsExpandedConfigScreen
                .registerConfigScreen();
      });
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

  /*
   * ClientTickEvent is fired on the normal Forge event bus instead
   * of the mod event bus.
   */
  @Mod.EventBusSubscriber(
          modid = MOD_ID,
          bus = Mod.EventBusSubscriber.Bus.FORGE,
          value = Dist.CLIENT
  )
  public static final class ClientForgeEvents {

    private ClientForgeEvents() {
    }

    @SubscribeEvent
    public static void onClientTick(
            TickEvent.ClientTickEvent event
    ) {
      if (event.phase != TickEvent.Phase.END) {
        return;
      }

      Minecraft client =
              Minecraft.getInstance();

      if (client.player == null) {
        return;
      }

      /*
       * Do not register a chain-crossbow attack tick handler
       * here. The converted Forge handler or mixin should
       * continue handling chain-crossbow firing.
       */
      while (ClientModEvents
              .WEAPONSEXPANDED$TOGGLE_WEAPON_MODE_KEY
              .consumeClick()) {
        ItemStack stack =
                client.player.getMainHandItem();

        if (stack.getItem()
                instanceof BastardSwordItem) {
          ModPackets.sendToServer(
                  new ToggleBastardSwordModePayload()
          );

          client.player
                  .resetAttackStrengthTicker();
        } else if (stack.getItem()
                instanceof WarhammerItem) {
          ModPackets.sendToServer(
                  new ToggleWarhammerModePayload()
          );

          client.player
                  .resetAttackStrengthTicker();
        }
      }
    }
  }
}