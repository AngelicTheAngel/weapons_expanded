package net.angelic.weaponsexpanded.client.render;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class HeavyArrowEntityRenderer
        extends ProjectileEntityRenderer<HeavyArrowEntity> {

    private static final Identifier HEAVY_ARROW_TEXTURE =
            new Identifier(
                    WeaponsExpanded.MOD_ID,
                    "textures/entity/projectiles/heavy_arrow.png"
            );

    public HeavyArrowEntityRenderer(
            EntityRendererFactory.Context context
    ) {
        super(context);
    }

    @Override
    public Identifier getTexture(HeavyArrowEntity entity) {
        return HEAVY_ARROW_TEXTURE;
    }
}