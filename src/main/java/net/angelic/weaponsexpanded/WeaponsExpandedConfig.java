package net.angelic.weaponsexpanded;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = WeaponsExpanded.MODID)

public class WeaponsExpandedConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue ENABLE_CUSTOM_LOOT_TABLES = BUILDER
            .comment("Allow custom items to spawn in loot chests")
            .define("enable_custom_loot_tables", true);

    private static final ModConfigSpec.BooleanValue ENABLE_ENTITY_MELEE_EQUIPMENT = BUILDER
            .comment("Allow mobs to spawn with custom weapons")
            .define("enable_entity_melee_equipment", true);

    private static final ModConfigSpec.BooleanValue ENABLE_TRIAL_CHAMBER_MELEE_EQUIPMENT = BUILDER
            .comment("Allow mobs to spawn with custom weapons in trial chambers")
            .define("enable_trial_chamber_melee_equipment", true);

    private static final ModConfigSpec.BooleanValue ALTERNATE_TWO_HANDED_SWORD_HANDLING = BUILDER
            .comment("Changes the way two-handed sword logic is handled")
            .define("alternate_two_handed_sword", false);

    private static final ModConfigSpec.BooleanValue DISABLE_EXTRA_AXE_DAMAGE = BUILDER
            .comment("Disables the extra durability damage on axes")
            .define("disable_extra_axe_damage", true);

    private static final ModConfigSpec.BooleanValue DYNAMITE_ARROWS_DESTROY_BLOCKS = BUILDER
            .comment("Determines if Dynamite Arrows destroy blocks")
            .define("dynamite_arrows_destroy_blocks", true);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean lootTables;
    public static boolean meleeEquip;
    public static boolean trialEquip;
    public static boolean twohandedSword;
    public static boolean axeDamage;
    public static boolean dynamiteArrow;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        lootTables = ENABLE_CUSTOM_LOOT_TABLES.get();
        meleeEquip = ENABLE_ENTITY_MELEE_EQUIPMENT.get();
        trialEquip = ENABLE_TRIAL_CHAMBER_MELEE_EQUIPMENT.get();
        twohandedSword = ALTERNATE_TWO_HANDED_SWORD_HANDLING.get();
        axeDamage = DISABLE_EXTRA_AXE_DAMAGE.get();
        dynamiteArrow = DYNAMITE_ARROWS_DESTROY_BLOCKS.get();
    }

    @SubscribeEvent
    static void onConfigChanged(final ModConfigEvent event) {
        if (event.getConfig().getSpec() != SPEC) {
            return;
        }

        lootTables = ENABLE_CUSTOM_LOOT_TABLES.get();
        meleeEquip = ENABLE_ENTITY_MELEE_EQUIPMENT.get();
        trialEquip = ENABLE_TRIAL_CHAMBER_MELEE_EQUIPMENT.get();
        twohandedSword = ALTERNATE_TWO_HANDED_SWORD_HANDLING.get();
        axeDamage = DISABLE_EXTRA_AXE_DAMAGE.get();
        dynamiteArrow = DYNAMITE_ARROWS_DESTROY_BLOCKS.get();
    }
}
