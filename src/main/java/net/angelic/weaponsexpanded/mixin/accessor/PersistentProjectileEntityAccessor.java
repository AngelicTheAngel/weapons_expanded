package net.angelic.weaponsexpanded.mixin.accessor;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PersistentProjectileEntity.class)
public interface PersistentProjectileEntityAccessor {

    @Accessor("weapon")
    void weaponsexpanded$setWeapon(@Nullable ItemStack weapon);
}
