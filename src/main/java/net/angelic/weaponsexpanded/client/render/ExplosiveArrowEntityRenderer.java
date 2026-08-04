package net.angelic.weaponsexpanded.client.render;

import net.angelic.weaponsexpanded.entity.projectile.ExplosiveArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.resources.ResourceLocation;

public class ExplosiveArrowEntityRenderer
        extends ArrowRenderer<ExplosiveArrowEntity> {

    public ExplosiveArrowEntityRenderer(
            EntityRendererProvider.Context context
    ) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(
            ExplosiveArrowEntity entity
    ) {
        return TippableArrowRenderer.NORMAL_ARROW_LOCATION;
    }
}