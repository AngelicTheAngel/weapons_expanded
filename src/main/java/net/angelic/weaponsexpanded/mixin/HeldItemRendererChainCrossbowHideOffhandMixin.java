package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererChainCrossbowHideOffhandMixin {

    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"), cancellable = true)
    private void weaponsexpanded$hideOffhandWhenChainCrossbow(
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

        ItemStack main = player.getMainHandStack();
        if (!(main.getItem() instanceof ChainCrossbowItem)) return;

        boolean mainIsCharged = CrossbowItem.isCharged(main);
        boolean mainIsBeingUsed = player.isUsingItem() && player.getActiveHand() == Hand.MAIN_HAND;

        // Hide offhand when charged OR while loading/using (vanilla-like behavior)
        if (mainIsCharged || mainIsBeingUsed) {
            ci.cancel();
        }
    }
}
