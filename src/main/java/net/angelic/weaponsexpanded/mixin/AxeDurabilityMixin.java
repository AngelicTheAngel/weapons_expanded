package net.angelic.weaponsexpanded.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.custom.BluntWeaponItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public abstract class AxeDurabilityMixin {

    @ModifyExpressionValue(
            method = "postDamageEntity("
                    + "Lnet/minecraft/entity/LivingEntity;"
                    + "Lnet/minecraft/entity/LivingEntity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/component/type/WeaponComponent;"
                            + "itemDamagePerAttack()I"
            ),
            require = 1
    )
    private int weaponsexpanded$axeHitDurabilityCost(int originalAmount) {
        ItemStack stack = (ItemStack) (Object) this;

        if (WeaponsExpandedConfig.get().disableExtraDurabilityDamageForAxes
                && stack.isIn(ItemTags.AXES) || stack.getItem() instanceof BluntWeaponItem) {
            return 1;
        }

        return originalAmount;
    }
}