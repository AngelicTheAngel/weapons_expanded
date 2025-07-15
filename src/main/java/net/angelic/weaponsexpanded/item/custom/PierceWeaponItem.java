package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.item.*;
import net.minecraft.item.ToolMaterial;

public class PierceWeaponItem extends Item {
    public PierceWeaponItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(settings.sword(material, attackDamage, attackSpeed));
    }


}
