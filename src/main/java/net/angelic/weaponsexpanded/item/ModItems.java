package net.angelic.weaponsexpanded.item;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.custom.*;
import net.angelic.weaponsexpanded.item.custom.projectile.ExplosiveArrowItem;
import net.angelic.weaponsexpanded.item.custom.projectile.HeavyArrowItem;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ToolMaterial;
import java.util.function.Function;

import static net.minecraft.world.item.Items.*;

public class ModItems {

    public static final Item WOODEN_RAPIER = registerItem("wooden_rapier",
            settings -> new Item(ModItemProperties.pierce(ToolMaterial.WOOD, 1.5F, -2.2F, settings)));

    public static final Item GOLDEN_RAPIER = registerItem("golden_rapier",
            settings -> new Item(ModItemProperties.pierce(ToolMaterial.GOLD, 1.5F, -2.2F, settings)));

    public static final Item STONE_RAPIER = registerItem("stone_rapier",
            settings -> new Item(ModItemProperties.pierce(ToolMaterial.STONE, 1.5F, -2.2F, settings)));

    public static final Item COPPER_RAPIER = registerItem("copper_rapier",
            settings -> new Item(ModItemProperties.pierce(ToolMaterial.COPPER, 1.5F, -2.2F, settings)));

    public static final Item IRON_RAPIER = registerItem("iron_rapier",
            settings -> new Item(ModItemProperties.pierce(ToolMaterial.IRON, 1.5F, -2.2F, settings)));

    public static final Item DIAMOND_RAPIER = registerItem("diamond_rapier",
            settings -> new Item(ModItemProperties.pierce(ToolMaterial.DIAMOND, 1.5F, -2.2F, settings)));

    public static final Item NETHERITE_RAPIER = registerItem("netherite_rapier",
            settings -> new Item(ModItemProperties.pierce(ToolMaterial.NETHERITE, 1.5F, -2.2F, settings.fireResistant())));

    public static final Item WOODEN_BROADSWORD = registerItem("wooden_broadsword",
            settings -> new Item(settings.sword(ToolMaterial.WOOD, 2.5F, -2.3F)));

    public static final Item GOLDEN_BROADSWORD = registerItem("golden_broadsword",
            settings -> new Item(settings.sword(ToolMaterial.GOLD, 2.5F, -2.3F)));

    public static final Item STONE_BROADSWORD = registerItem("stone_broadsword",
            settings -> new Item(settings.sword(ToolMaterial.STONE, 2.5F, -2.3F)));

    public static final Item COPPER_BROADSWORD = registerItem("copper_broadsword",
            settings -> new Item(settings.sword(ToolMaterial.COPPER, 2.5F, -2.3F)));

    public static final Item IRON_BROADSWORD = registerItem("iron_broadsword",
            settings -> new Item(settings.sword(ToolMaterial.IRON, 2.5F, -2.3F)));

    public static final Item DIAMOND_BROADSWORD = registerItem("diamond_broadsword",
            settings -> new Item(settings.sword(ToolMaterial.DIAMOND, 2.5F, -2.3F)));

    public static final Item NETHERITE_BROADSWORD = registerItem("netherite_broadsword",
            settings -> new Item(settings.sword(ToolMaterial.NETHERITE, 2.5F, -2.3F).fireResistant()));

    public static final Item WOODEN_SICKLE = registerItem("wooden_sickle",
            settings -> new Item(settings.sword(ToolMaterial.WOOD, 4.0F, -2.6F)));

    public static final Item GOLDEN_SICKLE = registerItem("golden_sickle",
            settings -> new Item(settings.sword(ToolMaterial.GOLD, 4.0F, -2.6F)));

    public static final Item STONE_SICKLE = registerItem("stone_sickle",
            settings -> new Item(settings.sword(ToolMaterial.STONE, 4.0F, -2.6F)));

    public static final Item COPPER_SICKLE = registerItem("copper_sickle",
            settings -> new Item(settings.sword(ToolMaterial.COPPER, 4.0F, -2.6F)));

    public static final Item IRON_SICKLE = registerItem("iron_sickle",
            settings -> new Item(settings.sword(ToolMaterial.IRON, 4.0F, -2.6F)));

