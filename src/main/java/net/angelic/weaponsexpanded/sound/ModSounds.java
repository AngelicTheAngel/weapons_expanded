package net.angelic.weaponsexpanded.sound;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public final class ModSounds {
    private ModSounds() {}

    public static final SoundEvent CHAIN_CROSSBOW_CHAMBER =
            SoundEvent.of(Identifier.of(WeaponsExpanded.MOD_ID, "item.chain_crossbow.chamber"));

    public static final SoundEvent CHAIN_CROSSBOW_FULL =
            SoundEvent.of(Identifier.of(WeaponsExpanded.MOD_ID, "item.chain_crossbow.full"));

    public static void register() {
        Registry.register(Registries.SOUND_EVENT,
                Identifier.of(WeaponsExpanded.MOD_ID, "item.chain_crossbow.chamber"),
                CHAIN_CROSSBOW_CHAMBER
        );

        Registry.register(Registries.SOUND_EVENT,
                Identifier.of(WeaponsExpanded.MOD_ID, "item.chain_crossbow.full"),
                CHAIN_CROSSBOW_FULL
        );
    }
}