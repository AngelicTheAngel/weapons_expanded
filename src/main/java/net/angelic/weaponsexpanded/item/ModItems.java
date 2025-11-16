package net.angelic.weaponsexpanded.item;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.custom.BluntWeaponItem;
import net.angelic.weaponsexpanded.item.custom.PierceWeaponItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedHeavySwordItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

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

    public static final Item WOODEN_LONGSWORD = registerItem("wooden_longsword", new SwordItem(ToolMaterials.WOOD,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.WOOD, 6, -2.9F))));

    public static final Item GOLDEN_LONGSWORD = registerItem("golden_longsword", new SwordItem(ToolMaterials.GOLD,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.GOLD, 6, -2.9F))));

    public static final Item STONE_LONGSWORD = registerItem("stone_longsword", new SwordItem(ToolMaterials.STONE,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.STONE, 6, -2.9F))));

    public static final Item IRON_LONGSWORD = registerItem("iron_longsword", new SwordItem(ToolMaterials.IRON,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.IRON, 6, -2.9F))));

    public static final Item DIAMOND_LONGSWORD = registerItem("diamond_longsword", new SwordItem(ToolMaterials.DIAMOND,
            new Item.Settings().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 6, -2.9F))));

    public static final Item NETHERITE_LONGSWORD = registerItem("netherite_longsword", new SwordItem(ToolMaterials.NETHERITE,
            new Item.Settings().fireproof().attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.NETHERITE, 6, -2.9F))));

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

    public static final Item WOODEN_GREATSWORD = registerItem("wooden_greatsword", new TwoHandedHeavySwordItem(ModToolMaterials.FAUX_WOOD,
            new Item.Settings().attributeModifiers(TwoHandedHeavySwordItem.createAttributeModifiers(ModToolMaterials.FAUX_WOOD, 8, -3.3F))));

    public static final Item GOLDEN_GREATSWORD = registerItem("golden_greatsword", new TwoHandedHeavySwordItem(ModToolMaterials.FAUX_GOLD,
            new Item.Settings().attributeModifiers(TwoHandedHeavySwordItem.createAttributeModifiers(ModToolMaterials.FAUX_GOLD, 8, -3.1F))));

    public static final Item STONE_GREATSWORD = registerItem("stone_greatsword", new TwoHandedHeavySwordItem(ModToolMaterials.FAUX_STONE,
            new Item.Settings().attributeModifiers(TwoHandedHeavySwordItem.createAttributeModifiers(ModToolMaterials.FAUX_STONE, 8, -3.3F))));

    public static final Item IRON_GREATSWORD = registerItem("iron_greatsword", new TwoHandedHeavySwordItem(ModToolMaterials.FAUX_IRON,
            new Item.Settings().attributeModifiers(TwoHandedHeavySwordItem.createAttributeModifiers(ModToolMaterials.FAUX_IRON, 8, -3.2F))));

    public static final Item DIAMOND_GREATSWORD = registerItem("diamond_greatsword", new TwoHandedHeavySwordItem(ModToolMaterials.FAUX_DIAMOND,
            new Item.Settings().attributeModifiers(TwoHandedHeavySwordItem.createAttributeModifiers(ModToolMaterials.FAUX_DIAMOND, 8, -3.1F))));

    public static final Item NETHERITE_GREATSWORD = registerItem("netherite_greatsword", new TwoHandedHeavySwordItem(ModToolMaterials.FAUX_NETHERITE,
            new Item.Settings().fireproof().attributeModifiers(TwoHandedHeavySwordItem.createAttributeModifiers(ModToolMaterials.FAUX_NETHERITE, 8, -3.1F))));

    public static final Item IRON_SPEAR = registerItem("iron_spear", new PierceWeaponItem(ToolMaterials.IRON,
            new Item.Settings().attributeModifiers(PierceWeaponItem.createAttributeModifiers(ToolMaterials.IRON, 4, -2.7F))));

    public static final Item IRON_RAPIER = registerItem("iron_rapier", new PierceWeaponItem(ToolMaterials.IRON,
            new Item.Settings().attributeModifiers(PierceWeaponItem.createAttributeModifiers(ToolMaterials.IRON, 2, -2.2F))));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(WeaponsExpanded.MOD_ID, name), item);
    }

    public static void registerModItems() {
        WeaponsExpanded.LOGGER.info("Registering Items for " + WeaponsExpanded.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(WOODEN_BROADSWORD);
            entries.add(GOLDEN_BROADSWORD);
            entries.add(STONE_BROADSWORD);
            entries.add(IRON_BROADSWORD);
            entries.add(DIAMOND_BROADSWORD);
            entries.add(NETHERITE_BROADSWORD);
            entries.add(WOODEN_SICKLE);
            entries.add(GOLDEN_SICKLE);
            entries.add(STONE_SICKLE);
            entries.add(IRON_SICKLE);
            entries.add(DIAMOND_SICKLE);
            entries.add(NETHERITE_SICKLE);
            entries.add(WOODEN_SCYTHE);
            entries.add(GOLDEN_SCYTHE);
            entries.add(STONE_SCYTHE);
            entries.add(IRON_SCYTHE);
            entries.add(DIAMOND_SCYTHE);
            entries.add(NETHERITE_SCYTHE);
            entries.add(WOODEN_LONGSWORD);
            entries.add(GOLDEN_LONGSWORD);
            entries.add(STONE_LONGSWORD);
            entries.add(IRON_LONGSWORD);
            entries.add(DIAMOND_LONGSWORD);
            entries.add(NETHERITE_LONGSWORD);
            entries.add(WOODEN_KATANA);
            entries.add(GOLDEN_KATANA);
            entries.add(STONE_KATANA);
            entries.add(IRON_KATANA);
            entries.add(DIAMOND_KATANA);
            entries.add(NETHERITE_KATANA);
            entries.add(WOODEN_HATCHET);
            entries.add(GOLDEN_HATCHET);
            entries.add(STONE_HATCHET);
            entries.add(IRON_HATCHET);
            entries.add(DIAMOND_HATCHET);
            entries.add(NETHERITE_HATCHET);
            entries.add(WOODEN_HAMMER);
            entries.add(GOLDEN_HAMMER);
            entries.add(STONE_HAMMER);
            entries.add(IRON_HAMMER);
            entries.add(DIAMOND_HAMMER);
            entries.add(NETHERITE_HAMMER);
            entries.add(WOODEN_BATTLEAXE);
            entries.add(GOLDEN_BATTLEAXE);
            entries.add(STONE_BATTLEAXE);
            entries.add(IRON_BATTLEAXE);
            entries.add(DIAMOND_BATTLEAXE);
            entries.add(NETHERITE_BATTLEAXE);
            entries.add(WOODEN_GREATSWORD);
            entries.add(GOLDEN_GREATSWORD);
            entries.add(STONE_GREATSWORD);
            entries.add(IRON_GREATSWORD);
            entries.add(DIAMOND_GREATSWORD);
            entries.add(NETHERITE_GREATSWORD);
            entries.add(IRON_SPEAR);
            entries.add(IRON_RAPIER);
        });
    }
}
