package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.CustomModelData;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.List;
import java.util.function.Consumer;

public class WarhammerItem extends Item {
    private static final String SHARP_SIDE_KEY =
            "weaponsexpanded:warhammer_sharp_side";

    private final ToolMaterial material;
    private final float bluntSideAttackDamage;
    private final float bluntSideAttackSpeed;
    private final float sharpSideAttackDamage;
    private final float sharpSideAttackSpeed;
    private final ItemAttributeModifiers bluntSideModifiers;

    public WarhammerItem(
            ToolMaterial material,
            float attackDamage,
            float attackSpeed,
            float sharpSideAttackDamage,
            float sharpSideAttackSpeed,
            String modelName,
            Item.Properties properties
    ) {
        super(properties.sword(material, attackDamage, attackSpeed));

        this.material = material;
        this.bluntSideAttackDamage = attackDamage;
        this.bluntSideAttackSpeed = attackSpeed;
        this.sharpSideAttackDamage = sharpSideAttackDamage;
        this.sharpSideAttackSpeed = sharpSideAttackSpeed;
        this.bluntSideModifiers = createModifiers(
                material,
                attackDamage,
                attackSpeed
        );
    }

    private static ItemAttributeModifiers createModifiers(
            ToolMaterial material,
            float attackDamage,
            float attackSpeed
    ) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_ID,
                                material.attackDamageBonus()
                                        + attackDamage,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                Item.BASE_ATTACK_SPEED_ID,
                                attackSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        String translationKey = isSharpSide(stack)
                ? "tooltip.weaponsexpanded.warhammer.sharp_side"
                : "tooltip.weaponsexpanded.warhammer.blunt_side";

        tooltipAdder.accept(
                Component.translatable(translationKey)
                        .withStyle(ChatFormatting.BLUE)
        );
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
    }

    @Override
    public void hurtEnemy(
            ItemStack stack,
            LivingEntity target,
            LivingEntity attacker
    ) {
        if (!isSharpSide(stack)
                && target instanceof Player player
                && player.isBlocking()) {
            player.getSecondsToDisableBlocking();
        }

        super.hurtEnemy(stack, target, attacker);
    }

    public float getBluntSideAttackDamage() {
        return bluntSideAttackDamage;
    }

    public float getBluntSideAttackSpeed() {
        return bluntSideAttackSpeed;
    }

    public float getSharpSideAttackDamage() {
        return sharpSideAttackDamage;
    }

    public float getSharpSideAttackSpeed() {
        return sharpSideAttackSpeed;
    }

    public boolean isSharpSide(ItemStack stack) {
        return stack.getOrDefault(
                DataComponents.CUSTOM_DATA,
                CustomData.EMPTY
        ).copyTag().getBooleanOr(SHARP_SIDE_KEY, false);
    }

    public void setSharpSide(ItemStack stack, boolean sharpSide) {
        CustomData.update(DataComponents.CUSTOM_DATA, stack, tag -> {
            if (sharpSide) {
                tag.putBoolean(SHARP_SIDE_KEY, true);
            } else {
                tag.remove(SHARP_SIDE_KEY);
            }
        });

        stack.set(
                DataComponents.ATTRIBUTE_MODIFIERS,
                sharpSide ? createSharpSideModifiers() : bluntSideModifiers
        );

        if (sharpSide) {
            stack.set(
                    DataComponents.CUSTOM_MODEL_DATA,
                    new CustomModelData(List.of(1.0F), List.of(), List.of(), List.of())
            );
        } else {
            stack.remove(DataComponents.CUSTOM_MODEL_DATA);
        }
    }

    private ItemAttributeModifiers createSharpSideModifiers() {
        return createModifiers(
                material,
                sharpSideAttackDamage,
                sharpSideAttackSpeed
        );
    }

    public void toggleSharpSide(ItemStack stack) {
        setSharpSide(stack, !isSharpSide(stack));
    }

    public double getSharpSideDisplayedAttackDamage() {
        return 1.0D
                + material.attackDamageBonus()
                + sharpSideAttackDamage;
    }

    public double getSharpSideDisplayedAttackSpeed() {
        return 4.0D + sharpSideAttackSpeed;
    }
}