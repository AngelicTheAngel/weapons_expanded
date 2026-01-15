package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.angelic.weaponsexpanded.enchantment.ModEnchantmentEffects;
import net.angelic.weaponsexpanded.entity.ModEntities;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.network.FireChainCrossbowPayload;
import net.angelic.weaponsexpanded.network.ModPackets;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.angelic.weaponsexpanded.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import java.util.Optional;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeaponsExpanded implements ModInitializer {
    public static final String MOD_ID = "weaponsexpanded";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Everything in this tag will be usable as furnace fuel.
    public static final TagKey<Item> WOODEN_FUEL = TagKey.of(
            RegistryKeys.ITEM,
            Identifier.of(MOD_ID, "wooden_fuel")
    );

    // Global version variable fetched from fabric.mod.json
    public static final String VERSION = FabricLoader.getInstance()
            .getModContainer(MOD_ID)
            .map(container -> container.getMetadata().getVersion().getFriendlyString())
            .orElse("Unknown");

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing {} version {}", MOD_ID, VERSION);

        WeaponsExpandedConfig.get();

        ModItems.registerModItems();

        // 200 ticks = same as most vanilla wooden tools.
        FuelRegistryEvents.BUILD.register((builder, context) -> builder.add(WOODEN_FUEL, 200));

        ModEnchantmentEffects.registerEnchantmentEffects();
        ModEffects.registerEffects();
        ModEntities.registerEntities();
        ModSounds.register();

        // Register loot modifications (these will be gated by config inside the callback)
        ModLootTableModifiers.modifyLootTables();

        // Register payload types once (safe if called again elsewhere)
        ModPackets.register();

        ServerPlayNetworking.registerGlobalReceiver(FireChainCrossbowPayload.ID, (payload, context) ->
                context.server().execute(() -> weaponsexpanded$tryFireChainCrossbow(context.player()))
        );

        if (WeaponsExpandedConfig.get().enableWeaponsmithTrades) {
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 4, factories -> {
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextBetween(5, 19);
                    ItemStack hammer = new ItemStack(ModItems.DIAMOND_HAMMER, 1);
                    EnchantmentHelper.enchant(
                            random,
                            hammer,
                            enchantLevel,
                            world.getRegistryManager(),
                            Optional.empty()
                    );
                    int cost = 17 + enchantLevel;
                    return new TradeOffer(
                            new TradedItem(Items.EMERALD, cost),
                            hammer,
                            3, 15, 0.2F
                    );
                });
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextBetween(5, 19);
                    ItemStack hatchet = new ItemStack(ModItems.DIAMOND_HATCHET, 1);
                    EnchantmentHelper.enchant(
                            random,
                            hatchet,
                            enchantLevel,
                            world.getRegistryManager(),
                            Optional.empty()
                    );
                    int cost = 17 + enchantLevel;
                    return new TradeOffer(
                            new TradedItem(Items.EMERALD, cost),
                            hatchet,
                            3, 15, 0.2F
                    );
                });
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextBetween(5, 19);
                    ItemStack battleaxe = new ItemStack(ModItems.DIAMOND_BATTLEAXE, 1);
                    EnchantmentHelper.enchant(
                            random,
                            battleaxe,
                            enchantLevel,
                            world.getRegistryManager(),
                            Optional.empty()
                    );
                    int cost = 17 + enchantLevel;
                    return new TradeOffer(
                            new TradedItem(Items.EMERALD, cost),
                            battleaxe,
                            3, 15, 0.2F
                    );
                });
            });
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 5, factories -> {
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextBetween(5, 19);
                    ItemStack weapon = new ItemStack(ModItems.DIAMOND_BROADSWORD, 1);
                    EnchantmentHelper.enchant(
                            random,
                            weapon,
                            enchantLevel,
                            world.getRegistryManager(),
                            Optional.empty()
                    );
                    int cost = 13 + enchantLevel;
                    return new TradeOffer(
                            new TradedItem(Items.EMERALD, cost),
                            weapon,
                            3, 30, 0.2F
                    );
                });
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextBetween(5, 19);
                    ItemStack weapon = new ItemStack(ModItems.DIAMOND_SICKLE, 1);
                    EnchantmentHelper.enchant(
                            random,
                            weapon,
                            enchantLevel,
                            world.getRegistryManager(),
                            Optional.empty()
                    );
                    int cost = 13 + enchantLevel;
                    return new TradeOffer(
                            new TradedItem(Items.EMERALD, cost),
                            weapon,
                            3, 30, 0.2F
                    );
                });
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextBetween(5, 19);
                    ItemStack weapon = new ItemStack(ModItems.DIAMOND_SCYTHE, 1);
                    EnchantmentHelper.enchant(
                            random,
                            weapon,
                            enchantLevel,
                            world.getRegistryManager(),
                            Optional.empty()
                    );
                    int cost = 13 + enchantLevel;
                    return new TradeOffer(
                            new TradedItem(Items.EMERALD, cost),
                            weapon,
                            3, 30, 0.2F
                    );
                });
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextBetween(5, 19);
                    ItemStack weapon = new ItemStack(ModItems.DIAMOND_LONGSWORD, 1);
                    EnchantmentHelper.enchant(
                            random,
                            weapon,
                            enchantLevel,
                            world.getRegistryManager(),
                            Optional.empty()
                    );
                    int cost = 13 + enchantLevel;
                    return new TradeOffer(
                            new TradedItem(Items.EMERALD, cost),
                            weapon,
                            3, 30, 0.2F
                    );
                });
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextBetween(5, 19);
                    ItemStack weapon = new ItemStack(ModItems.DIAMOND_KATANA, 1);
                    EnchantmentHelper.enchant(
                            random,
                            weapon,
                            enchantLevel,
                            world.getRegistryManager(),
                            Optional.empty()
                    );
                    int cost = 13 + enchantLevel;
                    return new TradeOffer(
                            new TradedItem(Items.EMERALD, cost),
                            weapon,
                            3, 30, 0.2F
                    );
                });
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextBetween(5, 19);
                    ItemStack weapon = new ItemStack(ModItems.DIAMOND_GREATSWORD, 1);
                    EnchantmentHelper.enchant(
                            random,
                            weapon,
                            enchantLevel,
                            world.getRegistryManager(),
                            Optional.empty()
                    );
                    int cost = 13 + enchantLevel;
                    return new TradeOffer(
                            new TradedItem(Items.EMERALD, cost),
                            weapon,
                            3, 30, 0.2F
                    );
                });
            });
        }
    }

    private static void weaponsexpanded$tryFireChainCrossbow(PlayerEntity player) {
        ItemStack stack = player.getMainHandStack();
        if (!(stack.getItem() instanceof ChainCrossbowItem chainCrossbow)) return;
        if (!net.minecraft.item.CrossbowItem.isCharged(stack)) return;

        ChargedProjectilesComponent charged =
                stack.getOrDefault(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);

        float speed = charged.contains(net.minecraft.item.Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;

        chainCrossbow.shootAll(player.getEntityWorld(), player, Hand.MAIN_HAND, stack, speed, 1.0F, null);

        if (player instanceof ServerPlayerEntity serverPlayer) {
            ItemStack dummy = new ItemStack(Items.CROSSBOW);
            Criteria.SHOT_CROSSBOW.trigger(serverPlayer, dummy);
        }
    }
}