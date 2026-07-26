package net.angelic.weaponsexpanded.mixin.enchantment;

import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class FrostbiteCleanupMixin {

    @Unique
    private boolean weaponsexpanded$hadFrostbiteLastTick;

    @Unique
    private static boolean weaponsexpanded$isInPowderSnow(
            LivingEntity entity
    ) {
        BlockState state = entity.level()
                .getBlockState(entity.blockPosition());

        return state.is(Blocks.POWDER_SNOW);
    }

    @Unique
    private static boolean weaponsexpanded$shouldClearFrozenTicks(
            LivingEntity entity
    ) {
        return !weaponsexpanded$isInPowderSnow(entity);
    }

    @Inject(
            method = "removeEffect",
            at = @At("HEAD")
    )
    private void weaponsexpanded$onRemoveStatusEffect(
            Holder<MobEffect> effect,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (!effect.equals(ModEffects.FROSTBITE)) {
            return;
        }

        LivingEntity self = (LivingEntity) (Object) this;

        if (weaponsexpanded$shouldClearFrozenTicks(self)) {
            self.setTicksFrozen(0);
        }
    }

    @Inject(
            method = "tickEffects",
            at = @At("TAIL")
    )
    private void weaponsexpanded$afterTickStatusEffects(
            CallbackInfo ci
    ) {
        LivingEntity self = (LivingEntity) (Object) this;

        boolean hasFrostbiteNow =
                self.hasEffect(ModEffects.FROSTBITE);

        if (weaponsexpanded$hadFrostbiteLastTick
                && !hasFrostbiteNow
                && weaponsexpanded$shouldClearFrozenTicks(self)) {
            self.setTicksFrozen(0);
        }

        weaponsexpanded$hadFrostbiteLastTick = hasFrostbiteNow;
    }
}