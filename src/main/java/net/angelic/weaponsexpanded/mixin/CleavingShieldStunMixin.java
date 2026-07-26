package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.enchantment.ModEnchantmentHelper;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
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
            method = "getSecondsToDisableBlocking()F",
            at = @At("RETURN"),
            cancellable = true
    )
    private void weaponsexpanded$cleavingIncreasesShieldStunTime(CallbackInfoReturnable<Float> cir) {
        LivingEntity attacker = (LivingEntity) (Object) this;
        ItemStack weaponStack = attacker.getWeaponItem();

        float baseStunSeconds = cir.getReturnValueF();
        if (baseStunSeconds <= 0.0F) return;

        int cleavingLevel = ModEnchantmentHelper.getLevel(
                attacker.level(),
                weaponStack,
                ModEnchantments.CLEAVING
        );

        if (cleavingLevel <= 0) return;

        cir.setReturnValue(baseStunSeconds + WEAPONSEXPANDED$CLEAVING_SECONDS_PER_LEVEL * cleavingLevel);
    }
}
