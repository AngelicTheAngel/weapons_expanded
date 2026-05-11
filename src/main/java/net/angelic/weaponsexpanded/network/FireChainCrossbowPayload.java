package net.angelic.weaponsexpanded.network;

import static net.angelic.weaponsexpanded.WeaponsExpanded.MOD_ID;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record FireChainCrossbowPayload() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<FireChainCrossbowPayload> ID =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(MOD_ID, "fire_chain_crossbow"));

    public static final StreamCodec<RegistryFriendlyByteBuf, FireChainCrossbowPayload> CODEC =
            StreamCodec.unit(new FireChainCrossbowPayload());

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
