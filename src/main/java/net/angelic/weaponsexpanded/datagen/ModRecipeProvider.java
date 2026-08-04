package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.util.ModItemTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.data.recipes.RecipeCategory;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {

    private Consumer<FinishedRecipe> recipeExporter;

    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    public void offerBroadswordRecipe(Item output, TagKey<Item> input) {
        if (input == ItemTags.PLANKS) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("  M")
                    .pattern(" M ")
                    .pattern("S  ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("  M")
                    .pattern(" M ")
                    .pattern("S  ")
                    .group(getItemName(output))
                    .unlockedBy("has_" + input.location().getPath(), has(input))
                    .save(this.recipeExporter);
        }
    }

    public void offerSickleRecipe(Item output, TagKey<Item> input) {
        if (input == ItemTags.PLANKS) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern(" M ")
                    .pattern("  M")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern(" M ")
                    .pattern("  M")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy("has_" + input.location().getPath(), has(input))
                    .save(this.recipeExporter);
        }
    }

    public void offerScytheRecipe(Item output, TagKey<Item> input) {
        if (input == ItemTags.PLANKS) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("MMM")
                    .pattern("  S")
                    .pattern("  S")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("MMM")
                    .pattern("  S")
                    .pattern("  S")
                    .group(getItemName(output))
                    .unlockedBy("has_" + input.location().getPath(), has(input))
                    .save(this.recipeExporter);
        }
    }

    public void offerLongswordRecipe(Item output, TagKey<Item> input) {
        if (input == ItemTags.PLANKS) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern(" M ")
                    .pattern(" M ")
                    .pattern("MSM")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern(" M ")
                    .pattern(" M ")
                    .pattern("MSM")
                    .group(getItemName(output))
                    .unlockedBy("has_" + input.location().getPath(), has(input))
                    .save(this.recipeExporter);
        }
    }

    public void offerKatanaRecipe(Item output, TagKey<Item> input) {
        if (input == ItemTags.PLANKS) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("  M")
                    .pattern(" M ")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("  M")
                    .pattern(" M ")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy("has_" + input.location().getPath(), has(input))
                    .save(this.recipeExporter);
        }
    }

    public void offerGreatswordRecipe(Item output, TagKey<Item> input) {
        if (input == ItemTags.PLANKS) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("  M")
                    .pattern("MM ")
                    .pattern("SM ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("  M")
                    .pattern("MM ")
                    .pattern("SM ")
                    .group(getItemName(output))
                    .unlockedBy("has_" + input.location().getPath(), has(input))
                    .save(this.recipeExporter);
        }
    }

    public void offerHatchetRecipe(Item output, TagKey<Item> input) {
        if (input == ItemTags.PLANKS) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern(" M ")
                    .pattern("MS ")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern(" M ")
                    .pattern("MS ")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy("has_" + input.location().getPath(), has(input))
                    .save(this.recipeExporter);
        }
    }

    public void offerHammerRecipe(Item output, TagKey<Item> input) {
        if (input == ItemTags.PLANKS) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("MSM")
                    .pattern("MSM")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("MSM")
                    .pattern("MSM")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy("has_" + input.location().getPath(), has(input))
                    .save(this.recipeExporter);
        }
    }

    public void offerBattleaxeRecipe(Item output, TagKey<Item> input) {
        if (input == ItemTags.PLANKS) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("MM ")
                    .pattern("MSM")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("MM ")
                    .pattern("MSM")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy("has_" + input.location().getPath(), has(input))
                    .save(this.recipeExporter);
        }
    }

    public void offerWarhammerRecipe(Item output, TagKey<Item> input) {
        if (input == ItemTags.PLANKS) {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("MMM")
                    .pattern("MS ")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("MMM")
                    .pattern("MS ")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy("has_" + input.location().getPath(), has(input))
                    .save(this.recipeExporter);
        }
    }

    private void offerSmeltingNuggetRecipes(
            String materialName,
            List<Item> inputs,
            Item result,
            float xp,
            int cookTime
    ) {
        for (Item input : inputs) {
            String inputIdPath = getItemName(input);

            SimpleCookingRecipeBuilder.smelting(
                            Ingredient.of(input),
                            RecipeCategory.MISC,
                            result,
                            xp,
                            cookTime
                    )
                    .group(materialName + "nugget")
                    .unlockedBy(getHasName(input), has(input))
                    .save(
                            recipeExporter,
                            new ResourceLocation(
                                    WeaponsExpanded.MOD_ID,
                                    "smelting/"
                                            + materialName
                                            + "_nugget_from_"
                                            + inputIdPath
                            )
                    );
        }
    }

    private void offerBlastingNuggetRecipes(
            String materialName,
            List<Item> inputs,
            Item result,
            float xp,
            int cookTime
    ) {
        for (Item input : inputs) {
            String inputIdPath = getItemName(input);

            SimpleCookingRecipeBuilder.blasting(
                            Ingredient.of(input),
                            RecipeCategory.MISC,
                            result,
                            xp,
                            cookTime
                    )
                    .group(materialName + "nugget")
                    .unlockedBy(getHasName(input), has(input))
                    .save(
                            recipeExporter,
                            new ResourceLocation(
                                    WeaponsExpanded.MOD_ID,
                                    "blasting/"
                                            + materialName
                                            + "_nugget_from_"
                                            + inputIdPath
                            )
                    );
        }
    }

    private List<Item> goldItems() {
        return List.of(
                ModItems.GOLDEN_BROADSWORD.get(),
                ModItems.GOLDEN_SICKLE.get(),
                ModItems.GOLDEN_SCYTHE.get(),
                ModItems.GOLDEN_LONGSWORD.get(),
                ModItems.GOLDEN_KATANA.get(),
                ModItems.GOLDEN_HATCHET.get(),
                ModItems.GOLDEN_HAMMER.get(),
                ModItems.GOLDEN_BATTLEAXE.get(),
                ModItems.GOLDEN_GREATSWORD.get()
        );
    }

    private List<Item> ironItems() {
        return List.of(
                ModItems.IRON_BROADSWORD.get(),
                ModItems.IRON_SICKLE.get(),
                ModItems.IRON_SCYTHE.get(),
                ModItems.IRON_LONGSWORD.get(),
                ModItems.IRON_KATANA.get(),
                ModItems.IRON_HATCHET.get(),
                ModItems.IRON_HAMMER.get(),
                ModItems.IRON_BATTLEAXE.get(),
                ModItems.IRON_GREATSWORD.get()
        );
    }

    private void offerNetheriteUpgradeRecipe(
            Consumer<FinishedRecipe> exporter,
            Item input,
            RecipeCategory category,
            Item result
    ) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(
                                Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE
                        ),
                        Ingredient.of(input),
                        Ingredient.of(Items.NETHERITE_INGOT),
                        category,
                        result
                )
                .unlocks(
                        getHasName(Items.NETHERITE_INGOT),
                        has(Items.NETHERITE_INGOT)
                )
                .save(
                        exporter,
                        new ResourceLocation(
                                WeaponsExpanded.MOD_ID,
                                getItemName(result) + "_smithing"
                        )
                );
    }

    @Override
    protected void buildRecipes(
            @NotNull Consumer<FinishedRecipe> exporter
    ) {
        float xp = 0.1f;

        int smeltTime = 200;
        int blastTime = 100;

        recipeExporter = exporter;

        // Gold weapons -> gold nugget
        offerSmeltingNuggetRecipes(
                "gold",
                goldItems(),
                Items.GOLD_NUGGET,
                xp,
                smeltTime
        );

        offerBlastingNuggetRecipes(
                "gold",
                goldItems(),
                Items.GOLD_NUGGET,
                xp,
                blastTime
        );

        // Iron weapons -> iron nugget
        offerSmeltingNuggetRecipes(
                "iron",
                ironItems(),
                Items.IRON_NUGGET,
                xp,
                smeltTime
        );

        offerBlastingNuggetRecipes(
                "iron",
                ironItems(),
                Items.IRON_NUGGET,
                xp,
                blastTime
        );

        offerBroadswordRecipe(ModItems.WOODEN_BROADSWORD.get(), ItemTags.PLANKS);
        offerBroadswordRecipe(ModItems.STONE_BROADSWORD.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerBroadswordRecipe(ModItems.GOLDEN_BROADSWORD.get(), ModItemTags.GOLD_TOOL_MATERIALS);
        offerBroadswordRecipe(ModItems.IRON_BROADSWORD.get(), ModItemTags.IRON_TOOL_MATERIALS);
        offerBroadswordRecipe(ModItems.DIAMOND_BROADSWORD.get(), ModItemTags.DIAMOND_TOOL_MATERIALS);

        offerSickleRecipe(ModItems.WOODEN_SICKLE.get(), ItemTags.PLANKS);
        offerSickleRecipe(ModItems.STONE_SICKLE.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerSickleRecipe(ModItems.GOLDEN_SICKLE.get(), ModItemTags.GOLD_TOOL_MATERIALS);
        offerSickleRecipe(ModItems.IRON_SICKLE.get(), ModItemTags.IRON_TOOL_MATERIALS);
        offerSickleRecipe(ModItems.DIAMOND_SICKLE.get(), ModItemTags.DIAMOND_TOOL_MATERIALS);

        offerScytheRecipe(ModItems.WOODEN_SCYTHE.get(), ItemTags.PLANKS);
        offerScytheRecipe(ModItems.STONE_SCYTHE.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerScytheRecipe(ModItems.GOLDEN_SCYTHE.get(), ModItemTags.GOLD_TOOL_MATERIALS);
        offerScytheRecipe(ModItems.IRON_SCYTHE.get(), ModItemTags.IRON_TOOL_MATERIALS);
        offerScytheRecipe(ModItems.DIAMOND_SCYTHE.get(), ModItemTags.DIAMOND_TOOL_MATERIALS);

        offerLongswordRecipe(ModItems.WOODEN_LONGSWORD.get(), ItemTags.PLANKS);
        offerLongswordRecipe(ModItems.STONE_LONGSWORD.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerLongswordRecipe(ModItems.GOLDEN_LONGSWORD.get(), ModItemTags.GOLD_TOOL_MATERIALS);
        offerLongswordRecipe(ModItems.IRON_LONGSWORD.get(), ModItemTags.IRON_TOOL_MATERIALS);
        offerLongswordRecipe(ModItems.DIAMOND_LONGSWORD.get(), ModItemTags.DIAMOND_TOOL_MATERIALS);

        offerKatanaRecipe(ModItems.WOODEN_KATANA.get(), ItemTags.PLANKS);
        offerKatanaRecipe(ModItems.STONE_KATANA.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerKatanaRecipe(ModItems.GOLDEN_KATANA.get(), ModItemTags.GOLD_TOOL_MATERIALS);
        offerKatanaRecipe(ModItems.IRON_KATANA.get(), ModItemTags.IRON_TOOL_MATERIALS);
        offerKatanaRecipe(ModItems.DIAMOND_KATANA.get(), ModItemTags.DIAMOND_TOOL_MATERIALS);

        offerGreatswordRecipe(ModItems.WOODEN_GREATSWORD.get(), ItemTags.PLANKS);
        offerGreatswordRecipe(ModItems.STONE_GREATSWORD.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerGreatswordRecipe(ModItems.GOLDEN_GREATSWORD.get(), ModItemTags.GOLD_TOOL_MATERIALS);
        offerGreatswordRecipe(ModItems.IRON_GREATSWORD.get(), ModItemTags.IRON_TOOL_MATERIALS);
        offerGreatswordRecipe(ModItems.DIAMOND_GREATSWORD.get(), ModItemTags.DIAMOND_TOOL_MATERIALS);

        offerHatchetRecipe(ModItems.WOODEN_HATCHET.get(), ItemTags.PLANKS);
        offerHatchetRecipe(ModItems.STONE_HATCHET.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerHatchetRecipe(ModItems.GOLDEN_HATCHET.get(), ModItemTags.GOLD_TOOL_MATERIALS);
        offerHatchetRecipe(ModItems.IRON_HATCHET.get(), ModItemTags.IRON_TOOL_MATERIALS);
        offerHatchetRecipe(ModItems.DIAMOND_HATCHET.get(), ModItemTags.DIAMOND_TOOL_MATERIALS);

        offerHammerRecipe(ModItems.WOODEN_HAMMER.get(), ItemTags.PLANKS);
        offerHammerRecipe(ModItems.STONE_HAMMER.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerHammerRecipe(ModItems.GOLDEN_HAMMER.get(), ModItemTags.GOLD_TOOL_MATERIALS);
        offerHammerRecipe(ModItems.IRON_HAMMER.get(), ModItemTags.IRON_TOOL_MATERIALS);
        offerHammerRecipe(ModItems.DIAMOND_HAMMER.get(), ModItemTags.DIAMOND_TOOL_MATERIALS);

        offerBattleaxeRecipe(ModItems.WOODEN_BATTLEAXE.get(), ItemTags.PLANKS);
        offerBattleaxeRecipe(ModItems.STONE_BATTLEAXE.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerBattleaxeRecipe(ModItems.GOLDEN_BATTLEAXE.get(), ModItemTags.GOLD_TOOL_MATERIALS);
        offerBattleaxeRecipe(ModItems.IRON_BATTLEAXE.get(), ModItemTags.IRON_TOOL_MATERIALS);
        offerBattleaxeRecipe(ModItems.DIAMOND_BATTLEAXE.get(), ModItemTags.DIAMOND_TOOL_MATERIALS);

        offerWarhammerRecipe(ModItems.WOODEN_WARHAMMER.get(), ItemTags.PLANKS);
        offerWarhammerRecipe(ModItems.STONE_WARHAMMER.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerWarhammerRecipe(ModItems.GOLDEN_WARHAMMER.get(), ModItemTags.GOLD_TOOL_MATERIALS);
        offerWarhammerRecipe(ModItems.IRON_WARHAMMER.get(), ModItemTags.IRON_TOOL_MATERIALS);
        offerWarhammerRecipe(ModItems.DIAMOND_WARHAMMER.get(), ModItemTags.DIAMOND_TOOL_MATERIALS);

        offerNetheriteUpgradeRecipe(
                exporter,
                ModItems.DIAMOND_BROADSWORD.get(),
                RecipeCategory.COMBAT,
                ModItems.NETHERITE_BROADSWORD.get()
        );

        offerNetheriteUpgradeRecipe(
                exporter,
                ModItems.DIAMOND_SICKLE.get(),
                RecipeCategory.COMBAT,
                ModItems.NETHERITE_SICKLE.get()
        );

        offerNetheriteUpgradeRecipe(
                exporter,
                ModItems.DIAMOND_SCYTHE.get(),
                RecipeCategory.COMBAT,
                ModItems.NETHERITE_SCYTHE.get()
        );

        offerNetheriteUpgradeRecipe(
                exporter,
                ModItems.DIAMOND_LONGSWORD.get(),
                RecipeCategory.COMBAT,
                ModItems.NETHERITE_LONGSWORD.get()
        );

        offerNetheriteUpgradeRecipe(
                exporter,
                ModItems.DIAMOND_KATANA.get(),
                RecipeCategory.COMBAT,
                ModItems.NETHERITE_KATANA.get()
        );

        offerNetheriteUpgradeRecipe(
                exporter,
                ModItems.DIAMOND_GREATSWORD.get(),
                RecipeCategory.COMBAT,
                ModItems.NETHERITE_GREATSWORD.get()
        );

        offerNetheriteUpgradeRecipe(
                exporter,
                ModItems.DIAMOND_HATCHET.get(),
                RecipeCategory.COMBAT,
                ModItems.NETHERITE_HATCHET.get()
        );

        offerNetheriteUpgradeRecipe(
                exporter,
                ModItems.DIAMOND_HAMMER.get(),
                RecipeCategory.COMBAT,
                ModItems.NETHERITE_HAMMER.get()
        );

        offerNetheriteUpgradeRecipe(
                exporter,
                ModItems.DIAMOND_BATTLEAXE.get(),
                RecipeCategory.COMBAT,
                ModItems.NETHERITE_BATTLEAXE.get()
        );

        offerNetheriteUpgradeRecipe(
                exporter,
                ModItems.DIAMOND_WARHAMMER.get(),
                RecipeCategory.COMBAT,
                ModItems.NETHERITE_WARHAMMER.get()
        );
    }
}