package net.angelic.weaponsexpanded.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.Requirement;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class WeaponsExpandedConfigScreen {
    private WeaponsExpandedConfigScreen() {}

    @SuppressWarnings("UnstableApiUsage")
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
                .setTooltip(Component.translatable("config.weaponsexpanded.option.enableCustomLootTables.description"))
                .setSaveConsumer(v -> cfg.enableCustomLootTables = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.dynamiteArrowsDestroyBlocks"), cfg.dynamiteArrowsDestroyBlocks)
                .setDefaultValue(true)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.dynamiteArrowsDestroyBlocks.description"))
                .setSaveConsumer(v -> cfg.dynamiteArrowsDestroyBlocks = v)
                .build());

        var extraDamageEnchantsEntry = eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.enableExtraDamageEnchants"), cfg.enableExtraDamageEnchants)
                .setDefaultValue(true)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.enableExtraDamageEnchants.description"))
                .requireRestart()
                .setSaveConsumer(v -> cfg.enableExtraDamageEnchants = v)
                .build();

        general.addEntry(extraDamageEnchantsEntry);

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.enableExtraDamageEnchantsVillager"), cfg.enableExtraDamageEnchantsVillager)
                .setDefaultValue(false)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.enableExtraDamageEnchantsVillager.description"))
                .setRequirement(Requirement.isFalse(extraDamageEnchantsEntry))
                .requireRestart()
                .setSaveConsumer(v -> cfg.enableExtraDamageEnchantsVillager = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.enableExtraDamageEnchantsLoot"), cfg.enableExtraDamageEnchantsLoot)
                .setDefaultValue(false)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.enableExtraDamageEnchantsLoot.description"))
                .setRequirement(Requirement.isFalse(extraDamageEnchantsEntry))
                .requireRestart()
                .setSaveConsumer(v -> cfg.enableExtraDamageEnchantsLoot = v)
                .build());

        general.addEntry(eb.startIntSlider(Component.translatable("config.weaponsexpanded.option.chainCrossbowMagazineSize"), cfg.chainCrossbowMagazineSize, 1, 10)
                .setDefaultValue(3)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.chainCrossbowMagazineSize.description"))
                .setSaveConsumer(v -> cfg.chainCrossbowMagazineSize = v)
                .build());

        general.addEntry(eb.startIntSlider(Component.translatable("config.weaponsexpanded.option.chainCrossbowCooldown"), cfg.chainCrossbowCooldown, 0, 20)
                .setDefaultValue(8)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.chainCrossbowCooldown.description"))
                .setSaveConsumer(v -> cfg.chainCrossbowCooldown = v)
                .build());

        general.addEntry(eb.startIntSlider(Component.translatable("config.weaponsexpanded.option.chainCrossbowLoadTime"), cfg.chainCrossbowLoadTime, 1, 50)
                .setDefaultValue(38)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.chainCrossbowLoadTime.description"))
                .setSaveConsumer(v -> cfg.chainCrossbowLoadTime = v)
                .build());

        general.addEntry(eb.startIntSlider(Component.translatable("config.weaponsexpanded.option.extraSizePerCapacityLevel"), cfg.chainCrossbowExtraSizePerCapacityLevel, 1, 3)
                .setDefaultValue(1)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.extraSizePerCapacityLevel.description"))
                .setSaveConsumer(v -> cfg.chainCrossbowExtraSizePerCapacityLevel = v)
                .build());

        general.addEntry(eb.startIntSlider(Component.translatable("config.weaponsexpanded.option.dynamiteArrowDurabilityDamage"), cfg.dynamiteArrowDurabilityDamage, 1, 5)
                .setDefaultValue(4)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.dynamiteArrowDurabilityDamage.description"))
                .setSaveConsumer(v -> cfg.dynamiteArrowDurabilityDamage = v)
                .build());

        general.addEntry(eb.startIntSlider(Component.translatable("config.weaponsexpanded.option.autoLoadFiringDelay"), cfg.autoLoadFiringDelay, 0, 50)
                .setDefaultValue(20)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.autoLoadFiringDelay.description"))
                .setSaveConsumer(v -> cfg.autoLoadFiringDelay = v)
                .build());

        general.addEntry(eb.startFloatField(Component.translatable("config.weaponsexpanded.option.autoLoadChargeMultiplier"), cfg.autoLoadChargeMultiplier)
                .setDefaultValue(2)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.autoLoadChargeMultiplier.description"))
                .setSaveConsumer(v -> cfg.autoLoadChargeMultiplier = v)
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

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.enableExtraNetherEntities"), cfg.enableExtraNetherEntities)
                .setDefaultValue(false)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.enableExtraNetherEntities.description"))
                .requireRestart()
                .setSaveConsumer(v -> cfg.enableExtraNetherEntities = v)
                .build());

//        general.addEntry(eb.startIntSlider(Component.translatable("config.weaponsexpanded.option.ritualDaggerMaxLevel"), cfg.ritualDaggerMaxLevel, 1, 4)
//                .setDefaultValue(2)
//                .setSaveConsumer(v -> cfg.ritualDaggerMaxLevel = v)
//                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.enableWeaponsmithTrades"), cfg.enableWeaponsmithTrades)
                .setDefaultValue(true)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.enableWeaponsmithTrades.description"))
                .requireRestart()
                .setSaveConsumer(v -> cfg.enableWeaponsmithTrades = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.enableFletcherTrades"), cfg.enableFletcherTrades)
                .setDefaultValue(true)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.enableFletcherTrades.description"))
                .requireRestart()
                .setSaveConsumer(v -> cfg.enableFletcherTrades = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.frostbitePotionRecipe"), cfg.frostbitePotionRecipe)
                .setDefaultValue(true)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.frostbitePotionRecipe.description"))
                .requireRestart()
                .setSaveConsumer(v -> cfg.frostbitePotionRecipe = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.altTwoHandedSwordHandling"), cfg.altTwoHandedSwordHandling)
                .setDefaultValue(false)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.altTwoHandedSwordHandling.description"))
                .setSaveConsumer(v -> cfg.altTwoHandedSwordHandling = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.weaponModeSwapSetsShieldCooldown"), cfg.weaponModeSwapSetsShieldCooldown)
                .setDefaultValue(false)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.weaponModeSwapSetsShieldCooldown.description"))
                .setSaveConsumer(v -> cfg.weaponModeSwapSetsShieldCooldown = v)
                .build());

        general.addEntry(eb.startBooleanToggle(Component.translatable("config.weaponsexpanded.option.disableExtraDurabilityDamageForAxes"), cfg.disableExtraDurabilityDamageForAxes)
                .setDefaultValue(true)
                .setTooltip(Component.translatable("config.weaponsexpanded.option.disableExtraDurabilityDamageForAxes.description"))
                .setSaveConsumer(v -> cfg.disableExtraDurabilityDamageForAxes = v)
                .build());

        return builder.build();
    }
}
