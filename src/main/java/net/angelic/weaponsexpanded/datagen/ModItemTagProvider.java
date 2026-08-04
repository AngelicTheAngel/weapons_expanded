package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider
        extends IntrinsicHolderTagsProvider<Item> {

    public ModItemTagProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(
                output,
                Registries.ITEM,
                lookupProvider,
                item -> ForgeRegistries.ITEMS
                        .getResourceKey(item)
                        .orElseThrow(),
                WeaponsExpanded.MOD_ID,
                existingFileHelper
        );
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(ItemTags.SWORDS)
                .add(ModItems.WOODEN_BROADSWORD.get())
                .add(ModItems.GOLDEN_BROADSWORD.get())
                .add(ModItems.STONE_BROADSWORD.get())
                .add(ModItems.IRON_BROADSWORD.get())
                .add(ModItems.DIAMOND_BROADSWORD.get())
                .add(ModItems.NETHERITE_BROADSWORD.get())
                .add(ModItems.WOODEN_SICKLE.get())
                .add(ModItems.GOLDEN_SICKLE.get())
                .add(ModItems.STONE_SICKLE.get())
                .add(ModItems.IRON_SICKLE.get())
                .add(ModItems.DIAMOND_SICKLE.get())
                .add(ModItems.NETHERITE_SICKLE.get())
                .add(ModItems.WOODEN_SCYTHE.get())
                .add(ModItems.GOLDEN_SCYTHE.get())
                .add(ModItems.STONE_SCYTHE.get())
                .add(ModItems.IRON_SCYTHE.get())
                .add(ModItems.DIAMOND_SCYTHE.get())
                .add(ModItems.NETHERITE_SCYTHE.get())
                .add(ModItems.WOODEN_LONGSWORD.get())
                .add(ModItems.GOLDEN_LONGSWORD.get())
                .add(ModItems.STONE_LONGSWORD.get())
                .add(ModItems.IRON_LONGSWORD.get())
                .add(ModItems.DIAMOND_LONGSWORD.get())
                .add(ModItems.NETHERITE_LONGSWORD.get())
                .add(ModItems.WOODEN_KATANA.get())
                .add(ModItems.GOLDEN_KATANA.get())
                .add(ModItems.STONE_KATANA.get())
                .add(ModItems.IRON_KATANA.get())
                .add(ModItems.DIAMOND_KATANA.get())
                .add(ModItems.NETHERITE_KATANA.get())
                .add(ModItems.WOODEN_GREATSWORD.get())
                .add(ModItems.GOLDEN_GREATSWORD.get())
                .add(ModItems.STONE_GREATSWORD.get())
                .add(ModItems.IRON_GREATSWORD.get())
                .add(ModItems.DIAMOND_GREATSWORD.get())
                .add(ModItems.NETHERITE_GREATSWORD.get())
                .add(ModItems.WOODEN_WARHAMMER.get())
                .add(ModItems.GOLDEN_WARHAMMER.get())
                .add(ModItems.STONE_WARHAMMER.get())
                .add(ModItems.IRON_WARHAMMER.get())
                .add(ModItems.DIAMOND_WARHAMMER.get())
                .add(ModItems.NETHERITE_WARHAMMER.get());

        tag(ItemTags.AXES)
                .add(ModItems.WOODEN_HATCHET.get())
                .add(ModItems.GOLDEN_HATCHET.get())
                .add(ModItems.STONE_HATCHET.get())
                .add(ModItems.IRON_HATCHET.get())
                .add(ModItems.DIAMOND_HATCHET.get())
                .add(ModItems.NETHERITE_HATCHET.get())
                .add(ModItems.WOODEN_BATTLEAXE.get())
                .add(ModItems.GOLDEN_BATTLEAXE.get())
                .add(ModItems.STONE_BATTLEAXE.get())
                .add(ModItems.IRON_BATTLEAXE.get())
                .add(ModItems.DIAMOND_BATTLEAXE.get())
                .add(ModItems.NETHERITE_BATTLEAXE.get());

        tag(ItemTags.PIGLIN_LOVED)
                .add(ModItems.GOLDEN_BROADSWORD.get())
                .add(ModItems.GOLDEN_SICKLE.get())
                .add(ModItems.GOLDEN_SCYTHE.get())
                .add(ModItems.GOLDEN_LONGSWORD.get())
                .add(ModItems.GOLDEN_KATANA.get())
                .add(ModItems.GOLDEN_HATCHET.get())
                .add(ModItems.GOLDEN_HAMMER.get())
                .add(ModItems.GOLDEN_BATTLEAXE.get())
                .add(ModItems.GOLDEN_GREATSWORD.get())
                .add(ModItems.GOLDEN_WARHAMMER.get());

        tag(ItemTags.ARROWS)
                .add(ModItems.HEAVY_ARROW.get())
                .add(ModItems.EXPLOSIVE_ARROW.get());
    }
}