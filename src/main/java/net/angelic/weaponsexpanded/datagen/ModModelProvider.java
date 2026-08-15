package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.function.Supplier;

public final class ModModelProvider
        extends ItemModelProvider {

    public ModModelProvider(
            PackOutput output,
            ExistingFileHelper existingFileHelper
    ) {
        super(
                output,
                WeaponsExpanded.MOD_ID,
                existingFileHelper
        );
    }

    @Override
    protected void registerModels() {
        handheldItem(ModItems.WOODEN_BROADSWORD);
        handheldItem(ModItems.GOLDEN_BROADSWORD);
        handheldItem(ModItems.STONE_BROADSWORD);
        handheldItem(ModItems.IRON_BROADSWORD);
        handheldItem(ModItems.DIAMOND_BROADSWORD);
        handheldItem(ModItems.NETHERITE_BROADSWORD);

        handheldItem(ModItems.WOODEN_SICKLE);
        handheldItem(ModItems.GOLDEN_SICKLE);
        handheldItem(ModItems.STONE_SICKLE);
        handheldItem(ModItems.IRON_SICKLE);
        handheldItem(ModItems.DIAMOND_SICKLE);
        handheldItem(ModItems.NETHERITE_SICKLE);

        handheldItem(ModItems.WOODEN_SCYTHE);
        handheldItem(ModItems.GOLDEN_SCYTHE);
        handheldItem(ModItems.STONE_SCYTHE);
        handheldItem(ModItems.IRON_SCYTHE);
        handheldItem(ModItems.DIAMOND_SCYTHE);
        handheldItem(ModItems.NETHERITE_SCYTHE);

        handheldItem(ModItems.WOODEN_LONGSWORD);
        handheldItem(ModItems.GOLDEN_LONGSWORD);
        handheldItem(ModItems.STONE_LONGSWORD);
        handheldItem(ModItems.IRON_LONGSWORD);
        handheldItem(ModItems.DIAMOND_LONGSWORD);
        handheldItem(ModItems.NETHERITE_LONGSWORD);

        handheldItem(ModItems.WOODEN_KATANA);
        handheldItem(ModItems.GOLDEN_KATANA);
        handheldItem(ModItems.STONE_KATANA);
        handheldItem(ModItems.IRON_KATANA);
        handheldItem(ModItems.DIAMOND_KATANA);
        handheldItem(ModItems.NETHERITE_KATANA);

        handheldItem(ModItems.WOODEN_HATCHET);
        handheldItem(ModItems.GOLDEN_HATCHET);
        handheldItem(ModItems.STONE_HATCHET);
        handheldItem(ModItems.IRON_HATCHET);
        handheldItem(ModItems.DIAMOND_HATCHET);
        handheldItem(ModItems.NETHERITE_HATCHET);

        handheldItem(ModItems.WOODEN_HAMMER);
        handheldItem(ModItems.GOLDEN_HAMMER);
        handheldItem(ModItems.STONE_HAMMER);
        handheldItem(ModItems.IRON_HAMMER);
        handheldItem(ModItems.DIAMOND_HAMMER);
        handheldItem(ModItems.NETHERITE_HAMMER);

        handheldItem(ModItems.WOODEN_BATTLEAXE);
        handheldItem(ModItems.GOLDEN_BATTLEAXE);
        handheldItem(ModItems.STONE_BATTLEAXE);
        handheldItem(ModItems.IRON_BATTLEAXE);
        handheldItem(ModItems.DIAMOND_BATTLEAXE);
        handheldItem(ModItems.NETHERITE_BATTLEAXE);

        handheldItem(ModItems.WOODEN_GREATSWORD);
        handheldItem(ModItems.GOLDEN_GREATSWORD);
        handheldItem(ModItems.STONE_GREATSWORD);
        handheldItem(ModItems.IRON_GREATSWORD);
        handheldItem(ModItems.DIAMOND_GREATSWORD);
        handheldItem(ModItems.NETHERITE_GREATSWORD);

        basicItem(ModItems.HEAVY_ARROW.get());
        basicItem(ModItems.EXPLOSIVE_ARROW.get());
    }

    private ItemModelBuilder handheldItem(
            Supplier<? extends Item> itemSupplier
    ) {
        Item item = itemSupplier.get();

        ResourceLocation itemId =
                BuiltInRegistries.ITEM.getKey(item);

        return withExistingParent(
                itemId.getPath(),
                mcLoc("item/handheld")
        ).texture(
                "layer0",
                modLoc(
                        "item/" + itemId.getPath()
                )
        );
    }
}