package net.angelic.weaponsexpanded.sound;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(
                    Registries.SOUND_EVENT,
                    WeaponsExpanded.MODID
            );

    public static final DeferredHolder<SoundEvent, SoundEvent>
            CHAIN_CROSSBOW_CHAMBER =
            registerSound("item.chain_crossbow.chamber");

    public static final DeferredHolder<SoundEvent, SoundEvent>
            CHAIN_CROSSBOW_FULL =
            registerSound("item.chain_crossbow.full");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSound(
            String name
    ) {
        Identifier id = Identifier.fromNamespaceAndPath(
                WeaponsExpanded.MODID,
                name
        );

        return SOUND_EVENTS.register(
                name,
                () -> SoundEvent.createVariableRangeEvent(id)
        );
    }

    public static void register(IEventBus modEventBus) {
        SOUND_EVENTS.register(modEventBus);
    }

    private ModSounds() {
    }
}