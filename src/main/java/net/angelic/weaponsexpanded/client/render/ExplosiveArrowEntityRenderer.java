package net.angelic.weaponsexpanded.client.render;

import net.angelic.weaponsexpanded.entity.projectile.ExplosiveArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;

public class ExplosiveArrowEntityRenderer extends ArrowRenderer<ExplosiveArrowEntity, ArrowRenderState> {

    public ExplosiveArrowEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }

    @Override
    public Identifier getTextureLocation(ArrowRenderState arrowRenderState) {
        return TippableArrowRenderer.NORMAL_ARROW_LOCATION;
    }
}