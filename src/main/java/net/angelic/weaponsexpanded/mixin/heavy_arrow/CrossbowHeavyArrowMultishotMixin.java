package net.angelic.weaponsexpanded.mixin.heavy_arrow;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CrossbowItem.class)
public class CrossbowHeavyArrowMultishotMixin {

    @Unique
    private static final String WEAPONSEXPANDED$SIDE_HEAVY_ARROW_TAG = "weaponsexpanded.side_heavy_arrow";

    @Inject(method = "shoot", at = @At("HEAD"))
    private void weaponsexpanded$multishotPickupRules(LivingEntity shooter, ProjectileEntity projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target, CallbackInfo ci) {

        if (shooter.getEntityWorld().isClient()) return;

        // index 0 is the center projectile; side projectiles are 1 and 2
        if (index == 0) return;

        if (!(projectile instanceof PersistentProjectileEntity persistentProjectile)) return;

        // Mark side heavy arrows so they can vanish on pickup without giving an item
        if (projectile instanceof HeavyArrowEntity) {
            persistentProjectile.addCommandTag(WEAPONSEXPANDED$SIDE_HEAVY_ARROW_TAG);
        }

        // Only apply to non-creative player shots (matches vanilla intent)
        if (shooter instanceof PlayerEntity player && !player.getAbilities().creativeMode) {
            persistentProjectile.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
        }
    }
}
