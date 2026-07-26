package net.angelic.weaponsexpanded.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

import static net.angelic.weaponsexpanded.WeaponsExpanded.MODID;

public record ToggleBastardSwordModePayload() implements CustomPacketPayload {
    public static final Type<ToggleBastardSwordModePayload> ID =
            new Type<>(Identifier.fromNamespaceAndPath(MODID, "toggle_bastard_sword_mode"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ToggleBastardSwordModePayload> CODEC =
            StreamCodec.unit(new ToggleBastardSwordModePayload());

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
