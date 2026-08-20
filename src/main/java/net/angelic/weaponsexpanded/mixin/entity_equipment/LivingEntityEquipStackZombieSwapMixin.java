package net.angelic.weaponsexpanded.mixin.entity_equipment;

import net.angelic.weaponsexpanded.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.util.ZombieWeaponSwapUtil;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
            method = "setItemSlot(Lnet/minecraft/world/entity/EquipmentSlot;Lnet/minecraft/world/item/ItemStack;)V",
            at = @At("TAIL")
    )
    private void weaponsexpanded$swapZombieSwordWhenEquipped(EquipmentSlot slot, ItemStack stack, CallbackInfo ci) {
        if (WeaponsExpandedConfig.trialEquip) {
            if (slot != EquipmentSlot.MAINHAND) return;
            if (weaponsexpanded$replacingMainhand) return;

            if (!((Object) this instanceof Zombie zombie)) return;

            // Prevent overwriting swords the zombie equips later (e.g., picked up from the ground).
            // "age" is in ticks since spawn; spawner gear is typically applied immediately.
            if (zombie.tickCount > 1) return;

            boolean isIronSword = stack.is(Items.IRON_SWORD);
            boolean isDiamondSword = stack.is(Items.DIAMOND_SWORD);
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
}