package net.angelic.weaponsexpanded.client;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;

public final class ModModelPredicates {

    private static final ResourceLocation PULL =
            ResourceLocation.withDefaultNamespace("pull");

    private static final ResourceLocation PULLING =
            ResourceLocation.withDefaultNamespace("pulling");

    private static final ResourceLocation CHARGED =
            ResourceLocation.withDefaultNamespace("charged");

    private static final ResourceLocation FIREWORK =
            ResourceLocation.withDefaultNamespace("firework");

    private static final ResourceLocation SHARP_SIDE =
            ResourceLocation.fromNamespaceAndPath(
                    WeaponsExpanded.MOD_ID,
                    "sharp_side"
            );

    private static final ResourceLocation EXPLOSIVE =
            ResourceLocation.fromNamespaceAndPath(
                    WeaponsExpanded.MOD_ID,
                    "explosive"
            );

    private static final float LONGBOW_DRAW_TICKS = 32.0F;

    private ModModelPredicates() {
    }

    public static void register() {
        registerWarhammers();
        registerLongbow();
        registerChainCrossbow();

        // Adds the dynamite-arrow model predicate to vanilla crossbows.
        registerExplosiveProjectilePredicate(Items.CROSSBOW);
    }

    private static void registerWarhammers() {
        registerWarhammer(ModItems.WOODEN_WARHAMMER.get());
        registerWarhammer(ModItems.GOLDEN_WARHAMMER.get());
        registerWarhammer(ModItems.STONE_WARHAMMER.get());
        registerWarhammer(ModItems.IRON_WARHAMMER.get());
        registerWarhammer(ModItems.DIAMOND_WARHAMMER.get());
        registerWarhammer(ModItems.NETHERITE_WARHAMMER.get());
    }

    private static void registerWarhammer(Item item) {
        ItemProperties.register(
                item,
                SHARP_SIDE,
                (stack, level, entity, seed) -> {
                    if (stack.getItem()
                            instanceof WarhammerItem warhammer) {
                        return warhammer.isSharpSide(stack)
                                ? 1.0F
                                : 0.0F;
                    }

                    return 0.0F;
                }
        );
    }

    private static void registerLongbow() {
        ItemProperties.register(
                ModItems.LONGBOW.get(),
                PULLING,
                (stack, level, entity, seed) -> {
                    if (entity == null) {
                        return 0.0F;
                    }

                    return entity.isUsingItem()
                            && entity.getUseItem() == stack
                            ? 1.0F
                            : 0.0F;
                }
        );

        ItemProperties.register(
                ModItems.LONGBOW.get(),
                PULL,
                (stack, level, entity, seed) -> {
                    if (entity == null
                            || entity.getUseItem() != stack) {
                        return 0.0F;
                    }

                    return Math.min(
                            entity.getTicksUsingItem()
                                    / LONGBOW_DRAW_TICKS,
                            1.0F
                    );
                }
        );
    }

    private static void registerChainCrossbow() {
        ItemProperties.register(
                ModItems.CHAIN_CROSSBOW.get(),
                PULLING,
                (stack, level, entity, seed) -> {
                    if (entity == null) {
                        return 0.0F;
                    }

                    return entity.isUsingItem()
                            && entity.getUseItem() == stack
                            && !CrossbowItem.isCharged(stack)
                            ? 1.0F
                            : 0.0F;
                }
        );

        ItemProperties.register(
                ModItems.CHAIN_CROSSBOW.get(),
                PULL,
                (stack, level, entity, seed) -> {
                    if (entity == null
                            || entity.getUseItem() != stack) {
                        return 0.0F;
                    }

                    int pullTime =
                            CrossbowItem.getChargeDuration(
                                    stack,
                                    entity
                            );

                    if (pullTime <= 0) {
                        return 0.0F;
                    }

                    return Math.min(
                            (float) entity.getTicksUsingItem()
                                    / (float) pullTime,
                            1.0F
                    );
                }
        );

        ItemProperties.register(
                ModItems.CHAIN_CROSSBOW.get(),
                CHARGED,
                (stack, level, entity, seed) ->
                        CrossbowItem.isCharged(stack)
                                ? 1.0F
                                : 0.0F
        );

        ItemProperties.register(
                ModItems.CHAIN_CROSSBOW.get(),
                FIREWORK,
                (stack, level, entity, seed) ->
                        containsChargedProjectile(
                                stack,
                                Items.FIREWORK_ROCKET
                        ) ? 1.0F : 0.0F
        );

        registerExplosiveProjectilePredicate(
                ModItems.CHAIN_CROSSBOW.get()
        );
    }

    private static void registerExplosiveProjectilePredicate(
            Item crossbow
    ) {
        ItemProperties.register(
                crossbow,
                EXPLOSIVE,
                (stack, level, entity, seed) ->
                        containsChargedProjectile(
                                stack,
                                ModItems.EXPLOSIVE_ARROW.get()
                        ) ? 1.0F : 0.0F
        );
    }

    private static boolean containsChargedProjectile(
            ItemStack stack,
            Item projectile
    ) {
        ChargedProjectiles chargedProjectiles =
                stack.getOrDefault(
                        DataComponents.CHARGED_PROJECTILES,
                        ChargedProjectiles.EMPTY
                );

        return chargedProjectiles.contains(projectile);
    }
}