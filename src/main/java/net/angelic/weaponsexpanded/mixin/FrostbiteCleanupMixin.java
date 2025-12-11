package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
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
    private static boolean weaponsexpanded$isInPowderSnow(LivingEntity entity) {
        BlockState state = entity.getEntityWorld().getBlockState(entity.getBlockPos());
        return state.isOf(Blocks.POWDER_SNOW);
    }

    @Unique
    private static boolean weaponsexpanded$shouldClearFrozenTicks(LivingEntity entity) {
        return !weaponsexpanded$isInPowderSnow(entity);
    }

    @Inject(method = "removeStatusEffect", at = @At("HEAD"))
    private void weaponsexpanded$onRemoveStatusEffect(RegistryEntry<StatusEffect> effect, CallbackInfoReturnable<Boolean> cir) {
        if (effect == ModEffects.FROSTBITE) {
            LivingEntity self = (LivingEntity) (Object) this;
            if (weaponsexpanded$shouldClearFrozenTicks(self)) {
                self.setFrozenTicks(0);
            }
        }
    }

    @Inject(method = "tickStatusEffects", at = @At("TAIL"))
    private void weaponsexpanded$afterTickStatusEffects(CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;

        boolean hasFrostbiteNow = self.hasStatusEffect(ModEffects.FROSTBITE);

        if (weaponsexpanded$hadFrostbiteLastTick && !hasFrostbiteNow) {
            if (weaponsexpanded$shouldClearFrozenTicks(self)) {
                self.setFrozenTicks(0);
            }
        }

        weaponsexpanded$hadFrostbiteLastTick = hasFrostbiteNow;
    }
}
