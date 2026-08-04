package net.angelic.weaponsexpanded.mixin.chain_crossbow;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HeldItemRenderer.class)
public abstract class HeldItemRendererChainCrossbowMixin {

    /*
     * These static helpers determine which hands should be rendered.
     * Treating the chain crossbow as Items.CROSSBOW makes Minecraft:
     *
     * - hide the other hand while loading;
     * - hide the other hand while charged;
     * - use the correct crossbow hand-rendering rules.
     */
    @WrapOperation(
            method = {
                    "getHandRenderType",
                    "getUsingItemHandRenderType",
                    "isChargedCrossbow"
            },
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;"
                            + "isOf(Lnet/minecraft/item/Item;)Z"
            )
    )
    private static boolean weaponsexpanded$treatChainCrossbowAsCrossbowForHands(
            ItemStack stack,
            Item checkedItem,
            Operation<Boolean> original
    ) {
        return weaponsexpanded$isCrossbowCheck(
                stack,
                checkedItem,
                original
        );
    }

    /*
     * In renderFirstPersonItem, ordinal 0 is the FILLED_MAP check.
     * Ordinal 1 is the CROSSBOW check.
     *
     * Reaching the vanilla crossbow rendering branch applies the
     * correct loading translation and rotation, keeping the weapon
     * inside the player's field of view.
     */
    @WrapOperation(
            method = "renderFirstPersonItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;"
                            + "isOf(Lnet/minecraft/item/Item;)Z",
                    ordinal = 1
            )
    )
    private boolean weaponsexpanded$treatChainCrossbowAsCrossbowForRendering(
            ItemStack stack,
            Item checkedItem,
            Operation<Boolean> original
    ) {
        return weaponsexpanded$isCrossbowCheck(
                stack,
                checkedItem,
                original
        );
    }

    @Unique
    private static boolean weaponsexpanded$isCrossbowCheck(
            ItemStack stack,
            Item checkedItem,
            Operation<Boolean> original
    ) {
        boolean vanillaResult =
                original.call(stack, checkedItem);

        if (vanillaResult) {
            return true;
        }

        return checkedItem == Items.CROSSBOW
                && stack.getItem()
                instanceof ChainCrossbowItem;
    }
}