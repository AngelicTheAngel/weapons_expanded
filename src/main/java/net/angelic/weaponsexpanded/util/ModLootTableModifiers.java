package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.LootTableLoadEvent;

public class ModLootTableModifiers {

    // Trial chamber tables don’t always have constants; match by ID.
    private static final Identifier TRIAL_ENTRANCE = Identifier.fromNamespaceAndPath("minecraft", "chests/trial_chambers/entrance");
    private static final Identifier TRIAL_CORRIDOR = Identifier.fromNamespaceAndPath("minecraft", "chests/trial_chambers/corridor");
    private static final Identifier TRIAL_INTERSECTION = Identifier.fromNamespaceAndPath("minecraft", "chests/trial_chambers/intersection");
    private static final Identifier TRIAL_INTERSECTION_BARREL = Identifier.fromNamespaceAndPath("minecraft", "chests/trial_chambers/intersection_barrel");
    private static final Identifier TRIAL_REWARD_RARE = Identifier.fromNamespaceAndPath("minecraft", "chests/trial_chambers/reward_rare");
    private static final Identifier TRIAL_REWARD_OMINOUS_RARE = Identifier.fromNamespaceAndPath("minecraft", "chests/trial_chambers/reward_ominous_rare");

    // More vanilla loot tables by ID (safe even if constants change)
    private static final Identifier VILLAGE_WEAPONSMITH = Identifier.fromNamespaceAndPath("minecraft", "chests/village/village_weaponsmith");

    // Chance tuning:
    // - COMMON: most mod loot rolls (30-40%)
    // - SPECIAL: diamond treasure + enchanted gear (10-20%)
    private static final float CHANCE_COMMON = 0.35f;
    private static final float CHANCE_SPECIAL = 0.15f;
    private static final float CHANCE_SPECIAL_HIGH = 0.20f;

    public static void modifyLootTables() {
        NeoForge.EVENT_BUS.addListener(
                ModLootTableModifiers::modifyLootTable
        );
    }

