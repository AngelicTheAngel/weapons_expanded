package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.util.ModItemTags;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider
        extends AdvancementProvider {

    public ModAdvancementProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper existingFileHelper
    ) {
        super(
                output,
                lookupProvider,
                existingFileHelper,
                List.of(ModAdvancementProvider::generate)
        );
    }

    private static void generate(
            HolderLookup.Provider registries,
            Consumer<AdvancementHolder> saver,
            ExistingFileHelper existingFileHelper
    ) {
        Advancement.Builder.advancement()
                .parent(
                        AdvancementSubProvider.createPlaceholder(
                                "minecraft:story/mine_diamond"
                        )
                )
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
                .addCriterion("has_diamond_weapon", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.DIAMOND_WEAPONS)))
                .save(saver, id("diamond_weapon"), existingFileHelper);

        AdvancementHolder netheriteWeapon =
                Advancement.Builder.advancement()
                        .parent(AdvancementSubProvider.createPlaceholder("minecraft:nether/obtain_ancient_debris"))
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
                        .addCriterion("has_netherite_weapon", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.NETHERITE_WEAPONS)))
                        .save(saver, id("netherite_weapon"), existingFileHelper);

        Advancement.Builder.advancement()
                .parent(netheriteWeapon)
                .display(
                        ModItems.NETHERITE_SCYTHE.get(),
                        Component.translatable("advancements.weaponsexpanded.weapons_expanded"),
                        Component.translatable("advancements.weaponsexpanded.weapons_expanded.description"),
                        null,
                        AdvancementType.CHALLENGE,
                        true,
                        true,
                        false
                )
                .addCriterion("has_netherite_broadsword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_BROADSWORD.get()))
                .addCriterion("has_netherite_sickle", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_SICKLE.get()))
                .addCriterion("has_netherite_scythe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_SCYTHE.get()))
                .addCriterion("has_netherite_longsword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_LONGSWORD.get()))
                .addCriterion("has_netherite_katana", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_KATANA.get()))
                .addCriterion("has_netherite_greatsword", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_GREATSWORD.get()))
                .addCriterion("has_netherite_hatchet", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_HATCHET.get()))
                .addCriterion("has_netherite_hammer", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_HAMMER.get()))
                .addCriterion("has_netherite_battleaxe", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_BATTLEAXE.get()))
                .addCriterion("has_netherite_warhammer", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.NETHERITE_WARHAMMER.get()))
                .addCriterion("has_netherite_sword", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_SWORD))
                .addCriterion("has_netherite_axe", InventoryChangeTrigger.TriggerInstance.hasItems(Items.NETHERITE_AXE))
                .rewards(AdvancementRewards.Builder.experience(100))
                .save(saver, id("weapons_expanded"), existingFileHelper);
    }

    private static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(
                WeaponsExpanded.MOD_ID,
                path
        );
    }
}