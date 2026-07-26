package net.angelic.weaponsexpanded.sound;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public final class ModSounds {
    private ModSounds() {}

    public static final SoundEvent CHAIN_CROSSBOW_CHAMBER =
            SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(WeaponsExpanded.MODID, "item.chain_crossbow.chamber"));

    public static final SoundEvent CHAIN_CROSSBOW_FULL =
            SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath(WeaponsExpanded.MODID, "item.chain_crossbow.full"));

    public static void register() {
        Registry.register(BuiltInRegistries.SOUND_EVENT,
                Identifier.fromNamespaceAndPath(WeaponsExpanded.MODID, "item.chain_crossbow.chamber"),
                CHAIN_CROSSBOW_CHAMBER
        );

        Registry.register(BuiltInRegistries.SOUND_EVENT,
                Identifier.fromNamespaceAndPath(WeaponsExpanded.MODID, "item.chain_crossbow.full"),
                CHAIN_CROSSBOW_FULL
        );
    }
}
