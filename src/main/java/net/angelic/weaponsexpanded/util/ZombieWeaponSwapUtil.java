package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public final class ZombieWeaponSwapUtil {
    private ZombieWeaponSwapUtil() {
    }

    public static void maybeSwapSword(Zombie zombie, RandomSource random) {
        ItemStack mainHand = zombie.getItemBySlot(EquipmentSlot.MAINHAND);

        boolean isIronSword = mainHand.is(Items.IRON_SWORD);
        boolean isDiamondSword = mainHand.is(Items.DIAMOND_SWORD);

        if (!isIronSword && !isDiamondSword) return;
        if (random.nextInt(2) != 0) return;

        ItemStack replacement;
        if (isDiamondSword) {
            replacement = random.nextBoolean()
                    ? new ItemStack(ModItems.DIAMOND_SICKLE.get())
                    : new ItemStack(ModItems.DIAMOND_SCYTHE.get());
        } else {
            replacement = random.nextBoolean()
                    ? new ItemStack(ModItems.IRON_SICKLE.get())
                    : new ItemStack(ModItems.IRON_SCYTHE.get());
        }

        ItemEnchantments ench = mainHand.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY);
        if (!ench.isEmpty()) {
            replacement.set(DataComponents.ENCHANTMENTS, ench);
        }

        zombie.setItemSlot(EquipmentSlot.MAINHAND, replacement);
    }
}
