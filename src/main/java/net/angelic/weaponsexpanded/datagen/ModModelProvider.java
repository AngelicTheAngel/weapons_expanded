package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.RangeSelectItemModel;
import net.minecraft.client.renderer.item.properties.conditional.HasComponent;
import net.minecraft.client.renderer.item.properties.numeric.CrossbowPull;
import net.minecraft.client.renderer.item.properties.numeric.CustomModelDataProperty;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.client.renderer.item.properties.select.Charge;
import net.minecraft.client.renderer.item.properties.select.DisplayContext;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    public static class CustomItemModelGenerator {
        public static final ModelTemplate GLAIVE_IN_HAND = item("glaive_in_hand", TextureSlot.LAYER0);
        public static final ModelTemplate SCYTHE_IN_HAND = item("scythe_in_hand", TextureSlot.LAYER0);

        @SuppressWarnings("SameParameterValue")
        private static ModelTemplate item(String parent, TextureSlot requiredTextureKeys) {
            return new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "item/" + parent)), Optional.empty(), requiredTextureKeys);
        }

    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.WOODEN_RAPIER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_RAPIER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STONE_RAPIER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COPPER_RAPIER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IRON_RAPIER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_RAPIER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_RAPIER, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.WOODEN_BROADSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_BROADSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STONE_BROADSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COPPER_BROADSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IRON_BROADSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_BROADSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_BROADSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.WOODEN_SICKLE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_SICKLE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STONE_SICKLE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COPPER_SICKLE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IRON_SICKLE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_SICKLE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_SICKLE, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.WOODEN_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STONE_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COPPER_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IRON_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_SCYTHE, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.WOODEN_LONGSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_LONGSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STONE_LONGSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COPPER_LONGSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IRON_LONGSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_LONGSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_LONGSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.WOODEN_KATANA, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_KATANA, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STONE_KATANA, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COPPER_KATANA, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IRON_KATANA, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_KATANA, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_KATANA, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.WOODEN_HATCHET, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_HATCHET, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STONE_HATCHET, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COPPER_HATCHET, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IRON_HATCHET, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_HATCHET, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_HATCHET, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.WOODEN_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STONE_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COPPER_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IRON_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_HAMMER, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.WOODEN_BATTLEAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_BATTLEAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STONE_BATTLEAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COPPER_BATTLEAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IRON_BATTLEAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_BATTLEAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_BATTLEAXE, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.WOODEN_GREATSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_GREATSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STONE_GREATSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COPPER_GREATSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IRON_GREATSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_GREATSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_GREATSWORD, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.WOODEN_MORNINGSTAR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_MORNINGSTAR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.STONE_MORNINGSTAR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COPPER_MORNINGSTAR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IRON_MORNINGSTAR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DIAMOND_MORNINGSTAR, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.NETHERITE_MORNINGSTAR, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.HEAVY_ARROW, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.EXPLOSIVE_ARROW, ModelTemplates.FLAT_ITEM);

        ItemModel.Unbaked longbow = ItemModelUtils.plainModel(itemModelGenerator.createFlatItemModel(ModItems.LONGBOW, ModelTemplates.BOW));
        ItemModel.Unbaked longbow_pulling_0 = ItemModelUtils.plainModel(itemModelGenerator.createFlatItemModel(ModItems.LONGBOW, "_pulling_0", ModelTemplates.BOW));
        ItemModel.Unbaked longbow_pulling_1 = ItemModelUtils.plainModel(itemModelGenerator.createFlatItemModel(ModItems.LONGBOW, "_pulling_1", ModelTemplates.BOW));
        ItemModel.Unbaked longbow_pulling_2 = ItemModelUtils.plainModel(itemModelGenerator.createFlatItemModel(ModItems.LONGBOW, "_pulling_2", ModelTemplates.BOW));
        ItemModel.Unbaked longbow_pulling = new RangeSelectItemModel.Unbaked(
                Optional.empty(),
                new UseDuration(false),
                0.03125F,
                List.of(
                        ItemModelUtils.override(longbow_pulling_1, 0.65F),
                        ItemModelUtils.override(longbow_pulling_2, 0.9F)),
                Optional.of(longbow_pulling_0));

        itemModelGenerator.itemModelOutput.accept(ModItems.LONGBOW, ItemModelUtils.conditional(ItemModelUtils.isUsingItem(), longbow_pulling, longbow));

        ItemModel.Unbaked chain_crossbow = ItemModelUtils.plainModel(itemModelGenerator.createFlatItemModel(ModItems.CHAIN_CROSSBOW, ModelTemplates.CROSSBOW));
        ItemModel.Unbaked chain_crossbow_pulling_0 = ItemModelUtils.plainModel(itemModelGenerator.createFlatItemModel(ModItems.CHAIN_CROSSBOW, "_pulling_0", ModelTemplates.CROSSBOW));
        ItemModel.Unbaked chain_crossbow_pulling_1 = ItemModelUtils.plainModel(itemModelGenerator.createFlatItemModel(ModItems.CHAIN_CROSSBOW, "_pulling_1", ModelTemplates.CROSSBOW));
        ItemModel.Unbaked chain_crossbow_pulling_2 = ItemModelUtils.plainModel(itemModelGenerator.createFlatItemModel(ModItems.CHAIN_CROSSBOW, "_pulling_2", ModelTemplates.CROSSBOW));
        ItemModel.Unbaked chain_crossbow_arrow = ItemModelUtils.plainModel(itemModelGenerator.createFlatItemModel(ModItems.CHAIN_CROSSBOW, "_arrow", ModelTemplates.CROSSBOW));
        ItemModel.Unbaked chain_crossbow_firework = ItemModelUtils.plainModel(itemModelGenerator.createFlatItemModel(ModItems.CHAIN_CROSSBOW, "_firework", ModelTemplates.CROSSBOW));
        ItemModel.Unbaked chain_crossbow_explosive_arrow = ItemModelUtils.plainModel(itemModelGenerator.createFlatItemModel(ModItems.CHAIN_CROSSBOW, "_explosive_arrow", ModelTemplates.CROSSBOW));
        ItemModel.Unbaked chain_crossbow_pulling = new RangeSelectItemModel.Unbaked(
                Optional.empty(),
                new CrossbowPull(),
                1.0F,
                List.of(
                        ItemModelUtils.override(chain_crossbow_pulling_1, 0.58F),
                        ItemModelUtils.override(chain_crossbow_pulling_2, 1.0F)),
                Optional.of(chain_crossbow_pulling_0));

        ItemModel.Unbaked chain_crossbow_uncharged = ItemModelUtils.conditional(ItemModelUtils.isUsingItem(), chain_crossbow_pulling, chain_crossbow);
        ItemModel.Unbaked chain_crossbow_vanilla = ItemModelUtils.select(new Charge(),
                chain_crossbow_uncharged,
                ItemModelUtils.when(
                        CrossbowItem.ChargeType.ARROW,
                        chain_crossbow_arrow
                ),
                ItemModelUtils.when(
                        CrossbowItem.ChargeType.ROCKET,
                        chain_crossbow_firework
                ));

        ItemModel.Unbaked chain_crossbow_explosive = ItemModelUtils.select(new Charge(),
                chain_crossbow_uncharged,
                ItemModelUtils.when(
                        CrossbowItem.ChargeType.ARROW,
                        chain_crossbow_explosive_arrow
                ));

        ItemModel.Unbaked chain_crossbow_final = new RangeSelectItemModel.Unbaked(
                Optional.empty(),
                new CustomModelDataProperty(0),
                1.0F,
                List.of(
                        ItemModelUtils.override(chain_crossbow_explosive, 1.0F)),
                Optional.of(chain_crossbow_vanilla));

        itemModelGenerator.itemModelOutput.accept(ModItems.CHAIN_CROSSBOW, chain_crossbow_final);

        for (Item warhammer : List.of(
                ModItems.WOODEN_WARHAMMER,
                ModItems.GOLDEN_WARHAMMER,
                ModItems.STONE_WARHAMMER,
                ModItems.COPPER_WARHAMMER,
                ModItems.IRON_WARHAMMER,
                ModItems.DIAMOND_WARHAMMER,
                ModItems.NETHERITE_WARHAMMER
        )) {
            itemModelGenerator.generateBooleanDispatch(
                    warhammer,
                    new HasComponent(DataComponents.CUSTOM_DATA, false),
                    ItemModelUtils.plainModel(itemModelGenerator.createFlatItemModel(warhammer, "_sharp", ModelTemplates.FLAT_HANDHELD_ITEM)),
                    ItemModelUtils.plainModel(itemModelGenerator.createFlatItemModel(warhammer, ModelTemplates.FLAT_HANDHELD_ITEM))
            );
        }

        for (Item glaive : List.of(
                ModItems.WOODEN_GLAIVE,
                ModItems.GOLDEN_GLAIVE,
                ModItems.STONE_GLAIVE,
                ModItems.COPPER_GLAIVE,
                ModItems.IRON_GLAIVE,
                ModItems.DIAMOND_GLAIVE,
                ModItems.NETHERITE_GLAIVE
        )) {
            CustomItemModelGenerator.GLAIVE_IN_HAND.create(
                    ModelLocationUtils.getModelLocation(glaive).withSuffix("_in_hand"),
                    TextureMapping.singleSlot(TextureSlot.LAYER0, new Material(ModelLocationUtils.getModelLocation(glaive).withSuffix("_in_hand"))),
                    itemModelGenerator.modelOutput);

            itemModelGenerator.itemModelOutput.accept(glaive, ItemModelUtils.select(
                    new DisplayContext(),
                    ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(glaive).withSuffix("_in_hand")),
                    ItemModelUtils.when(List.of(ItemDisplayContext.GUI, ItemDisplayContext.GROUND, ItemDisplayContext.FIXED, ItemDisplayContext.ON_SHELF),
                            ItemModelUtils.plainModel(itemModelGenerator.createFlatItemModel(glaive, ModelTemplates.FLAT_HANDHELD_ITEM)))));
        }
    }
}
