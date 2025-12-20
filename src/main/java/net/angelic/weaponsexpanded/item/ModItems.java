package net.angelic.weaponsexpanded.item;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.custom.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.util.function.Function;

import static net.minecraft.item.Items.*;

public class ModItems {

    public static final Item WOODEN_BROADSWORD = registerItem("wooden_broadsword",
            setting -> new Item(setting.sword(ToolMaterial.WOOD, 2, -2.1f)));

    public static final Item GOLDEN_BROADSWORD = registerItem("golden_broadsword",
            setting -> new Item(setting.sword(ToolMaterial.GOLD, 2, -2.1f)));

    public static final Item STONE_BROADSWORD = registerItem("stone_broadsword",
            setting -> new Item(setting.sword(ToolMaterial.STONE, 2, -2.1F)));

    public static final Item COPPER_BROADSWORD = registerItem("copper_broadsword",
            setting -> new Item(setting.sword(ToolMaterial.COPPER, 2, -2.1F)));

    public static final Item IRON_BROADSWORD = registerItem("iron_broadsword",
            settings -> new Item(settings.sword(ToolMaterial.IRON, 2.0F, -2.2F)));

    public static final Item DIAMOND_BROADSWORD = registerItem("diamond_broadsword",
            settings -> new Item(settings.sword(ToolMaterial.DIAMOND, 2.0F, -2.2F)));

    public static final Item NETHERITE_BROADSWORD = registerItem("netherite_broadsword",
            settings -> new Item(settings.sword(ToolMaterial.NETHERITE, 2.0F, -2.2F).fireproof()));

    public static final Item WOODEN_SICKLE = registerItem("wooden_sickle",
            settings -> new Item(settings.sword(ToolMaterial.WOOD, 2.5F, -2.3F)));

    public static final Item GOLDEN_SICKLE = registerItem("golden_sickle",
            settings -> new Item(settings.sword(ToolMaterial.GOLD, 2.5F, -2.3F)));

    public static final Item STONE_SICKLE = registerItem("stone_sickle",
            settings -> new Item(settings.sword(ToolMaterial.STONE, 2.5F, -2.3F)));

    public static final Item COPPER_SICKLE = registerItem("copper_sickle",
            settings -> new Item(settings.sword(ToolMaterial.COPPER, 2.5F, -2.3F)));

    public static final Item IRON_SICKLE = registerItem("iron_sickle",
            settings -> new Item(settings.sword(ToolMaterial.IRON, 2.5F, -2.3F)));

    public static final Item DIAMOND_SICKLE = registerItem("diamond_sickle",
            settings -> new Item(settings.sword(ToolMaterial.DIAMOND, 2.5F, -2.3F)));

    public static final Item NETHERITE_SICKLE = registerItem("netherite_sickle",
            settings -> new Item(settings.sword(ToolMaterial.NETHERITE, 2.5F, -2.3F).fireproof()));

    public static final Item WOODEN_SCYTHE = registerItem("wooden_scythe",
            settings -> new Item(settings.sword(ToolMaterial.WOOD, 4.0F, -2.5F)));

    public static final Item GOLDEN_SCYTHE = registerItem("golden_scythe",
            settings -> new Item(settings.sword(ToolMaterial.GOLD, 4.0F, -2.5F)));

    public static final Item STONE_SCYTHE = registerItem("stone_scythe",
            settings -> new Item(settings.sword(ToolMaterial.STONE, 4.0F, -2.5F)));

    public static final Item COPPER_SCYTHE = registerItem("copper_scythe",
            settings -> new Item(settings.sword(ToolMaterial.COPPER, 4.0F, -2.5F)));

    public static final Item IRON_SCYTHE = registerItem("iron_scythe",
            settings -> new Item(settings.sword(ToolMaterial.IRON, 4.0F, -2.5F)));

    public static final Item DIAMOND_SCYTHE = registerItem("diamond_scythe",
            settings -> new Item(settings.sword(ToolMaterial.DIAMOND, 4.5F, -2.5F)));

