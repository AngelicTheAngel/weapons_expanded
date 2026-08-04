package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.ModItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
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

public final class ModLootTableModifiers {

    private static final float CHANCE_COMMON = 0.35f;
    private static final float CHANCE_SPECIAL = 0.15f;
    private static final float CHANCE_SPECIAL_HIGH = 0.20f;

    private ModLootTableModifiers() {
    }

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register(
                (resourceManager, lootManager, id, tableBuilder, source) -> {
                    WeaponsExpandedConfig cfg = WeaponsExpandedConfig.get();

                    if (!cfg.enableCustomLootTables) {
                        return;
                    }

                    /*
                     * Igloo
                     */
                    if (LootTables.IGLOO_CHEST_CHEST.equals(id)) {
                        LootPool.Builder pool = LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(CHANCE_COMMON))
                                .with(weightedItem(ModItems.STONE_HATCHET, 1));

                        tableBuilder.pool(pool);
                    }

                    /*
                     * Village weaponsmith
                     */
                    if (LootTables.VILLAGE_WEAPONSMITH_CHEST.equals(id)) {
                        LootPool.Builder pool = LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(CHANCE_COMMON))
                                .with(weightedItem(ModItems.IRON_LONGSWORD, 2))
                                .with(weightedItem(ModItems.IRON_HAMMER, 1));

                        tableBuilder.pool(pool);
                    }

                    /*
                     * Pillager outpost
                     */
                    if (LootTables.PILLAGER_OUTPOST_CHEST.equals(id)) {
                        LootPool.Builder pool = LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(CHANCE_COMMON))
                                .with(weightedItem(ModItems.CHAIN_CROSSBOW, 1));

