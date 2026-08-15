package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.tags.EnchantmentTags;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentTagProvider
        extends EnchantmentTagsProvider {

    public ModEnchantmentTagProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(
                output,
                lookupProvider,
                WeaponsExpanded.MOD_ID,
                existingFileHelper
        );
    }

    @Override
    protected void addTags(
            HolderLookup.@NotNull Provider provider
    ) {
        tag(EnchantmentTags.IN_ENCHANTING_TABLE)
                .add(
                        ModEnchantments.WITHERING,
                        ModEnchantments.POLLUTING,
                        ModEnchantments.FROSTBITE,
                        ModEnchantments.FREEZE,
                        ModEnchantments.CLEAVING
                );

        tag(EnchantmentTags.NON_TREASURE)
                .add(
                        ModEnchantments.WITHERING,
                        ModEnchantments.POLLUTING,
                        ModEnchantments.FROSTBITE,
                        ModEnchantments.FREEZE,
                        ModEnchantments.CLEAVING
                );

        tag(EnchantmentTags.TRADEABLE)
                .add(
                        ModEnchantments.WITHERING,
                        ModEnchantments.POLLUTING,
                        ModEnchantments.FROSTBITE,
                        ModEnchantments.FREEZE,
                        ModEnchantments.LEECH,
                        ModEnchantments.CLEAVING
                );

        tag(EnchantmentTags.TREASURE)
                .add(
                        ModEnchantments.LEECH
                );
    }
}