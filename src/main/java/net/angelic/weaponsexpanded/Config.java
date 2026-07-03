package net.angelic.weaponsexpanded;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Forge's config APIs
@Mod.EventBusSubscriber(modid = WeaponsExpanded.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.BooleanValue ENABLE_CUSTOM_LOOT_TABLES = BUILDER
            .comment("Allow custom items to spawn in loot chests")
            .define("enable_custom_loot_tables", true);

    private static final ForgeConfigSpec.BooleanValue ENABLE_ENTITY_MELEE_EQUIPMENT = BUILDER
            .comment("Allow mobs to spawn with custom weapons")
            .define("enable_entity_melee_equipment", true);

    private static final ForgeConfigSpec.BooleanValue ENABLE_TRIAL_CHAMBER_MELEE_EQUIPMENT = BUILDER
            .comment("Allow mobs to spawn with custom weapons in trial chambers")
            .define("enable_trial_chamber_melee_equipment", true);

    private static final ForgeConfigSpec.BooleanValue ALTERNATE_TWO_HANDED_SWORD_HANDLING = BUILDER
            .comment("Changes the way two-handed sword logic is handled")
            .define("alternate_two_handed_sword", false);

    private static final ForgeConfigSpec.BooleanValue DISABLE_EXTRA_AXE_DAMAGE = BUILDER
            .comment("Disables the extra durability damage on axes")
            .define("disable_extra_axe_damage", true);

    static final ForgeConfigSpec SPEC = BUILDER.build();

    public static boolean lootTables;
    public static boolean meleeEquip;
    public static boolean trialEquip;
    public static boolean twohandedSword;
    public static boolean axeDamage;

    private static boolean validateItemName(final Object obj) {
        return obj instanceof final String itemName && ForgeRegistries.ITEMS.containsKey(Identifier.tryParse(itemName));
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        lootTables = ENABLE_CUSTOM_LOOT_TABLES.get();
        meleeEquip = ENABLE_ENTITY_MELEE_EQUIPMENT.get();
        trialEquip = ENABLE_TRIAL_CHAMBER_MELEE_EQUIPMENT.get();
        twohandedSword = ALTERNATE_TWO_HANDED_SWORD_HANDLING.get();
        axeDamage = DISABLE_EXTRA_AXE_DAMAGE.get();
    }
}
