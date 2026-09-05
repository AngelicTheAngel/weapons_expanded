package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.util.tags.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(HolderLookup.Provider registryLookup, Consumer<AdvancementHolder> consumer) {
        Advancement.Builder.advancement()
                .parent(createPlaceholder(Identifier.withDefaultNamespace("story/mine_diamond")))
                .display(
                        Items.DIAMOND_SWORD,
                        Component.translatable("advancements.weaponsexpanded.diamond_weapon"),
                        Component.translatable("advancements.weaponsexpanded.diamond_weapon.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_diamond_weapon", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(registryLookup.lookupOrThrow(Registries.ITEM), ModItemTags.DIAMOND_WEAPON)))
                .save(consumer, Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "diamond_weapon"));

        Advancement.Builder.advancement()
                .parent(createPlaceholder(Identifier.withDefaultNamespace("nether/obtain_ancient_debris")))
                .display(
                        Items.NETHERITE_SWORD,
                        Component.translatable("advancements.weaponsexpanded.netherite_weapon"),
                        Component.translatable("advancements.weaponsexpanded.netherite_weapon.description"),
                        null,
                        AdvancementType.TASK,
                        true,
                        true,
                        false
                )
                .addCriterion("has_netherite_weapon", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(registryLookup.lookupOrThrow(Registries.ITEM), ModItemTags.NETHERITE_WEAPON)))
                .save(consumer, Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "netherite_weapon"));

        Advancement.Builder.advancement()
                .parent(createPlaceholder(Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "netherite_weapon")))
                .display(
                        ModItems.NETHERITE_SCYTHE,
                        Component.translatable("advancements.weaponsexpanded.weapons_expanded"),
                        Component.translatable("advancements.weaponsexpanded.weapons_expanded.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("has_netherite_rapier", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_RAPIER))
                .addCriterion("has_netherite_broadsword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_BROADSWORD))
                .addCriterion("has_netherite_sickle", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_SICKLE))
                .addCriterion("has_netherite_scythe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_SCYTHE))
                .addCriterion("has_netherite_longsword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_LONGSWORD))
                .addCriterion("has_netherite_katana", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_KATANA))
                .addCriterion("has_netherite_greatsword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_GREATSWORD))
                .addCriterion("has_netherite_hatchet", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_HATCHET))
                .addCriterion("has_netherite_hammer", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_HAMMER))
                .addCriterion("has_netherite_battleaxe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_BATTLEAXE))
                .addCriterion("has_netherite_warhammer", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_WARHAMMER))
                .addCriterion("has_netherite_glaive", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_GLAIVE))
                .addCriterion("has_netherite_morningstar", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_MORNINGSTAR))
                .addCriterion("has_netherite_halberd", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_HALBERD))
                .addCriterion("has_netherite_sword", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_SWORD))
                .addCriterion("has_netherite_axe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_AXE))
                .addCriterion("has_netherite_spear", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_SPEAR))
                .rewards(AdvancementRewards.Builder.experience(200))
                .save(consumer, Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "weapons_expanded"));
    }
}
