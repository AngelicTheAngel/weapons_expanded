package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.Map;

public final class ZombieWeaponSwapUtil {

    private ZombieWeaponSwapUtil() {
    }

    public static void maybeSwapSword(
            Zombie zombie,
            RandomSource random
    ) {
        ItemStack mainHand = zombie.getItemBySlot(
                EquipmentSlot.MAINHAND
        );

        boolean isIronSword =
                mainHand.is(Items.IRON_SWORD);

        boolean isDiamondSword =
                mainHand.is(Items.DIAMOND_SWORD);

        if (!isIronSword && !isDiamondSword) {
            return;
        }

        // 50% chance to replace the vanilla sword.
        if (random.nextInt(2) != 0) {
            return;
        }

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

        Map<Enchantment, Integer> enchantments =
                EnchantmentHelper.getEnchantments(mainHand);

        if (!enchantments.isEmpty()) {
            EnchantmentHelper.setEnchantments(
                    enchantments,
                    replacement
            );
        }

        zombie.setItemSlot(
                EquipmentSlot.MAINHAND,
                replacement
        );
    }
}