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
import net.minecraft.world.item.component.AttackRange;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;

public class BastardSwordItem extends Item {

    private static final String WEAPONSEXPANDED$TWO_HANDED_KEY = "weaponsexpanded:bastard_sword_two_handed";

    private final ToolMaterial material;

    private final float oneHandedAttackDamage;
    private final float oneHandedAttackSpeed;

    private final float twoHandedAttackDamage;
    private final float twoHandedAttackSpeed;

    private final ItemAttributeModifiers weaponsexpanded$oneHandedModifiers;
    private final ItemAttributeModifiers weaponsexpanded$twoHandedModifiers;

    public BastardSwordItem(
            ToolMaterial material,
            float attackDamage,
            float attackSpeed,
            float twoHandedAttackDamage,
            float twoHandedAttackSpeed,
            Properties settings
    ) {
        super(settings.sword(material, attackDamage, attackSpeed));
        this.material = material;

        this.oneHandedAttackDamage = attackDamage;
        this.oneHandedAttackSpeed = attackSpeed;

        this.twoHandedAttackDamage = twoHandedAttackDamage;
        this.twoHandedAttackSpeed = twoHandedAttackSpeed;

        // Build explicit modifier sets (matching vanilla sword math).
        this.weaponsexpanded$oneHandedModifiers = ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_ID,
                                (double) material.attackDamageBonus() + (double) attackDamage,
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

        this.weaponsexpanded$twoHandedModifiers = ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                Item.BASE_ATTACK_DAMAGE_ID,
                                (double) material.attackDamageBonus() + (double) twoHandedAttackDamage,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                Item.BASE_ATTACK_SPEED_ID,
                                twoHandedAttackSpeed,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND
                )
                .build();
    }

    @Override
    @SuppressWarnings("deprecation")
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        if(isTwoHanded(stack)) {
            textConsumer.accept(Component.translatable("tooltip.weaponsexpanded.twohandedsword").withStyle(ChatFormatting.BLUE));
            super.appendHoverText(stack, context, displayComponent, textConsumer, type);
        }
    }

    public float getTwoHandedAttackDamage() {
        return twoHandedAttackDamage;
    }

    public float getTwoHandedAttackSpeed() {
        return twoHandedAttackSpeed;
    }

    public boolean isTwoHanded(ItemStack stack) {
        CustomData custom = stack.get(DataComponents.CUSTOM_DATA);
        if (custom == null) return false;
        CompoundTag nbt = custom.copyTag();
        return nbt.getBoolean(WEAPONSEXPANDED$TWO_HANDED_KEY).orElse(false);
    }

    public void setTwoHanded(ItemStack stack, boolean twoHanded) {
        CustomData custom = stack.get(DataComponents.CUSTOM_DATA);
        CompoundTag nbt = (custom != null) ? custom.copyTag() : new CompoundTag();

        if (twoHanded) {
            nbt.putBoolean(WEAPONSEXPANDED$TWO_HANDED_KEY, true);
        } else {
            nbt.remove(WEAPONSEXPANDED$TWO_HANDED_KEY);
        }

        if (nbt.isEmpty()) {
            stack.remove(DataComponents.CUSTOM_DATA);
        } else {
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(nbt));
        }

        // Swap modifiers explicitly (don't remove), so we always keep sword-like stats.
        stack.set(
                DataComponents.ATTRIBUTE_MODIFIERS,
                twoHanded ? this.weaponsexpanded$twoHandedModifiers : this.weaponsexpanded$oneHandedModifiers
        );

        stack.set(DataComponents.ATTACK_RANGE,
                twoHanded ? new AttackRange(1.0F, 3.5F, 1.0F, 5.5F, 0.0F, 0.5F)
                        : new AttackRange(0.0F, 3.0F, 0.0F, 5.0F, 0.0F, 0.5F));
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