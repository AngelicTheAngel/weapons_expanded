package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

public class BastardSwordItem extends Item {

    private static final String WEAPONSEXPANDED$TWO_HANDED_KEY = "weaponsexpanded:bastard_sword_two_handed";

    private final ToolMaterial material;

    private final float oneHandedAttackDamage;
    private final float oneHandedAttackSpeed;

    private final float twoHandedAttackDamage;
    private final float twoHandedAttackSpeed;

    private final AttributeModifiersComponent weaponsexpanded$oneHandedModifiers;
    private final AttributeModifiersComponent weaponsexpanded$twoHandedModifiers;

    public BastardSwordItem(
            ToolMaterial material,
            float attackDamage,
            float attackSpeed,
            float twoHandedAttackDamage,
            float twoHandedAttackSpeed,
            Settings settings
    ) {
        super(settings.sword(material, attackDamage, attackSpeed));
        this.material = material;

        this.oneHandedAttackDamage = attackDamage;
        this.oneHandedAttackSpeed = attackSpeed;

        this.twoHandedAttackDamage = twoHandedAttackDamage;
        this.twoHandedAttackSpeed = twoHandedAttackSpeed;

        // Build explicit modifier sets (matching vanilla sword math).
        this.weaponsexpanded$oneHandedModifiers = AttributeModifiersComponent.builder()
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

        this.weaponsexpanded$twoHandedModifiers = AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_MODIFIER_ID,
                                (double) material.attackDamageBonus() + (double) twoHandedAttackDamage,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .add(
                        EntityAttributes.ATTACK_SPEED,
                        new EntityAttributeModifier(
                                Item.BASE_ATTACK_SPEED_MODIFIER_ID,
                                twoHandedAttackSpeed,
                                EntityAttributeModifier.Operation.ADD_VALUE
                        ),
                        AttributeModifierSlot.MAINHAND
                )
                .build();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        if(isTwoHanded(stack)) {
            textConsumer.accept(Text.translatable("tooltip.weaponsexpanded.twohandedsword").formatted(Formatting.BLUE));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    }

    public float getTwoHandedAttackDamage() {
        return twoHandedAttackDamage;
    }

    public float getTwoHandedAttackSpeed() {
        return twoHandedAttackSpeed;
    }

    public boolean isTwoHanded(ItemStack stack) {
        NbtComponent custom = stack.get(DataComponentTypes.CUSTOM_DATA);
        if (custom == null) return false;
        NbtCompound nbt = custom.copyNbt();
        return nbt.getBoolean(WEAPONSEXPANDED$TWO_HANDED_KEY).orElse(false);
    }

    public void setTwoHanded(ItemStack stack, boolean twoHanded) {
        NbtComponent custom = stack.get(DataComponentTypes.CUSTOM_DATA);
        NbtCompound nbt = (custom != null) ? custom.copyNbt() : new NbtCompound();

        if (twoHanded) {
            nbt.putBoolean(WEAPONSEXPANDED$TWO_HANDED_KEY, true);
        } else {
            nbt.remove(WEAPONSEXPANDED$TWO_HANDED_KEY);
        }

        if (nbt.isEmpty()) {
            stack.remove(DataComponentTypes.CUSTOM_DATA);
        } else {
            stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
        }

        // Swap modifiers explicitly (don't remove), so we always keep sword-like stats.
        stack.set(
                DataComponentTypes.ATTRIBUTE_MODIFIERS,
                twoHanded ? this.weaponsexpanded$twoHandedModifiers : this.weaponsexpanded$oneHandedModifiers
        );
    }

    public void toggleTwoHanded(ItemStack stack) {
        boolean next = !isTwoHanded(stack);
        setTwoHanded(stack, next);
    }

    /**
     * Matches vanilla tooltip math:
     * - Attack Damage tooltip shows (1.0 base + damage modifier)
     * - Damage modifier for weapons is (material bonus + item damage value)
     */
    public double getTwoHandedDisplayedAttackDamage() {
        return 1.0D + (double) material.attackDamageBonus() + (double) twoHandedAttackDamage;
    }

    /**
     * Matches vanilla tooltip math:
     * - Attack Speed tooltip shows (4.0 base + speed modifier)
     */
    public double getTwoHandedDisplayedAttackSpeed() {
        return 4.0D + (double) twoHandedAttackSpeed;
    }
}