package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.random.Random;

import java.util.Map;

public final class ZombieWeaponSwapUtil {

    private ZombieWeaponSwapUtil() {
    }

    public static void maybeSwapSword(
            ZombieEntity zombie,
            Random random
    ) {
        ItemStack mainHand = zombie.getEquippedStack(
                EquipmentSlot.MAINHAND
        );

        boolean isIronSword =
                mainHand.isOf(Items.IRON_SWORD);

        boolean isDiamondSword =
                mainHand.isOf(Items.DIAMOND_SWORD);

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
                    ? new ItemStack(ModItems.DIAMOND_SICKLE)
                    : new ItemStack(ModItems.DIAMOND_SCYTHE);
        } else {
            replacement = random.nextBoolean()
                    ? new ItemStack(ModItems.IRON_SICKLE)
                    : new ItemStack(ModItems.IRON_SCYTHE);
        }

        Map<Enchantment, Integer> enchantments =
                EnchantmentHelper.get(mainHand);

        if (!enchantments.isEmpty()) {
            EnchantmentHelper.set(
                    enchantments,
                    replacement
            );
        }

        zombie.equipStack(
                EquipmentSlot.MAINHAND,
                replacement
        );
    }
}