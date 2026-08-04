package net.angelic.weaponsexpanded.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SickleItem extends SwordItem {
    private final float attackDamage;

    private final Multimap<Attribute, AttributeModifier>
            attributeModifiers;

    public SickleItem(
            Tier material,
            float attackDamageBonus,
            float attackSpeed,
            Item.Properties properties
    ) {
        // The vanilla attributes are replaced by our override.
        super(
                material,
                (int) attackDamageBonus,
                attackSpeed,
                properties
        );

        this.attackDamage =
                material.getAttackDamageBonus()
                        + attackDamageBonus;

        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder =
                ImmutableMultimap.builder();

        builder.put(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(
                        BASE_ATTACK_DAMAGE_UUID,
                        "Weapon modifier",
                        this.attackDamage,
                        AttributeModifier.Operation.ADDITION
                )
        );

        builder.put(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(
                        BASE_ATTACK_SPEED_UUID,
                        "Weapon modifier",
                        attackSpeed,
                        AttributeModifier.Operation.ADDITION
                )
        );

        this.attributeModifiers = builder.build();
    }

    @Override
    public float getDamage() {
        return this.attackDamage;
    }

    @Override
    public Multimap<Attribute, AttributeModifier>
    getDefaultAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return this.attributeModifiers;
        }

        return super.getDefaultAttributeModifiers(slot);
    }
}