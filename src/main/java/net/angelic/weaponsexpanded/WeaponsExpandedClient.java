package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.client.render.HeavyArrowEntityRenderer;
import net.angelic.weaponsexpanded.entity.ModEntities;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.angelic.weaponsexpanded.network.FireChainCrossbowPayload;
import net.angelic.weaponsexpanded.network.ModPackets;
import net.angelic.weaponsexpanded.network.ToggleBastardSwordModePayload;
import net.angelic.weaponsexpanded.network.ToggleWarhammerModePayload;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import org.lwjgl.glfw.GLFW;

public final class WeaponsExpandedClient {
    private static final KeyMapping.Category WEAPONSEXPANDED$KEY_CATEGORY =
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath(WeaponsExpanded.MODID, "general"));

    private static final String WEAPONSEXPANDED$KEY_TOGGLE_BASTARD_SWORD =
            "key.weaponsexpanded.toggle_bastard_sword_mode";

    private static final KeyMapping WEAPONSEXPANDED$TOGGLE_BASTARD_SWORD_MODE_KEY =
            new KeyMapping(
                    WEAPONSEXPANDED$KEY_TOGGLE_BASTARD_SWORD,
                    GLFW.GLFW_KEY_V,
                    WEAPONSEXPANDED$KEY_CATEGORY
            );

    private WeaponsExpandedClient() {
    }

    /**
     * Call this once from your main mod constructor, client-side only.
     */
    public static void register() {
        RegisterKeyMappingsEvent.BUS.addListener(WeaponsExpandedClient::weaponsexpanded$registerKeyMappings);
        EntityRenderersEvent.RegisterRenderers.BUS.addListener(WeaponsExpandedClient::weaponsexpanded$registerEntityRenderers);
        TickEvent.ClientTickEvent.Post.BUS.addListener(WeaponsExpandedClient::weaponsexpanded$onClientTick);
    }

    private static void weaponsexpanded$registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(WEAPONSEXPANDED$TOGGLE_BASTARD_SWORD_MODE_KEY);
    }

    private static void weaponsexpanded$registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.HEAVY_ARROW.get(), HeavyArrowEntityRenderer::new);
        event.registerEntityRenderer(ModEntities.EXPLOSIVE_ARROW.get(), TippableArrowRenderer::new);
    }

    private static void weaponsexpanded$onClientTick(TickEvent.ClientTickEvent.Post event) {
        Minecraft client = Minecraft.getInstance();

        if (client.player == null) {
            return;
        }

        weaponsexpanded$handleChainCrossbowLeftClick(client);
        weaponsexpanded$handleBastardSwordToggleKey(client);
    }

    private static void weaponsexpanded$handleBastardSwordToggleKey(Minecraft client) {
        while (WEAPONSEXPANDED$TOGGLE_BASTARD_SWORD_MODE_KEY.consumeClick()) {
            ItemStack stack = client.player.getMainHandItem();

            if (stack.getItem() instanceof BastardSwordItem) {
                ModPackets.sendToServer(new ToggleBastardSwordModePayload());
                client.player.resetAttackStrengthTicker();
                return;
            }

            if (stack.getItem() instanceof WarhammerItem) {
                ModPackets.sendToServer(new ToggleWarhammerModePayload());
                client.player.resetAttackStrengthTicker();
                return;
            }
        }
    }

    private static void weaponsexpanded$handleChainCrossbowLeftClick(Minecraft client) {
        if (client.options == null) {
            return;
        }

        while (client.options.keyAttack.consumeClick()) {
            ItemStack stack = client.player.getMainHandItem();

            if (!(stack.getItem() instanceof ChainCrossbowItem)) {
                return;
            }

            if (!CrossbowItem.isCharged(stack)) {
                return;
            }

            if (client.hitResult != null && client.hitResult.getType() != HitResult.Type.MISS) {
                return;
            }

            ModPackets.sendToServer(new FireChainCrossbowPayload());
            client.player.resetAttackStrengthTicker();
        }
    }
}