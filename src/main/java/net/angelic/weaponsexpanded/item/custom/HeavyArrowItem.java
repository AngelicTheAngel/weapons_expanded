package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.angelic.weaponsexpanded.util.ProjectileEnchantmentApplier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HeavyArrowItem extends ArrowItem {
    private static final String CREATIVE_FIRED_TAG =
            "weaponsexpanded.creative_fired_heavy_arrow";

    public HeavyArrowItem(Settings settings) {
        super(settings);
    }

    /**
     * Vanilla 1.20.1 ArrowItem override.
     * Vanilla BowItem applies Power, Punch, and Flame after calling this,
     * so only the custom Freeze enchantment is applied here.
     */
    @Override
    public PersistentProjectileEntity createArrow(
            World world,
            ItemStack stack,
            LivingEntity shooter
    ) {
        ItemStack weaponStack = shooter.getActiveItem();

        HeavyArrowEntity arrow = createHeavyArrow(
                world,
                stack,
                shooter,
                weaponStack
        );

        if (!world.isClient && !weaponStack.isEmpty()) {
            ProjectileEnchantmentApplier.applyFreeze(
                    world,
                    weaponStack,
                    arrow
            );
        }

        return arrow;
    }

    /**
     * Custom overload used by LongbowItem, which has the weapon stack.
     */
    public HeavyArrowEntity createArrow(
            World world,
            ItemStack stack,
            LivingEntity shooter,
            ItemStack weaponStack
    ) {
        HeavyArrowEntity arrow = createHeavyArrow(
                world,
                stack,
                shooter,
                weaponStack
        );

        if (!world.isClient) {
            ProjectileEnchantmentApplier.applyPowerAndPunchForHeavyArrow(
                    world,
                    weaponStack,
                    arrow
            );
        }

        return arrow;
    }

    private HeavyArrowEntity createHeavyArrow(
            World world,
            ItemStack stack,
            LivingEntity shooter,
            ItemStack weaponStack
    ) {
        ItemStack pickupStack = stack.copy();
        pickupStack.setCount(1);

        HeavyArrowEntity arrow = new HeavyArrowEntity(
                world,
                shooter,
                pickupStack,
                weaponStack
        );

        if (!world.isClient
                && shooter instanceof PlayerEntity player
                && player.getAbilities().creativeMode) {
            arrow.addCommandTag(CREATIVE_FIRED_TAG);
        }

        return arrow;
    }
}