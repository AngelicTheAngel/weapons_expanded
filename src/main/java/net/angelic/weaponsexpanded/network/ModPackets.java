package net.angelic.weaponsexpanded.network;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public final class ModPackets {

    private static final String PROTOCOL_VERSION =
            "1";

    public static final SimpleChannel CHANNEL =
            NetworkRegistry.newSimpleChannel(
                    new ResourceLocation(
                            WeaponsExpanded.MOD_ID,
                            "main"
                    ),
                    () -> PROTOCOL_VERSION,
                    PROTOCOL_VERSION::equals,
                    PROTOCOL_VERSION::equals
            );

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

    private static final String
            WEAPONSEXPANDED$CHARGED_PROJECTILES_KEY =
            "ChargedProjectiles";

    private static int packetId;
    private static boolean registered;

    private ModPackets() {
    }

    public static void register() {
        if (registered) {
            return;
        }

        registered = true;

        registerBastardSwordReceiver();
        registerWarhammerReceiver();
        registerChainCrossbowReceiver();
    }

    private static void registerBastardSwordReceiver() {
        CHANNEL.registerMessage(
                packetId++,
                ToggleBastardSwordModePayload.class,
                (message, buffer) -> {
                },
                buffer ->
                        new ToggleBastardSwordModePayload(),
                ModPackets::handleBastardSwordPacket
        );
    }

    private static void handleBastardSwordPacket(
            ToggleBastardSwordModePayload message,
            Supplier<NetworkEvent.Context> contextSupplier
    ) {
        NetworkEvent.Context context =
                contextSupplier.get();

        context.enqueueWork(() -> {
            ServerPlayer player =
                    context.getSender();

            if (player == null) {
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
        });

        context.setPacketHandled(true);
    }

    private static void registerWarhammerReceiver() {
        CHANNEL.registerMessage(
                packetId++,
                ToggleWarhammerModePayload.class,
                (message, buffer) -> {
                },
                buffer ->
                        new ToggleWarhammerModePayload(),
                ModPackets::handleWarhammerPacket
        );
    }

    private static void handleWarhammerPacket(
            ToggleWarhammerModePayload message,
            Supplier<NetworkEvent.Context> contextSupplier
    ) {
        NetworkEvent.Context context =
                contextSupplier.get();

        context.enqueueWork(() -> {
            ServerPlayer player =
                    context.getSender();

            if (player == null) {
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
        });

        context.setPacketHandled(true);
    }

    private static void registerChainCrossbowReceiver() {
        CHANNEL.registerMessage(
                packetId++,
                FireChainCrossbowPayload.class,
                (message, buffer) -> {
                },
                buffer ->
                        new FireChainCrossbowPayload(),
                ModPackets::handleChainCrossbowPacket
        );
    }

    private static void handleChainCrossbowPacket(
            FireChainCrossbowPayload message,
            Supplier<NetworkEvent.Context> contextSupplier
    ) {
        NetworkEvent.Context context =
                contextSupplier.get();

        context.enqueueWork(() -> {
            ServerPlayer player =
                    context.getSender();

            if (player == null) {
                return;
            }

            ItemStack stack =
                    player.getMainHandItem();

            if (!(stack.getItem()
                    instanceof ChainCrossbowItem)) {
                return;
            }

            if (!CrossbowItem.isCharged(stack)) {
                return;
            }

            if (!weaponsexpanded$hasLoadedProjectiles(stack)) {
                /*
                 * Repair an invalid state where Charged is true but
                 * no projectile is actually loaded.
                 */
                CrossbowItem.setCharged(
                        stack,
                        false
                );

                ChainCrossbowItem
                        .weaponsexpanded$refreshLoadedVisual(
                                stack
                        );

                player.containerMenu.broadcastChanges();
                player.inventoryMenu.broadcastChanges();
                return;
            }

            if (player.getCooldowns()
                    .isOnCooldown(stack.getItem())) {
                return;
            }

            float speed =
                    CrossbowItem.containsChargedProjectile(
                            stack,
                            Items.FIREWORK_ROCKET
                    )
                            ? WEAPONSEXPANDED$FIREWORK_SPEED
                            : WEAPONSEXPANDED$ARROW_SPEED;

            CrossbowItem.performShooting(
                    player.level(),
                    player,
                    InteractionHand.MAIN_HAND,
                    stack,
                    speed,
                    WEAPONSEXPANDED$DIVERGENCE
            );

            /*
             * A successful shot clears ChargedProjectiles. If the list
             * still contains something, a Forge event cancelled it.
             */
            if (weaponsexpanded$hasLoadedProjectiles(stack)) {
                return;
            }

            /*
             * performShooting clears the projectiles but does not clear
             * vanilla's Charged boolean.
             */
            CrossbowItem.setCharged(
                    stack,
                    false
            );

            player.getCooldowns().addCooldown(
                    stack.getItem(),
                    WEAPONSEXPANDED$FIRE_COOLDOWN
            );

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
        });

        context.setPacketHandled(true);
    }

    private static boolean weaponsexpanded$hasLoadedProjectiles(
            ItemStack stack
    ) {
        CompoundTag tag = stack.getTag();

        if (tag == null) {
            return false;
        }

        return !tag.getList(
                WEAPONSEXPANDED$CHARGED_PROJECTILES_KEY,
                Tag.TAG_COMPOUND
        ).isEmpty();
    }

    public static void sendFireChainCrossbow() {
        CHANNEL.sendToServer(
                new FireChainCrossbowPayload()
        );
    }

    public static void sendToServer(Object message) {
        CHANNEL.sendToServer(message);
    }
}