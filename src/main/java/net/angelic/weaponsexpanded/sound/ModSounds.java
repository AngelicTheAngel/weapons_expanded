package net.angelic.weaponsexpanded.sound;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModSounds {
    private ModSounds() {}

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(
                    BuiltInRegistries.SOUND_EVENT,
                    WeaponsExpanded.MOD_ID
            );

    public static final DeferredHolder<SoundEvent, SoundEvent>
            CHAIN_CROSSBOW_CHAMBER =
            registerSoundEvent("item.chain_crossbow.chamber");

    public static final DeferredHolder<SoundEvent, SoundEvent>
            CHAIN_CROSSBOW_FULL =
            registerSoundEvent("item.chain_crossbow.full");

    private static DeferredHolder<SoundEvent, SoundEvent>
    registerSoundEvent(String name) {
        ResourceLocation id =
                ResourceLocation.fromNamespaceAndPath(
                        WeaponsExpanded.MOD_ID,
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
}