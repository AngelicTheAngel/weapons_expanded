package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.EnchantRandomlyLootFunction;
import net.minecraft.loot.function.EnchantWithLevelsLootFunction;
import net.minecraft.loot.function.SetDamageLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

public class ModLootTableModifiers {

    // Trial chamber tables don’t always have constants; match by ID.
    private static final Identifier TRIAL_ENTRANCE = Identifier.of("minecraft", "chests/trial_chambers/entrance");
    private static final Identifier TRIAL_CORRIDOR = Identifier.of("minecraft", "chests/trial_chambers/corridor");
    private static final Identifier TRIAL_INTERSECTION = Identifier.of("minecraft", "chests/trial_chambers/intersection");
    private static final Identifier TRIAL_INTERSECTION_BARREL = Identifier.of("minecraft", "chests/trial_chambers/intersection_barrel");
    private static final Identifier TRIAL_REWARD_RARE = Identifier.of("minecraft", "chests/trial_chambers/reward_rare");
    private static final Identifier TRIAL_REWARD_OMINOUS_RARE = Identifier.of("minecraft", "chests/trial_chambers/reward_ominous_rare");

    // More vanilla loot tables by ID (safe even if constants change)
    private static final Identifier VILLAGE_WEAPONSMITH = Identifier.of("minecraft", "chests/village/village_weaponsmith");

    // Chance tuning:
    // - COMMON: most mod loot rolls (30-40%)
    // - SPECIAL: diamond treasure + enchanted gear (10-20%)
    private static final float CHANCE_COMMON = 0.35f;
    private static final float CHANCE_SPECIAL = 0.15f;
    private static final float CHANCE_SPECIAL_HIGH = 0.20f;

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            var cfg = WeaponsExpandedConfig.get();
            Identifier id = key.getValue();

            // --- General toggle: custom chest loot tweaks ---
            if (!cfg.enableCustomLootTables) return;

