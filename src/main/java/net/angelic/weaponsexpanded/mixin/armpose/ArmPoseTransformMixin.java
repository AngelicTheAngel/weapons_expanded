package net.angelic.weaponsexpanded.mixin.armpose;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public abstract class ArmPoseTransformMixin {
    @Inject(
            method = "poseRightArm",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaponsexpanded$poseRightArm(HumanoidRenderState state, CallbackInfo ci) {
        if (state.rightArmPose != HumanoidModel.ArmPose.WEAPONSEXPANDED_SCYTHE_HOLD) {
            return;
        }

        weaponsexpanded$applyScythePose(true);
        ci.cancel();
    }

    @Inject(
            method = "poseLeftArm",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaponsexpanded$poseLeftArm(HumanoidRenderState state, CallbackInfo ci) {
        if (state.leftArmPose != HumanoidModel.ArmPose.WEAPONSEXPANDED_SCYTHE_HOLD) {
            return;
        }

        weaponsexpanded$applyScythePose(false);
        ci.cancel();
    }

    @Unique
    private void weaponsexpanded$applyScythePose(boolean rightHanded) {
        HumanoidModel<?> model = (HumanoidModel<?>)(Object)this;

        if(rightHanded) {
            model.rightArm.xRot = -1.5F;
            model.rightArm.yRot = -0.50F;
            model.rightArm.zRot = 0.9F;

            model.leftArm.xRot = -1.75F;
            model.leftArm.yRot = 0.35F;
            model.leftArm.zRot = 0.0F;
        } else {
            model.rightArm.xRot = -1.65F;
            model.rightArm.yRot = -0.45F;
            model.rightArm.zRot = 0.0F;

            model.leftArm.xRot = -1.5F;
            model.leftArm.yRot = 0.50F;
            model.leftArm.zRot = -0.9F;
        }
    }
}