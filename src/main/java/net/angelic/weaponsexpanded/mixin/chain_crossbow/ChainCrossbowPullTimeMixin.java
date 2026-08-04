package net.angelic.weaponsexpanded.mixin.chain_crossbow;

import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public abstract class ChainCrossbowPullTimeMixin {

    @Unique
    private static final int WEAPONSEXPANDED$BASE_PULL_TICKS = 38;

    @Inject(
            method = "getPullTime(Lnet/minecraft/item/ItemStack;)I",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void weaponsexpanded$fixedPullTimeWithQuickCharge(
            ItemStack stack,
            CallbackInfoReturnable<Integer> cir
    ) {
        if (!(stack.getItem() instanceof ChainCrossbowItem)) {
            return;
        }

        int quickChargeLevel = EnchantmentHelper.getLevel(
                Enchantments.QUICK_CHARGE,
                stack
        );

        // Matches vanilla 1.20.1: subtract five ticks per level.
        int pullTicks =
                WEAPONSEXPANDED$BASE_PULL_TICKS
                        - quickChargeLevel * 5;

        cir.setReturnValue(Math.max(1, pullTicks));
    }
}