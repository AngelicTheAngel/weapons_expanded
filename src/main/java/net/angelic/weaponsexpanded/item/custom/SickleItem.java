package net.angelic.weaponsexpanded.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class SickleItem extends SwordItem {

    private final float attackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public SickleItem(
            ToolMaterial material,
            float attackDamageBonus,
            float attackSpeed,
            Item.Settings settings
    ) {
        // The vanilla attributes created here are replaced by our override.
        super(material, (int) attackDamageBonus, attackSpeed, settings);

        this.attackDamage = material.getAttackDamage() + attackDamageBonus;

        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder =
                ImmutableMultimap.builder();

        builder.put(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(
                        ATTACK_DAMAGE_MODIFIER_ID,
                        "Weapon modifier",
                        this.attackDamage,
                        EntityAttributeModifier.Operation.ADDITION
                )
        );

        builder.put(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(
                        ATTACK_SPEED_MODIFIER_ID,
                        "Weapon modifier",
                        attackSpeed,
                        EntityAttributeModifier.Operation.ADDITION
                )
        );

        this.attributeModifiers = builder.build();
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(
            EquipmentSlot slot
    ) {
        if (slot == EquipmentSlot.MAINHAND) {
            return this.attributeModifiers;
        }

        return super.getAttributeModifiers(slot);
    }
}