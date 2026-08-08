package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererArmPoseMixin {

    @Inject(
            method = "getArmPose(Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState;Lnet/minecraft/client/render/entity/state/PlayerEntityRenderState$HandState;Lnet/minecraft/util/Hand;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void weaponsexpanded$overrideArmPose(
            PlayerEntityRenderState state,
            PlayerEntityRenderState.HandState handState,
            Hand hand,
            CallbackInfoReturnable<BipedEntityModel.ArmPose> cir
    ) {
        if (hand != Hand.MAIN_HAND) {
            return;
        }

        ItemStack main = state.getMainHandStack();

        if (main.getItem() instanceof TwoHandedSwordItem) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            return;
        }

        if (main.getItem() instanceof BastardSwordItem bastardSword
                && bastardSword.isTwoHanded(main)) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            return;
        }

        if (main.getItem() instanceof ChainCrossbowItem && CrossbowItem.isCharged(main)) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}