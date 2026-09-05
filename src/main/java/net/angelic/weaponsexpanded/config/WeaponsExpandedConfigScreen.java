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
        //ConfigCategory chainCrossbow = builder.getOrCreateCategory(Component.translatable("config.weaponsexpanded.category.chain_crossbow"));
        ConfigEntryBuilder eb = builder.entryBuilder();

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.enableCustomLootTables"), cfg.enableCustomLootTables)
                .setDefaultValue(true)
                .requireRestart()
                .setSaveConsumer(v -> cfg.enableCustomLootTables = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.dynamiteArrowsDestroyBlocks"), cfg.dynamiteArrowsDestroyBlocks)
                .setDefaultValue(true)
                .setSaveConsumer(v -> cfg.dynamiteArrowsDestroyBlocks = v)
                .build());

        general.addEntry(eb.startIntSlider(Component.translatable("config.weaponsexpanded.option.chainCrossbowMagazineSize"), cfg.chainCrossbowMagazineSize, 1, 10)
                .setDefaultValue(3)
                .setSaveConsumer(v -> cfg.chainCrossbowMagazineSize = v)
                .build());

        general.addEntry(eb.startIntSlider(Component.translatable("config.weaponsexpanded.option.chainCrossbowCooldown"), cfg.chainCrossbowCooldown, 0, 20)
                .setDefaultValue(8)
                .setSaveConsumer(v -> cfg.chainCrossbowCooldown = v)
                .build());

        general.addEntry(eb.startIntSlider(Component.translatable("config.weaponsexpanded.option.chainCrossbowLoadTime"), cfg.chainCrossbowLoadTime, 1, 50)
                .setDefaultValue(38)
                .setSaveConsumer(v -> cfg.chainCrossbowLoadTime = v)
                .build());

        general.addEntry(eb.startIntSlider(Component.translatable("config.weaponsexpanded.option.extraSizePerCapacityLevel"), cfg.chainCrossbowExtraSizePerCapacityLevel, 1, 3)
                .setDefaultValue(1)
                .setSaveConsumer(v -> cfg.chainCrossbowExtraSizePerCapacityLevel = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.enableEntityMeleeEquipment"), cfg.enableEntityMeleeEquipment)
                .setDefaultValue(true)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.enableEntityMeleeEquipment.description"))
                .setSaveConsumer(v -> cfg.enableEntityMeleeEquipment = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.enableTrialChamberMeleeEquipment"), cfg.enableTrialChamberMeleeEquipment)
                .setDefaultValue(true)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.enableTrialChamberMeleeEquipment.description"))
                .setSaveConsumer(v -> cfg.enableTrialChamberMeleeEquipment = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.enableEntityTypeChanges"), cfg.enableEntityTypeChanges)
                .setDefaultValue(true)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.enableEntityTypeChanges.description"))
                .requireRestart()
                .setSaveConsumer(v -> cfg.enableEntityTypeChanges = v)
                .build());

//        general.addEntry(eb.startIntSlider(Component.translatable("config.weaponsexpanded.option.ritualDaggerMaxLevel"), cfg.ritualDaggerMaxLevel, 1, 4)
//                .setDefaultValue(2)
//                .setSaveConsumer(v -> cfg.ritualDaggerMaxLevel = v)
//                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.enableWeaponsmithTrades"), cfg.enableWeaponsmithTrades)
                .setDefaultValue(true)
                .requireRestart()
                .setSaveConsumer(v -> cfg.enableWeaponsmithTrades = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.frostbitePotionRecipe"), cfg.frostbitePotionRecipe)
                .setDefaultValue(true)
                .requireRestart()
                .setSaveConsumer(v -> cfg.frostbitePotionRecipe = v)
                .build());

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
