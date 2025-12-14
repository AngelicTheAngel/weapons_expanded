package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.PlayerLikeEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntityRenderer.class)
public abstract class PlayerEntityRendererChainCrossbowPoseMixin {

    @Inject(
            method = "getArmPose(Lnet/minecraft/entity/PlayerLikeEntity;Lnet/minecraft/util/Arm;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void weaponsexpanded$forceChainCrossbowPose(
            PlayerLikeEntity player,
            Arm arm,
            CallbackInfoReturnable<BipedEntityModel.ArmPose> cir
    ) {
        ItemStack main = player.getStackInHand(Hand.MAIN_HAND);
        if (!(main.getItem() instanceof ChainCrossbowItem)) return;

        if (CrossbowItem.isCharged(main)) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}
