package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public class ProjectileFreezeMixin {

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void applyFreezeOnHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        Entity target = entityHitResult.getEntity();

        // Check if the target is alive and the projectile has our tag
        if (target instanceof LivingEntity livingTarget && !projectile.getEntityWorld().isClient()) {
            for (String tag : projectile.getCommandTags()) {
                if (tag.startsWith("weaponsexpanded.freeze.level.")) {
                    try {
                        // Extract level from tag
                        int level = Integer.parseInt(tag.substring("weaponsexpanded.freeze.level.".length()));

                        // Apply the effect (Duration: 100 ticks base, scaling logic can be added here)
                        int duration = 60 + (level * 40);
                        livingTarget.addStatusEffect(new StatusEffectInstance(ModEffects.FROSTBITE, duration, 0));

                        // Remove tag to prevent re-application
                        projectile.removeCommandTag(tag);
                        break;
                    } catch (NumberFormatException ignored) {
                        // Handle malformed tags if necessary
                    }
                }
            }
        }
    }
}
