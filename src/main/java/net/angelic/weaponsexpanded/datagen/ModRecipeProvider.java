package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {

    private Consumer<RecipeJsonProvider> recipeExporter;

    public ModRecipeProvider(FabricDataOutput output, Consumer<RecipeJsonProvider> exporter) {
        super(output);
    }
            public void offerBroadswordRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.PLANKS) {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("  M")
                            .pattern(" M ")
                            .pattern("S  ")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.recipeExporter);
                } else {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("  M")
                            .pattern(" M ")
                            .pattern("S  ")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.recipeExporter);
                }
            }

            public void offerSickleRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.PLANKS) {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern(" M ")
                            .pattern("  M")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.recipeExporter);
                } else {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern(" M ")
                            .pattern("  M")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.recipeExporter);
                }
            }

            public void offerScytheRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.PLANKS) {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("MMM")
                            .pattern("  S")
                            .pattern("  S")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.recipeExporter);
                } else {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("MMM")
                            .pattern("  S")
                            .pattern("  S")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.recipeExporter);
                }
            }

            public void offerLongswordRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.PLANKS) {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern(" M ")
                            .pattern(" M ")
                            .pattern("MSM")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.recipeExporter);
                } else {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern(" M ")
                            .pattern(" M ")
                            .pattern("MSM")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.recipeExporter);
                }
            }

            public void offerKatanaRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.PLANKS) {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("  M")
                            .pattern(" M ")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.recipeExporter);
                } else {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("  M")
                            .pattern(" M ")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.recipeExporter);
                }
            }

            public void offerGreatswordRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.PLANKS) {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("  M")
                            .pattern("MM ")
                            .pattern("SM ")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.recipeExporter);
                } else {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("  M")
                            .pattern("MM ")
                            .pattern("SM ")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.recipeExporter);
                }
            }

            public void offerHatchetRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.PLANKS) {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern(" M ")
                            .pattern("MS ")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.recipeExporter);
                } else {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern(" M ")
                            .pattern("MS ")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.recipeExporter);
                }
            }

            public void offerHammerRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.PLANKS) {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("MSM")
                            .pattern("MSM")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.recipeExporter);
                } else {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("MSM")
                            .pattern("MSM")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.recipeExporter);
                }
            }

            public void offerBattleaxeRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.PLANKS) {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("MM ")
                            .pattern("MSM")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.recipeExporter);
                } else {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("MM ")
                            .pattern("MSM")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.recipeExporter);
                }
            }

            public void offerWarhammerRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.PLANKS) {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("MMM")
                            .pattern("MS ")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.recipeExporter);
                } else {
                    new ShapedRecipeJsonBuilder(RecipeCategory.COMBAT, output, 1)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("MMM")
                            .pattern("MS ")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.recipeExporter);
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
                    String inputIdPath = getRecipeName(input);

                    CookingRecipeJsonBuilder.createSmelting(
                                    Ingredient.ofItems(input),
                                    RecipeCategory.MISC,
                                    result,
                                    xp,
                                    cookTime
                            )
                            .group(materialName + "nugget")
                            .criterion(hasItem(input), conditionsFromItem(input))
                            .offerTo(recipeExporter, "smelting/" + materialName + "_nugget_from_" + inputIdPath);
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
                    String inputIdPath = getRecipeName(input);

                    CookingRecipeJsonBuilder.createBlasting(
                                    Ingredient.ofItems(input),
                                    RecipeCategory.MISC,
                                    result,
                                    xp,
                                    cookTime
                            )
                            .group(materialName + "nugget")
                            .criterion(hasItem(input), conditionsFromItem(input))
                            .offerTo(recipeExporter, "blasting/" + materialName + "_nugget_from_" + inputIdPath);
                }
            }

            private List<Item> goldItems() {
                return List.of(
                        ModItems.GOLDEN_BROADSWORD,
                        ModItems.GOLDEN_SICKLE,
                        ModItems.GOLDEN_SCYTHE,
                        ModItems.GOLDEN_LONGSWORD,
                        ModItems.GOLDEN_KATANA,
                        ModItems.GOLDEN_HATCHET,
                        ModItems.GOLDEN_HAMMER,
                        ModItems.GOLDEN_BATTLEAXE,
                        ModItems.GOLDEN_GREATSWORD
                );
            }

            private List<Item> ironItems() {
                return List.of(
                        ModItems.IRON_BROADSWORD,
                        ModItems.IRON_SICKLE,
                        ModItems.IRON_SCYTHE,
                        ModItems.IRON_LONGSWORD,
                        ModItems.IRON_KATANA,
                        ModItems.IRON_HATCHET,
                        ModItems.IRON_HAMMER,
                        ModItems.IRON_BATTLEAXE,
                        ModItems.IRON_GREATSWORD
                );
            }
    @Override
    public String getName() {
        return "WeaponsExpanded Recipes";
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        float xp = 0.1f;

        int smeltTime = 200;
        int blastTime = 100;

        recipeExporter = exporter;

        // Gold weapons -> gold nugget
        offerSmeltingNuggetRecipes("gold", goldItems(), Items.GOLD_NUGGET, xp, smeltTime);
        offerBlastingNuggetRecipes("gold", goldItems(), Items.GOLD_NUGGET, xp, blastTime);

        // Iron weapons -> iron nugget
        offerSmeltingNuggetRecipes("iron", ironItems(), Items.IRON_NUGGET, xp, smeltTime);
        offerBlastingNuggetRecipes("iron", ironItems(), Items.IRON_NUGGET, xp, blastTime);

        offerBroadswordRecipe(ModItems.WOODEN_BROADSWORD, ItemTags.PLANKS);
        offerBroadswordRecipe(ModItems.STONE_BROADSWORD, ItemTags.STONE_TOOL_MATERIALS);
        offerBroadswordRecipe(ModItems.GOLDEN_BROADSWORD, Items.GOLD_INGOT);
        offerBroadswordRecipe(ModItems.IRON_BROADSWORD, Items.IRON_TOOL_MATERIALS);
        offerBroadswordRecipe(ModItems.DIAMOND_BROADSWORD, Items.DIAMOND_TOOL_MATERIALS);

        offerSickleRecipe(ModItems.WOODEN_SICKLE, ItemTags.PLANKS);
        offerSickleRecipe(ModItems.STONE_SICKLE, ItemTags.STONE_TOOL_MATERIALS);
        offerSickleRecipe(ModItems.GOLDEN_SICKLE, ItemTags.GOLD_TOOL_MATERIALS);
        offerSickleRecipe(ModItems.IRON_SICKLE, ItemTags.IRON_TOOL_MATERIALS);
        offerSickleRecipe(ModItems.DIAMOND_SICKLE, ItemTags.DIAMOND_TOOL_MATERIALS);

        offerScytheRecipe(ModItems.WOODEN_SCYTHE, ItemTags.PLANKS);
        offerScytheRecipe(ModItems.STONE_SCYTHE, ItemTags.STONE_TOOL_MATERIALS);
        offerScytheRecipe(ModItems.GOLDEN_SCYTHE, ItemTags.GOLD_TOOL_MATERIALS);
        offerScytheRecipe(ModItems.IRON_SCYTHE, ItemTags.IRON_TOOL_MATERIALS);
        offerScytheRecipe(ModItems.DIAMOND_SCYTHE, ItemTags.DIAMOND_TOOL_MATERIALS);

        offerLongswordRecipe(ModItems.WOODEN_LONGSWORD, ItemTags.PLANKS);
        offerLongswordRecipe(ModItems.STONE_LONGSWORD, ItemTags.STONE_TOOL_MATERIALS);
        offerLongswordRecipe(ModItems.GOLDEN_LONGSWORD, ItemTags.GOLD_TOOL_MATERIALS);
        offerLongswordRecipe(ModItems.IRON_LONGSWORD, ItemTags.IRON_TOOL_MATERIALS);
        offerLongswordRecipe(ModItems.DIAMOND_LONGSWORD, ItemTags.DIAMOND_TOOL_MATERIALS);

        offerKatanaRecipe(ModItems.WOODEN_KATANA, ItemTags.PLANKS);
        offerKatanaRecipe(ModItems.STONE_KATANA, ItemTags.STONE_TOOL_MATERIALS);
        offerKatanaRecipe(ModItems.GOLDEN_KATANA, ItemTags.GOLD_TOOL_MATERIALS);
        offerKatanaRecipe(ModItems.IRON_KATANA, ItemTags.IRON_TOOL_MATERIALS);
        offerKatanaRecipe(ModItems.DIAMOND_KATANA, ItemTags.DIAMOND_TOOL_MATERIALS);

        offerGreatswordRecipe(ModItems.WOODEN_GREATSWORD, ItemTags.PLANKS);
        offerGreatswordRecipe(ModItems.STONE_GREATSWORD, ItemTags.STONE_TOOL_MATERIALS);
        offerGreatswordRecipe(ModItems.GOLDEN_GREATSWORD, ItemTags.GOLD_TOOL_MATERIALS);
        offerGreatswordRecipe(ModItems.IRON_GREATSWORD, ItemTags.IRON_TOOL_MATERIALS);
        offerGreatswordRecipe(ModItems.DIAMOND_GREATSWORD, ItemTags.DIAMOND_TOOL_MATERIALS);

        offerHatchetRecipe(ModItems.WOODEN_HATCHET, ItemTags.PLANKS);
        offerHatchetRecipe(ModItems.STONE_HATCHET, ItemTags.STONE_TOOL_MATERIALS);
        offerHatchetRecipe(ModItems.GOLDEN_HATCHET, ItemTags.GOLD_TOOL_MATERIALS);
        offerHatchetRecipe(ModItems.IRON_HATCHET, ItemTags.IRON_TOOL_MATERIALS);
        offerHatchetRecipe(ModItems.DIAMOND_HATCHET, ItemTags.DIAMOND_TOOL_MATERIALS);

        offerHammerRecipe(ModItems.WOODEN_HAMMER, ItemTags.PLANKS);
        offerHammerRecipe(ModItems.STONE_HAMMER, ItemTags.STONE_TOOL_MATERIALS);
        offerHammerRecipe(ModItems.GOLDEN_HAMMER, ItemTags.GOLD_TOOL_MATERIALS);
        offerHammerRecipe(ModItems.IRON_HAMMER, ItemTags.IRON_TOOL_MATERIALS);
        offerHammerRecipe(ModItems.DIAMOND_HAMMER, ItemTags.DIAMOND_TOOL_MATERIALS);

        offerBattleaxeRecipe(ModItems.WOODEN_BATTLEAXE, ItemTags.PLANKS);
        offerBattleaxeRecipe(ModItems.STONE_BATTLEAXE, ItemTags.STONE_TOOL_MATERIALS);
        offerBattleaxeRecipe(ModItems.GOLDEN_BATTLEAXE, ItemTags.GOLD_TOOL_MATERIALS);
        offerBattleaxeRecipe(ModItems.IRON_BATTLEAXE, ItemTags.IRON_TOOL_MATERIALS);
        offerBattleaxeRecipe(ModItems.DIAMOND_BATTLEAXE, ItemTags.DIAMOND_TOOL_MATERIALS);

        offerWarhammerRecipe(ModItems.WOODEN_WARHAMMER, ItemTags.PLANKS);
        offerWarhammerRecipe(ModItems.STONE_WARHAMMER, ItemTags.STONE_TOOL_MATERIALS);
        offerWarhammerRecipe(ModItems.GOLDEN_WARHAMMER, ItemTags.GOLD_TOOL_MATERIALS);
        offerWarhammerRecipe(ModItems.IRON_WARHAMMER, ItemTags.IRON_TOOL_MATERIALS);
        offerWarhammerRecipe(ModItems.DIAMOND_WARHAMMER, ItemTags.DIAMOND_TOOL_MATERIALS);

        offerNetheriteUpgradeRecipe(exporter, ModItems.DIAMOND_BROADSWORD, RecipeCategory.COMBAT, ModItems.NETHERITE_BROADSWORD);
        offerNetheriteUpgradeRecipe(exporter, ModItems.DIAMOND_SICKLE, RecipeCategory.COMBAT, ModItems.NETHERITE_SICKLE);
        offerNetheriteUpgradeRecipe(exporter, ModItems.DIAMOND_SCYTHE, RecipeCategory.COMBAT, ModItems.NETHERITE_SCYTHE);
        offerNetheriteUpgradeRecipe(exporter, ModItems.DIAMOND_LONGSWORD, RecipeCategory.COMBAT, ModItems.NETHERITE_LONGSWORD);
        offerNetheriteUpgradeRecipe(exporter, ModItems.DIAMOND_KATANA, RecipeCategory.COMBAT, ModItems.NETHERITE_KATANA);
        offerNetheriteUpgradeRecipe(exporter, ModItems.DIAMOND_GREATSWORD, RecipeCategory.COMBAT, ModItems.NETHERITE_GREATSWORD);
        offerNetheriteUpgradeRecipe(exporter, ModItems.DIAMOND_HATCHET, RecipeCategory.COMBAT, ModItems.NETHERITE_HATCHET);
        offerNetheriteUpgradeRecipe(exporter, ModItems.DIAMOND_HAMMER, RecipeCategory.COMBAT, ModItems.NETHERITE_HAMMER);
        offerNetheriteUpgradeRecipe(exporter, ModItems.DIAMOND_BATTLEAXE, RecipeCategory.COMBAT, ModItems.NETHERITE_BATTLEAXE);
        offerNetheriteUpgradeRecipe(exporter, ModItems.DIAMOND_WARHAMMER, RecipeCategory.COMBAT, ModItems.NETHERITE_WARHAMMER);
    }
}
