package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class BastardSwordItem extends Item {
    private static final String TWO_HANDED_KEY =
            "weaponsexpanded:bastard_sword_two_handed";

    private final ToolMaterial material;
    private final ItemAttributeModifiers oneHandedModifiers;
    private final float twoHandedAttackDamage;
    private final float twoHandedAttackSpeed;

    public BastardSwordItem(
            ToolMaterial material,
            int attackDamage,
            float attackSpeed,
            int twoHandedAttackDamage,
            float twoHandedAttackSpeed,
            Item.Properties properties
    ) {
        super(properties.sword(material, attackDamage, attackSpeed));

        this.material = material;
        this.oneHandedModifiers =
                createModifiers(material, attackDamage, attackSpeed);
        this.twoHandedAttackDamage = twoHandedAttackDamage;
        this.twoHandedAttackSpeed = twoHandedAttackSpeed;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        if (isTwoHanded(stack)) {
            tooltipAdder.accept(
                    Component.translatable(
                            "tooltip.weaponsexpanded.twohandedsword"
                    ).withStyle(ChatFormatting.BLUE)
            );
        }

        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }

    private static ItemAttributeModifiers createModifiers(
            ToolMaterial material,
            float attackDamage,
            float attackSpeed
    ) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_ID,
                                material.attackDamageBonus() + attackDamage,
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND
                ).add(Attributes.ATTACK_SPEED, new AttributeModifier(
                                Item.BASE_ATTACK_SPEED_ID,
                                attackSpeed,
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND).build();
    }

    public float getTwoHandedAttackDamage() {
        return twoHandedAttackDamage;
    }

    public float getTwoHandedAttackSpeed() {
        return twoHandedAttackSpeed;
    }

    public boolean isTwoHanded(ItemStack stack) {
        return stack.getOrDefault(
                DataComponents.CUSTOM_DATA,
                CustomData.EMPTY
        ).copyTag().getBooleanOr(TWO_HANDED_KEY, false);
    }

    public void setTwoHanded(ItemStack stack, boolean twoHanded) {
        CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> {
            if (twoHanded) {
                tag.putBoolean(TWO_HANDED_KEY, true);
            } else {
                tag.remove(TWO_HANDED_KEY);
            }
        });

        stack.set(
                DataComponents.ATTRIBUTE_MODIFIERS,
                twoHanded ? createTwoHandedModifiers(material, twoHandedAttackDamage, twoHandedAttackSpeed) : oneHandedModifiers
        );
    }

    private static ItemAttributeModifiers createTwoHandedModifiers(
            ToolMaterial material,
            float attackDamage,
            float attackSpeed
    ) {
        return createModifiers(
                material,
                attackDamage,
                attackSpeed
        );
    }

    public void toggleTwoHanded(ItemStack stack) {
        setTwoHanded(stack, !isTwoHanded(stack));
    }

    public double getTwoHandedDisplayedAttackDamage() {
        return 1.0D
                + material.attackDamageBonus()
                + twoHandedAttackDamage;
    }

    public double getTwoHandedDisplayedAttackSpeed() {
        return 4.0D + twoHandedAttackSpeed;
    }
}