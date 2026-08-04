package net.angelic.weaponsexpanded.enchantment;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.enchantment.custom.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS =
            DeferredRegister.create(
                    ForgeRegistries.ENCHANTMENTS,
                    WeaponsExpanded.MOD_ID
            );

    public static final RegistryObject<Enchantment> WITHERING =
            ENCHANTMENTS.register(
                    "withering",
                    WitheringEnchantment::new
            );

    public static final RegistryObject<Enchantment> POLLUTING =
            ENCHANTMENTS.register(
                    "polluting",
                    PollutingEnchantment::new
            );

    public static final RegistryObject<Enchantment> FROSTBITE =
            ENCHANTMENTS.register(
                    "frostbite",
                    FrostbiteEnchantment::new
            );

    public static final RegistryObject<Enchantment> FREEZE =
            ENCHANTMENTS.register(
                    "freeze",
                    FreezeEnchantment::new
            );

    public static final RegistryObject<Enchantment> LEECH =
            ENCHANTMENTS.register(
                    "leech",
                    LeechEnchantment::new
            );

    public static final RegistryObject<Enchantment> CLEAVING =
            ENCHANTMENTS.register(
                    "cleaving",
                    CleavingEnchantment::new
            );

    public static void registerEnchantments(IEventBus modEventBus) {
        ENCHANTMENTS.register(modEventBus);

        WeaponsExpanded.LOGGER.info(
                "Registering enchantments for {}",
                WeaponsExpanded.MOD_ID
        );
    }

    private ModEnchantments() {}
}