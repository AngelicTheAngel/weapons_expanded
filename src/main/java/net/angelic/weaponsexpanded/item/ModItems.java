package net.angelic.weaponsexpanded.item;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.custom.*;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public final class ModItems {
    private static final CreativeModeTab.TabVisibility TAB_VISIBILITY =
            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;

    public static final DeferredRegister.Items ITEMS =
            DeferredRegister.createItems(WeaponsExpanded.MOD_ID);

    public static final Supplier<Item> WOODEN_BROADSWORD = registerItem("wooden_broadsword",
            properties -> new Item(properties.sword(ToolMaterial.WOOD, 2.0F, -2.1F)));
    public static final Supplier<Item> GOLDEN_BROADSWORD = registerItem("golden_broadsword",
            properties -> new Item(properties.sword(ToolMaterial.GOLD, 2.0F, -2.1F)));
    public static final Supplier<Item> STONE_BROADSWORD = registerItem("stone_broadsword",
            properties -> new Item(properties.sword(ToolMaterial.STONE, 2.0F, -2.1F)));
    public static final Supplier<Item> COPPER_BROADSWORD = registerItem("copper_broadsword",
            properties -> new Item(properties.sword(ToolMaterial.COPPER, 2.0F, -2.1F)));
    public static final Supplier<Item> IRON_BROADSWORD = registerItem("iron_broadsword",
            properties -> new Item(properties.sword(ToolMaterial.IRON, 2.0F, -2.2F)));
    public static final Supplier<Item> DIAMOND_BROADSWORD = registerItem("diamond_broadsword",
            properties -> new Item(properties.sword(ToolMaterial.DIAMOND, 2.0F, -2.2F)));
    public static final Supplier<Item> NETHERITE_BROADSWORD = registerItem("netherite_broadsword",
            properties -> new Item(properties.sword(ToolMaterial.NETHERITE, 2.0F, -2.2F).fireResistant()));

    public static final Supplier<Item> WOODEN_SICKLE = registerItem("wooden_sickle",
            properties -> new Item(properties.sword(ToolMaterial.WOOD, 2.5f, -2.3F)));
    public static final Supplier<Item> GOLDEN_SICKLE = registerItem("golden_sickle",
            properties -> new Item(properties.sword(ToolMaterial.GOLD, 2.5f, -2.3F)));
    public static final Supplier<Item> STONE_SICKLE = registerItem("stone_sickle",
            properties -> new Item(properties.sword(ToolMaterial.STONE, 2.5f, -2.3F)));
    public static final Supplier<Item> COPPER_SICKLE = registerItem("copper_sickle",
            properties -> new Item(properties.sword(ToolMaterial.COPPER, 2.5f, -2.3F)));
    public static final Supplier<Item> IRON_SICKLE = registerItem("iron_sickle",
            properties -> new Item(properties.sword(ToolMaterial.IRON, 2.5f, -2.3F)));
    public static final Supplier<Item> DIAMOND_SICKLE = registerItem("diamond_sickle",
            properties -> new Item(properties.sword(ToolMaterial.DIAMOND, 2.5f, -2.3F)));
    public static final Supplier<Item> NETHERITE_SICKLE = registerItem("netherite_sickle",
            properties -> new Item(properties.sword(ToolMaterial.NETHERITE, 2.5f, -2.3F).fireResistant()));

    public static final Supplier<Item> WOODEN_SCYTHE = registerItem("wooden_scythe",
            properties -> new Item(properties.sword(ToolMaterial.WOOD, 4.0F, -2.5F)));
    public static final Supplier<Item> GOLDEN_SCYTHE = registerItem("golden_scythe",
            properties -> new Item(properties.sword(ToolMaterial.GOLD, 4.0F, -2.5F)));
    public static final Supplier<Item> STONE_SCYTHE = registerItem("stone_scythe",
            properties -> new Item(properties.sword(ToolMaterial.STONE, 4.0F, -2.5F)));
    public static final Supplier<Item> COPPER_SCYTHE = registerItem("copper_scythe",
            properties -> new Item(properties.sword(ToolMaterial.COPPER, 4.0F, -2.5F)));
    public static final Supplier<Item> IRON_SCYTHE = registerItem("iron_scythe",
            properties -> new Item(properties.sword(ToolMaterial.IRON, 4.0F, -2.5F)));
    public static final Supplier<Item> DIAMOND_SCYTHE = registerItem("diamond_scythe",
            properties -> new Item(properties.sword(ToolMaterial.DIAMOND, 4.5F, -2.5F)));
    public static final Supplier<Item> NETHERITE_SCYTHE = registerItem("netherite_scythe",
            properties -> new Item(properties.sword(ToolMaterial.NETHERITE, 4.5F, -2.5F).fireResistant()));

    public static final Supplier<Item> WOODEN_LONGSWORD = registerItem("wooden_longsword",
            properties -> new BastardSwordItem(ToolMaterial.WOOD, 6, -2.9F, 7, -2.9F, properties));
    public static final Supplier<Item> GOLDEN_LONGSWORD = registerItem("golden_longsword",
            properties -> new BastardSwordItem(ToolMaterial.GOLD, 6, -2.9F, 7, -2.9F, properties));
    public static final Supplier<Item> STONE_LONGSWORD = registerItem("stone_longsword",
            properties -> new BastardSwordItem(ToolMaterial.STONE, 6, -2.9F, 7, -2.9F, properties));
    public static final Supplier<Item> COPPER_LONGSWORD = registerItem("copper_longsword",
            properties -> new BastardSwordItem(ToolMaterial.COPPER, 6, -2.9F, 7, -2.9F, properties));
    public static final Supplier<Item> IRON_LONGSWORD = registerItem("iron_longsword",
            properties -> new BastardSwordItem(ToolMaterial.IRON, 6, -2.9F, 7, -2.9F, properties));
    public static final Supplier<Item> DIAMOND_LONGSWORD = registerItem("diamond_longsword",
            properties -> new BastardSwordItem(ToolMaterial.DIAMOND, 6, -2.9F, 7, -2.9F, properties));
    public static final Supplier<Item> NETHERITE_LONGSWORD = registerItem("netherite_longsword",
            properties -> new BastardSwordItem(ToolMaterial.NETHERITE, 6, -2.9F, 7, -2.9F, properties.fireResistant()));

    public static final Supplier<Item> WOODEN_KATANA = registerItem("wooden_katana",
            properties -> new TwoHandedSwordItem(ToolMaterial.WOOD, 3, -2.2F, properties));
    public static final Supplier<Item> GOLDEN_KATANA = registerItem("golden_katana",
            properties -> new TwoHandedSwordItem(ToolMaterial.GOLD, 3, -2.2F, properties));
    public static final Supplier<Item> STONE_KATANA = registerItem("stone_katana",
            properties -> new TwoHandedSwordItem(ToolMaterial.STONE, 3, -2.2F, properties));
    public static final Supplier<Item> COPPER_KATANA = registerItem("copper_katana",
            properties -> new TwoHandedSwordItem(ToolMaterial.COPPER, 3, -2.2F, properties));
    public static final Supplier<Item> IRON_KATANA = registerItem("iron_katana",
            properties -> new TwoHandedSwordItem(ToolMaterial.IRON, 3, -2.2F, properties));
    public static final Supplier<Item> DIAMOND_KATANA = registerItem("diamond_katana",
            properties -> new TwoHandedSwordItem(ToolMaterial.DIAMOND, 3, -2.2F, properties));
    public static final Supplier<Item> NETHERITE_KATANA = registerItem("netherite_katana",
            properties -> new TwoHandedSwordItem(ToolMaterial.NETHERITE, 3, -2.2F, properties.fireResistant()));

    public static final Supplier<Item> WOODEN_HATCHET = registerItem("wooden_hatchet",
            properties -> new AxeItem(ToolMaterial.WOOD, 5.0F, -3.0F, properties));
    public static final Supplier<Item> GOLDEN_HATCHET = registerItem("golden_hatchet",
            properties -> new AxeItem(ToolMaterial.GOLD, 5.0F, -2.8F, properties));
    public static final Supplier<Item> STONE_HATCHET = registerItem("stone_hatchet",
            properties -> new AxeItem(ToolMaterial.STONE, 6.0F, -3.0F, properties));
    public static final Supplier<Item> COPPER_HATCHET = registerItem("copper_hatchet",
            properties -> new AxeItem(ToolMaterial.COPPER, 6.0F, -3.0F, properties));
    public static final Supplier<Item> IRON_HATCHET = registerItem("iron_hatchet",
            properties -> new AxeItem(ToolMaterial.IRON, 5.0F, -2.9F, properties));
    public static final Supplier<Item> DIAMOND_HATCHET = registerItem("diamond_hatchet",
            properties -> new AxeItem(ToolMaterial.DIAMOND, 4.0F, -2.8F, properties));
    public static final Supplier<Item> NETHERITE_HATCHET = registerItem("netherite_hatchet",
            properties -> new AxeItem(ToolMaterial.NETHERITE, 4.0F, -2.8F, properties.fireResistant()));

    public static final Supplier<Item> WOODEN_HAMMER = registerItem("wooden_hammer",
            properties -> new BluntWeaponItem(ModToolMaterials.FAUX_WOOD, 7, -3.3F, properties));
    public static final Supplier<Item> GOLDEN_HAMMER = registerItem("golden_hammer",
            properties -> new BluntWeaponItem(ModToolMaterials.FAUX_GOLD, 7, -3.1F, properties));
    public static final Supplier<Item> STONE_HAMMER = registerItem("stone_hammer",
            properties -> new BluntWeaponItem(ModToolMaterials.FAUX_STONE, 8, -3.3F, properties));
    public static final Supplier<Item> COPPER_HAMMER = registerItem("copper_hammer",
            properties -> new BluntWeaponItem(ModToolMaterials.FAUX_COPPER, 8, -3.3F, properties));
    public static final Supplier<Item> IRON_HAMMER = registerItem("iron_hammer",
            properties -> new BluntWeaponItem(ModToolMaterials.FAUX_IRON, 7, -3.2F, properties));
    public static final Supplier<Item> DIAMOND_HAMMER = registerItem("diamond_hammer",
            properties -> new BluntWeaponItem(ModToolMaterials.FAUX_DIAMOND, 6, -3.1F, properties));
    public static final Supplier<Item> NETHERITE_HAMMER = registerItem("netherite_hammer",
            properties -> new BluntWeaponItem(ModToolMaterials.FAUX_NETHERITE, 6, -3.1F, properties.fireResistant()));

    public static final Supplier<Item> WOODEN_BATTLEAXE = registerItem("wooden_battleaxe",
            properties -> new AxeItem(ToolMaterial.WOOD, 8.0F, -3.4F, properties));
    public static final Supplier<Item> GOLDEN_BATTLEAXE = registerItem("golden_battleaxe",
            properties -> new AxeItem(ToolMaterial.GOLD, 8.0F, -3.2F, properties));
    public static final Supplier<Item> STONE_BATTLEAXE = registerItem("stone_battleaxe",
            properties -> new AxeItem(ToolMaterial.STONE, 9.0F, -3.4F, properties));
    public static final Supplier<Item> COPPER_BATTLEAXE = registerItem("copper_battleaxe",
            properties -> new AxeItem(ToolMaterial.COPPER, 9.0F, -3.4F, properties));
    public static final Supplier<Item> IRON_BATTLEAXE = registerItem("iron_battleaxe",
            properties -> new AxeItem(ToolMaterial.IRON, 8.0F, -3.3F, properties));
    public static final Supplier<Item> DIAMOND_BATTLEAXE = registerItem("diamond_battleaxe",
            properties -> new AxeItem(ToolMaterial.DIAMOND, 7.0F, -3.2F, properties));
    public static final Supplier<Item> NETHERITE_BATTLEAXE = registerItem("netherite_battleaxe",
            properties -> new AxeItem(ToolMaterial.NETHERITE, 7.0F, -3.2F, properties.fireResistant()));

    public static final Supplier<Item> WOODEN_GREATSWORD = registerItem("wooden_greatsword",
            properties -> new TwoHandedSwordItem(ToolMaterial.WOOD, 8, -3.3F, properties));
    public static final Supplier<Item> GOLDEN_GREATSWORD = registerItem("golden_greatsword",
            properties -> new TwoHandedSwordItem(ToolMaterial.GOLD, 8, -3.1F, properties));
    public static final Supplier<Item> STONE_GREATSWORD = registerItem("stone_greatsword",
            properties -> new TwoHandedSwordItem(ToolMaterial.STONE, 8, -3.3F, properties));
    public static final Supplier<Item> COPPER_GREATSWORD = registerItem("copper_greatsword",
            properties -> new TwoHandedSwordItem(ToolMaterial.COPPER, 8, -3.3F, properties));
    public static final Supplier<Item> IRON_GREATSWORD = registerItem("iron_greatsword",
            properties -> new TwoHandedSwordItem(ToolMaterial.IRON, 8, -3.2F, properties));
    public static final Supplier<Item> DIAMOND_GREATSWORD = registerItem("diamond_greatsword",
            properties -> new TwoHandedSwordItem(ToolMaterial.DIAMOND, 8, -3.1F, properties));
    public static final Supplier<Item> NETHERITE_GREATSWORD = registerItem("netherite_greatsword",
            properties -> new TwoHandedSwordItem(ToolMaterial.NETHERITE, 8, -3.1F, properties.fireResistant()));

    public static final Supplier<Item> WOODEN_WARHAMMER = registerItem("wooden_warhammer",
            properties -> new WarhammerItem(ToolMaterial.WOOD, 5, -3.1F, 5, -2.7F, "wooden_warhammer", properties));
    public static final Supplier<Item> GOLDEN_WARHAMMER = registerItem("golden_warhammer",
            properties -> new WarhammerItem(ToolMaterial.GOLD, 5, -2.9F, 5, -2.7F, "golden_warhammer", properties));
    public static final Supplier<Item> STONE_WARHAMMER = registerItem("stone_warhammer",
            properties -> new WarhammerItem(ToolMaterial.STONE, 6, -3.1F, 5, -2.7F, "stone_warhammer", properties));
    public static final Supplier<Item> COPPER_WARHAMMER = registerItem("copper_warhammer",
            properties -> new WarhammerItem(ToolMaterial.COPPER, 6, -3.1F, 5, -2.7F, "stone_warhammer", properties));
    public static final Supplier<Item> IRON_WARHAMMER = registerItem("iron_warhammer",
            properties -> new WarhammerItem(ToolMaterial.IRON, 5, -3.0F, 5, -2.7F, "iron_warhammer", properties));
    public static final Supplier<Item> DIAMOND_WARHAMMER = registerItem("diamond_warhammer",
            properties -> new WarhammerItem(ToolMaterial.DIAMOND, 4, -2.9F, 5, -2.7F, "diamond_warhammer", properties));
    public static final Supplier<Item> NETHERITE_WARHAMMER = registerItem("netherite_warhammer",
            properties -> new WarhammerItem(ToolMaterial.NETHERITE, 4, -2.9F, 5, -2.7F, "netherite_warhammer", properties.fireResistant()));

    public static final Supplier<Item> HEAVY_ARROW = registerItem("heavy_arrow",
            properties -> new HeavyArrowItem(properties.stacksTo(64)));
    public static final Supplier<Item> EXPLOSIVE_ARROW = registerItem("explosive_arrow",
            properties -> new ExplosiveArrowItem(properties.stacksTo(64)));
    public static final Supplier<Item> LONGBOW = registerItem("longbow",
            properties -> new LongbowItem(properties.durability(384)));
    public static final Supplier<Item> CHAIN_CROSSBOW = registerItem("chain_crossbow",
            properties -> new ChainCrossbowItem(properties.durability(465)));

    private ModItems() {}

    private static <T extends Item> DeferredItem<T> registerItem(
            String name,
            Function<Item.Properties, T> factory
    ) {
        return ITEMS.registerItem(name, factory);
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
        modEventBus.addListener(ModItems::addCreativeTabItems);
    }

    private static void addCreativeTabItems(BuildCreativeModeTabContentsEvent event) {
        if (!event.getTabKey().equals(CreativeModeTabs.COMBAT)) {
            return;
        }

        putAfter(event, Items.BOW, LONGBOW);
        putAfter(event, Items.CROSSBOW, CHAIN_CROSSBOW);
        putAfter(event, Items.SPECTRAL_ARROW, HEAVY_ARROW);
        putAfter(event, HEAVY_ARROW, EXPLOSIVE_ARROW);

        putBefore(event, Items.WOODEN_SWORD, WOODEN_BROADSWORD);
        addChain(event, WOODEN_BROADSWORD, GOLDEN_BROADSWORD, STONE_BROADSWORD, COPPER_BROADSWORD,
                IRON_BROADSWORD, DIAMOND_BROADSWORD, NETHERITE_BROADSWORD);

        putAfter(event, NETHERITE_BROADSWORD, WOODEN_SICKLE);
        addChain(event, WOODEN_SICKLE, GOLDEN_SICKLE, STONE_SICKLE, COPPER_SICKLE,
                IRON_SICKLE, DIAMOND_SICKLE, NETHERITE_SICKLE);

        putAfter(event, Items.NETHERITE_SWORD, WOODEN_SCYTHE);
        addChain(event, WOODEN_SCYTHE, GOLDEN_SCYTHE, STONE_SCYTHE, COPPER_SCYTHE,
                IRON_SCYTHE, DIAMOND_SCYTHE, NETHERITE_SCYTHE);

        putAfter(event, NETHERITE_SCYTHE, WOODEN_LONGSWORD);
        addChain(event, WOODEN_LONGSWORD, GOLDEN_LONGSWORD, STONE_LONGSWORD, COPPER_LONGSWORD,
                IRON_LONGSWORD, DIAMOND_LONGSWORD, NETHERITE_LONGSWORD);

        putAfter(event, NETHERITE_LONGSWORD, WOODEN_KATANA);
        addChain(event, WOODEN_KATANA, GOLDEN_KATANA, STONE_KATANA, COPPER_KATANA,
                IRON_KATANA, DIAMOND_KATANA, NETHERITE_KATANA);

        putBefore(event, Items.WOODEN_AXE, WOODEN_HATCHET);
        addChain(event, WOODEN_HATCHET, GOLDEN_HATCHET, STONE_HATCHET, COPPER_HATCHET,
                IRON_HATCHET, DIAMOND_HATCHET, NETHERITE_HATCHET);

        putAfter(event, Items.NETHERITE_AXE, WOODEN_HAMMER);
        addChain(event, WOODEN_HAMMER, GOLDEN_HAMMER, STONE_HAMMER, COPPER_HAMMER,
                IRON_HAMMER, DIAMOND_HAMMER, NETHERITE_HAMMER);

        putAfter(event, NETHERITE_HAMMER, WOODEN_BATTLEAXE);
        addChain(event, WOODEN_BATTLEAXE, GOLDEN_BATTLEAXE, STONE_BATTLEAXE, COPPER_BATTLEAXE,
                IRON_BATTLEAXE, DIAMOND_BATTLEAXE, NETHERITE_BATTLEAXE);

        putAfter(event, NETHERITE_KATANA, WOODEN_GREATSWORD);
        addChain(event, WOODEN_GREATSWORD, GOLDEN_GREATSWORD, STONE_GREATSWORD, COPPER_GREATSWORD,
                IRON_GREATSWORD, DIAMOND_GREATSWORD, NETHERITE_GREATSWORD);

        putAfter(event, NETHERITE_GREATSWORD, WOODEN_WARHAMMER);
        addChain(event, WOODEN_WARHAMMER, GOLDEN_WARHAMMER, STONE_WARHAMMER, COPPER_WARHAMMER,
                IRON_WARHAMMER, DIAMOND_WARHAMMER, NETHERITE_WARHAMMER);
    }

    @SafeVarargs
    private static void addChain(
            BuildCreativeModeTabContentsEvent event,
            Supplier<Item>... items
    ) {
        for (int index = 1; index < items.length; index++) {
            putAfter(
                    event,
                    items[index - 1],
                    items[index]
            );
        }
    }

    private static void putBefore(
            BuildCreativeModeTabContentsEvent event,
            Item anchor,
            Supplier<Item> item
    ) {
        event.insertBefore(
                anchor.getDefaultInstance(),
                item.get().getDefaultInstance(),
                TAB_VISIBILITY
        );
    }

    private static void putAfter(
            BuildCreativeModeTabContentsEvent event,
            Item anchor,
            Supplier<Item> item
    ) {
        event.insertAfter(
                anchor.getDefaultInstance(),
                item.get().getDefaultInstance(),
                TAB_VISIBILITY
        );
    }

    private static void putAfter(
            BuildCreativeModeTabContentsEvent event,
            Supplier<Item> anchor,
            Supplier<Item> item
    ) {
        event.insertAfter(
                anchor.get().getDefaultInstance(),
                item.get().getDefaultInstance(),
                TAB_VISIBILITY
        );
    }
}