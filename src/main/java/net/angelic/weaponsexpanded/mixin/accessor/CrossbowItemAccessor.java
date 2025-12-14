package net.angelic.weaponsexpanded.mixin.accessor;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CrossbowItem.class)
public interface CrossbowItemAccessor {

    @Invoker("loadProjectiles")
    boolean weaponsexpanded$loadProjectiles(LivingEntity shooter, ItemStack crossbow);
}
