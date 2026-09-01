package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.*;

import java.util.Optional;
import java.util.function.Consumer;

public class HalberdItem extends Item {

    private static final String WEAPONSEXPANDED$HALBERD_PIERCE_KEY = "weaponsexpanded:halberd_pierce";

    private final ItemAttributeModifiers weaponsexpanded$slashModifiers;
    private final ItemAttributeModifiers weaponsexpanded$pierceModifiers;

    public HalberdItem(ToolMaterial material, float attackDamage, float attackSpeed, float pierceAttackDamage, float pierceAttackSpeed, Properties settings) {
        super(settings.sword(material, attackDamage, attackSpeed));

        this.weaponsexpanded$slashModifiers = ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_ID,
                                (double) material.attackDamageBonus() + (double) attackDamage,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                Item.BASE_ATTACK_SPEED_ID,
                                attackSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();

        this.weaponsexpanded$pierceModifiers = ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_ID,
                                (double) material.attackDamageBonus() + (double) pierceAttackDamage,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                Item.BASE_ATTACK_SPEED_ID,
                                pierceAttackSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        if(isPiercing(stack)) {
            textConsumer.accept(Component.translatable("tooltip.weaponsexpanded.halberd_piercing").withStyle(ChatFormatting.BLUE));
            super.appendHoverText(stack, context, displayComponent, textConsumer, type);
        } else {
            textConsumer.accept(Component.translatable("tooltip.weaponsexpanded.halberd_slashing").withStyle(ChatFormatting.BLUE));
            super.appendHoverText(stack, context, displayComponent, textConsumer, type);
        }
    }

    public boolean isPiercing(ItemStack stack) {
        CustomData custom = stack.get(DataComponents.CUSTOM_DATA);
        if (custom == null) return false;
        CompoundTag nbt = custom.copyTag();
        return nbt.getBoolean(WEAPONSEXPANDED$HALBERD_PIERCE_KEY).orElse(false);
    }

    public void setPiercing(ItemStack stack, boolean piercing) {
        CustomData custom = stack.get(DataComponents.CUSTOM_DATA);
        CompoundTag nbt = (custom != null) ? custom.copyTag() : new CompoundTag();

        if (piercing) {
            nbt.putBoolean(WEAPONSEXPANDED$HALBERD_PIERCE_KEY, true);
        } else {
            nbt.remove(WEAPONSEXPANDED$HALBERD_PIERCE_KEY);
        }

        if (nbt.isEmpty()) {
            stack.remove(DataComponents.CUSTOM_DATA);
        } else {
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(nbt));
        }

        stack.set(
                DataComponents.ATTRIBUTE_MODIFIERS,
                piercing ? this.weaponsexpanded$pierceModifiers : this.weaponsexpanded$slashModifiers
        );

        if(piercing) {
            stack.set(DataComponents.MINIMUM_ATTACK_CHARGE, 1.0F);
            stack.set(DataComponents.ATTACK_RANGE, new AttackRange(2.0F, 4.5F, 2.0F, 6.5F, 0.0F, 0.5F));
            stack.set(DataComponents.SWING_ANIMATION, new SwingAnimation(SwingAnimationType.STAB, 23));
            stack.set(DataComponents.PIERCING_WEAPON, new PiercingWeapon(true, false,
                    Optional.of(SoundEvents.SPEAR_ATTACK),
                    Optional.of(SoundEvents.SPEAR_HIT)));
        } else {
            stack.remove(DataComponents.MINIMUM_ATTACK_CHARGE);
            stack.remove(DataComponents.ATTACK_RANGE);
            stack.remove(DataComponents.SWING_ANIMATION);
            stack.remove(DataComponents.PIERCING_WEAPON);
        }
    }

    public void togglePiercing(ItemStack stack) {
        boolean next = !isPiercing(stack);
        setPiercing(stack, next);
    }
}