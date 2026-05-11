package net.angelic.weaponsexpanded.mixin.chain_crossbow;

import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

/**
 * Make HeldItemRenderer apply vanilla crossbow first-person behavior to ChainCrossbowItem,
 * but ONLY when vanilla is checking for the CROSSBOW item.
 */
@Mixin(ItemInHandRenderer.class)
public class HeldItemRendererChainCrossbowMixin {

    @Redirect(
            method = "renderArmWithItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"
            )
    )
    private boolean weaponsexpanded$treatChainCrossbowAsCrossbow(ItemStack stack, Item item) {
        // Only widen the check for the vanilla crossbow item.
        if (item == Items.CROSSBOW) {
            return stack.is(Items.CROSSBOW) || stack.getItem() instanceof ChainCrossbowItem;
        }

        // For all other checks, preserve vanilla behavior exactly.
        return stack.is(item);
    }
}
