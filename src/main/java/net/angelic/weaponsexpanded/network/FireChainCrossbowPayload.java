package net.angelic.weaponsexpanded.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import static net.angelic.weaponsexpanded.WeaponsExpanded.MODID;

public record FireChainCrossbowPayload() implements CustomPacketPayload {
    public static final Type<FireChainCrossbowPayload> ID =
            new Type<>(Identifier.fromNamespaceAndPath(MODID, "fire_chain_crossbow"));

    public static final StreamCodec<RegistryFriendlyByteBuf, FireChainCrossbowPayload> CODEC =
            StreamCodec.unit(new FireChainCrossbowPayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
