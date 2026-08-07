package net.angelic.weaponsexpanded.item;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.custom.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.minecraft.item.Items.*;

public class ModItems {

    public static final Item WOODEN_BROADSWORD = registerItem("wooden_broadsword", new SwordItem(ToolMaterials.WOOD,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 2, -2.1F))));

    public static final Item GOLDEN_BROADSWORD = registerItem("golden_broadsword", new SwordItem(ToolMaterials.GOLD,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.GOLD, 2, -2.1F))));

    public static final Item STONE_BROADSWORD = registerItem("stone_broadsword", new SwordItem(ToolMaterials.STONE,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.STONE, 2, -2.1F))));

    public static final Item IRON_BROADSWORD = registerItem("iron_broadsword", new SwordItem(ToolMaterials.IRON,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.IRON, 2, -2.2F))));

    public static final Item DIAMOND_BROADSWORD = registerItem("diamond_broadsword", new SwordItem(ToolMaterials.DIAMOND,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 2, -2.2F))));

    public static final Item NETHERITE_BROADSWORD = registerItem("netherite_broadsword", new SwordItem(ToolMaterials.NETHERITE,
            new Item.Settings().fireproof().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 2, -2.2F))));

    public static final Item WOODEN_SICKLE = registerItem("wooden_sickle", new SwordItem(ToolMaterials.WOOD,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 2, -2.2F))));

    public static final Item GOLDEN_SICKLE = registerItem("golden_sickle", new SwordItem(ToolMaterials.GOLD,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.GOLD, 2, -2.2F))));

    public static final Item STONE_SICKLE = registerItem("stone_sickle", new SwordItem(ToolMaterials.STONE,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.STONE, 2, -2.2F))));

    public static final Item IRON_SICKLE = registerItem("iron_sickle", new SwordItem(ToolMaterials.IRON,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.IRON, 2, -2.2F))));

    public static final Item DIAMOND_SICKLE = registerItem("diamond_sickle", new SwordItem(ToolMaterials.DIAMOND,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 2, -2.2F))));

    public static final Item NETHERITE_SICKLE = registerItem("netherite_sickle", new SwordItem(ToolMaterials.NETHERITE,
            new Item.Settings().fireproof().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 2, -2.2F))));

    public static final Item WOODEN_SCYTHE = registerItem("wooden_scythe", new SwordItem(ToolMaterials.WOOD,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 4, -2.5F))));

    public static final Item GOLDEN_SCYTHE = registerItem("golden_scythe", new SwordItem(ToolMaterials.GOLD,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.GOLD, 4, -2.5F))));

    public static final Item STONE_SCYTHE = registerItem("stone_scythe", new SwordItem(ToolMaterials.STONE,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.STONE, 4, -2.5F))));

    public static final Item IRON_SCYTHE = registerItem("iron_scythe", new SwordItem(ToolMaterials.IRON,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.IRON, 4, -2.5F))));

    public static final Item DIAMOND_SCYTHE = registerItem("diamond_scythe", new SwordItem(ToolMaterials.DIAMOND,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 4, -2.5F))));

    public static final Item NETHERITE_SCYTHE = registerItem("netherite_scythe", new SwordItem(ToolMaterials.NETHERITE,
            new Item.Settings().fireproof().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 4, -2.5F))));

    public static final Item WOODEN_LONGSWORD = registerItem("wooden_longsword", new BastardSwordItem(ToolMaterials.WOOD, 6.0F, -2.9F, 7.0F, -2.9F,
            new Item.Settings().attributeModifiers(BastardSwordItem.createAttributeModifiers(ToolMaterials.WOOD, 6, -2.9F))));

    public static final Item GOLDEN_LONGSWORD = registerItem("golden_longsword", new BastardSwordItem(ToolMaterials.GOLD, 6.0F, -2.9F, 7.0F, -2.9F,
            new Item.Settings().attributeModifiers(BastardSwordItem.createAttributeModifiers(ToolMaterials.GOLD, 6, -2.9F))));

    public static final Item STONE_LONGSWORD = registerItem("stone_longsword", new BastardSwordItem(ToolMaterials.STONE, 6.0F, -2.9F, 7.0F, -2.9F,
            new Item.Settings().attributeModifiers(BastardSwordItem.createAttributeModifiers(ToolMaterials.STONE, 6, -2.9F))));

    public static final Item IRON_LONGSWORD = registerItem("iron_longsword", new BastardSwordItem(ToolMaterials.IRON, 6.0F, -2.9F, 7.0F, -2.9F,
            new Item.Settings().attributeModifiers(BastardSwordItem.createAttributeModifiers(ToolMaterials.IRON, 6, -2.9F))));

    public static final Item DIAMOND_LONGSWORD = registerItem("diamond_longsword", new BastardSwordItem(ToolMaterials.DIAMOND, 6.0F, -2.9F, 7.0F, -2.9F,
            new Item.Settings().attributeModifiers(BastardSwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 6, -2.9F))));

    public static final Item NETHERITE_LONGSWORD = registerItem("netherite_longsword", new BastardSwordItem(ToolMaterials.NETHERITE, 6.0F, -2.9F, 7.0F, -2.9F,
            new Item.Settings().fireproof().attributeModifiers(BastardSwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 6, -2.9F))));

    public static final Item WOODEN_KATANA = registerItem("wooden_katana", new TwoHandedSwordItem(ToolMaterials.WOOD,
            new Item.Settings().attributeModifiers(TwoHandedSwordItem.createAttributeModifiers(ToolMaterials.WOOD, 3, -2.2F))));

    public static final Item GOLDEN_KATANA = registerItem("golden_katana", new TwoHandedSwordItem(ToolMaterials.GOLD,
            new Item.Settings().attributeModifiers(TwoHandedSwordItem.createAttributeModifiers(ToolMaterials.GOLD, 3, -2.2F))));

    public static final Item STONE_KATANA = registerItem("stone_katana", new TwoHandedSwordItem(ToolMaterials.STONE,
            new Item.Settings().attributeModifiers(TwoHandedSwordItem.createAttributeModifiers(ToolMaterials.STONE, 3, -2.2F))));

    public static final Item IRON_KATANA = registerItem("iron_katana", new TwoHandedSwordItem(ToolMaterials.IRON,
            new Item.Settings().attributeModifiers(TwoHandedSwordItem.createAttributeModifiers(ToolMaterials.IRON, 3, -2.2F))));

    public static final Item DIAMOND_KATANA = registerItem("diamond_katana", new TwoHandedSwordItem(ToolMaterials.DIAMOND,
            new Item.Settings().attributeModifiers(TwoHandedSwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 3, -2.2F))));

    public static final Item NETHERITE_KATANA = registerItem("netherite_katana", new TwoHandedSwordItem(ToolMaterials.NETHERITE,
            new Item.Settings().fireproof().attributeModifiers(TwoHandedSwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 3, -2.2F))));

    public static final Item WOODEN_HATCHET = registerItem("wooden_hatchet", new AxeItem(ToolMaterials.WOOD,
            new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.WOOD, 5, -3.0F))));

    public static final Item GOLDEN_HATCHET = registerItem("golden_hatchet", new AxeItem(ToolMaterials.GOLD,
            new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.GOLD, 5, -2.8F))));
    
    public static final Item STONE_HATCHET = registerItem("stone_hatchet", new AxeItem(ToolMaterials.STONE,
            new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.STONE, 6, -3.0F))));

    public static final Item IRON_HATCHET = registerItem("iron_hatchet", new AxeItem(ToolMaterials.IRON,
            new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.IRON, 5, -2.9F))));

    public static final Item DIAMOND_HATCHET = registerItem("diamond_hatchet", new AxeItem(ToolMaterials.DIAMOND,
            new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.DIAMOND, 4, -2.8F))));

    public static final Item NETHERITE_HATCHET = registerItem("netherite_hatchet", new AxeItem(ToolMaterials.NETHERITE,
            new Item.Settings().fireproof().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.NETHERITE, 4, -2.8F))));

    public static final Item WOODEN_HAMMER = registerItem("wooden_hammer", new BluntWeaponItem(ModToolMaterials.FAUX_WOOD,
            new Item.Settings().attributeModifiers(BluntWeaponItem.createAttributeModifiers(ModToolMaterials.FAUX_WOOD, 7, -3.3F))));

    public static final Item GOLDEN_HAMMER = registerItem("golden_hammer", new BluntWeaponItem(ModToolMaterials.FAUX_GOLD,
            new Item.Settings().attributeModifiers(BluntWeaponItem.createAttributeModifiers(ModToolMaterials.FAUX_GOLD, 7, -3.1F))));

    public static final Item STONE_HAMMER = registerItem("stone_hammer", new BluntWeaponItem(ModToolMaterials.FAUX_STONE,
            new Item.Settings().attributeModifiers(BluntWeaponItem.createAttributeModifiers(ModToolMaterials.FAUX_STONE, 8, -3.3F))));

    public static final Item IRON_HAMMER = registerItem("iron_hammer", new BluntWeaponItem(ModToolMaterials.FAUX_IRON,
            new Item.Settings().attributeModifiers(BluntWeaponItem.createAttributeModifiers(ModToolMaterials.FAUX_IRON, 7, -3.2F))));

    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer", new BluntWeaponItem(ModToolMaterials.FAUX_DIAMOND,
            new Item.Settings().attributeModifiers(BluntWeaponItem.createAttributeModifiers(ModToolMaterials.FAUX_DIAMOND, 6, -3.1F))));

    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer", new BluntWeaponItem(ModToolMaterials.FAUX_NETHERITE,
            new Item.Settings().fireproof().attributeModifiers(BluntWeaponItem.createAttributeModifiers(ModToolMaterials.FAUX_NETHERITE, 6, -3.1F))));

    public static final Item WOODEN_BATTLEAXE = registerItem("wooden_battleaxe", new AxeItem(ToolMaterials.WOOD,
            new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.WOOD, 8, -3.4F))));

    public static final Item GOLDEN_BATTLEAXE = registerItem("golden_battleaxe", new AxeItem(ToolMaterials.GOLD,
            new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.GOLD, 8, -3.2F))));

    public static final Item STONE_BATTLEAXE = registerItem("stone_battleaxe", new AxeItem(ToolMaterials.STONE,
            new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.STONE, 9, -3.4F))));

    public static final Item IRON_BATTLEAXE = registerItem("iron_battleaxe", new AxeItem(ToolMaterials.IRON,
            new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.IRON, 8, -3.3F))));

    public static final Item DIAMOND_BATTLEAXE = registerItem("diamond_battleaxe", new AxeItem(ToolMaterials.DIAMOND,
            new Item.Settings().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.DIAMOND, 7, -3.2F))));

    public static final Item NETHERITE_BATTLEAXE = registerItem("netherite_battleaxe", new AxeItem(ToolMaterials.NETHERITE,
            new Item.Settings().fireproof().attributeModifiers(AxeItem.createAttributeModifiers(ToolMaterials.WOOD, 7, -3.2F))));

    public static final Item WOODEN_GREATSWORD = registerItem("wooden_greatsword", new TwoHandedSwordItem(ToolMaterials.WOOD,
            new Item.Settings().attributeModifiers(TwoHandedSwordItem.createAttributeModifiers(ToolMaterials.WOOD, 8, -3.3F))));

    public static final Item GOLDEN_GREATSWORD = registerItem("golden_greatsword", new TwoHandedSwordItem(ToolMaterials.GOLD,
            new Item.Settings().attributeModifiers(TwoHandedSwordItem.createAttributeModifiers(ToolMaterials.GOLD, 8, -3.1F))));

    public static final Item STONE_GREATSWORD = registerItem("stone_greatsword", new TwoHandedSwordItem(ToolMaterials.STONE,
            new Item.Settings().attributeModifiers(TwoHandedSwordItem.createAttributeModifiers(ToolMaterials.STONE, 8, -3.3F))));

    public static final Item IRON_GREATSWORD = registerItem("iron_greatsword", new TwoHandedSwordItem(ToolMaterials.IRON,
            new Item.Settings().attributeModifiers(TwoHandedSwordItem.createAttributeModifiers(ToolMaterials.IRON, 8, -3.2F))));

    public static final Item DIAMOND_GREATSWORD = registerItem("diamond_greatsword", new TwoHandedSwordItem(ToolMaterials.DIAMOND,
            new Item.Settings().attributeModifiers(TwoHandedSwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 8, -3.1F))));

    public static final Item NETHERITE_GREATSWORD = registerItem("netherite_greatsword", new TwoHandedSwordItem(ToolMaterials.NETHERITE,
            new Item.Settings().fireproof().attributeModifiers(TwoHandedSwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 8, -3.1F))));

    public static final Item WOODEN_WARHAMMER = registerItem("wooden_warhammer", new WarhammerItem(ToolMaterials.WOOD, 5.0F, -3.1F, 5.0F, -2.7F,
            new Item.Settings().attributeModifiers(WarhammerItem.createAttributeModifiers(ToolMaterials.WOOD, 8, -3.3F))));

    public static final Item GOLDEN_WARHAMMER = registerItem("golden_warhammer", new WarhammerItem(ToolMaterials.GOLD, 5.0F, -2.9F, 5.0F, -2.7F,
            new Item.Settings().attributeModifiers(WarhammerItem.createAttributeModifiers(ToolMaterials.GOLD, 8, -3.1F))));

    public static final Item STONE_WARHAMMER = registerItem("stone_warhammer", new WarhammerItem(ToolMaterials.STONE, 6.0F, -3.1F, 5.0F, -2.7F,
            new Item.Settings().attributeModifiers(WarhammerItem.createAttributeModifiers(ToolMaterials.STONE, 8, -3.3F))));

    public static final Item IRON_WARHAMMER = registerItem("iron_warhammer", new WarhammerItem(ToolMaterials.IRON, 5.0F, -3.0F, 5.0F, -2.7F,
            new Item.Settings().attributeModifiers(WarhammerItem.createAttributeModifiers(ToolMaterials.IRON, 8, -3.2F))));

    public static final Item DIAMOND_WARHAMMER = registerItem("diamond_warhammer", new WarhammerItem(ToolMaterials.DIAMOND, 4.0F, -2.9F, 5.0F, -2.7F,
            new Item.Settings().attributeModifiers(WarhammerItem.createAttributeModifiers(ToolMaterials.DIAMOND, 8, -3.1F))));

    public static final Item NETHERITE_WARHAMMER = registerItem("netherite_warhammer", new WarhammerItem(ToolMaterials.NETHERITE, 4.0F, -2.9F, 5.0F, -2.7F,
            new Item.Settings().fireproof().attributeModifiers(WarhammerItem.createAttributeModifiers(ToolMaterials.NETHERITE, 8, -3.1F))));

    public static final Item CHAIN_CROSSBOW = registerItem("chain_crossbow",
            new ChainCrossbowItem(new Item.Settings().maxDamage(465)));

    public static final Item LONGBOW = registerItem("longbow",
            new LongbowItem(new Item.Settings().maxDamage(384)));

    public static final Item HEAVY_ARROW = registerItem("heavy_arrow",
            new HeavyArrowItem(new Item.Settings().maxCount(64)));

    public static final Item EXPLOSIVE_ARROW = registerItem("explosive_arrow",
            new ExplosiveArrowItem(new Item.Settings().maxCount(64)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(WeaponsExpanded.MOD_ID, name), item);
    }

    public static void registerModItems() {
        WeaponsExpanded.LOGGER.info("Registering Items for " + WeaponsExpanded.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            // Ranged placement relative to vanilla items
            entries.addAfter(Items.BOW, LONGBOW);
            entries.addAfter(Items.CROSSBOW, CHAIN_CROSSBOW);
            entries.addAfter(Items.SPECTRAL_ARROW, HEAVY_ARROW);
            entries.addAfter(HEAVY_ARROW, EXPLOSIVE_ARROW);

            Item anchor = Items.WOODEN_SWORD;

            // Broadswords
            entries.addBefore(anchor, WOODEN_BROADSWORD);
            entries.addAfter(WOODEN_BROADSWORD, GOLDEN_BROADSWORD);
            entries.addAfter(GOLDEN_BROADSWORD, STONE_BROADSWORD);
            entries.addAfter(STONE_BROADSWORD, IRON_BROADSWORD);
            entries.addAfter(IRON_BROADSWORD, DIAMOND_BROADSWORD);
            entries.addAfter(DIAMOND_BROADSWORD, NETHERITE_BROADSWORD);
            anchor = NETHERITE_BROADSWORD;

            // Sickles
            entries.addAfter(anchor, WOODEN_SICKLE);
            entries.addAfter(WOODEN_SICKLE, GOLDEN_SICKLE);
            entries.addAfter(GOLDEN_SICKLE, STONE_SICKLE);
            entries.addAfter(STONE_SICKLE, IRON_SICKLE);
            entries.addAfter(IRON_SICKLE, DIAMOND_SICKLE);
            entries.addAfter(DIAMOND_SICKLE, NETHERITE_SICKLE);
            anchor = NETHERITE_SWORD;

            // Scythes
            entries.addAfter(anchor, WOODEN_SCYTHE);
            entries.addAfter(WOODEN_SCYTHE, GOLDEN_SCYTHE);
            entries.addAfter(GOLDEN_SCYTHE, STONE_SCYTHE);
            entries.addAfter(STONE_SCYTHE, IRON_SCYTHE);
            entries.addAfter(IRON_SCYTHE, DIAMOND_SCYTHE);
            entries.addAfter(DIAMOND_SCYTHE, NETHERITE_SCYTHE);
            anchor = NETHERITE_SCYTHE;

            // Longswords
            entries.addAfter(anchor, WOODEN_LONGSWORD);
            entries.addAfter(WOODEN_LONGSWORD, GOLDEN_LONGSWORD);
            entries.addAfter(GOLDEN_LONGSWORD, STONE_LONGSWORD);
            entries.addAfter(STONE_LONGSWORD, IRON_LONGSWORD);
            entries.addAfter(IRON_LONGSWORD, DIAMOND_LONGSWORD);
            entries.addAfter(DIAMOND_LONGSWORD, NETHERITE_LONGSWORD);
            anchor = NETHERITE_LONGSWORD;

            // Katanas
            entries.addAfter(anchor, WOODEN_KATANA);
            entries.addAfter(WOODEN_KATANA, GOLDEN_KATANA);
            entries.addAfter(GOLDEN_KATANA, STONE_KATANA);
            entries.addAfter(STONE_KATANA, IRON_KATANA);
            entries.addAfter(IRON_KATANA, DIAMOND_KATANA);
            entries.addAfter(DIAMOND_KATANA, NETHERITE_KATANA);
            anchor = WOODEN_AXE;

            // Hatchets
            entries.addBefore(anchor, WOODEN_HATCHET);
            entries.addAfter(WOODEN_HATCHET, GOLDEN_HATCHET);
            entries.addAfter(GOLDEN_HATCHET, STONE_HATCHET);
            entries.addAfter(STONE_HATCHET, IRON_HATCHET);
            entries.addAfter(IRON_HATCHET, DIAMOND_HATCHET);
            entries.addAfter(DIAMOND_HATCHET, NETHERITE_HATCHET);
            anchor = NETHERITE_AXE;

            // Hammers
            entries.addAfter(anchor, WOODEN_HAMMER);
            entries.addAfter(WOODEN_HAMMER, GOLDEN_HAMMER);
            entries.addAfter(GOLDEN_HAMMER, STONE_HAMMER);
            entries.addAfter(STONE_HAMMER, IRON_HAMMER);
            entries.addAfter(IRON_HAMMER, DIAMOND_HAMMER);
            entries.addAfter(DIAMOND_HAMMER, NETHERITE_HAMMER);
            anchor = NETHERITE_HAMMER;

            // Battleaxes
            entries.addAfter(anchor, WOODEN_BATTLEAXE);
            entries.addAfter(WOODEN_BATTLEAXE, GOLDEN_BATTLEAXE);
            entries.addAfter(GOLDEN_BATTLEAXE, STONE_BATTLEAXE);
            entries.addAfter(STONE_BATTLEAXE, IRON_BATTLEAXE);
            entries.addAfter(IRON_BATTLEAXE, DIAMOND_BATTLEAXE);
            entries.addAfter(DIAMOND_BATTLEAXE, NETHERITE_BATTLEAXE);
            anchor = NETHERITE_KATANA;

            // Greatswords
            entries.addAfter(anchor, WOODEN_GREATSWORD);
            entries.addAfter(WOODEN_GREATSWORD, GOLDEN_GREATSWORD);
            entries.addAfter(GOLDEN_GREATSWORD, STONE_GREATSWORD);
            entries.addAfter(STONE_GREATSWORD, IRON_GREATSWORD);
            entries.addAfter(IRON_GREATSWORD, DIAMOND_GREATSWORD);
            entries.addAfter(DIAMOND_GREATSWORD, NETHERITE_GREATSWORD);
            anchor = NETHERITE_GREATSWORD;

            // Warhammer
            entries.addAfter(anchor, WOODEN_WARHAMMER);
            entries.addAfter(WOODEN_WARHAMMER, GOLDEN_WARHAMMER);
            entries.addAfter(GOLDEN_WARHAMMER, STONE_WARHAMMER);
            entries.addAfter(STONE_WARHAMMER, IRON_WARHAMMER);
            entries.addAfter(IRON_WARHAMMER, DIAMOND_WARHAMMER);
            entries.addAfter(DIAMOND_WARHAMMER, NETHERITE_WARHAMMER);
        });
    }
}
