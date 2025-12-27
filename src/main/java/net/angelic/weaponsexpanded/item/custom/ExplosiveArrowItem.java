package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.entity.projectile.ExplosiveArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ExplosiveArrowItem extends ArrowItem {
    public ExplosiveArrowItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, ItemStack weaponStack) {
        ItemStack one = stack.copy();
        one.setCount(1);

        return new ExplosiveArrowEntity(world, shooter, one);
    }
}
