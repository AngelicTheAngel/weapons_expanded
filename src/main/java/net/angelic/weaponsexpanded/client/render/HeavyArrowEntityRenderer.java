package net.angelic.weaponsexpanded.client.render;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TippableArrowRenderer;
import net.minecraft.client.renderer.entity.state.TippableArrowRenderState;
import net.minecraft.resources.Identifier;

public class HeavyArrowEntityRenderer extends TippableArrowRenderer {
    private static final Identifier HEAVY_ARROW =
            Identifier.fromNamespaceAndPath(WeaponsExpanded.MODID, "textures/entity/projectiles/heavy_arrow.png");

    public HeavyArrowEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Identifier getTextureLocation(TippableArrowRenderState state) {
        return HEAVY_ARROW;
    }
}
