package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.Config;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemStack.class)
public class AxeDurabilityMixin {

    @Redirect(
            method = "postHurtEnemy(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/LivingEntity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V"
            )
    )
    private void weaponsexpanded$axeHitDurabilityCost(ItemStack stack, int amount, LivingEntity entity, EquipmentSlot slot) {
        if (Config.axeDamage && (stack.is(ItemTags.AXES))) {
            amount = 1;
        }

        stack.hurtAndBreak(amount, entity, slot);
    }
}
