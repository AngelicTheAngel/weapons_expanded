package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class TwoHandedSwordItem extends SwordItem {
    public TwoHandedSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.weaponsexpanded.twohandedsword").formatted(Formatting.BLUE));
        super.appendTooltip(stack, context, tooltip, type);
    }
}


