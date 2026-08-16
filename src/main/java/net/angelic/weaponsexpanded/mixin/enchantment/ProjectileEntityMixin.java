package net.angelic.weaponsexpanded.mixin.enchantment;

import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Projectile.class)
public abstract class ProjectileEntityMixin {

    @Inject(
            method =
                    "setOwner("
                            + "Lnet/minecraft/world/entity/Entity;"
                            + ")V",
            at = @At("TAIL"),
            require = 1
    )
    private void weaponsexpanded$copyFreezeLevel(
            Entity owner,
            CallbackInfo ci
    ) {
        if (!((Object) this
                instanceof AbstractArrow projectile)) {
            return;
        }

        if (projectile.level().isClientSide()) {
            return;
        }

        if (!(owner
                instanceof LivingEntity livingOwner)) {
            return;
        }

        Holder<Enchantment> freeze =
                livingOwner.level()
                        .registryAccess()
                        .lookupOrThrow(
                                Registries.ENCHANTMENT
                        )
                        .getOrThrow(
                                ModEnchantments.FREEZE
                        );

        int mainHandLevel =
                livingOwner.getMainHandItem()
                        .getEnchantmentLevel(freeze);

        int offHandLevel =
                livingOwner.getOffhandItem()
                        .getEnchantmentLevel(freeze);

        int freezeLevel =
                Math.max(
                        mainHandLevel,
                        offHandLevel
                );

        if (freezeLevel > 0) {
            projectile.addTag(
                    "weaponsexpanded.freeze.level."
                            + freezeLevel
            );
        }
    }
}