package net.angelic.weaponsexpanded.client;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public final class ModModelPredicates {

    private static final Identifier PULL =
            new Identifier("minecraft", "pull");

    private static final Identifier PULLING =
            new Identifier("minecraft", "pulling");

    private static final Identifier CHARGED =
            new Identifier("minecraft", "charged");

    private static final Identifier FIREWORK =
            new Identifier("minecraft", "firework");

    private static final Identifier SHARP_SIDE =
            new Identifier(WeaponsExpanded.MOD_ID, "sharp_side");

    private static final Identifier EXPLOSIVE =
            new Identifier(WeaponsExpanded.MOD_ID, "explosive");

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
        registerWarhammer(ModItems.WOODEN_WARHAMMER);
        registerWarhammer(ModItems.GOLDEN_WARHAMMER);
        registerWarhammer(ModItems.STONE_WARHAMMER);
        registerWarhammer(ModItems.IRON_WARHAMMER);
        registerWarhammer(ModItems.DIAMOND_WARHAMMER);
        registerWarhammer(ModItems.NETHERITE_WARHAMMER);
    }

    private static void registerWarhammer(Item item) {
        ModelPredicateProviderRegistry.register(
                item,
                SHARP_SIDE,
                (stack, world, entity, seed) -> {
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
        ModelPredicateProviderRegistry.register(
                ModItems.LONGBOW,
                PULLING,
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0F;
                    }

                    return entity.isUsingItem()
                            && entity.getActiveItem() == stack
                            ? 1.0F
                            : 0.0F;
                }
        );

        ModelPredicateProviderRegistry.register(
                ModItems.LONGBOW,
                PULL,
                (stack, world, entity, seed) -> {
                    if (entity == null
                            || entity.getActiveItem() != stack) {
                        return 0.0F;
                    }

                    return Math.min(
                            entity.getItemUseTime()
                                    / LONGBOW_DRAW_TICKS,
                            1.0F
                    );
                }
        );
    }

    private static void registerChainCrossbow() {
        ModelPredicateProviderRegistry.register(
                ModItems.CHAIN_CROSSBOW,
                PULLING,
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0F;
                    }

                    return entity.isUsingItem()
                            && entity.getActiveItem() == stack
                            && !CrossbowItem.isCharged(stack)
                            ? 1.0F
                            : 0.0F;
                }
        );

        ModelPredicateProviderRegistry.register(
                ModItems.CHAIN_CROSSBOW,
                PULL,
                (stack, world, entity, seed) -> {
                    if (entity == null
                            || entity.getActiveItem() != stack) {
                        return 0.0F;
                    }

                    int pullTime = CrossbowItem.getPullTime(stack);

                    if (pullTime <= 0) {
                        return 0.0F;
                    }

                    return Math.min(
                            (float) entity.getItemUseTime()
                                    / (float) pullTime,
                            1.0F
                    );
                }
        );

        ModelPredicateProviderRegistry.register(
                ModItems.CHAIN_CROSSBOW,
                CHARGED,
                (stack, world, entity, seed) ->
                        CrossbowItem.isCharged(stack)
                                ? 1.0F
                                : 0.0F
        );

        ModelPredicateProviderRegistry.register(
                ModItems.CHAIN_CROSSBOW,
                FIREWORK,
                (stack, world, entity, seed) ->
                        CrossbowItem.hasProjectile(
                                stack,
                                Items.FIREWORK_ROCKET
                        ) ? 1.0F : 0.0F
        );

        registerExplosiveProjectilePredicate(
                ModItems.CHAIN_CROSSBOW
        );
    }

    private static void registerExplosiveProjectilePredicate(
            Item crossbow
    ) {
        ModelPredicateProviderRegistry.register(
                crossbow,
                EXPLOSIVE,
                (stack, world, entity, seed) ->
                        CrossbowItem.hasProjectile(
                                stack,
                                ModItems.EXPLOSIVE_ARROW
                        ) ? 1.0F : 0.0F
        );
    }
}