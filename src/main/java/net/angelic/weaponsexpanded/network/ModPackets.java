package net.angelic.weaponsexpanded.network;

import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public final class ModPackets {
    private ModPackets() {
    }

    /**
     * Call this from your main mod constructor.
     */
    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(ModPackets::registerPayloadHandlers);
    }

    private static void registerPayloadHandlers(
            RegisterPayloadHandlersEvent event
    ) {
        PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(
                FireChainCrossbowPayload.ID,
                FireChainCrossbowPayload.CODEC,
                ModPackets::handleFireChainCrossbow
        );

        registrar.playToServer(
                ToggleBastardSwordModePayload.ID,
                ToggleBastardSwordModePayload.CODEC,
                ModPackets::handleToggleBastardSwordMode
        );

        registrar.playToServer(
                ToggleWarhammerModePayload.ID,
                ToggleWarhammerModePayload.CODEC,
                ModPackets::handleToggleWarhammerMode
        );
    }

    private static void handleToggleBastardSwordMode(
            ToggleBastardSwordModePayload payload,
            IPayloadContext context
    ) {
        if (!(context.player() instanceof ServerPlayer player)) {
            return;
        }

        weaponsexpanded$toggleBastardSwordMode(player);
    }

    private static void handleToggleWarhammerMode(
            ToggleWarhammerModePayload payload,
            IPayloadContext context
    ) {
        if (!(context.player() instanceof ServerPlayer player)) {
            return;
        }

        weaponsexpanded$toggleWarhammerMode(player);
    }

    private static void handleFireChainCrossbow(
            FireChainCrossbowPayload payload,
            IPayloadContext context
    ) {
        if (!(context.player() instanceof ServerPlayer player)) {
            return;
        }

        weaponsexpanded$tryFireChainCrossbow(player);
    }

    private static void weaponsexpanded$toggleBastardSwordMode(
            ServerPlayer player
    ) {
        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof BastardSwordItem bastardSword)) {
            return;
        }

        bastardSword.toggleTwoHanded(stack);

        if (player.getOffhandItem().getItem() instanceof ShieldItem shield) {
            player.getCooldowns().addCooldown(
                    shield.getDefaultInstance(),
                    20
            );
        }

        player.resetAttackStrengthTicker();
    }

    private static void weaponsexpanded$toggleWarhammerMode(
            ServerPlayer player
    ) {
        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof WarhammerItem warhammer)) {
            return;
        }

        warhammer.toggleSharpSide(stack);
        player.resetAttackStrengthTicker();
    }

    private static void weaponsexpanded$tryFireChainCrossbow(
            ServerPlayer player
    ) {
        ItemStack stack = player.getMainHandItem();

        if (!(stack.getItem() instanceof ChainCrossbowItem chainCrossbow)) {
            return;
        }

        if (!CrossbowItem.isCharged(stack)) {
            return;
        }

        ChargedProjectiles charged = stack.getOrDefault(
                DataComponents.CHARGED_PROJECTILES,
                ChargedProjectiles.EMPTY
        );

        float speed = charged.contains(Items.FIREWORK_ROCKET)
                ? 1.6F
                : 3.15F;

        chainCrossbow.performShooting(
                player.level(),
                player,
                InteractionHand.MAIN_HAND,
                stack,
                speed,
                1.0F,
                null
        );

        ItemStack dummyCrossbow = new ItemStack(Items.CROSSBOW);
        CriteriaTriggers.SHOT_CROSSBOW.trigger(
                player,
                dummyCrossbow
        );
    }
}