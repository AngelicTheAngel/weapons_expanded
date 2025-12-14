package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Make HeldItemRenderer apply vanilla crossbow first-person behavior to ChainCrossbowItem,
 * but ONLY when vanilla is checking for the CROSSBOW item.
 */
@Mixin(HeldItemRenderer.class)
public class HeldItemRendererChainCrossbowMixin {

    @Redirect(
            method = "renderFirstPersonItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"
            )
    )
    private boolean weaponsexpanded$treatChainCrossbowAsCrossbow(ItemStack stack, Item item) {
        // Only widen the check for the vanilla crossbow item.
        if (item == Items.CROSSBOW) {
            return stack.isOf(Items.CROSSBOW) || stack.getItem() instanceof ChainCrossbowItem;
        }

        // For all other checks, preserve vanilla behavior exactly.
        return stack.isOf(item);
    }
}
