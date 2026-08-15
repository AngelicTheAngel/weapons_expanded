package net.angelic.weaponsexpanded.client.render;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HeavyArrowEntityRenderer
        extends ArrowRenderer<HeavyArrowEntity> {

    private static final ResourceLocation HEAVY_ARROW_TEXTURE =
            ResourceLocation.fromNamespaceAndPath(
                    WeaponsExpanded.MOD_ID,
                    "textures/entity/projectiles/heavy_arrow.png"
            );

    public HeavyArrowEntityRenderer(
            EntityRendererProvider.Context context
    ) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(
            HeavyArrowEntity entity
    ) {
        return HEAVY_ARROW_TEXTURE;
    }
}