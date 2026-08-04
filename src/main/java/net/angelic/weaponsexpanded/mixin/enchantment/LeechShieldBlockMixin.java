package net.angelic.weaponsexpanded.mixin.enchantment;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LeechShieldBlockMixin {

    /*
     * Your original code used 0.5, meaning 50% extra durability damage,
     * even though its comment said 25%.
     *
     * Change this to 0.25F if 25% was the intended value.
     */
    @Unique
    private static final float WEAPONSEXPANDED$EXTRA_DURABILITY_MULTIPLIER =
            0.5F;

    @Unique
    private static final float WEAPONSEXPANDED$HEAL_MULTIPLIER =
            0.15F;

    @WrapOperation(
            method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;"
                            + "damageShield(F)V"
            ),
            require = 1
    )
    private void weaponsexpanded$applyLeechShieldEffects(
            LivingEntity defender,
            float blockedDamage,
            Operation<Void> original
    ) {
        ItemStack blockingItem = defender.getActiveItem();
        Hand activeHand = defender.getActiveHand();

        /*
         * Read the enchantment before calling vanilla because the shield
         * could break during the original durability operation.
         */
        int leechLevel = blockingItem.isEmpty()
                ? 0
                : EnchantmentHelper.getLevel(
                ModEnchantments.LEECH,
                blockingItem
        );

        int damageBefore =
                blockingItem.isDamageable()
                        ? blockingItem.getDamage()
                        : 0;

        /*
         * Run vanilla shield durability handling first.
         */
        original.call(defender, blockedDamage);

        if (leechLevel <= 0 || !blockingItem.isDamageable()) {
            return;
        }

        int damageAfter = blockingItem.getDamage();

        int vanillaDurabilityUsed = Math.max(
                0,
                damageAfter - damageBefore
        );

        if (vanillaDurabilityUsed <= 0) {
            return;
        }

        int extraDurabilityDamage = MathHelper.ceil(
                vanillaDurabilityUsed
                        * WEAPONSEXPANDED$EXTRA_DURABILITY_MULTIPLIER
        );

        if (extraDurabilityDamage > 0 && !blockingItem.isEmpty()) {
            blockingItem.damage(
                    extraDurabilityDamage,
                    defender,
                    entity -> entity.sendToolBreakStatus(activeHand)
            );
        }

        float healing = MathHelper.ceil(
                vanillaDurabilityUsed
                        * WEAPONSEXPANDED$HEAL_MULTIPLIER
        );

        if (healing > 0.0F) {
            defender.heal(healing);
        }
    }
}