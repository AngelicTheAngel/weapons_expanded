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
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import org.lwjgl.glfw.GLFW;

import static net.angelic.weaponsexpanded.WeaponsExpanded.MOD_ID;

public class WeaponsExpandedClient implements ClientModInitializer {

    private static final KeyBinding.Category WEAPONSEXPANDED$KEY_CATEGORY =
            KeyBinding.Category.create(Identifier.of(MOD_ID, "general"));
    private static final String WEAPONSEXPANDED$KEY_TOGGLE_BASTARD_SWORD =
            "key.weaponsexpanded.toggle_bastard_sword_mode";

    private static KeyBinding weaponsexpanded$toggleBastardSwordModeKey;

    @Override
    public void onInitializeClient() {
        EntityRendererFactories.register(ModEntities.HEAVY_ARROW, HeavyArrowEntityRenderer::new);
        EntityRendererFactories.register(ModEntities.EXPLOSIVE_ARROW, ArrowEntityRenderer::new);

        weaponsexpanded$toggleBastardSwordModeKey = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        WEAPONSEXPANDED$KEY_TOGGLE_BASTARD_SWORD,
                        GLFW.GLFW_KEY_V,
                        WEAPONSEXPANDED$KEY_CATEGORY
                )
        );

        // Register payload types once (guarded against double-register)
        ModPackets.register();

        ClientTickEvents.END_CLIENT_TICK.register(WeaponsExpandedClient::weaponsexpanded$handleChainCrossbowLeftClick);
        ClientTickEvents.END_CLIENT_TICK.register(WeaponsExpandedClient::weaponsexpanded$handleBastardSwordToggleKey);
    }

    private static void weaponsexpanded$handleBastardSwordToggleKey(MinecraftClient client) {
        if (client.player == null) return;

        while (weaponsexpanded$toggleBastardSwordModeKey.wasPressed()) {
            ItemStack stack = client.player.getMainHandStack();
            if (!(stack.getItem() instanceof BastardSwordItem || stack.getItem() instanceof WarhammerItem)) return;

            if (stack.getItem() instanceof BastardSwordItem) {
                ClientPlayNetworking.send(new ToggleBastardSwordModePayload());
            }

            if (stack.getItem() instanceof WarhammerItem) {
                ClientPlayNetworking.send(new ToggleWarhammerModePayload());
            }
        }
    }

    private static void weaponsexpanded$handleChainCrossbowLeftClick(MinecraftClient client) {
        if (client.player == null) return;
        if (client.options == null) return;

        while (client.options.attackKey.wasPressed()) {
            ItemStack stack = client.player.getMainHandStack();
            if (!(stack.getItem() instanceof ChainCrossbowItem)) return;
            if (!net.minecraft.item.CrossbowItem.isCharged(stack)) return;

            if (client.crosshairTarget != null && client.crosshairTarget.getType() != HitResult.Type.MISS) return;

            ClientPlayNetworking.send(new FireChainCrossbowPayload());
        }
    }
}