package net.angelic.weaponsexpanded.mixin.invoker;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CrossbowItem.class)
public interface CrossbowItemInvoker {
    @Invoker("tryLoadProjectiles")
    static boolean weaponsexpanded$tryLoadProjectiles(
            LivingEntity shooter,
            ItemStack crossbow
    ) {
        throw new AssertionError();
    }
}