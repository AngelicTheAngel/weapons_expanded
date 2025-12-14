package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ChargedProjectilesComponent;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ChainCrossbowItem extends CrossbowItem {

    private static final String WEAPONSEXPANDED$AUTO_RELOAD_USED_KEY = "weaponsexpanded:auto_reload_used";

    public ChainCrossbowItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        // If we're NOT currently charged, this use() is starting a manual load attempt.
        ChargedProjectilesComponent charged =
                stack.getOrDefault(DataComponentTypes.CHARGED_PROJECTILES, ChargedProjectilesComponent.DEFAULT);

        boolean isCharged = !charged.isEmpty();

        if (!world.isClient() && !isCharged) {
            NbtComponent custom = stack.get(DataComponentTypes.CUSTOM_DATA);
            if (custom != null) {
                NbtCompound nbt = custom.copyNbt();
                nbt.remove(WEAPONSEXPANDED$AUTO_RELOAD_USED_KEY);

                if (nbt.isEmpty()) {
                    stack.remove(DataComponentTypes.CUSTOM_DATA);
                } else {
                    stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));
                }
            }
        }

        return super.use(world, user, hand);
    }

    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        return super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }
}
