package net.angelic.weaponsexpanded.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.consume.UseAction;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class TwoHandedSwordItem extends Item {

    public TwoHandedSwordItem(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(settings.sword(material, attackDamage, attackSpeed));
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot, boolean selected) {
        if (selected) {
            if (entity instanceof PlayerEntity) {
                ItemStack offhandItem = ((PlayerEntity) entity).getStackInHand(Hand.OFF_HAND);
                ((PlayerEntity) entity).setStackInHand(Hand.OFF_HAND, ItemStack.EMPTY);
                ((PlayerEntity) entity).giveOrDropStack(offhandItem);
            }
        }
        super.inventoryTick(stack, world, entity, slot);
    }

}
