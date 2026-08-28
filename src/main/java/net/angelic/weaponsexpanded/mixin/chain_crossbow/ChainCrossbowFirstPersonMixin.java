package net.angelic.weaponsexpanded.mixin.chain_crossbow;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ItemInHandRenderer.class)
public abstract class ChainCrossbowFirstPersonMixin {

    @WrapOperation(
            method = "submitArmWithItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z",
                    ordinal = 0
            )
    )
    private boolean weaponsexpanded$treatChainCrossbowAsCrossbow(
            ItemStack stack,
            Object item,
            Operation<Boolean> original
    ) {
        if (item == Items.CROSSBOW) {
            return original.call(stack, item)
                    || stack.is(ModItems.CHAIN_CROSSBOW);
        }

        return original.call(stack, item);
    }
}
