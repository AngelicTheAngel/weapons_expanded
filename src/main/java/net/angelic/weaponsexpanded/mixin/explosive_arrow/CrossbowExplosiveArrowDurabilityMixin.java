package net.angelic.weaponsexpanded.mixin.explosive_arrow;

import com.llamalad7.mixinextras.sugar.Local;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(CrossbowItem.class)
public abstract class CrossbowExplosiveArrowDurabilityMixin {

    @Unique
    private static final int
            WEAPONSEXPANDED$DURABILITY_PER_EXPLOSIVE_ARROW =
            4;

    @ModifyArg(
            method =
                    "shootProjectile("
                            + "Lnet/minecraft/world/level/Level;"
                            + "Lnet/minecraft/world/entity/LivingEntity;"
                            + "Lnet/minecraft/world/InteractionHand;"
                            + "Lnet/minecraft/world/item/ItemStack;"
                            + "Lnet/minecraft/world/item/ItemStack;"
                            + "FZFFF)V",
            at = @At(
                    value = "INVOKE",
                    target =
                            "Lnet/minecraft/world/item/ItemStack;"
                                    + "hurtAndBreak("
                                    + "I"
                                    + "Lnet/minecraft/world/entity/LivingEntity;"
                                    + "Ljava/util/function/Consumer;"
                                    + ")V"
            ),
            index = 0,
            require = 1
    )
    private static int weaponsexpanded$getExplosiveArrowDurabilityCost(
            int vanillaCost,
            @Local(
                    argsOnly = true,
                    ordinal = 1
            )
            ItemStack projectile
    ) {
        if (projectile.is(
                ModItems.EXPLOSIVE_ARROW.get()
        )) {
            return WEAPONSEXPANDED$DURABILITY_PER_EXPLOSIVE_ARROW;
        }

        return vanillaCost;
    }
}