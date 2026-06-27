package net.angelic.weaponsexpanded.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

import static net.angelic.weaponsexpanded.WeaponsExpanded.MOD_ID;

public record ToggleWarhammerModePayload() implements CustomPacketPayload {

    public static final Type<ToggleWarhammerModePayload> ID =
            new Type<>(Identifier.fromNamespaceAndPath(MOD_ID, "toggle_warhammer_mode"));

    public static final StreamCodec<RegistryFriendlyByteBuf, ToggleWarhammerModePayload> CODEC =
            StreamCodec.unit(new ToggleWarhammerModePayload());

    @Override
    public @NonNull Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}

