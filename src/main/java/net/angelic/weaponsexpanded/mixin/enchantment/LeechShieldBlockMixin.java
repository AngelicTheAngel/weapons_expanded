package net.angelic.weaponsexpanded.mixin.enchantment;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
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

        EquipmentSlot activeSlot =
                activeHand == InteractionHand.MAIN_HAND
                        ? EquipmentSlot.MAINHAND
                        : EquipmentSlot.OFFHAND;

        /*
         * ResourceKey<Enchantment> must be resolved through the
         * current world's dynamic enchantment registry.
         */
        Holder<Enchantment> leech =
                defender.level()
                        .registryAccess()
                        .lookupOrThrow(
                                Registries.ENCHANTMENT
                        )
                        .getOrThrow(
                                ModEnchantments.LEECH
                        );

        /*
         * Read the enchantment before vanilla damages the shield,
         * because vanilla may break and empty the stack.
         */
        int leechLevel =
                blockingItem.isEmpty()
                        ? 0
                        : blockingItem.getEnchantmentLevel(
                        leech
                );

        int damageBefore =
                blockingItem.isDamageableItem()
                        ? blockingItem.getDamageValue()
                        : 0;

        int maximumDamageBefore =
                blockingItem.isDamageableItem()
                        ? blockingItem.getMaxDamage()
                        : 0;

        /*
         * Continue the operation chain and eventually invoke
         * LivingEntity#hurtCurrentlyUsedShield.
         */
        original.call(
                defender,
                blockedDamage
        );

        if (leechLevel <= 0
                || maximumDamageBefore <= 0) {
            return;
        }

        /*
         * If vanilla broke the shield, the ItemStack may now be empty.
         * In that case, all remaining durability was consumed.
         */
        int vanillaDurabilityUsed;

        if (blockingItem.isEmpty()) {
            vanillaDurabilityUsed =
                    maximumDamageBefore - damageBefore;
        } else {
            vanillaDurabilityUsed =
                    Math.max(
                            0,
                            blockingItem.getDamageValue()
                                    - damageBefore
                    );
        }

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
                    activeSlot
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