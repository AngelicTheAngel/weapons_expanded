package net.angelic.weaponsexpanded.mixin.heavy_arrow;

import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.CrossbowItem;
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

    @Inject(method = "shootProjectile", at = @At("HEAD"))
    private void weaponsexpanded$multishotPickupRules(LivingEntity shooter, Projectile projectile, int index, float speed, float divergence, float yaw, @Nullable LivingEntity target, CallbackInfo ci) {

        if (shooter.level().isClientSide()) return;

        // index 0 is the center projectile; side projectiles are 1 and 2
        if (index == 0) return;

        if (!(projectile instanceof AbstractArrow persistentProjectile)) return;

        // Mark side heavy arrows so they can vanish on pickup without giving an item
        if (projectile instanceof HeavyArrowEntity) {
            persistentProjectile.addTag(WEAPONSEXPANDED$SIDE_HEAVY_ARROW_TAG);
        }

        // Only apply to non-creative player shots (matches vanilla intent)
        if (shooter instanceof Player player && !player.getAbilities().instabuild) {
            persistentProjectile.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
        }
    }
}
