package net.angelic.weaponsexpanded.client;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.item.custom.WarhammerItem;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public final class ModModelPredicates {

    private static final int LONGBOW_DRAW_TICKS = 32;

    private ModModelPredicates() {
    }

    public static void register() {
        registerLongbow();
        registerChainCrossbow();
        registerWarhammers();
        registerVanillaCrossbow();
    }

    private static void registerLongbow() {
        ModelPredicateProviderRegistry.register(
                ModItems.LONGBOW,
                Identifier.ofVanilla("pulling"),
                (stack, world, entity, seed) ->
                        entity != null
                                && entity.isUsingItem()
                                && entity.getActiveItem() == stack
                                ? 1.0F
                                : 0.0F
        );

        ModelPredicateProviderRegistry.register(
                ModItems.LONGBOW,
                Identifier.ofVanilla("pull"),
                (stack, world, entity, seed) -> {
                    if (entity == null || entity.getActiveItem() != stack) {
                        return 0.0F;
                    }

                    int elapsedTicks =
                            stack.getItem().getMaxUseTime(stack, entity)
                                    - entity.getItemUseTimeLeft();

                    return Math.min(
                            (float) elapsedTicks / LONGBOW_DRAW_TICKS,
                            1.0F
                    );
                }
        );
    }

    private static void registerChainCrossbow() {
        ModelPredicateProviderRegistry.register(
                ModItems.CHAIN_CROSSBOW,
                Identifier.ofVanilla("pulling"),
                (stack, world, entity, seed) ->
                        entity != null
                                && entity.isUsingItem()
                                && entity.getActiveItem() == stack
                                && !CrossbowItem.isCharged(stack)
                                ? 1.0F
                                : 0.0F
        );

        ModelPredicateProviderRegistry.register(
                ModItems.CHAIN_CROSSBOW,
                Identifier.ofVanilla("pull"),
                (stack, world, entity, seed) -> {
                    if (entity == null
                            || entity.getActiveItem() != stack
                            || CrossbowItem.isCharged(stack)) {
                        return 0.0F;
                    }

                    int maximumUseTime =
                            stack.getItem().getMaxUseTime(stack, entity);

                    int elapsedTicks =
                            maximumUseTime - entity.getItemUseTimeLeft();

                    int pullTime = Math.max(1, maximumUseTime - 3);

                    return Math.min(
                            (float) elapsedTicks / pullTime,
                            1.0F
                    );
                }
        );

        ModelPredicateProviderRegistry.register(
                ModItems.CHAIN_CROSSBOW,
                Identifier.ofVanilla("charged"),
                (stack, world, entity, seed) ->
                        CrossbowItem.isCharged(stack) ? 1.0F : 0.0F
        );

        ModelPredicateProviderRegistry.register(
                ModItems.CHAIN_CROSSBOW,
                Identifier.ofVanilla("firework"),
                (stack, world, entity, seed) ->
                        getProjectiles(stack).contains(Items.FIREWORK_ROCKET)
                                ? 1.0F
                                : 0.0F
        );

        ModelPredicateProviderRegistry.register(
                ModItems.CHAIN_CROSSBOW,
                Identifier.of(WeaponsExpanded.MOD_ID, "explosive"),
                (stack, world, entity, seed) ->
                        getProjectiles(stack).contains(ModItems.EXPLOSIVE_ARROW)
                                ? 1.0F
                                : 0.0F
        );
    }

    private static void registerWarhammers() {
        registerWarhammer(ModItems.WOODEN_WARHAMMER);
        registerWarhammer(ModItems.STONE_WARHAMMER);
        registerWarhammer(ModItems.IRON_WARHAMMER);
        registerWarhammer(ModItems.GOLDEN_WARHAMMER);
        registerWarhammer(ModItems.DIAMOND_WARHAMMER);
        registerWarhammer(ModItems.NETHERITE_WARHAMMER);
    }

    private static void registerWarhammer(Item item) {
        ModelPredicateProviderRegistry.register(
                item,
                Identifier.of(WeaponsExpanded.MOD_ID, "sharp_side"),
                (stack, world, entity, seed) -> {
                    if (stack.getItem() instanceof WarhammerItem warhammer
                            && warhammer.isSharpSide(stack)) {
                        return 1.0F;
                    }

                    return 0.0F;
                }
        );
    }

    private static void registerVanillaCrossbow() {
        ModelPredicateProviderRegistry.register(
                Items.CROSSBOW,
                Identifier.of(WeaponsExpanded.MOD_ID, "explosive"),
                (stack, world, entity, seed) ->
                        getProjectiles(stack).contains(ModItems.EXPLOSIVE_ARROW)
                                ? 1.0F
                                : 0.0F
        );
    }

    private static ChargedProjectilesComponent getProjectiles(
            net.minecraft.item.ItemStack stack
    ) {
        return stack.getOrDefault(
                DataComponentTypes.CHARGED_PROJECTILES,
                ChargedProjectilesComponent.DEFAULT
        );
    }
}