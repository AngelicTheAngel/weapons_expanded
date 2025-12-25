package net.angelic.weaponsexpanded.mixin;

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
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LeechShieldBlockMixin {

    @Redirect(
            method = "damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/LivingEntity;getDamageBlockedAmount(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)F"
            )
    )
    private float weaponsexpanded$leechAndDisableBlocking(
            LivingEntity defender, ServerWorld world, DamageSource source, float incomingDamage
    ) {
        ItemStack blockingItem = defender.getBlockingItem(); // null if not actually blocking
        int damageBefore = (blockingItem != null && blockingItem.isDamageable()) ? blockingItem.getDamage() : 0;

        float blocked = defender.getDamageBlockedAmount(world, source, incomingDamage);
        if (blocked <= 0.0F) {
            return blocked;
        }

        // If attacker is using a weapon that disables blocking, only block HALF of this hit
        // AND do not apply Leech healing / extra durability.
        Entity attackerEntity = source.getAttacker();
        if (attackerEntity instanceof LivingEntity attacker) {
            if (attacker.getWeaponDisableBlockingForSeconds() > 0.0F) {
                return incomingDamage * 0.75F; // defender takes .75% damage
            }
        }

        // Successful block: if the blocking item has Leech, heal and take 25% more shield durability.
        if (blockingItem != null) {
            RegistryEntry.Reference<Enchantment> leechEntry = world.getRegistryManager()
                    .getOrThrow(RegistryKeys.ENCHANTMENT)
                    .getOrThrow(ModEnchantments.LEECH);

            int lvl = EnchantmentHelper.getLevel(leechEntry, blockingItem);
            if (lvl > 0) {
                // Add +25% durability cost compared to what vanilla just consumed for this block.
                int vanillaUsed = 0;
                if (blockingItem.isDamageable()) {
                    vanillaUsed = blockingItem.getDamage() - damageBefore;
                    if (vanillaUsed > 0) {
                        int extra = MathHelper.ceil(vanillaUsed * 0.5F);
                        if (extra > 0) {
                            blockingItem.damage(extra, defender, defender.getActiveHand().getEquipmentSlot());
                        }
                    }
                }
                defender.heal(MathHelper.ceil(vanillaUsed * 0.15F));
            }
        }

        return blocked;
    }
}