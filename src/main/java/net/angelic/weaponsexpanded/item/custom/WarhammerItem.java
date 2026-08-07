package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class WarhammerItem extends SwordItem {

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
        super(material, attackDamage, attackSpeed, settings.component(
                DataComponentTypes.CUSTOM_MODEL_DATA,
                new CustomModelDataComponent(0)
        ));

        this.material = material;

        this.bluntSideAttackDamage = attackDamage;
        this.bluntSideAttackSpeed = attackSpeed;

        this.sharpSideAttackDamage = sharpSideAttackDamage;
        this.sharpSideAttackSpeed = sharpSideAttackSpeed;

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
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean result = super.postHit(stack, target, attacker);

        if (!isSharpSide(stack)
                && target instanceof PlayerEntity player
                && player.isBlocking()) {
            player.disableShield(player.getActiveItem());
        }

        return result;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (isSharpSide(stack)) {
            tooltip.add(Text.translatable("tooltip.weaponsexpanded.warhammer.sharp_side").formatted(Formatting.BLUE));
            super.appendTooltip(stack, context, tooltip, type);
        } else {
            tooltip.add(Text.translatable("tooltip.weaponsexpanded.warhammer.blunt_side").formatted(Formatting.BLUE));
            super.appendTooltip(stack, context, tooltip, type);
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
        if(nbt.getBoolean(WEAPONSEXPANDED$SHARP_SIDE_KEY)) {
            return nbt.getBoolean(WEAPONSEXPANDED$SHARP_SIDE_KEY);
        } else {
            return false;
        }
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
                DataComponentTypes.CUSTOM_MODEL_DATA,
                new CustomModelDataComponent(sharpSide ? 1 : 0)
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