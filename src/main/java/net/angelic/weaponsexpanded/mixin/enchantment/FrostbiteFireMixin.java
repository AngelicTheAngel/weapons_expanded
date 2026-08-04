package net.angelic.weaponsexpanded.mixin.enchantment;

import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class FrostbiteFireMixin {

    @Inject(
            method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z",
            at = @At("HEAD")
    )
    private void weaponsexpanded$removeFrostbiteOnFireDamage(
            DamageSource source,
            float amount,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (!source.isIn(DamageTypeTags.IS_FIRE)) {
            return;
        }

        LivingEntity entity = (LivingEntity) (Object) this;
        entity.removeStatusEffect(ModEffects.FROSTBITE.value());
    }
}