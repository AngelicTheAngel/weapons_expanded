package net.angelic.weaponsexpanded.client.event;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = WeaponsExpanded.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.FORGE,
        value = Dist.CLIENT
)
public final class HeldItemRenderEvents {

    private HeldItemRenderEvents() {
    }

    @SubscribeEvent
    public static void
    weaponsexpanded$hideOffhandForCertainMainhandItems(
            RenderHandEvent event
    ) {
        if (event.getHand()
                != InteractionHand.OFF_HAND) {
            return;
        }

        LocalPlayer player =
                Minecraft.getInstance().player;

        if (player == null) {
            return;
        }

        ItemStack mainHandStack =
                player.getMainHandItem();

        if (mainHandStack.getItem()
                instanceof TwoHandedSwordItem) {
            event.setCanceled(true);
            return;
        }

        if (mainHandStack.getItem()
                instanceof BastardSwordItem bastardSword
                && bastardSword.isTwoHanded(
                mainHandStack
        )) {
            event.setCanceled(true);
            return;
        }

        if (!(mainHandStack.getItem()
                instanceof ChainCrossbowItem)) {
            return;
        }

        boolean mainHandCharged =
                CrossbowItem.isCharged(
                        mainHandStack
                );

        boolean usingMainHand =
                player.isUsingItem()
                        && player.getUsedItemHand()
                        == InteractionHand.MAIN_HAND;

        if (mainHandCharged || usingMainHand) {
            event.setCanceled(true);
        }
    }
}