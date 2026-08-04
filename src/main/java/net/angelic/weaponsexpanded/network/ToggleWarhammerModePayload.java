package net.angelic.weaponsexpanded.network;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.util.Identifier;

public final class ToggleWarhammerModePayload {
    public static final Identifier ID =
            new Identifier(
                    WeaponsExpanded.MOD_ID,
                    "toggle_warhammer_mode"
            );

    private ToggleWarhammerModePayload() {}
}