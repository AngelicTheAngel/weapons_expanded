package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.world.item.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public class BluntWeaponItem extends Item {
    public BluntWeaponItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties settings) {
        super(settings.axe(material, attackDamage, attackSpeed));
    }
}