                        tableBuilder.pool(pool);
                    }

                    /*
                     * Ruined portal
                     */
                    if (LootTables.RUINED_PORTAL_CHEST.equals(id)) {
                        LootPool.Builder pool = LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL_HIGH))
                                .with(enchantRandomly(
                                        weightedItem(ModItems.GOLDEN_LONGSWORD, 5)
                                ))
                                .with(enchantRandomly(
                                        weightedItem(ModItems.GOLDEN_GREATSWORD, 5)
                                ))
                                .with(enchantRandomly(
                                        weightedItem(ModItems.GOLDEN_BATTLEAXE, 5)
                                ))
                                .with(enchantRandomly(
                                        weightedItem(ModItems.GOLDEN_HAMMER, 5)
                                ));

                        tableBuilder.pool(pool);
                    }

                    /*
                     * Nether fortress
                     */
                    if (LootTables.NETHER_BRIDGE_CHEST.equals(id)) {
                        LootPool.Builder pool = LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(CHANCE_COMMON))
                                .with(weightedItem(ModItems.GOLDEN_LONGSWORD, 2))
                                .with(weightedItem(ModItems.GOLDEN_GREATSWORD, 1));

                        tableBuilder.pool(pool);
                    }

                    /*
                     * Small underwater ruin
                     */
                    if (LootTables.UNDERWATER_RUIN_SMALL_CHEST.equals(id)) {
                        LootPool.Builder pool = LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(CHANCE_COMMON))
                                .with(weightedItem(ModItems.STONE_HAMMER, 1));

                        tableBuilder.pool(pool);
                    }

                    /*
                     * Stronghold corridor
                     */
                    if (LootTables.STRONGHOLD_CORRIDOR_CHEST.equals(id)) {
                        LootPool.Builder pool = LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(CHANCE_COMMON))
                                .with(weightedItem(ModItems.IRON_HAMMER, 2))
                                .with(weightedItem(ModItems.IRON_KATANA, 1));

                        tableBuilder.pool(pool);
                    }

                    /*
                     * Buried treasure
                     */
                    if (LootTables.BURIED_TREASURE_CHEST.equals(id)) {
                        LootPool.Builder pool = LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(CHANCE_COMMON))
                                .with(weightedItem(ModItems.IRON_BROADSWORD, 1))
                                .with(weightedItem(ModItems.IRON_SICKLE, 1))
                                .with(weightedItem(ModItems.IRON_SCYTHE, 1));

                        tableBuilder.pool(pool);
                    }

                    /*
                     * Bastion bridge
                     */
                    if (LootTables.BASTION_BRIDGE_CHEST.equals(id)) {
                        LootPool.Builder pool = LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL_HIGH))
                                .with(weightedItem(ModItems.GOLDEN_LONGSWORD, 1))
                                .with(enchantRandomly(
                                        weightedItem(ModItems.GOLDEN_BATTLEAXE, 1)
                                ))
                                .with(enchantRandomly(
                                        weightedItem(ModItems.CHAIN_CROSSBOW, 2)
                                ));

                        tableBuilder.pool(pool);
                    }

                    /*
                     * Bastion hoglin stable
                     */
                    if (LootTables.BASTION_HOGLIN_STABLE_CHEST.equals(id)) {
                        LootPool.Builder pool = LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL_HIGH))
                                .with(enchantRandomly(
                                        weightedItem(ModItems.GOLDEN_BATTLEAXE, 1)
                                ));

                        tableBuilder.pool(pool);
                    }

                    /*
                     * General bastion chest
                     */
                    if (LootTables.BASTION_OTHER_CHEST.equals(id)) {
                        LootPool.Builder pool = LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL_HIGH))
                                .with(enchantRandomly(
                                        withDamage(
                                                weightedItem(ModItems.IRON_SCYTHE, 2),
                                                0.1f,
                                                0.9f
                                        )
                                ))
                                .with(enchantRandomly(
                                        weightedItem(ModItems.GOLDEN_HATCHET, 1)
                                ))
                                .with(weightedItem(ModItems.GOLDEN_LONGSWORD, 1))
                                .with(weightedItem(ModItems.CHAIN_CROSSBOW, 1));

                        tableBuilder.pool(pool);
                    }

                    /*
                     * Bastion treasure
                     */
                    if (LootTables.BASTION_TREASURE_CHEST.equals(id)) {
                        LootPool.Builder pool = LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL))
                                .with(enchantRandomly(
                                        withDamage(
                                                weightedItem(ModItems.DIAMOND_SCYTHE, 2),
                                                0.8f,
                                                1.0f
                                        )
                                ))
                                .with(enchantRandomly(
                                        withDamage(
                                                weightedItem(ModItems.DIAMOND_LONGSWORD, 2),
                                                0.8f,
                                                1.0f
                                        )
                                ))
                                .with(weightedItem(ModItems.DIAMOND_GREATSWORD, 2))
                                .with(weightedItem(ModItems.DIAMOND_KATANA, 2));

                        tableBuilder.pool(pool);
                    }

                    /*
                     * End city treasure
                     */
                    if (LootTables.END_CITY_TREASURE_CHEST.equals(id)) {
                        LootPool.Builder pool = LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(RandomChanceLootCondition.builder(CHANCE_SPECIAL))
                                .with(enchantWithLevels(
                                        weightedItem(ModItems.DIAMOND_SCYTHE, 1),
                                        20,
                                        39
                                ))
                                .with(enchantWithLevels(
                                        weightedItem(ModItems.DIAMOND_GREATSWORD, 1),
                                        20,
                                        39
                                ))
                                .with(enchantWithLevels(
                                        weightedItem(ModItems.IRON_LONGSWORD, 1),
                                        20,
                                        39
                                ))
                                .with(enchantWithLevels(
                                        weightedItem(ModItems.IRON_GREATSWORD, 1),
                                        20,
                                        39
                                ));

                        tableBuilder.pool(pool);
                    }
                }
        );
    }

    private static LeafEntry.Builder<?> weightedItem(Item item, int weight) {
        return ItemEntry.builder(item).weight(weight);
    }

    private static LeafEntry.Builder<?> withDamage(
            LeafEntry.Builder<?> entry,
            float minimum,
            float maximum
    ) {
        return entry.apply(
                SetDamageLootFunction.builder(
                        UniformLootNumberProvider.create(minimum, maximum)
                )
        );
    }

    private static LeafEntry.Builder<?> enchantRandomly(
            LeafEntry.Builder<?> entry
    ) {
        return entry.apply(
                EnchantRandomlyLootFunction.builder()
        );
    }

    private static LeafEntry.Builder<?> enchantWithLevels(
            LeafEntry.Builder<?> entry,
            int minimumLevel,
            int maximumLevel
    ) {
        return entry.apply(
                EnchantWithLevelsLootFunction.builder(
                        UniformLootNumberProvider.create(
                                minimumLevel,
                                maximumLevel
                        )
                )
        );
    }
}