package net.angelic.weaponsexpanded.enchantment.custom;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.FlameEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;

public final class FreezeEnchantment extends Enchantment {
    public FreezeEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.BOW, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND});
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof BowItem;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other)
                && !(other instanceof FlameEnchantment);
    }
}