package net.angelic.weaponsexpanded;

import com.mojang.logging.LogUtils;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.angelic.weaponsexpanded.enchantment.ModEnchantmentEffects;
import net.angelic.weaponsexpanded.entity.ModEntities;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.network.ModPackets;
import net.angelic.weaponsexpanded.potion.ModBrewingRecipes;
import net.angelic.weaponsexpanded.potion.ModPotions;
import net.angelic.weaponsexpanded.registries.ModFuels;
import net.angelic.weaponsexpanded.util.ModLootTableModifiers;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(WeaponsExpanded.MODID)
public final class WeaponsExpanded {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "weaponsexpanded";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "weaponsexpanded" namespace
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "weaponsexpanded" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "weaponsexpanded" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public WeaponsExpanded(FMLJavaModLoadingContext context) {
        var modBusGroup = context.getModBusGroup();

        ModItems.register(modBusGroup);
        ModEntities.register(modBusGroup);

        ModEffects.register(modBusGroup);
        ModEnchantmentEffects.register(modBusGroup);
        ModPotions.register(modBusGroup);

        ModBrewingRecipes.register();
        ModPackets.register();

        ModFuels.registerFuels();
        ModLootTableModifiers.modifyLootTables();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            WeaponsExpandedClient.register();
        }

        FMLCommonSetupEvent.getBus(modBusGroup).addListener(this::commonSetup);

        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }
}
