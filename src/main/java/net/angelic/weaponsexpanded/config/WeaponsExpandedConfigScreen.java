package net.angelic.weaponsexpanded.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class WeaponsExpandedConfigScreen {
    private WeaponsExpandedConfigScreen() {}

    public static Screen create(Screen parent) {
        WeaponsExpandedConfig cfg = WeaponsExpandedConfig.get();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.translatable("config.weaponsexpanded.title"))
                .setSavingRunnable(cfg::save);

        ConfigCategory general = builder.getOrCreateCategory(Component.translatable("config.weaponsexpanded.category.general"));
        ConfigEntryBuilder eb = builder.entryBuilder();

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.enableCustomLootTables"), cfg.enableCustomLootTables)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.enableCustomLootTables = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.dynamiteArrowsDestroyBlocks"), cfg.dynamiteArrowsDestroyBlocks)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.dynamiteArrowsDestroyBlocks = v)
                .build());

        general.addEntry(eb.startIntField(Component.translatable("config.weaponsexpanded.option.chainCrossbowMagazineSize"), cfg.chainCrossbowMagazineSize)
                .setDefaultValue(3)
                .setSaveConsumer(v -> cfg.chainCrossbowMagazineSize = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.enableEntityMeleeEquipment"), cfg.enableEntityMeleeEquipment)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.enableEntityMeleeEquipment = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.enableTrialChamberMeleeEquipment"), cfg.enableTrialChamberMeleeEquipment)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.enableTrialChamberMeleeEquipment = v)
                .build());

         //general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.enableWeaponsmithTrades"), cfg.enableWeaponsmithTrades)
         //       .setDefaultValue(true)
         //       .setSaveConsumer(v -> cfg.enableWeaponsmithTrades = v)
         //       .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.altTwoHandedSwordHandling"), cfg.altTwoHandedSwordHandling)
                .setDefaultValue(false)
                .setSaveConsumer(v -> cfg.altTwoHandedSwordHandling = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.disableExtraDurabilityDamageForAxes"), cfg.disableExtraDurabilityDamageForAxes)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.disableExtraDurabilityDamageForAxes = v)
                .build());

        return builder.build();
    }
}
