package net.angelic.weaponsexpanded.mixin.explosive_arrow;

import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public abstract class CrossbowExplosiveArrowDurabilityMixin {

    @Unique
    private static final int
            WEAPONSEXPANDED$DURABILITY_PER_EXPLOSIVE_ARROW =
            4;

    @Inject(
            method =
                    "getDurabilityUse("
                            + "Lnet/minecraft/world/item/ItemStack;"
                            + ")I",
            at = @At("HEAD"),
            cancellable = true,
            require = 1
    )
    private void weaponsexpanded$getExplosiveArrowDurabilityCost(
            ItemStack projectile,
            CallbackInfoReturnable<Integer> cir
    ) {
        if (projectile.is(
                ModItems.EXPLOSIVE_ARROW.get()
        )) {
            cir.setReturnValue(
                    WEAPONSEXPANDED$DURABILITY_PER_EXPLOSIVE_ARROW
            );
        }
    }
}