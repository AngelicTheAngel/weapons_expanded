package net.angelic.weaponsexpanded.mixin.enchantment;

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
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public class ProjectileFreezeMixin {

    @Unique
    private static final String WEAPONSEXPANDED$FREEZE_TAG_PREFIX = "weaponsexpanded.freeze.level.";

    @Unique
    private static final String WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG = "weaponsexpanded.freeze.applied_from_powder_snow";

    @Unique
    private static boolean weaponsexpanded$hasFreezeTag(PersistentProjectileEntity projectile) {
        for (String tag : projectile.getCommandTags()) {
            if (tag.startsWith(WEAPONSEXPANDED$FREEZE_TAG_PREFIX)) return true;
        }
        return false;
    }

    @Unique
    private static void weaponsexpanded$applyFreezeTagOnce(PersistentProjectileEntity projectile, int level) {
        // Guard 1: don't stack multiple freeze tags (prevents weird multiple-consume behavior)
        if (weaponsexpanded$hasFreezeTag(projectile)) return;

        // Guard 2: don't apply repeatedly from powdered snow every tick
        if (projectile.getCommandTags().contains(WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG)) return;

        int safeLevel = Math.max(1, level);
        projectile.addCommandTag(WEAPONSEXPANDED$FREEZE_TAG_PREFIX + safeLevel);
        projectile.addCommandTag(WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG);
    }

    @Inject(method = "onEntityHit", at = @At("TAIL"))
    private void applyFreezeOnHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        Entity target = entityHitResult.getEntity();

        if (target instanceof LivingEntity livingTarget && !projectile.getEntityWorld().isClient()) {
            for (String tag : projectile.getCommandTags()) {
                if (tag.startsWith(WEAPONSEXPANDED$FREEZE_TAG_PREFIX)) {
                    try {
                        int level = Integer.parseInt(tag.substring(WEAPONSEXPANDED$FREEZE_TAG_PREFIX.length()));

                        int duration = 60 + (level * 40);
                        livingTarget.addStatusEffect(new StatusEffectInstance(ModEffects.FROSTBITE, duration, 0));

                        projectile.removeCommandTag(tag);
                        projectile.removeCommandTag(WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG);
                        break;

                    } catch (NumberFormatException ignored) {
                        // ignore malformed tags
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
                if (tag.startsWith(WEAPONSEXPANDED$FREEZE_TAG_PREFIX)) {

                    serverWorld.spawnParticles(
                            ParticleTypes.SNOWFLAKE,
                            projectile.getX(),
                            projectile.getY(),
                            projectile.getZ(),
                            1,
                            0.0,
                            0.0,
                            0.0,
                            0.0
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
                if (tag.startsWith(WEAPONSEXPANDED$FREEZE_TAG_PREFIX)) {
                    BlockPos pos = projectile.getBlockPos();

                    if (world.getBlockState(pos).isOf(Blocks.WATER)) {
                        world.setBlockState(pos, Blocks.FROSTED_ICE.getDefaultState());
                        world.scheduleBlockTick(pos, Blocks.FROSTED_ICE, net.minecraft.util.math.random.Random.create().nextInt());
                        projectile.removeCommandTag(tag);
                        projectile.removeCommandTag(WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG);
                        projectile.discard();
                    }
                    break;
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void weaponsexpanded$applyFreezeWhenInPowderSnow(CallbackInfo ci) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        World world = projectile.getEntityWorld();

        if (world.isClient()) return;

        // Check the block(s) around the projectile; powder snow is a block, so this is reliable.
        BlockPos pos = projectile.getBlockPos();
        boolean inPowderSnow =
                world.getBlockState(pos).isOf(Blocks.POWDER_SNOW)
                        || world.getBlockState(pos.up()).isOf(Blocks.POWDER_SNOW);

        if (!inPowderSnow) return;

        // Apply level 1 from powdered snow.
        weaponsexpanded$applyFreezeTagOnce(projectile, 1);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void weaponsexpanded$removeFreezeTagInHeat(CallbackInfo ci) {
        PersistentProjectileEntity projectile = (PersistentProjectileEntity) (Object) this;
        World world = projectile.getEntityWorld();

        if (world.isClient()) return;

        boolean inHeat = projectile.isInLava() || projectile.isOnFire();
        if (!inHeat) return;

        for (String tag : projectile.getCommandTags()) {
            if (tag.startsWith(WEAPONSEXPANDED$FREEZE_TAG_PREFIX)) {
                projectile.removeCommandTag(tag);
                projectile.removeCommandTag(WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG);
                break;
            }
        }
    }
}
