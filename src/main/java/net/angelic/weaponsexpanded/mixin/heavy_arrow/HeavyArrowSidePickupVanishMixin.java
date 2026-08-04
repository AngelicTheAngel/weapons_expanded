package net.angelic.weaponsexpanded.mixin.heavy_arrow;

import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractArrow.class)
public abstract class HeavyArrowSidePickupVanishMixin {

    @Unique
    private static final String
            WEAPONSEXPANDED$SIDE_HEAVY_ARROW_TAG =
            "weaponsexpanded.side_heavy_arrow";

    @Unique
    private static final String
            WEAPONSEXPANDED$CREATIVE_FIRED_TAG =
            "weaponsexpanded.creative_fired_heavy_arrow";

    @Inject(
            method = "tryPickup",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaponsexpanded$vanishHeavyArrowOnPickup(
            Player player,
            CallbackInfoReturnable<Boolean> cir
    ) {
        AbstractArrow projectile =
                (AbstractArrow) (Object) this;

        if (!(projectile instanceof HeavyArrowEntity)) {
            return;
        }

        boolean markedToVanish =
                projectile.getTags().contains(
                        WEAPONSEXPANDED$SIDE_HEAVY_ARROW_TAG
                )
                        || projectile.getTags().contains(
                        WEAPONSEXPANDED$CREATIVE_FIRED_TAG
                );

        if (!markedToVanish) {
            return;
        }

        /*
         * Survival players cannot collect side or
         * creative-fired heavy arrows.
         */
        if (!player.getAbilities().instabuild) {
            cir.setReturnValue(false);
            return;
        }

        /*
         * Creative players remove the projectile without
         * receiving an arrow item.
         */
        projectile.discard();
        cir.setReturnValue(true);
    }
}