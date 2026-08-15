package net.angelic.weaponsexpanded.mixin.enchantment;

import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Player.class)
public abstract class CleavingShieldStunMixin {

    @Unique
    private static final int
            WEAPONSEXPANDED$CLEAVING_TICKS_PER_LEVEL =
            20;

    @ModifyArg(
            method = "disableShield()V",
            at = @At(
                    value = "INVOKE",
                    target =
                            "Lnet/minecraft/world/item/"
                                    + "ItemCooldowns;"
                                    + "addCooldown("
                                    + "Lnet/minecraft/world/item/Item;"
                                    + "I)V"
            ),
            index = 1,
            require = 1
    )
    private int
    weaponsexpanded$cleavingIncreasesShieldStunTime(
            int baseStunTicks
    ) {
        Player shieldUser =
                (Player) (Object) this;

        LivingEntity attacker =
                shieldUser.getLastHurtByMob();

        if (attacker == null) {
            return baseStunTicks;
        }

        ItemStack weaponStack =
                attacker.getMainHandItem();

        Holder<Enchantment> cleaving =
                shieldUser.level()
                        .registryAccess()
                        .lookupOrThrow(
                                Registries.ENCHANTMENT
                        )
                        .getOrThrow(
                                ModEnchantments.CLEAVING
                        );

        int cleavingLevel =
                weaponStack.getEnchantmentLevel(
                        cleaving
                );

        if (cleavingLevel <= 0) {
            return baseStunTicks;
        }

        return baseStunTicks
                + WEAPONSEXPANDED$CLEAVING_TICKS_PER_LEVEL
                * cleavingLevel;
    }
}