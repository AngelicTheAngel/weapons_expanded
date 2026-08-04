package net.angelic.weaponsexpanded.network;

import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;

public final class ModPackets {

    private static final float WEAPONSEXPANDED$ARROW_SPEED =
            3.15F;

    private static final float WEAPONSEXPANDED$FIREWORK_SPEED =
            1.6F;

    private static final float WEAPONSEXPANDED$DIVERGENCE =
            1.0F;

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
        ServerPlayNetworking.registerGlobalReceiver(
                ToggleBastardSwordModePayload.ID,
                (server, player, handler, buf, responseSender) ->
                        server.execute(() -> {
                            ItemStack stack =
                                    player.getMainHandStack();

                            if (!(stack.getItem()
                                    instanceof BastardSwordItem sword)) {
                                return;
                            }

                            sword.toggleTwoHanded(stack);

                            player.currentScreenHandler
                                    .sendContentUpdates();
                        })
        );
    }

    private static void registerWarhammerReceiver() {
        ServerPlayNetworking.registerGlobalReceiver(
                ToggleWarhammerModePayload.ID,
                (server, player, handler, buf, responseSender) ->
                        server.execute(() -> {
                            ItemStack stack =
                                    player.getMainHandStack();

                            if (!(stack.getItem()
                                    instanceof WarhammerItem warhammer)) {
                                return;
                            }

                            warhammer.toggleSharpSide(stack);

                            player.currentScreenHandler
                                    .sendContentUpdates();
                        })
        );
    }

    private static void registerChainCrossbowReceiver() {
        ServerPlayNetworking.registerGlobalReceiver(
                FireChainCrossbowPayload.ID,
                (server, player, handler, buf, responseSender) ->
                        server.execute(() -> {
                            ItemStack stack =
                                    player.getMainHandStack();

                            if (!(stack.getItem()
                                    instanceof ChainCrossbowItem)) {
                                return;
                            }

                            if (!CrossbowItem.isCharged(stack)) {
                                return;
                            }

                            if (player.getItemCooldownManager()
                                    .isCoolingDown(stack.getItem())) {
                                return;
                            }

                            float speed =
                                    CrossbowItem.hasProjectile(
                                            stack,
                                            Items.FIREWORK_ROCKET
                                    )
                                            ? WEAPONSEXPANDED$FIREWORK_SPEED
                                            : WEAPONSEXPANDED$ARROW_SPEED;

                            /*
                             * Vanilla clears the Charged flag before
                             * calling shootAll(). shootAll() itself only
                             * clears ChargedProjectiles in 1.20.1.
                             */
                            CrossbowItem.setCharged(
                                    stack,
                                    false
                            );

                            CrossbowItem.shootAll(
                                    player.getWorld(),
                                    player,
                                    Hand.MAIN_HAND,
                                    stack,
                                    speed,
                                    WEAPONSEXPANDED$DIVERGENCE
                            );
                        })
        );
    }
}