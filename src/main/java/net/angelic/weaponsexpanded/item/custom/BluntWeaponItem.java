package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.util.ModBlockTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.Weapon;

public class BluntWeaponItem extends Item {
    public BluntWeaponItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties settings) {
        super(settings.tool(material, ModBlockTags.BLUNT_MINEABLE, attackDamage, attackSpeed, 6.0F)
                .component(DataComponents.WEAPON, new Weapon(1)));
    }
}
