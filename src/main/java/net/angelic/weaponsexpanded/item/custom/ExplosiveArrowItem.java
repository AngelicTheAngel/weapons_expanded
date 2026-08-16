package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.entity.projectile.ExplosiveArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ExplosiveArrowItem extends ArrowItem {
    public ExplosiveArrowItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public AbstractArrow createArrow(
            Level level,
            ItemStack stack,
            LivingEntity shooter,
            ItemStack weaponStack
    ) {
        ItemStack singleArrow = stack.copy();
        singleArrow.setCount(1);

        return new ExplosiveArrowEntity(
                level,
                shooter,
                singleArrow,
                weaponStack
        );
    }
}