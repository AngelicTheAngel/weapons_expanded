package net.angelic.weaponsexpanded.mixin.explosive_arrow;

import com.llamalad7.mixinextras.sugar.Local;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CrossbowItem.class)
public abstract class CrossbowExplosiveArrowDurabilityMixin {

    @Unique
    private static final int WEAPONSEXPANDED$DURABILITY_PER_EXPLOSIVE_ARROW = 4;

    @ModifyArg(
            method = "shoot(Lnet/minecraft/world/World;"
                    + "Lnet/minecraft/entity/LivingEntity;"
                    + "Lnet/minecraft/util/Hand;"
                    + "Lnet/minecraft/item/ItemStack;"
                    + "Lnet/minecraft/item/ItemStack;"
                    + "FZFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;"
                            + "damage(ILnet/minecraft/entity/LivingEntity;"
                            + "Ljava/util/function/Consumer;)V"
            ),
            index = 0,
            require = 1
    )
    private static int weaponsexpanded$getExplosiveArrowDurabilityCost(
            int vanillaCost,
            @Local(argsOnly = true, ordinal = 1) ItemStack projectile
    ) {
        if (projectile.isOf(ModItems.EXPLOSIVE_ARROW)) {
            return WEAPONSEXPANDED$DURABILITY_PER_EXPLOSIVE_ARROW;
        }

        return vanillaCost;
    }
}