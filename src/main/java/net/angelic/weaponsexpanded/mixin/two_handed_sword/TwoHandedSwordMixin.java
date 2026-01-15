package net.angelic.weaponsexpanded.mixin.two_handed_sword;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class TwoHandedSwordMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void weaponsexpanded$twoHandedSwordTick(CallbackInfo ci) {
        if (!WeaponsExpandedConfig.get().altTwoHandedSwordHandling) {
            LivingEntity entity = (LivingEntity) (Object) this;
            if (!(entity instanceof PlayerEntity player)) return;

            ItemStack mainHandStack = player.getMainHandStack();
            boolean holdingTwoHanded =
                    mainHandStack.getItem() instanceof TwoHandedSwordItem;

            if (!holdingTwoHanded) return;

            // If they were already using *anything* (including offhand) and then ended up holding a two-handed sword,
            // stop the use action.
            if (player.isUsingItem()) {
                player.stopUsingItem();
            }
        } else if (WeaponsExpandedConfig.get().altTwoHandedSwordHandling) {
            LivingEntity entity = (LivingEntity) (Object) this;
            if (!(entity instanceof PlayerEntity player)) return;

            ItemStack mainHandStack = player.getMainHandStack();
            boolean holdingTwoHanded =
                    mainHandStack.getItem() instanceof TwoHandedSwordItem;

            if (!holdingTwoHanded) return;

            ItemStack offhand = player.getOffHandStack();
            if (!offhand.isEmpty()) {
                ItemStack stack = player.getOffHandStack();
                player.setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
                player.giveOrDropStack(stack);
            }
        }
    }

    @Inject(method = "setCurrentHand(Lnet/minecraft/util/Hand;)V", at = @At("HEAD"), cancellable = true)
    private void weaponsexpanded$preventOffhandUseWhileTwoHanded(Hand hand, CallbackInfo ci) {
        if (!WeaponsExpandedConfig.get().altTwoHandedSwordHandling) {
            LivingEntity entity = (LivingEntity) (Object) this;
            if (!(entity instanceof PlayerEntity player)) return;

            ItemStack mainHandStack = player.getMainHandStack();
            boolean holdingTwoHanded =
                    mainHandStack.getItem() instanceof TwoHandedSwordItem;

            if (!holdingTwoHanded) return;

            // Block *any* attempt to start "using" the OFF_HAND item while two-handing.
            if (hand == Hand.OFF_HAND) {
                ci.cancel();
            }
        }
    }
}
