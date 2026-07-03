package net.angelic.weaponsexpanded.potion;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModPotions {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, WeaponsExpanded.MODID);

    public static final RegistryObject<Potion> FROSTBITE_POTION = POTIONS.register(
            "frostbite_potion",
            () -> new Potion("frostbite", new MobEffectInstance(ModEffects.frostbiteHolder(), 600, 0))
    );

    public static final RegistryObject<Potion> LONG_FROSTBITE_POTION = POTIONS.register(
            "long_frostbite_potion",
            () -> new Potion("frostbite", new MobEffectInstance(ModEffects.frostbiteHolder(), 1200, 0))
    );

    public static Holder<Potion> frostbitePotionHolder() {
        return FROSTBITE_POTION.getHolder().orElseThrow();
    }

    public static Holder<Potion> longFrostbitePotionHolder() {
        return LONG_FROSTBITE_POTION.getHolder().orElseThrow();
    }

    public static void register(BusGroup modBusGroup) {
        POTIONS.register(modBusGroup);
        BrewingRecipeRegisterEvent.BUS.addListener(ModPotions::registerBrewingRecipes);
    }

    private static void registerBrewingRecipes(BrewingRecipeRegisterEvent event) {
        event.addRecipe(new PotionMixRecipe(
                Potions.AWKWARD,
                Ingredient.of(Items.BLUE_ICE),
                PotionContents.createItemStack(Items.POTION, frostbitePotionHolder())
        ));

        event.addRecipe(new PotionMixRecipe(
                frostbitePotionHolder(),
                Ingredient.of(Items.REDSTONE),
                PotionContents.createItemStack(Items.POTION, longFrostbitePotionHolder())
        ));
    }

    /**
     * Forge's generic BrewingRecipe uses Ingredient#test for the input slot.
     * For potions, checking the PotionContents component directly is safer than
     * trying to match a component-sensitive ItemStack ingredient.
     */
    private record PotionMixRecipe(Holder<Potion> inputPotion, Ingredient ingredient, ItemStack output) implements IBrewingRecipe {
        @Override
        public boolean isInput(ItemStack input) {
            PotionContents contents = input.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY);
            return contents.is(inputPotion);
        }

        @Override
        public boolean isIngredient(ItemStack ingredientStack) {
            return ingredient.test(ingredientStack);
        }

        @Override
        public ItemStack getOutput(ItemStack input, ItemStack ingredientStack) {
            return isInput(input) && isIngredient(ingredientStack) ? output.copy() : ItemStack.EMPTY;
        }
    }

    private ModPotions() {
    }
}
