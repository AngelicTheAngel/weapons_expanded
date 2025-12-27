package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedHeavySwordItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
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
public abstract class PlayerEntityRendererArmPoseMixin {

    @Inject(
            method = "getArmPose(Lnet/minecraft/entity/PlayerLikeEntity;Lnet/minecraft/util/Arm;)Lnet/minecraft/client/render/entity/model/BipedEntityModel$ArmPose;",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void weaponsexpanded$overrideArmPose(
            PlayerLikeEntity player,
            Arm arm,
            CallbackInfoReturnable<BipedEntityModel.ArmPose> cir
    ) {
        ItemStack main = player.getStackInHand(Hand.MAIN_HAND);

        // Two-handed swords: always force the pose
        if (main.getItem() instanceof TwoHandedSwordItem || main.getItem() instanceof TwoHandedHeavySwordItem) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            return;
        }

        // Chain crossbow: force pose only when charged
        if (main.getItem() instanceof ChainCrossbowItem && CrossbowItem.isCharged(main)) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}
