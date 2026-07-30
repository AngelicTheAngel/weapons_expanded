package net.angelic.weaponsexpanded.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static net.angelic.weaponsexpanded.WeaponsExpanded.MOD_ID;

public record FireChainCrossbowPayload() implements CustomPayload {

    public static final CustomPayload.Id<FireChainCrossbowPayload> ID =
            new CustomPayload.Id<>(Identifier.of(MOD_ID, "fire_chain_crossbow"));

    public static final PacketCodec<RegistryByteBuf, FireChainCrossbowPayload> CODEC =
            PacketCodec.unit(new FireChainCrossbowPayload());

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}
