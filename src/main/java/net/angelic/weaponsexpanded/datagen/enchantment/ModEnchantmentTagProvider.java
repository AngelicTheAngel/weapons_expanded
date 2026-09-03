package net.angelic.weaponsexpanded.datagen.enchantment;

import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.angelic.weaponsexpanded.util.ModEnchantmentTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagProvider extends FabricTagsProvider<Enchantment> {
    public ModEnchantmentTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, Registries.ENCHANTMENT, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        builder(EnchantmentTags.TRADEABLE)
                .add(ModEnchantments.LEECH);

        builder(EnchantmentTags.TREASURE)
                .add(ModEnchantments.LEECH);

        builder(EnchantmentTags.NON_TREASURE)
                .add(ModEnchantments.POLLUTING)
                .add(ModEnchantments.WITHERING)
                .add(ModEnchantments.FROSTBITE)
                .add(ModEnchantments.FREEZE)
                .add(ModEnchantments.CLEAVING)
                .add(ModEnchantments.CAPACITY);

        builder(ModEnchantmentTags.POST_ATTACK_EXCLUSIVE_SET)
                .add(Enchantments.FIRE_ASPECT)
                .add(ModEnchantments.POLLUTING)
                .add(ModEnchantments.WITHERING)
                .add(ModEnchantments.FROSTBITE);

        builder(ModEnchantmentTags.BOW_EFFECTS_EXCLUSIVE_SET)
                .add(Enchantments.FLAME)
                .add(ModEnchantments.FREEZE);
    }
}
