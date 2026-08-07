package net.angelic.weaponsexpanded.mixin.explosive_arrow;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.RangedWeaponItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(RangedWeaponItem.class)
public abstract class CrossbowExplosiveArrowDurabilityMixin {

    @Unique
    private static final int WEAPONSEXPANDED$DURABILITY_PER_EXPLOSIVE_ARROW = 4;

    @WrapOperation(
            method = "shootAll(Lnet/minecraft/server/world/ServerWorld;"
                    + "Lnet/minecraft/entity/LivingEntity;"
                    + "Lnet/minecraft/util/Hand;"
                    + "Lnet/minecraft/item/ItemStack;"
                    + "Ljava/util/List;FFZ"
                    + "Lnet/minecraft/entity/LivingEntity;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/RangedWeaponItem;"
                            + "getWeaponStackDamage(Lnet/minecraft/item/ItemStack;)I"
            ),
            require = 1
    )
    private int weaponsexpanded$getExplosiveArrowDurabilityCost(
            RangedWeaponItem weapon,
            ItemStack projectile,
            Operation<Integer> original
    ) {
        int vanillaCost = original.call(weapon, projectile);

        if (weapon instanceof CrossbowItem
                && projectile.isOf(ModItems.EXPLOSIVE_ARROW)) {
            return WEAPONSEXPANDED$DURABILITY_PER_EXPLOSIVE_ARROW;
        }

        return vanillaCost;
    }
}