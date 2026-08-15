package net.angelic.weaponsexpanded.util;

import net.angelic.weaponsexpanded.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemDamageFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.LootTableLoadEvent;

import java.util.function.Supplier;

public final class ModLootTableModifiers {

    private static final float CHANCE_COMMON = 0.35f;
    private static final float CHANCE_SPECIAL = 0.15f;
    private static final float CHANCE_SPECIAL_HIGH = 0.20f;

    private ModLootTableModifiers() {
    }

    public static void modifyLootTables() {
        NeoForge.EVENT_BUS.addListener(
                ModLootTableModifiers::onLootTableLoad
        );
    }

    private static void onLootTableLoad(
            LootTableLoadEvent event
    ) {

        if (!WeaponsExpandedConfig.ENABLE_CUSTOM_LOOT_TABLES.get()) {
            return;
        }

        /*
         * Igloo
         */
        if (BuiltInLootTables.IGLOO_CHEST.location().equals(event.getName())) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_COMMON))
                    .add(weightedItem(ModItems.STONE_HATCHET, 1));

            event.getTable().addPool(pool.build());
        }

        /*
         * Village weaponsmith
         */
        if (BuiltInLootTables.VILLAGE_WEAPONSMITH.location().equals(event.getName())) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_COMMON))
                    .add(weightedItem(ModItems.IRON_LONGSWORD, 2))
                    .add(weightedItem(ModItems.IRON_HAMMER, 1));

            event.getTable().addPool(pool.build());
        }

        /*
         * Pillager outpost
         */
        if (BuiltInLootTables.PILLAGER_OUTPOST.location().equals(event.getName())) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_COMMON))
                    .add(weightedItem(ModItems.CHAIN_CROSSBOW, 1));

            event.getTable().addPool(pool.build());
        }

        /*
         * Ruined portal
         */
        if (BuiltInLootTables.RUINED_PORTAL.location().equals(event.getName())) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL_HIGH))
                    .add(enchantRandomly(
                            weightedItem(ModItems.GOLDEN_LONGSWORD, 5)
                    ))
                    .add(enchantRandomly(
                            weightedItem(ModItems.GOLDEN_GREATSWORD, 5)
                    ))
                    .add(enchantRandomly(
                            weightedItem(ModItems.GOLDEN_BATTLEAXE, 5)
                    ))
                    .add(enchantRandomly(
                            weightedItem(ModItems.GOLDEN_HAMMER, 5)
                    ));

            event.getTable().addPool(pool.build());
        }

        /*
         * Nether fortress
         */
        if (BuiltInLootTables.NETHER_BRIDGE.location().equals(event.getName())) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_COMMON))
                    .add(weightedItem(ModItems.GOLDEN_LONGSWORD, 2))
                    .add(weightedItem(ModItems.GOLDEN_GREATSWORD, 1));

            event.getTable().addPool(pool.build());
        }

        /*
         * Small underwater ruin
         */
        if (BuiltInLootTables.UNDERWATER_RUIN_SMALL.location().equals(event.getName())) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_COMMON))
                    .add(weightedItem(ModItems.STONE_HAMMER, 1));

            event.getTable().addPool(pool.build());
        }

        /*
         * Stronghold corridor
         */
        if (BuiltInLootTables.STRONGHOLD_CORRIDOR.location().equals(event.getName())) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_COMMON))
                    .add(weightedItem(ModItems.IRON_HAMMER, 2))
                    .add(weightedItem(ModItems.IRON_KATANA, 1));

            event.getTable().addPool(pool.build());
        }

        /*
         * Buried treasure
         */
        if (BuiltInLootTables.BURIED_TREASURE.location().equals(event.getName())) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_COMMON))
                    .add(weightedItem(ModItems.IRON_BROADSWORD, 1))
                    .add(weightedItem(ModItems.IRON_SICKLE, 1))
                    .add(weightedItem(ModItems.IRON_SCYTHE, 1));

            event.getTable().addPool(pool.build());
        }

        /*
         * Bastion bridge
         */
        if (BuiltInLootTables.BASTION_BRIDGE.location().equals(event.getName())) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL_HIGH))
                    .add(weightedItem(ModItems.GOLDEN_LONGSWORD, 1))
                    .add(enchantRandomly(
                            weightedItem(ModItems.GOLDEN_BATTLEAXE, 1)
                    ))
                    .add(enchantRandomly(
                            weightedItem(ModItems.CHAIN_CROSSBOW, 2)
                    ));

            event.getTable().addPool(pool.build());
        }

        /*
         * Bastion hoglin stable
         */
        if (BuiltInLootTables.BASTION_HOGLIN_STABLE.location().equals(event.getName())) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL_HIGH))
                    .add(enchantRandomly(
                            weightedItem(ModItems.GOLDEN_BATTLEAXE, 1)
                    ));

            event.getTable().addPool(pool.build());
        }

        /*
         * General bastion chest
         */
        if (BuiltInLootTables.BASTION_OTHER.location().equals(event.getName())) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL_HIGH))
                    .add(enchantRandomly(
                            withDamage(
                                    weightedItem(ModItems.IRON_SCYTHE, 2),
                                    0.1f,
                                    0.9f
                            )
                    ))
                    .add(enchantRandomly(
                            weightedItem(ModItems.GOLDEN_HATCHET, 1)
                    ))
                    .add(weightedItem(ModItems.GOLDEN_LONGSWORD, 1))
                    .add(weightedItem(ModItems.CHAIN_CROSSBOW, 1));

            event.getTable().addPool(pool.build());
        }

        /*
         * Bastion treasure
         */
        if (BuiltInLootTables.BASTION_TREASURE.location().equals(event.getName())) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL))
                    .add(enchantRandomly(
                            withDamage(
                                    weightedItem(ModItems.DIAMOND_SCYTHE, 2),
                                    0.8f,
                                    1.0f
                            )
                    ))
                    .add(enchantRandomly(
                            withDamage(
                                    weightedItem(ModItems.DIAMOND_LONGSWORD, 2),
                                    0.8f,
                                    1.0f
                            )
                    ))
                    .add(weightedItem(ModItems.DIAMOND_GREATSWORD, 2))
                    .add(weightedItem(ModItems.DIAMOND_KATANA, 2));

            event.getTable().addPool(pool.build());
        }

        /*
         * End city treasure
         */
        if (BuiltInLootTables.END_CITY_TREASURE.location().equals(event.getName())) {
            LootPool.Builder pool = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(CHANCE_SPECIAL))
                    .add(enchantWithLevels(
                            weightedItem(ModItems.DIAMOND_SCYTHE, 1),
                            20,
                            39
                    ))
                    .add(enchantWithLevels(
                            weightedItem(ModItems.DIAMOND_GREATSWORD, 1),
                            20,
                            39
                    ))
                    .add(enchantWithLevels(
                            weightedItem(ModItems.IRON_LONGSWORD, 1),
                            20,
                            39
                    ))
                    .add(enchantWithLevels(
                            weightedItem(ModItems.IRON_GREATSWORD, 1),
                            20,
                            39
                    ));

            event.getTable().addPool(pool.build());
        }
    }

    private static LootPoolSingletonContainer.Builder<?> weightedItem(
            Supplier<? extends Item> item,
            int weight
    ) {
        return LootItem.lootTableItem(item.get()).setWeight(weight);
    }

    private static LootPoolSingletonContainer.Builder<?> withDamage(
            LootPoolSingletonContainer.Builder<?> entry,
            float minimum,
            float maximum
    ) {
        return entry.apply(
                SetItemDamageFunction.setDamage(
                        UniformGenerator.between(minimum, maximum)
                )
        );
    }

    private static LootPoolSingletonContainer.Builder<?> enchantRandomly(
            LootPoolSingletonContainer.Builder<?> entry
    ) {
        return entry.apply(
                EnchantRandomlyFunction.randomEnchantment()
        );
    }

    private static LootPoolSingletonContainer.Builder<?> enchantWithLevels(
            LootPoolSingletonContainer.Builder<?> entry,
            int minimumLevel,
            int maximumLevel
    ) {
        return entry.apply(
                new EnchantWithLevelsFunction.Builder(
                        UniformGenerator.between(
                                minimumLevel,
                                maximumLevel
                        )
                )
        );
    }
}