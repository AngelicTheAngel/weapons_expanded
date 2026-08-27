package net.angelic.weaponsexpanded.item.custom.arrow;

import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.angelic.weaponsexpanded.mixin.accessor.PersistentProjectileEntityAccessor;
import net.angelic.weaponsexpanded.util.ProjectileEnchantmentApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HeavyArrowItem extends ArrowItem {
    private static final String WEAPONSEXPANDED$CREATIVE_FIRED_TAG = "weaponsexpanded.creative_fired_heavy_arrow";

    public HeavyArrowItem(Item.Properties settings) {
        super(settings);
    }

    @Override
    public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter, ItemStack weaponStack) {
        ItemStack newStack = stack.copy();
        newStack.setCount(1);

        HeavyArrowEntity arrow = new HeavyArrowEntity(world, shooter, newStack, weaponStack);

        if (!world.isClientSide()) {
            // Tag arrows fired by creative players so pickup destroys them (no item granted)
            if (shooter instanceof Player player && player.getAbilities().instabuild) {
                arrow.addTag(WEAPONSEXPANDED$CREATIVE_FIRED_TAG);
            }

            ((PersistentProjectileEntityAccessor) arrow).weaponsexpanded$setWeapon(weaponStack.copy());
            ProjectileEnchantmentApplier.applyPowerAndPunchForHeavyArrow(world, weaponStack, arrow);
        }

        ProjectileEnchantmentApplier.applyFreezeAndFlame(world, weaponStack, arrow);
        return arrow;
    }
}
