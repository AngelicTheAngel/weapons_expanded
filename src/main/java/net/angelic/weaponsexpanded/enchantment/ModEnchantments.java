package net.angelic.weaponsexpanded.enchantment;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.util.ModItemTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.ApplyMobEffect;

public final class ModEnchantments {

    /*
     * Old Rarity.RARE enchantments had weight 2.
     */
    private static final int RARE_WEIGHT = 2;
    private static final int DEFAULT_ANVIL_COST = 4;

    public static final ResourceKey<Enchantment> WITHERING =
            createKey("withering");

    public static final ResourceKey<Enchantment> POLLUTING =
            createKey("polluting");

    public static final ResourceKey<Enchantment> FROSTBITE =
            createKey("frostbite");

    public static final ResourceKey<Enchantment> FREEZE =
            createKey("freeze");

    public static final ResourceKey<Enchantment> LEECH =
            createKey("leech");

    public static final ResourceKey<Enchantment> CLEAVING =
            createKey("cleaving");

    private static ResourceKey<Enchantment> createKey(String path) {
        return ResourceKey.create(
                Registries.ENCHANTMENT,
                Identifier.fromNamespaceAndPath(
                        WeaponsExpanded.MOD_ID,
                        path
                )
        );
    }

    public static void bootstrap(
            BootstrapContext<Enchantment> context
    ) {
        HolderGetter<Item> items =
                context.lookup(Registries.ITEM);

        HolderGetter<Enchantment> enchantments =
                context.lookup(Registries.ENCHANTMENT);

        HolderGetter<MobEffect> effects =
                context.lookup(Registries.MOB_EFFECT);

        register(
                context,
                CLEAVING,
                createBuilder(
                        items.getOrThrow(
                                ModItemTags.CLEAVING_ENCHANTABLE
                        ),
                        3,
                        Enchantment.dynamicCost(11, 10),
                        Enchantment.dynamicCost(16, 10),
                        EquipmentSlotGroup.HAND
                )
        );

        register(
                context,
                FREEZE,
                createBuilder(
                        items.getOrThrow(
                                ItemTags.BOW_ENCHANTABLE
                        ),
                        1,
                        Enchantment.dynamicCost(11, 10),
                        Enchantment.dynamicCost(16, 10),
                        EquipmentSlotGroup.HAND
                ).exclusiveWith(
                        HolderSet.direct(
                                enchantments.getOrThrow(
                                        Enchantments.FLAME
                                )
                        )
                )
        );

        register(
                context,
                LEECH,
                createBuilder(
                        items.getOrThrow(
                                ModItemTags.LEECH_ENCHANTABLE
                        ),
                        1,
                        Enchantment.dynamicCost(11, 10),
                        Enchantment.dynamicCost(16, 10),
                        EquipmentSlotGroup.HAND
                )
        );

        /*
         * Old Frostbite duration:
         *
         * Level I:  80 ticks = 4 seconds
         * Level II: 120 ticks = 6 seconds
         */
        LevelBasedValue frostbiteDuration =
                LevelBasedValue.perLevel(4.0F, 2.0F);

        ResourceKey<MobEffect> frostbiteEffectKey =
                ResourceKey.create(
                        Registries.MOB_EFFECT,
                        Identifier.fromNamespaceAndPath(
                                WeaponsExpanded.MOD_ID,
                                "frostbite"
                        )
                );

        register(
                context,
                FROSTBITE,
                createMeleeEffectBuilder(
                        items,
                        enchantments,
                        POLLUTING,
                        WITHERING
                ).withEffect(
                        EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM,
                        new ApplyMobEffect(
                                HolderSet.direct(
                                        effects.getOrThrow(
                                                frostbiteEffectKey
                                        )
                                ),
                                frostbiteDuration,
                                frostbiteDuration,
                                LevelBasedValue.constant(0.0F),
                                LevelBasedValue.constant(0.0F)
                        )
                )
        );

        /*
         * Old Polluting duration:
         *
         * Level I:  160 ticks = 8 seconds
         * Level II: 300 ticks = 15 seconds
         */
        LevelBasedValue poisonDuration =
                LevelBasedValue.perLevel(8.0F, 7.0F);

        register(
                context,
                POLLUTING,
                createMeleeEffectBuilder(
                        items,
                        enchantments,
                        FROSTBITE,
                        WITHERING
                ).withEffect(
                        EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM,
                        new ApplyMobEffect(
                                HolderSet.direct(MobEffects.POISON),
                                poisonDuration,
                                poisonDuration,
                                LevelBasedValue.constant(0.0F),
                                LevelBasedValue.constant(0.0F)
                        )
                )
        );

        /*
         * Old Withering duration:
         *
         * Level I:  100 ticks = 5 seconds
         * Level II: 160 ticks = 8 seconds
         *
         * Amplifier 1 means Wither II.
         */
        LevelBasedValue witherDuration =
                LevelBasedValue.perLevel(5.0F, 3.0F);

        register(
                context,
                WITHERING,
                createMeleeEffectBuilder(
                        items,
                        enchantments,
                        FROSTBITE,
                        POLLUTING
                ).withEffect(
                        EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM,
                        new ApplyMobEffect(
                                HolderSet.direct(MobEffects.WITHER),
                                witherDuration,
                                witherDuration,
                                LevelBasedValue.constant(1.0F),
                                LevelBasedValue.constant(1.0F)
                        )
                )
        );
    }

    private static Enchantment.Builder createMeleeEffectBuilder(
            HolderGetter<Item> items,
            HolderGetter<Enchantment> enchantments,
            ResourceKey<Enchantment> firstCustomConflict,
            ResourceKey<Enchantment> secondCustomConflict
    ) {
        return createBuilder(
                items.getOrThrow(
                        ItemTags.MELEE_WEAPON_ENCHANTABLE
                ),
                2,
                Enchantment.dynamicCost(10, 20),
                Enchantment.dynamicCost(60, 20),
                EquipmentSlotGroup.MAINHAND
        ).exclusiveWith(
                HolderSet.direct(
                        enchantments.getOrThrow(
                                Enchantments.FIRE_ASPECT
                        ),
                        enchantments.getOrThrow(
                                firstCustomConflict
                        ),
                        enchantments.getOrThrow(
                                secondCustomConflict
                        )
                )
        );
    }

    private static Enchantment.Builder createBuilder(
            HolderSet<Item> supportedItems,
            int maxLevel,
            Enchantment.Cost minimumCost,
            Enchantment.Cost maximumCost,
            EquipmentSlotGroup... slots
    ) {
        return Enchantment.enchantment(
                Enchantment.definition(
                        supportedItems,
                        RARE_WEIGHT,
                        maxLevel,
                        minimumCost,
                        maximumCost,
                        DEFAULT_ANVIL_COST,
                        slots
                )
        );
    }

    private static void register(
            BootstrapContext<Enchantment> context,
            ResourceKey<Enchantment> key,
            Enchantment.Builder builder
    ) {
        context.register(
                key,
                builder.build(key.identifier())
        );
    }

    private ModEnchantments() {
    }
}