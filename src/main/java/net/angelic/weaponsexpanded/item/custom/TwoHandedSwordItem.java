package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class TwoHandedSwordItem extends SwordItem {
    public TwoHandedSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.twohandedsword"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}


