package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.util.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.references.ItemIds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(output, completableFuture);
    }

    private static ResourceKey<Item> key(Item item) {
        return BuiltInRegistries.ITEM.getResourceKey(item).orElseThrow();
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
        builder(ItemTags.MELEE_WEAPON_ENCHANTABLE)
                .forceAddTag(ItemTags.AXES)
                .forceAddTag(ModItemTags.BLUNT)
                .forceAddTag(ModItemTags.PIERCE);

        builder(ModItemTags.CLEAVING_ENCHANTABLE)
                .forceAddTag(ItemTags.AXES)
                .forceAddTag(ModItemTags.BLUNT);

        builder(ItemTags.DURABILITY_ENCHANTABLE)
                .forceAddTag(ModItemTags.BLUNT)
                .forceAddTag(ModItemTags.PIERCE)
                .add(key(ModItems.LONGBOW))
                .add(key(ModItems.CHAIN_CROSSBOW));

        builder(ItemTags.MINING_ENCHANTABLE)
                .forceAddTag(ModItemTags.BLUNT);

        builder(ItemTags.BOW_ENCHANTABLE)
                .add(key(ModItems.LONGBOW));

        builder(ItemTags.CROSSBOW_ENCHANTABLE)
                .add(key(ModItems.CHAIN_CROSSBOW));

        builder(ItemTags.PIGLIN_LOVED)
                .add(key(ModItems.GOLDEN_BROADSWORD))
                .add(key(ModItems.GOLDEN_SICKLE))
                .add(key(ModItems.GOLDEN_SCYTHE))
                .add(key(ModItems.GOLDEN_LONGSWORD))
                .add(key(ModItems.GOLDEN_KATANA))
                .add(key(ModItems.GOLDEN_HATCHET))
                .add(key(ModItems.GOLDEN_HAMMER))
                .add(key(ModItems.GOLDEN_WARHAMMER))
                .add(key(ModItems.GOLDEN_BATTLEAXE))
                .add(key(ModItems.GOLDEN_GREATSWORD));

        builder(ModItemTags.WOODEN_WEAPON_FUEL)
                .add(key(ModItems.WOODEN_RAPIER))
                .add(key(ModItems.WOODEN_BROADSWORD))
                .add(key(ModItems.WOODEN_SICKLE))
                .add(key(ModItems.WOODEN_SCYTHE))
                .add(key(ModItems.WOODEN_LONGSWORD))
                .add(key(ModItems.WOODEN_KATANA))
                .add(key(ModItems.WOODEN_GREATSWORD))
                .add(key(ModItems.WOODEN_HATCHET))
                .add(key(ModItems.WOODEN_HAMMER))
                .add(key(ModItems.WOODEN_WARHAMMER))
                .add(key(ModItems.WOODEN_BATTLEAXE));

        builder(ModItemTags.DIAMOND_WEAPON)
                .add(ItemIds.DIAMOND_SWORD)
                .add(ItemIds.DIAMOND_AXE)
                .add(ItemIds.DIAMOND_SPEAR)
                .add(key(ModItems.DIAMOND_RAPIER))
                .add(key(ModItems.DIAMOND_BROADSWORD))
                .add(key(ModItems.DIAMOND_SICKLE))
                .add(key(ModItems.DIAMOND_SCYTHE))
                .add(key(ModItems.DIAMOND_LONGSWORD))
                .add(key(ModItems.DIAMOND_KATANA))
                .add(key(ModItems.DIAMOND_GREATSWORD))
                .add(key(ModItems.DIAMOND_HATCHET))
                .add(key(ModItems.DIAMOND_HAMMER))
                .add(key(ModItems.DIAMOND_WARHAMMER))
                .add(key(ModItems.DIAMOND_BATTLEAXE));

        builder(ModItemTags.NETHERITE_WEAPON)
                .add(ItemIds.NETHERITE_SWORD)
                .add(ItemIds.NETHERITE_AXE)
                .add(ItemIds.NETHERITE_SPEAR)
                .add(key(ModItems.NETHERITE_RAPIER))
                .add(key(ModItems.NETHERITE_BROADSWORD))
                .add(key(ModItems.NETHERITE_SICKLE))
                .add(key(ModItems.NETHERITE_SCYTHE))
                .add(key(ModItems.NETHERITE_LONGSWORD))
                .add(key(ModItems.NETHERITE_KATANA))
                .add(key(ModItems.NETHERITE_GREATSWORD))
                .add(key(ModItems.NETHERITE_HATCHET))
                .add(key(ModItems.NETHERITE_HAMMER))
                .add(key(ModItems.NETHERITE_WARHAMMER))
                .add(key(ModItems.NETHERITE_BATTLEAXE));

        builder(ModItemTags.TWOHANDED)
                .add(key(ModItems.WOODEN_SCYTHE))
                .add(key(ModItems.GOLDEN_SCYTHE))
                .add(key(ModItems.STONE_SCYTHE))
                .add(key(ModItems.COPPER_SCYTHE))
                .add(key(ModItems.IRON_SCYTHE))
                .add(key(ModItems.DIAMOND_SCYTHE))
                .add(key(ModItems.NETHERITE_SCYTHE))

                .add(key(ModItems.WOODEN_KATANA))
                .add(key(ModItems.GOLDEN_KATANA))
                .add(key(ModItems.STONE_KATANA))
                .add(key(ModItems.COPPER_KATANA))
                .add(key(ModItems.IRON_KATANA))
                .add(key(ModItems.DIAMOND_KATANA))
                .add(key(ModItems.NETHERITE_KATANA))

                .add(key(ModItems.WOODEN_GREATSWORD))
                .add(key(ModItems.GOLDEN_GREATSWORD))
                .add(key(ModItems.STONE_GREATSWORD))
                .add(key(ModItems.COPPER_GREATSWORD))
                .add(key(ModItems.IRON_GREATSWORD))
                .add(key(ModItems.DIAMOND_GREATSWORD))
                .add(key(ModItems.NETHERITE_GREATSWORD));

        builder(ItemTags.ARROWS)
                .add(key(ModItems.HEAVY_ARROW))
                .add(key(ModItems.EXPLOSIVE_ARROW));

        builder(ItemTags.SWORDS)
                .add(key(ModItems.WOODEN_BROADSWORD))
                .add(key(ModItems.GOLDEN_BROADSWORD))
                .add(key(ModItems.STONE_BROADSWORD))
                .add(key(ModItems.COPPER_BROADSWORD))
                .add(key(ModItems.IRON_BROADSWORD))
                .add(key(ModItems.DIAMOND_BROADSWORD))
                .add(key(ModItems.NETHERITE_BROADSWORD))

                .add(key(ModItems.WOODEN_SICKLE))
                .add(key(ModItems.GOLDEN_SICKLE))
                .add(key(ModItems.STONE_SICKLE))
                .add(key(ModItems.COPPER_SICKLE))
                .add(key(ModItems.IRON_SICKLE))
                .add(key(ModItems.DIAMOND_SICKLE))
                .add(key(ModItems.NETHERITE_SICKLE))

                .add(key(ModItems.WOODEN_SCYTHE))
                .add(key(ModItems.GOLDEN_SCYTHE))
                .add(key(ModItems.STONE_SCYTHE))
                .add(key(ModItems.COPPER_SCYTHE))
                .add(key(ModItems.IRON_SCYTHE))
                .add(key(ModItems.DIAMOND_SCYTHE))
                .add(key(ModItems.NETHERITE_SCYTHE))

                .add(key(ModItems.WOODEN_LONGSWORD))
                .add(key(ModItems.GOLDEN_LONGSWORD))
                .add(key(ModItems.STONE_LONGSWORD))
                .add(key(ModItems.COPPER_LONGSWORD))
                .add(key(ModItems.IRON_LONGSWORD))
                .add(key(ModItems.DIAMOND_LONGSWORD))
                .add(key(ModItems.NETHERITE_LONGSWORD))

                .add(key(ModItems.WOODEN_KATANA))
                .add(key(ModItems.GOLDEN_KATANA))
                .add(key(ModItems.STONE_KATANA))
                .add(key(ModItems.COPPER_KATANA))
                .add(key(ModItems.IRON_KATANA))
                .add(key(ModItems.DIAMOND_KATANA))
                .add(key(ModItems.NETHERITE_KATANA))

                .add(key(ModItems.WOODEN_GREATSWORD))
                .add(key(ModItems.GOLDEN_GREATSWORD))
                .add(key(ModItems.STONE_GREATSWORD))
                .add(key(ModItems.COPPER_GREATSWORD))
                .add(key(ModItems.IRON_GREATSWORD))
                .add(key(ModItems.DIAMOND_GREATSWORD))
                .add(key(ModItems.NETHERITE_GREATSWORD))

                .add(key(ModItems.WOODEN_WARHAMMER))
                .add(key(ModItems.GOLDEN_WARHAMMER))
                .add(key(ModItems.STONE_WARHAMMER))
                .add(key(ModItems.COPPER_WARHAMMER))
                .add(key(ModItems.IRON_WARHAMMER))
                .add(key(ModItems.DIAMOND_WARHAMMER))
                .add(key(ModItems.NETHERITE_WARHAMMER));

        builder(ItemTags.AXES)
                .add(key(ModItems.WOODEN_HATCHET))
                .add(key(ModItems.GOLDEN_HATCHET))
                .add(key(ModItems.STONE_HATCHET))
                .add(key(ModItems.COPPER_HATCHET))
                .add(key(ModItems.IRON_HATCHET))
                .add(key(ModItems.DIAMOND_HATCHET))
                .add(key(ModItems.NETHERITE_HATCHET))

                .add(key(ModItems.WOODEN_BATTLEAXE))
                .add(key(ModItems.GOLDEN_BATTLEAXE))
                .add(key(ModItems.STONE_BATTLEAXE))
                .add(key(ModItems.COPPER_BATTLEAXE))
                .add(key(ModItems.IRON_BATTLEAXE))
                .add(key(ModItems.DIAMOND_BATTLEAXE))
                .add(key(ModItems.NETHERITE_BATTLEAXE));

        builder(ModItemTags.BLUNT)
                .add(key(ModItems.WOODEN_HAMMER))
                .add(key(ModItems.GOLDEN_HAMMER))
                .add(key(ModItems.STONE_HAMMER))
                .add(key(ModItems.COPPER_HAMMER))
                .add(key(ModItems.IRON_HAMMER))
                .add(key(ModItems.DIAMOND_HAMMER))
                .add(key(ModItems.NETHERITE_HAMMER))

                .add(key(ModItems.WOODEN_WARHAMMER))
                .add(key(ModItems.GOLDEN_WARHAMMER))
                .add(key(ModItems.STONE_WARHAMMER))
                .add(key(ModItems.COPPER_WARHAMMER))
                .add(key(ModItems.IRON_WARHAMMER))
                .add(key(ModItems.DIAMOND_WARHAMMER))
                .add(key(ModItems.NETHERITE_WARHAMMER));

        builder(ModItemTags.PIERCE)
                .add(key(ModItems.WOODEN_RAPIER))
                .add(key(ModItems.GOLDEN_RAPIER))
                .add(key(ModItems.STONE_RAPIER))
                .add(key(ModItems.COPPER_RAPIER))
                .add(key(ModItems.IRON_RAPIER))
                .add(key(ModItems.DIAMOND_RAPIER))
                .add(key(ModItems.NETHERITE_RAPIER));
    }
}
