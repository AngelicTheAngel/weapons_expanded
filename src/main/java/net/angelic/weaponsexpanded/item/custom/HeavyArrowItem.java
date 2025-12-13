package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.angelic.weaponsexpanded.util.ProjectileEnchantmentApplier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HeavyArrowItem extends ArrowItem {
    public HeavyArrowItem(net.minecraft.item.Item.Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, ItemStack weaponStack) {
        HeavyArrowEntity arrow = new HeavyArrowEntity(world, shooter, stack.copy(), weaponStack);

        // Shared logic: apply Freeze tag + Flame fire based on the weapon’s enchantments
        ProjectileEnchantmentApplier.applyFreezeAndFlame(world, weaponStack, arrow);

        return arrow;
    }
}
