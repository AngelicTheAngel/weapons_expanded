package net.angelic.weaponsexpanded.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public final class ModPackets {
    private ModPackets() {}

    private static boolean weaponsexpanded$registered = false;

    public static void register() {
        if (weaponsexpanded$registered) return;
        weaponsexpanded$registered = true;

        // Client -> Server (PLAY)
        PayloadTypeRegistry.serverboundPlay().register(FireChainCrossbowPayload.ID, FireChainCrossbowPayload.CODEC);
        PayloadTypeRegistry.serverboundPlay().register(ToggleBastardSwordModePayload.ID, ToggleBastardSwordModePayload.CODEC);
        PayloadTypeRegistry.serverboundPlay().register(ToggleWarhammerModePayload.ID, ToggleWarhammerModePayload.CODEC);
        PayloadTypeRegistry.serverboundPlay().register(ToggleHalberdModePayload.ID, ToggleHalberdModePayload.CODEC);
    }
}