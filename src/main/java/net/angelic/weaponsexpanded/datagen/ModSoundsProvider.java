package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.sound.ModSounds;
import net.fabricmc.fabric.api.client.datagen.v1.builder.SoundTypeBuilder;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;

import java.util.concurrent.CompletableFuture;

public class ModSoundsProvider extends FabricSoundsProvider {
    public ModSoundsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registryLookup, SoundExporter exporter) {
        exporter.add(ModSounds.CHAIN_CROSSBOW_CHAMBER, SoundTypeBuilder.of(ModSounds.CHAIN_CROSSBOW_CHAMBER)
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "item/chain_crossbow_chamber"))));

        exporter.add(ModSounds.CHAIN_CROSSBOW_FULL, SoundTypeBuilder.of(ModSounds.CHAIN_CROSSBOW_FULL)
                .sound(SoundTypeBuilder.RegistrationBuilder.ofFile(Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, "item/chain_crossbow_full"))));
    }

    @Override
    public String getName() {
        return "Weapons Expanded Sounds";
    }
}
