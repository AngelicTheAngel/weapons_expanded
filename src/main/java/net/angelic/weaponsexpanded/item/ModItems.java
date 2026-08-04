package net.angelic.weaponsexpanded.item;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.custom.*;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public final class ModItems {
    private static final CreativeModeTab.TabVisibility TAB_VISIBILITY =
            CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS;

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WeaponsExpanded.MOD_ID);

    public static final RegistryObject<Item> WOODEN_BROADSWORD = registerItem("wooden_broadsword",
            () -> new SwordItem(Tiers.WOOD, 2, -2.1F, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_BROADSWORD = registerItem("golden_broadsword",
            () -> new SwordItem(Tiers.GOLD, 2, -2.1F, new Item.Properties()));
    public static final RegistryObject<Item> STONE_BROADSWORD = registerItem("stone_broadsword",
            () -> new SwordItem(Tiers.STONE, 2, -2.1F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_BROADSWORD = registerItem("iron_broadsword",
            () -> new SwordItem(Tiers.IRON, 2, -2.2F, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_BROADSWORD = registerItem("diamond_broadsword",
            () -> new SwordItem(Tiers.DIAMOND, 2, -2.2F, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_BROADSWORD = registerItem("netherite_broadsword",
            () -> new SwordItem(Tiers.NETHERITE, 2, -2.2F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> WOODEN_SICKLE = registerItem("wooden_sickle",
            () -> new SickleItem(Tiers.WOOD, 2.5F, -2.3F, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_SICKLE = registerItem("golden_sickle",
            () -> new SickleItem(Tiers.GOLD, 2.5F, -2.3F, new Item.Properties()));
    public static final RegistryObject<Item> STONE_SICKLE = registerItem("stone_sickle",
            () -> new SickleItem(Tiers.STONE, 2.5F, -2.3F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_SICKLE = registerItem("iron_sickle",
            () -> new SickleItem(Tiers.IRON, 2.5F, -2.3F, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_SICKLE = registerItem("diamond_sickle",
            () -> new SickleItem(Tiers.DIAMOND, 2.5F, -2.3F, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_SICKLE = registerItem("netherite_sickle",
            () -> new SickleItem(Tiers.NETHERITE, 2.5F, -2.3F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> WOODEN_SCYTHE = registerItem("wooden_scythe",
            () -> new SwordItem(Tiers.WOOD, 4, -2.5F, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_SCYTHE = registerItem("golden_scythe",
            () -> new SwordItem(Tiers.GOLD, 4, -2.5F, new Item.Properties()));
    public static final RegistryObject<Item> STONE_SCYTHE = registerItem("stone_scythe",
            () -> new SwordItem(Tiers.STONE, 4, -2.5F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_SCYTHE = registerItem("iron_scythe",
            () -> new SwordItem(Tiers.IRON, 4, -2.5F, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_SCYTHE = registerItem("diamond_scythe",
            () -> new SwordItem(Tiers.DIAMOND, 4, -2.5F, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_SCYTHE = registerItem("netherite_scythe",
            () -> new SwordItem(Tiers.NETHERITE, 4, -2.5F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> WOODEN_LONGSWORD = registerItem("wooden_longsword",
            () -> new BastardSwordItem(Tiers.WOOD, 6, -2.9F, 7, -2.9F, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_LONGSWORD = registerItem("golden_longsword",
            () -> new BastardSwordItem(Tiers.GOLD, 6, -2.9F, 7, -2.9F, new Item.Properties()));
    public static final RegistryObject<Item> STONE_LONGSWORD = registerItem("stone_longsword",
            () -> new BastardSwordItem(Tiers.STONE, 6, -2.9F, 7, -2.9F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_LONGSWORD = registerItem("iron_longsword",
            () -> new BastardSwordItem(Tiers.IRON, 6, -2.9F, 7, -2.9F, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_LONGSWORD = registerItem("diamond_longsword",
            () -> new BastardSwordItem(Tiers.DIAMOND, 6, -2.9F, 7, -2.9F, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_LONGSWORD = registerItem("netherite_longsword",
            () -> new BastardSwordItem(Tiers.NETHERITE, 6, -2.9F, 7, -2.9F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> WOODEN_KATANA = registerItem("wooden_katana",
            () -> new TwoHandedSwordItem(Tiers.WOOD, 3, -2.2F, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_KATANA = registerItem("golden_katana",
            () -> new TwoHandedSwordItem(Tiers.GOLD, 3, -2.2F, new Item.Properties()));
    public static final RegistryObject<Item> STONE_KATANA = registerItem("stone_katana",
            () -> new TwoHandedSwordItem(Tiers.STONE, 3, -2.2F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_KATANA = registerItem("iron_katana",
            () -> new TwoHandedSwordItem(Tiers.IRON, 3, -2.2F, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_KATANA = registerItem("diamond_katana",
            () -> new TwoHandedSwordItem(Tiers.DIAMOND, 3, -2.2F, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_KATANA = registerItem("netherite_katana",
            () -> new TwoHandedSwordItem(Tiers.NETHERITE, 3, -2.2F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> WOODEN_HATCHET = registerItem("wooden_hatchet",
            () -> new AxeItem(Tiers.WOOD, 5, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_HATCHET = registerItem("golden_hatchet",
            () -> new AxeItem(Tiers.GOLD, 5, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> STONE_HATCHET = registerItem("stone_hatchet",
            () -> new AxeItem(Tiers.STONE, 6, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_HATCHET = registerItem("iron_hatchet",
            () -> new AxeItem(Tiers.IRON, 5, -2.9F, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_HATCHET = registerItem("diamond_hatchet",
            () -> new AxeItem(Tiers.DIAMOND, 4, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_HATCHET = registerItem("netherite_hatchet",
            () -> new AxeItem(Tiers.NETHERITE, 4, -2.8F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> WOODEN_HAMMER = registerItem("wooden_hammer",
            () -> new BluntWeaponItem(ModToolMaterials.FAUX_WOOD, 7, -3.3F, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_HAMMER = registerItem("golden_hammer",
            () -> new BluntWeaponItem(ModToolMaterials.FAUX_GOLD, 7, -3.1F, new Item.Properties()));
    public static final RegistryObject<Item> STONE_HAMMER = registerItem("stone_hammer",
            () -> new BluntWeaponItem(ModToolMaterials.FAUX_STONE, 8, -3.3F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_HAMMER = registerItem("iron_hammer",
            () -> new BluntWeaponItem(ModToolMaterials.FAUX_IRON, 7, -3.2F, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_HAMMER = registerItem("diamond_hammer",
            () -> new BluntWeaponItem(ModToolMaterials.FAUX_DIAMOND, 6, -3.1F, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_HAMMER = registerItem("netherite_hammer",
            () -> new BluntWeaponItem(ModToolMaterials.FAUX_NETHERITE, 6, -3.1F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> WOODEN_BATTLEAXE = registerItem("wooden_battleaxe",
            () -> new AxeItem(Tiers.WOOD, 8, -3.4F, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_BATTLEAXE = registerItem("golden_battleaxe",
            () -> new AxeItem(Tiers.GOLD, 8, -3.2F, new Item.Properties()));
    public static final RegistryObject<Item> STONE_BATTLEAXE = registerItem("stone_battleaxe",
            () -> new AxeItem(Tiers.STONE, 9, -3.4F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_BATTLEAXE = registerItem("iron_battleaxe",
            () -> new AxeItem(Tiers.IRON, 8, -3.3F, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_BATTLEAXE = registerItem("diamond_battleaxe",
            () -> new AxeItem(Tiers.DIAMOND, 7, -3.2F, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_BATTLEAXE = registerItem("netherite_battleaxe",
            () -> new AxeItem(Tiers.NETHERITE, 7, -3.2F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> WOODEN_GREATSWORD = registerItem("wooden_greatsword",
            () -> new TwoHandedSwordItem(Tiers.WOOD, 8, -3.3F, new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_GREATSWORD = registerItem("golden_greatsword",
            () -> new TwoHandedSwordItem(Tiers.GOLD, 8, -3.1F, new Item.Properties()));
    public static final RegistryObject<Item> STONE_GREATSWORD = registerItem("stone_greatsword",
            () -> new TwoHandedSwordItem(Tiers.STONE, 8, -3.3F, new Item.Properties()));
    public static final RegistryObject<Item> IRON_GREATSWORD = registerItem("iron_greatsword",
            () -> new TwoHandedSwordItem(Tiers.IRON, 8, -3.2F, new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_GREATSWORD = registerItem("diamond_greatsword",
            () -> new TwoHandedSwordItem(Tiers.DIAMOND, 8, -3.1F, new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_GREATSWORD = registerItem("netherite_greatsword",
            () -> new TwoHandedSwordItem(Tiers.NETHERITE, 8, -3.1F, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> WOODEN_WARHAMMER = registerItem("wooden_warhammer",
            () -> new WarhammerItem(Tiers.WOOD, 5, -3.1F, 5, -2.7F, "wooden_warhammer", new Item.Properties()));
    public static final RegistryObject<Item> GOLDEN_WARHAMMER = registerItem("golden_warhammer",
            () -> new WarhammerItem(Tiers.GOLD, 5, -2.9F, 5, -2.7F, "golden_warhammer", new Item.Properties()));
    public static final RegistryObject<Item> STONE_WARHAMMER = registerItem("stone_warhammer",
            () -> new WarhammerItem(Tiers.STONE, 6, -3.1F, 5, -2.7F, "stone_warhammer", new Item.Properties()));
    public static final RegistryObject<Item> IRON_WARHAMMER = registerItem("iron_warhammer",
            () -> new WarhammerItem(Tiers.IRON, 5, -3.0F, 5, -2.7F, "iron_warhammer", new Item.Properties()));
    public static final RegistryObject<Item> DIAMOND_WARHAMMER = registerItem("diamond_warhammer",
            () -> new WarhammerItem(Tiers.DIAMOND, 4, -2.9F, 5, -2.7F, "diamond_warhammer", new Item.Properties()));
    public static final RegistryObject<Item> NETHERITE_WARHAMMER = registerItem("netherite_warhammer",
            () -> new WarhammerItem(Tiers.NETHERITE, 4, -2.9F, 5, -2.7F, "netherite_warhammer", new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> HEAVY_ARROW = registerItem("heavy_arrow",
            () -> new HeavyArrowItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> EXPLOSIVE_ARROW = registerItem("explosive_arrow",
            () -> new ExplosiveArrowItem(new Item.Properties().stacksTo(64)));
    public static final RegistryObject<Item> LONGBOW = registerItem("longbow",
            () -> new LongbowItem(new Item.Properties().durability(384)));
    public static final RegistryObject<Item> CHAIN_CROSSBOW = registerItem("chain_crossbow",
            () -> new ChainCrossbowItem(new Item.Properties().durability(465)));

    private ModItems() {
    }

    private static RegistryObject<Item> registerItem(String name, Supplier<Item> supplier) {
        return ITEMS.register(name, supplier);
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
        addChain(event, WOODEN_BROADSWORD, GOLDEN_BROADSWORD, STONE_BROADSWORD,
                IRON_BROADSWORD, DIAMOND_BROADSWORD, NETHERITE_BROADSWORD);

        putAfter(event, NETHERITE_BROADSWORD, WOODEN_SICKLE);
        addChain(event, WOODEN_SICKLE, GOLDEN_SICKLE, STONE_SICKLE,
                IRON_SICKLE, DIAMOND_SICKLE, NETHERITE_SICKLE);

        putAfter(event, Items.NETHERITE_SWORD, WOODEN_SCYTHE);
        addChain(event, WOODEN_SCYTHE, GOLDEN_SCYTHE, STONE_SCYTHE,
                IRON_SCYTHE, DIAMOND_SCYTHE, NETHERITE_SCYTHE);

        putAfter(event, NETHERITE_SCYTHE, WOODEN_LONGSWORD);
        addChain(event, WOODEN_LONGSWORD, GOLDEN_LONGSWORD, STONE_LONGSWORD,
                IRON_LONGSWORD, DIAMOND_LONGSWORD, NETHERITE_LONGSWORD);

        putAfter(event, NETHERITE_LONGSWORD, WOODEN_KATANA);
        addChain(event, WOODEN_KATANA, GOLDEN_KATANA, STONE_KATANA,
                IRON_KATANA, DIAMOND_KATANA, NETHERITE_KATANA);

        putBefore(event, Items.WOODEN_AXE, WOODEN_HATCHET);
        addChain(event, WOODEN_HATCHET, GOLDEN_HATCHET, STONE_HATCHET,
                IRON_HATCHET, DIAMOND_HATCHET, NETHERITE_HATCHET);

        putAfter(event, Items.NETHERITE_AXE, WOODEN_HAMMER);
        addChain(event, WOODEN_HAMMER, GOLDEN_HAMMER, STONE_HAMMER,
                IRON_HAMMER, DIAMOND_HAMMER, NETHERITE_HAMMER);

        putAfter(event, NETHERITE_HAMMER, WOODEN_BATTLEAXE);
        addChain(event, WOODEN_BATTLEAXE, GOLDEN_BATTLEAXE, STONE_BATTLEAXE,
                IRON_BATTLEAXE, DIAMOND_BATTLEAXE, NETHERITE_BATTLEAXE);

        putAfter(event, NETHERITE_KATANA, WOODEN_GREATSWORD);
        addChain(event, WOODEN_GREATSWORD, GOLDEN_GREATSWORD, STONE_GREATSWORD,
                IRON_GREATSWORD, DIAMOND_GREATSWORD, NETHERITE_GREATSWORD);

        putAfter(event, NETHERITE_GREATSWORD, WOODEN_WARHAMMER);
        addChain(event, WOODEN_WARHAMMER, GOLDEN_WARHAMMER, STONE_WARHAMMER,
                IRON_WARHAMMER, DIAMOND_WARHAMMER, NETHERITE_WARHAMMER);
    }

    @SafeVarargs
    private static void addChain(BuildCreativeModeTabContentsEvent event,
                                 RegistryObject<Item>... items) {
        for (int index = 1; index < items.length; index++) {
            putAfter(event, items[index - 1], items[index]);
        }
    }

    private static void putBefore(BuildCreativeModeTabContentsEvent event,
                                  Item anchor, RegistryObject<Item> item) {
        event.getEntries().putBefore(anchor.getDefaultInstance(), item.get().getDefaultInstance(), TAB_VISIBILITY);
    }

    private static void putAfter(BuildCreativeModeTabContentsEvent event,
                                 Item anchor, RegistryObject<Item> item) {
        event.getEntries().putAfter(anchor.getDefaultInstance(), item.get().getDefaultInstance(), TAB_VISIBILITY);
    }

    private static void putAfter(BuildCreativeModeTabContentsEvent event,
                                 RegistryObject<Item> anchor, RegistryObject<Item> item) {
        putAfter(event, anchor.get(), item);
    }
}