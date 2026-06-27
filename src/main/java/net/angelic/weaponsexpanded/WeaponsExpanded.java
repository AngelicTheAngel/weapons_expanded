package net.angelic.weaponsexpanded;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.effect.ModEffects;
import net.angelic.weaponsexpanded.enchantment.ModEnchantmentEffects;
import net.angelic.weaponsexpanded.entity.ModEntities;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.angelic.weaponsexpanded.network.FireChainCrossbowPayload;
import net.angelic.weaponsexpanded.network.ModPackets;
import net.angelic.weaponsexpanded.network.ToggleBastardSwordModePayload;
import net.angelic.weaponsexpanded.network.ToggleWarhammerModePayload;
import net.angelic.weaponsexpanded.potion.ModPotions;
import net.angelic.weaponsexpanded.registries.ModFuels;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.angelic.weaponsexpanded.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.crafting.Ingredient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeaponsExpanded implements ModInitializer {
    public static final String MOD_ID = "weaponsexpanded";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

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
        ModEnchantmentEffects.registerEnchantmentEffects();
        ModEffects.registerEffects();
        ModEntities.registerEntities();
        ModSounds.register();
        ModFuels.registerFuels();
        ModPotions.registerPotions();

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

        ServerPlayNetworking.registerGlobalReceiver(ToggleWarhammerModePayload.ID, (payload, context) ->
                context.server().execute(() -> weaponsexpanded$toggleWarhammerMode(context.player()))
        );

        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(Items.BLUE_ICE), ModPotions.FROSTBITE_POTION);
            builder.registerPotionRecipe(ModPotions.FROSTBITE_POTION, Ingredient.of(Items.REDSTONE), ModPotions.LONG_FROSTBITE_POTION);
        });
    }

    private static void weaponsexpanded$toggleBastardSwordMode(Player player) {
        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof BastardSwordItem bastardSword)) return;

        bastardSword.toggleTwoHanded(stack);
        if (player.getOffhandItem().getItem() instanceof ShieldItem shield) {
            player.getCooldowns().addCooldown(shield.getDefaultInstance(), 20);
        }
        player.resetAttackStrengthTicker();
    }

    private static void weaponsexpanded$toggleWarhammerMode(Player player) {
        ItemStack stack = player.getMainHandItem();
        if (!(stack.getItem() instanceof WarhammerItem warhammer)) return;

        warhammer.toggleSharpSide(stack);
        player.resetAttackStrengthTicker();
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