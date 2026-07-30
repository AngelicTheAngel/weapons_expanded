package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.item.*;

public class BluntWeaponItem extends Item {
    public BluntWeaponItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(settings.axe(material, attackDamage, attackSpeed));
    }
}
