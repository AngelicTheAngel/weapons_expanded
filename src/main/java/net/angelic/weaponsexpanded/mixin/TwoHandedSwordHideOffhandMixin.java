package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.custom.TwoHandedHeavySwordItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Hides the OFF_HAND item in first-person when holding a two-handed sword in MAIN_HAND.
 */
@Mixin(HeldItemRenderer.class)
public abstract class TwoHandedSwordHideOffhandMixin {

    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"), cancellable = true)
    private void weaponsexpanded$hideOffhandWhenTwoHanded(
            AbstractClientPlayerEntity player,
            float tickProgress,
            float pitch,
            Hand hand,
            float swingProgress,
            ItemStack item,
            float equipProgress,
            MatrixStack matrices,
            OrderedRenderCommandQueue orderedRenderCommandQueue,
            int light,
            CallbackInfo ci
    ) {
        if (hand != Hand.OFF_HAND) return;

        if (player.getMainHandStack().getItem() instanceof TwoHandedSwordItem
                || player.getMainHandStack().getItem() instanceof TwoHandedHeavySwordItem) {
            ci.cancel();
        }
    }
}
