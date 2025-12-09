package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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

    @Inject(method = "tick", at = @At("TAIL"))
    private void spawnFreezeParticles(CallbackInfo ci) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        World world = projectile.getEntityWorld();

        if (!world.isClient()) {
            ServerWorld serverWorld = (ServerWorld) world;

            for (String tag : projectile.getCommandTags()) {
                if (tag.startsWith("weaponsexpanded.freeze.level.")) {

                    serverWorld.spawnParticles(
                            ParticleTypes.SNOWFLAKE,
                            projectile.getX(),
                            projectile.getY(),
                            projectile.getZ(),
                            1,      // count
                            0.0,    // offsetX range
                            0.0,    // offsetY range
                            0.0,    // offsetZ range
                            0.0     // speed
                    );

                    break;
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void freezeWaterInteraction(CallbackInfo ci) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        World world = projectile.getEntityWorld();

        // Check if server-side and touching water
        if (!world.isClient() && projectile.isTouchingWater()) {
            for (String tag : projectile.getCommandTags()) {
                // Check for the specific freeze enchantment tag
                if (tag.startsWith("weaponsexpanded.freeze.level.")) {
                    BlockPos pos = projectile.getBlockPos();

                    // Turn water block to frosted ice
                    if (world.getBlockState(pos).isOf(Blocks.WATER)) {
                        world.setBlockState(pos, Blocks.FROSTED_ICE.getDefaultState());
                        world.scheduleBlockTick(pos, Blocks.FROSTED_ICE, net.minecraft.util.math.random.Random.create().nextInt());
                        projectile.removeCommandTag(tag);
                    }
                    break;
                }
            }
        }
    }
}
