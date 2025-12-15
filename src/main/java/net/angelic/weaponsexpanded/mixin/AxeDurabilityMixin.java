package net.angelic.weaponsexpanded.mixin;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.ItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemStack.class)
public class AxeDurabilityMixin {

    @Redirect(
            method = "postDamageEntity(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/LivingEntity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;damage(ILnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/EquipmentSlot;)V"
            )
    )
    private void weaponsexpanded$axeHitDurabilityCost(ItemStack stack, int amount, LivingEntity entity, EquipmentSlot slot) {
        // For axes (vanilla + your custom items in the AXES tag), force 1 durability per hit.
        if (stack.isIn(ItemTags.AXES)) {
            amount = 1;
        }

        stack.damage(amount, entity, slot);
    }
}
