package net.angelic.weaponsexpanded.enchantment.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.FireAspectEnchantment;

public class PollutingEnchantment extends Enchantment {

    public PollutingEnchantment() {
        super(
                Rarity.RARE,
                EnchantmentCategory.BREAKABLE,
                new EquipmentSlot[]{
                        EquipmentSlot.MAINHAND
                }
        );
    }

    @Override
    public int getMaxLevel() {
        return 2;
    }

    @Override
    public int getMinCost(int level) {
        return 10 + 20 * (level - 1);
    }

    @Override
    public int getMaxCost(int level) {
        return super.getMinCost(level) + 50;
    }

    @Override
    public boolean canEnchant(ItemStack stack) {
        return stack.getItem() instanceof SwordItem
                || stack.getItem() instanceof AxeItem;
    }

    @Override
    public boolean canApplyAtEnchantingTable(
            ItemStack stack
    ) {
        if (stack.is(Items.BOOK)
                || stack.is(Items.ENCHANTED_BOOK)) {
            return true;
        }

        return canEnchant(stack);
    }

    @Override
    protected boolean checkCompatibility(
            Enchantment other
    ) {
        return super.checkCompatibility(other)
                && !(other instanceof FireAspectEnchantment)
                && !(other instanceof FrostbiteEnchantment)
                && !(other instanceof WitheringEnchantment);
    }

    @Override
    public void doPostAttack(
            LivingEntity user,
            Entity target,
            int level
    ) {
        if (!user.level().isClientSide
                && target
                instanceof LivingEntity livingTarget) {
            livingTarget.addEffect(
                    new MobEffectInstance(
                            MobEffects.POISON,
                            20 + level * 140,
                            0
                    )
            );
        }
    }
}