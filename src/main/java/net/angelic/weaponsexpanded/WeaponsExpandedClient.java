package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactories;

public class WeaponsExpandedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererFactories.register(ModEntities.HEAVY_ARROW, ArrowEntityRenderer::new);
    }
}