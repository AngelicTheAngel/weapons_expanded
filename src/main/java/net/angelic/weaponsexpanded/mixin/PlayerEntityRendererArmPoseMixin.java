package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
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
            method = "getArmPose("
                    + "Lnet/minecraft/client/network/AbstractClientPlayerEntity;"
                    + "Lnet/minecraft/util/Hand;)"
                    + "Lnet/minecraft/client/render/entity/model/"
                    + "BipedEntityModel$ArmPose;",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void weaponsexpanded$overrideArmPose(
            AbstractClientPlayerEntity player,
            Hand hand,
            CallbackInfoReturnable<BipedEntityModel.ArmPose> cir
    ) {
        // CROSSBOW_HOLD is a two-handed pose, so only assign it to
        // the main-hand arm-pose entry.
        if (hand != Hand.MAIN_HAND) return;

        ItemStack mainHandStack = player.getMainHandStack();

        // Two-handed swords always use the two-handed holding pose.
        if (mainHandStack.getItem() instanceof TwoHandedSwordItem) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            return;
        }

        // Bastard swords use it only while in two-handed mode.
        if (mainHandStack.getItem() instanceof BastardSwordItem bastardSword
                && bastardSword.isTwoHanded(mainHandStack)) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
            return;
        }

        // Chain crossbows use it while charged.
        if (mainHandStack.getItem() instanceof ChainCrossbowItem
                && CrossbowItem.isCharged(mainHandStack)) {
            cir.setReturnValue(BipedEntityModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}