package net.angelic.weaponsexpanded;

import com.mojang.logging.LogUtils;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.angelic.weaponsexpanded.enchantment.ModEnchantmentEffects;
import net.angelic.weaponsexpanded.entity.ModEntities;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.network.ModPackets;
import net.angelic.weaponsexpanded.potion.ModBrewingRecipes;
import net.angelic.weaponsexpanded.potion.ModPotions;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.angelic.weaponsexpanded.util.ModLootTableModifiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(WeaponsExpanded.MODID)
public final class WeaponsExpanded {

    public static final String MODID = "weaponsexpanded";

    public static final Logger LOGGER = LogUtils.getLogger();

    public WeaponsExpanded(
            IEventBus modEventBus,
            ModContainer modContainer
    ) {
        // Lifecycle events posted on this mod's event bus.
        modEventBus.addListener(this::commonSetup);

        // Deferred registries.
        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);
        ModEffects.register(modEventBus);
        ModEnchantmentEffects.register(modEventBus);
        ModPotions.register(modEventBus);
        ModSounds.register(modEventBus);

        // Other mod and game event listeners.
        ModPackets.register(modEventBus);
        ModBrewingRecipes.register();
        ModLootTableModifiers.modifyLootTables();

        // Required because onServerStarting uses @SubscribeEvent.
        NeoForge.EVENT_BUS.register(this);

        // Register the common configuration.
        modContainer.registerConfig(
                ModConfig.Type.COMMON,
                Config.SPEC
        );
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Weapons Expanded common setup");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("Weapons Expanded server starting");
    }
}