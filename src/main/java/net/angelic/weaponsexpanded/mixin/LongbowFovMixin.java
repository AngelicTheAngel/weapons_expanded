package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.item.custom.LongbowItem;
import net.minecraft.client.player.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public class LongbowFovMixin {

    @Inject(method = "getFieldOfViewModifier", at = @At("RETURN"), cancellable = true)
    private void weaponsexpanded$longbowZoom(CallbackInfoReturnable<Float> cir) {
        AbstractClientPlayer player = (AbstractClientPlayer) (Object) this;

        if (player.isUsingItem() && player.getUseItem().is(ModItems.LONGBOW)) {
            int i = player.getTicksUsingItem();
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
