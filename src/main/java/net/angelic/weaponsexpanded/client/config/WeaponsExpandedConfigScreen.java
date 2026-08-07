package net.angelic.weaponsexpanded.client.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public final class WeaponsExpandedConfigScreen {
    private WeaponsExpandedConfigScreen() {}

    public static Screen create(Screen parent) {
        WeaponsExpandedConfig cfg = WeaponsExpandedConfig.get();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("config.weaponsexpanded.title"))
                .setSavingRunnable(cfg::save);

        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("config.weaponsexpanded.category.general"));
        ConfigEntryBuilder eb = builder.entryBuilder();

        general.addEntry(eb.startBooleanToggle(Text.translatable("config.weaponsexpanded.option.enableCustomLootTables"), cfg.enableCustomLootTables)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.enableCustomLootTables = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Text.translatable("config.weaponsexpanded.option.enableEntityMeleeEquipment"), cfg.enableEntityMeleeEquipment)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.enableEntityMeleeEquipment = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Text.translatable("config.weaponsexpanded.option.enableTrialChamberMeleeEquipment"), cfg.enableTrialChamberMeleeEquipment)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.enableTrialChamberMeleeEquipment = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Text.translatable("config.weaponsexpanded.option.enableWeaponsmithTrades"), cfg.enableWeaponsmithTrades)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.enableWeaponsmithTrades = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Text.translatable("config.weaponsexpanded.option.altTwoHandedSwordHandling"), cfg.altTwoHandedSwordHandling)
                .setDefaultValue(false)
                .setSaveConsumer(v -> cfg.altTwoHandedSwordHandling = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Text.translatable("config.weaponsexpanded.option.disableExtraDurabilityDamageForAxes"), cfg.disableExtraDurabilityDamageForAxes)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.disableExtraDurabilityDamageForAxes = v)
                .build());

        return builder.build();
    }
}
