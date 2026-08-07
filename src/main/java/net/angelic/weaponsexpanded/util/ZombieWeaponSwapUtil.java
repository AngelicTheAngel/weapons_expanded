package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;

public final class ZombieWeaponSwapUtil {
    private ZombieWeaponSwapUtil() {}

    public static void maybeSwapSword(ZombieEntity zombie, Random random) {
        ItemStack mainHand = zombie.getEquippedStack(EquipmentSlot.MAINHAND);

        boolean isIronSword = mainHand.isOf(Items.IRON_SWORD);
        boolean isDiamondSword = mainHand.isOf(Items.DIAMOND_SWORD);

        if (!isIronSword && !isDiamondSword) return;
        if (random.nextInt(2) != 0) return;

        ItemStack replacement;
        if (isDiamondSword) {
            replacement = random.nextBoolean()
                    ? new ItemStack(ModItems.DIAMOND_SICKLE)
                    : new ItemStack(ModItems.DIAMOND_SCYTHE);
        } else {
            replacement = random.nextBoolean()
                    ? new ItemStack(ModItems.IRON_SICKLE)
                    : new ItemStack(ModItems.IRON_SCYTHE);
        }

        // Carry over enchantments from the original weapon
        ItemEnchantmentsComponent ench =
                mainHand.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
        if (!ench.isEmpty()) {
            replacement.set(DataComponentTypes.ENCHANTMENTS, ench);
        }

        zombie.equipStack(EquipmentSlot.MAINHAND, replacement);
    }
}
