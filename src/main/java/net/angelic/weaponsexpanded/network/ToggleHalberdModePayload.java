package net.angelic.weaponsexpanded.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

import static net.angelic.weaponsexpanded.WeaponsExpanded.MOD_ID;

public record ToggleHalberdModePayload() implements CustomPacketPayload {

    public static final Type<ToggleHalberdModePayload> ID =
            new Type<>(Identifier.fromNamespaceAndPath(MOD_ID, "toggle_halberd_mode"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ToggleHalberdModePayload> CODEC =
            StreamCodec.unit(new ToggleHalberdModePayload());

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}

