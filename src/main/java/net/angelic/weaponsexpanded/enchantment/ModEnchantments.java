package net.angelic.weaponsexpanded.enchantment;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.enchantment.custom.*;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModEnchantments {
    public static final Enchantment WITHERING =
            register("withering", new WitheringEnchantment());

    public static final Enchantment POLLUTING =
            register("polluting", new PollutingEnchantment());

    public static final Enchantment FROSTBITE =
            register("frostbite", new FrostbiteEnchantment());

    public static final Enchantment FREEZE =
            register("freeze", new FreezeEnchantment());

    public static final Enchantment LEECH =
            register("leech", new LeechEnchantment());

    public static final Enchantment CLEAVING =
            register("cleaving", new CleavingEnchantment());

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(
                Registries.ENCHANTMENT,
                new Identifier(WeaponsExpanded.MOD_ID, name),
                enchantment
        );
    }

    public static void registerEnchantments() {
        WeaponsExpanded.LOGGER.info(
                "Registering enchantments for {}",
                WeaponsExpanded.MOD_ID
        );
    }

    private ModEnchantments() {}
}