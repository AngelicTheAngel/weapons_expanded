package net.angelic.weaponsexpanded.mixin.heavy_arrow;

import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.CrossbowItem;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CrossbowItem.class)
public abstract class CrossbowHeavyArrowMultishotMixin {

    @Unique
    private static final String
            WEAPONSEXPANDED$SIDE_HEAVY_ARROW_TAG =
            "weaponsexpanded.side_heavy_arrow";

    @Inject(
            method =
                    "shootProjectile("
                            + "Lnet/minecraft/world/entity/LivingEntity;"
                            + "Lnet/minecraft/world/entity/projectile/Projectile;"
                            + "I"
                            + "F"
                            + "F"
                            + "F"
                            + "Lnet/minecraft/world/entity/LivingEntity;"
                            + ")V",
            at = @At("HEAD"),
            require = 1
    )
    private void weaponsexpanded$applyMultishotPickupRules(
            LivingEntity shooter,
            Projectile projectile,
            int projectileIndex,
            float velocity,
            float inaccuracy,
            float angle,
            @Nullable LivingEntity target,
            CallbackInfo ci
    ) {
        /*
         * Zero degrees is the center projectile.
         * Multishot side projectiles use nonzero angles.
         */
        if (angle == 0.0F) {
            return;
        }

        if (projectile instanceof HeavyArrowEntity) {
            projectile.addTag(
                    WEAPONSEXPANDED$SIDE_HEAVY_ARROW_TAG
            );
        }
    }
}