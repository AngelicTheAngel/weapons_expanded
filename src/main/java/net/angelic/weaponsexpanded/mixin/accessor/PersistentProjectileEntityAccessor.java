package net.angelic.weaponsexpanded.mixin.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

@Mixin(AbstractArrow.class)
public interface PersistentProjectileEntityAccessor {

    @Accessor("firedFromWeapon")
    void weaponsexpanded$setWeapon(@Nullable ItemStack weapon);
}
