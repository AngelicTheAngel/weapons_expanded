package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.datagen.villager.ModVillagerTrades;
import net.angelic.weaponsexpanded.util.conditions.FletcherTradesCondition;
import net.angelic.weaponsexpanded.util.conditions.WeaponsmithTradesCondition;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("NullableProblems")
public class ModRegistryDataProvider extends FabricDynamicRegistryProvider {
    public ModRegistryDataProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        List<ResourceKey<VillagerTrade>> weaponsmithTrades = List.of(
                ModVillagerTrades.WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_HATCHET,
                ModVillagerTrades.WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_MORNINGSTAR,
                ModVillagerTrades.WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_WARHAMMER,
                ModVillagerTrades.WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_HAMMER,
                ModVillagerTrades.WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_BATTLEAXE,
                ModVillagerTrades.WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_HALBERD,

                ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_RAPIER,
                ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_BROADSWORD,
                ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_SICKLE,
                ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_GLAIVE,
                ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_SCYTHE,
                ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_LONGSWORD,
                ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_GREATSWORD,
                ModVillagerTrades.WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_KATANA
        );

        List<ResourceKey<VillagerTrade>> fletcherTrades = List.of(
                ModVillagerTrades.FLETCHER_2_EMERALD_LONGBOW,
                ModVillagerTrades.FLETCHER_3_EMERALD_CHAIN_CROSSBOW,
                ModVillagerTrades.FLETCHER_4_EMERALD_DYNAMITE_ARROW,
                ModVillagerTrades.FLETCHER_4_EMERALD_ENCHANTED_LONGBOW,
                ModVillagerTrades.FLETCHER_5_EMERALD_ENCHANTED_CHAIN_CROSSBOW
        );

        for (ResourceKey<VillagerTrade> trade : weaponsmithTrades) {
            entries.add(registries.lookupOrThrow(Registries.VILLAGER_TRADE), trade, WeaponsmithTradesCondition.INSTANCE);
        }

        for (ResourceKey<VillagerTrade> trade : fletcherTrades) {
            entries.add(registries.lookupOrThrow(Registries.VILLAGER_TRADE), trade, FletcherTradesCondition.INSTANCE);
        }

        entries.addAll(registries.lookupOrThrow(Registries.DAMAGE_TYPE));
    }

    @Override
    public String getName() {
        return "Weapons Expanded Registry Provider";
    }
}
