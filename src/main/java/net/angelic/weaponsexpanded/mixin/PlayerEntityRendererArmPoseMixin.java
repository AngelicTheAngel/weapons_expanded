package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AvatarRenderer.class)
public abstract class PlayerEntityRendererArmPoseMixin {

    @Inject(
            method = "getArmPose(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/world/entity/HumanoidArm;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void weaponsexpanded$overrideArmPose(
            Avatar player,
            HumanoidArm arm,
            CallbackInfoReturnable<HumanoidModel.ArmPose> cir
    ) {
        ItemStack main = player.getItemInHand(InteractionHand.MAIN_HAND);

        if (main.getItem() instanceof TwoHandedSwordItem) {
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
            return;
        }

        if (main.getItem() instanceof BastardSwordItem bastardSword && bastardSword.isTwoHanded(main)) {
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
            return;
        }

        if (main.getItem() instanceof ChainCrossbowItem && CrossbowItem.isCharged(main)) {
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}
