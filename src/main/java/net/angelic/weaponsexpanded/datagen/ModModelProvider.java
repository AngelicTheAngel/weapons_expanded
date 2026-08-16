package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.renderer.item.BlockModelWrapper;
import net.minecraft.client.renderer.item.ConditionalItemModel;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.RangeSelectItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.conditional.IsUsingItem;
import net.minecraft.client.renderer.item.properties.numeric.CrossbowPull;
import net.minecraft.client.renderer.item.properties.numeric.CustomModelDataProperty;
import net.minecraft.client.renderer.item.properties.numeric.UseDuration;
import net.minecraft.client.renderer.item.properties.select.Charge;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Optional;

public final class ModModelProvider extends ModelProvider {

    private static final float LONGBOW_DURATION_SCALE =
            1.0F / 32.0F;

    public ModModelProvider(PackOutput output) {
        super(output, WeaponsExpanded.MOD_ID);
    }

    @Override
    protected void registerModels(
            @NonNull BlockModelGenerators blockModels,
            ItemModelGenerators itemModels
    ) {
        generateFlatHandheldItems(
                itemModels,
                ModItems.WOODEN_BROADSWORD.get(),
                ModItems.GOLDEN_BROADSWORD.get(),
                ModItems.STONE_BROADSWORD.get(),
                ModItems.COPPER_BROADSWORD.get(),
                ModItems.IRON_BROADSWORD.get(),
                ModItems.DIAMOND_BROADSWORD.get(),
                ModItems.NETHERITE_BROADSWORD.get(),

                ModItems.WOODEN_SICKLE.get(),
                ModItems.GOLDEN_SICKLE.get(),
                ModItems.STONE_SICKLE.get(),
                ModItems.COPPER_SICKLE.get(),
                ModItems.IRON_SICKLE.get(),
                ModItems.DIAMOND_SICKLE.get(),
                ModItems.NETHERITE_SICKLE.get(),

                ModItems.WOODEN_SCYTHE.get(),
                ModItems.GOLDEN_SCYTHE.get(),
                ModItems.STONE_SCYTHE.get(),
                ModItems.COPPER_SCYTHE.get(),
                ModItems.IRON_SCYTHE.get(),
                ModItems.DIAMOND_SCYTHE.get(),
                ModItems.NETHERITE_SCYTHE.get(),

                ModItems.WOODEN_LONGSWORD.get(),
                ModItems.GOLDEN_LONGSWORD.get(),
                ModItems.STONE_LONGSWORD.get(),
                ModItems.COPPER_LONGSWORD.get(),
                ModItems.IRON_LONGSWORD.get(),
                ModItems.DIAMOND_LONGSWORD.get(),
                ModItems.NETHERITE_LONGSWORD.get(),

                ModItems.WOODEN_KATANA.get(),
                ModItems.GOLDEN_KATANA.get(),
                ModItems.STONE_KATANA.get(),
                ModItems.COPPER_KATANA.get(),
                ModItems.IRON_KATANA.get(),
                ModItems.DIAMOND_KATANA.get(),
                ModItems.NETHERITE_KATANA.get(),

                ModItems.WOODEN_HATCHET.get(),
                ModItems.GOLDEN_HATCHET.get(),
                ModItems.STONE_HATCHET.get(),
                ModItems.COPPER_HATCHET.get(),
                ModItems.IRON_HATCHET.get(),
                ModItems.DIAMOND_HATCHET.get(),
                ModItems.NETHERITE_HATCHET.get(),

                ModItems.WOODEN_HAMMER.get(),
                ModItems.GOLDEN_HAMMER.get(),
                ModItems.STONE_HAMMER.get(),
                ModItems.COPPER_HAMMER.get(),
                ModItems.IRON_HAMMER.get(),
                ModItems.DIAMOND_HAMMER.get(),
                ModItems.NETHERITE_HAMMER.get(),

                ModItems.WOODEN_BATTLEAXE.get(),
                ModItems.GOLDEN_BATTLEAXE.get(),
                ModItems.STONE_BATTLEAXE.get(),
                ModItems.COPPER_BATTLEAXE.get(),
                ModItems.IRON_BATTLEAXE.get(),
                ModItems.DIAMOND_BATTLEAXE.get(),
                ModItems.NETHERITE_BATTLEAXE.get(),

                ModItems.WOODEN_GREATSWORD.get(),
                ModItems.GOLDEN_GREATSWORD.get(),
                ModItems.STONE_GREATSWORD.get(),
                ModItems.COPPER_GREATSWORD.get(),
                ModItems.IRON_GREATSWORD.get(),
                ModItems.DIAMOND_GREATSWORD.get(),
                ModItems.NETHERITE_GREATSWORD.get()
        );

        generateWarhammer(itemModels, ModItems.WOODEN_WARHAMMER.get());
        generateWarhammer(itemModels, ModItems.GOLDEN_WARHAMMER.get());
        generateWarhammer(itemModels, ModItems.STONE_WARHAMMER.get());
        generateWarhammer(itemModels, ModItems.COPPER_WARHAMMER.get());
        generateWarhammer(itemModels, ModItems.IRON_WARHAMMER.get());
        generateWarhammer(itemModels, ModItems.DIAMOND_WARHAMMER.get());
        generateWarhammer(itemModels, ModItems.NETHERITE_WARHAMMER.get());

        generateLongbow(itemModels);
        generateChainCrossbow(itemModels);

        itemModels.generateFlatItem(
                ModItems.HEAVY_ARROW.get(),
                ModelTemplates.FLAT_ITEM
        );
        itemModels.generateFlatItem(
                ModItems.EXPLOSIVE_ARROW.get(),
                ModelTemplates.FLAT_ITEM
        );
    }

