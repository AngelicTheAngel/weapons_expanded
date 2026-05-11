package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.angelic.weaponsexpanded.enchantment.ModEnchantmentEffects;
import net.angelic.weaponsexpanded.entity.ModEntities;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.network.FireChainCrossbowPayload;
import net.angelic.weaponsexpanded.network.ModPackets;
import net.angelic.weaponsexpanded.network.ToggleBastardSwordModePayload;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.angelic.weaponsexpanded.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FuelRegistryEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeaponsExpanded implements ModInitializer {
    public static final String MOD_ID = "weaponsexpanded";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    // Everything in this tag will be usable as furnace fuel.
    public static final TagKey<Item> WOODEN_FUEL = TagKey.create(
            Registries.ITEM,
            Identifier.fromNamespaceAndPath(MOD_ID, "wooden_fuel")
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

        ServerPlayNetworking.registerGlobalReceiver(ToggleBastardSwordModePayload.ID, (payload, context) ->
                context.server().execute(() -> weaponsexpanded$toggleBastardSwordMode(context.player()))
        );

        if (WeaponsExpandedConfig.get().enableWeaponsmithTrades) {
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 4, factories -> {
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextIntBetweenInclusive(5, 19);
                    ItemStack hammer = new ItemStack(ModItems.DIAMOND_HAMMER, 1);
                    EnchantmentHelper.enchantItem(
                            random,
                            hammer,
                            enchantLevel,
                            world.registryAccess(),
                            Optional.empty()
                    );
                    int cost = 17 + enchantLevel;
                    return new MerchantOffer(
                            new ItemCost(Items.EMERALD, cost),
                            hammer,
                            3, 15, 0.2F
                    );
                });
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextIntBetweenInclusive(5, 19);
                    ItemStack hatchet = new ItemStack(ModItems.DIAMOND_HATCHET, 1);
                    EnchantmentHelper.enchantItem(
                            random,
                            hatchet,
                            enchantLevel,
                            world.registryAccess(),
                            Optional.empty()
                    );
                    int cost = 17 + enchantLevel;
                    return new MerchantOffer(
                            new ItemCost(Items.EMERALD, cost),
                            hatchet,
                            3, 15, 0.2F
                    );
                });
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextIntBetweenInclusive(5, 19);
                    ItemStack battleaxe = new ItemStack(ModItems.DIAMOND_BATTLEAXE, 1);
                    EnchantmentHelper.enchantItem(
                            random,
                            battleaxe,
                            enchantLevel,
                            world.registryAccess(),
                            Optional.empty()
                    );
                    int cost = 17 + enchantLevel;
                    return new MerchantOffer(
                            new ItemCost(Items.EMERALD, cost),
                            battleaxe,
                            3, 15, 0.2F
                    );
                });
            });
            TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH, 5, factories -> {
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextIntBetweenInclusive(5, 19);
                    ItemStack weapon = new ItemStack(ModItems.DIAMOND_BROADSWORD, 1);
                    EnchantmentHelper.enchantItem(
                            random,
                            weapon,
                            enchantLevel,
                            world.registryAccess(),
                            Optional.empty()
                    );
                    int cost = 13 + enchantLevel;
                    return new MerchantOffer(
                            new ItemCost(Items.EMERALD, cost),
                            weapon,
                            3, 30, 0.2F
                    );
                });
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextIntBetweenInclusive(5, 19);
                    ItemStack weapon = new ItemStack(ModItems.DIAMOND_SICKLE, 1);
                    EnchantmentHelper.enchantItem(
                            random,
                            weapon,
                            enchantLevel,
                            world.registryAccess(),
                            Optional.empty()
                    );
                    int cost = 13 + enchantLevel;
                    return new MerchantOffer(
                            new ItemCost(Items.EMERALD, cost),
                            weapon,
                            3, 30, 0.2F
                    );
                });
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextIntBetweenInclusive(5, 19);
                    ItemStack weapon = new ItemStack(ModItems.DIAMOND_SCYTHE, 1);
                    EnchantmentHelper.enchantItem(
                            random,
                            weapon,
                            enchantLevel,
                            world.registryAccess(),
                            Optional.empty()
                    );
                    int cost = 13 + enchantLevel;
                    return new MerchantOffer(
                            new ItemCost(Items.EMERALD, cost),
                            weapon,
                            3, 30, 0.2F
                    );
                });
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextIntBetweenInclusive(5, 19);
                    ItemStack weapon = new ItemStack(ModItems.DIAMOND_LONGSWORD, 1);
                    EnchantmentHelper.enchantItem(
                            random,
                            weapon,
                            enchantLevel,
                            world.registryAccess(),
                            Optional.empty()
                    );
                    int cost = 13 + enchantLevel;
                    return new MerchantOffer(
                            new ItemCost(Items.EMERALD, cost),
                            weapon,
                            3, 30, 0.2F
                    );
                });
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextIntBetweenInclusive(5, 19);
                    ItemStack weapon = new ItemStack(ModItems.DIAMOND_KATANA, 1);
                    EnchantmentHelper.enchantItem(
                            random,
                            weapon,
                            enchantLevel,
                            world.registryAccess(),
                            Optional.empty()
                    );
                    int cost = 13 + enchantLevel;
                    return new MerchantOffer(
                            new ItemCost(Items.EMERALD, cost),
                            weapon,
                            3, 30, 0.2F
                    );
                });
                factories.add((world, entity, random) -> {
                    int enchantLevel = random.nextIntBetweenInclusive(5, 19);
                    ItemStack weapon = new ItemStack(ModItems.DIAMOND_GREATSWORD, 1);
                    EnchantmentHelper.enchantItem(
                            random,
                            weapon,
                            enchantLevel,
                            world.registryAccess(),
                            Optional.empty()
                    );
                    int cost = 13 + enchantLevel;
                    return new MerchantOffer(
                            new ItemCost(Items.EMERALD, cost),
                            weapon,
                            3, 30, 0.2F
                    );
                });
            });
        }
    }

    private static void weaponsexpanded$toggleBastardSwordMode(Player player) {
        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof BastardSwordItem bastardSword)) return;

        bastardSword.toggleTwoHanded(stack);
        if (player.getOffhandItem().getItem() instanceof ShieldItem shield) {
            player.getCooldowns().addCooldown(shield.getDefaultInstance(), 20);
        }
    }

    private static void weaponsexpanded$tryFireChainCrossbow(Player player) {
        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof ChainCrossbowItem chainCrossbow)) return;
        if (!net.minecraft.world.item.CrossbowItem.isCharged(stack)) return;

        ChargedProjectiles charged =
                stack.getOrDefault(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);

        float speed = charged.contains(net.minecraft.world.item.Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;

        chainCrossbow.performShooting(player.level(), player, InteractionHand.MAIN_HAND, stack, speed, 1.0F, null);

        if (player instanceof ServerPlayer serverPlayer) {
            ItemStack dummy = new ItemStack(Items.CROSSBOW);
            CriteriaTriggers.SHOT_CROSSBOW.trigger(serverPlayer, dummy);
        }
    }
}