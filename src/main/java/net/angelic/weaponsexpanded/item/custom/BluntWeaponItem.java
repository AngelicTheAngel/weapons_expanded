package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tier;

public class BluntWeaponItem extends AxeItem {
    public BluntWeaponItem(
            Tier material,
            float attackDamage,
            float attackSpeed,
            Item.Properties properties
    ) {
        super(material, properties.attributes(
                AxeItem.createAttributes(material, attackDamage, attackSpeed)
        ));
    }
}