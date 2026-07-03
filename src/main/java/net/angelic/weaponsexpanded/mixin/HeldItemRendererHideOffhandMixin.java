package net.angelic.weaponsexpanded.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public abstract class HeldItemRendererHideOffhandMixin {

    @Inject(method = "submitArmWithItem", at = @At("HEAD"), cancellable = true)
    private void weaponsexpanded$hideOffhandForCertainMainhandItems(
            AbstractClientPlayer player,
            float tickProgress,
            float pitch,
            InteractionHand hand,
            float swingProgress,
            ItemStack item,
            float equipProgress,
            PoseStack matrices,
            SubmitNodeCollector orderedRenderCommandQueue,
            int light,
            CallbackInfo ci
    ) {
        if (hand != InteractionHand.OFF_HAND) return;

        ItemStack main = player.getMainHandItem();

        if (main.getItem() instanceof TwoHandedSwordItem) {
            ci.cancel();
            return;
        }

        if (main.getItem() instanceof BastardSwordItem bastardSword && bastardSword.isTwoHanded(main)) {
            ci.cancel();
            return;
        }

        if (!(main.getItem() instanceof ChainCrossbowItem)) return;

        boolean mainIsCharged = CrossbowItem.isCharged(main);
        boolean mainIsBeingUsed = player.isUsingItem() && player.getUsedItemHand() == InteractionHand.MAIN_HAND;

        if (mainIsCharged || mainIsBeingUsed) {
            ci.cancel();
        }
    }
}
