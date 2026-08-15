package net.angelic.weaponsexpanded.client.event;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.ModItems;
import net.angelic.weaponsexpanded.item.custom.LongbowItem;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;

@EventBusSubscriber(
        modid = WeaponsExpanded.MOD_ID,
        value = Dist.CLIENT
)
public final class LongbowFovEvents {

    private LongbowFovEvents() {
    }

    @SubscribeEvent
    public static void weaponsexpanded$longbowZoom(
            ComputeFovModifierEvent event
    ) {
        Player player =
                event.getPlayer();

        if (!player.isUsingItem()
                || !player.getUseItem().is(
                ModItems.LONGBOW.get()
        )) {
            return;
        }

        int useTicks =
                player.getTicksUsingItem();

        float pull =
                (float) useTicks
                        / (float) LongbowItem
                        .getFullDrawTicks();

        if (pull > 1.0F) {
            pull = 1.0F;
        } else {
            pull *= pull;
        }

        /*
         * Modify the event's current value so changes made by
         * earlier Forge event handlers are preserved.
         */
        event.setNewFovModifier(
                event.getNewFovModifier()
                        * (1.0F - pull * 0.15F)
        );
    }
}