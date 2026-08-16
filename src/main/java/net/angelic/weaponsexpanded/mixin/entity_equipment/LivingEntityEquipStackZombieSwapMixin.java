package net.angelic.weaponsexpanded.mixin.entity_equipment;

import net.angelic.weaponsexpanded.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.util.ZombieWeaponSwapUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mob.class)
public abstract class LivingEntityEquipStackZombieSwapMixin {

    @Unique
    private boolean weaponsexpanded$replacingMainhand;

    @Inject(
            method = "setItemSlotAndDropWhenKilled",
            at = @At("TAIL")
    )
    private void weaponsexpanded$swapZombieSwordWhenEquipped(
            EquipmentSlot slot,
            ItemStack stack,
            CallbackInfo ci
    ) {
        if (!WeaponsExpandedConfig.ENABLE_TRIAL_CHAMBER_MELEE_EQUIPMENT.get()) {
            return;
        }

        if (slot != EquipmentSlot.MAINHAND) {
            return;
        }

        if (weaponsexpanded$replacingMainhand) {
            return;
        }

        if (!((Object) this instanceof Zombie zombie)) {
            return;
        }

        // Avoid replacing weapons picked up after spawning.
        if (zombie.tickCount > 1) {
            return;
        }

        boolean isIronSword =
                stack.is(Items.IRON_SWORD);

        boolean isDiamondSword =
                stack.is(Items.DIAMOND_SWORD);

        if (!isIronSword && !isDiamondSword) {
            return;
        }

        // Custom names are data components in 1.21.1.
        if (stack.get(DataComponents.CUSTOM_NAME) != null) {
            return;
        }

        try {
            weaponsexpanded$replacingMainhand = true;

            ZombieWeaponSwapUtil.maybeSwapSword(
                    zombie,
                    zombie.getRandom()
            );
        } finally {
            weaponsexpanded$replacingMainhand = false;
        }
    }
}