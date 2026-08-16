package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.util.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public final class ModItemTagProvider
        extends IntrinsicHolderTagsProvider<Item> {

    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(
                output,
                Registries.ITEM,
                lookupProvider,
                item -> BuiltInRegistries.ITEM
                        .getResourceKey(item)
                        .orElseThrow(),
                WeaponsExpanded.MOD_ID
        );
    }

    @Override
    protected void addTags(
            HolderLookup.@NotNull Provider provider
    ) {
        tag(ItemTags.SWORDS).add(
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

                ModItems.WOODEN_GREATSWORD.get(),
                ModItems.GOLDEN_GREATSWORD.get(),
                ModItems.STONE_GREATSWORD.get(),
                ModItems.COPPER_GREATSWORD.get(),
                ModItems.IRON_GREATSWORD.get(),
                ModItems.DIAMOND_GREATSWORD.get(),
                ModItems.NETHERITE_GREATSWORD.get(),

                ModItems.WOODEN_WARHAMMER.get(),
                ModItems.GOLDEN_WARHAMMER.get(),
                ModItems.STONE_WARHAMMER.get(),
                ModItems.COPPER_WARHAMMER.get(),
                ModItems.IRON_WARHAMMER.get(),
                ModItems.DIAMOND_WARHAMMER.get(),
                ModItems.NETHERITE_WARHAMMER.get()
        );

        tag(ItemTags.MELEE_WEAPON_ENCHANTABLE).add(
                Items.WOODEN_AXE,
                Items.GOLDEN_AXE,
                Items.STONE_AXE,
                Items.STONE_AXE,
                Items.IRON_AXE,
                Items.DIAMOND_AXE,
                Items.NETHERITE_AXE,

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

                ModItems.WOODEN_GREATSWORD.get(),
                ModItems.GOLDEN_GREATSWORD.get(),
                ModItems.STONE_GREATSWORD.get(),
                ModItems.COPPER_GREATSWORD.get(),
                ModItems.IRON_GREATSWORD.get(),
                ModItems.DIAMOND_GREATSWORD.get(),
                ModItems.NETHERITE_GREATSWORD.get(),

                ModItems.WOODEN_WARHAMMER.get(),
                ModItems.GOLDEN_WARHAMMER.get(),
                ModItems.STONE_WARHAMMER.get(),
                ModItems.COPPER_WARHAMMER.get(),
                ModItems.IRON_WARHAMMER.get(),
                ModItems.DIAMOND_WARHAMMER.get(),
                ModItems.NETHERITE_WARHAMMER.get(),

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
                ModItems.NETHERITE_BATTLEAXE.get()
        );

        tag(ItemTags.SWEEPING_ENCHANTABLE).add(
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

                ModItems.WOODEN_GREATSWORD.get(),
                ModItems.GOLDEN_GREATSWORD.get(),
                ModItems.STONE_GREATSWORD.get(),
                ModItems.COPPER_GREATSWORD.get(),
                ModItems.IRON_GREATSWORD.get(),
                ModItems.DIAMOND_GREATSWORD.get(),
                ModItems.NETHERITE_GREATSWORD.get(),

                ModItems.WOODEN_WARHAMMER.get(),
                ModItems.GOLDEN_WARHAMMER.get(),
                ModItems.STONE_WARHAMMER.get(),
                ModItems.COPPER_WARHAMMER.get(),
                ModItems.IRON_WARHAMMER.get(),
                ModItems.DIAMOND_WARHAMMER.get(),
                ModItems.NETHERITE_WARHAMMER.get()
        );

        tag(ItemTags.DURABILITY_ENCHANTABLE).add(
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

                ModItems.WOODEN_GREATSWORD.get(),
                ModItems.GOLDEN_GREATSWORD.get(),
                ModItems.STONE_GREATSWORD.get(),
                ModItems.COPPER_GREATSWORD.get(),
                ModItems.IRON_GREATSWORD.get(),
                ModItems.DIAMOND_GREATSWORD.get(),
                ModItems.NETHERITE_GREATSWORD.get(),

                ModItems.WOODEN_WARHAMMER.get(),
                ModItems.GOLDEN_WARHAMMER.get(),
                ModItems.STONE_WARHAMMER.get(),
                ModItems.COPPER_WARHAMMER.get(),
                ModItems.IRON_WARHAMMER.get(),
                ModItems.DIAMOND_WARHAMMER.get(),
                ModItems.NETHERITE_WARHAMMER.get(),

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
                ModItems.NETHERITE_BATTLEAXE.get()
        );

        tag(ItemTags.MINING_ENCHANTABLE).add(
                ModItems.WOODEN_WARHAMMER.get(),
                ModItems.GOLDEN_WARHAMMER.get(),
                ModItems.STONE_WARHAMMER.get(),
                ModItems.COPPER_WARHAMMER.get(),
                ModItems.IRON_WARHAMMER.get(),
                ModItems.DIAMOND_WARHAMMER.get(),
                ModItems.NETHERITE_WARHAMMER.get(),

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
                ModItems.NETHERITE_BATTLEAXE.get()
        );

        tag(ModItemTags.CLEAVING_ENCHANTABLE).add(
                Items.WOODEN_AXE,
                Items.GOLDEN_AXE,
                Items.STONE_AXE,
                Items.COPPER_AXE,
                Items.IRON_AXE,
                Items.DIAMOND_AXE,
                Items.NETHERITE_AXE,

                ModItems.WOODEN_WARHAMMER.get(),
                ModItems.GOLDEN_WARHAMMER.get(),
                ModItems.STONE_WARHAMMER.get(),
                ModItems.COPPER_WARHAMMER.get(),
                ModItems.IRON_WARHAMMER.get(),
                ModItems.DIAMOND_WARHAMMER.get(),
                ModItems.NETHERITE_WARHAMMER.get(),

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
                ModItems.NETHERITE_BATTLEAXE.get()
        );

        tag(ModItemTags.LEECH_ENCHANTABLE).add(
                Items.SHIELD
        );

        tag(ItemTags.AXES).add(
                ModItems.WOODEN_HATCHET.get(),
                ModItems.GOLDEN_HATCHET.get(),
                ModItems.STONE_HATCHET.get(),
                ModItems.COPPER_HATCHET.get(),
                ModItems.IRON_HATCHET.get(),
                ModItems.DIAMOND_HATCHET.get(),
                ModItems.NETHERITE_HATCHET.get(),

                ModItems.WOODEN_BATTLEAXE.get(),
                ModItems.GOLDEN_BATTLEAXE.get(),
                ModItems.STONE_BATTLEAXE.get(),
                ModItems.COPPER_BATTLEAXE.get(),
                ModItems.IRON_BATTLEAXE.get(),
                ModItems.DIAMOND_BATTLEAXE.get(),
                ModItems.NETHERITE_BATTLEAXE.get()
        );

        tag(ItemTags.PIGLIN_LOVED).add(
                ModItems.GOLDEN_BROADSWORD.get(),
                ModItems.GOLDEN_SICKLE.get(),
                ModItems.GOLDEN_SCYTHE.get(),
                ModItems.GOLDEN_LONGSWORD.get(),
                ModItems.GOLDEN_KATANA.get(),
                ModItems.GOLDEN_HATCHET.get(),
                ModItems.GOLDEN_HAMMER.get(),
                ModItems.GOLDEN_BATTLEAXE.get(),
                ModItems.GOLDEN_GREATSWORD.get(),
                ModItems.GOLDEN_WARHAMMER.get()
        );

        tag(ItemTags.ARROWS).add(
                ModItems.HEAVY_ARROW.get(),
                ModItems.EXPLOSIVE_ARROW.get()
        );

        tag(ItemTags.BOW_ENCHANTABLE).add(
                ModItems.LONGBOW.get()
        );

        tag(ItemTags.CROSSBOW_ENCHANTABLE).add(
                ModItems.CHAIN_CROSSBOW.get()
        );
    }
}