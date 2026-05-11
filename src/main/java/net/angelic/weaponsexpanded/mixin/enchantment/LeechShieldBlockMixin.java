package net.angelic.weaponsexpanded.mixin.enchantment;

import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LeechShieldBlockMixin {

    @Redirect(
            method = "hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;applyItemBlocking(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)F"
            )
    )
    private float weaponsexpanded$leechAndDisableBlocking(
            LivingEntity defender, ServerLevel world, DamageSource source, float incomingDamage
    ) {
        ItemStack blockingItem = defender.getItemBlockingWith(); // null if not actually blocking
        int damageBefore = (blockingItem != null && blockingItem.isDamageableItem()) ? blockingItem.getDamageValue() : 0;

        float blocked = defender.applyItemBlocking(world, source, incomingDamage);
        if (blocked <= 0.0F) {
            return blocked;
        }

        // If attacker is using a weapon that disables blocking, only block HALF of this hit
        // AND do not apply Leech healing / extra durability.
        Entity attackerEntity = source.getEntity();
        if (attackerEntity instanceof LivingEntity attacker) {
            if (attacker.getSecondsToDisableBlocking() > 0.0F) {
                return incomingDamage * 0.75F; // defender takes .75% damage
            }
        }

        // Successful block: if the blocking item has Leech, heal and take 25% more shield durability.
        if (blockingItem != null) {
            Holder.Reference<Enchantment> leechEntry = world.registryAccess()
                    .lookupOrThrow(Registries.ENCHANTMENT)
                    .getOrThrow(ModEnchantments.LEECH);

            int lvl = EnchantmentHelper.getItemEnchantmentLevel(leechEntry, blockingItem);
            if (lvl > 0) {
                // Add +25% durability cost compared to what vanilla just consumed for this block.
                int vanillaUsed = 0;
                if (blockingItem.isDamageableItem()) {
                    vanillaUsed = blockingItem.getDamageValue() - damageBefore;
                    if (vanillaUsed > 0) {
                        int extra = Mth.ceil(vanillaUsed * 0.5F);
                        if (extra > 0) {
                            blockingItem.hurtAndBreak(extra, defender, defender.getUsedItemHand().asEquipmentSlot());
                        }
                    }
                }
                defender.heal(Mth.ceil(vanillaUsed * 0.15F));
            }
        }

        return blocked;
    }
}