    public static final Item DIAMOND_SICKLE = registerItem("diamond_sickle",
            settings -> new Item(settings.sword(ToolMaterial.DIAMOND, 4.0F, -2.6F)));

    public static final Item NETHERITE_SICKLE = registerItem("netherite_sickle",
            settings -> new Item(settings.sword(ToolMaterial.NETHERITE, 4.0F, -2.6F).fireResistant()));

    public static final Item WOODEN_SCYTHE = registerItem("wooden_scythe",
            settings -> new Item(ModItemProperties.scythe(ToolMaterial.WOOD, 5.5F, -2.9F, settings)));

    public static final Item GOLDEN_SCYTHE = registerItem("golden_scythe",
            settings -> new Item(ModItemProperties.scythe(ToolMaterial.GOLD, 5.5F, -2.9F, settings)));

    public static final Item STONE_SCYTHE = registerItem("stone_scythe",
            settings -> new Item(ModItemProperties.scythe(ToolMaterial.STONE, 5.5F, -2.9F, settings)));

    public static final Item COPPER_SCYTHE = registerItem("copper_scythe",
            settings -> new Item(ModItemProperties.scythe(ToolMaterial.COPPER, 5.5F, -2.9F, settings)));

    public static final Item IRON_SCYTHE = registerItem("iron_scythe",
            settings -> new Item(ModItemProperties.scythe(ToolMaterial.IRON, 5.5F, -2.9F, settings)));

    public static final Item DIAMOND_SCYTHE = registerItem("diamond_scythe",
            settings -> new Item(ModItemProperties.scythe(ToolMaterial.DIAMOND, 5.5F, -2.9F, settings)));

    public static final Item NETHERITE_SCYTHE = registerItem("netherite_scythe",
            settings -> new Item(ModItemProperties.scythe(ToolMaterial.NETHERITE, 5.5F, -2.9F, settings.fireResistant())));

    public static final Item WOODEN_LONGSWORD = registerItem("wooden_longsword",
            settings -> new BastardSwordItem(ToolMaterial.WOOD, 6.0F, -3.0F, 7.0F, -3.0F, settings));

    public static final Item GOLDEN_LONGSWORD = registerItem("golden_longsword",
            settings -> new BastardSwordItem(ToolMaterial.GOLD, 6.0F, -3.0F, 7.0F, -3.0F, settings));

    public static final Item STONE_LONGSWORD = registerItem("stone_longsword",
            settings -> new BastardSwordItem(ToolMaterial.STONE, 6.0F, -3.0F, 7.0F, -3.0F, settings));

    public static final Item COPPER_LONGSWORD = registerItem("copper_longsword",
            settings -> new BastardSwordItem(ToolMaterial.COPPER, 6.0F, -3.0F, 7.0F, -3.0F, settings));

    public static final Item IRON_LONGSWORD = registerItem("iron_longsword",
            settings -> new BastardSwordItem(ToolMaterial.IRON, 6.0F, -3.0F, 7.0F, -3.0F, settings));

    public static final Item DIAMOND_LONGSWORD = registerItem("diamond_longsword",
            settings -> new BastardSwordItem(ToolMaterial.DIAMOND, 6.0F, -3.0F, 7.0F, -3.0F, settings));

    public static final Item NETHERITE_LONGSWORD = registerItem("netherite_longsword",
            settings -> new BastardSwordItem(ToolMaterial.NETHERITE, 6.0F, -3.0F, 7.0F, -3.0F, settings.fireResistant()));

    public static final Item WOODEN_KATANA = registerItem("wooden_katana",
            settings -> new Item(settings.sword(ToolMaterial.WOOD, 3.0F, -2.2F)));

    public static final Item GOLDEN_KATANA = registerItem("golden_katana",
            settings -> new Item(settings.sword(ToolMaterial.GOLD, 3.0F, -2.2F)));

    public static final Item STONE_KATANA = registerItem("stone_katana",
            settings -> new Item(settings.sword(ToolMaterial.STONE, 3.0F, -2.2F)));

    public static final Item COPPER_KATANA = registerItem("copper_katana",
            settings -> new Item(settings.sword(ToolMaterial.COPPER, 3.0F, -2.2F)));

