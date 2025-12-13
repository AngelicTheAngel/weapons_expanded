package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.angelic.weaponsexpanded.client.render.HeavyArrowEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactories;

public class WeaponsExpandedClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererFactories.register(ModEntities.HEAVY_ARROW, HeavyArrowEntityRenderer::new);
    }
}