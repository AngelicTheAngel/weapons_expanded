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
