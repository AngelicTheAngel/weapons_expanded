package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BastardSwordItem extends SwordItem {
    private static final String TWO_HANDED_KEY =
            "weaponsexpanded:bastard_sword_two_handed";

    private static final String ATTRIBUTE_MODIFIERS_KEY =
            "AttributeModifiers";

    private static final String MODIFIER_NAME =
            "Weapon modifier";

    private final Tier material;

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
        super(
                material,
                attackDamage,
                attackSpeed,
                properties
        );

        this.material = material;
        this.twoHandedAttackDamage = twoHandedAttackDamage;
        this.twoHandedAttackSpeed = twoHandedAttackSpeed;
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            @Nullable Level level,
            List<Component> tooltip,
            TooltipFlag flag
    ) {
        super.appendHoverText(stack, level, tooltip, flag);

        if (isTwoHanded(stack)) {
            tooltip.add(
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
        CompoundTag tag = stack.getTag();

        return tag != null && tag.getBoolean(TWO_HANDED_KEY);
    }

    public void setTwoHanded(
            ItemStack stack,
            boolean twoHanded
    ) {
        /*
         * Removing this tag makes the stack fall back to the
         * default modifiers supplied by SwordItem.
         */
        stack.removeTagKey(ATTRIBUTE_MODIFIERS_KEY);

        if (twoHanded) {
            stack.getOrCreateTag().putBoolean(
                    TWO_HANDED_KEY,
                    true
            );

            applyTwoHandedModifiers(stack);
        } else {
            CompoundTag tag = stack.getTag();

            if (tag != null) {
                tag.remove(TWO_HANDED_KEY);

                if (tag.isEmpty()) {
                    stack.setTag(null);
                }
            }
        }
    }

    private void applyTwoHandedModifiers(ItemStack stack) {
        double damageModifier =
                material.getAttackDamageBonus()
                        + twoHandedAttackDamage;

        stack.addAttributeModifier(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(
                        BASE_ATTACK_DAMAGE_UUID,
                        MODIFIER_NAME,
                        damageModifier,
                        AttributeModifier.Operation.ADDITION
                ),
                EquipmentSlot.MAINHAND
        );

        stack.addAttributeModifier(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(
                        BASE_ATTACK_SPEED_UUID,
                        MODIFIER_NAME,
                        twoHandedAttackSpeed,
                        AttributeModifier.Operation.ADDITION
                ),
                EquipmentSlot.MAINHAND
        );
    }

    public void toggleTwoHanded(ItemStack stack) {
        setTwoHanded(stack, !isTwoHanded(stack));
    }

    /**
     * The displayed damage includes the player's base 1.0 damage.
     */
    public double getTwoHandedDisplayedAttackDamage() {
        return 1.0D
                + material.getAttackDamageBonus()
                + twoHandedAttackDamage;
    }

    /**
     * The displayed speed includes the player's base 4.0 speed.
     */
    public double getTwoHandedDisplayedAttackSpeed() {
        return 4.0D + twoHandedAttackSpeed;
    }
}