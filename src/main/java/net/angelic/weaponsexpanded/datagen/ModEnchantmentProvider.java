package net.angelic.weaponsexpanded.datagen;

import net.angelic.weaponsexpanded.enchantment.ModEnchantments;
import net.angelic.weaponsexpanded.enchantment.effect.FreezeEnchantmentEffect;
import net.angelic.weaponsexpanded.enchantment.effect.FrostbiteEnchantmentEffect;
import net.angelic.weaponsexpanded.enchantment.effect.PollutingEnchantmentEffect;
import net.angelic.weaponsexpanded.enchantment.effect.WitheringEnchantmentEffect;
import net.angelic.weaponsexpanded.util.ModEnchantmentTags;
import net.angelic.weaponsexpanded.util.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;

import java.util.concurrent.CompletableFuture;

public class ModEnchantmentProvider extends FabricDynamicRegistryProvider {
    public ModEnchantmentProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.identifier()));
    }

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        register(context, ModEnchantments.POLLUTING,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        context.lookup(Registries.ITEM).getOrThrow(ItemTags.MELEE_WEAPON_ENCHANTABLE),
                                        2,
                                        2,
                                        Enchantment.dynamicCost(10, 20),
                                        Enchantment.dynamicCost(60, 20),
                                        4,
                                        EquipmentSlotGroup.MAINHAND
                                )
                        )
                        .withEffect(
                                EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM,
                                new PollutingEnchantmentEffect()
                        )
                        .exclusiveWith(context.lookup(Registries.ENCHANTMENT).getOrThrow(ModEnchantmentTags.POST_ATTACK_EXCLUSIVE_SET)));

        register(context, ModEnchantments.WITHERING,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        context.lookup(Registries.ITEM).getOrThrow(ItemTags.MELEE_WEAPON_ENCHANTABLE),
                                        2,
                                        2,
                                        Enchantment.dynamicCost(10, 20),
                                        Enchantment.dynamicCost(60, 20),
                                        4,
                                        EquipmentSlotGroup.MAINHAND
                                )
                        )
                        .withEffect(
                                EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM,
                                new WitheringEnchantmentEffect()
                        )
                        .exclusiveWith(context.lookup(Registries.ENCHANTMENT).getOrThrow(ModEnchantmentTags.POST_ATTACK_EXCLUSIVE_SET)));

        register(context, ModEnchantments.FROSTBITE,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        context.lookup(Registries.ITEM).getOrThrow(ItemTags.MELEE_WEAPON_ENCHANTABLE),
                                        2,
                                        2,
                                        Enchantment.dynamicCost(10, 20),
                                        Enchantment.dynamicCost(60, 20),
                                        4,
                                        EquipmentSlotGroup.MAINHAND
                                )
                        )
                        .withEffect(
                                EnchantmentEffectComponents.POST_ATTACK,
                                EnchantmentTarget.ATTACKER,
                                EnchantmentTarget.VICTIM,
                                new FrostbiteEnchantmentEffect()
                        )
                        .exclusiveWith(context.lookup(Registries.ENCHANTMENT).getOrThrow(ModEnchantmentTags.POST_ATTACK_EXCLUSIVE_SET)));

        register(context, ModEnchantments.FREEZE,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        context.lookup(Registries.ITEM).getOrThrow(ItemTags.BOW_ENCHANTABLE),
                                        2,
                                        1,
                                        Enchantment.dynamicCost(20, 0),
                                        Enchantment.dynamicCost(50, 0),
                                        4,
                                        EquipmentSlotGroup.HAND
                                )
                        )
                        .withEffect(
                                EnchantmentEffectComponents.PROJECTILE_SPAWNED,
                                new FreezeEnchantmentEffect()
                        )
                        .exclusiveWith(context.lookup(Registries.ENCHANTMENT).getOrThrow(ModEnchantmentTags.BOW_EFFECTS_EXCLUSIVE_SET)));

        register(context, ModEnchantments.LEECH,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        context.lookup(Registries.ITEM).getOrThrow(ModItemTags.LEECH_ENCHANTABLE),
                                        2,
                                        1,
                                        Enchantment.dynamicCost(10, 20),
                                        Enchantment.dynamicCost(60, 20),
                                        4,
                                        EquipmentSlotGroup.HAND
                                )
                        ));

        register(context, ModEnchantments.CLEAVING,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        context.lookup(Registries.ITEM).getOrThrow(ModItemTags.CLEAVING_ENCHANTABLE),
                                        2,
                                        3,
                                        Enchantment.dynamicCost(20, 9),
                                        Enchantment.dynamicCost(5, 9),
                                        4,
                                        EquipmentSlotGroup.MAINHAND
                                )
                        ));

        register(context, ModEnchantments.CAPACITY,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        context.lookup(Registries.ITEM).getOrThrow(ModItemTags.CAPACITY_ENCHANTABLE),
                                        4,
                                        2,
                                        Enchantment.dynamicCost(50, 0),
                                        Enchantment.dynamicCost(12, 20),
                                        4,
                                        EquipmentSlotGroup.HAND
                                )
                        ));
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.ENCHANTMENT));
    }

    @Override
    public String getName() {
        return "Weapons Expanded Enchantments";
    }
}
