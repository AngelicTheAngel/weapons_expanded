package net.angelic.weaponsexpanded.datagen.villager;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.VillagerTradeTags;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.concurrent.CompletableFuture;

public class ModVillagerTradeTags extends FabricTagsProvider<VillagerTrade> {
    public ModVillagerTradeTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.VILLAGER_TRADE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        getOrCreateRawBuilder(VillagerTradeTags.WEAPONSMITH_LEVEL_4)
                .add(TagEntry.optionalElement(ModVillagerTrades.WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_HATCHET.identifier()))
                .add(TagEntry.optionalElement(ModVillagerTrades.WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_MORNINGSTAR.identifier()))
                .add(TagEntry.optionalElement(ModVillagerTrades.WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_WARHAMMER.identifier()))
                .add(TagEntry.optionalElement(ModVillagerTrades.WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_HAMMER.identifier()))
                .add(TagEntry.optionalElement(ModVillagerTrades.WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_BATTLEAXE.identifier()))
                .add(TagEntry.optionalElement(ModVillagerTrades.WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_HALBERD.identifier()));

        getOrCreateRawBuilder(VillagerTradeTags.WEAPONSMITH_LEVEL_5)
                .add(TagEntry.optionalElement(ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_RAPIER.identifier()))
                .add(TagEntry.optionalElement(ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_BROADSWORD.identifier()))
                .add(TagEntry.optionalElement(ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_SICKLE.identifier()))
                .add(TagEntry.optionalElement(ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_GLAIVE.identifier()))
                .add(TagEntry.optionalElement(ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_SCYTHE.identifier()))
                .add(TagEntry.optionalElement(ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_LONGSWORD.identifier()))
                .add(TagEntry.optionalElement(ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_GREATSWORD.identifier()))
                .add(TagEntry.optionalElement(ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_KATANA.identifier()));
    }
}