    public static final Item IRON_KATANA = registerItem("iron_katana",
            settings -> new Item(settings.sword(ToolMaterial.IRON, 3.0F, -2.2F)));

    public static final Item DIAMOND_KATANA = registerItem("diamond_katana",
            settings -> new Item(settings.sword(ToolMaterial.DIAMOND, 3.0F, -2.2F)));

    public static final Item NETHERITE_KATANA = registerItem("netherite_katana",
            settings -> new Item(settings.sword(ToolMaterial.NETHERITE, 3.0F, -2.2F).fireResistant()));

    public static final Item WOODEN_HATCHET = registerItem("wooden_hatchet",
            settings -> new AxeItem(ToolMaterial.WOOD, 4.0F, -2.9F, settings));

    public static final Item GOLDEN_HATCHET = registerItem("golden_hatchet",
            settings -> new AxeItem(ToolMaterial.GOLD, 4.0F, -2.7F, settings));

    public static final Item STONE_HATCHET = registerItem("stone_hatchet",
            settings -> new AxeItem(ToolMaterial.STONE, 5.0F, -2.9F, settings));

    public static final Item COPPER_HATCHET = registerItem("copper_hatchet",
            settings -> new AxeItem(ToolMaterial.COPPER, 5.0F, -2.9F, settings));

    public static final Item IRON_HATCHET = registerItem("iron_hatchet",
            settings -> new AxeItem(ToolMaterial.IRON, 4.0F, -2.8F, settings));

    public static final Item DIAMOND_HATCHET = registerItem("diamond_hatchet",
            settings -> new AxeItem(ToolMaterial.DIAMOND, 3.0F, -2.7F, settings));

    public static final Item NETHERITE_HATCHET = registerItem("netherite_hatchet",
            settings -> new AxeItem(ToolMaterial.NETHERITE, 3.0F, -2.7F, settings.fireResistant()));

    public static final Item WOODEN_HAMMER = registerItem("wooden_hammer",
            settings -> new Item(ModItemProperties.blunt(ToolMaterial.WOOD, 7.0F, -3.3F, settings)));

    public static final Item GOLDEN_HAMMER = registerItem("golden_hammer",
            settings -> new Item(ModItemProperties.blunt(ToolMaterial.GOLD, 7.0F, -3.1F, settings)));

    public static final Item STONE_HAMMER = registerItem("stone_hammer",
            settings -> new Item(ModItemProperties.blunt(ToolMaterial.STONE, 8.0F, -3.3F, settings)));

    public static final Item COPPER_HAMMER = registerItem("copper_hammer",
            settings -> new Item(ModItemProperties.blunt(ToolMaterial.COPPER, 8.0F, -3.3F, settings)));

