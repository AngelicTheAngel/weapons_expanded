package net.angelic.weaponsexpanded.mixin.chain_crossbow;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererChainCrossbowMixin {

    @WrapOperation(
            method = "renderFirstPersonItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z",
                    ordinal = 0
            )
    )
    private boolean weaponsexpanded$treatChainCrossbowAsCrossbow(
            ItemStack stack,
            Item checkedItem,
            Operation<Boolean> original
    ) {
        boolean vanillaResult = original.call(stack, checkedItem);

        return vanillaResult
                || checkedItem == Items.CROSSBOW
                && stack.getItem() instanceof ChainCrossbowItem;
    }
}