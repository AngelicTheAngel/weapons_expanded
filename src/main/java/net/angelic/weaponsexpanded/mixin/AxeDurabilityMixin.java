package net.angelic.weaponsexpanded.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MiningToolItem;
import net.minecraft.registry.tag.ItemTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MiningToolItem.class)
public abstract class AxeDurabilityMixin {

    @ModifyArg(
            method = "postHit(Lnet/minecraft/item/ItemStack;"
                    + "Lnet/minecraft/entity/LivingEntity;"
                    + "Lnet/minecraft/entity/LivingEntity;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;"
                            + "damage(ILnet/minecraft/entity/LivingEntity;"
                            + "Ljava/util/function/Consumer;)V"
            ),
            index = 0,
            require = 1
    )
    private int weaponsexpanded$axeHitDurabilityCost(
            int originalAmount,
            @Local(argsOnly = true) ItemStack stack
    ) {
        if (WeaponsExpandedConfig.get().disableExtraDurabilityDamageForAxes
                && stack.isIn(ItemTags.AXES)) {
            return 1;
        }

        return originalAmount;
    }
}