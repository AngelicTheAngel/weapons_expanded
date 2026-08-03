package net.angelic.weaponsexpanded.mixin.enchantment;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LeechShieldBlockMixin {

    @WrapOperation(
            method = "damage(Lnet/minecraft/server/world/ServerWorld;"
                    + "Lnet/minecraft/entity/damage/DamageSource;F)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;"
                            + "getDamageBlockedAmount("
                            + "Lnet/minecraft/server/world/ServerWorld;"
                            + "Lnet/minecraft/entity/damage/DamageSource;F)F"
            ),
            require = 1
    )
    private float weaponsexpanded$modifyBlockedDamage(
            LivingEntity defender,
            ServerWorld world,
            DamageSource source,
            float incomingDamage,
            Operation<Float> original
    ) {
        ItemStack blockingItem = defender.getBlockingItem();
        int damageBefore = blockingItem != null && blockingItem.isDamageable()
                ? blockingItem.getDamage()
                : 0;

        // Calls vanilla and any other compatible wrappers.
        float blocked = original.call(defender, world, source, incomingDamage);

        if (blocked <= 0.0F) {
            return blocked;
        }

        Entity attackerEntity = source.getAttacker();
        if (attackerEntity instanceof LivingEntity attacker
                && attacker.getWeaponDisableBlockingForSeconds() > 0.0F) {
            // Returning 25% blocked means the defender takes 75%.
            return incomingDamage * 0.25F;
        }

        if (blockingItem == null) {
            return blocked;
        }

        RegistryEntry.Reference<Enchantment> leechEntry =
                world.getRegistryManager()
                        .getOrThrow(RegistryKeys.ENCHANTMENT)
                        .getOrThrow(ModEnchantments.LEECH);

        int level = EnchantmentHelper.getLevel(leechEntry, blockingItem);
        if (level <= 0) {
            return blocked;
        }

        int vanillaDurabilityUsed = blockingItem.isDamageable()
                ? Math.max(0, blockingItem.getDamage() - damageBefore)
                : 0;

        if (vanillaDurabilityUsed > 0) {
            // 25% additional durability cost.
            int extraDamage = MathHelper.ceil(vanillaDurabilityUsed * 0.5F);

            if (extraDamage > 0) {
                blockingItem.damage(
                        extraDamage,
                        defender,
                        defender.getActiveHand().getEquipmentSlot()
                );
            }

            defender.heal(MathHelper.ceil(vanillaDurabilityUsed * 0.15F));
        }

        return blocked;
    }
}