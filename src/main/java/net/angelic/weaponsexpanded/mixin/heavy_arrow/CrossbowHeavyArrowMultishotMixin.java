package net.angelic.weaponsexpanded.mixin.heavy_arrow;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CrossbowItem.class)
public abstract class CrossbowHeavyArrowMultishotMixin {

    @Unique
    private static final String
            WEAPONSEXPANDED$SIDE_HEAVY_ARROW_TAG =
            "weaponsexpanded.side_heavy_arrow";

    @WrapOperation(
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
                            "Lnet/minecraft/world/item/CrossbowItem;"
                                    + "getArrow("
                                    + "Lnet/minecraft/world/level/Level;"
                                    + "Lnet/minecraft/world/entity/LivingEntity;"
                                    + "Lnet/minecraft/world/item/ItemStack;"
                                    + "Lnet/minecraft/world/item/ItemStack;"
                                    + ")"
                                    + "Lnet/minecraft/world/entity/projectile/"
                                    + "AbstractArrow;"
            ),
            require = 1
    )
    private static AbstractArrow
    weaponsexpanded$applyMultishotPickupRules(
            Level level,
            LivingEntity shooter,
            ItemStack crossbow,
            ItemStack projectileStack,
            Operation<AbstractArrow> original,
            @Local(
                    argsOnly = true,
                    ordinal = 3
            )
            float simulatedYaw
    ) {
        AbstractArrow projectile =
                original.call(
                        level,
                        shooter,
                        crossbow,
                        projectileStack
                );

        /*
         * Zero is the center projectile. Multishot side
         * projectiles use -10 or +10 degrees.
         */
        if (simulatedYaw == 0.0F) {
            return projectile;
        }

        if (projectile instanceof HeavyArrowEntity) {
            projectile.addTag(
                    WEAPONSEXPANDED$SIDE_HEAVY_ARROW_TAG
            );
        }

        return projectile;
    }
}