package net.angelic.weaponsexpanded.mixin.enchantment;

import net.angelic.weaponsexpanded.enchantment.ModEnchantmentHelper;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class CleavingShieldStunMixin {

    @Unique
    private static final float WEAPONSEXPANDED$CLEAVING_SECONDS_PER_LEVEL = 1.0F;

    @Inject(
            method = "getWeaponDisableBlockingForSeconds()F",
            at = @At("RETURN"),
            cancellable = true
    )
    private void weaponsexpanded$cleavingIncreasesShieldStunTime(
            CallbackInfoReturnable<Float> cir
    ) {
        LivingEntity attacker = (LivingEntity) (Object) this;
        ItemStack weaponStack = attacker.getWeaponStack();

        float baseStunSeconds = cir.getReturnValueF();

        // Only increase duration for weapons/modes that already stun shields.
        if (baseStunSeconds <= 0.0F) {
            return;
        }

        int cleavingLevel = ModEnchantmentHelper.getLevel(
                attacker.getEntityWorld(),
                weaponStack,
                ModEnchantments.CLEAVING
        );

        if (cleavingLevel <= 0) {
            return;
        }

        float extraSeconds = WEAPONSEXPANDED$CLEAVING_SECONDS_PER_LEVEL * cleavingLevel;

        cir.setReturnValue(baseStunSeconds + extraSeconds);
    }
}