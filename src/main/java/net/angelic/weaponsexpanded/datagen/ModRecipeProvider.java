package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.data.recipes.RecipeCategory;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class ModRecipeProvider extends RecipeProvider {

    private final RecipeOutput recipeExporter;

    public ModRecipeProvider(
            HolderLookup.Provider registries,
            RecipeOutput recipeExporter
    ) {
        super(registries, recipeExporter);
        this.recipeExporter = recipeExporter;
    }

    public void offerBroadswordRecipe(Item output, TagKey<Item> input) {
        if (input == ItemTags.PLANKS) {
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("  M")
                    .pattern(" M ")
                    .pattern("S  ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
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
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern(" M ")
                    .pattern("  M")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
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
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("MMM")
                    .pattern("  S")
                    .pattern("  S")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
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
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern(" M ")
                    .pattern(" M ")
                    .pattern("MSM")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
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
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("  M")
                    .pattern(" M ")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
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
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("  M")
                    .pattern("MM ")
                    .pattern("SM ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
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
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern(" M ")
                    .pattern("MS ")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
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
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("MSM")
                    .pattern("MSM")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
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
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("MM ")
                    .pattern("MSM")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
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
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
                    .define('M', input)
                    .define('S', Items.STICK)
                    .pattern("MMM")
                    .pattern("MS ")
                    .pattern(" S ")
                    .group(getItemName(output))
                    .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                    .save(this.recipeExporter);
        } else {
            ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, output, 1)
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
                            Identifier.fromNamespaceAndPath(
                                    WeaponsExpanded.MOD_ID,
                                    "smelting/"
                                            + materialName
                                            + "_nugget_from_"
                                            + inputIdPath
                            ).toString()
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
                            Identifier.fromNamespaceAndPath(
                                    WeaponsExpanded.MOD_ID,
                                    "blasting/"
                                            + materialName
                                            + "_nugget_from_"
                                            + inputIdPath
                            ).toString()
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

    @Override
    protected void buildRecipes() {
        float xp = 0.1f;

        int smeltTime = 200;
        int blastTime = 100;

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
        offerBroadswordRecipe(ModItems.COPPER_BROADSWORD.get(), ItemTags.COPPER_TOOL_MATERIALS);
        offerBroadswordRecipe(ModItems.GOLDEN_BROADSWORD.get(), ItemTags.GOLD_TOOL_MATERIALS);
        offerBroadswordRecipe(ModItems.IRON_BROADSWORD.get(), ItemTags.IRON_TOOL_MATERIALS);
        offerBroadswordRecipe(ModItems.DIAMOND_BROADSWORD.get(), ItemTags.DIAMOND_TOOL_MATERIALS);

        offerSickleRecipe(ModItems.WOODEN_SICKLE.get(), ItemTags.PLANKS);
        offerSickleRecipe(ModItems.STONE_SICKLE.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerSickleRecipe(ModItems.COPPER_SICKLE.get(), ItemTags.COPPER_TOOL_MATERIALS);
        offerSickleRecipe(ModItems.GOLDEN_SICKLE.get(), ItemTags.GOLD_TOOL_MATERIALS);
        offerSickleRecipe(ModItems.IRON_SICKLE.get(), ItemTags.IRON_TOOL_MATERIALS);
        offerSickleRecipe(ModItems.DIAMOND_SICKLE.get(), ItemTags.DIAMOND_TOOL_MATERIALS);

        offerScytheRecipe(ModItems.WOODEN_SCYTHE.get(), ItemTags.PLANKS);
        offerScytheRecipe(ModItems.STONE_SCYTHE.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerScytheRecipe(ModItems.COPPER_SCYTHE.get(), ItemTags.COPPER_TOOL_MATERIALS);
        offerScytheRecipe(ModItems.GOLDEN_SCYTHE.get(), ItemTags.GOLD_TOOL_MATERIALS);
        offerScytheRecipe(ModItems.IRON_SCYTHE.get(), ItemTags.IRON_TOOL_MATERIALS);
        offerScytheRecipe(ModItems.DIAMOND_SCYTHE.get(), ItemTags.DIAMOND_TOOL_MATERIALS);

        offerLongswordRecipe(ModItems.WOODEN_LONGSWORD.get(), ItemTags.PLANKS);
        offerLongswordRecipe(ModItems.STONE_LONGSWORD.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerLongswordRecipe(ModItems.COPPER_LONGSWORD.get(), ItemTags.COPPER_TOOL_MATERIALS);
        offerLongswordRecipe(ModItems.GOLDEN_LONGSWORD.get(), ItemTags.GOLD_TOOL_MATERIALS);
        offerLongswordRecipe(ModItems.IRON_LONGSWORD.get(), ItemTags.IRON_TOOL_MATERIALS);
        offerLongswordRecipe(ModItems.DIAMOND_LONGSWORD.get(), ItemTags.DIAMOND_TOOL_MATERIALS);

        offerKatanaRecipe(ModItems.WOODEN_KATANA.get(), ItemTags.PLANKS);
        offerKatanaRecipe(ModItems.STONE_KATANA.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerKatanaRecipe(ModItems.COPPER_KATANA.get(), ItemTags.COPPER_TOOL_MATERIALS);
        offerKatanaRecipe(ModItems.GOLDEN_KATANA.get(), ItemTags.GOLD_TOOL_MATERIALS);
        offerKatanaRecipe(ModItems.IRON_KATANA.get(), ItemTags.IRON_TOOL_MATERIALS);
        offerKatanaRecipe(ModItems.DIAMOND_KATANA.get(), ItemTags.DIAMOND_TOOL_MATERIALS);

        offerGreatswordRecipe(ModItems.WOODEN_GREATSWORD.get(), ItemTags.PLANKS);
        offerGreatswordRecipe(ModItems.STONE_GREATSWORD.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerGreatswordRecipe(ModItems.COPPER_GREATSWORD.get(), ItemTags.COPPER_TOOL_MATERIALS);
        offerGreatswordRecipe(ModItems.GOLDEN_GREATSWORD.get(), ItemTags.GOLD_TOOL_MATERIALS);
        offerGreatswordRecipe(ModItems.IRON_GREATSWORD.get(), ItemTags.IRON_TOOL_MATERIALS);
        offerGreatswordRecipe(ModItems.DIAMOND_GREATSWORD.get(), ItemTags.DIAMOND_TOOL_MATERIALS);

        offerHatchetRecipe(ModItems.WOODEN_HATCHET.get(), ItemTags.PLANKS);
        offerHatchetRecipe(ModItems.STONE_HATCHET.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerHatchetRecipe(ModItems.COPPER_HATCHET.get(), ItemTags.COPPER_TOOL_MATERIALS);
        offerHatchetRecipe(ModItems.GOLDEN_HATCHET.get(), ItemTags.GOLD_TOOL_MATERIALS);
        offerHatchetRecipe(ModItems.IRON_HATCHET.get(), ItemTags.IRON_TOOL_MATERIALS);
        offerHatchetRecipe(ModItems.DIAMOND_HATCHET.get(), ItemTags.DIAMOND_TOOL_MATERIALS);

        offerHammerRecipe(ModItems.WOODEN_HAMMER.get(), ItemTags.PLANKS);
        offerHammerRecipe(ModItems.STONE_HAMMER.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerHammerRecipe(ModItems.COPPER_HAMMER.get(), ItemTags.COPPER_TOOL_MATERIALS);
        offerHammerRecipe(ModItems.GOLDEN_HAMMER.get(), ItemTags.GOLD_TOOL_MATERIALS);
        offerHammerRecipe(ModItems.IRON_HAMMER.get(), ItemTags.IRON_TOOL_MATERIALS);
        offerHammerRecipe(ModItems.DIAMOND_HAMMER.get(), ItemTags.DIAMOND_TOOL_MATERIALS);

        offerBattleaxeRecipe(ModItems.WOODEN_BATTLEAXE.get(), ItemTags.PLANKS);
        offerBattleaxeRecipe(ModItems.STONE_BATTLEAXE.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerBattleaxeRecipe(ModItems.COPPER_BATTLEAXE.get(), ItemTags.COPPER_TOOL_MATERIALS);
        offerBattleaxeRecipe(ModItems.GOLDEN_BATTLEAXE.get(), ItemTags.GOLD_TOOL_MATERIALS);
        offerBattleaxeRecipe(ModItems.IRON_BATTLEAXE.get(), ItemTags.IRON_TOOL_MATERIALS);
        offerBattleaxeRecipe(ModItems.DIAMOND_BATTLEAXE.get(), ItemTags.DIAMOND_TOOL_MATERIALS);

        offerWarhammerRecipe(ModItems.WOODEN_WARHAMMER.get(), ItemTags.PLANKS);
        offerWarhammerRecipe(ModItems.STONE_WARHAMMER.get(), ItemTags.STONE_TOOL_MATERIALS);
        offerWarhammerRecipe(ModItems.COPPER_WARHAMMER.get(), ItemTags.COPPER_TOOL_MATERIALS);
        offerWarhammerRecipe(ModItems.GOLDEN_WARHAMMER.get(), ItemTags.GOLD_TOOL_MATERIALS);
        offerWarhammerRecipe(ModItems.IRON_WARHAMMER.get(), ItemTags.IRON_TOOL_MATERIALS);
        offerWarhammerRecipe(ModItems.DIAMOND_WARHAMMER.get(), ItemTags.DIAMOND_TOOL_MATERIALS);

        netheriteSmithing(ModItems.DIAMOND_BROADSWORD.get(), RecipeCategory.COMBAT, ModItems.NETHERITE_BROADSWORD.get());
        netheriteSmithing(ModItems.DIAMOND_SICKLE.get(), RecipeCategory.COMBAT, ModItems.NETHERITE_SICKLE.get());
        netheriteSmithing(ModItems.DIAMOND_SCYTHE.get(), RecipeCategory.COMBAT, ModItems.NETHERITE_SCYTHE.get());
        netheriteSmithing(ModItems.DIAMOND_LONGSWORD.get(), RecipeCategory.COMBAT, ModItems.NETHERITE_LONGSWORD.get());
        netheriteSmithing(ModItems.DIAMOND_KATANA.get(), RecipeCategory.COMBAT, ModItems.NETHERITE_KATANA.get());
        netheriteSmithing(ModItems.DIAMOND_GREATSWORD.get(), RecipeCategory.COMBAT, ModItems.NETHERITE_GREATSWORD.get());
        netheriteSmithing(ModItems.DIAMOND_HATCHET.get(), RecipeCategory.COMBAT, ModItems.NETHERITE_HATCHET.get());
        netheriteSmithing(ModItems.DIAMOND_HAMMER.get(), RecipeCategory.COMBAT, ModItems.NETHERITE_HAMMER.get());
        netheriteSmithing(ModItems.DIAMOND_BATTLEAXE.get(), RecipeCategory.COMBAT, ModItems.NETHERITE_BATTLEAXE.get());
        netheriteSmithing(ModItems.DIAMOND_WARHAMMER.get(), RecipeCategory.COMBAT, ModItems.NETHERITE_WARHAMMER.get());

        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, ModItems.CHAIN_CROSSBOW.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('C', Items.IRON_CHAIN)
                .define('N', Items.IRON_NUGGET)
                .define('T', Items.TRIPWIRE_HOOK)
                .pattern("INI")
                .pattern("CTC")
                .pattern(" I ")
                .group(getItemName(ModItems.CHAIN_CROSSBOW.get()))
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_chain", has(Items.IRON_CHAIN))
                .unlockedBy("has_iron_nugget", has(Items.IRON_NUGGET))
                .unlockedBy("has_tripwire_hook", has(Items.TRIPWIRE_HOOK))
                .save(this.recipeExporter);

        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, ModItems.LONGBOW.get(), 1)
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STRING)
                .define('T', Items.STICK)
                .pattern(" TS")
                .pattern("TIS")
                .pattern(" TS")
                .group(getItemName(ModItems.LONGBOW.get()))
                .unlockedBy("has_string", has(Items.STRING))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(this.recipeExporter);

        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, ModItems.EXPLOSIVE_ARROW.get(), 2)
                .define('S', Items.STICK)
                .define('F', Items.FLINT)
                .define('E', Items.FEATHER)
                .define('T', Items.STRING)
                .define('N', Items.TNT)
                .pattern("TF ")
                .pattern("NS ")
                .pattern(" E ")
                .group(getItemName(ModItems.EXPLOSIVE_ARROW.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(this.recipeExporter);

        ShapedRecipeBuilder.shaped(this.registries.lookupOrThrow(Registries.ITEM), RecipeCategory.COMBAT, ModItems.HEAVY_ARROW.get(), 4)
                .define('S', Items.STICK)
                .define('I', Items.IRON_INGOT)
                .define('F', Items.FEATHER)
                .pattern(" I ")
                .pattern(" S ")
                .pattern(" F ")
                .group(getItemName(ModItems.HEAVY_ARROW.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(this.recipeExporter);
    }

    public static final class Runner extends RecipeProvider.Runner {

        public Runner(
                PackOutput output,
                CompletableFuture<HolderLookup.Provider> registries
        ) {
            super(output, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(
                HolderLookup.Provider registries,
                RecipeOutput recipeOutput
        ) {
            return new ModRecipeProvider(registries, recipeOutput);
        }

        @Override
        public String getName() {
            return "";
        }
    }
}