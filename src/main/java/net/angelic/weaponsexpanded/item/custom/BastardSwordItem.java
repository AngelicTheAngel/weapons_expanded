package net.angelic.weaponsexpanded.item.custom;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BastardSwordItem extends SwordItem {
    private static final String TWO_HANDED_KEY =
            "weaponsexpanded:bastard_sword_two_handed";

    private static final String ATTRIBUTE_MODIFIERS_KEY =
            "AttributeModifiers";

    private static final String MODIFIER_NAME =
            "Weapon modifier";

    private final ToolMaterial material;

    private final float twoHandedAttackDamage;
    private final float twoHandedAttackSpeed;

    public BastardSwordItem(
            ToolMaterial material,
            int attackDamage,
            float attackSpeed,
            int twoHandedAttackDamage,
            float twoHandedAttackSpeed,
            FabricItemSettings settings
    ) {
        super(
                material,
                attackDamage,
                attackSpeed,
                settings
        );

        this.material = material;
        this.twoHandedAttackDamage = twoHandedAttackDamage;
        this.twoHandedAttackSpeed = twoHandedAttackSpeed;
    }

    @Override
    public void appendTooltip(
            ItemStack stack,
            @Nullable World world,
            List<Text> tooltip,
            TooltipContext context
    ) {
        super.appendTooltip(stack, world, tooltip, context);

        if (isTwoHanded(stack)) {
            tooltip.add(
                    Text.translatable(
                            "tooltip.weaponsexpanded.twohandedsword"
                    ).formatted(Formatting.BLUE)
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
        NbtCompound nbt = stack.getNbt();

        return nbt != null && nbt.getBoolean(TWO_HANDED_KEY);
    }

    public void setTwoHanded(
            ItemStack stack,
            boolean twoHanded
    ) {
        /*
         * Remove the custom AttributeModifiers list first.
         *
         * When this list is absent, ItemStack falls back to the
         * normal modifiers supplied by SwordItem.
         */
        stack.removeSubNbt(ATTRIBUTE_MODIFIERS_KEY);

        if (twoHanded) {
            stack.getOrCreateNbt().putBoolean(
                    TWO_HANDED_KEY,
                    true
            );

            applyTwoHandedModifiers(stack);
        } else {
            NbtCompound nbt = stack.getNbt();

            if (nbt != null) {
                nbt.remove(TWO_HANDED_KEY);

                if (nbt.isEmpty()) {
                    stack.setNbt(null);
                }
            }
        }
    }

    private void applyTwoHandedModifiers(ItemStack stack) {
        double damageModifier =
                material.getAttackDamage()
                        + twoHandedAttackDamage;

        stack.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(
                        ATTACK_DAMAGE_MODIFIER_ID,
                        MODIFIER_NAME,
                        damageModifier,
                        EntityAttributeModifier.Operation.ADDITION
                ),
                EquipmentSlot.MAINHAND
        );

        stack.addAttributeModifier(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(
                        ATTACK_SPEED_MODIFIER_ID,
                        MODIFIER_NAME,
                        twoHandedAttackSpeed,
                        EntityAttributeModifier.Operation.ADDITION
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
                + material.getAttackDamage()
                + twoHandedAttackDamage;
    }

    /**
     * The displayed speed includes the player's base 4.0 speed.
     */
    public double getTwoHandedDisplayedAttackSpeed() {
        return 4.0D + twoHandedAttackSpeed;
    }
}