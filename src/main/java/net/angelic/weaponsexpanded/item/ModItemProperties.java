package net.angelic.weaponsexpanded.item;

import net.angelic.weaponsexpanded.util.tags.ModBlockTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwingAnimationType;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.*;

import java.util.Optional;

public class ModItemProperties extends Item.Properties{
    public static Item.Properties scythe(ToolMaterial material, float attackDamage, float attackSpeed, Item.Properties properties) {
        return properties.sword(material, attackDamage, attackSpeed)
                .component(DataComponents.ATTACK_RANGE, new AttackRange(1.0F, 4.0F, 1.0F, 6.0F, 0.0F, 0.5F))
                .component(DataComponents.SWING_ANIMATION, new SwingAnimation(SwingAnimationType.STAB, (int) ((4 + attackSpeed) * 9.0F)));
    }

    public static Item.Properties glaive(ToolMaterial material, float attackDamage, float attackSpeed, Item.Properties properties) {
        return properties.sword(material, attackDamage, attackSpeed)
                .component(DataComponents.ATTACK_RANGE, new AttackRange(0.5F, 3.5F, 0.5F, 5.5F, 0.0F, 0.5F));
    }

    public static Item.Properties greatsword(ToolMaterial material, float attackDamage, float attackSpeed, Item.Properties properties) {
        return properties.sword(material, attackDamage, attackSpeed)
                .component(DataComponents.ATTACK_RANGE, new AttackRange(0.25F, 3.25F, 0.25F, 5.25F, 0.0F, 0.5F));
    }

    public static Item.Properties blunt(ToolMaterial material, float attackDamage, float attackSpeed, Item.Properties properties) {
        return properties.tool(material, ModBlockTags.BLUNT_MINEABLE, attackDamage, attackSpeed, 6.0F)
            .component(DataComponents.WEAPON, new Weapon(1));
    }

    public static Item.Properties pierce(ToolMaterial material, float attackDamage, float attackSpeed, Item.Properties properties) {
    return properties.sword(material, attackDamage, attackSpeed)
            .component(DataComponents.WEAPON, new Weapon(1, 1.0F))
            .component(DataComponents.MINIMUM_ATTACK_CHARGE, 0.9F)
            .component(DataComponents.SWING_ANIMATION, new SwingAnimation(SwingAnimationType.STAB, (int) ((4 + attackSpeed) * 9.0F)))
            .component(DataComponents.PIERCING_WEAPON, new PiercingWeapon(true, false,
                    Optional.of(material == ToolMaterial.WOOD ? SoundEvents.SPEAR_WOOD_ATTACK : SoundEvents.SPEAR_ATTACK),
                    Optional.of(material == ToolMaterial.WOOD ? SoundEvents.SPEAR_WOOD_HIT : SoundEvents.SPEAR_HIT)));
    }
}