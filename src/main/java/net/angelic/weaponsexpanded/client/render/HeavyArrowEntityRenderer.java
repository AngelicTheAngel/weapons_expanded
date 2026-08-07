package net.angelic.weaponsexpanded.client.render;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.client.render.entity.ArrowEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.Identifier;

public class HeavyArrowEntityRenderer extends ArrowEntityRenderer {
    private static final Identifier HEAVY_ARROW =
            Identifier.of(WeaponsExpanded.MOD_ID, "textures/entity/projectiles/heavy_arrow.png");

    public HeavyArrowEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
    }

    @Override
    public Identifier getTexture(ArrowEntity arrowEntity) {
        return HEAVY_ARROW;
    }
}
