package net.angelic.weaponsexpanded.mixin.two_handed_sword;

import net.angelic.weaponsexpanded.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class TwoHandedSwordMixin {

    @Unique
    private static boolean weaponsexpanded$isEffectivelyTwoHanded(ItemStack mainHandStack) {
        if (mainHandStack.getItem() instanceof TwoHandedSwordItem) return true;
        if (mainHandStack.getItem() instanceof BastardSwordItem bastardSword) {
            return bastardSword.isTwoHanded(mainHandStack);
        }
        return false;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void weaponsexpanded$twoHandedSwordTick(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (!(entity instanceof Player player)) return;

        ItemStack mainHandStack = player.getMainHandItem();
        if (!weaponsexpanded$isEffectivelyTwoHanded(mainHandStack)) return;

        if (!WeaponsExpandedConfig.twohandedSword) {
            if (player.isUsingItem()) {
                player.releaseUsingItem();
            }
        } else {
            ItemStack offhand = player.getOffhandItem();
            if (!offhand.isEmpty()) {
                player.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
                player.handleExtraItemsCreatedOnUse(offhand);
            }
        }
    }

    @Inject(method = "startUsingItem(Lnet/minecraft/world/InteractionHand;)V", at = @At("HEAD"), cancellable = true)
    private void weaponsexpanded$preventOffhandUseWhileTwoHanded(InteractionHand hand, CallbackInfo ci) {
        if (WeaponsExpandedConfig.twohandedSword) return;

        LivingEntity entity = (LivingEntity) (Object) this;
        if (!(entity instanceof Player player)) return;

        ItemStack mainHandStack = player.getMainHandItem();
        if (!weaponsexpanded$isEffectivelyTwoHanded(mainHandStack)) return;

        if (hand == InteractionHand.OFF_HAND) {
            ci.cancel();
        }
    }
}
