package net.angelic.weaponsexpanded.mixin.entity_equipment;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.util.ZombieWeaponSwapUtil;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public abstract class LivingEntityEquipStackZombieSwapMixin {

    @Unique
    private boolean weaponsexpanded$replacingMainhand;

    @Inject(
            method = "equipStack(Lnet/minecraft/entity/EquipmentSlot;Lnet/minecraft/item/ItemStack;)V",
            at = @At("TAIL")
    )
    private void weaponsexpanded$swapZombieSwordWhenEquipped(
            EquipmentSlot slot,
            ItemStack stack,
            CallbackInfo ci
    ) {
        if (!WeaponsExpandedConfig.get().enableTrialChamberMeleeEquipment) return;
        if (slot != EquipmentSlot.MAINHAND) return;
        if (weaponsexpanded$replacingMainhand) return;
        if (!((Object) this instanceof ZombieEntity zombie)) return;

        // Prevent replacing swords that the zombie equips later, such as loot
        // picked up from the ground. Initial spawn equipment is applied at age 0.
        if (zombie.age > 1) return;

        boolean isIronSword = stack.isOf(Items.IRON_SWORD);
        boolean isDiamondSword = stack.isOf(Items.DIAMOND_SWORD);
        if (!isIronSword && !isDiamondSword) return;
        if (stack.hasCustomName()) return;

        try {
            weaponsexpanded$replacingMainhand = true;
            ZombieWeaponSwapUtil.maybeSwapSword(zombie, zombie.getRandom());
        } finally {
            weaponsexpanded$replacingMainhand = false;
        }
    }
}
