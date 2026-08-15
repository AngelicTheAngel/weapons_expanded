package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class TwoHandedSwordItem extends SwordItem {
    public TwoHandedSwordItem(Tier material, int attackDamage, float attackSpeed, Item.Properties properties) {
        super(material, properties.attributes(
                SwordItem.createAttributes(material, attackDamage, attackSpeed)
        ));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(
                Component.translatable(
                        "tooltip.weaponsexpanded.twohandedsword"
                ).withStyle(ChatFormatting.BLUE)
        );

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}