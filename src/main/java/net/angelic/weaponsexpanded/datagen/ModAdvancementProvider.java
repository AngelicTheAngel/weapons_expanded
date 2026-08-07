package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.util.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.*;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        Advancement.Builder.create()
                .parent(new AdvancementEntry(Identifier.ofVanilla("story/mine_diamond"), null))
                .display(
                        Items.DIAMOND_SWORD,
                        Text.translatable("advancements.weaponsexpanded.diamond_weapon"),
                        Text.translatable("advancements.weaponsexpanded.diamond_weapon.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_diamond_weapon", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create().tag(registryLookup.getOrThrow(RegistryKeys.ITEM), ModItemTags.DIAMOND_WEAPONS)))
                .build(consumer, WeaponsExpanded.MOD_ID + "/diamond_weapon");

        Advancement.Builder.create()
                .parent(new AdvancementEntry(Identifier.ofVanilla("nether/obtain_ancient_debris"), null))
                .display(
                        Items.NETHERITE_SWORD,
                        Text.translatable("advancements.weaponsexpanded.netherite_weapon"),
                        Text.translatable("advancements.weaponsexpanded.netherite_weapon.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("has_netherite_weapon", InventoryChangedCriterion.Conditions.items(ItemPredicate.Builder.create().tag(registryLookup.getOrThrow(RegistryKeys.ITEM), ModItemTags.NETHERITE_WEAPONS)))
                .build(consumer, WeaponsExpanded.MOD_ID + "/netherite_weapon");

        Advancement.Builder.create()
                .parent(new AdvancementEntry(Identifier.of("weaponsexpanded/netherite_weapon"), null))
                .display(
                        ModItems.NETHERITE_SCYTHE,
                        Text.translatable("advancements.weaponsexpanded.weapons_expanded"),
                        Text.translatable("advancements.weaponsexpanded.weapons_expanded.description"),
                        null,
                        AdvancementFrame.CHALLENGE,
                        true,
                        true,
                        false
                )
                .criterion("has_netherite_broadsword", InventoryChangedCriterion.Conditions.items(ModItems.NETHERITE_BROADSWORD))
                .criterion("has_netherite_sickle", InventoryChangedCriterion.Conditions.items(ModItems.NETHERITE_SICKLE))
                .criterion("has_netherite_scythe", InventoryChangedCriterion.Conditions.items(ModItems.NETHERITE_SCYTHE))
                .criterion("has_netherite_longsword", InventoryChangedCriterion.Conditions.items(ModItems.NETHERITE_LONGSWORD))
                .criterion("has_netherite_katana", InventoryChangedCriterion.Conditions.items(ModItems.NETHERITE_KATANA))
                .criterion("has_netherite_greatsword", InventoryChangedCriterion.Conditions.items(ModItems.NETHERITE_GREATSWORD))
                .criterion("has_netherite_hatchet", InventoryChangedCriterion.Conditions.items(ModItems.NETHERITE_HATCHET))
                .criterion("has_netherite_hammer", InventoryChangedCriterion.Conditions.items(ModItems.NETHERITE_HAMMER))
                .criterion("has_netherite_battleaxe", InventoryChangedCriterion.Conditions.items(ModItems.NETHERITE_BATTLEAXE))
                .criterion("has_netherite_warhammer", InventoryChangedCriterion.Conditions.items(ModItems.NETHERITE_WARHAMMER))
                .criterion("has_netherite_sword", InventoryChangedCriterion.Conditions.items(Items.NETHERITE_SWORD))
                .criterion("has_netherite_axe", InventoryChangedCriterion.Conditions.items(Items.NETHERITE_AXE))
                .build(consumer, WeaponsExpanded.MOD_ID + "/weapons_expanded");
    }
}
