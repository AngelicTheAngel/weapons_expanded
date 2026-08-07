package net.angelic.weaponsexpanded.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static net.angelic.weaponsexpanded.WeaponsExpanded.MOD_ID;

public record ToggleWarhammerModePayload() implements CustomPayload {

    public static final Id<ToggleWarhammerModePayload> ID =
            new Id<>(Identifier.of(MOD_ID, "toggle_warhammer_mode"));

    public static final PacketCodec<RegistryByteBuf, ToggleWarhammerModePayload> CODEC =
            PacketCodec.unit(new ToggleWarhammerModePayload());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}

