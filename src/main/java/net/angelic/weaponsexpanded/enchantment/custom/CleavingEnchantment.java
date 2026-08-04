package net.angelic.weaponsexpanded.enchantment.custom;

import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class CleavingEnchantment extends Enchantment {

    public CleavingEnchantment() {
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
        return 3;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof AxeItem
                || stack.getItem()
                instanceof WarhammerItem;
    }

    @Override
    public boolean canApplyAtEnchantingTable(
            ItemStack stack
    ) {
        /*
         * Books must remain valid so Cleaving can be obtained
         * from the enchanting table and later applied in an anvil.
         */
        if (stack.is(Items.BOOK)
                || stack.is(Items.ENCHANTED_BOOK)) {
            return true;
        }

        return canEnchant(stack);
    }
}