package net.angelic.weaponsexpanded.client.event;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.angelic.weaponsexpanded.network.ModPackets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;

@EventBusSubscriber(
        modid = WeaponsExpanded.MOD_ID,
        value = Dist.CLIENT
)
public final class ClientInteractionEvents {

    private ClientInteractionEvents() {
    }

    @SubscribeEvent
    public static void weaponsexpanded$handleInteractionInput(
            InputEvent.InteractionKeyMappingTriggered event
    ) {
        LocalPlayer player =
                Minecraft.getInstance().player;

        if (player == null) {
            return;
        }

        ItemStack mainHandStack =
                player.getMainHandItem();

        if (event.isAttack()) {
            weaponsexpanded$handleChainCrossbowAttack(
                    event,
                    player,
                    mainHandStack
            );

            return;
        }

        if (event.isUseItem()) {
            weaponsexpanded$handleOffhandUse(
                    event,
                    mainHandStack
            );
        }
    }

    private static void
    weaponsexpanded$handleChainCrossbowAttack(
            InputEvent.InteractionKeyMappingTriggered event,
            LocalPlayer player,
            ItemStack mainHandStack
    ) {
        if (!(mainHandStack.getItem()
                instanceof ChainCrossbowItem)) {
            return;
        }

        if (!CrossbowItem.isCharged(
                mainHandStack
        )) {
            return;
        }

        /*
         * Always cancel the normal attack so the player does
         * not punch while holding a charged chain crossbow.
         */
        event.setCanceled(true);
        event.setSwingHand(false);

        if (player.getCooldowns().isOnCooldown(
                mainHandStack.getItem()
        )) {
            return;
        }

        ModPackets.sendFireChainCrossbow();
    }

    private static void
    weaponsexpanded$handleOffhandUse(
            InputEvent.InteractionKeyMappingTriggered event,
            ItemStack mainHandStack
    ) {
        if (event.getHand()
                != InteractionHand.OFF_HAND) {
            return;
        }

        if (WeaponsExpandedConfig
                .ALT_TWO_HANDED_SWORD_HANDLING
                .get()) {
            return;
        }

        if (!weaponsexpanded$isEffectivelyTwoHanded(
                mainHandStack
        )) {
            return;
        }

        /*
         * Cancel before Minecraft starts using the offhand
         * item, preventing the one-tick animation.
         */
        event.setCanceled(true);
        event.setSwingHand(false);
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