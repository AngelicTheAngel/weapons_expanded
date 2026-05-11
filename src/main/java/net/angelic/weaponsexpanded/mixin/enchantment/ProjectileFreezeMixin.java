package net.angelic.weaponsexpanded.mixin.enchantment;

import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public class ProjectileFreezeMixin {

    @Unique
    private static final String WEAPONSEXPANDED$FREEZE_TAG_PREFIX = "weaponsexpanded.freeze.level.";

    @Unique
    private static final String WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG = "weaponsexpanded.freeze.applied_from_powder_snow";

    @Unique
    private static boolean weaponsexpanded$hasFreezeTag(AbstractArrow projectile) {
        for (String tag : projectile.getTags()) {
            if (tag.startsWith(WEAPONSEXPANDED$FREEZE_TAG_PREFIX)) return true;
        }
        return false;
    }

    @Unique
    private static void weaponsexpanded$applyFreezeTagOnce(AbstractArrow projectile, int level) {
        // Guard 1: don't stack multiple freeze tags (prevents weird multiple-consume behavior)
        if (weaponsexpanded$hasFreezeTag(projectile)) return;

        // Guard 2: don't apply repeatedly from powdered snow every tick
        if (projectile.getTags().contains(WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG)) return;

        int safeLevel = Math.max(1, level);
        projectile.addTag(WEAPONSEXPANDED$FREEZE_TAG_PREFIX + safeLevel);
        projectile.addTag(WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG);
    }

    @Inject(method = "onHitEntity", at = @At("TAIL"))
    private void applyFreezeOnHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        AbstractArrow projectile = (AbstractArrow) (Object) this;
        Entity target = entityHitResult.getEntity();

        if (target instanceof LivingEntity livingTarget && !projectile.level().isClientSide()) {
            for (String tag : projectile.getTags()) {
                if (tag.startsWith(WEAPONSEXPANDED$FREEZE_TAG_PREFIX)) {
                    try {
                        int level = Integer.parseInt(tag.substring(WEAPONSEXPANDED$FREEZE_TAG_PREFIX.length()));

                        int duration = 60 + (level * 40);
                        livingTarget.addEffect(new MobEffectInstance(ModEffects.FROSTBITE, duration, 0));

                        projectile.removeTag(tag);
                        projectile.removeTag(WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG);
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
        AbstractArrow projectile = (AbstractArrow) (Object) this;
        Level world = projectile.level();

        if (!world.isClientSide()) {
            ServerLevel serverWorld = (ServerLevel) world;

            for (String tag : projectile.getTags()) {
                if (tag.startsWith(WEAPONSEXPANDED$FREEZE_TAG_PREFIX)) {

                    serverWorld.sendParticles(
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
        AbstractArrow projectile = (AbstractArrow) (Object) this;
        Level world = projectile.level();

        if (!world.isClientSide() && projectile.isInWater()) {
            for (String tag : projectile.getTags()) {
                if (tag.startsWith(WEAPONSEXPANDED$FREEZE_TAG_PREFIX)) {
                    BlockPos pos = projectile.blockPosition();

                    if (world.getBlockState(pos).is(Blocks.WATER)) {
                        world.setBlockAndUpdate(pos, Blocks.FROSTED_ICE.defaultBlockState());
                        world.scheduleTick(pos, Blocks.FROSTED_ICE, net.minecraft.util.RandomSource.create().nextInt());
                        projectile.removeTag(tag);
                        projectile.removeTag(WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG);
                        projectile.discard();
                    }
                    break;
                }
            }
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void weaponsexpanded$applyFreezeWhenInPowderSnow(CallbackInfo ci) {
        AbstractArrow projectile = (AbstractArrow) (Object) this;
        Level world = projectile.level();

        if (world.isClientSide()) return;

        // Check the block(s) around the projectile; powder snow is a block, so this is reliable.
        BlockPos pos = projectile.blockPosition();
        boolean inPowderSnow =
                world.getBlockState(pos).is(Blocks.POWDER_SNOW)
                        || world.getBlockState(pos.above()).is(Blocks.POWDER_SNOW);

        if (!inPowderSnow) return;

        // Apply level 1 from powdered snow.
        weaponsexpanded$applyFreezeTagOnce(projectile, 1);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void weaponsexpanded$removeFreezeTagInHeat(CallbackInfo ci) {
        AbstractArrow projectile = (AbstractArrow) (Object) this;
        Level world = projectile.level();

        if (world.isClientSide()) return;

        boolean inHeat = projectile.isInLava() || projectile.isOnFire();
        if (!inHeat) return;

        for (String tag : projectile.getTags()) {
            if (tag.startsWith(WEAPONSEXPANDED$FREEZE_TAG_PREFIX)) {
                projectile.removeTag(tag);
                projectile.removeTag(WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG);
                break;
            }
        }
    }
}
