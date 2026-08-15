package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import java.util.List;

public class WarhammerItem extends SwordItem {
    private static final String SHARP_SIDE_KEY =
            "weaponsexpanded:warhammer_sharp_side";

    private final Tier material;
    private final float bluntSideAttackDamage;
    private final float bluntSideAttackSpeed;
    private final float sharpSideAttackDamage;
    private final float sharpSideAttackSpeed;
    private final ItemAttributeModifiers bluntSideModifiers;

    public WarhammerItem(
            Tier material,
            float attackDamage,
            float attackSpeed,
            float sharpSideAttackDamage,
            float sharpSideAttackSpeed,
            String modelName,
            Item.Properties properties
    ) {
        super(material, properties.attributes(createModifiers(
                material,
                attackDamage,
                attackSpeed
        )));

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
            Tier material,
            float attackDamage,
            float attackSpeed
    ) {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_ID,
                                material.getAttackDamageBonus()
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
    public void appendHoverText(
            ItemStack stack,
            TooltipContext context,
            List<Component> tooltipComponents,
            TooltipFlag tooltipFlag
    ) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        String translationKey = isSharpSide(stack)
                ? "tooltip.weaponsexpanded.warhammer.sharp_side"
                : "tooltip.weaponsexpanded.warhammer.blunt_side";

        tooltipComponents.add(
                Component.translatable(translationKey)
                        .withStyle(ChatFormatting.BLUE)
        );
    }

    @Override
    public boolean hurtEnemy(
            ItemStack stack,
            LivingEntity target,
            LivingEntity attacker
    ) {
        if (!isSharpSide(stack)
                && target instanceof Player player
                && player.isBlocking()) {
            player.disableShield();
        }

        return super.hurtEnemy(stack, target, attacker);
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
        ).copyTag().getBoolean(SHARP_SIDE_KEY);
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
                + material.getAttackDamageBonus()
                + sharpSideAttackDamage;
    }

    public double getSharpSideDisplayedAttackSpeed() {
        return 4.0D + sharpSideAttackSpeed;
    }
}