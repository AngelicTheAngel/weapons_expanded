package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.angelic.weaponsexpanded.mixin.accessor.PersistentProjectileEntityAccessor;
import net.angelic.weaponsexpanded.util.ProjectileEnchantmentApplier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HeavyArrowItem extends ArrowItem {
    private static final String WEAPONSEXPANDED$CREATIVE_FIRED_TAG = "weaponsexpanded.creative_fired_heavy_arrow";

    public HeavyArrowItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter, ItemStack weaponStack) {
        ItemStack newStack = stack.copy();
        newStack.setCount(1);

        HeavyArrowEntity arrow = new HeavyArrowEntity(world, shooter, newStack, weaponStack);

        if (!world.isClient()) {
            // Tag arrows fired by creative players so pickup destroys them (no item granted)
            if (shooter instanceof PlayerEntity player && player.getAbilities().creativeMode) {
                arrow.addCommandTag(WEAPONSEXPANDED$CREATIVE_FIRED_TAG);
            }

            ((PersistentProjectileEntityAccessor) arrow).weaponsexpanded$setWeapon(weaponStack.copy());
            ProjectileEnchantmentApplier.applyPowerAndPunchForHeavyArrow(world, weaponStack, arrow);
        }

        ProjectileEnchantmentApplier.applyFreezeAndFlame(world, weaponStack, arrow);
        return arrow;
    }
}