    private static void modifyLootTable(LootTableLoadEvent event) {
        if (!WeaponsExpandedConfig.lootTables) {
            return;
        }

        LootTable table = event.getTable();
        Identifier id = event.getName();
        HolderLookup.Provider registries = event.getRegistries();

        if (BuiltInLootTables.IGLOO_CHEST.identifier().equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_COMMON))
                    .add(weightedItem(ModItems.STONE_HATCHET, 1));
            table.addPool(pool.build());
        }

        if (VILLAGE_WEAPONSMITH.equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_COMMON))
                    .add(weightedItem(ModItems.IRON_LONGSWORD, 2))
                    .add(weightedItem(ModItems.IRON_HAMMER, 1));
            table.addPool(pool.build());
        }

        if (BuiltInLootTables.PILLAGER_OUTPOST.identifier().equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_COMMON))
                    .add(weightedItem(ModItems.CHAIN_CROSSBOW, 1));
            table.addPool(pool.build());
        }

        if (TRIAL_ENTRANCE.equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_COMMON))
                    .add(weightedItem(ModItems.WOODEN_BATTLEAXE, 3))
                    .add(weightedItem(ModItems.WOODEN_HATCHET, 3));
            table.addPool(pool.build());
        }

        if (TRIAL_CORRIDOR.equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL))
                    .add(enchantRandomly(withDamage(weightedItem(ModItems.IRON_HATCHET, 1), 0.4f, 0.9f), registries))
                    .add(enchantRandomly(withDamage(weightedItem(ModItems.IRON_BATTLEAXE, 1), 0.4f, 0.9f), registries));
            table.addPool(pool.build());
        }

        if (TRIAL_INTERSECTION.equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL))
                    .add(withDamage(weightedItem(ModItems.DIAMOND_HATCHET, 2), 0.1f, 0.5f))
                    .add(withDamage(weightedItem(ModItems.DIAMOND_BATTLEAXE, 1), 0.1f, 0.5f));
            table.addPool(pool.build());
        }

        if (TRIAL_INTERSECTION_BARREL.equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL))
                    .add(enchantRandomly(withDamage(weightedItem(ModItems.DIAMOND_HATCHET, 1), 0.4f, 0.9f), registries))
                    .add(enchantRandomly(withDamage(weightedItem(ModItems.DIAMOND_BATTLEAXE, 1), 0.4f, 0.9f), registries))
                    .add(withDamage(weightedItem(ModItems.GOLDEN_HATCHET, 1), 0.15f, 0.8f))
                    .add(withDamage(weightedItem(ModItems.GOLDEN_BATTLEAXE, 1), 0.15f, 0.8f));
            table.addPool(pool.build());
        }

        if (BuiltInLootTables.RUINED_PORTAL.identifier().equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL_HIGH))
                    .add(enchantRandomly(weightedItem(ModItems.GOLDEN_LONGSWORD, 5), registries))
                    .add(enchantRandomly(weightedItem(ModItems.GOLDEN_GREATSWORD, 5), registries))
                    .add(enchantRandomly(weightedItem(ModItems.GOLDEN_BATTLEAXE, 5), registries))
                    .add(enchantRandomly(weightedItem(ModItems.GOLDEN_HAMMER, 5), registries));
            table.addPool(pool.build());
        }

        if (BuiltInLootTables.NETHER_BRIDGE.identifier().equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_COMMON))
                    .add(weightedItem(ModItems.GOLDEN_LONGSWORD, 2))
                    .add(weightedItem(ModItems.GOLDEN_GREATSWORD, 1));
            table.addPool(pool.build());
        }

        if (TRIAL_REWARD_RARE.equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL))
                    .add(enchantWithLevels(weightedItem(ModItems.IRON_HATCHET, 1), registries, 5, 15));
            table.addPool(pool.build());
        }

        if (TRIAL_REWARD_OMINOUS_RARE.equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL))
                    .add(enchantWithLevels(weightedItem(ModItems.DIAMOND_HAMMER, 1), registries, 10, 20))
                    .add(enchantWithLevels(weightedItem(ModItems.DIAMOND_BATTLEAXE, 1), registries, 10, 20));
            table.addPool(pool.build());
        }

        if (BuiltInLootTables.UNDERWATER_RUIN_SMALL.identifier().equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_COMMON))
                    .add(weightedItem(ModItems.STONE_HAMMER, 1));
            table.addPool(pool.build());
        }

        if (BuiltInLootTables.STRONGHOLD_CORRIDOR.identifier().equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_COMMON))
                    .add(weightedItem(ModItems.IRON_HAMMER, 2))
                    .add(weightedItem(ModItems.IRON_KATANA, 1));
            table.addPool(pool.build());
        }

        if (BuiltInLootTables.BURIED_TREASURE.identifier().equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_COMMON))
                    .add(weightedItem(ModItems.IRON_BROADSWORD, 1))
                    .add(weightedItem(ModItems.IRON_SICKLE, 1))
                    .add(weightedItem(ModItems.IRON_SCYTHE, 1));
            table.addPool(pool.build());
        }

        if (BuiltInLootTables.BASTION_BRIDGE.identifier().equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL_HIGH))
                    .add(weightedItem(ModItems.GOLDEN_LONGSWORD, 1))
                    .add(enchantRandomly(weightedItem(ModItems.GOLDEN_BATTLEAXE, 1), registries))
                    .add(enchantRandomly(weightedItem(ModItems.CHAIN_CROSSBOW, 2), registries));
            table.addPool(pool.build());
        }

        if (BuiltInLootTables.BASTION_HOGLIN_STABLE.identifier().equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL_HIGH))
                    .add(enchantRandomly(weightedItem(ModItems.GOLDEN_BATTLEAXE, 1), registries));
            table.addPool(pool.build());
        }

        if (BuiltInLootTables.BASTION_OTHER.identifier().equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL_HIGH))
                    .add(enchantRandomly(withDamage(weightedItem(ModItems.IRON_SCYTHE, 2), 0.1f, 0.9f), registries))
                    .add(enchantRandomly(weightedItem(ModItems.GOLDEN_HATCHET, 1), registries))
                    .add(weightedItem(ModItems.GOLDEN_LONGSWORD, 1))
                    .add(weightedItem(ModItems.CHAIN_CROSSBOW, 1));
            table.addPool(pool.build());
        }

        if (BuiltInLootTables.BASTION_TREASURE.identifier().equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL))
                    .add(enchantRandomly(withDamage(weightedItem(ModItems.DIAMOND_SCYTHE, 2), 0.8f, 1.0f), registries))
                    .add(enchantRandomly(withDamage(weightedItem(ModItems.DIAMOND_LONGSWORD, 2), 0.8f, 1.0f), registries))
                    .add(weightedItem(ModItems.DIAMOND_GREATSWORD, 2))
                    .add(weightedItem(ModItems.DIAMOND_KATANA, 2));
            table.addPool(pool.build());
        }

        if (BuiltInLootTables.END_CITY_TREASURE.identifier().equals(id)) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL))
                    .add(enchantWithLevels(weightedItem(ModItems.DIAMOND_SCYTHE, 1), registries, 20, 39))
                    .add(enchantWithLevels(weightedItem(ModItems.DIAMOND_GREATSWORD, 1), registries, 20, 39))
                    .add(enchantWithLevels(weightedItem(ModItems.IRON_LONGSWORD, 1), registries, 20, 39))
                    .add(enchantWithLevels(weightedItem(ModItems.IRON_GREATSWORD, 1), registries, 20, 39));
            table.addPool(pool.build());
        }
    }
    private static LootPoolSingletonContainer.Builder<?> weightedItem(
            ItemLike item,
            int weight
    ) {
        return LootItem.lootTableItem(item)
                .setWeight(weight);
    }

    private static LootPoolSingletonContainer.Builder<?> withDamage(
            LootPoolSingletonContainer.Builder<?> entry,
            float min,
            float max
    ) {
        return entry.apply(
                SetItemDamageFunction.setDamage(
                        UniformGenerator.between(min, max)
                )
        );
    }

    private static LootPoolSingletonContainer.Builder<?> enchantRandomly(
            LootPoolSingletonContainer.Builder<?> entry,
            HolderLookup.Provider registries
    ) {
        // Still a no-op until the enchantment function is restored.
        return entry;
    }

    private static LootPoolSingletonContainer.Builder<?> enchantWithLevels(
            LootPoolSingletonContainer.Builder<?> entry,
            HolderLookup.Provider registries,
            int min,
            int max
    ) {
        // Still a no-op until the enchantment function is restored.
        return entry;
    }
}


