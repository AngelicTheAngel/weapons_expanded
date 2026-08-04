package net.angelic.weaponsexpanded.mixin.enchantment;

import net.angelic.weaponsexpanded.enchantment.custom.LeechEnchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public abstract class LeechEnchantingTableMixin {

    @Inject(
            method = "getPossibleEntries("
                    + "ILnet/minecraft/item/ItemStack;Z)"
                    + "Ljava/util/List;",
            at = @At("RETURN")
    )
    private static void weaponsexpanded$filterLeechTargets(
            int power,
            ItemStack stack,
            boolean treasureAllowed,
            CallbackInfoReturnable<List<EnchantmentLevelEntry>> cir
    ) {
        if (stack.isOf(Items.BOOK)
                || stack.isOf(Items.ENCHANTED_BOOK)) {
            return;
        }

        cir.getReturnValue().removeIf(entry ->
                entry.enchantment instanceof LeechEnchantment
                        && !entry.enchantment.isAcceptableItem(stack)
        );
    }
}