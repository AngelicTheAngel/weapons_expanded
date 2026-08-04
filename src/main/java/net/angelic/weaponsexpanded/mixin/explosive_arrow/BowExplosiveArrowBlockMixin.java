package net.angelic.weaponsexpanded.mixin.explosive_arrow;

import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

@Mixin(BowItem.class)
public class BowExplosiveArrowBlockMixin {

    @Inject(
            method = "getAllSupportedProjectiles",
            at = @At("RETURN"),
            cancellable = true
    )
    private void weaponsexpanded$blockExplosiveArrowsOnBow(
            CallbackInfoReturnable<
                    Predicate<ItemStack>
                    > cir
    ) {
        Predicate<ItemStack> vanilla =
                cir.getReturnValue();

        cir.setReturnValue(
                stack ->
                        vanilla.test(stack)
                                && !stack.is(
                                ModItems.EXPLOSIVE_ARROW.get()
                        )
        );
    }
}