package net.angelic.weaponsexpanded.mixin.accessor;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(CrossbowItem.class)
public interface CrossbowItemAccessor {

    @Invoker("tryLoadProjectiles")
    static boolean weaponsexpanded$loadProjectiles(LivingEntity shooter, ItemStack crossbow) {
        throw new AssertionError("Mixin invoker not transformed");
    }
}