    public static final Item NETHERITE_SCYTHE = registerItem("netherite_scythe",
            settings -> new Item(settings.sword(ToolMaterial.NETHERITE, 4.5F, -2.5F).fireproof()));

    public static final Item WOODEN_LONGSWORD = registerItem("wooden_longsword",
            settings -> new Item(settings.sword(ToolMaterial.WOOD, 6.0F, -2.9F)));

    public static final Item GOLDEN_LONGSWORD = registerItem("golden_longsword",
            settings -> new Item(settings.sword(ToolMaterial.GOLD, 6.0F, -2.9F)));

    public static final Item STONE_LONGSWORD = registerItem("stone_longsword",
            settings -> new Item(settings.sword(ToolMaterial.STONE, 6.0F, -2.9F)));

    public static final Item COPPER_LONGSWORD = registerItem("copper_longsword",
            settings -> new Item(settings.sword(ToolMaterial.COPPER, 6.0F, -2.9F)));

    public static final Item IRON_LONGSWORD = registerItem("iron_longsword",
            settings -> new Item(settings.sword(ToolMaterial.IRON, 6.0F, -2.9F)));

    public static final Item DIAMOND_LONGSWORD = registerItem("diamond_longsword",
            settings -> new Item(settings.sword(ToolMaterial.DIAMOND, 6.0F, -2.9F)));

    public static final Item NETHERITE_LONGSWORD = registerItem("netherite_longsword",
            settings -> new Item(settings.sword(ToolMaterial.NETHERITE, 6.0F, -2.9F).fireproof()));

    public static final Item WOODEN_KATANA = registerItem("wooden_katana",
            settings -> new TwoHandedSwordItem(ToolMaterial.WOOD, 3.0F, -2.2F, settings));

    public static final Item GOLDEN_KATANA = registerItem("golden_katana",
            settings -> new TwoHandedSwordItem(ToolMaterial.GOLD, 3.0F, -2.2F, settings));

    public static final Item STONE_KATANA = registerItem("stone_katana",
            settings -> new TwoHandedSwordItem(ToolMaterial.STONE, 3.0F, -2.2F, settings));

    public static final Item COPPER_KATANA = registerItem("copper_katana",
            settings -> new TwoHandedSwordItem(ToolMaterial.COPPER, 3.0F, -2.2F, settings));

    public static final Item IRON_KATANA = registerItem("iron_katana",
            settings -> new TwoHandedSwordItem(ToolMaterial.IRON, 3.0F, -2.2F, settings));

    public static final Item DIAMOND_KATANA = registerItem("diamond_katana",
            settings -> new TwoHandedSwordItem(ToolMaterial.DIAMOND, 3.0F, -2.2F, settings));

    public static final Item NETHERITE_KATANA = registerItem("netherite_katana",
            settings -> new TwoHandedSwordItem(ToolMaterial.NETHERITE, 3.0F, -2.2F, settings.fireproof()));

    public static final Item WOODEN_HATCHET = registerItem("wooden_hatchet",
            settings -> new Item(settings.axe(ToolMaterial.WOOD, 5.0F, -3.0F)));

    public static final Item GOLDEN_HATCHET = registerItem("golden_hatchet",
            settings -> new Item(settings.axe(ToolMaterial.GOLD, 5.0F, -2.8F)));

    public static final Item STONE_HATCHET = registerItem("stone_hatchet",
            settings -> new Item(settings.axe(ToolMaterial.STONE, 6.0F, -3.0F)));

    public static final Item COPPER_HATCHET = registerItem("copper_hatchet",
            settings -> new Item(settings.axe(ToolMaterial.COPPER, 6.0F, -3.0F)));

    public static final Item IRON_HATCHET = registerItem("iron_hatchet",
            settings -> new Item(settings.axe(ToolMaterial.IRON, 5.0F, -2.9F)));