            // =========================
            // Existing conversions (keep / adjust as you like)
            // =========================
            if (LootTables.IGLOO_CHEST_CHEST.equals(key)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_COMMON))
                        .with(weightedItem(ModItems.STONE_HATCHET, 1));
                tableBuilder.pool(pool.build());
            }

            if (VILLAGE_WEAPONSMITH.equals(id)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_COMMON))
                        .with(weightedItem(ModItems.IRON_LONGSWORD, 2))
                        .with(weightedItem(ModItems.IRON_HAMMER, 1));
                tableBuilder.pool(pool.build());
            }

            if (LootTables.PILLAGER_OUTPOST_CHEST.equals(key)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_COMMON))
                        .with(weightedItem(ModItems.CHAIN_CROSSBOW, 1));
                tableBuilder.pool(pool.build());
            }

            if (TRIAL_ENTRANCE.equals(id)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_COMMON))
                        .with(weightedItem(ModItems.WOODEN_BATTLEAXE, 3))
                        .with(weightedItem(ModItems.WOODEN_HATCHET, 3));
                tableBuilder.pool(pool.build());
            }

            if (TRIAL_CORRIDOR.equals(id)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL))
                        .with(enchantRandomly(withDamage(weightedItem(ModItems.IRON_HATCHET, 1), 0.4f, 0.9f), registry))
                        .with(enchantRandomly(withDamage(weightedItem(ModItems.IRON_BATTLEAXE, 1), 0.4f, 0.9f), registry));
                tableBuilder.pool(pool.build());
            }

            if (TRIAL_INTERSECTION.equals(id)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL))
                        .with(withDamage(weightedItem(ModItems.DIAMOND_HATCHET, 2), 0.1f, 0.5f))
                        .with(withDamage(weightedItem(ModItems.DIAMOND_BATTLEAXE, 1), 0.1f, 0.5f));
                tableBuilder.pool(pool.build());
            }

            if (TRIAL_INTERSECTION_BARREL.equals(id)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL))
                        .with(enchantRandomly(withDamage(weightedItem(ModItems.DIAMOND_HATCHET, 1), 0.4f, 0.9f), registry))
                        .with(enchantRandomly(withDamage(weightedItem(ModItems.DIAMOND_BATTLEAXE, 1), 0.4f, 0.9f), registry))
                        .with(withDamage(weightedItem(ModItems.GOLDEN_HATCHET, 1), 0.15f, 0.8f))
                        .with(withDamage(weightedItem(ModItems.GOLDEN_BATTLEAXE, 1), 0.15f, 0.8f));
                tableBuilder.pool(pool.build());
            }

            if (LootTables.RUINED_PORTAL_CHEST.equals(key)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL_HIGH))
                        .with(enchantRandomly(weightedItem(ModItems.GOLDEN_LONGSWORD, 5), registry))
                        .with(enchantRandomly(weightedItem(ModItems.GOLDEN_GREATSWORD, 5), registry))
                        .with(enchantRandomly(weightedItem(ModItems.GOLDEN_BATTLEAXE, 5), registry))
                        .with(enchantRandomly(weightedItem(ModItems.GOLDEN_HAMMER, 5), registry));
                tableBuilder.pool(pool.build());
            }

            if (LootTables.NETHER_BRIDGE_CHEST.equals(key)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_COMMON))
                        .with(weightedItem(ModItems.GOLDEN_LONGSWORD, 2))
                        .with(weightedItem(ModItems.GOLDEN_GREATSWORD, 1));
                tableBuilder.pool(pool.build());
            }

            if (TRIAL_REWARD_RARE.equals(id)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL))
                        .with(enchantWithLevels(weightedItem(ModItems.DIAMOND_HAMMER, 1), registry, 5, 15));
                tableBuilder.pool(pool.build());
            }

            if (TRIAL_REWARD_OMINOUS_RARE.equals(id)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL))
                        .with(enchantWithLevels(weightedItem(ModItems.DIAMOND_BATTLEAXE, 1), registry, 10, 20));
                tableBuilder.pool(pool.build());
            }

            // ==========================================================
            // Conversions for the attached JSON files (mod items only)
            // ==========================================================

            // underwater_ruin_small.json -> weaponsexpanded:stone_hammer weight 1
            if (LootTables.UNDERWATER_RUIN_SMALL_CHEST.equals(key)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_COMMON))
                        .with(weightedItem(ModItems.STONE_HAMMER, 1));
                tableBuilder.pool(pool.build());
            }

            // stronghold_corridor.json -> weaponsexpanded:iron_hammer (2), weaponsexpanded:iron_katana (1)
            if (LootTables.STRONGHOLD_CORRIDOR_CHEST.equals(key)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_COMMON))
                        .with(weightedItem(ModItems.IRON_HAMMER, 2))
                        .with(weightedItem(ModItems.IRON_KATANA, 1));
                tableBuilder.pool(pool.build());
            }

            // buried_treasure.json -> weaponsexpanded:iron_broadsword, iron_sickle, iron_scythe
            if (LootTables.BURIED_TREASURE_CHEST.equals(key)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_COMMON))
                        .with(weightedItem(ModItems.IRON_BROADSWORD, 1))
                        .with(weightedItem(ModItems.IRON_SICKLE, 1))
                        .with(weightedItem(ModItems.IRON_SCYTHE, 1));
                tableBuilder.pool(pool.build());
            }

            // bastion_bridge.json -> weaponsexpanded:golden_longsword (no enchant), weaponsexpanded:golden_battleaxe (enchanted randomly)
            if (LootTables.BASTION_BRIDGE_CHEST.equals(key)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL_HIGH))
                        .with(weightedItem(ModItems.GOLDEN_LONGSWORD, 1))
                        .with(enchantRandomly(weightedItem(ModItems.GOLDEN_BATTLEAXE, 1), registry))
                        .with(enchantRandomly(weightedItem(ModItems.CHAIN_CROSSBOW, 2), registry));
                tableBuilder.pool(pool.build());
            }

            // bastion_hoglin_stable.json -> weaponsexpanded:golden_battleaxe (enchanted randomly)
            if (LootTables.BASTION_HOGLIN_STABLE_CHEST.equals(key)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL_HIGH))
                        .with(enchantRandomly(weightedItem(ModItems.GOLDEN_BATTLEAXE, 1), registry));
                tableBuilder.pool(pool.build());
            }

            // bastion_other.json -> weaponsexpanded:iron_scythe (damage + enchant_randomly), weaponsexpanded:golden_hatchet (enchant_randomly), weaponsexpanded:golden_longsword
            if (LootTables.BASTION_OTHER_CHEST.equals(key)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL_HIGH))
                        .with(enchantRandomly(withDamage(weightedItem(ModItems.IRON_SCYTHE, 2), 0.1f, 0.9f), registry))
                        .with(enchantRandomly(weightedItem(ModItems.GOLDEN_HATCHET, 1), registry))
                        .with(weightedItem(ModItems.GOLDEN_LONGSWORD, 1))
                        .with(weightedItem(ModItems.CHAIN_CROSSBOW, 1));
                tableBuilder.pool(pool.build());
            }

            // bastion_treasure.json -> weaponsexpanded diamond weapons
            if (LootTables.BASTION_TREASURE_CHEST.equals(key)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL))
                        .with(enchantRandomly(withDamage(weightedItem(ModItems.DIAMOND_SCYTHE, 2), 0.8f, 1.0f), registry))
                        .with(enchantRandomly(withDamage(weightedItem(ModItems.DIAMOND_LONGSWORD, 2), 0.8f, 1.0f), registry))
                        .with(weightedItem(ModItems.DIAMOND_GREATSWORD, 2))
                        .with(weightedItem(ModItems.DIAMOND_KATANA, 2));
                tableBuilder.pool(pool.build());
            }

            // end_city_treasure.json -> weaponsexpanded:diamond_scythe, diamond_greatsword, iron_longsword, iron_greatsword
            if (LootTables.END_CITY_TREASURE_CHEST.equals(key)) {
                LootPool.Builder pool = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL))
                        .with(enchantWithLevels(weightedItem(ModItems.DIAMOND_SCYTHE, 1), registry, 20, 39))
                        .with(enchantWithLevels(weightedItem(ModItems.DIAMOND_GREATSWORD, 1), registry, 20, 39))
                        .with(enchantWithLevels(weightedItem(ModItems.IRON_LONGSWORD, 1), registry, 20, 39))
                        .with(enchantWithLevels(weightedItem(ModItems.IRON_GREATSWORD, 1), registry, 20, 39));
                tableBuilder.pool(pool.build());
            }
        });
    }

    // -------- helper builders --------
    // IMPORTANT: keep the return type as LeafEntry.Builder<?> so apply(...) exists.

    private static LeafEntry.Builder<?> weightedItem(net.minecraft.item.Item item, int weight) {
        return ItemEntry.builder(item).weight(weight);
    }

    private static LeafEntry.Builder<?> withDamage(LeafEntry.Builder<?> entry, float min, float max) {
        return entry.apply(SetDamageLootFunction.builder(UniformLootNumberProvider.create(min, max)));
    }

    private static LeafEntry.Builder<?> enchantRandomly(LeafEntry.Builder<?> entry, RegistryWrapper.WrapperLookup registry) {
        return entry.apply(EnchantRandomlyLootFunction.builder(registry));
    }

    private static LeafEntry.Builder<?> enchantWithLevels(LeafEntry.Builder<?> entry, RegistryWrapper.WrapperLookup registry, int min, int max) {
        return entry.apply(EnchantWithLevelsLootFunction.builder(registry, UniformLootNumberProvider.create(min, max)));
    }
}
