package net.angelic.weaponsexpanded.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemStack.class)
public class AxeDurabilityMixin {

    @WrapOperation(
            method = "postHurtEnemy(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/LivingEntity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V"
            )
    )
    private void weaponsexpanded$axeHitDurabilityCost(
            ItemStack stack,
            int amount,
            LivingEntity owner,
            EquipmentSlot slot,
            Operation<Void> original
    ) {
        // For axes (vanilla + custom items in the AXES tag), force 1 durability per hit.
        if (WeaponsExpandedConfig.get().disableExtraDurabilityDamageForAxes) {
            if (stack.is(ItemTags.AXES)) {
                amount = 1;
            }
        }

        original.call(stack, amount, owner, slot);
    }
}