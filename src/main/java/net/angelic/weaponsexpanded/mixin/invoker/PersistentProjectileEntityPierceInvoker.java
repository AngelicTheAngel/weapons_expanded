package net.angelic.weaponsexpanded.mixin.invoker;

import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractArrow.class)
public interface PersistentProjectileEntityPierceInvoker {

    @Invoker("setPierceLevel")
    void weaponsexpanded$invokeSetPierceLevel(byte level);
}
