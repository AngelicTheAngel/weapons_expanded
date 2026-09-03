package net.angelic.weaponsexpanded.datagen.villager;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.advancements.predicates.DataComponentMatchers;
import net.minecraft.advancements.predicates.EnchantmentPredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.MinMaxBounds;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.predicates.DataComponentPredicates;
import net.minecraft.core.component.predicates.EnchantmentsPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.functions.DiscardItem;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.FilteredFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Optional;

public class ModVillagerTrades {

    public static final ResourceKey<VillagerTrade> WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_HATCHET = createKey("weaponsmith/4/emerald_enchanted_diamond_hatchet");
    public static final ResourceKey<VillagerTrade> WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_MORNINGSTAR = createKey("weaponsmith/4/emerald_enchanted_diamond_morningstar");
    public static final ResourceKey<VillagerTrade> WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_WARHAMMER = createKey("weaponsmith/4/emerald_enchanted_diamond_warhammer");
    public static final ResourceKey<VillagerTrade> WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_HAMMER = createKey("weaponsmith/4/emerald_enchanted_diamond_hammer");
    public static final ResourceKey<VillagerTrade> WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_BATTLEAXE = createKey("weaponsmith/4/emerald_enchanted_diamond_battleaxe");
    public static final ResourceKey<VillagerTrade> WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_HALBERD = createKey("weaponsmith/4/emerald_enchanted_diamond_halberd");

    public static final ResourceKey<VillagerTrade> WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_RAPIER = createKey("weaponsmith/5/emerald_enchanted_diamond_rapier");
    public static final ResourceKey<VillagerTrade> WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_BROADSWORD = createKey("weaponsmith/5/emerald_enchanted_diamond_broadsword");
    public static final ResourceKey<VillagerTrade> WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_SICKLE = createKey("weaponsmith/5/emerald_enchanted_diamond_sickle");
    public static final ResourceKey<VillagerTrade> WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_GLAIVE = createKey("weaponsmith/5/emerald_enchanted_diamond_glaive");
    public static final ResourceKey<VillagerTrade> WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_SCYTHE = createKey("weaponsmith/5/emerald_enchanted_diamond_scythe");
    public static final ResourceKey<VillagerTrade> WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_LONGSWORD = createKey("weaponsmith/5/emerald_enchanted_diamond_longsword");
    public static final ResourceKey<VillagerTrade> WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_GREATSWORD = createKey("weaponsmith/5/emerald_enchanted_diamond_greatsword");
    public static final ResourceKey<VillagerTrade> WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_KATANA = createKey("weaponsmith/5/emerald_enchanted_diamond_katana");


    public static void bootstrap(BootstrapContext<VillagerTrade> context) {
        var items = context.lookup(Registries.ITEM);
        var enchantments = context.lookup(Registries.ENCHANTMENT);
        HolderSet<Enchantment> enchantmentsForTradedEquipment = enchantments.getOrThrow(EnchantmentTags.ON_TRADED_EQUIPMENT);

        context.register(WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_HATCHET, new VillagerTrade(
                new TradeCost(Items.EMERALD, 12),
                new ItemStackTemplate(ModItems.DIAMOND_HATCHET),
                3, 15, 0.2F, Optional.empty(),
                enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_HATCHET)));

        context.register(WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_MORNINGSTAR, new VillagerTrade(
                new TradeCost(Items.EMERALD, 12),
                new ItemStackTemplate(ModItems.DIAMOND_MORNINGSTAR),
                3, 15, 0.2F, Optional.empty(),
                enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_MORNINGSTAR)));

        context.register(WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_WARHAMMER, new VillagerTrade(
                new TradeCost(Items.EMERALD, 12),
                new ItemStackTemplate(ModItems.DIAMOND_WARHAMMER),
                3, 15, 0.2F, Optional.empty(),
                enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_WARHAMMER)));

        context.register(WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_HAMMER, new VillagerTrade(
                new TradeCost(Items.EMERALD, 12),
                new ItemStackTemplate(ModItems.DIAMOND_HAMMER),
                3, 15, 0.2F, Optional.empty(),
                enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_HAMMER)));

        context.register(WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_BATTLEAXE, new VillagerTrade(
                new TradeCost(Items.EMERALD, 12),
                new ItemStackTemplate(ModItems.DIAMOND_BATTLEAXE),
                3, 15, 0.2F, Optional.empty(),
                enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_BATTLEAXE)));

        context.register(WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_HALBERD, new VillagerTrade(
                new TradeCost(Items.EMERALD, 12),
                new ItemStackTemplate(ModItems.DIAMOND_HALBERD),
                3, 15, 0.2F, Optional.empty(),
                enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_HALBERD)));


        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_RAPIER, new VillagerTrade(
                new TradeCost(Items.EMERALD, 8),
                new ItemStackTemplate(ModItems.DIAMOND_RAPIER),
                3, 30, 0.2F, Optional.empty(),
                enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_RAPIER)));

        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_BROADSWORD, new VillagerTrade(
                new TradeCost(Items.EMERALD, 8),
                new ItemStackTemplate(ModItems.DIAMOND_BROADSWORD),
                3, 30, 0.2F, Optional.empty(),
                enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_BROADSWORD)));

        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_SICKLE, new VillagerTrade(
                new TradeCost(Items.EMERALD, 8),
                new ItemStackTemplate(ModItems.DIAMOND_SICKLE),
                3, 30, 0.2F, Optional.empty(),
                enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_SICKLE)));

        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_GLAIVE, new VillagerTrade(
                new TradeCost(Items.EMERALD, 8),
                new ItemStackTemplate(ModItems.DIAMOND_GLAIVE),
                3, 30, 0.2F, Optional.empty(),
                enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_GLAIVE)));

        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_SCYTHE, new VillagerTrade(
                new TradeCost(Items.EMERALD, 8),
                new ItemStackTemplate(ModItems.DIAMOND_SCYTHE),
                3, 30, 0.2F, Optional.empty(),
                enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_SCYTHE)));

        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_LONGSWORD, new VillagerTrade(
                new TradeCost(Items.EMERALD, 8),
                new ItemStackTemplate(ModItems.DIAMOND_LONGSWORD),
                3, 30, 0.2F, Optional.empty(),
                enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_LONGSWORD)));

        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_GREATSWORD, new VillagerTrade(
                new TradeCost(Items.EMERALD, 8),
                new ItemStackTemplate(ModItems.DIAMOND_GREATSWORD),
                3, 30, 0.2F, Optional.empty(),
                enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_GREATSWORD)));

        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_KATANA, new VillagerTrade(
                new TradeCost(Items.EMERALD, 8),
                new ItemStackTemplate(ModItems.DIAMOND_KATANA),
                3, 30, 0.2F, Optional.empty(),
                enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_KATANA)));
    }

    private static ResourceKey<VillagerTrade> createKey(String name) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, name));
    }

    public static List<LootItemFunction> enchantedItem(final HolderGetter<Item> items, final HolderSet<Enchantment> options, final Item expectedItem) {
        return List.of((new EnchantWithLevelsFunction.Builder(UniformGenerator.between(5.0F, 19.0F))).withOptions(options).includeAdditionalCostComponent().build(), FilteredFunction.filtered((new ItemPredicate.Builder()).of(items, new ItemLike[]{expectedItem}).withComponents(DataComponentMatchers.Builder.components().partial(DataComponentPredicates.ENCHANTMENTS, EnchantmentsPredicate.enchantments(List.of(new EnchantmentPredicate(Optional.empty(), MinMaxBounds.Ints.ANY)))).build()).build()).onFail(Optional.of(DiscardItem.discardItem().build())).build());
    }

}
