package net.angelic.weaponsexpanded.network;

import io.netty.buffer.ByteBuf;
import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record ToggleBastardSwordModePayload()
        implements CustomPacketPayload {

    public static final Type<ToggleBastardSwordModePayload>
            TYPE =
            new Type<>(
                    WeaponsExpanded.id(
                            "toggle_bastard_sword_mode"
                    )
            );

    public static final StreamCodec<
            ByteBuf,
            ToggleBastardSwordModePayload
            > STREAM_CODEC =
            StreamCodec.unit(
                    new ToggleBastardSwordModePayload()
            );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}