package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class TwoHandedHeavySwordItem extends SwordItem {
    public TwoHandedHeavySwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.twohandedsword"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
