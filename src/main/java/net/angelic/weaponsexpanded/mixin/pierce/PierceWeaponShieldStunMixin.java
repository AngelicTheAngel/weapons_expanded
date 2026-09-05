package net.angelic.weaponsexpanded.mixin.pierce;

import net.angelic.weaponsexpanded.util.tags.ModItemTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class PierceWeaponShieldStunMixin {

    @Inject(
            method = "getSecondsToDisableBlocking()F",
            at = @At("RETURN"),
            cancellable = true
    )
    private void pierceWeaponShieldStunChance(CallbackInfoReturnable<Float> cir) {
        LivingEntity attacker = (LivingEntity) (Object) this;
        ItemStack weapon = attacker.getWeaponItem();
        Random random = new Random();

        if (weapon.is(ModItemTags.PIERCE) && random.nextInt(3) != 0) {
            cir.setReturnValue(0.0F);
        }
    }
}