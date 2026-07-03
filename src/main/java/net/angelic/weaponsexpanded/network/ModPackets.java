package net.angelic.weaponsexpanded.network;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;

public final class ModPackets {
    private ModPackets() {
    }

    public static final Channel<CustomPacketPayload> CHANNEL = ChannelBuilder
            .named(Identifier.fromNamespaceAndPath(WeaponsExpanded.MODID, "main"))
            .networkProtocolVersion(1)
            .payloadChannel()
            .play()
            .serverbound()
            .addMain(
                    FireChainCrossbowPayload.ID,
                    FireChainCrossbowPayload.CODEC,
                    ModPackets::handleFireChainCrossbow
            )
            .addMain(
                    ToggleBastardSwordModePayload.ID,
                    ToggleBastardSwordModePayload.CODEC,
                    ModPackets::handleToggleBastardSwordMode
            )
            .addMain(
                    ToggleWarhammerModePayload.ID,
                    ToggleWarhammerModePayload.CODEC,
                    ModPackets::handleToggleWarhammerMode
            )
            .build();

    /**
     * Called from your main mod constructor to force this class to load.
     */
    public static void register() {
    }

    /**
     * Client-only helper. Use this instead of ClientPlayNetworking.send(...).
     */
    public static void sendToServer(CustomPacketPayload payload) {
        CHANNEL.send(payload, PacketDistributor.SERVER.noArg());
    }

    private static void handleToggleBastardSwordMode(
            ToggleBastardSwordModePayload payload,
            CustomPayloadEvent.Context context
    ) {
        ServerPlayer player = context.getSender();
        if (player == null) return;

        weaponsexpanded$toggleBastardSwordMode(player);
    }

    private static void handleToggleWarhammerMode(
            ToggleWarhammerModePayload payload,
            CustomPayloadEvent.Context context
    ) {
        ServerPlayer player = context.getSender();
        if (player == null) return;

        weaponsexpanded$toggleWarhammerMode(player);
    }

    private static void handleFireChainCrossbow(
            FireChainCrossbowPayload payload,
            CustomPayloadEvent.Context context
    ) {
        ServerPlayer player = context.getSender();
        if (player == null) return;

        weaponsexpanded$tryFireChainCrossbow(player);
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
        if (!CrossbowItem.isCharged(stack)) return;

        ChargedProjectiles charged =
                stack.getOrDefault(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY);

        float speed = charged.contains(Items.FIREWORK_ROCKET) ? 1.6F : 3.15F;

        chainCrossbow.performShooting(
                player.level(),
                player,
                InteractionHand.MAIN_HAND,
                stack,
                speed,
                1.0F,
                null
        );

        if (player instanceof ServerPlayer serverPlayer) {
            ItemStack dummy = new ItemStack(Items.CROSSBOW);
            CriteriaTriggers.SHOT_CROSSBOW.trigger(serverPlayer, dummy);
        }
    }
}