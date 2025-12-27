package net.angelic.weaponsexpanded.mixin.entity_equipment;

import net.angelic.weaponsexpanded.util.ZombieWeaponSwapUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityEquipStackZombieSwapMixin {

    @Unique
    private boolean weaponsexpanded$replacingMainhand;

    @Inject(
            method = "equipStack(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/item/ItemStack;)V",
            at = @At("TAIL")
    )
    private void weaponsexpanded$swapZombieSwordWhenEquipped(EquipmentSlot slot, ItemStack stack, CallbackInfo ci) {
        if (slot != EquipmentSlot.MAINHAND) return;
        if (weaponsexpanded$replacingMainhand) return;

        if (!((Object) this instanceof ZombieEntity zombie)) return;

        // Prevent overwriting swords the zombie equips later (e.g., picked up from the ground).
        // "age" is in ticks since spawn; spawner gear is typically applied immediately.
        if (zombie.age > 1) return;

        boolean isIronSword = stack.isOf(Items.IRON_SWORD);
        boolean isDiamondSword = stack.isOf(Items.DIAMOND_SWORD);
        if (!isIronSword && !isDiamondSword) return;
        if (stack.getCustomName() != null) return;

        try {
            weaponsexpanded$replacingMainhand = true;
            ZombieWeaponSwapUtil.maybeSwapSword(zombie, zombie.getRandom());
        } finally {
            weaponsexpanded$replacingMainhand = false;
        }
    }
}
