package net.angelic.weaponsexpanded.datagen.villager;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.item.trading.VillagerTrades;

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

        context.register(WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_HATCHET, VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 12),
                        new ItemStackTemplate(ModItems.DIAMOND_HATCHET),
                        3, 15, 0.2F)
                .addModifiers(VillagerTrades.enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_HATCHET)).build());

        context.register(WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_MORNINGSTAR, VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 12),
                        new ItemStackTemplate(ModItems.DIAMOND_MORNINGSTAR),
                        3, 15, 0.2F)
                .addModifiers(VillagerTrades.enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_MORNINGSTAR)).build());

        context.register(WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_WARHAMMER, VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 12),
                        new ItemStackTemplate(ModItems.DIAMOND_WARHAMMER),
                        3, 15, 0.2F)
                .addModifiers(VillagerTrades.enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_WARHAMMER)).build());

        context.register(WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_HAMMER, VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 12),
                        new ItemStackTemplate(ModItems.DIAMOND_HAMMER),
                        3, 15, 0.2F)
                .addModifiers(VillagerTrades.enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_HAMMER)).build());

        context.register(WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_BATTLEAXE, VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 12),
                        new ItemStackTemplate(ModItems.DIAMOND_BATTLEAXE),
                        3, 15, 0.2F)
                .addModifiers(VillagerTrades.enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_BATTLEAXE)).build());

        context.register(WEAPONSMITH_4_EMERALD_ENCHANTED_DIAMOND_HALBERD, VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 12),
                        new ItemStackTemplate(ModItems.DIAMOND_HALBERD),
                        3, 15, 0.2F)
                .addModifiers(VillagerTrades.enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_HALBERD)).build());


        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_RAPIER, VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 8),
                        new ItemStackTemplate(ModItems.DIAMOND_RAPIER),
                        3, 30, 0.2F)
                .addModifiers(VillagerTrades.enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_RAPIER)).build());

        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_BROADSWORD, VillagerTrade.builder(
                new TradeCost(Items.EMERALD, 8),
                new ItemStackTemplate(ModItems.DIAMOND_BROADSWORD),
                3, 30, 0.2F)
                .addModifiers(VillagerTrades.enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_BROADSWORD)).build());

        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_SICKLE, VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 8),
                        new ItemStackTemplate(ModItems.DIAMOND_SICKLE),
                        3, 30, 0.2F)
                .addModifiers(VillagerTrades.enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_SICKLE)).build());

        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_GLAIVE, VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 8),
                        new ItemStackTemplate(ModItems.DIAMOND_GLAIVE),
                        3, 30, 0.2F)
                .addModifiers(VillagerTrades.enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_GLAIVE)).build());

        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_SCYTHE, VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 8),
                        new ItemStackTemplate(ModItems.DIAMOND_GLAIVE),
                        3, 30, 0.2F)
                .addModifiers(VillagerTrades.enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_GLAIVE)).build());

        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_LONGSWORD, VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 8),
                        new ItemStackTemplate(ModItems.DIAMOND_LONGSWORD),
                        3, 30, 0.2F)
                .addModifiers(VillagerTrades.enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_LONGSWORD)).build());

        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_GREATSWORD, VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 8),
                        new ItemStackTemplate(ModItems.DIAMOND_GREATSWORD),
                        3, 30, 0.2F)
                .addModifiers(VillagerTrades.enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_GREATSWORD)).build());

        context.register(WEAPONSMITH_5_EMERALD_ENCHANTED_DIAMOND_KATANA, VillagerTrade.builder(
                        new TradeCost(Items.EMERALD, 8),
                        new ItemStackTemplate(ModItems.DIAMOND_KATANA),
                        3, 30, 0.2F)
                .addModifiers(VillagerTrades.enchantedItem(items, enchantmentsForTradedEquipment, ModItems.DIAMOND_KATANA)).build());
    }

    private static ResourceKey<VillagerTrade> createKey(String name) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, name));
    }

}
