package net.angelic.weaponsexpanded.mixin.enchantment;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LeechShieldBlockMixin {

    @Unique
    private boolean weaponsexpanded$bypassShieldBlock;

    @WrapMethod(
            method = "damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z"
    )
    private boolean weaponsexpanded$wrapDamage(
            ServerWorld world,
            DamageSource source,
            float incomingDamage,
            Operation<Boolean> original
    ) {
        LivingEntity defender = (LivingEntity) (Object) this;

        boolean wasBlocked = defender.blockedByShield(source);
        ItemStack blockingItem = wasBlocked
                ? defender.getActiveItem()
                : ItemStack.EMPTY;

        int damageBefore = blockingItem.isDamageable()
                ? blockingItem.getDamage()
                : 0;

        Entity sourceAttacker = source.getAttacker();
        boolean partialBlock =
                wasBlocked
                        && sourceAttacker instanceof LivingEntity attacker
                        && attacker.disablesShield();

        /*
         * Minecraft 1.21 only supports fully blocked or unblocked damage.
         * For a weapon that disables shields, bypass the vanilla block check
         * and pass 75% of the incoming damage.
         */
        float appliedDamage = partialBlock
                ? incomingDamage * 0.75F
                : incomingDamage;

        boolean result;

        weaponsexpanded$bypassShieldBlock = partialBlock;
        try {
            result = original.call(world, source, appliedDamage);
        } finally {
            weaponsexpanded$bypassShieldBlock = false;
        }

        if (partialBlock && defender instanceof PlayerEntity player) {
            player.disableShield(player.getActiveItem());
        }

        if (!wasBlocked || blockingItem.isEmpty()) {
            return result;
        }

        if (!(defender.getWorld() instanceof ServerWorld serverWorld)) {
            return result;
        }

        RegistryEntry.Reference<Enchantment> leechEntry =
                serverWorld.getRegistryManager()
                        .getOrThrow(RegistryKeys.ENCHANTMENT)
                        .getOrThrow(ModEnchantments.LEECH);

        int level = EnchantmentHelper.getLevel(leechEntry, blockingItem);
        if (level <= 0) {
            return result;
        }

        int vanillaDurabilityUsed = blockingItem.isDamageable()
                ? Math.max(0, blockingItem.getDamage() - damageBefore)
                : 0;

        if (vanillaDurabilityUsed > 0) {
            // 50% additional durability cost.
            int extraDamage =
                    MathHelper.ceil(vanillaDurabilityUsed * 0.5F);

            if (extraDamage > 0) {
                EquipmentSlot slot =
                        defender.getActiveHand() == Hand.MAIN_HAND
                                ? EquipmentSlot.MAINHAND
                                : EquipmentSlot.OFFHAND;

                blockingItem.damage(extraDamage, defender, slot);
            }

            defender.heal(
                    MathHelper.ceil(vanillaDurabilityUsed * 0.15F)
            );
        }

        return result;
    }

    @WrapOperation(
            method = "damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;"
                            + "blockedByShield("
                            + "Lnet/minecraft/entity/damage/DamageSource;)Z"
            )
    )
    private boolean weaponsexpanded$conditionallyBypassShield(
            LivingEntity defender,
            DamageSource source,
            Operation<Boolean> original
    ) {
        if (weaponsexpanded$bypassShieldBlock) {
            return false;
        }

        return original.call(defender, source);
    }
}