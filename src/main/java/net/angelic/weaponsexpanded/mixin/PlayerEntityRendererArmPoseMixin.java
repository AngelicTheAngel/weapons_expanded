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
        // Two-handed swords: always force the pose
        if (itemInHand.is(ModItemTags.TWOHANDED)) {
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
            return;
        }

        // Bastard swords: force pose when two-handed
        if (itemInHand.getItem() instanceof BastardSwordItem && ((BastardSwordItem) itemInHand.getItem()).isTwoHanded(itemInHand)) {
            if (((BastardSwordItem) itemInHand.getItem()).isTwoHanded(itemInHand)) {
                cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
                return;
            }
        }

        // Chain crossbow: force pose only when charged
        if (itemInHand.getItem() instanceof ChainCrossbowItem && CrossbowItem.isCharged(itemInHand)) {
            cir.setReturnValue(HumanoidModel.ArmPose.CROSSBOW_HOLD);
        }

        if (itemInHand.is(ModItemTags.PIERCE)) {
            cir.setReturnValue(HumanoidModel.ArmPose.SPEAR);
        }
    }
}
