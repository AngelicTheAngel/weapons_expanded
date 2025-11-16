
package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.custom.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.LivingEntity;

@Mixin(LivingEntity.class)
public abstract class TwoHandedSwordMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void onTick(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity)(Object)this;

        if (!(entity instanceof PlayerEntity player)) return;

        ItemStack mainHandStack = player.getMainHandStack();

        if (!(mainHandStack.getItem() instanceof TwoHandedSwordItem)) return;

        ItemStack offhand = player.getOffHandStack();
        if (!offhand.isEmpty()) {
            ItemStack stack = player.getOffHandStack();
            player.setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
            player.getInventory().insertStack(stack);
        }
    }
}
