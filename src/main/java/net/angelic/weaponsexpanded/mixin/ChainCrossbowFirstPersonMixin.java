package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemInHandRenderer.class)
public abstract class ChainCrossbowFirstPersonMixin {

    @Redirect(
            method = "renderArmWithItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z",
                    ordinal = 0
            )
    )
    private boolean weaponsexpanded$treatChainCrossbowAsCrossbow(
            ItemStack stack,
            Object item
    ) {
        if (item == Items.CROSSBOW) {
            return stack.is(Items.CROSSBOW)
                    || stack.is(ModItems.CHAIN_CROSSBOW);
        }

        return item instanceof Item realItem && stack.is(realItem);
    }
}