package net.angelic.weaponsexpanded.item.custom;

import net.angelic.weaponsexpanded.util.ModBlockTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwingAnimationType;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.item.component.Weapon;

public class PierceWeaponItem extends Item {
    public PierceWeaponItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties settings) {
        super(settings.tool(material, ModBlockTags.PIERCE_MINEABLE, attackDamage, attackSpeed, 1.0F)
                .component(DataComponents.WEAPON, new Weapon(1))
                .component(DataComponents.MINIMUM_ATTACK_CHARGE, 0.9F)
                .component(DataComponents.SWING_ANIMATION, new SwingAnimation(SwingAnimationType.STAB, (int) ((4 + attackSpeed) * 9.0F)))
        );
    }
}
