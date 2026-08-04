package net.angelic.weaponsexpanded.mixin.heavy_arrow;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CrossbowItem.class)
public abstract class CrossbowHeavyArrowMultishotMixin {

    @Unique
    private static final String WEAPONSEXPANDED$SIDE_HEAVY_ARROW_TAG =
            "weaponsexpanded.side_heavy_arrow";

    @WrapOperation(
            method = "shoot(Lnet/minecraft/world/World;"
                    + "Lnet/minecraft/entity/LivingEntity;"
                    + "Lnet/minecraft/util/Hand;"
                    + "Lnet/minecraft/item/ItemStack;"
                    + "Lnet/minecraft/item/ItemStack;"
                    + "FZFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/CrossbowItem;"
                            + "createArrow(Lnet/minecraft/world/World;"
                            + "Lnet/minecraft/entity/LivingEntity;"
                            + "Lnet/minecraft/item/ItemStack;"
                            + "Lnet/minecraft/item/ItemStack;)"
                            + "Lnet/minecraft/entity/projectile/"
                            + "PersistentProjectileEntity;"
            ),
            require = 1
    )
    private static PersistentProjectileEntity weaponsexpanded$applyMultishotPickupRules(
            World world,
            LivingEntity shooter,
            ItemStack crossbow,
            ItemStack projectileStack,
            Operation<PersistentProjectileEntity> original,
            @Local(argsOnly = true, ordinal = 3) float simulatedYaw
    ) {
        PersistentProjectileEntity projectile =
                original.call(world, shooter, crossbow, projectileStack);

        // Zero is the center projectile; the side projectiles use ±10 degrees.
        if (simulatedYaw == 0.0F) {
            return projectile;
        }

        if (projectile instanceof HeavyArrowEntity) {
            projectile.addCommandTag(
                    WEAPONSEXPANDED$SIDE_HEAVY_ARROW_TAG
            );
        }

        if (shooter instanceof PlayerEntity player
                && !player.getAbilities().creativeMode) {
            projectile.pickupType =
                    PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
        }

        return projectile;
    }
}