    private static void generateFlatHandheldItems(
            ItemModelGenerators itemModels,
            Item... items
    ) {
        for (Item item : items) {
            itemModels.generateFlatItem(
                    item,
                    ModelTemplates.FLAT_HANDHELD_ITEM
            );
        }
    }

    private static void generateWarhammer(
            ItemModelGenerators itemModels,
            Item warhammer
    ) {
        Identifier bluntModel =
                itemModels.createFlatItemModel(
                        warhammer,
                        ModelTemplates.FLAT_HANDHELD_ITEM
                );

        Identifier sharpModel =
                itemModels.createFlatItemModel(
                        warhammer,
                        "_sharp",
                        ModelTemplates.FLAT_HANDHELD_ITEM
                );

        itemModels.itemModelOutput.accept(
                warhammer,
                new RangeSelectItemModel.Unbaked(
                        new CustomModelDataProperty(0),
                        1.0F,
                        List.of(
                                new RangeSelectItemModel.Entry(
                                        1.0F,
                                        model(sharpModel)
                                )
                        ),
                        Optional.of(model(bluntModel))
                )
        );
    }

    private static void generateLongbow(
            ItemModelGenerators itemModels
    ) {
        Item longbow = ModItems.LONGBOW.get();

        ItemModel.Unbaked base = model(
                itemModels.createFlatItemModel(
                        longbow,
                        ModelTemplates.BOW
                )
        );
        ItemModel.Unbaked pulling0 = model(
                itemModels.createFlatItemModel(
                        longbow,
                        "_pulling_0",
                        ModelTemplates.BOW
                )
        );
        ItemModel.Unbaked pulling1 = model(
                itemModels.createFlatItemModel(
                        longbow,
                        "_pulling_1",
                        ModelTemplates.BOW
                )
        );
        ItemModel.Unbaked pulling2 = model(
                itemModels.createFlatItemModel(
                        longbow,
                        "_pulling_2",
                        ModelTemplates.BOW
                )
        );

        ItemModel.Unbaked pulling =
                new RangeSelectItemModel.Unbaked(
                        new UseDuration(false),
                        LONGBOW_DURATION_SCALE,
                        List.of(
                                new RangeSelectItemModel.Entry(
                                        0.65F,
                                        pulling1
                                ),
                                new RangeSelectItemModel.Entry(
                                        0.90F,
                                        pulling2
                                )
                        ),
                        Optional.of(pulling0)
                );

        itemModels.itemModelOutput.accept(
                longbow,
                new ConditionalItemModel.Unbaked(
                        new IsUsingItem(),
                        pulling,
                        base
                )
        );
    }

