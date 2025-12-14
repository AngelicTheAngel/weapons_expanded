package net.angelic.weaponsexpanded.mixin.invoker;

import net.minecraft.entity.projectile.PersistentProjectileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PersistentProjectileEntity.class)
public interface PersistentProjectileEntityPierceInvoker {

    @Invoker("setPierceLevel")
    void weaponsexpanded$invokeSetPierceLevel(byte level);
}
