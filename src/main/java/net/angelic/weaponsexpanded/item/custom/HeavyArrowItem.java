package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.angelic.weaponsexpanded.mixin.accessor.PersistentProjectileEntityAccessor;
import net.angelic.weaponsexpanded.util.ProjectileEnchantmentApplier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HeavyArrowItem extends ArrowItem {
    public HeavyArrowItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, ItemStack weaponStack) {
        ItemStack newStack = stack.copy();
        newStack.setCount(1);

        HeavyArrowEntity arrow = new HeavyArrowEntity(world, shooter, newStack, weaponStack);

        // Make enchantment logic see the bow/crossbow stack
        if (!world.isClient()) {
            ((PersistentProjectileEntityAccessor) arrow).weaponsexpanded$setWeapon(weaponStack.copy());

            // Make Power/Punch apply
            ProjectileEnchantmentApplier.applyPowerAndPunchForHeavyArrow(world, weaponStack, arrow);
        }

        ProjectileEnchantmentApplier.applyFreezeAndFlame(world, weaponStack, arrow);
        return arrow;
    }
}
