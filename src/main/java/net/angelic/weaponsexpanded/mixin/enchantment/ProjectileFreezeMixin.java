package net.angelic.weaponsexpanded.mixin.enchantment;

import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractArrow.class)
public abstract class ProjectileFreezeMixin {

    @Unique
    private static final String
            WEAPONSEXPANDED$FREEZE_TAG_PREFIX =
            "weaponsexpanded.freeze.level.";

    @Unique
    private static final String
            WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG =
            "weaponsexpanded.freeze.applied_from_powder_snow";

    @Unique
    @Nullable
    private static String weaponsexpanded$findFreezeTag(
            AbstractArrow projectile
    ) {
        for (String tag : projectile.getTags()) {
            if (tag.startsWith(
                    WEAPONSEXPANDED$FREEZE_TAG_PREFIX
            )) {
                return tag;
            }
        }

        return null;
    }

    @Unique
    private static boolean weaponsexpanded$hasFreezeTag(
            AbstractArrow projectile
    ) {
        return weaponsexpanded$findFreezeTag(
                projectile
        ) != null;
    }

    @Unique
    private static void weaponsexpanded$removeFreezeTags(
            AbstractArrow projectile
    ) {
        String freezeTag =
                weaponsexpanded$findFreezeTag(
                        projectile
                );

        if (freezeTag != null) {
            projectile.removeTag(freezeTag);
        }

        projectile.removeTag(
                WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG
        );
    }

    @Unique
    private static void weaponsexpanded$applyFreezeTagOnce(
            AbstractArrow projectile,
            int level
    ) {
        if (weaponsexpanded$hasFreezeTag(
                projectile
        )) {
            return;
        }

        if (projectile.getTags().contains(
                WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG
        )) {
            return;
        }

        int safeLevel = Math.max(1, level);

        projectile.addTag(
                WEAPONSEXPANDED$FREEZE_TAG_PREFIX
                        + safeLevel
        );

        projectile.addTag(
                WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG
        );
    }

    @Inject(
            method = "onHitEntity",
            at = @At("TAIL")
    )
    private void weaponsexpanded$applyFreezeOnHit(
            EntityHitResult hitResult,
            CallbackInfo ci
    ) {
        AbstractArrow projectile =
                (AbstractArrow) (Object) this;

        if (!(projectile.level()
                instanceof ServerLevel)) {
            return;
        }

        Entity target =
                hitResult.getEntity();

        if (!(target
                instanceof LivingEntity livingTarget)) {
            return;
        }

        String freezeTag =
                weaponsexpanded$findFreezeTag(
                        projectile
                );

        if (freezeTag == null) {
            return;
        }

        try {
            String levelText =
                    freezeTag.substring(
                            WEAPONSEXPANDED$FREEZE_TAG_PREFIX
                                    .length()
                    );

            int level = Math.max(
                    1,
                    Integer.parseInt(levelText)
            );

            int duration =
                    60 + level * 40;

            livingTarget.addEffect(
                    new MobEffectInstance(
                            ModEffects.FROSTBITE.get(),
                            duration,
                            0
                    )
            );

            weaponsexpanded$removeFreezeTags(
                    projectile
            );
        } catch (NumberFormatException ignored) {
            /*
             * Remove malformed Freeze tags so they do not remain
             * attached to the projectile indefinitely.
             */
            weaponsexpanded$removeFreezeTags(
                    projectile
            );
        }
    }

    /*
     * Heat removal, powder snow, particles, and water freezing
     * are combined into one tick operation to avoid ordering
     * problems.
     */
    @Inject(
            method = "tick",
            at = @At("TAIL")
    )
    private void weaponsexpanded$tickFreezeEffects(
            CallbackInfo ci
    ) {
        AbstractArrow projectile =
                (AbstractArrow) (Object) this;

        if (!(projectile.level()
                instanceof ServerLevel serverLevel)) {
            return;
        }

        /*
         * Heat takes priority and removes Freeze immediately.
         */
        if (projectile.isInLava()
                || projectile.isOnFire()) {
            weaponsexpanded$removeFreezeTags(
                    projectile
            );

            return;
        }

        BlockPos projectilePos =
                projectile.blockPosition();

        /*
         * Powder snow applies level-one Freeze.
         */
        boolean inPowderSnow =
                serverLevel
                        .getBlockState(projectilePos)
                        .is(Blocks.POWDER_SNOW)
                        || serverLevel
                        .getBlockState(
                                projectilePos.above()
                        )
                        .is(Blocks.POWDER_SNOW);

        if (inPowderSnow) {
            weaponsexpanded$applyFreezeTagOnce(
                    projectile,
                    1
            );
        }

        String freezeTag =
                weaponsexpanded$findFreezeTag(
                        projectile
                );

        if (freezeTag == null) {
            return;
        }

        serverLevel.sendParticles(
                ParticleTypes.SNOWFLAKE,
                projectile.getX(),
                projectile.getY(),
                projectile.getZ(),
                1,
                0.0D,
                0.0D,
                0.0D,
                0.0D
        );

        /*
         * Convert water touched by a frozen projectile into
         * frosted ice.
         */
        if (!projectile.isInWater()) {
            return;
        }

        if (!serverLevel
                .getBlockState(projectilePos)
                .is(Blocks.WATER)) {
            return;
        }

        serverLevel.setBlockAndUpdate(
                projectilePos,
                Blocks.FROSTED_ICE
                        .defaultBlockState()
        );

        /*
         * Use a valid positive delay instead of an unbounded random
         * integer, which could produce a negative tick delay.
         */
        int meltDelay =
                60 + serverLevel
                        .getRandom()
                        .nextInt(60);

        serverLevel.scheduleTick(
                projectilePos,
                Blocks.FROSTED_ICE,
                meltDelay
        );

        weaponsexpanded$removeFreezeTags(
                projectile
        );

        projectile.discard();
    }
}