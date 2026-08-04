package net.angelic.weaponsexpanded.client.event;

import net.angelic.weaponsexpanded.WeaponsExpanded;
import net.angelic.weaponsexpanded.item.custom.BastardSwordItem;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.item.custom.TwoHandedSwordItem;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = WeaponsExpanded.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.FORGE,
        value = Dist.CLIENT
)
public final class PlayerArmPoseEvents {

    private PlayerArmPoseEvents() {
    }

    @SubscribeEvent(
            priority = EventPriority.LOWEST
    )
    public static void weaponsexpanded$overrideArmPose(
            RenderPlayerEvent.Pre event
    ) {
        if (!(event.getEntity()
                instanceof AbstractClientPlayer player)) {
            return;
        }

        ItemStack mainHandStack =
                player.getMainHandItem();

        if (!weaponsexpanded$usesTwoHandedPose(
                mainHandStack
        )) {
            return;
        }

        PlayerModel<AbstractClientPlayer> model =
                event.getRenderer().getModel();

        if (player.getMainArm()
                == HumanoidArm.RIGHT) {
            model.rightArmPose =
                    HumanoidModel.ArmPose.CROSSBOW_HOLD;
        } else {
            model.leftArmPose =
                    HumanoidModel.ArmPose.CROSSBOW_HOLD;
        }
    }

    private static boolean
    weaponsexpanded$usesTwoHandedPose(
            ItemStack mainHandStack
    ) {
        if (mainHandStack.getItem()
                instanceof TwoHandedSwordItem) {
            return true;
        }

        if (mainHandStack.getItem()
                instanceof BastardSwordItem bastardSword
                && bastardSword.isTwoHanded(
                mainHandStack
        )) {
            return true;
        }

        return mainHandStack.getItem()
                instanceof ChainCrossbowItem
                && CrossbowItem.isCharged(
                mainHandStack
        );
    }
}