package net.angelic.weaponsexpanded.enchantment.custom;

import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;

public class CleavingEnchantment extends Enchantment {
    public CleavingEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND}
        );
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof AxeItem || stack.getItem() instanceof WarhammerItem;
    }
}