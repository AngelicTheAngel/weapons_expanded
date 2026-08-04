package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TwoHandedSwordItem extends SwordItem {
    public TwoHandedSwordItem(
            Tier material,
            int attackDamage,
            float attackSpeed,
            Item.Properties properties
    ) {
        super(
                material,
                attackDamage,
                attackSpeed,
                properties
        );
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            @Nullable Level level,
            List<Component> tooltip,
            TooltipFlag flag
    ) {
        tooltip.add(
                Component.translatable(
                        "tooltip.weaponsexpanded.twohandedsword"
                ).withStyle(ChatFormatting.BLUE)
        );

        super.appendHoverText(stack, level, tooltip, flag);
    }
}