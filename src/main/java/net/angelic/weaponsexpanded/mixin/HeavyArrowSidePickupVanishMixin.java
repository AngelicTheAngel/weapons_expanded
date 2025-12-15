package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PersistentProjectileEntity.class)
public abstract class HeavyArrowSidePickupVanishMixin {

    @Unique
    private static final String WEAPONSEXPANDED$SIDE_HEAVY_ARROW_TAG = "weaponsexpanded.side_heavy_arrow";
    @Unique
    private static final String WEAPONSEXPANDED$CREATIVE_FIRED_TAG = "weaponsexpanded.creative_fired_heavy_arrow";

    @Inject(method = "tryPickup", at = @At("HEAD"), cancellable = true)
    private void weaponsexpanded$vanishHeavyArrowOnPickup(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;

        if (!(projectile instanceof HeavyArrowEntity)) return;

        boolean markedToVanish =
                projectile.getCommandTags().contains(WEAPONSEXPANDED$SIDE_HEAVY_ARROW_TAG)
                        || projectile.getCommandTags().contains(WEAPONSEXPANDED$CREATIVE_FIRED_TAG);

        if (!markedToVanish) return;

        // Only creative players can "vacuum" these arrows
        if (!player.isInCreativeMode()) {
            cir.setReturnValue(false);
            return;
        }

        // Creative: remove entity, grant no item, but still look like a pickup (sendPickup gets called)
        projectile.discard();
        cir.setReturnValue(true);
    }
}
