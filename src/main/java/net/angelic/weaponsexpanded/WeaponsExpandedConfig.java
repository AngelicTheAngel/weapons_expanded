package net.angelic.weaponsexpanded;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class WeaponsExpandedConfig {
  public static final ModConfigSpec SPEC;

  public static final ModConfigSpec.BooleanValue ENABLE_CUSTOM_LOOT_TABLES;
  public static final ModConfigSpec.BooleanValue ENABLE_ENTITY_MELEE_EQUIPMENT;
  public static final ModConfigSpec.BooleanValue ENABLE_TRIAL_CHAMBER_MELEE_EQUIPMENT;
  public static final ModConfigSpec.BooleanValue ENABLE_WEAPONSMITH_TRADES;
  public static final ModConfigSpec.BooleanValue ALT_TWO_HANDED_SWORD_HANDLING;
  public static final ModConfigSpec.BooleanValue DISABLE_EXTRA_DURABILITY_DAMAGE_FOR_AXES;

  static {
    ModConfigSpec.Builder builder =
            new ModConfigSpec.Builder();

    builder.push("general");

    ENABLE_CUSTOM_LOOT_TABLES = builder
            .comment("Allows custom weapons to appear in loot chests.")
            .translation("config.weaponsexpanded.option.enableCustomLootTables")
            .define("enableCustomLootTables", true);

    ENABLE_ENTITY_MELEE_EQUIPMENT = builder
            .comment("Allows entities to spawn with custom weapons.")
            .translation("config.weaponsexpanded.option.enableEntityMeleeEquipment")
            .define("enableEntityMeleeEquipment", true);

    ENABLE_TRIAL_CHAMBER_MELEE_EQUIPMENT = builder
            .comment("Allows entities in Trial Chambers to spawn with custom weapons.")
            .translation("config.weaponsexpanded.option.enableTrialChamberMeleeEquipment")
            .define("enableTrialChamberMeleeEquipment", true);

    ENABLE_WEAPONSMITH_TRADES = builder
            .comment("Enables custom weaponsmith trades.")
            .translation("config.weaponsexpanded.option.enableWeaponsmithTrades")
            .define("enableWeaponsmithTrades", true);

    ALT_TWO_HANDED_SWORD_HANDLING = builder
            .comment("Enables alternate two-handed sword handling.")
            .translation("config.weaponsexpanded.option.altTwoHandedSwordHandling")
            .define("altTwoHandedSwordHandling", false);

    DISABLE_EXTRA_DURABILITY_DAMAGE_FOR_AXES = builder
            .comment("Prevents axes from taking extra durability.")
            .translation("config.weaponsexpanded.option.disableExtraDurabilityDamageForAxes")
            .define("disableExtraDurabilityDamageForAxes", true);

    builder.pop();
    SPEC = builder.build();
  }

  private WeaponsExpandedConfig() {
  }
}