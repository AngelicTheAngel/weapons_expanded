package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.client.render.HeavyArrowEntityRenderer;
import net.angelic.weaponsexpanded.entity.ModEntities;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.network.FireChainCrossbowPayload;
import net.angelic.weaponsexpanded.network.ModPackets;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRendererFactories;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;

public class WeaponsExpandedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererFactories.register(ModEntities.HEAVY_ARROW, HeavyArrowEntityRenderer::new);

        // Register payload types once (guarded against double-register)
        ModPackets.register();

        ClientTickEvents.END_CLIENT_TICK.register(WeaponsExpandedClient::weaponsexpanded$handleChainCrossbowLeftClick);
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