package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class FrostbiteFireMixin {

    @Shadow public abstract boolean removeStatusEffect(RegistryEntry<StatusEffect> effect);

    @Inject(method = "damage", at = @At("HEAD"))
    private void onDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(source.isOf(DamageTypes.IN_FIRE) || source.isOf(DamageTypes.ON_FIRE)) {
            removeStatusEffect(ModEffects.FROSTBITE);
        }
    }
}
