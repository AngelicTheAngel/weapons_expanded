package net.angelic.weaponsexpanded.mixin.chain_crossbow;

import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemInHandRenderer.class)
public abstract class HeldItemRendererChainCrossbowMixin {

    /*
     * These static helpers determine which hands should be rendered.
     * Treating the chain crossbow as Items.CROSSBOW makes Minecraft:
     *
     * - hide the other hand while loading;
     * - hide the other hand while charged;
     * - use the correct crossbow hand-rendering rules.
     *
     * Forge already recognizes CrossbowItem subclasses inside
     * renderArmWithItem(), so that method does not need to be mixed
     * into separately.
     */
    @Redirect(
            method = {
                    "evaluateWhichHandsToRender",
                    "selectionUsingItemWhileHoldingBowLike",
                    "isChargedCrossbow"
            },
            at = @At(
                    value = "INVOKE",
                    target =
                            "Lnet/minecraft/world/item/ItemStack;"
                                    + "is("
                                    + "Lnet/minecraft/world/item/Item;"
                                    + ")Z"
            ),
            require = 1
    )
    private static boolean
    weaponsexpanded$treatChainCrossbowAsCrossbowForHands(
            ItemStack stack,
            Item checkedItem
    ) {
        return weaponsexpanded$isCrossbowCheck(
                stack,
                checkedItem
        );
    }

    @Unique
    private static boolean
    weaponsexpanded$isCrossbowCheck(
            ItemStack stack,
            Item checkedItem
    ) {
        if (stack.is(checkedItem)) {
            return true;
        }

        return checkedItem == Items.CROSSBOW
                && stack.getItem()
                instanceof ChainCrossbowItem;
    }
}