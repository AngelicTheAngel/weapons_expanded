package net.angelic.weaponsexpanded.mixin.two_handed_weapon;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.HalberdItem;
import net.angelic.weaponsexpanded.util.tags.ModItemTags;
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
public abstract class TwoHandedWeaponMixin {

    @Unique
    private static boolean weaponsexpanded$isEffectivelyTwoHanded(ItemStack mainHandStack) {
        if (mainHandStack.is(ModItemTags.TWOHANDED)) return true;
        if (mainHandStack.getItem() instanceof BastardSwordItem bastardSword) {
            return bastardSword.isTwoHanded(mainHandStack);
        }
        if (mainHandStack.getItem() instanceof HalberdItem halberd) {
            return !halberd.isPiercing(mainHandStack);
        }
        return false;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void weaponsexpanded$twoHandedSwordTick(CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;
        if (!(entity instanceof Player player)) return;

        ItemStack mainHandStack = player.getMainHandItem();
        if (!weaponsexpanded$isEffectivelyTwoHanded(mainHandStack)) return;

        if (!WeaponsExpandedConfig.get().altTwoHandedSwordHandling) {
            // If they were already using *anything* (including offhand) and then ended up holding a two-handed sword,
            // stop the use action.
            if (player.isUsingItem()) {
                player.releaseUsingItem();
            }
        } else {
            ItemStack offhand = player.getOffhandItem();
            if (!offhand.isEmpty()) {
                ItemStack stack = player.getOffhandItem();
                player.setItemInHand(InteractionHand.OFF_HAND, ItemStack.EMPTY);
                player.handleExtraItemsCreatedOnUse(stack);
            }
        }
    }

    @Inject(method = "startUsingItem(Lnet/minecraft/world/InteractionHand;)V", at = @At("HEAD"), cancellable = true)
    private void weaponsexpanded$preventOffhandUseWhileTwoHanded(InteractionHand hand, CallbackInfo ci) {
        if (WeaponsExpandedConfig.get().altTwoHandedSwordHandling) return;

        LivingEntity entity = (LivingEntity) (Object) this;
        if (!(entity instanceof Player player)) return;

        ItemStack mainHandStack = player.getMainHandItem();
        if (!weaponsexpanded$isEffectivelyTwoHanded(mainHandStack)) return;

        // Block *any* attempt to start "using" the OFF_HAND item while two-handing.
        if (hand == InteractionHand.OFF_HAND) {
            ci.cancel();
        }
    }
}