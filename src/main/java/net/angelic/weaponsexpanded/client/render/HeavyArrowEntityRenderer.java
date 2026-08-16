package net.angelic.weaponsexpanded.client.render;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;

public class HeavyArrowEntityRenderer
        extends ArrowRenderer<HeavyArrowEntity, ArrowRenderState> {

    private static final Identifier HEAVY_ARROW_TEXTURE =
            Identifier.fromNamespaceAndPath(
                    WeaponsExpanded.MOD_ID,
                    "textures/entity/projectiles/heavy_arrow.png"
            );

    public HeavyArrowEntityRenderer(
            EntityRendererProvider.Context context
    ) {
        super(context);
    }

    @Override
    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }

    @Override
    public Identifier getTextureLocation(ArrowRenderState arrowRenderState) {
        return HEAVY_ARROW_TEXTURE;
    }
}