package net.angelic.weaponsexpanded.mixin.enchantment;

import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
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
            method = "disableShield(Z)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/player/ItemCooldownManager;"
                            + "set(Lnet/minecraft/item/Item;I)V"
            ),
            index = 1,
            require = 1
    )
    private int weaponsexpanded$cleavingIncreasesShieldStunTime(
            int baseStunTicks
    ) {
        PlayerEntity shieldUser = (PlayerEntity) (Object) this;
        LivingEntity attacker = shieldUser.getAttacker();

        if (attacker == null) {
            return baseStunTicks;
        }

        ItemStack weaponStack = attacker.getMainHandStack();

        int cleavingLevel = EnchantmentHelper.getLevel(
                ModEnchantments.CLEAVING,
                weaponStack
        );

        if (cleavingLevel <= 0) {
            return baseStunTicks;
        }

        return baseStunTicks
                + WEAPONSEXPANDED$CLEAVING_TICKS_PER_LEVEL
                * cleavingLevel;
    }
}