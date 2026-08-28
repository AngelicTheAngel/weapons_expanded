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
import net.angelic.weaponsexpanded.util.ModItemTags;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.ChatFormatting;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.HitResult;
import org.lwjgl.glfw.GLFW;

import static net.angelic.weaponsexpanded.WeaponsExpanded.MOD_ID;

public class WeaponsExpandedClient implements ClientModInitializer {

    private static final KeyMapping.Category WEAPONSEXPANDED$KEY_CATEGORY =
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath(MOD_ID, "general"));
    private static final String WEAPONSEXPANDED$KEY_TOGGLE_BASTARD_SWORD =
            "key.weaponsexpanded.toggle_bastard_sword_mode";

    private static KeyMapping weaponsexpanded$toggleBastardSwordModeKey;

    @Override
    public void onInitializeClient() {
        EntityRenderers.register(ModEntities.HEAVY_ARROW, HeavyArrowEntityRenderer::new);
        EntityRenderers.register(ModEntities.EXPLOSIVE_ARROW, TippableArrowRenderer::new);

        ItemTooltipCallback.EVENT.register((stack, context, flag, tooltip) -> {
            if(stack.is(ModItemTags.TWOHANDED)) {
                Component twoHanded = Component.translatable("tooltip.weaponsexpanded.twohandedsword").withStyle(ChatFormatting.BLUE);
                int i = 1;

                while (i < tooltip.size() && !tooltip.get(i).getString().isBlank()) {
                    i++;
                }

                tooltip.add(i, twoHanded);
            }
        });

        weaponsexpanded$toggleBastardSwordModeKey = KeyMappingHelper.registerKeyMapping(
                new KeyMapping(
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

    private static void weaponsexpanded$handleBastardSwordToggleKey(Minecraft client) {
        if (client.player == null) return;

        while (weaponsexpanded$toggleBastardSwordModeKey.consumeClick()) {
            ItemStack stack = client.player.getMainHandItem();
            if (!(stack.getItem() instanceof BastardSwordItem || stack.getItem() instanceof WarhammerItem)) return;

            if(stack.getItem() instanceof BastardSwordItem) {
                ClientPlayNetworking.send(new ToggleBastardSwordModePayload());
                client.player.resetAttackStrengthTicker();
            }

            if(stack.getItem() instanceof WarhammerItem) {
                ClientPlayNetworking.send(new ToggleWarhammerModePayload());
                client.player.resetAttackStrengthTicker();
            }
        }
    }

    private static void weaponsexpanded$handleChainCrossbowLeftClick(Minecraft client) {
        if (client.player == null) return;
        if (client.options == null) return;

        while (client.options.keyAttack.consumeClick()) {
            ItemStack stack = client.player.getMainHandItem();
            if (!(stack.getItem() instanceof ChainCrossbowItem)) return;
            if (!net.minecraft.world.item.CrossbowItem.isCharged(stack)) return;

            if (client.hitResult != null && client.hitResult.getType() != HitResult.Type.MISS) return;

            ClientPlayNetworking.send(new FireChainCrossbowPayload());
        }
    }
}