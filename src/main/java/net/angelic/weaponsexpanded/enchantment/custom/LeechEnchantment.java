package net.angelic.weaponsexpanded.enchantment.custom;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class LeechEnchantment extends Enchantment {

    public LeechEnchantment() {
        super(
                Rarity.RARE,
                EnchantmentCategory.BREAKABLE,
                new EquipmentSlot[]{
                        EquipmentSlot.MAINHAND,
                        EquipmentSlot.OFFHAND
                }
        );
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof ShieldItem;
    }

    @Override
    public boolean canApplyAtEnchantingTable(
            ItemStack stack
    ) {
        if (stack.is(Items.BOOK)
                || stack.is(Items.ENCHANTED_BOOK)) {
            return true;
        }

        return canEnchant(stack);
    }
}