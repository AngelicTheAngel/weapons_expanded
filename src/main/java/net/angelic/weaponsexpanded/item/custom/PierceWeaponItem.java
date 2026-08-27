package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwingAnimationType;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.PiercingWeapon;
import net.minecraft.world.item.component.SwingAnimation;
import net.minecraft.world.item.component.Weapon;

import java.util.Optional;

public class PierceWeaponItem extends Item {
    public PierceWeaponItem(ToolMaterial material, float attackDamage, float attackSpeed, Properties settings) {
        super(settings.sword(material, attackDamage, attackSpeed)
                .component(DataComponents.WEAPON, new Weapon(1, 1.0F))
                .component(DataComponents.MINIMUM_ATTACK_CHARGE, 0.9F)
                .component(DataComponents.SWING_ANIMATION, new SwingAnimation(SwingAnimationType.STAB, (int) ((4 + attackSpeed) * 9.0F)))
                .component(DataComponents.PIERCING_WEAPON, new PiercingWeapon(true, false,
                        Optional.of(material == ToolMaterial.WOOD ? SoundEvents.SPEAR_WOOD_ATTACK : SoundEvents.SPEAR_ATTACK),
                        Optional.of(material == ToolMaterial.WOOD ? SoundEvents.SPEAR_WOOD_HIT : SoundEvents.SPEAR_HIT)))
        );
    }
}
