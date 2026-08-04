package net.angelic.weaponsexpanded.mixin.enchantment;

import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Projectile.class)
public abstract class ProjectileEntityMixin {

    @Inject(
            method = "setOwner",
            at = @At("TAIL")
    )
    private void weaponsexpanded$copyFreezeLevel(
            @Nullable Entity owner,
            CallbackInfo ci
    ) {
        Entity projectile =
                (Entity) (Object) this;

        if (projectile.level().isClientSide) {
            return;
        }

        if (!(projectile instanceof AbstractArrow)) {
            return;
        }

        if (!(owner
                instanceof LivingEntity livingOwner)) {
            return;
        }

        int mainHandLevel =
                EnchantmentHelper.getTagEnchantmentLevel(
                        ModEnchantments.FREEZE.get(),
                        livingOwner.getMainHandItem()
                );

        int offHandLevel =
                EnchantmentHelper.getTagEnchantmentLevel(
                        ModEnchantments.FREEZE.get(),
                        livingOwner.getOffhandItem()
                );

        int level = Math.max(
                mainHandLevel,
                offHandLevel
        );

        if (level > 0) {
            projectile.addTag(
                    "weaponsexpanded.freeze.level."
                            + level
            );
        }
    }
}