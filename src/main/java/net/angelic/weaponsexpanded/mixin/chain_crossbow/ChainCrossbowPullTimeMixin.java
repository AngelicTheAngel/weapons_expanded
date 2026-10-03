package net.angelic.weaponsexpanded.mixin.chain_crossbow;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CrossbowItem.class)
public class ChainCrossbowPullTimeMixin {

    @Inject(method = "getChargeDuration", at = @At("HEAD"), cancellable = true)
    private static void weaponsexpanded$fixedPullTimeWithQuickCharge(
            ItemStack stack,
            LivingEntity user,
            CallbackInfoReturnable<Integer> cir
    ) {
        if (!(stack.getItem() instanceof ChainCrossbowItem)) return;

        float baseSeconds = WeaponsExpandedConfig.get().chainCrossbowLoadTime / 20.0F;

        // Let vanilla apply Quick Charge (and any other crossbow charge-time modifiers)
        float seconds = EnchantmentHelper.modifyCrossbowChargingTime(stack, user, baseSeconds);

        // Vanilla style conversion to ticks
        int ticks = Mth.floor(seconds * 20.0F);

        // Safety: never allow 0 ticks
        cir.setReturnValue(Math.max(1, ticks));
    }
}
