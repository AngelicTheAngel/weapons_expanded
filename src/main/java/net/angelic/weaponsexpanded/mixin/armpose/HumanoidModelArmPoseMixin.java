package net.angelic.weaponsexpanded.mixin.armpose;

import net.minecraft.client.model.HumanoidModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(HumanoidModel.ArmPose.class)
enum HumanoidModelArmPoseMixin {
    WEAPONSEXPANDED_SCYTHE_HOLD(true, true);

    @Shadow
    HumanoidModelArmPoseMixin(boolean twoHanded, boolean affectsOffhandPose) {}
}
