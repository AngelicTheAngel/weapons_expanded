package net.angelic.weaponsexpanded.network;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

import static net.angelic.weaponsexpanded.WeaponsExpanded.MOD_ID;

public record ToggleBastardSwordModePayload() implements CustomPayload {

    public static final CustomPayload.Id<ToggleBastardSwordModePayload> ID =
            new CustomPayload.Id<>(Identifier.of(MOD_ID, "toggle_bastard_sword_mode"));

    public static final PacketCodec<RegistryByteBuf, ToggleBastardSwordModePayload> CODEC =
            PacketCodec.unit(new ToggleBastardSwordModePayload());

    @Override
    public CustomPayload.Id<? extends CustomPayload> getId() {
        return ID;
    }
}

