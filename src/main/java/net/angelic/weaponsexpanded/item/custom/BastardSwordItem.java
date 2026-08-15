package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.List;

public class BastardSwordItem extends SwordItem {
    private static final String TWO_HANDED_KEY =
            "weaponsexpanded:bastard_sword_two_handed";

    private static final ResourceLocation TWO_HANDED_DAMAGE_ID =
            ResourceLocation.fromNamespaceAndPath(
                    "weaponsexpanded", "bastard_sword_two_handed_damage");
    private static final ResourceLocation TWO_HANDED_SPEED_ID =
            ResourceLocation.fromNamespaceAndPath(
                    "weaponsexpanded", "bastard_sword_two_handed_speed");

    private final Tier material;
    private final ItemAttributeModifiers oneHandedModifiers;
    private final float twoHandedAttackDamage;
    private final float twoHandedAttackSpeed;

    public BastardSwordItem(
            Tier material,
            int attackDamage,
            float attackSpeed,
            int twoHandedAttackDamage,
            float twoHandedAttackSpeed,
            Item.Properties properties
    ) {
        super(material, properties.attributes(
                SwordItem.createAttributes(material, attackDamage, attackSpeed)
        ));

        this.material = material;
        this.oneHandedModifiers =
                SwordItem.createAttributes(material, attackDamage, attackSpeed);
        this.twoHandedAttackDamage = twoHandedAttackDamage;
        this.twoHandedAttackSpeed = twoHandedAttackSpeed;
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            List<Component> tooltipComponents,
            TooltipFlag tooltipFlag
    ) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        if (isTwoHanded(stack)) {
            tooltipComponents.add(
                    Component.translatable(
                            "tooltip.weaponsexpanded.twohandedsword"
                    ).withStyle(ChatFormatting.BLUE)
            );
        }
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
        ).copyTag().getBoolean(TWO_HANDED_KEY);
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
                twoHanded ? createTwoHandedModifiers() : oneHandedModifiers
        );
    }

    private ItemAttributeModifiers createTwoHandedModifiers() {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                TWO_HANDED_DAMAGE_ID,
                                material.getAttackDamageBonus()
                                        + twoHandedAttackDamage,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                TWO_HANDED_SPEED_ID,
                                twoHandedAttackSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    public void toggleTwoHanded(ItemStack stack) {
        setTwoHanded(stack, !isTwoHanded(stack));
    }

    public double getTwoHandedDisplayedAttackDamage() {
        return 1.0D
                + material.getAttackDamageBonus()
                + twoHandedAttackDamage;
    }

    public double getTwoHandedDisplayedAttackSpeed() {
        return 4.0D + twoHandedAttackSpeed;
    }
}