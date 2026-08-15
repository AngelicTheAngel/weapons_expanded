package net.angelic.weaponsexpanded.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum ModToolMaterials implements Tier {
    FAUX_WOOD(0, 59, 1.0F, 0.0F, 15, BlockTags.INCORRECT_FOR_WOODEN_TOOL, () -> Ingredient.of(ItemTags.PLANKS)),
    FAUX_STONE(0, 131, 1.0F, 1.0F, 5, BlockTags.INCORRECT_FOR_STONE_TOOL, () -> Ingredient.of(ItemTags.STONE_TOOL_MATERIALS)),
    FAUX_IRON(0, 250, 1.0F, 2.0F, 14, BlockTags.INCORRECT_FOR_IRON_TOOL, () -> Ingredient.of(Items.IRON_INGOT)),
    FAUX_DIAMOND(0, 1561, 1.0F, 3.0F, 10, BlockTags.INCORRECT_FOR_DIAMOND_TOOL, () -> Ingredient.of(Items.DIAMOND)),
    FAUX_GOLD(0, 32, 1.0F, 0.0F, 22, BlockTags.INCORRECT_FOR_GOLD_TOOL, () -> Ingredient.of(Items.GOLD_INGOT)),
    FAUX_NETHERITE(0, 2031, 1.0F, 4.0F, 15, BlockTags.INCORRECT_FOR_NETHERITE_TOOL, () -> Ingredient.of(Items.NETHERITE_INGOT));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final TagKey<Block> incorrectBlocksForDrops;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(
            int miningLevel,
            int itemDurability,
            float miningSpeed,
            float attackDamage,
            int enchantability,
            TagKey<Block> incorrectBlocksForDrops,
            Supplier<Ingredient> repairIngredient
    ) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.incorrectBlocksForDrops =
                incorrectBlocksForDrops;
        this.repairIngredient =
                repairIngredient;
    }

    @Override
    public int getUses() {
        return itemDurability;
    }

    @Override
    public float getSpeed() {
        return miningSpeed;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return incorrectBlocksForDrops;
    }

    public int getMiningLevel() {
        return miningLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}