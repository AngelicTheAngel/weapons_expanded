package net.angelic.weaponsexpanded.network;

import io.netty.buffer.ByteBuf;
import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record ToggleWarhammerModePayload()
        implements CustomPacketPayload {

    public static final Type<ToggleWarhammerModePayload>
            TYPE =
            new Type<>(
                    WeaponsExpanded.id(
                            "toggle_warhammer_mode"
                    )
            );

    public static final StreamCodec<
            ByteBuf,
            ToggleWarhammerModePayload
            > STREAM_CODEC =
            StreamCodec.unit(
                    new ToggleWarhammerModePayload()
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}