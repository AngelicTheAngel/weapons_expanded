package net.angelic.weaponsexpanded.mixin.two_handed_sword;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class TwoHandedSwordMixin {

    @Unique
    private static boolean weaponsexpanded$isEffectivelyTwoHanded(
            ItemStack mainHandStack
    ) {
        if (mainHandStack.getItem() instanceof TwoHandedSwordItem) {
            return true;
        }

        if (mainHandStack.getItem() instanceof BastardSwordItem bastardSword) {
            return bastardSword.isTwoHanded(mainHandStack);
        }

        return false;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void weaponsexpanded$twoHandedSwordTick(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (!(entity instanceof PlayerEntity player)) return;

        ItemStack mainHandStack = player.getMainHandStack();

        if (!weaponsexpanded$isEffectivelyTwoHanded(mainHandStack)) {
            return;
        }

        if (!WeaponsExpandedConfig.get().altTwoHandedSwordHandling) {
            // Stop any active use action, including offhand use.
            if (player.isUsingItem()) {
                player.stopUsingItem();
            }

            return;
        }

        ItemStack offhandStack = player.getOffHandStack();

        if (offhandStack.isEmpty()) return;

        // Remove the offhand item before attempting to move it.
        player.setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);

        // Insert as much as possible into the inventory.
        player.getInventory().insertStack(offhandStack);

        // Drop anything that did not fit.
        if (!offhandStack.isEmpty()) {
            player.dropItem(offhandStack, false);
        }
    }

    @Inject(
            method = "setCurrentHand(Lnet/minecraft/util/Hand;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void weaponsexpanded$preventOffhandUseWhileTwoHanded(
            Hand hand,
            CallbackInfo ci
    ) {
        if (WeaponsExpandedConfig.get().altTwoHandedSwordHandling) {
            return;
        }

        LivingEntity entity = (LivingEntity) (Object) this;

        if (!(entity instanceof PlayerEntity player)) return;

        ItemStack mainHandStack = player.getMainHandStack();

        if (!weaponsexpanded$isEffectivelyTwoHanded(mainHandStack)) {
            return;
        }

        // Prevent using any offhand item while wielding a two-handed sword.
        if (hand == Hand.OFF_HAND) {
            ci.cancel();
        }
    }
}