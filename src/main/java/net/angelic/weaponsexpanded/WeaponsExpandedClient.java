package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.client.render.HeavyArrowEntityRenderer;
import net.angelic.weaponsexpanded.entity.ModEntities;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.angelic.weaponsexpanded.network.FireChainCrossbowPayload;
import net.angelic.weaponsexpanded.network.ToggleBastardSwordModePayload;
import net.angelic.weaponsexpanded.network.ToggleWarhammerModePayload;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.common.NeoForge;
import org.lwjgl.glfw.GLFW;

@Mod(value = WeaponsExpanded.MODID, dist = Dist.CLIENT)
public final class WeaponsExpandedClient {

    private static final KeyMapping.Category WEAPONSEXPANDED$KEY_CATEGORY =
            new KeyMapping.Category(
                    Identifier.fromNamespaceAndPath(
                            WeaponsExpanded.MODID,
                            "general"
                    )
            );

    private static final String WEAPONSEXPANDED$KEY_TOGGLE_BASTARD_SWORD =
            "key.weaponsexpanded.toggle_bastard_sword_mode";

    private static final KeyMapping WEAPONSEXPANDED$TOGGLE_BASTARD_SWORD_MODE_KEY =
            new KeyMapping(
                    WEAPONSEXPANDED$KEY_TOGGLE_BASTARD_SWORD,
                    GLFW.GLFW_KEY_V,
                    WEAPONSEXPANDED$KEY_CATEGORY
            );

    public WeaponsExpandedClient(
            IEventBus modEventBus,
            ModContainer container
    ) {
        container.registerExtensionPoint(
                IConfigScreenFactory.class,
                ConfigurationScreen::new
        );

        // Mod-bus client events
        modEventBus.addListener(
                WeaponsExpandedClient::weaponsexpanded$registerKeyMappings
        );

        modEventBus.addListener(
                WeaponsExpandedClient::weaponsexpanded$registerEntityRenderers
        );

        modEventBus.addListener(
                WeaponsExpandedClient::weaponsexpanded$onClientSetup
        );

        // Game-bus client event
        NeoForge.EVENT_BUS.addListener(
                WeaponsExpandedClient::weaponsexpanded$onClientTick
        );
    }

    private static void weaponsexpanded$registerKeyMappings(
            RegisterKeyMappingsEvent event
    ) {
        event.registerCategory(WEAPONSEXPANDED$KEY_CATEGORY);
        event.register(WEAPONSEXPANDED$TOGGLE_BASTARD_SWORD_MODE_KEY);
    }

    private static void weaponsexpanded$registerEntityRenderers(
            EntityRenderersEvent.RegisterRenderers event
    ) {
        event.registerEntityRenderer(
                ModEntities.HEAVY_ARROW.get(),
                HeavyArrowEntityRenderer::new
        );

        event.registerEntityRenderer(
                ModEntities.EXPLOSIVE_ARROW.get(),
                TippableArrowRenderer::new
        );
    }

    private static void weaponsexpanded$onClientTick(
            ClientTickEvent.Post event
    ) {
        Minecraft client = Minecraft.getInstance();

        if (client.player == null) {
            return;
        }

        weaponsexpanded$handleChainCrossbowLeftClick(client);
        weaponsexpanded$handleBastardSwordToggleKey(client);
    }

    private static void weaponsexpanded$handleBastardSwordToggleKey(
            Minecraft client
    ) {
        while (WEAPONSEXPANDED$TOGGLE_BASTARD_SWORD_MODE_KEY.consumeClick()) {
            ItemStack stack = client.player.getMainHandItem();

            if (stack.getItem() instanceof BastardSwordItem) {
                ClientPacketDistributor.sendToServer(
                        new ToggleBastardSwordModePayload()
                );

                client.player.resetAttackStrengthTicker();
                return;
            }

            if (stack.getItem() instanceof WarhammerItem) {
                ClientPacketDistributor.sendToServer(
                        new ToggleWarhammerModePayload()
                );

                client.player.resetAttackStrengthTicker();
                return;
            }
        }
    }

    private static void weaponsexpanded$handleChainCrossbowLeftClick(
            Minecraft client
    ) {
        while (client.options.keyAttack.consumeClick()) {
            ItemStack stack = client.player.getMainHandItem();

            if (!(stack.getItem() instanceof ChainCrossbowItem)) {
                return;
            }

            if (!CrossbowItem.isCharged(stack)) {
                return;
            }

            if (client.hitResult != null
                    && client.hitResult.getType() != HitResult.Type.MISS) {
                return;
            }

            ClientPacketDistributor.sendToServer(
                    new FireChainCrossbowPayload()
            );

            client.player.resetAttackStrengthTicker();
        }
    }

    private static void weaponsexpanded$onClientSetup(
            FMLClientSetupEvent event
    ) {
        WeaponsExpanded.LOGGER.info("HELLO FROM CLIENT SETUP");
        WeaponsExpanded.LOGGER.info(
                "MINECRAFT NAME >> {}",
                Minecraft.getInstance().getUser().getName()
        );
    }
}