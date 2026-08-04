package net.angelic.weaponsexpanded.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WarhammerItem extends SwordItem {
    private static final String SHARP_SIDE_KEY =
            "weaponsexpanded:warhammer_sharp_side";

    private static final String ATTRIBUTE_MODIFIERS_KEY =
            "AttributeModifiers";

    private static final String MODIFIER_NAME =
            "Weapon modifier";

    private final Tier material;

    private final float bluntSideAttackDamage;
    private final float bluntSideAttackSpeed;

    private final float sharpSideAttackDamage;
    private final float sharpSideAttackSpeed;

    /*
     * These are the default item-level modifiers. ItemStack falls
     * back to these when it has no custom AttributeModifiers NBT.
     */
    private final Multimap<Attribute, AttributeModifier>
            bluntSideModifiers;

    public WarhammerItem(
            Tier material,
            float attackDamage,
            float attackSpeed,
            float sharpSideAttackDamage,
            float sharpSideAttackSpeed,
            String modelName,
            Item.Properties properties
    ) {
        /*
         * SwordItem supplies tool durability, repair behavior,
         * mining behavior, and durability loss when attacking.
         *
         * The zero values are unused because this class overrides
         * getDefaultAttributeModifiers().
         */
        super(material, 0, 0.0F, properties);

        this.material = material;

        this.bluntSideAttackDamage = attackDamage;
        this.bluntSideAttackSpeed = attackSpeed;

        this.sharpSideAttackDamage = sharpSideAttackDamage;
        this.sharpSideAttackSpeed = sharpSideAttackSpeed;

        this.bluntSideModifiers = createModifiers(
                attackDamage,
                attackSpeed
        );

        /*
         * modelName remains in the constructor so existing item
         * registration calls do not need to change. Model switching
         * is handled by an item property and model JSON.
         */
    }

    private Multimap<Attribute, AttributeModifier> createModifiers(
            float attackDamage,
            float attackSpeed
    ) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder =
                ImmutableMultimap.builder();

        builder.put(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(
                        BASE_ATTACK_DAMAGE_UUID,
                        MODIFIER_NAME,
                        material.getAttackDamageBonus()
                                + attackDamage,
                        AttributeModifier.Operation.ADDITION
                )
        );

        builder.put(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(
                        BASE_ATTACK_SPEED_UUID,
                        MODIFIER_NAME,
                        attackSpeed,
                        AttributeModifier.Operation.ADDITION
                )
        );

        return builder.build();
    }

    @Override
    public Multimap<Attribute, AttributeModifier>
    getDefaultAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return bluntSideModifiers;
        }

        return super.getDefaultAttributeModifiers(slot);
    }

    @Override
    public void appendHoverText(
            ItemStack stack,
            @Nullable Level level,
            List<Component> tooltip,
            TooltipFlag flag
    ) {
        super.appendHoverText(stack, level, tooltip, flag);

        String translationKey = isSharpSide(stack)
                ? "tooltip.weaponsexpanded.warhammer.sharp_side"
                : "tooltip.weaponsexpanded.warhammer.blunt_side";

        tooltip.add(
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
        /*
         * Shield disabling for the blunt side is implemented
         * manually in 1.20.1.
         */
        if (!isSharpSide(stack)
                && target instanceof Player player
                && player.isBlocking()) {
            player.disableShield(true);
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
        CompoundTag tag = stack.getTag();

        return tag != null && tag.getBoolean(SHARP_SIDE_KEY);
    }

    public void setSharpSide(
            ItemStack stack,
            boolean sharpSide
    ) {
        /*
         * Remove the previous stack-specific modifiers.
         *
         * In blunt mode, leaving this tag absent makes ItemStack use
         * getDefaultAttributeModifiers(), returning bluntSideModifiers.
         */
        stack.removeTagKey(ATTRIBUTE_MODIFIERS_KEY);

        if (sharpSide) {
            stack.getOrCreateTag().putBoolean(
                    SHARP_SIDE_KEY,
                    true
            );

            applySharpSideModifiers(stack);
        } else {
            CompoundTag tag = stack.getTag();

            if (tag != null) {
                tag.remove(SHARP_SIDE_KEY);

                if (tag.isEmpty()) {
                    stack.setTag(null);
                }
            }
        }
    }

    private void applySharpSideModifiers(ItemStack stack) {
        double damageModifier =
                material.getAttackDamageBonus()
                        + sharpSideAttackDamage;

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
                        sharpSideAttackSpeed,
                        AttributeModifier.Operation.ADDITION
                ),
                EquipmentSlot.MAINHAND
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