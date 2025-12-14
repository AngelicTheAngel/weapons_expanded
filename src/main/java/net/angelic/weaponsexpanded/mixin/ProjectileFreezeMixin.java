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

        if (target instanceof LivingEntity livingTarget && !projectile.getEntityWorld().isClient()) {
            for (String tag : projectile.getCommandTags()) {
                if (tag.startsWith("weaponsexpanded.freeze.level.")) {
                    try {
                        int level = Integer.parseInt(tag.substring("weaponsexpanded.freeze.level.".length()));

                        // Apply the effect (Duration: 100 ticks base, scaling logic can be added here)
                        int duration = 60 + (level * 40);
                        livingTarget.addStatusEffect(new StatusEffectInstance(ModEffects.FROSTBITE, duration, 0));

                        projectile.removeCommandTag(tag);
                        break;

                    } catch (NumberFormatException ignored) {

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
    private void freezeFreezeWater(CallbackInfo ci) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        World world = projectile.getEntityWorld();

        if (!world.isClient() && projectile.isTouchingWater()) {
            for (String tag : projectile.getCommandTags()) {
                if (tag.startsWith("weaponsexpanded.freeze.level.")) {
                    BlockPos pos = projectile.getBlockPos();

                    if (world.getBlockState(pos).isOf(Blocks.WATER)) {
                        world.setBlockState(pos, Blocks.FROSTED_ICE.getDefaultState());
                        world.scheduleBlockTick(pos, Blocks.FROSTED_ICE, net.minecraft.util.math.random.Random.create().nextInt());
                        projectile.removeCommandTag(tag);
                        projectile.discard();
                    }
                    break;
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void weaponsexpanded$removeFreezeTagInHeat(CallbackInfo ci) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        World world = projectile.getEntityWorld();

        if (world.isClient()) return;

        boolean inHeat = projectile.isInLava() || projectile.isOnFire();
        if (!inHeat) return;

        for (String tag : projectile.getCommandTags()) {
            if (tag.startsWith("weaponsexpanded.freeze.level.")) {
                projectile.removeCommandTag(tag);
                break;
            }
        }
    }
}