    public static final Item IRON_HAMMER = registerItem("iron_hammer",
            settings -> new Item(ModItemProperties.blunt(ToolMaterial.IRON, 7.0F, -3.2F, settings)));

    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer",
            settings -> new Item(ModItemProperties.blunt(ToolMaterial.DIAMOND, 6.0F, -3.1F, settings)));

    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer",
            settings -> new Item(ModItemProperties.blunt(ToolMaterial.NETHERITE, 6.0F, -3.1F, settings.fireResistant())));

    public static final Item WOODEN_BATTLEAXE = registerItem("wooden_battleaxe",
            settings -> new AxeItem(ToolMaterial.WOOD, 8.0F, -3.4F, settings));

    public static final Item GOLDEN_BATTLEAXE = registerItem("golden_battleaxe",
            settings -> new AxeItem(ToolMaterial.GOLD, 8.0F, -3.2F, settings));

    public static final Item STONE_BATTLEAXE = registerItem("stone_battleaxe",
            settings -> new AxeItem(ToolMaterial.STONE, 9.0F, -3.4F, settings));

    public static final Item COPPER_BATTLEAXE = registerItem("copper_battleaxe",
            settings -> new AxeItem(ToolMaterial.COPPER, 9.0F, -3.4F, settings));

    public static final Item IRON_BATTLEAXE = registerItem("iron_battleaxe",
            settings -> new AxeItem(ToolMaterial.IRON, 8.0F, -3.3F, settings));

    public static final Item DIAMOND_BATTLEAXE = registerItem("diamond_battleaxe",
            settings -> new AxeItem(ToolMaterial.DIAMOND, 7.0F, -3.2F, settings));

    public static final Item NETHERITE_BATTLEAXE = registerItem("netherite_battleaxe",
            settings -> new AxeItem(ToolMaterial.NETHERITE, 7.0F, -3.2F, settings.fireResistant()));

    public static final Item WOODEN_GREATSWORD = registerItem("wooden_greatsword",
            settings -> new Item(ModItemProperties.greatsword(ToolMaterial.WOOD, 8.0F, -3.2F, settings)));

    public static final Item GOLDEN_GREATSWORD = registerItem("golden_greatsword",
            settings -> new Item(ModItemProperties.greatsword(ToolMaterial.GOLD, 8.0F, -3.2F, settings)));

    public static final Item STONE_GREATSWORD = registerItem("stone_greatsword",
            settings -> new Item(ModItemProperties.greatsword(ToolMaterial.STONE, 8.0F, -3.2F, settings)));

    public static final Item COPPER_GREATSWORD = registerItem("copper_greatsword",
            settings -> new Item(ModItemProperties.greatsword(ToolMaterial.COPPER, 8.0F, -3.2F, settings)));

    public static final Item IRON_GREATSWORD = registerItem("iron_greatsword",
            settings -> new Item(ModItemProperties.greatsword(ToolMaterial.IRON, 8.0F, -3.2F, settings)));

    public static final Item DIAMOND_GREATSWORD = registerItem("diamond_greatsword",
            settings -> new Item(ModItemProperties.greatsword(ToolMaterial.DIAMOND, 8.0F, -3.2F, settings)));

    public static final Item NETHERITE_GREATSWORD = registerItem("netherite_greatsword",
            settings -> new Item(ModItemProperties.greatsword(ToolMaterial.NETHERITE, 8.0F, -3.2F, settings).fireResistant()));

    public static final Item WOODEN_WARHAMMER = registerItem("wooden_warhammer",
            settings -> new WarhammerItem(ToolMaterial.WOOD, 5.0F, -3.1F, 5.0F, -2.8F, settings));

    public static final Item GOLDEN_WARHAMMER = registerItem("golden_warhammer",
            settings -> new WarhammerItem(ToolMaterial.GOLD, 5.0F, -2.9F, 5.0F, -2.8F, settings));

    public static final Item STONE_WARHAMMER = registerItem("stone_warhammer",
            settings -> new WarhammerItem(ToolMaterial.STONE, 6.0F, -3.1F, 5.0F, -2.8F, settings));

    public static final Item COPPER_WARHAMMER = registerItem("copper_warhammer",
            settings -> new WarhammerItem(ToolMaterial.COPPER, 6.0F, -3.1F, 5.0F, -2.8F, settings));

    public static final Item IRON_WARHAMMER = registerItem("iron_warhammer",
            settings -> new WarhammerItem(ToolMaterial.IRON, 5.0F, -3.0F, 5.0F, -2.8F, settings));

    public static final Item DIAMOND_WARHAMMER = registerItem("diamond_warhammer",
            settings -> new WarhammerItem(ToolMaterial.DIAMOND, 4.0F, -2.9F, 5.0F, -2.8F, settings));

    public static final Item NETHERITE_WARHAMMER = registerItem("netherite_warhammer",
            settings -> new WarhammerItem(ToolMaterial.NETHERITE, 4.0F, -2.9F, 5.0F, -2.8F, settings.fireResistant()));

    public static final Item WOODEN_MORNINGSTAR = registerItem("wooden_morningstar",
            settings -> new Item(ModItemProperties.blunt(ToolMaterial.WOOD, 5.0F, -3.0F, settings)));

    public static final Item GOLDEN_MORNINGSTAR = registerItem("golden_morningstar",
            settings -> new Item(ModItemProperties.blunt(ToolMaterial.GOLD, 5.0F, -2.8F, settings)));

    public static final Item STONE_MORNINGSTAR = registerItem("stone_morningstar",
            settings -> new Item(ModItemProperties.blunt(ToolMaterial.STONE, 6.0F, -3.0F, settings)));

    public static final Item COPPER_MORNINGSTAR = registerItem("copper_morningstar",
            settings -> new Item(ModItemProperties.blunt(ToolMaterial.COPPER, 6.0F, -3.0F, settings)));

    public static final Item IRON_MORNINGSTAR = registerItem("iron_morningstar",
            settings -> new Item(ModItemProperties.blunt(ToolMaterial.IRON, 5.0F, -2.9F, settings)));

    public static final Item DIAMOND_MORNINGSTAR = registerItem("diamond_morningstar",
            settings -> new Item(ModItemProperties.blunt(ToolMaterial.DIAMOND, 4.0F, -2.8F, settings)));

    public static final Item NETHERITE_MORNINGSTAR = registerItem("netherite_morningstar",
            settings -> new Item(ModItemProperties.blunt(ToolMaterial.NETHERITE, 4.0F, -2.8F, settings.fireResistant())));

    public static final Item WOODEN_GLAIVE = registerItem("wooden_glaive",
            settings -> new Item(ModItemProperties.glaive(ToolMaterial.WOOD, 4.0F, -2.7F, settings)));

    public static final Item GOLDEN_GLAIVE = registerItem("golden_glaive",
            settings -> new Item(ModItemProperties.glaive(ToolMaterial.GOLD, 4.0F, -2.7F, settings)));

    public static final Item STONE_GLAIVE = registerItem("stone_glaive",
            settings -> new Item(ModItemProperties.glaive(ToolMaterial.STONE, 4.0F, -2.7F, settings)));

    public static final Item COPPER_GLAIVE = registerItem("copper_glaive",
            settings -> new Item(ModItemProperties.glaive(ToolMaterial.COPPER, 4.0F, -2.7F, settings)));

    public static final Item IRON_GLAIVE = registerItem("iron_glaive",
            settings -> new Item(ModItemProperties.glaive(ToolMaterial.IRON, 4.0F, -2.7F, settings)));

    public static final Item DIAMOND_GLAIVE = registerItem("diamond_glaive",
            settings -> new Item(ModItemProperties.glaive(ToolMaterial.DIAMOND, 4.0F, -2.7F, settings)));

    public static final Item NETHERITE_GLAIVE = registerItem("netherite_glaive",
            settings -> new Item(ModItemProperties.glaive(ToolMaterial.NETHERITE, 4.0F, -2.7F, settings)));

    public static final Item HEAVY_ARROW = registerItem("heavy_arrow",
            settings -> new HeavyArrowItem(settings.stacksTo(64)));

    public static final Item EXPLOSIVE_ARROW = registerItem("explosive_arrow",
            settings -> new ExplosiveArrowItem(settings.stacksTo(64)));

    public static final Item LONGBOW = registerItem("longbow",
            settings -> new LongbowItem(settings.durability(384).enchantable(1)));

    public static final Item CHAIN_CROSSBOW = registerItem("chain_crossbow",
            settings -> new ChainCrossbowItem(settings.durability(465).enchantable(1)));

    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, name)))));
    }

    public static void registerModItems() {
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.COMBAT).register(entries -> {
            // Ranged placement relative to vanilla items
            entries.insertAfter(Items.BOW, LONGBOW);
            entries.insertAfter(Items.CROSSBOW, CHAIN_CROSSBOW);
            entries.insertAfter(Items.SPECTRAL_ARROW, HEAVY_ARROW);
            entries.insertAfter(HEAVY_ARROW, EXPLOSIVE_ARROW);

            Item anchor = Items.WOODEN_SWORD;

            // Rapiers
            entries.insertBefore(anchor, WOODEN_RAPIER);
            entries.insertAfter(WOODEN_RAPIER, GOLDEN_RAPIER);
            entries.insertAfter(GOLDEN_RAPIER, STONE_RAPIER);
            entries.insertAfter(STONE_RAPIER, COPPER_RAPIER);
            entries.insertAfter(COPPER_RAPIER, IRON_RAPIER);
            entries.insertAfter(IRON_RAPIER, DIAMOND_RAPIER);
            entries.insertAfter(DIAMOND_RAPIER, NETHERITE_RAPIER);
            anchor = NETHERITE_RAPIER;

            // Broadswords
            entries.insertAfter(anchor, WOODEN_BROADSWORD);
            entries.insertAfter(WOODEN_BROADSWORD, GOLDEN_BROADSWORD);
            entries.insertAfter(GOLDEN_BROADSWORD, STONE_BROADSWORD);
            entries.insertAfter(STONE_BROADSWORD, COPPER_BROADSWORD);
            entries.insertAfter(COPPER_BROADSWORD, IRON_BROADSWORD);
            entries.insertAfter(IRON_BROADSWORD, DIAMOND_BROADSWORD);
            entries.insertAfter(DIAMOND_BROADSWORD, NETHERITE_BROADSWORD);
            anchor = NETHERITE_SWORD;

            // Sickles
            entries.insertAfter(anchor, WOODEN_SICKLE);
            entries.insertAfter(WOODEN_SICKLE, GOLDEN_SICKLE);
            entries.insertAfter(GOLDEN_SICKLE, STONE_SICKLE);
            entries.insertAfter(STONE_SICKLE, COPPER_SICKLE);
            entries.insertAfter(COPPER_SICKLE, IRON_SICKLE);
            entries.insertAfter(IRON_SICKLE, DIAMOND_SICKLE);
            entries.insertAfter(DIAMOND_SICKLE, NETHERITE_SICKLE);
            anchor = NETHERITE_SICKLE;

            // Glaives
            entries.insertAfter(anchor, WOODEN_GLAIVE);
            entries.insertAfter(WOODEN_GLAIVE, GOLDEN_GLAIVE);
            entries.insertAfter(GOLDEN_GLAIVE, STONE_GLAIVE);
            entries.insertAfter(STONE_GLAIVE, COPPER_GLAIVE);
            entries.insertAfter(COPPER_GLAIVE, IRON_GLAIVE);
            entries.insertAfter(IRON_GLAIVE, DIAMOND_GLAIVE);
            entries.insertAfter(DIAMOND_GLAIVE, NETHERITE_GLAIVE);
            anchor = NETHERITE_GLAIVE;

            // Scythes
            entries.insertAfter(anchor, WOODEN_SCYTHE);
            entries.insertAfter(WOODEN_SCYTHE, GOLDEN_SCYTHE);
            entries.insertAfter(GOLDEN_SCYTHE, STONE_SCYTHE);
            entries.insertAfter(STONE_SCYTHE, COPPER_SCYTHE);
            entries.insertAfter(COPPER_SCYTHE, IRON_SCYTHE);
            entries.insertAfter(IRON_SCYTHE, DIAMOND_SCYTHE);
            entries.insertAfter(DIAMOND_SCYTHE, NETHERITE_SCYTHE);
            anchor = NETHERITE_SCYTHE;

            // Longswords
            entries.insertAfter(anchor, WOODEN_LONGSWORD);
            entries.insertAfter(WOODEN_LONGSWORD, GOLDEN_LONGSWORD);
            entries.insertAfter(GOLDEN_LONGSWORD, STONE_LONGSWORD);
            entries.insertAfter(STONE_LONGSWORD, COPPER_LONGSWORD);
            entries.insertAfter(COPPER_LONGSWORD, IRON_LONGSWORD);
            entries.insertAfter(IRON_LONGSWORD, DIAMOND_LONGSWORD);
            entries.insertAfter(DIAMOND_LONGSWORD, NETHERITE_LONGSWORD);
            anchor = NETHERITE_LONGSWORD;

            // Katanas
            entries.insertAfter(anchor, WOODEN_KATANA);
            entries.insertAfter(WOODEN_KATANA, GOLDEN_KATANA);
            entries.insertAfter(GOLDEN_KATANA, STONE_KATANA);
            entries.insertAfter(STONE_KATANA, COPPER_KATANA);
            entries.insertAfter(COPPER_KATANA, IRON_KATANA);
            entries.insertAfter(IRON_KATANA, DIAMOND_KATANA);
            entries.insertAfter(DIAMOND_KATANA, NETHERITE_KATANA);
            anchor = NETHERITE_KATANA;

            // Greatswords
            entries.insertAfter(anchor, WOODEN_GREATSWORD);
            entries.insertAfter(WOODEN_GREATSWORD, GOLDEN_GREATSWORD);
            entries.insertAfter(GOLDEN_GREATSWORD, STONE_GREATSWORD);
            entries.insertAfter(STONE_GREATSWORD, COPPER_GREATSWORD);
            entries.insertAfter(COPPER_GREATSWORD, IRON_GREATSWORD);
            entries.insertAfter(IRON_GREATSWORD, DIAMOND_GREATSWORD);
            entries.insertAfter(DIAMOND_GREATSWORD, NETHERITE_GREATSWORD);
            anchor = WOODEN_AXE;

            // Hatchets
            entries.insertBefore(anchor, WOODEN_HATCHET);
            entries.insertAfter(WOODEN_HATCHET, GOLDEN_HATCHET);
            entries.insertAfter(GOLDEN_HATCHET, STONE_HATCHET);
            entries.insertAfter(STONE_HATCHET, COPPER_HATCHET);
            entries.insertAfter(COPPER_HATCHET, IRON_HATCHET);
            entries.insertAfter(IRON_HATCHET, DIAMOND_HATCHET);
            entries.insertAfter(DIAMOND_HATCHET, NETHERITE_HATCHET);
            anchor = NETHERITE_HATCHET;

            // Morningstars
            entries.insertAfter(anchor, WOODEN_MORNINGSTAR);
            entries.insertAfter(WOODEN_MORNINGSTAR, GOLDEN_MORNINGSTAR);
            entries.insertAfter(GOLDEN_MORNINGSTAR, STONE_MORNINGSTAR);
            entries.insertAfter(STONE_MORNINGSTAR, COPPER_MORNINGSTAR);
            entries.insertAfter(COPPER_MORNINGSTAR, IRON_MORNINGSTAR);
            entries.insertAfter(IRON_MORNINGSTAR, DIAMOND_MORNINGSTAR);
            entries.insertAfter(DIAMOND_MORNINGSTAR, NETHERITE_MORNINGSTAR);
            anchor = NETHERITE_MORNINGSTAR;

            // Warhammer
            entries.insertAfter(anchor, WOODEN_WARHAMMER);
            entries.insertAfter(WOODEN_WARHAMMER, GOLDEN_WARHAMMER);
            entries.insertAfter(GOLDEN_WARHAMMER, STONE_WARHAMMER);
            entries.insertAfter(STONE_WARHAMMER, COPPER_WARHAMMER);
            entries.insertAfter(COPPER_WARHAMMER, IRON_WARHAMMER);
            entries.insertAfter(IRON_WARHAMMER, DIAMOND_WARHAMMER);
            entries.insertAfter(DIAMOND_WARHAMMER, NETHERITE_WARHAMMER);
            anchor = NETHERITE_AXE;

            // Hammers
            entries.insertAfter(anchor, WOODEN_HAMMER);
            entries.insertAfter(WOODEN_HAMMER, GOLDEN_HAMMER);
            entries.insertAfter(GOLDEN_HAMMER, STONE_HAMMER);
            entries.insertAfter(STONE_HAMMER, COPPER_HAMMER);
            entries.insertAfter(COPPER_HAMMER, IRON_HAMMER);
            entries.insertAfter(IRON_HAMMER, DIAMOND_HAMMER);
            entries.insertAfter(DIAMOND_HAMMER, NETHERITE_HAMMER);
            anchor = NETHERITE_HAMMER;

            // Battleaxes
            entries.insertAfter(anchor, WOODEN_BATTLEAXE);
            entries.insertAfter(WOODEN_BATTLEAXE, GOLDEN_BATTLEAXE);
            entries.insertAfter(GOLDEN_BATTLEAXE, STONE_BATTLEAXE);
            entries.insertAfter(STONE_BATTLEAXE, COPPER_BATTLEAXE);
            entries.insertAfter(COPPER_BATTLEAXE, IRON_BATTLEAXE);
            entries.insertAfter(IRON_BATTLEAXE, DIAMOND_BATTLEAXE);
            entries.insertAfter(DIAMOND_BATTLEAXE, NETHERITE_BATTLEAXE);
        });
    }
}