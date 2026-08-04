package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
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
public abstract class HeldItemRendererHideOffhandMixin {

    @Inject(
            method = "renderFirstPersonItem",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaponsexpanded$hideOffhandForCertainMainhandItems(
            AbstractClientPlayerEntity player,
            float tickDelta,
            float pitch,
            Hand hand,
            float swingProgress,
            ItemStack item,
            float equipProgress,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            CallbackInfo ci
    ) {
        if (hand != Hand.OFF_HAND) return;

        ItemStack mainHandStack = player.getMainHandStack();

        if (mainHandStack.getItem() instanceof TwoHandedSwordItem) {
            ci.cancel();
            return;
        }

        if (mainHandStack.getItem() instanceof BastardSwordItem bastardSword
                && bastardSword.isTwoHanded(mainHandStack)) {
            ci.cancel();
            return;
        }

        if (!(mainHandStack.getItem() instanceof ChainCrossbowItem)) {
            return;
        }

        boolean mainHandCharged =
                CrossbowItem.isCharged(mainHandStack);

        boolean usingMainHand =
                player.isUsingItem()
                        && player.getActiveHand() == Hand.MAIN_HAND;

        if (mainHandCharged || usingMainHand) {
            ci.cancel();
        }
    }
}