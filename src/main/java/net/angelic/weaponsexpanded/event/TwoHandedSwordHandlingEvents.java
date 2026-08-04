package net.angelic.weaponsexpanded.event;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = WeaponsExpanded.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.FORGE
)
public final class TwoHandedSwordHandlingEvents {

    private TwoHandedSwordHandlingEvents() {
    }

    @SubscribeEvent
    public static void weaponsexpanded$twoHandedSwordTick(
            TickEvent.PlayerTickEvent event
    ) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }

        Player player = event.player;

        ItemStack mainHandStack =
                player.getMainHandItem();

        if (!weaponsexpanded$isEffectivelyTwoHanded(
                mainHandStack
        )) {
            return;
        }

        if (!WeaponsExpandedConfig
                .ALT_TWO_HANDED_SWORD_HANDLING
                .get()) {
            /*
             * Stop any active use action. This prevents the
             * player from continuing to use an offhand item.
             */
            if (player.isUsingItem()) {
                player.stopUsingItem();
            }

            return;
        }

        /*
         * Inventory changes must only happen on the server.
         */
        if (player.level().isClientSide) {
            return;
        }

        ItemStack offhandStack =
                player.getOffhandItem();

        if (offhandStack.isEmpty()) {
            return;
        }

        /*
         * Clear the offhand before attempting to move its item
         * into the inventory.
         */
        player.setItemInHand(
                InteractionHand.OFF_HAND,
                ItemStack.EMPTY
        );

        player.getInventory().add(offhandStack);

        /*
         * Drop anything that did not fit in the inventory.
         */
        if (!offhandStack.isEmpty()) {
            player.drop(
                    offhandStack,
                    false
            );
        }
    }

    private static boolean
    weaponsexpanded$isEffectivelyTwoHanded(
            ItemStack mainHandStack
    ) {
        if (mainHandStack.getItem()
                instanceof TwoHandedSwordItem) {
            return true;
        }

        if (mainHandStack.getItem()
                instanceof BastardSwordItem bastardSword) {
            return bastardSword.isTwoHanded(
                    mainHandStack
            );
        }

        return false;
    }
}