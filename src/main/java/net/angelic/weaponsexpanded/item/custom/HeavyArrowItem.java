package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.angelic.weaponsexpanded.util.ProjectileEnchantmentApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HeavyArrowItem extends ArrowItem {
    private static final String CREATIVE_FIRED_TAG =
            "weaponsexpanded.creative_fired_heavy_arrow";

    public HeavyArrowItem(Item.Properties properties) {
        super(properties);
    }

    /**
     * Vanilla BowItem applies Power, Punch, and Flame after calling this,
     * so only the custom Freeze enchantment is applied here.
     */
    @Override
    public AbstractArrow createArrow(
            Level level,
            ItemStack stack,
            LivingEntity shooter
    ) {
        ItemStack weaponStack = shooter.getUseItem();

        HeavyArrowEntity arrow = createHeavyArrow(
                level,
                stack,
                shooter,
                weaponStack
        );

        if (!level.isClientSide && !weaponStack.isEmpty()) {
            ProjectileEnchantmentApplier.applyFreeze(
                    level,
                    weaponStack,
                    arrow
            );
        }

        return arrow;
    }

    /**
     * Custom overload used by LongbowItem, which supplies the weapon stack.
     */
    public HeavyArrowEntity createArrow(
            Level level,
            ItemStack stack,
            LivingEntity shooter,
            ItemStack weaponStack
    ) {
        HeavyArrowEntity arrow = createHeavyArrow(
                level,
                stack,
                shooter,
                weaponStack
        );

        if (!level.isClientSide) {
            ProjectileEnchantmentApplier.applyPowerAndPunchForHeavyArrow(
                    level,
                    weaponStack,
                    arrow
            );
        }

        return arrow;
    }

    private HeavyArrowEntity createHeavyArrow(
            Level level,
            ItemStack stack,
            LivingEntity shooter,
            ItemStack weaponStack
    ) {
        ItemStack pickupStack = stack.copy();
        pickupStack.setCount(1);

        HeavyArrowEntity arrow = new HeavyArrowEntity(
                level,
                shooter,
                pickupStack,
                weaponStack
        );

        if (!level.isClientSide
                && shooter instanceof Player player
                && player.getAbilities().instabuild) {
            arrow.addTag(CREATIVE_FIRED_TAG);
        }

        return arrow;
    }
}