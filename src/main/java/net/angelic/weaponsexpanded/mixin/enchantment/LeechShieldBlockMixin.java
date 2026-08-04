package net.angelic.weaponsexpanded.mixin.enchantment;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LeechShieldBlockMixin {

    // 50% extra durability damage.
    @Unique
    private static final float
            WEAPONSEXPANDED$EXTRA_DURABILITY_MULTIPLIER =
            0.5F;

    @Unique
    private static final float
            WEAPONSEXPANDED$HEAL_MULTIPLIER =
            0.15F;

    @WrapOperation(
            method = "hurt",
            at = @At(
                    value = "INVOKE",
                    target =
                            "Lnet/minecraft/world/entity/"
                                    + "LivingEntity;"
                                    + "hurtCurrentlyUsedShield(F)V"
            )
    )
    private void weaponsexpanded$applyLeechShieldEffects(
            LivingEntity defender,
            float blockedDamage,
            Operation<Void> original
    ) {
        ItemStack blockingItem =
                defender.getUseItem();

        InteractionHand activeHand =
                defender.getUsedItemHand();

        /*
         * Read the enchantment before vanilla damages the shield,
         * since the shield could break during that operation.
         */
        int leechLevel =
                blockingItem.isEmpty()
                        ? 0
                        : EnchantmentHelper.getTagEnchantmentLevel(
                        ModEnchantments.LEECH.get(),
                        blockingItem
                );

        int damageBefore =
                blockingItem.isDamageableItem()
                        ? blockingItem.getDamageValue()
                        : 0;

        /*
         * Continue the operation chain.
         *
         * This runs any compatible wrappers installed by other
         * mods before eventually invoking vanilla's method.
         */
        original.call(
                defender,
                blockedDamage
        );

        if (leechLevel <= 0
                || !blockingItem.isDamageableItem()) {
            return;
        }

        int damageAfter =
                blockingItem.getDamageValue();

        int vanillaDurabilityUsed =
                Math.max(
                        0,
                        damageAfter - damageBefore
                );

        if (vanillaDurabilityUsed <= 0) {
            return;
        }

        int extraDurabilityDamage =
                Mth.ceil(
                        vanillaDurabilityUsed
                                * WEAPONSEXPANDED$EXTRA_DURABILITY_MULTIPLIER
                );

        if (extraDurabilityDamage > 0
                && !blockingItem.isEmpty()) {
            blockingItem.hurtAndBreak(
                    extraDurabilityDamage,
                    defender,
                    entity ->
                            entity.broadcastBreakEvent(
                                    activeHand
                            )
            );
        }

        float healing =
                Mth.ceil(
                        vanillaDurabilityUsed
                                * WEAPONSEXPANDED$HEAL_MULTIPLIER
                );

        if (healing > 0.0F) {
            defender.heal(healing);
        }
    }
}