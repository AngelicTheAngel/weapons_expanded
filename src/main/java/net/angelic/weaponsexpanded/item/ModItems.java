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

import java.util.function.Function;

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

    public static final Item IRON_SPEAR = registerItem("iron_spear",
            settings -> new PierceWeaponItem(ToolMaterial.IRON, 4.5F, -2.7F, settings));

    public static final Item IRON_RAPIER = registerItem("iron_rapier",
            settings -> new PierceWeaponItem(ToolMaterial.IRON, 2.0F, -2.2F, settings));

    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(WeaponsExpanded.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID, name)))));
    }

    public static void registerModItems() {
        WeaponsExpanded.LOGGER.info("Registering Items for " + WeaponsExpanded.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(WOODEN_BROADSWORD);
            entries.add(GOLDEN_BROADSWORD);
            entries.add(STONE_BROADSWORD);
            entries.add(COPPER_BROADSWORD);
            entries.add(IRON_BROADSWORD);
            entries.add(DIAMOND_BROADSWORD);
            entries.add(NETHERITE_BROADSWORD);
            entries.add(WOODEN_SICKLE);
            entries.add(GOLDEN_SICKLE);
            entries.add(STONE_SICKLE);
            entries.add(COPPER_SICKLE);
            entries.add(IRON_SICKLE);
            entries.add(DIAMOND_SICKLE);
            entries.add(NETHERITE_SICKLE);
            entries.add(WOODEN_SCYTHE);
            entries.add(GOLDEN_SCYTHE);
            entries.add(STONE_SCYTHE);
            entries.add(COPPER_SCYTHE);
            entries.add(IRON_SCYTHE);
            entries.add(DIAMOND_SCYTHE);
            entries.add(NETHERITE_SCYTHE);
            entries.add(WOODEN_LONGSWORD);
            entries.add(GOLDEN_LONGSWORD);
            entries.add(STONE_LONGSWORD);
            entries.add(COPPER_LONGSWORD);
            entries.add(IRON_LONGSWORD);
            entries.add(DIAMOND_LONGSWORD);
            entries.add(NETHERITE_LONGSWORD);
            entries.add(WOODEN_KATANA);
            entries.add(GOLDEN_KATANA);
            entries.add(STONE_KATANA);
            entries.add(COPPER_KATANA);
            entries.add(IRON_KATANA);
            entries.add(DIAMOND_KATANA);
            entries.add(NETHERITE_KATANA);
            entries.add(WOODEN_HATCHET);
            entries.add(GOLDEN_HATCHET);
            entries.add(STONE_HATCHET);
            entries.add(COPPER_HATCHET);
            entries.add(IRON_HATCHET);
            entries.add(DIAMOND_HATCHET);
            entries.add(NETHERITE_HATCHET);
            entries.add(WOODEN_HAMMER);
            entries.add(GOLDEN_HAMMER);
            entries.add(STONE_HAMMER);
            entries.add(COPPER_HAMMER);
            entries.add(IRON_HAMMER);
            entries.add(DIAMOND_HAMMER);
            entries.add(NETHERITE_HAMMER);
            entries.add(WOODEN_BATTLEAXE);
            entries.add(GOLDEN_BATTLEAXE);
            entries.add(STONE_BATTLEAXE);
            entries.add(COPPER_BATTLEAXE);
            entries.add(IRON_BATTLEAXE);
            entries.add(DIAMOND_BATTLEAXE);
            entries.add(NETHERITE_BATTLEAXE);
            entries.add(WOODEN_GREATSWORD);
            entries.add(GOLDEN_GREATSWORD);
            entries.add(STONE_GREATSWORD);
            entries.add(COPPER_GREATSWORD);
            entries.add(IRON_GREATSWORD);
            entries.add(DIAMOND_GREATSWORD);
            entries.add(NETHERITE_GREATSWORD);
            entries.add(IRON_SPEAR);
            entries.add(IRON_RAPIER);
        });
    }
}
