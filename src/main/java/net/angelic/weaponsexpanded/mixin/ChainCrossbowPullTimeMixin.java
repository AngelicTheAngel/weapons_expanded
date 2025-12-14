package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public class ChainCrossbowPullTimeMixin {

    // Fixed base charge time (before Quick Charge), in ticks
    @Unique
    private static final int CHAIN_CROSSBOW_BASE_PULL_TICKS = 45;

    @Inject(method = "getPullTime", at = @At("HEAD"), cancellable = true)
    private static void weaponsexpanded$fixedPullTimeWithQuickCharge(
            ItemStack stack,
            LivingEntity user,
            CallbackInfoReturnable<Integer> cir
    ) {
        if (!(stack.getItem() instanceof ChainCrossbowItem)) return;

        float baseSeconds = CHAIN_CROSSBOW_BASE_PULL_TICKS / 20.0F;

        // Let vanilla apply Quick Charge (and any other crossbow charge-time modifiers)
        float seconds = EnchantmentHelper.getCrossbowChargeTime(stack, user, baseSeconds);

        // Vanilla style conversion to ticks
        int ticks = MathHelper.floor(seconds * 20.0F);

        // Safety: never allow 0 ticks
        cir.setReturnValue(Math.max(1, ticks));
    }
}
