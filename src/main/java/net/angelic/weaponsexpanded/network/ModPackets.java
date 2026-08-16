package net.angelic.weaponsexpanded.network;

import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.neoforged.neoforge.client.network.ClientPacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public final class ModPackets {

    private static final String PROTOCOL_VERSION = "1";

    private static final float
            WEAPONSEXPANDED$ARROW_SPEED =
            3.15F;

    private static final float
            WEAPONSEXPANDED$FIREWORK_SPEED =
            1.6F;

    private static final float
            WEAPONSEXPANDED$DIVERGENCE =
            1.0F;

    private static final int
            WEAPONSEXPANDED$FIRE_COOLDOWN =
            8;

    private ModPackets() {
    }

    public static void register(
            RegisterPayloadHandlersEvent event
    ) {
        PayloadRegistrar registrar =
                event.registrar(PROTOCOL_VERSION);

        registrar.playToServer(
                ToggleBastardSwordModePayload.TYPE,
                ToggleBastardSwordModePayload.STREAM_CODEC,
                ModPackets::handleBastardSwordPacket
        );

        registrar.playToServer(
                ToggleWarhammerModePayload.TYPE,
                ToggleWarhammerModePayload.STREAM_CODEC,
                ModPackets::handleWarhammerPacket
        );

        registrar.playToServer(
                FireChainCrossbowPayload.TYPE,
                FireChainCrossbowPayload.STREAM_CODEC,
                ModPackets::handleChainCrossbowPacket
        );
    }

    private static void handleBastardSwordPacket(
            ToggleBastardSwordModePayload payload,
            IPayloadContext context
    ) {
        if (!(context.player()
                instanceof ServerPlayer player)) {
            return;
        }

        ItemStack stack =
                player.getMainHandItem();

        if (!(stack.getItem()
                instanceof BastardSwordItem sword)) {
            return;
        }

        sword.toggleTwoHanded(stack);

        player.containerMenu.broadcastChanges();
        player.inventoryMenu.broadcastChanges();
    }

    private static void handleWarhammerPacket(
            ToggleWarhammerModePayload payload,
            IPayloadContext context
    ) {
        if (!(context.player()
                instanceof ServerPlayer player)) {
            return;
        }

        ItemStack stack =
                player.getMainHandItem();

        if (!(stack.getItem()
                instanceof WarhammerItem warhammer)) {
            return;
        }

        warhammer.toggleSharpSide(stack);

        player.containerMenu.broadcastChanges();
        player.inventoryMenu.broadcastChanges();
    }

    private static void handleChainCrossbowPacket(
            FireChainCrossbowPayload payload,
            IPayloadContext context
    ) {
        if (!(context.player()
                instanceof ServerPlayer player)) {
            return;
        }

        ItemStack stack =
                player.getMainHandItem();

        if (!(stack.getItem()
                instanceof ChainCrossbowItem chainCrossbow)) {
            return;
        }

        ChargedProjectiles chargedProjectiles =
                stack.getOrDefault(
                        DataComponents.CHARGED_PROJECTILES,
                        ChargedProjectiles.EMPTY
                );

        if (chargedProjectiles.isEmpty()) {
            ChainCrossbowItem
                    .weaponsexpanded$refreshLoadedVisual(
                            stack
                    );

            player.containerMenu.broadcastChanges();
            player.inventoryMenu.broadcastChanges();
            return;
        }

        if (player.getCooldowns()
                .isOnCooldown(stack)) {
            return;
        }

        float speed =
                chargedProjectiles.contains(
                        Items.FIREWORK_ROCKET
                )
                        ? WEAPONSEXPANDED$FIREWORK_SPEED
                        : WEAPONSEXPANDED$ARROW_SPEED;

        /*
         * performShooting is an instance method in 1.21.1.
         * The final argument is an optional target used by mobs.
         */
        chainCrossbow.performShooting(
                player.level(),
                player,
                InteractionHand.MAIN_HAND,
                stack,
                speed,
                WEAPONSEXPANDED$DIVERGENCE,
                null
        );

        /*
         * If projectiles remain, shooting was prevented or did not
         * complete successfully.
         */
        if (weaponsexpanded$hasLoadedProjectiles(stack)) {
            return;
        }

        player.getCooldowns().addCooldown(stack, WEAPONSEXPANDED$FIRE_COOLDOWN);

        CriteriaTriggers.SHOT_CROSSBOW.trigger(
                player,
                new ItemStack(Items.CROSSBOW)
        );

        boolean loadedNextChamber =
                ChainCrossbowItem
                        .weaponsexpanded$loadNextChamber(
                                player.level(),
                                stack
                        );

        if (loadedNextChamber) {
            player.level().playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    ModSounds.CHAIN_CROSSBOW_CHAMBER.get(),
                    SoundSource.PLAYERS,
                    0.7F,
                    1.0F
            );
        }

        ChainCrossbowItem
                .weaponsexpanded$refreshLoadedVisual(
                        stack
                );

        player.containerMenu.broadcastChanges();
        player.inventoryMenu.broadcastChanges();
    }

    private static boolean weaponsexpanded$hasLoadedProjectiles(
            ItemStack stack
    ) {
        ChargedProjectiles projectiles =
                stack.getOrDefault(
                        DataComponents.CHARGED_PROJECTILES,
                        ChargedProjectiles.EMPTY
                );

        return !projectiles.isEmpty();
    }

    public static void sendFireChainCrossbow() {
        sendToServer(
                new FireChainCrossbowPayload()
        );
    }

    public static void sendToServer(
            CustomPacketPayload payload
    ) {
        ClientPacketDistributor.sendToServer(payload);
    }
}