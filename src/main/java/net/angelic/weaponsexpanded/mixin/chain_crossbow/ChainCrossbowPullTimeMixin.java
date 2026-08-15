package net.angelic.weaponsexpanded.mixin.chain_crossbow;

import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public abstract class ChainCrossbowPullTimeMixin {

    @Unique
    private static final int
            WEAPONSEXPANDED$BASE_PULL_TICKS = 38;

    @Inject(
            method =
                    "getChargeDuration("
                            + "Lnet/minecraft/world/item/ItemStack;"
                            + "Lnet/minecraft/world/entity/LivingEntity;"
                            + ")I",
            at = @At("HEAD"),
            cancellable = true,
            require = 1
    )
    private static void
    weaponsexpanded$fixedPullTimeWithQuickCharge(
            ItemStack stack,
            LivingEntity shooter,
            CallbackInfoReturnable<Integer> cir
    ) {
        if (!(stack.getItem()
                instanceof ChainCrossbowItem)) {
            return;
        }

        float basePullSeconds =
                WEAPONSEXPANDED$BASE_PULL_TICKS / 20.0F;

        float modifiedPullSeconds =
                EnchantmentHelper.modifyCrossbowChargingTime(
                        stack,
                        shooter,
                        basePullSeconds
                );

        int modifiedPullTicks =
                Mth.floor(modifiedPullSeconds * 20.0F);

        cir.setReturnValue(
                Math.max(1, modifiedPullTicks)
        );
    }
}