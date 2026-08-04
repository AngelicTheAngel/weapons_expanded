package net.angelic.weaponsexpanded.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WarhammerItem extends SwordItem {
    private static final String SHARP_SIDE_KEY =
            "weaponsexpanded:warhammer_sharp_side";

    private static final String ATTRIBUTE_MODIFIERS_KEY =
            "AttributeModifiers";

    private static final String MODIFIER_NAME =
            "Weapon modifier";

    private final ToolMaterial material;

    private final float bluntSideAttackDamage;
    private final float bluntSideAttackSpeed;

    private final float sharpSideAttackDamage;
    private final float sharpSideAttackSpeed;

    /*
     * These are the default item-level modifiers. ItemStack falls
     * back to these when it has no custom AttributeModifiers NBT.
     */
    private final Multimap<
            EntityAttribute,
            EntityAttributeModifier
            > bluntSideModifiers;

    public WarhammerItem(
            ToolMaterial material,
            float attackDamage,
            float attackSpeed,
            float sharpSideAttackDamage,
            float sharpSideAttackSpeed,
            String modelName,
            Settings settings
    ) {
        /*
         * SwordItem gives the warhammer tool durability, repair
         * behavior, mining behavior, and hit durability loss.
         *
         * The zero values are unused because this class overrides
         * getAttributeModifiers().
         */
        super(material, 0, 0.0F, settings);

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
         * modelName is retained in the constructor so existing item
         * registration calls do not need to change. In 1.20.1, model
         * switching is handled by a model predicate and model JSON.
         */
    }

    private Multimap<
            EntityAttribute,
            EntityAttributeModifier
            > createModifiers(
            float attackDamage,
            float attackSpeed
    ) {
        ImmutableMultimap.Builder<
                EntityAttribute,
                EntityAttributeModifier
                > builder = ImmutableMultimap.builder();

        builder.put(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(
                        ATTACK_DAMAGE_MODIFIER_ID,
                        MODIFIER_NAME,
                        material.getAttackDamage() + attackDamage,
                        EntityAttributeModifier.Operation.ADDITION
                )
        );

        builder.put(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(
                        ATTACK_SPEED_MODIFIER_ID,
                        MODIFIER_NAME,
                        attackSpeed,
                        EntityAttributeModifier.Operation.ADDITION
                )
        );

        return builder.build();
    }

    @Override
    public Multimap<
            EntityAttribute,
            EntityAttributeModifier
            > getAttributeModifiers(EquipmentSlot slot) {
        if (slot == EquipmentSlot.MAINHAND) {
            return bluntSideModifiers;
        }

        return super.getAttributeModifiers(slot);
    }

    @Override
    public void appendTooltip(
            ItemStack stack,
            @Nullable World world,
            List<Text> tooltip,
            TooltipContext context
    ) {
        super.appendTooltip(stack, world, tooltip, context);

        if (isSharpSide(stack)) {
            tooltip.add(
                    Text.translatable(
                            "tooltip.weaponsexpanded.warhammer.sharp_side"
                    ).formatted(Formatting.BLUE)
            );
        } else {
            tooltip.add(
                    Text.translatable(
                            "tooltip.weaponsexpanded.warhammer.blunt_side"
                    ).formatted(Formatting.BLUE)
            );
        }
    }

    @Override
    public boolean postHit(
            ItemStack stack,
            LivingEntity target,
            LivingEntity attacker
    ) {
        /*
         * The modern WeaponComponent disabled blocking on the blunt
         * side. In 1.20.1 this behavior must be implemented manually.
         */
        if (!isSharpSide(stack)
                && target instanceof PlayerEntity player
                && player.isBlocking()) {
            player.disableShield(true);
        }

        return super.postHit(stack, target, attacker);
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
        NbtCompound nbt = stack.getNbt();

        return nbt != null && nbt.getBoolean(SHARP_SIDE_KEY);
    }

    public void setSharpSide(
            ItemStack stack,
            boolean sharpSide
    ) {
        /*
         * Remove the previous stack-specific modifiers.
         *
         * In blunt mode, leaving this tag absent makes ItemStack
         * use getAttributeModifiers(), returning bluntSideModifiers.
         */
        stack.removeSubNbt(ATTRIBUTE_MODIFIERS_KEY);

        if (sharpSide) {
            stack.getOrCreateNbt().putBoolean(
                    SHARP_SIDE_KEY,
                    true
            );

            applySharpSideModifiers(stack);
        } else {
            NbtCompound nbt = stack.getNbt();

            if (nbt != null) {
                nbt.remove(SHARP_SIDE_KEY);

                if (nbt.isEmpty()) {
                    stack.setNbt(null);
                }
            }
        }
    }

    private void applySharpSideModifiers(ItemStack stack) {
        double damageModifier =
                material.getAttackDamage()
                        + sharpSideAttackDamage;

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
                        sharpSideAttackSpeed,
                        EntityAttributeModifier.Operation.ADDITION
                ),
                EquipmentSlot.MAINHAND
        );
    }

    public void toggleSharpSide(ItemStack stack) {
        setSharpSide(stack, !isSharpSide(stack));
    }

    public double getSharpSideDisplayedAttackDamage() {
        return 1.0D
                + material.getAttackDamage()
                + sharpSideAttackDamage;
    }

    public double getSharpSideDisplayedAttackSpeed() {
        return 4.0D + sharpSideAttackSpeed;
    }
}