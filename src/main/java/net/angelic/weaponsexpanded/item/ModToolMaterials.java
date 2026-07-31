package net.angelic.weaponsexpanded.item;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    FAUX_WOOD(0, 59, 1.0F, 0.0F, 15, () -> Ingredient.fromTag(ItemTags.PLANKS)),
    FAUX_STONE(0, 131, 1.0F, 1.0F, 5, () -> Ingredient.fromTag(ItemTags.STONE_TOOL_MATERIALS)),
    FAUX_IRON(0, 250, 1.0F, 2.0F, 14, () -> Ingredient.ofItems(Items.IRON_INGOT)),
    FAUX_DIAMOND(0, 1561, 1.0F, 3.0F, 10, () -> Ingredient.ofItems(Items.DIAMOND)),
    FAUX_GOLD(0, 32, 1.0F, 0.0F, 22, () -> Ingredient.ofItems(Items.GOLD_INGOT)),
    FAUX_NETHERITE(0, 2031, 1.0F, 4.0F, 15, () -> Ingredient.ofItems(Items.NETHERITE_INGOT));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
        return itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}
