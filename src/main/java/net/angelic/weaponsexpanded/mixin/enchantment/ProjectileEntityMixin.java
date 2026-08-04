package net.angelic.weaponsexpanded.mixin.enchantment;

import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ProjectileEntity.class)
public abstract class ProjectileEntityMixin {
    @Inject(method = "setOwner", at = @At("TAIL"))
    private void weaponsExpanded$copyFreezeLevel(
            @Nullable Entity owner,
            CallbackInfo ci
    ) {
        Entity projectile = (Entity) (Object) this;

        if (projectile.getWorld().isClient) {
            return;
        }

        if (!(projectile instanceof PersistentProjectileEntity)) {
            return;
        }

        if (!(owner instanceof LivingEntity livingOwner)) {
            return;
        }

        int mainHandLevel = EnchantmentHelper.getLevel(
                ModEnchantments.FREEZE,
                livingOwner.getMainHandStack()
        );

        int offHandLevel = EnchantmentHelper.getLevel(
                ModEnchantments.FREEZE,
                livingOwner.getOffHandStack()
        );

        int level = Math.max(mainHandLevel, offHandLevel);

        if (level > 0) {
            projectile.addCommandTag(
                    "weaponsexpanded.freeze.level." + level
            );
        }
    }
}