    private static void generateChainCrossbow(
            ItemModelGenerators itemModels
    ) {
        Item crossbow = ModItems.CHAIN_CROSSBOW.get();

        ItemModel.Unbaked base = model(
                itemModels.createFlatItemModel(
                        crossbow,
                        ModelTemplates.CROSSBOW
                )
        );
        ItemModel.Unbaked arrow = model(
                itemModels.createFlatItemModel(
                        crossbow,
                        "_arrow",
                        ModelTemplates.CROSSBOW
                )
        );
        ItemModel.Unbaked explosiveArrow = model(
                itemModels.createFlatItemModel(
                        crossbow,
                        "_explosive_arrow",
                        ModelTemplates.CROSSBOW
                )
        );
        ItemModel.Unbaked firework = model(
                itemModels.createFlatItemModel(
                        crossbow,
                        "_firework",
                        ModelTemplates.CROSSBOW
                )
        );
        ItemModel.Unbaked pulling0 = model(
                itemModels.createFlatItemModel(
                        crossbow,
                        "_pulling_0",
                        ModelTemplates.CROSSBOW
                )
        );
        ItemModel.Unbaked pulling1 = model(
                itemModels.createFlatItemModel(
                        crossbow,
                        "_pulling_1",
                        ModelTemplates.CROSSBOW
                )
        );
        ItemModel.Unbaked pulling2 = model(
                itemModels.createFlatItemModel(
                        crossbow,
                        "_pulling_2",
                        ModelTemplates.CROSSBOW
                )
        );

        ItemModel.Unbaked pulling =
                new RangeSelectItemModel.Unbaked(
                        new CrossbowPull(),
                        1.0F,
                        List.of(
                                new RangeSelectItemModel.Entry(
                                        0.58F,
                                        pulling1
                                ),
                                new RangeSelectItemModel.Entry(
                                        1.0F,
                                        pulling2
                                )
                        ),
                        Optional.of(pulling0)
                );

        ItemModel.Unbaked usingOrBase =
                new ConditionalItemModel.Unbaked(
                        new IsUsingItem(),
                        pulling,
                        base
                );

        ItemModel.Unbaked normalCharge = chargeSelector(
                arrow,
                firework,
                usingOrBase
        );

        ItemModel.Unbaked explosiveCharge = chargeSelector(
                explosiveArrow,
                firework,
                usingOrBase
        );

        itemModels.itemModelOutput.accept(
                crossbow,
                new RangeSelectItemModel.Unbaked(
                        new CustomModelDataProperty(0),
                        1.0F,
                        List.of(
                                new RangeSelectItemModel.Entry(
                                        1.0F,
                                        explosiveCharge
                                )
                        ),
                        Optional.of(normalCharge)
                )
        );
    }

    private static ItemModel.Unbaked chargeSelector(
            ItemModel.Unbaked arrow,
            ItemModel.Unbaked rocket,
            ItemModel.Unbaked fallback
    ) {
        return new SelectItemModel.Unbaked(
                new SelectItemModel.UnbakedSwitch<>(
                        new Charge(),
                        List.of(
                                new SelectItemModel.SwitchCase<>(
                                        List.of(
                                                CrossbowItem.ChargeType.ARROW
                                        ),
                                        arrow
                                ),
                                new SelectItemModel.SwitchCase<>(
                                        List.of(
                                                CrossbowItem.ChargeType.ROCKET
                                        ),
                                        rocket
                                )
                        )
                ),
                Optional.of(fallback)
        );
    }

    private static Identifier createNamedModel(
            ItemModelGenerators itemModels,
            String name,
            net.minecraft.client.data.models.model.ModelTemplate template
    ) {
        Identifier location = itemModel(name);

        return template.create(
                location,
                TextureMapping.layer0(location),
                itemModels.modelOutput
        );
    }

    private static ItemModel.Unbaked model(
            Identifier location
    ) {
        return new BlockModelWrapper.Unbaked(
                location,
                List.of()
        );
    }

    private static Identifier itemModel(String path) {
        return Identifier.fromNamespaceAndPath(
                WeaponsExpanded.MOD_ID,
                "item/" + path
        );
    }
}