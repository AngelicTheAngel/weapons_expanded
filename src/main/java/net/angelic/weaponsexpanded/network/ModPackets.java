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

        // If you later add server->client payloads:
        // PayloadTypeRegistry.clientboundPlay().register(SomePayload.ID, SomePayload.CODEC);
    }
}