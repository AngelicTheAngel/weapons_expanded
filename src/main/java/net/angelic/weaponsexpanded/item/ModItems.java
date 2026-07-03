package net.angelic.weaponsexpanded.item;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.custom.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

import static net.minecraft.world.item.Items.*;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, WeaponsExpanded.MODID);


    public static final RegistryObject<Item> WOODEN_BROADSWORD = registerItem("wooden_broadsword",
            setting -> new Item(setting.sword(ToolMaterial.WOOD, 2, -2.1f)));

    public static final RegistryObject<Item> GOLDEN_BROADSWORD = registerItem("golden_broadsword",
            setting -> new Item(setting.sword(ToolMaterial.GOLD, 2, -2.1f)));

    public static final RegistryObject<Item> STONE_BROADSWORD = registerItem("stone_broadsword",
            setting -> new Item(setting.sword(ToolMaterial.STONE, 2, -2.1F)));

    public static final RegistryObject<Item> COPPER_BROADSWORD = registerItem("copper_broadsword",
            setting -> new Item(setting.sword(ToolMaterial.COPPER, 2, -2.1F)));

    public static final RegistryObject<Item> IRON_BROADSWORD = registerItem("iron_broadsword",
            settings -> new Item(settings.sword(ToolMaterial.IRON, 2.0F, -2.2F)));

    public static final RegistryObject<Item> DIAMOND_BROADSWORD = registerItem("diamond_broadsword",
            settings -> new Item(settings.sword(ToolMaterial.DIAMOND, 2.0F, -2.2F)));

    public static final RegistryObject<Item> NETHERITE_BROADSWORD = registerItem("netherite_broadsword",
            settings -> new Item(settings.sword(ToolMaterial.NETHERITE, 2.0F, -2.2F).fireResistant()));

    public static final RegistryObject<Item> WOODEN_SICKLE = registerItem("wooden_sickle",
            settings -> new Item(settings.sword(ToolMaterial.WOOD, 2.5F, -2.3F)));

    public static final RegistryObject<Item> GOLDEN_SICKLE = registerItem("golden_sickle",
            settings -> new Item(settings.sword(ToolMaterial.GOLD, 2.5F, -2.3F)));

    public static final RegistryObject<Item> STONE_SICKLE = registerItem("stone_sickle",
            settings -> new Item(settings.sword(ToolMaterial.STONE, 2.5F, -2.3F)));

    public static final RegistryObject<Item> COPPER_SICKLE = registerItem("copper_sickle",
            settings -> new Item(settings.sword(ToolMaterial.COPPER, 2.5F, -2.3F)));

    public static final RegistryObject<Item> IRON_SICKLE = registerItem("iron_sickle",
            settings -> new Item(settings.sword(ToolMaterial.IRON, 2.5F, -2.3F)));

    public static final RegistryObject<Item> DIAMOND_SICKLE = registerItem("diamond_sickle",
            settings -> new Item(settings.sword(ToolMaterial.DIAMOND, 2.5F, -2.3F)));

    public static final RegistryObject<Item> NETHERITE_SICKLE = registerItem("netherite_sickle",
            settings -> new Item(settings.sword(ToolMaterial.NETHERITE, 2.5F, -2.3F).fireResistant()));

    public static final RegistryObject<Item> WOODEN_SCYTHE = registerItem("wooden_scythe",
            settings -> new Item(settings.sword(ToolMaterial.WOOD, 4.0F, -2.5F)));

    public static final RegistryObject<Item> GOLDEN_SCYTHE = registerItem("golden_scythe",
            settings -> new Item(settings.sword(ToolMaterial.GOLD, 4.0F, -2.5F)));

    public static final RegistryObject<Item> STONE_SCYTHE = registerItem("stone_scythe",
            settings -> new Item(settings.sword(ToolMaterial.STONE, 4.0F, -2.5F)));

    public static final RegistryObject<Item> COPPER_SCYTHE = registerItem("copper_scythe",
            settings -> new Item(settings.sword(ToolMaterial.COPPER, 4.0F, -2.5F)));

    public static final RegistryObject<Item> IRON_SCYTHE = registerItem("iron_scythe",
            settings -> new Item(settings.sword(ToolMaterial.IRON, 4.0F, -2.5F)));

    public static final RegistryObject<Item> DIAMOND_SCYTHE = registerItem("diamond_scythe",
            settings -> new Item(settings.sword(ToolMaterial.DIAMOND, 4.5F, -2.5F)));

    public static final RegistryObject<Item> NETHERITE_SCYTHE = registerItem("netherite_scythe",
            settings -> new Item(settings.sword(ToolMaterial.NETHERITE, 4.5F, -2.5F).fireResistant()));

    public static final RegistryObject<Item> WOODEN_LONGSWORD = registerItem("wooden_longsword",
            settings -> new BastardSwordItem(ToolMaterial.WOOD, 6.0F, -2.9F, 7.0F, -2.9F, settings));

    public static final RegistryObject<Item> GOLDEN_LONGSWORD = registerItem("golden_longsword",
            settings -> new BastardSwordItem(ToolMaterial.GOLD, 6.0F, -2.9F, 7.0F, -2.9F, settings));

    public static final RegistryObject<Item> STONE_LONGSWORD = registerItem("stone_longsword",
            settings -> new BastardSwordItem(ToolMaterial.STONE, 6.0F, -2.9F, 7.0F, -2.9F, settings));

    public static final RegistryObject<Item> COPPER_LONGSWORD = registerItem("copper_longsword",
            settings -> new BastardSwordItem(ToolMaterial.COPPER, 6.0F, -2.9F, 7.0F, -2.9F, settings));

    public static final RegistryObject<Item> IRON_LONGSWORD = registerItem("iron_longsword",
            settings -> new BastardSwordItem(ToolMaterial.IRON, 6.0F, -2.9F, 7.0F, -2.9F, settings));

    public static final RegistryObject<Item> DIAMOND_LONGSWORD = registerItem("diamond_longsword",
            settings -> new BastardSwordItem(ToolMaterial.DIAMOND, 6.0F, -2.9F, 7.0F, -2.9F, settings));

    public static final RegistryObject<Item> NETHERITE_LONGSWORD = registerItem("netherite_longsword",
            settings -> new BastardSwordItem(ToolMaterial.NETHERITE, 6.0F, -2.9F, 7.0F, -2.9F, settings.fireResistant()));

    public static final RegistryObject<Item> WOODEN_KATANA = registerItem("wooden_katana",
            settings -> new TwoHandedSwordItem(ToolMaterial.WOOD, 3.0F, -2.2F, settings));

    public static final RegistryObject<Item> GOLDEN_KATANA = registerItem("golden_katana",
            settings -> new TwoHandedSwordItem(ToolMaterial.GOLD, 3.0F, -2.2F, settings));

    public static final RegistryObject<Item> STONE_KATANA = registerItem("stone_katana",
            settings -> new TwoHandedSwordItem(ToolMaterial.STONE, 3.0F, -2.2F, settings));

    public static final RegistryObject<Item> COPPER_KATANA = registerItem("copper_katana",
            settings -> new TwoHandedSwordItem(ToolMaterial.COPPER, 3.0F, -2.2F, settings));

    public static final RegistryObject<Item> IRON_KATANA = registerItem("iron_katana",
            settings -> new TwoHandedSwordItem(ToolMaterial.IRON, 3.0F, -2.2F, settings));

    public static final RegistryObject<Item> DIAMOND_KATANA = registerItem("diamond_katana",
            settings -> new TwoHandedSwordItem(ToolMaterial.DIAMOND, 3.0F, -2.2F, settings));

    public static final RegistryObject<Item> NETHERITE_KATANA = registerItem("netherite_katana",
            settings -> new TwoHandedSwordItem(ToolMaterial.NETHERITE, 3.0F, -2.2F, settings.fireResistant()));

    public static final RegistryObject<Item> WOODEN_HATCHET = registerItem("wooden_hatchet",
            settings -> new AxeItem(ToolMaterial.WOOD, 5.0F, -3.0F, settings));

    public static final RegistryObject<Item> GOLDEN_HATCHET = registerItem("golden_hatchet",
            settings -> new AxeItem(ToolMaterial.GOLD, 5.0F, -2.8F, settings));

    public static final RegistryObject<Item> STONE_HATCHET = registerItem("stone_hatchet",
            settings -> new AxeItem(ToolMaterial.STONE, 6.0F, -3.0F, settings));

    public static final RegistryObject<Item> COPPER_HATCHET = registerItem("copper_hatchet",
            settings -> new AxeItem(ToolMaterial.COPPER, 6.0F, -3.0F, settings));

    public static final RegistryObject<Item> IRON_HATCHET = registerItem("iron_hatchet",
            settings -> new AxeItem(ToolMaterial.IRON, 5.0F, -2.9F, settings));

    public static final RegistryObject<Item> DIAMOND_HATCHET = registerItem("diamond_hatchet",
            settings -> new AxeItem(ToolMaterial.DIAMOND, 4.0F, -2.8F, settings));

    public static final RegistryObject<Item> NETHERITE_HATCHET = registerItem("netherite_hatchet",
            settings -> new AxeItem(ToolMaterial.NETHERITE, 4.0F, -2.8F, settings.fireResistant()));

    public static final RegistryObject<Item> WOODEN_HAMMER = registerItem("wooden_hammer",
            settings -> new BluntWeaponItem(ModToolMaterials.FAUX_WOOD, 7.0F, -3.3F, settings));

    public static final RegistryObject<Item> GOLDEN_HAMMER = registerItem("golden_hammer",
            settings -> new BluntWeaponItem(ModToolMaterials.FAUX_GOLD, 7.0F, -3.1F, settings));

    public static final RegistryObject<Item> STONE_HAMMER = registerItem("stone_hammer",
            settings -> new BluntWeaponItem(ModToolMaterials.FAUX_STONE, 8.0F, -3.3F, settings));

    public static final RegistryObject<Item> COPPER_HAMMER = registerItem("copper_hammer",
            settings -> new BluntWeaponItem(ModToolMaterials.FAUX_COPPER, 8.0F, -3.3F, settings));

    public static final RegistryObject<Item> IRON_HAMMER = registerItem("iron_hammer",
            settings -> new BluntWeaponItem(ModToolMaterials.FAUX_IRON, 7.0F, -3.2F, settings));

    public static final RegistryObject<Item> DIAMOND_HAMMER = registerItem("diamond_hammer",
            settings -> new BluntWeaponItem(ModToolMaterials.FAUX_DIAMOND, 6.0F, -3.1F, settings));

    public static final RegistryObject<Item> NETHERITE_HAMMER = registerItem("netherite_hammer",
            settings -> new BluntWeaponItem(ModToolMaterials.FAUX_NETHERITE, 6.0F, -3.1F, settings.fireResistant()));

    public static final RegistryObject<Item> WOODEN_BATTLEAXE = registerItem("wooden_battleaxe",
            settings -> new AxeItem(ToolMaterial.WOOD, 8.0F, -3.4F, settings));

    public static final RegistryObject<Item> GOLDEN_BATTLEAXE = registerItem("golden_battleaxe",
            settings -> new AxeItem(ToolMaterial.GOLD, 8.0F, -3.2F, settings));

    public static final RegistryObject<Item> STONE_BATTLEAXE = registerItem("stone_battleaxe",
            settings -> new AxeItem(ToolMaterial.STONE, 9.0F, -3.4F, settings));

    public static final RegistryObject<Item> COPPER_BATTLEAXE = registerItem("copper_battleaxe",
            settings -> new AxeItem(ToolMaterial.COPPER, 9.0F, -3.4F, settings));

    public static final RegistryObject<Item> IRON_BATTLEAXE = registerItem("iron_battleaxe",
            settings -> new AxeItem(ToolMaterial.IRON, 8.0F, -3.3F, settings));

    public static final RegistryObject<Item> DIAMOND_BATTLEAXE = registerItem("diamond_battleaxe",
            settings -> new AxeItem(ToolMaterial.DIAMOND, 7.0F, -3.2F, settings));

    public static final RegistryObject<Item> NETHERITE_BATTLEAXE = registerItem("netherite_battleaxe",
            settings -> new AxeItem(ToolMaterial.NETHERITE, 7.0F, -3.2F, settings.fireResistant()));

    public static final RegistryObject<Item> WOODEN_GREATSWORD = registerItem("wooden_greatsword",
            settings -> new TwoHandedSwordItem(ToolMaterial.WOOD, 8.0F, -3.3F, settings));

    public static final RegistryObject<Item> GOLDEN_GREATSWORD = registerItem("golden_greatsword",
            settings -> new TwoHandedSwordItem(ToolMaterial.GOLD, 8.0F, -3.1F, settings));

    public static final RegistryObject<Item> STONE_GREATSWORD = registerItem("stone_greatsword",
            settings -> new TwoHandedSwordItem(ToolMaterial.STONE, 8.0F, -3.3F, settings));

    public static final RegistryObject<Item> COPPER_GREATSWORD = registerItem("copper_greatsword",
            settings -> new TwoHandedSwordItem(ToolMaterial.COPPER, 8.0F, -3.3F, settings));

    public static final RegistryObject<Item> IRON_GREATSWORD = registerItem("iron_greatsword",
            settings -> new TwoHandedSwordItem(ToolMaterial.IRON, 8.0F, -3.2F, settings));

    public static final RegistryObject<Item> DIAMOND_GREATSWORD = registerItem("diamond_greatsword",
            settings -> new TwoHandedSwordItem(ToolMaterial.DIAMOND, 8.0F, -3.1F, settings));

    public static final RegistryObject<Item> NETHERITE_GREATSWORD = registerItem("netherite_greatsword",
            settings -> new TwoHandedSwordItem(ToolMaterial.NETHERITE, 8.0F, -3.1F, settings.fireResistant()));

    public static final RegistryObject<Item> WOODEN_WARHAMMER = registerItem("wooden_warhammer",
            settings -> new WarhammerItem(ToolMaterial.WOOD, 5.0F, -3.1F, 5.0F, -2.7F, "wooden_warhammer", settings));

    public static final RegistryObject<Item> GOLDEN_WARHAMMER = registerItem("golden_warhammer",
            settings -> new WarhammerItem(ToolMaterial.GOLD, 5.0F, -2.9F, 5.0F, -2.7F, "golden_warhammer", settings));

    public static final RegistryObject<Item> STONE_WARHAMMER = registerItem("stone_warhammer",
            settings -> new WarhammerItem(ToolMaterial.STONE, 6.0F, -3.1F, 5.0F, -2.7F, "stone_warhammer", settings));

    public static final RegistryObject<Item> COPPER_WARHAMMER = registerItem("copper_warhammer",
            settings -> new WarhammerItem(ToolMaterial.COPPER, 6.0F, -3.1F, 5.0F, -2.7F, "copper_warhammer",  settings));

    public static final RegistryObject<Item> IRON_WARHAMMER = registerItem("iron_warhammer",
            settings -> new WarhammerItem(ToolMaterial.IRON, 5.0F, -3.0F, 5.0F, -2.7F, "iron_warhammer", settings));

    public static final RegistryObject<Item> DIAMOND_WARHAMMER = registerItem("diamond_warhammer",
            settings -> new WarhammerItem(ToolMaterial.DIAMOND, 4.0F, -2.9F, 5.0F, -2.7F, "diamond_warhammer", settings));

    public static final RegistryObject<Item> NETHERITE_WARHAMMER = registerItem("netherite_warhammer",
            settings -> new WarhammerItem(ToolMaterial.NETHERITE, 4.0F, -2.9F, 5.0F, -2.7F, "netherite_warhammer", settings.fireResistant()));

    public static final RegistryObject<Item> HEAVY_ARROW = registerItem("heavy_arrow",
            settings -> new HeavyArrowItem(settings.stacksTo(64)));

    public static final RegistryObject<Item> EXPLOSIVE_ARROW = registerItem("explosive_arrow",
            settings -> new ExplosiveArrowItem(settings.stacksTo(64)));

    public static final RegistryObject<Item> LONGBOW = registerItem("longbow",
            settings -> new LongbowItem(settings.durability(384).enchantable(1)));

    public static final RegistryObject<Item> CHAIN_CROSSBOW = registerItem("chain_crossbow",
            settings -> new ChainCrossbowItem(settings.durability(465).enchantable(1)));


    private static <T extends Item> RegistryObject<T> registerItem(String name, Function<Item.Properties, T> function) {
        Identifier id = Identifier.fromNamespaceAndPath(WeaponsExpanded.MODID, name);
        ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id);

        return ITEMS.register(name, () -> function.apply(new Item.Properties().setId(key)));
    }

    public static void register(BusGroup modBusGroup) {
        ITEMS.register(modBusGroup);
        BuildCreativeModeTabContentsEvent.BUS.addListener(ModItems::buildCreativeTabContents);
    }

    private static void insertAfter(BuildCreativeModeTabContentsEvent event, Object anchor, RegistryObject<? extends Item> item) {
        event.getEntries().putAfter(
                asStack(anchor),
                item.get().getDefaultInstance(),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    private static void insertBefore(BuildCreativeModeTabContentsEvent event, Object anchor, RegistryObject<? extends Item> item) {
        event.getEntries().putBefore(
                asStack(anchor),
                item.get().getDefaultInstance(),
                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
        );
    }

    private static ItemStack asStack(Object value) {
        if (value instanceof RegistryObject<?> registryObject && registryObject.get() instanceof Item item) {
            return item.getDefaultInstance();
        }

        if (value instanceof ItemLike itemLike) {
            return itemLike.asItem().getDefaultInstance();
        }

        throw new IllegalArgumentException("Unsupported creative tab anchor: " + value);
    }

    private static void buildCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() != CreativeModeTabs.COMBAT) {
            return;
        }
        // Ranged placement relative to vanilla items
        insertAfter(event, Items.BOW, LONGBOW);
        insertAfter(event, Items.CROSSBOW, CHAIN_CROSSBOW);
        insertAfter(event, Items.SPECTRAL_ARROW, HEAVY_ARROW);
        insertAfter(event, HEAVY_ARROW, EXPLOSIVE_ARROW);

        Object anchor = Items.WOODEN_SWORD;

        // Broadswords
        insertBefore(event, anchor, WOODEN_BROADSWORD);
        insertAfter(event, WOODEN_BROADSWORD, GOLDEN_BROADSWORD);
        insertAfter(event, GOLDEN_BROADSWORD, STONE_BROADSWORD);
        insertAfter(event, STONE_BROADSWORD, COPPER_BROADSWORD);
        insertAfter(event, COPPER_BROADSWORD, IRON_BROADSWORD);
        insertAfter(event, IRON_BROADSWORD, DIAMOND_BROADSWORD);
        insertAfter(event, DIAMOND_BROADSWORD, NETHERITE_BROADSWORD);
        anchor = NETHERITE_BROADSWORD;

        // Sickles
        insertAfter(event, anchor, WOODEN_SICKLE);
        insertAfter(event, WOODEN_SICKLE, GOLDEN_SICKLE);
        insertAfter(event, GOLDEN_SICKLE, STONE_SICKLE);
        insertAfter(event, STONE_SICKLE, COPPER_SICKLE);
        insertAfter(event, COPPER_SICKLE, IRON_SICKLE);
        insertAfter(event, IRON_SICKLE, DIAMOND_SICKLE);
        insertAfter(event, DIAMOND_SICKLE, NETHERITE_SICKLE);
        anchor = NETHERITE_SWORD;

        //
        insertAfter(event, anchor, WOODEN_SCYTHE);
        insertAfter(event, WOODEN_SCYTHE, GOLDEN_SCYTHE);
        insertAfter(event, GOLDEN_SCYTHE, STONE_SCYTHE);
        insertAfter(event, STONE_SCYTHE, COPPER_SCYTHE);
        insertAfter(event, COPPER_SCYTHE, IRON_SCYTHE);
        insertAfter(event, IRON_SCYTHE, DIAMOND_SCYTHE);
        insertAfter(event, DIAMOND_SCYTHE, NETHERITE_SCYTHE);
        anchor = NETHERITE_SCYTHE;

        // Longswords
        insertAfter(event, anchor, WOODEN_LONGSWORD);
        insertAfter(event, WOODEN_LONGSWORD, GOLDEN_LONGSWORD);
        insertAfter(event, GOLDEN_LONGSWORD, STONE_LONGSWORD);
        insertAfter(event, STONE_LONGSWORD, COPPER_LONGSWORD);
        insertAfter(event, COPPER_LONGSWORD, IRON_LONGSWORD);
        insertAfter(event, IRON_LONGSWORD, DIAMOND_LONGSWORD);
        insertAfter(event, DIAMOND_LONGSWORD, NETHERITE_LONGSWORD);
        anchor = NETHERITE_LONGSWORD;

        // Katanas
        insertAfter(event, anchor, WOODEN_KATANA);
        insertAfter(event, WOODEN_KATANA, GOLDEN_KATANA);
        insertAfter(event, GOLDEN_KATANA, STONE_KATANA);
        insertAfter(event, STONE_KATANA, COPPER_KATANA);
        insertAfter(event, COPPER_KATANA, IRON_KATANA);
        insertAfter(event, IRON_KATANA, DIAMOND_KATANA);
        insertAfter(event, DIAMOND_KATANA, NETHERITE_KATANA);
        anchor = WOODEN_AXE;

        // Hatchets
        insertBefore(event, anchor, WOODEN_HATCHET);
        insertAfter(event, WOODEN_HATCHET, GOLDEN_HATCHET);
        insertAfter(event, GOLDEN_HATCHET, STONE_HATCHET);
        insertAfter(event, STONE_HATCHET, COPPER_HATCHET);
        insertAfter(event, COPPER_HATCHET, IRON_HATCHET);
        insertAfter(event, IRON_HATCHET, DIAMOND_HATCHET);
        insertAfter(event, DIAMOND_HATCHET, NETHERITE_HATCHET);
        anchor = NETHERITE_AXE;

        // Hammers
        insertAfter(event, anchor, WOODEN_HAMMER);
        insertAfter(event, WOODEN_HAMMER, GOLDEN_HAMMER);
        insertAfter(event, GOLDEN_HAMMER, STONE_HAMMER);
        insertAfter(event, STONE_HAMMER, COPPER_HAMMER);
        insertAfter(event, COPPER_HAMMER, IRON_HAMMER);
        insertAfter(event, IRON_HAMMER, DIAMOND_HAMMER);
        insertAfter(event, DIAMOND_HAMMER, NETHERITE_HAMMER);
        anchor = NETHERITE_HAMMER;

        // Battleaxes
        insertAfter(event, anchor, WOODEN_BATTLEAXE);
        insertAfter(event, WOODEN_BATTLEAXE, GOLDEN_BATTLEAXE);
        insertAfter(event, GOLDEN_BATTLEAXE, STONE_BATTLEAXE);
        insertAfter(event, STONE_BATTLEAXE, COPPER_BATTLEAXE);
        insertAfter(event, COPPER_BATTLEAXE, IRON_BATTLEAXE);
        insertAfter(event, IRON_BATTLEAXE, DIAMOND_BATTLEAXE);
        insertAfter(event, DIAMOND_BATTLEAXE, NETHERITE_BATTLEAXE);
        anchor = NETHERITE_KATANA;

        // Greatswords
        insertAfter(event, anchor, WOODEN_GREATSWORD);
        insertAfter(event, WOODEN_GREATSWORD, GOLDEN_GREATSWORD);
        insertAfter(event, GOLDEN_GREATSWORD, STONE_GREATSWORD);
        insertAfter(event, STONE_GREATSWORD, COPPER_GREATSWORD);
        insertAfter(event, COPPER_GREATSWORD, IRON_GREATSWORD);
        insertAfter(event, IRON_GREATSWORD, DIAMOND_GREATSWORD);
        insertAfter(event, DIAMOND_GREATSWORD, NETHERITE_GREATSWORD);
        anchor = NETHERITE_GREATSWORD;

        // Warhammer
        insertAfter(event, anchor, WOODEN_WARHAMMER);
        insertAfter(event, WOODEN_WARHAMMER, GOLDEN_WARHAMMER);
        insertAfter(event, GOLDEN_WARHAMMER, STONE_WARHAMMER);
        insertAfter(event, STONE_WARHAMMER, COPPER_WARHAMMER);
        insertAfter(event, COPPER_WARHAMMER, IRON_WARHAMMER);
        insertAfter(event, IRON_WARHAMMER, DIAMOND_WARHAMMER);
        insertAfter(event, DIAMOND_WARHAMMER, NETHERITE_WARHAMMER);
    }
}
