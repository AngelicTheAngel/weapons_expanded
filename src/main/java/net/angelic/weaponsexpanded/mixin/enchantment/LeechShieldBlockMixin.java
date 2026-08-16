package net.angelic.weaponsexpanded.mixin.enchantment;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LivingEntity.class)
public abstract class LeechShieldBlockMixin {

    @Unique
    private static final float
            WEAPONSEXPANDED$EXTRA_DURABILITY_MULTIPLIER =
            0.5F;

    @Unique
    private static final float
            WEAPONSEXPANDED$HEAL_MULTIPLIER =
            0.15F;

    @WrapMethod(
            method =
                    "applyItemBlocking("
                            + "Lnet/minecraft/server/level/ServerLevel;"
                            + "Lnet/minecraft/world/damagesource/DamageSource;"
                            + "F)F"
    )
    private float weaponsexpanded$applyLeechShieldEffects(
            ServerLevel level,
            DamageSource damageSource,
            float incomingDamage,
            Operation<Float> original
    ) {
        LivingEntity defender =
                (LivingEntity) (Object) this;

        /*
         * In 1.21.11, this identifies the stack that is actively
         * responsible for blocking.
         */
        ItemStack blockingItem =
                defender.getItemBlockingWith();

        /*
         * getItemBlockingWith() is nullable. applyItemBlocking() is also
         * reached for damage that is not blocked, so preserve vanilla and
         * leave immediately when no blocking stack exists.
         */
        if (blockingItem == null || blockingItem.isEmpty()) {
            return original.call(
                    level,
                    damageSource,
                    incomingDamage
            );
        }

        InteractionHand activeHand =
                defender.getUsedItemHand();

        EquipmentSlot activeSlot =
                activeHand == InteractionHand.MAIN_HAND
                        ? EquipmentSlot.MAINHAND
                        : EquipmentSlot.OFFHAND;

        Holder<Enchantment> leech =
                level.registryAccess()
                        .lookupOrThrow(Registries.ENCHANTMENT)
                        .getOrThrow(ModEnchantments.LEECH);

        int leechLevel =
                blockingItem.getEnchantmentLevel(leech);

        int damageBefore =
                blockingItem.isDamageableItem()
                        ? blockingItem.getDamageValue()
                        : 0;

        int maximumDamageBefore =
                blockingItem.isDamageableItem()
                        ? blockingItem.getMaxDamage()
                        : 0;

        /*
         * Runs vanilla blocking, including the normal durability damage.
         * The returned float is the amount of incoming damage blocked.
         */
        float blockedDamage = original.call(
                level,
                damageSource,
                incomingDamage
        );

        if (blockedDamage <= 0.0F
                || leechLevel <= 0
                || maximumDamageBefore <= 0) {
            return blockedDamage;
        }

        int vanillaDurabilityUsed;

        if (blockingItem.isEmpty()) {
            vanillaDurabilityUsed =
                    maximumDamageBefore - damageBefore;
        } else {
            vanillaDurabilityUsed =
                    Math.max(
                            0,
                            blockingItem.getDamageValue() - damageBefore
                    );
        }

        if (vanillaDurabilityUsed <= 0) {
            return blockedDamage;
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

        return blockedDamage;
    }
}