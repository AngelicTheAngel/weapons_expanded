package net.angelic.weaponsexpanded.mixin.enchantment;

import net.angelic.weaponsexpanded.enchantment.ModEnchantmentHelper;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(PlayerEntity.class)
public abstract class CleavingShieldStunMixin {

    @Unique
    private static final int WEAPONSEXPANDED$CLEAVING_TICKS_PER_LEVEL = 20;

    @ModifyArg(
            method = "disableShield(Lnet/minecraft/item/ItemStack;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/ItemCooldownManager;set(Lnet/minecraft/item/ItemStack;I)V"
            ),
            index = 1,
            require = 1
    )
    private int weaponsexpanded$cleavingIncreasesShieldStunTime(int originalTicks) {
        PlayerEntity defender = (PlayerEntity) (Object) this;
        LivingEntity attacker = defender.getAttacker();

        if (attacker == null) {
            return originalTicks;
        }

        ItemStack weaponStack = attacker.getMainHandStack();

        int cleavingLevel = ModEnchantmentHelper.getLevel(
                attacker.getEntityWorld(),
                weaponStack,
                ModEnchantments.CLEAVING
        );

        if (cleavingLevel <= 0) {
            return originalTicks;
        }

        return originalTicks
                + WEAPONSEXPANDED$CLEAVING_TICKS_PER_LEVEL * cleavingLevel;
    }
}