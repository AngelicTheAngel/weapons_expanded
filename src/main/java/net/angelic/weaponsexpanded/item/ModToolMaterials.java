package net.angelic.weaponsexpanded.item;

import com.google.common.base.Suppliers;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.Objects;
import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    FAUX_WOOD(BlockTags.INCORRECT_FOR_WOODEN_TOOL, 59, 1.0F, 0.0F, 15, () -> Ingredient.fromTag(ItemTags.PLANKS)),
    FAUX_STONE(BlockTags.INCORRECT_FOR_STONE_TOOL, 131, 1.0F, 1.0F, 5, () -> Ingredient.fromTag(ItemTags.STONE_TOOL_MATERIALS)),
    FAUX_IRON(BlockTags.INCORRECT_FOR_IRON_TOOL, 250, 1.0F, 2.0F, 14, () -> Ingredient.ofItems(new ItemConvertible[]{Items.IRON_INGOT})),
    FAUX_DIAMOND(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 1561, 1.0F, 3.0F, 10, () -> Ingredient.ofItems(new ItemConvertible[]{Items.DIAMOND})),
    FAUX_GOLD(BlockTags.INCORRECT_FOR_GOLD_TOOL, 32, 1.0F, 0.0F, 22, () -> Ingredient.ofItems(new ItemConvertible[]{Items.GOLD_INGOT})),
    FAUX_NETHERITE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2031, 1.0F, 4.0F, 15, () -> Ingredient.ofItems(new ItemConvertible[]{Items.NETHERITE_INGOT}));

    private final TagKey<Block> inverseTag;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    private ModToolMaterials(final TagKey<Block> inverseTag, final int itemDurability, final float miningSpeed, final float attackDamage, final int enchantability, final Supplier<Ingredient> repairIngredient) {
        this.inverseTag = inverseTag;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        Objects.requireNonNull(repairIngredient);
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public TagKey<Block> getInverseTag() {
        return this.inverseTag;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }
}