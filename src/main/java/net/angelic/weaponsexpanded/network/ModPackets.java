package net.angelic.weaponsexpanded.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;

public final class ModPackets {
    private ModPackets() {}

    private static boolean weaponsexpanded$registered = false;

    public static void register() {
        if (weaponsexpanded$registered) return;
        weaponsexpanded$registered = true;

        // Client -> Server (PLAY)
        PayloadTypeRegistry.playC2S().register(FireChainCrossbowPayload.ID, FireChainCrossbowPayload.CODEC);

        // If you later add server->client payloads:
        // PayloadTypeRegistry.playS2C().register(SomePayload.ID, SomePayload.CODEC);
    }
}
