package net.angelic.weaponsexpanded.network;

import io.netty.buffer.ByteBuf;
import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record FireChainCrossbowPayload()
        implements CustomPacketPayload {

    public static final Type<FireChainCrossbowPayload>
            TYPE =
            new Type<>(
                    WeaponsExpanded.id(
                            "fire_chain_crossbow"
                    )
            );

    public static final StreamCodec<
            ByteBuf,
            FireChainCrossbowPayload
            > STREAM_CODEC =
            StreamCodec.unit(
                    new FireChainCrossbowPayload()
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}