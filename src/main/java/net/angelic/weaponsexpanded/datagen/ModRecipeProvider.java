package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeGenerator getRecipeGenerator(RegistryWrapper.@NonNull WrapperLookup registries, @NonNull RecipeExporter exporter) {
        return new RecipeGenerator(registries, exporter) {
            @Override
            public void generate() {
                float xp = 0.1f;

                int smeltTime = 200;
                int blastTime = 100;

                // Gold weapons -> gold nugget
                offerSmeltingNuggetRecipes("gold", goldItems(), Items.GOLD_NUGGET, xp, smeltTime);
                offerBlastingNuggetRecipes("gold", goldItems(), Items.GOLD_NUGGET, xp, blastTime);

                // Iron weapons -> iron nugget
                offerSmeltingNuggetRecipes("iron", ironItems(), Items.IRON_NUGGET, xp, smeltTime);
                offerBlastingNuggetRecipes("iron", ironItems(), Items.IRON_NUGGET, xp, blastTime);

                // Copper weapons -> copper nugget
                offerSmeltingNuggetRecipes("copper", copperItems(), Items.COPPER_NUGGET, xp, smeltTime);
                offerBlastingNuggetRecipes("copper", copperItems(), Items.COPPER_NUGGET, xp, blastTime);

                offerBroadswordRecipe(ModItems.WOODEN_BROADSWORD, ItemTags.WOODEN_TOOL_MATERIALS);
                offerBroadswordRecipe(ModItems.STONE_BROADSWORD, ItemTags.STONE_TOOL_MATERIALS);
                offerBroadswordRecipe(ModItems.COPPER_BROADSWORD, ItemTags.COPPER_TOOL_MATERIALS);
                offerBroadswordRecipe(ModItems.GOLDEN_BROADSWORD, ItemTags.GOLD_TOOL_MATERIALS);
                offerBroadswordRecipe(ModItems.IRON_BROADSWORD, ItemTags.IRON_TOOL_MATERIALS);
                offerBroadswordRecipe(ModItems.DIAMOND_BROADSWORD, ItemTags.DIAMOND_TOOL_MATERIALS);

                offerSickleRecipe(ModItems.WOODEN_SICKLE, ItemTags.WOODEN_TOOL_MATERIALS);
                offerSickleRecipe(ModItems.STONE_SICKLE, ItemTags.STONE_TOOL_MATERIALS);
                offerSickleRecipe(ModItems.COPPER_SICKLE, ItemTags.COPPER_TOOL_MATERIALS);
                offerSickleRecipe(ModItems.GOLDEN_SICKLE, ItemTags.GOLD_TOOL_MATERIALS);
                offerSickleRecipe(ModItems.IRON_SICKLE, ItemTags.IRON_TOOL_MATERIALS);
                offerSickleRecipe(ModItems.DIAMOND_SICKLE, ItemTags.DIAMOND_TOOL_MATERIALS);

                offerScytheRecipe(ModItems.WOODEN_SCYTHE, ItemTags.WOODEN_TOOL_MATERIALS);
                offerScytheRecipe(ModItems.STONE_SCYTHE, ItemTags.STONE_TOOL_MATERIALS);
                offerScytheRecipe(ModItems.COPPER_SCYTHE, ItemTags.COPPER_TOOL_MATERIALS);
                offerScytheRecipe(ModItems.GOLDEN_SCYTHE, ItemTags.GOLD_TOOL_MATERIALS);
                offerScytheRecipe(ModItems.IRON_SCYTHE, ItemTags.IRON_TOOL_MATERIALS);
                offerScytheRecipe(ModItems.DIAMOND_SCYTHE, ItemTags.DIAMOND_TOOL_MATERIALS);

                offerLongswordRecipe(ModItems.WOODEN_LONGSWORD, ItemTags.WOODEN_TOOL_MATERIALS);
                offerLongswordRecipe(ModItems.STONE_LONGSWORD, ItemTags.STONE_TOOL_MATERIALS);
                offerLongswordRecipe(ModItems.COPPER_LONGSWORD, ItemTags.COPPER_TOOL_MATERIALS);
                offerLongswordRecipe(ModItems.GOLDEN_LONGSWORD, ItemTags.GOLD_TOOL_MATERIALS);
                offerLongswordRecipe(ModItems.IRON_LONGSWORD, ItemTags.IRON_TOOL_MATERIALS);
                offerLongswordRecipe(ModItems.DIAMOND_LONGSWORD, ItemTags.DIAMOND_TOOL_MATERIALS);

                offerKatanaRecipe(ModItems.WOODEN_KATANA, ItemTags.WOODEN_TOOL_MATERIALS);
                offerKatanaRecipe(ModItems.STONE_KATANA, ItemTags.STONE_TOOL_MATERIALS);
                offerKatanaRecipe(ModItems.COPPER_KATANA, ItemTags.COPPER_TOOL_MATERIALS);
                offerKatanaRecipe(ModItems.GOLDEN_KATANA, ItemTags.GOLD_TOOL_MATERIALS);
                offerKatanaRecipe(ModItems.IRON_KATANA, ItemTags.IRON_TOOL_MATERIALS);
                offerKatanaRecipe(ModItems.DIAMOND_KATANA, ItemTags.DIAMOND_TOOL_MATERIALS);

                offerGreatswordRecipe(ModItems.WOODEN_GREATSWORD, ItemTags.WOODEN_TOOL_MATERIALS);
                offerGreatswordRecipe(ModItems.STONE_GREATSWORD, ItemTags.STONE_TOOL_MATERIALS);
                offerGreatswordRecipe(ModItems.COPPER_GREATSWORD, ItemTags.COPPER_TOOL_MATERIALS);
                offerGreatswordRecipe(ModItems.GOLDEN_GREATSWORD, ItemTags.GOLD_TOOL_MATERIALS);
                offerGreatswordRecipe(ModItems.IRON_GREATSWORD, ItemTags.IRON_TOOL_MATERIALS);
                offerGreatswordRecipe(ModItems.DIAMOND_GREATSWORD, ItemTags.DIAMOND_TOOL_MATERIALS);

                offerHatchetRecipe(ModItems.WOODEN_HATCHET, ItemTags.WOODEN_TOOL_MATERIALS);
                offerHatchetRecipe(ModItems.STONE_HATCHET, ItemTags.STONE_TOOL_MATERIALS);
                offerHatchetRecipe(ModItems.COPPER_HATCHET, ItemTags.COPPER_TOOL_MATERIALS);
                offerHatchetRecipe(ModItems.GOLDEN_HATCHET, ItemTags.GOLD_TOOL_MATERIALS);
                offerHatchetRecipe(ModItems.IRON_HATCHET, ItemTags.IRON_TOOL_MATERIALS);
                offerHatchetRecipe(ModItems.DIAMOND_HATCHET, ItemTags.DIAMOND_TOOL_MATERIALS);

                offerHammerRecipe(ModItems.WOODEN_HAMMER, ItemTags.WOODEN_TOOL_MATERIALS);
                offerHammerRecipe(ModItems.STONE_HAMMER, ItemTags.STONE_TOOL_MATERIALS);
                offerHammerRecipe(ModItems.COPPER_HAMMER, ItemTags.COPPER_TOOL_MATERIALS);
                offerHammerRecipe(ModItems.GOLDEN_HAMMER, ItemTags.GOLD_TOOL_MATERIALS);
                offerHammerRecipe(ModItems.IRON_HAMMER, ItemTags.IRON_TOOL_MATERIALS);
                offerHammerRecipe(ModItems.DIAMOND_HAMMER, ItemTags.DIAMOND_TOOL_MATERIALS);

                offerBattleaxeRecipe(ModItems.WOODEN_BATTLEAXE, ItemTags.WOODEN_TOOL_MATERIALS);
                offerBattleaxeRecipe(ModItems.STONE_BATTLEAXE, ItemTags.STONE_TOOL_MATERIALS);
                offerBattleaxeRecipe(ModItems.COPPER_BATTLEAXE, ItemTags.COPPER_TOOL_MATERIALS);
                offerBattleaxeRecipe(ModItems.GOLDEN_BATTLEAXE, ItemTags.GOLD_TOOL_MATERIALS);
                offerBattleaxeRecipe(ModItems.IRON_BATTLEAXE, ItemTags.IRON_TOOL_MATERIALS);
                offerBattleaxeRecipe(ModItems.DIAMOND_BATTLEAXE, ItemTags.DIAMOND_TOOL_MATERIALS);

                offerNetheriteUpgradeRecipe(ModItems.DIAMOND_BROADSWORD, RecipeCategory.COMBAT, ModItems.NETHERITE_BROADSWORD);
                offerNetheriteUpgradeRecipe(ModItems.DIAMOND_SICKLE, RecipeCategory.COMBAT, ModItems.NETHERITE_SICKLE);
                offerNetheriteUpgradeRecipe(ModItems.DIAMOND_SCYTHE, RecipeCategory.COMBAT, ModItems.NETHERITE_SCYTHE);
                offerNetheriteUpgradeRecipe(ModItems.DIAMOND_LONGSWORD, RecipeCategory.COMBAT, ModItems.NETHERITE_LONGSWORD);
                offerNetheriteUpgradeRecipe(ModItems.DIAMOND_KATANA, RecipeCategory.COMBAT, ModItems.NETHERITE_KATANA);
                offerNetheriteUpgradeRecipe(ModItems.DIAMOND_GREATSWORD, RecipeCategory.COMBAT, ModItems.NETHERITE_GREATSWORD);
                offerNetheriteUpgradeRecipe(ModItems.DIAMOND_HATCHET, RecipeCategory.COMBAT, ModItems.NETHERITE_HATCHET);
                offerNetheriteUpgradeRecipe(ModItems.DIAMOND_HAMMER, RecipeCategory.COMBAT, ModItems.NETHERITE_HAMMER);
                offerNetheriteUpgradeRecipe(ModItems.DIAMOND_BATTLEAXE, RecipeCategory.COMBAT, ModItems.NETHERITE_BATTLEAXE);
            }

            public void offerBroadswordRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.WOODEN_TOOL_MATERIALS) {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("  M")
                            .pattern(" M ")
                            .pattern("S  ")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.exporter);
                } else {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("  M")
                            .pattern(" M ")
                            .pattern("S  ")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.exporter);
                }
            }

            public void offerSickleRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.WOODEN_TOOL_MATERIALS) {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern(" M ")
                            .pattern("  M")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.exporter);
                } else {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern(" M ")
                            .pattern("  M")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.exporter);
                }
            }

            public void offerScytheRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.WOODEN_TOOL_MATERIALS) {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("MMM")
                            .pattern("  S")
                            .pattern("  S")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.exporter);
                } else {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("MMM")
                            .pattern("  S")
                            .pattern("  S")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.exporter);
                }
            }

            public void offerLongswordRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.WOODEN_TOOL_MATERIALS) {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern(" M ")
                            .pattern(" M ")
                            .pattern("MSM")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.exporter);
                } else {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern(" M ")
                            .pattern(" M ")
                            .pattern("MSM")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.exporter);
                }
            }

            public void offerKatanaRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.WOODEN_TOOL_MATERIALS) {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("  M")
                            .pattern(" M ")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.exporter);
                } else {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("  M")
                            .pattern(" M ")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.exporter);
                }
            }

            public void offerGreatswordRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.WOODEN_TOOL_MATERIALS) {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("  M")
                            .pattern("MM ")
                            .pattern("SM ")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.exporter);
                } else {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("  M")
                            .pattern("MM ")
                            .pattern("SM ")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.exporter);
                }
            }

            public void offerHatchetRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.WOODEN_TOOL_MATERIALS) {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern(" M ")
                            .pattern("MS ")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.exporter);
                } else {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern(" M ")
                            .pattern("MS ")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.exporter);
                }
            }

            public void offerHammerRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.WOODEN_TOOL_MATERIALS) {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("MSM")
                            .pattern("MSM")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.exporter);
                } else {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("MSM")
                            .pattern("MSM")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.exporter);
                }
            }

            public void offerBattleaxeRecipe(Item output, TagKey<Item> input) {
                if (input == ItemTags.WOODEN_TOOL_MATERIALS) {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("MM ")
                            .pattern("MSM")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion(hasItem(Items.STICK), this.conditionsFromItem(Items.STICK))
                            .offerTo(this.exporter);
                } else {
                    this.createShaped(RecipeCategory.COMBAT, output)
                            .input('M', input)
                            .input('S', Items.STICK)
                            .pattern("MM ")
                            .pattern("MSM")
                            .pattern(" S ")
                            .group(getItemPath(output))
                            .criterion("has_" + input.id().getPath(), this.conditionsFromTag(input))
                            .offerTo(this.exporter);
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
                            .offerTo(exporter, "smelting/" + materialName + "_nugget_from_" + inputIdPath);
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
                            .offerTo(exporter, "blasting/" + materialName + "_nugget_from_" + inputIdPath);
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

            private List<Item> copperItems() {
                return List.of(
                        ModItems.COPPER_BROADSWORD,
                        ModItems.COPPER_SICKLE,
                        ModItems.COPPER_SCYTHE,
                        ModItems.COPPER_LONGSWORD,
                        ModItems.COPPER_KATANA,
                        ModItems.COPPER_HATCHET,
                        ModItems.COPPER_HAMMER,
                        ModItems.COPPER_BATTLEAXE,
                        ModItems.COPPER_GREATSWORD
                );
            }
        };
    }

    @Override
    public String getName() {
        return "WeaponsExpanded Recipes";
    }
}