    public static final Item DIAMOND_HATCHET = registerItem("diamond_hatchet",
            settings -> new Item(settings.axe(ToolMaterial.DIAMOND, 4.0F, -2.8F)));

    public static final Item NETHERITE_HATCHET = registerItem("netherite_hatchet",
            settings -> new Item(settings.axe(ToolMaterial.NETHERITE, 4.0F, -2.8F).fireproof()));

    public static final Item WOODEN_HAMMER = registerItem("wooden_hammer",
            settings -> new BluntWeaponItem(ModToolMaterials.FAUX_WOOD, 7.0F, -3.3F, settings));

    public static final Item GOLDEN_HAMMER = registerItem("golden_hammer",
            settings -> new BluntWeaponItem(ModToolMaterials.FAUX_GOLD, 7.0F, -3.1F, settings));

    public static final Item STONE_HAMMER = registerItem("stone_hammer",
            settings -> new BluntWeaponItem(ModToolMaterials.FAUX_STONE, 8.0F, -3.3F, settings));

    public static final Item COPPER_HAMMER = registerItem("copper_hammer",
            settings -> new BluntWeaponItem(ModToolMaterials.FAUX_COPPER, 8.0F, -3.3F, settings));

    public static final Item IRON_HAMMER = registerItem("iron_hammer",
            settings -> new BluntWeaponItem(ModToolMaterials.FAUX_IRON, 7.0F, -3.2F, settings));

    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer",
            settings -> new BluntWeaponItem(ModToolMaterials.FAUX_DIAMOND, 6.0F, -3.1F, settings));

    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer",
            settings -> new BluntWeaponItem(ModToolMaterials.FAUX_NETHERITE, 6.0F, -3.1F, settings.fireproof()));

    public static final Item WOODEN_BATTLEAXE = registerItem("wooden_battleaxe",
            settings -> new Item(settings.axe(ToolMaterial.WOOD, 8.0F, -3.4F)));

    public static final Item GOLDEN_BATTLEAXE = registerItem("golden_battleaxe",
            settings -> new Item(settings.axe(ToolMaterial.GOLD, 8.0F, -3.2F)));

    public static final Item STONE_BATTLEAXE = registerItem("stone_battleaxe",
            settings -> new Item(settings.axe(ToolMaterial.STONE, 9.0F, -3.4F)));

    public static final Item COPPER_BATTLEAXE = registerItem("copper_battleaxe",
            settings -> new Item(settings.axe(ToolMaterial.COPPER, 9.0F, -3.4F)));

    public static final Item IRON_BATTLEAXE = registerItem("iron_battleaxe",
            settings -> new Item(settings.axe(ToolMaterial.IRON, 8.0F, -3.3F)));

    public static final Item DIAMOND_BATTLEAXE = registerItem("diamond_battleaxe",
            settings -> new Item(settings.axe(ToolMaterial.DIAMOND, 7.0F, -3.2F)));

    public static final Item NETHERITE_BATTLEAXE = registerItem("netherite_battleaxe",
            settings -> new Item(settings.axe(ToolMaterial.NETHERITE, 7.0F, -3.2F).fireproof()));

    public static final Item WOODEN_GREATSWORD = registerItem("wooden_greatsword",
            settings -> new TwoHandedHeavySwordItem(ToolMaterial.WOOD, 8.0F, -3.3F, settings));

    public static final Item GOLDEN_GREATSWORD = registerItem("golden_greatsword",
            settings -> new TwoHandedHeavySwordItem(ToolMaterial.GOLD, 8.0F, -3.1F, settings));

    public static final Item STONE_GREATSWORD = registerItem("stone_greatsword",
            settings -> new TwoHandedHeavySwordItem(ToolMaterial.STONE, 8.0F, -3.3F, settings));

    public static final Item COPPER_GREATSWORD = registerItem("copper_greatsword",
            settings -> new TwoHandedHeavySwordItem(ToolMaterial.COPPER, 8.0F, -3.3F, settings));

    public static final Item IRON_GREATSWORD = registerItem("iron_greatsword",
            settings -> new TwoHandedHeavySwordItem(ToolMaterial.IRON, 8.0F, -3.2F, settings));

    public static final Item DIAMOND_GREATSWORD = registerItem("diamond_greatsword",
            settings -> new TwoHandedHeavySwordItem(ToolMaterial.DIAMOND, 8.0F, -3.1F, settings));

    public static final Item NETHERITE_GREATSWORD = registerItem("netherite_greatsword",
            settings -> new TwoHandedHeavySwordItem(ToolMaterial.NETHERITE, 8.0F, -3.1F, settings.fireproof()));

    public static final Item HEAVY_ARROW = registerItem("heavy_arrow",
            settings -> new HeavyArrowItem(settings.maxCount(64)));

    public static final Item LONGBOW = registerItem("longbow",
            settings -> new LongbowItem(settings.maxDamage(384).enchantable(1)));

    public static final Item CHAIN_CROSSBOW = registerItem("chain_crossbow",
            settings -> new ChainCrossbowItem(settings.maxDamage(465).enchantable(1)));

    public static final Item IRON_SPEAR = registerItem("iron_spear",
            settings -> new PierceWeaponItem(ToolMaterial.IRON, 4.5F, -2.7F, settings));

    public static final Item IRON_RAPIER = registerItem("iron_rapier",
            settings -> new PierceWeaponItem(ToolMaterial.IRON, 2.0F, -2.2F, settings));

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(WeaponsExpanded.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID, name)))));
    }

    public static void registerModItems() {
        WeaponsExpanded.LOGGER.info("Registering items for " + WeaponsExpanded.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            // Ranged placement relative to vanilla items
            entries.addAfter(Items.BOW, LONGBOW);
            entries.addAfter(Items.CROSSBOW, CHAIN_CROSSBOW);
            entries.addAfter(Items.SPECTRAL_ARROW, HEAVY_ARROW);

            Item anchor = Items.WOODEN_SWORD;

            // Broadswords
            entries.addBefore(anchor, WOODEN_BROADSWORD);
            entries.addAfter(WOODEN_BROADSWORD, GOLDEN_BROADSWORD);
            entries.addAfter(GOLDEN_BROADSWORD, STONE_BROADSWORD);
            entries.addAfter(STONE_BROADSWORD, COPPER_BROADSWORD);
            entries.addAfter(COPPER_BROADSWORD, IRON_BROADSWORD);
            entries.addAfter(IRON_BROADSWORD, DIAMOND_BROADSWORD);
            entries.addAfter(DIAMOND_BROADSWORD, NETHERITE_BROADSWORD);
            anchor = NETHERITE_BROADSWORD;

            // Sickles
            entries.addAfter(anchor, WOODEN_SICKLE);
            entries.addAfter(WOODEN_SICKLE, GOLDEN_SICKLE);
            entries.addAfter(GOLDEN_SICKLE, STONE_SICKLE);
            entries.addAfter(STONE_SICKLE, COPPER_SICKLE);
            entries.addAfter(COPPER_SICKLE, IRON_SICKLE);
            entries.addAfter(IRON_SICKLE, DIAMOND_SICKLE);
            entries.addAfter(DIAMOND_SICKLE, NETHERITE_SICKLE);
            anchor = NETHERITE_SWORD;

            // Scythes
            entries.addAfter(anchor, WOODEN_SCYTHE);
            entries.addAfter(WOODEN_SCYTHE, GOLDEN_SCYTHE);
            entries.addAfter(GOLDEN_SCYTHE, STONE_SCYTHE);
            entries.addAfter(STONE_SCYTHE, COPPER_SCYTHE);
            entries.addAfter(COPPER_SCYTHE, IRON_SCYTHE);
            entries.addAfter(IRON_SCYTHE, DIAMOND_SCYTHE);
            entries.addAfter(DIAMOND_SCYTHE, NETHERITE_SCYTHE);
            anchor = NETHERITE_SCYTHE;

            // Longswords
            entries.addAfter(anchor, WOODEN_LONGSWORD);
            entries.addAfter(WOODEN_LONGSWORD, GOLDEN_LONGSWORD);
            entries.addAfter(GOLDEN_LONGSWORD, STONE_LONGSWORD);
            entries.addAfter(STONE_LONGSWORD, COPPER_LONGSWORD);
            entries.addAfter(COPPER_LONGSWORD, IRON_LONGSWORD);
            entries.addAfter(IRON_LONGSWORD, DIAMOND_LONGSWORD);
            entries.addAfter(DIAMOND_LONGSWORD, NETHERITE_LONGSWORD);
            anchor = NETHERITE_LONGSWORD;

            // Katanas
            entries.addAfter(anchor, WOODEN_KATANA);
            entries.addAfter(WOODEN_KATANA, GOLDEN_KATANA);
            entries.addAfter(GOLDEN_KATANA, STONE_KATANA);
            entries.addAfter(STONE_KATANA, COPPER_KATANA);
            entries.addAfter(COPPER_KATANA, IRON_KATANA);
            entries.addAfter(IRON_KATANA, DIAMOND_KATANA);
            entries.addAfter(DIAMOND_KATANA, NETHERITE_KATANA);
            anchor = WOODEN_AXE;

            // Hatchets
            entries.addBefore(anchor, WOODEN_HATCHET);
            entries.addAfter(WOODEN_HATCHET, GOLDEN_HATCHET);
            entries.addAfter(GOLDEN_HATCHET, STONE_HATCHET);
            entries.addAfter(STONE_HATCHET, COPPER_HATCHET);
            entries.addAfter(COPPER_HATCHET, IRON_HATCHET);
            entries.addAfter(IRON_HATCHET, DIAMOND_HATCHET);
            entries.addAfter(DIAMOND_HATCHET, NETHERITE_HATCHET);
            anchor = NETHERITE_AXE;

            // Hammers
            entries.addAfter(anchor, WOODEN_HAMMER);
            entries.addAfter(WOODEN_HAMMER, GOLDEN_HAMMER);
            entries.addAfter(GOLDEN_HAMMER, STONE_HAMMER);
            entries.addAfter(STONE_HAMMER, COPPER_HAMMER);
            entries.addAfter(COPPER_HAMMER, IRON_HAMMER);
            entries.addAfter(IRON_HAMMER, DIAMOND_HAMMER);
            entries.addAfter(DIAMOND_HAMMER, NETHERITE_HAMMER);
            anchor = NETHERITE_HAMMER;

            // Battleaxes
            entries.addAfter(anchor, WOODEN_BATTLEAXE);
            entries.addAfter(WOODEN_BATTLEAXE, GOLDEN_BATTLEAXE);
            entries.addAfter(GOLDEN_BATTLEAXE, STONE_BATTLEAXE);
            entries.addAfter(STONE_BATTLEAXE, COPPER_BATTLEAXE);
            entries.addAfter(COPPER_BATTLEAXE, IRON_BATTLEAXE);
            entries.addAfter(IRON_BATTLEAXE, DIAMOND_BATTLEAXE);
            entries.addAfter(DIAMOND_BATTLEAXE, NETHERITE_BATTLEAXE);
            anchor = NETHERITE_KATANA;

            // Greatswords
            entries.addAfter(anchor, WOODEN_GREATSWORD);
            entries.addAfter(WOODEN_GREATSWORD, GOLDEN_GREATSWORD);
            entries.addAfter(GOLDEN_GREATSWORD, STONE_GREATSWORD);
            entries.addAfter(STONE_GREATSWORD, COPPER_GREATSWORD);
            entries.addAfter(COPPER_GREATSWORD, IRON_GREATSWORD);
            entries.addAfter(IRON_GREATSWORD, DIAMOND_GREATSWORD);
            entries.addAfter(DIAMOND_GREATSWORD, NETHERITE_GREATSWORD);
        });
    }
}
