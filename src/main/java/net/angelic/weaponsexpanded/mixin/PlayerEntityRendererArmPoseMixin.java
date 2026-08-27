package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.util.ModItemTags;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AvatarRenderer.class)
public abstract class PlayerEntityRendererArmPoseMixin {

    @Inject(
            method = "getArmPose(Lnet/minecraft/world/entity/Avatar;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/InteractionHand;)Lnet/minecraft/client/model/HumanoidModel$ArmPose;",
            at = @At("RETURN"),
            cancellable = true
    )
    private static void weaponsexpanded$overrideArmPose(Avatar player, ItemStack itemInHand, InteractionHand hand, CallbackInfoReturnable<HumanoidModel.ArmPose> cir) {
        if (itemInHand.is(ModItemTags.TWOHANDED) && hand == InteractionHand.MAIN_HAND) {
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
            return;
        }

        if (itemInHand.is(ModItemTags.PIERCE) && hand == InteractionHand.MAIN_HAND) {
            cir.setReturnValue(HumanoidModel.ArmPose.SPEAR);
        }

        if (itemInHand.getItem() instanceof BastardSwordItem && ((BastardSwordItem) itemInHand.getItem()).isTwoHanded(itemInHand) && hand == InteractionHand.MAIN_HAND) {
            if (((BastardSwordItem) itemInHand.getItem()).isTwoHanded(itemInHand)) {
                cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
                return;
            }
        }

        if (itemInHand.getItem() instanceof ChainCrossbowItem && CrossbowItem.isCharged(itemInHand) && hand == InteractionHand.MAIN_HAND) {
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
        }
    }
}
