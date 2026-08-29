package net.angelic.weaponsexpanded.item.custom;

import java.util.function.Consumer;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.Weapon;

public class WarhammerItem extends Item {

    private static final String WEAPONSEXPANDED$SHARP_SIDE_KEY = "weaponsexpanded:warhammer_sharp_side";

    private final ItemAttributeModifiers weaponsexpanded$bluntSideModifiers;
    private final ItemAttributeModifiers weaponsexpanded$sharpSideModifiers;

    public WarhammerItem(ToolMaterial material, float attackDamage, float attackSpeed, float sharpSideAttackDamage, float sharpSideAttackSpeed, Properties settings) {
        super(settings.sword(material, attackDamage, attackSpeed)
                .component(DataComponents.WEAPON, new Weapon(1, Weapon.AXE_DISABLES_BLOCKING_FOR_SECONDS))
        );

        this.weaponsexpanded$bluntSideModifiers = ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_ID,
                                (double) material.attackDamageBonus() + (double) attackDamage,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(
                                Item.BASE_ATTACK_SPEED_ID,
                                attackSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();

        this.weaponsexpanded$sharpSideModifiers = ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_ID,
                                (double) material.attackDamageBonus() + (double) sharpSideAttackDamage,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(
                                Item.BASE_ATTACK_SPEED_ID,
                                sharpSideAttackSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        if (isSharpSide(stack)) {
            textConsumer.accept(Component.translatable("tooltip.weaponsexpanded.warhammer.sharp_side").withStyle(ChatFormatting.BLUE));
        } else {
            textConsumer.accept(Component.translatable("tooltip.weaponsexpanded.warhammer.blunt_side").withStyle(ChatFormatting.BLUE));
        }

        super.appendHoverText(stack, context, displayComponent, textConsumer, type);
    }

    public boolean isSharpSide(ItemStack stack) {
        CustomData custom = stack.get(DataComponents.CUSTOM_DATA);
        if (custom == null) return false;

        CompoundTag nbt = custom.copyTag();
        return nbt.getBoolean(WEAPONSEXPANDED$SHARP_SIDE_KEY).orElse(false);
    }

    public void setSharpSide(ItemStack stack, boolean sharpSide) {
        CustomData custom = stack.get(DataComponents.CUSTOM_DATA);
        CompoundTag nbt = custom != null ? custom.copyTag() : new CompoundTag();

        if (sharpSide) {
            nbt.putBoolean(WEAPONSEXPANDED$SHARP_SIDE_KEY, true);
        } else {
            nbt.remove(WEAPONSEXPANDED$SHARP_SIDE_KEY);
        }

        if (nbt.isEmpty()) {
            stack.remove(DataComponents.CUSTOM_DATA);
        } else {
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(nbt));
        }

        stack.set(
                DataComponents.ATTRIBUTE_MODIFIERS,
                sharpSide ? this.weaponsexpanded$sharpSideModifiers : this.weaponsexpanded$bluntSideModifiers
        );

        stack.set(
                DataComponents.WEAPON,
                sharpSide ? new Weapon(1) : new Weapon(1, Weapon.AXE_DISABLES_BLOCKING_FOR_SECONDS)
        );
    }

    public void toggleSharpSide(ItemStack stack) {
        boolean next = !isSharpSide(stack);
        setSharpSide(stack, next);
    }
}