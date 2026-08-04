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
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PersistentProjectileEntity.class)
public abstract class ProjectileFreezeMixin {

    @Unique
    private static final String WEAPONSEXPANDED$FREEZE_TAG_PREFIX =
            "weaponsexpanded.freeze.level.";

    @Unique
    private static final String WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG =
            "weaponsexpanded.freeze.applied_from_powder_snow";

    @Unique
    @Nullable
    private static String weaponsexpanded$findFreezeTag(
            PersistentProjectileEntity projectile
    ) {
        for (String tag : projectile.getCommandTags()) {
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
            PersistentProjectileEntity projectile
    ) {
        return weaponsexpanded$findFreezeTag(projectile) != null;
    }

    @Unique
    private static void weaponsexpanded$removeFreezeTags(
            PersistentProjectileEntity projectile
    ) {
        String freezeTag =
                weaponsexpanded$findFreezeTag(projectile);

        if (freezeTag != null) {
            projectile.removeScoreboardTag(freezeTag);
        }

        projectile.removeScoreboardTag(
                WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG
        );
    }

    @Unique
    private static void weaponsexpanded$applyFreezeTagOnce(
            PersistentProjectileEntity projectile,
            int level
    ) {
        if (weaponsexpanded$hasFreezeTag(projectile)) {
            return;
        }

        if (projectile.getCommandTags().contains(
                WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG
        )) {
            return;
        }

        int safeLevel = Math.max(1, level);

        projectile.addCommandTag(
                WEAPONSEXPANDED$FREEZE_TAG_PREFIX + safeLevel
        );

        projectile.addCommandTag(
                WEAPONSEXPANDED$POWDER_SNOW_APPLIED_TAG
        );
    }

    @Inject(
            method = "onEntityHit",
            at = @At("TAIL")
    )
    private void weaponsexpanded$applyFreezeOnHit(
            EntityHitResult hitResult,
            CallbackInfo ci
    ) {
        PersistentProjectileEntity projectile =
                (PersistentProjectileEntity) (Object) this;

        if (!(projectile.getEntityWorld()
                instanceof ServerWorld)) {
            return;
        }

        Entity target = hitResult.getEntity();

        if (!(target instanceof LivingEntity livingTarget)) {
            return;
        }

        String freezeTag =
                weaponsexpanded$findFreezeTag(projectile);

        if (freezeTag == null) {
            return;
        }

        try {
            String levelText = freezeTag.substring(
                    WEAPONSEXPANDED$FREEZE_TAG_PREFIX.length()
            );

            int level = Math.max(
                    1,
                    Integer.parseInt(levelText)
            );

            int duration = 60 + level * 40;

            livingTarget.addStatusEffect(
                    new StatusEffectInstance(
                            ModEffects.FROSTBITE.value(),
                            duration,
                            0
                    )
            );

            weaponsexpanded$removeFreezeTags(projectile);
        } catch (NumberFormatException ignored) {
            // Ignore malformed command tags.
        }
    }

    /*
     * All the former tick injectors are combined into one operation.
     * This avoids ordering problems between heat removal, powder snow,
     * water freezing, and particles.
     */
    @Inject(
            method = "tick",
            at = @At("TAIL")
    )
    private void weaponsexpanded$tickFreezeEffects(
            CallbackInfo ci
    ) {
        PersistentProjectileEntity projectile =
                (PersistentProjectileEntity) (Object) this;

        if (!(projectile.getEntityWorld()
                instanceof ServerWorld serverWorld)) {
            return;
        }

        /*
         * Heat takes priority and removes Freeze immediately.
         */
        if (projectile.isInLava() || projectile.isOnFire()) {
            weaponsexpanded$removeFreezeTags(projectile);
            return;
        }

        BlockPos projectilePos = projectile.getBlockPos();

        /*
         * Powder snow applies level-one Freeze.
         */
        boolean inPowderSnow =
                serverWorld.getBlockState(projectilePos)
                        .isOf(Blocks.POWDER_SNOW)
                        || serverWorld.getBlockState(
                        projectilePos.up()
                ).isOf(Blocks.POWDER_SNOW);

        if (inPowderSnow) {
            weaponsexpanded$applyFreezeTagOnce(
                    projectile,
                    1
            );
        }

        String freezeTag =
                weaponsexpanded$findFreezeTag(projectile);

        if (freezeTag == null) {
            return;
        }

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

        /*
         * Convert water touched by a frozen projectile into frosted ice.
         */
        if (!projectile.isTouchingWater()) {
            return;
        }

        if (!serverWorld.getBlockState(projectilePos)
                .isOf(Blocks.WATER)) {
            return;
        }

        serverWorld.setBlockState(
                projectilePos,
                Blocks.FROSTED_ICE.getDefaultState()
        );

        /*
         * Use a valid positive delay rather than an unbounded random int,
         * which could produce a negative tick delay.
         */
        int meltDelay =
                60 + serverWorld.getRandom().nextInt(60);

        serverWorld.scheduleBlockTick(
                projectilePos,
                Blocks.FROSTED_ICE,
                meltDelay
        );

        weaponsexpanded$removeFreezeTags(projectile);
        projectile.discard();
    }
}