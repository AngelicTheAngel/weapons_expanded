package net.angelic.weaponsexpanded.mixin.two_handed_weapon;

import com.mojang.blaze3d.vertex.PoseStack;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.HalberdItem;
import net.angelic.weaponsexpanded.util.tags.ModItemTags;
import net.minecraft.client.renderer.FirstPersonHandsAndItemsRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.FirstPersonHandsAndItemsRenderState;
import net.minecraft.client.renderer.state.level.PlayerRenderState;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FirstPersonHandsAndItemsRenderer.class)
public abstract class HeldItemRendererHideOffhandMixin {

    @Inject(method = "submitArmWithItem", at = @At("HEAD"), cancellable = true)
    private void weaponsexpanded$hideOffhandForCertainMainhandItems(
            PlayerRenderState playerState,
            FirstPersonHandsAndItemsRenderState state,
            float partialTicks,
            float xRot,
            InteractionHand hand,
            float attack,
            ItemStack itemStack,
            float inverseArmHeight,
            PoseStack poseStack,
            SubmitNodeCollector submitNodeCollector,
            int lightCoords,
            CallbackInfo ci
    ) {
        if (hand != InteractionHand.OFF_HAND) return;

        boolean isTwoHanded = itemStack.is(ModItemTags.TWOHANDED);

        if (isTwoHanded) {
            ci.cancel();
            return;
        }

        boolean isTwoHandedBastardSword;

        if (itemStack.getItem() instanceof BastardSwordItem bastardSword) {
            isTwoHandedBastardSword = bastardSword.isTwoHanded(itemStack);
        } else {
            isTwoHandedBastardSword = false;
        }

        if (isTwoHandedBastardSword) {
            ci.cancel();
            return;
        }

        boolean isTwoHandedHalberd;

        if (itemStack.getItem() instanceof HalberdItem halberd) {
            isTwoHandedHalberd = !halberd.isPiercing(itemStack);
        } else {
            isTwoHandedHalberd = false;
        }

        if (isTwoHandedHalberd) {
            ci.cancel();
            return;
        }

        boolean isChainCrossbow = itemStack.getItem() instanceof ChainCrossbowItem;
        if (!isChainCrossbow) return;

        boolean mainIsCharged = CrossbowItem.isCharged(itemStack);

        if (mainIsCharged) {
            ci.cancel();
        }
    }
}