package net.angelic.weaponsexpanded.enchantment;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class ModEnchantments {
    private static ResourceKey<Enchantment> key(String path) {
        Identifier id = Identifier.fromNamespaceAndPath(WeaponsExpanded.MOD_ID, path);
        return ResourceKey.create(Registries.ENCHANTMENT, id);
    }

    public static final ResourceKey<Enchantment> POLLUTING = key("polluting");

    public static final ResourceKey<Enchantment> WITHERING = key("withering");

    public static final ResourceKey<Enchantment> FROSTBITE = key("frostbite");

    public static final ResourceKey<Enchantment> FREEZE = key("freeze");

    public static final ResourceKey<Enchantment> LEECH = key("leech");

    public static final ResourceKey<Enchantment> CLEAVING = key("cleaving");

    public static final ResourceKey<Enchantment> CAPACITY = key("capacity");
}
