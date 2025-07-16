package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;

public class TwoHandedHeavySwordItem extends Item {
    public TwoHandedHeavySwordItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(settings.sword(material, attackDamage, attackSpeed));
    }
}
