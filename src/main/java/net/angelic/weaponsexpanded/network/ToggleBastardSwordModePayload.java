package net.angelic.weaponsexpanded.network;

import static net.angelic.weaponsexpanded.WeaponsExpanded.MOD_ID;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record ToggleBastardSwordModePayload() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ToggleBastardSwordModePayload> ID =
            new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath(MOD_ID, "toggle_bastard_sword_mode"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ToggleBastardSwordModePayload> CODEC =
            StreamCodec.unit(new ToggleBastardSwordModePayload());

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}

