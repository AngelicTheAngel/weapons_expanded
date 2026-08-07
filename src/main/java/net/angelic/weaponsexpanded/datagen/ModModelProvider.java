package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.WOODEN_BROADSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_BROADSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_BROADSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_BROADSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_BROADSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_BROADSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WOODEN_SICKLE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_SICKLE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_SICKLE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_SICKLE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_SICKLE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_SICKLE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WOODEN_SCYTHE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_SCYTHE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_SCYTHE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_SCYTHE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_SCYTHE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_SCYTHE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WOODEN_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_LONGSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WOODEN_KATANA, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_KATANA, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_KATANA, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_KATANA, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_KATANA, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_KATANA, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WOODEN_HATCHET, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_HATCHET, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_HATCHET, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_HATCHET, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_HATCHET, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_HATCHET, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WOODEN_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_HAMMER, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WOODEN_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_BATTLEAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.WOODEN_GREATSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_GREATSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.STONE_GREATSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.IRON_GREATSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DIAMOND_GREATSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.NETHERITE_GREATSWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.HEAVY_ARROW, Models.GENERATED);
        itemModelGenerator.register(ModItems.EXPLOSIVE_ARROW, Models.GENERATED);
    }
}
