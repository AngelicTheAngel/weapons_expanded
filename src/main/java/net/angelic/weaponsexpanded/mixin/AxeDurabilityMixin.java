package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.custom.BluntWeaponItem;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(ItemStack.class)
public abstract class AxeDurabilityMixin {

    @ModifyArg(
            method =
                    "postHurtEnemy("
                            + "Lnet/minecraft/world/entity/LivingEntity;"
                            + "Lnet/minecraft/world/entity/LivingEntity;"
                            + ")V",
            at = @At(
                    value = "INVOKE",
                    target =
                            "Lnet/minecraft/world/item/ItemStack;"
                                    + "hurtAndBreak("
                                    + "I"
                                    + "Lnet/minecraft/world/entity/LivingEntity;"
                                    + "Lnet/minecraft/world/entity/EquipmentSlot;"
                                    + ")V"
            ),
            index = 0,
            require = 1
    )
    private int weaponsexpanded$axeHitDurabilityCost(
            int originalAmount
    ) {
        ItemStack stack =
                (ItemStack) (Object) this;

        if (WeaponsExpandedConfig
                .DISABLE_EXTRA_DURABILITY_DAMAGE_FOR_AXES
                .get()
                && (stack.is(ItemTags.AXES) || stack.getItem() instanceof BluntWeaponItem)) {
            return 1;
        }

        return originalAmount;
    }
}