package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.AttackRange;

public class ScytheItem extends Item {
    public ScytheItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties settings) {
        super(settings.sword(material, attackDamage, attackSpeed)
                .component(DataComponents.ATTACK_RANGE, new AttackRange(1.5F, 4.0F, 1.5F, 6.0F, 0.0F, 0.5F))
        );
    }
}
