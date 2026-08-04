package net.angelic.weaponsexpanded.enchantment.custom;

import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.FireAspectEnchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class FrostbiteEnchantment extends Enchantment {
    public FrostbiteEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.BREAKABLE, new EquipmentSlot[]{EquipmentSlot.MAINHAND}
        );
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getMinPower(int level) {
        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof SwordItem || stack.getItem() instanceof AxeItem;
    }

    @Override
    protected boolean canAccept(Enchantment other) {
        return super.canAccept(other)
                && !(other instanceof FireAspectEnchantment)
                && !(other instanceof PollutingEnchantment)
                && !(other instanceof WitheringEnchantment);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (!user.getWorld().isClient && target instanceof LivingEntity livingTarget) {
            livingTarget.addStatusEffect(
                    new StatusEffectInstance(ModEffects.FROSTBITE.value(), 40 + level * 40, 0)
            );
        }
    }
}