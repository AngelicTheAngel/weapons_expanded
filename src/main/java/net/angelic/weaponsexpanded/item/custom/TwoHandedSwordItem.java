package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.List;
import java.util.function.Consumer;

public class TwoHandedSwordItem extends Item {
    public TwoHandedSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Item.Properties properties) {
        super(properties.sword(material, attackDamage, attackSpeed));
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        tooltipAdder.accept(
                Component.translatable(
                        "tooltip.weaponsexpanded.twohandedsword"
                ).withStyle(ChatFormatting.BLUE)
        );

        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }
}