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

    public static final Item STONE_BROADSWORD = registerItem("stone_broadsword", new SwordItem(ToolMaterial.STONE, 2.0F, -2.1F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"stone_broadsword")))));

    public static final Item IRON_BROADSWORD = registerItem("iron_broadsword", new SwordItem(ToolMaterial.IRON, 2.0F, -2.2F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"iron_broadsword")))));

    public static final Item DIAMOND_BROADSWORD = registerItem("diamond_broadsword", new SwordItem(ToolMaterial.DIAMOND, 2.0F, -2.2F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"diamond_broadsword")))));

    public static final Item NETHERITE_BROADSWORD = registerItem("netherite_broadsword", new SwordItem(ToolMaterial.NETHERITE, 2.0F, -2.2F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"netherite_broadsword")))));

    public static final Item WOODEN_SICKLE = registerItem("wooden_sickle", new SwordItem(ToolMaterial.WOOD, 2.5F, -2.3F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"wooden_sickle")))));

    public static final Item GOLDEN_SICKLE = registerItem("golden_sickle", new SwordItem(ToolMaterial.GOLD, 2.5F, -2.3F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"golden_sickle")))));

    public static final Item STONE_SICKLE = registerItem("stone_sickle", new SwordItem(ToolMaterial.STONE, 2.5F, -2.3F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"stone_sickle")))));

    public static final Item IRON_SICKLE = registerItem("iron_sickle", new SwordItem(ToolMaterial.IRON, 2.5F, -2.3F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"iron_sickle")))));

    public static final Item DIAMOND_SICKLE = registerItem("diamond_sickle", new SwordItem(ToolMaterial.DIAMOND, 2.5F, -2.3F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"diamond_sickle")))));

    public static final Item NETHERITE_SICKLE = registerItem("netherite_sickle", new SwordItem(ToolMaterial.NETHERITE, 2.5F, -2.3F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"netherite_sickle")))));

    public static final Item WOODEN_SCYTHE = registerItem("wooden_scythe", new SwordItem(ToolMaterial.WOOD, 4.0F, -2.5F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"wooden_scythe")))));

    public static final Item GOLDEN_SCYTHE = registerItem("golden_scythe", new SwordItem(ToolMaterial.GOLD, 4.0F, -2.5F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"golden_scythe")))));

    public static final Item STONE_SCYTHE = registerItem("stone_scythe", new SwordItem(ToolMaterial.STONE, 4.0F, -2.5F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"stone_scythe")))));

    public static final Item IRON_SCYTHE = registerItem("iron_scythe", new SwordItem(ToolMaterial.IRON, 4.0F, -2.5F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"iron_scythe")))));

    public static final Item DIAMOND_SCYTHE = registerItem("diamond_scythe", new SwordItem(ToolMaterial.DIAMOND, 4.5F, -2.5F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"diamond_scythe")))));

    public static final Item NETHERITE_SCYTHE = registerItem("netherite_scythe", new SwordItem(ToolMaterial.NETHERITE, 4.5F, -2.5F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"netherite_scythe")))));

    public static final Item WOODEN_LONGSWORD = registerItem("wooden_longsword", new SwordItem(ToolMaterial.WOOD, 6.0F, -2.9F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"wooden_longsword")))));

    public static final Item GOLDEN_LONGSWORD = registerItem("golden_longsword", new SwordItem(ToolMaterial.GOLD, 6.0F, -2.9F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"golden_longsword")))));

    public static final Item STONE_LONGSWORD = registerItem("stone_longsword", new SwordItem(ToolMaterial.STONE, 6.0F, -2.9F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"stone_longsword")))));

    public static final Item IRON_LONGSWORD = registerItem("iron_longsword", new SwordItem(ToolMaterial.IRON, 6.0F, -2.9F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"iron_longsword")))));

    public static final Item DIAMOND_LONGSWORD = registerItem("diamond_longsword", new SwordItem(ToolMaterial.DIAMOND, 6.0F, -2.9F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"diamond_longsword")))));

    public static final Item NETHERITE_LONGSWORD = registerItem("netherite_longsword", new SwordItem(ToolMaterial.NETHERITE, 6.0F, -2.9F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"netherite_longsword")))));

    public static final Item WOODEN_KATANA = registerItem("wooden_katana", new TwoHandedSwordItem(ToolMaterial.WOOD, 3.0F, -2.2F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"wooden_katana")))));

    public static final Item GOLDEN_KATANA = registerItem("golden_katana", new TwoHandedSwordItem(ToolMaterial.GOLD, 3.0F, -2.2F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"golden_katana")))));

    public static final Item STONE_KATANA = registerItem("stone_katana", new TwoHandedSwordItem(ToolMaterial.STONE, 3.0F, -2.2F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"stone_katana")))));

    public static final Item IRON_KATANA = registerItem("iron_katana", new TwoHandedSwordItem(ToolMaterial.IRON, 3.0F, -2.2F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"iron_katana")))));

    public static final Item DIAMOND_KATANA = registerItem("diamond_katana", new TwoHandedSwordItem(ToolMaterial.DIAMOND, 3.0F, -2.2F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"diamond_katana")))));

    public static final Item NETHERITE_KATANA = registerItem("netherite_katana", new TwoHandedSwordItem(ToolMaterial.NETHERITE, 3.0F, -2.2F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"netherite_katana")))));

    public static final Item WOODEN_HATCHET = registerItem("wooden_hatchet", new AxeItem(ToolMaterial.WOOD, 5.0F, -3.0F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"wooden_hatchet")))));

    public static final Item GOLDEN_HATCHET = registerItem("golden_hatchet", new AxeItem(ToolMaterial.GOLD, 5.0F, -2.8F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"golden_hatchet")))));
    
    public static final Item STONE_HATCHET = registerItem("stone_hatchet", new AxeItem(ToolMaterial.STONE, 6.0F, -3.0F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"stone_hatchet")))));

    public static final Item IRON_HATCHET = registerItem("iron_hatchet", new AxeItem(ToolMaterial.IRON, 5.0F, -2.9F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"iron_hatchet")))));

    public static final Item DIAMOND_HATCHET = registerItem("diamond_hatchet", new AxeItem(ToolMaterial.DIAMOND, 4.0F, -2.8F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"diamond_hatchet")))));

    public static final Item NETHERITE_HATCHET = registerItem("netherite_hatchet", new AxeItem(ToolMaterial.NETHERITE, 4.0F, -2.8F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"netherite_hatchet")))));

    public static final Item WOODEN_HAMMER = registerItem("wooden_hammer", new BluntWeaponItem(ModToolMaterials.FAUX_WOOD, 7.0F, -3.3F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"wooden_hammer")))));

    public static final Item GOLDEN_HAMMER = registerItem("golden_hammer", new BluntWeaponItem(ModToolMaterials.FAUX_GOLD, 7.0F, -3.1F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"golden_hammer")))));

    public static final Item STONE_HAMMER = registerItem("stone_hammer", new BluntWeaponItem(ModToolMaterials.FAUX_STONE, 8.0F, -3.3F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"stone_hammer")))));

    public static final Item IRON_HAMMER = registerItem("iron_hammer", new BluntWeaponItem(ModToolMaterials.FAUX_IRON, 7.0F, -3.2F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"iron_hammer")))));

    public static final Item DIAMOND_HAMMER = registerItem("diamond_hammer", new BluntWeaponItem(ModToolMaterials.FAUX_DIAMOND, 6.0F, -3.1F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"diamond_hammer")))));

    public static final Item NETHERITE_HAMMER = registerItem("netherite_hammer", new BluntWeaponItem(ModToolMaterials.FAUX_NETHERITE, 6.0F, -3.1F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"netherite_hammer")))));

    public static final Item WOODEN_BATTLEAXE = registerItem("wooden_battleaxe", new AxeItem(ToolMaterial.WOOD, 8.0F, -3.4F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"wooden_battleaxe")))));

    public static final Item GOLDEN_BATTLEAXE = registerItem("golden_battleaxe", new AxeItem(ToolMaterial.GOLD, 8.0F, -3.2F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"golden_battleaxe")))));

    public static final Item STONE_BATTLEAXE = registerItem("stone_battleaxe", new AxeItem(ToolMaterial.STONE, 9.0F, -3.4F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"stone_battleaxe")))));

    public static final Item IRON_BATTLEAXE = registerItem("iron_battleaxe", new AxeItem(ToolMaterial.IRON, 8.0F, -3.3F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"iron_battleaxe")))));

    public static final Item DIAMOND_BATTLEAXE = registerItem("diamond_battleaxe", new AxeItem(ToolMaterial.DIAMOND, 7.0F, -3.2F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"diamond_battleaxe")))));

    public static final Item NETHERITE_BATTLEAXE = registerItem("netherite_battleaxe", new AxeItem(ToolMaterial.NETHERITE, 7.0F, -3.2F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"netherite_battleaxe")))));

    public static final Item WOODEN_GREATSWORD = registerItem("wooden_greatsword", new TwoHandedHeavySwordItem(ToolMaterial.WOOD, 8.0F, -3.3F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"wooden_greatsword")))));

    public static final Item GOLDEN_GREATSWORD = registerItem("golden_greatsword", new TwoHandedHeavySwordItem(ToolMaterial.GOLD, 8.0F, -3.1F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"golden_greatsword")))));

    public static final Item STONE_GREATSWORD = registerItem("stone_greatsword", new TwoHandedHeavySwordItem(ToolMaterial.STONE, 8.0F, -3.3F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"stone_greatsword")))));

    public static final Item IRON_GREATSWORD = registerItem("iron_greatsword", new TwoHandedHeavySwordItem(ToolMaterial.IRON, 8.0F, -3.2F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"iron_greatsword")))));

    public static final Item DIAMOND_GREATSWORD = registerItem("diamond_greatsword", new TwoHandedHeavySwordItem(ToolMaterial.DIAMOND, 8.0F, -3.1F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"diamond_greatsword")))));

    public static final Item NETHERITE_GREATSWORD = registerItem("netherite_greatsword", new TwoHandedHeavySwordItem(ToolMaterial.NETHERITE, 8.0F, -3.1F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"netherite_greatsword")))));

    public static final Item IRON_SPEAR = registerItem("iron_spear", new PierceWeaponItem(ToolMaterial.IRON, 4.5F, -2.7F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"iron_spear")))));

    public static final Item IRON_RAPIER = registerItem("iron_rapier", new PierceWeaponItem(ToolMaterial.IRON, 2.0F, -2.2F,
            new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(WeaponsExpanded.MOD_ID,"iron_rapier")))));

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
