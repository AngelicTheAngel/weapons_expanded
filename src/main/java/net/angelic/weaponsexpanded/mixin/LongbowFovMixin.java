package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.item.custom.LongbowItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public class LongbowFovMixin {

    @Inject(method = "getFovMultiplier", at = @At("RETURN"), cancellable = true)
    private void weaponsexpanded$longbowZoom(CallbackInfoReturnable<Float> cir) {
        AbstractClientPlayerEntity player = (AbstractClientPlayerEntity) (Object) this;

        if (player.isUsingItem() && player.getActiveItem().isOf(ModItems.LONGBOW)) {
            int i = player.getItemUseTime();
            float pull = (float) i / (float) LongbowItem.getFullDrawTicks();

            if (pull > 1.0F) {
                pull = 1.0F;
            } else {
                pull *= pull;
            }

            // cir.getReturnValue() is likely 1.0 or modified by speed/other factors.
            // We multiply our zoom (0.15f strength) into it.
            cir.setReturnValue(cir.getReturnValue() * (1.0F - pull * 0.15F));
        }
    }
}
