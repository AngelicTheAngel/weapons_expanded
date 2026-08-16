package net.angelic.weaponsexpanded.mixin.enchantment;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class CleavingShieldStunMixin {

    /*
     * The old value was 20 ticks, which equals one second.
     * getSecondsToDisableBlocking() uses seconds.
     */
    private static final float
            WEAPONSEXPANDED$CLEAVING_SECONDS_PER_LEVEL =
            1.0F;

    @ModifyReturnValue(
            method = "getSecondsToDisableBlocking()F",
            at = @At("RETURN")
    )
    private float weaponsexpanded$increaseBlockingDisableTime(
            float baseDisableSeconds
    ) {
        /*
         * Preserve the old behavior: Cleaving only extends an existing
         * blocking-disable effect. It does not make every weapon capable
         * of disabling blocking.
         */
        if (baseDisableSeconds <= 0.0F) {
            return baseDisableSeconds;
        }

        LivingEntity attacker =
                (LivingEntity) (Object) this;

        ItemStack weaponStack =
                attacker.getWeaponItem();

        if (weaponStack.isEmpty()) {
            return baseDisableSeconds;
        }

        Holder<Enchantment> cleaving =
                attacker.level()
                        .registryAccess()
                        .lookupOrThrow(Registries.ENCHANTMENT)
                        .getOrThrow(ModEnchantments.CLEAVING);

        int cleavingLevel =
                weaponStack.getEnchantmentLevel(cleaving);

        if (cleavingLevel <= 0) {
            return baseDisableSeconds;
        }

        return baseDisableSeconds
                + WEAPONSEXPANDED$CLEAVING_SECONDS_PER_LEVEL
                * cleavingLevel;
    }
}