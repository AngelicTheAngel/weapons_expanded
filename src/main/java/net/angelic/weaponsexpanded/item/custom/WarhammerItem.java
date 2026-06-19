package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

public class WarhammerItem extends Item {

    private static final String WEAPONSEXPANDED$SHARP_SIDE_KEY = "weaponsexpanded:warhammer_sharp_side";

    private final ToolMaterial material;

    private final float bluntSideAttackDamage;
    private final float bluntSideAttackSpeed;

    private final float sharpSideAttackDamage;
    private final float sharpSideAttackSpeed;

    private final AttributeModifiersComponent weaponsexpanded$bluntSideModifiers;
    private final AttributeModifiersComponent weaponsexpanded$sharpSideModifiers;

    public WarhammerItem(
            ToolMaterial material,
            float attackDamage,
            float attackSpeed,
            float sharpSideAttackDamage,
            float sharpSideAttackSpeed,
            Settings settings
    ) {
        super(settings.sword(material, attackDamage, attackSpeed)
                .component(
                        DataComponentTypes.WEAPON,
                        new WeaponComponent(1, WeaponComponent.AXE_DISABLE_BLOCKING_FOR_SECONDS)
                ));
        this.material = material;

        this.bluntSideAttackDamage = attackDamage;
        this.bluntSideAttackSpeed = attackSpeed;

        this.sharpSideAttackDamage = sharpSideAttackDamage;
        this.sharpSideAttackSpeed = sharpSideAttackSpeed;

        // Build explicit modifier sets.
        this.weaponsexpanded$bluntSideModifiers = AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                (double) material.attackDamageBonus() + (double) attackDamage,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.ATTACK_SPEED,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_SPEED_MODIFIER_ID,
                                attackSpeed,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .build();

        this.weaponsexpanded$sharpSideModifiers = AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                (double) material.attackDamageBonus() + (double) sharpSideAttackDamage,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.ATTACK_SPEED,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_SPEED_MODIFIER_ID,
                                sharpSideAttackSpeed,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        if (isSharpSide(stack)) {
            textConsumer.accept(Text.translatable("tooltip.weaponsexpanded.warhammer_sharp_side").formatted(Formatting.BLUE));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        } else {
            textConsumer.accept(Text.translatable("tooltip.weaponsexpanded.warhammer_blunt_side").formatted(Formatting.BLUE));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    }

    public float getSharpSideAttackDamage() {
        return sharpSideAttackDamage;
    }

    public float getSharpSideAttackSpeed() {
        return sharpSideAttackSpeed;
    }

    public boolean isSharpSide(ItemStack stack) {
        NbtComponent custom = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (custom == null) return false;
        NbtCompound nbt = custom.copyNbt();
        return nbt.getBoolean(WEAPONSEXPANDED$SHARP_SIDE_KEY).orElse(false);
    }

    public void setSharpSide(ItemStack stack, boolean sharpSide) {
        NbtComponent custom = stack.get(DataComponentTypes.CUSTOM_DATA);
        NbtCompound nbt = (custom != null) ? custom.copyNbt() : new NbtCompound();

        if (sharpSide) {
            nbt.putBoolean(WEAPONSEXPANDED$SHARP_SIDE_KEY, true);
        } else {
            nbt.remove(WEAPONSEXPANDED$SHARP_SIDE_KEY);
        }

        if (nbt.isEmpty()) {
            stack.remove(DataComponentTypes.CUSTOM_DATA);
        } else {
            stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
        }

        // Swap modifiers explicitly, so we always keep weapon stats.
        stack.set(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                sharpSide ? this.weaponsexpanded$sharpSideModifiers : this.weaponsexpanded$bluntSideModifiers
        );

        stack.set(
                DataComponentTypes.WEAPON,
                sharpSide
                        ? new WeaponComponent(1)
                        : new WeaponComponent(1, WeaponComponent.AXE_DISABLE_BLOCKING_FOR_SECONDS)
        );
    }

    public void toggleSharpSide(ItemStack stack) {
        boolean next = !isSharpSide(stack);
        setSharpSide(stack, next);
    }

    /**
     * Matches vanilla tooltip math:
     * - Attack Damage tooltip shows (1.0 base + damage modifier)
     * - Damage modifier for weapons is (material bonus + item damage value)
     */
    public double getSharpSideDisplayedAttackDamage() {
        return 1.0D + (double) material.attackDamageBonus() + (double) sharpSideAttackDamage;
    }

    /**
     * Matches vanilla tooltip math:
     * - Attack Speed tooltip shows (4.0 base + speed modifier)
     */
    public double getSharpSideDisplayedAttackSpeed() {
        return 4.0D + (double) sharpSideAttackSpeed;
    }
}