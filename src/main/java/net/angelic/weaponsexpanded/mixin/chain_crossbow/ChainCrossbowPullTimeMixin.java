package net.angelic.weaponsexpanded.mixin.chain_crossbow;

import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public abstract class ChainCrossbowPullTimeMixin {

    @Unique
    private static final int
            WEAPONSEXPANDED$BASE_PULL_TICKS =
            38;

    @Inject(
            method =
                    "getChargeDuration("
                            + "Lnet/minecraft/world/item/ItemStack;"
                            + ")I",
            at = @At("HEAD"),
            cancellable = true,
            require = 1
    )
    private static void
    weaponsexpanded$fixedPullTimeWithQuickCharge(
            ItemStack stack,
            CallbackInfoReturnable<Integer> cir
    ) {
        if (!(stack.getItem()
                instanceof ChainCrossbowItem)) {
            return;
        }

        int quickChargeLevel =
                EnchantmentHelper.getTagEnchantmentLevel(
                        Enchantments.QUICK_CHARGE,
                        stack
                );

        int pullTicks =
                WEAPONSEXPANDED$BASE_PULL_TICKS
                        - quickChargeLevel * 5;

        cir.setReturnValue(
                Math.max(1, pullTicks)
        );
    }
}