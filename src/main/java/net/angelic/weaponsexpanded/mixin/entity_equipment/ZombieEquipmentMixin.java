package net.angelic.weaponsexpanded.mixin.entity_equipment;

import net.angelic.weaponsexpanded.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.util.ZombieWeaponSwapUtil;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Zombie.class)
public class ZombieEquipmentMixin {

    @Inject(
            method = "populateDefaultEquipmentSlots",
            at = @At("TAIL")
    )
    private void
    weaponsexpanded$swapSwordToSickleOrScythe(
            RandomSource random,
            DifficultyInstance difficulty,
            CallbackInfo ci
    ) {
        if (!WeaponsExpandedConfig
                .ENABLE_ENTITY_MELEE_EQUIPMENT
                .get()) {
            return;
        }

        Zombie self =
                (Zombie) (Object) this;

        ZombieWeaponSwapUtil.maybeSwapSword(
                self,
                random
        );
    }

    @Inject(
            method = "finalizeSpawn",
            at = @At("TAIL")
    )
    private void
    weaponsexpanded$swapSwordAfterFinalizeSpawn(
            ServerLevelAccessor levelAccessor, DifficultyInstance difficultyInstance, EntitySpawnReason spawnReason, SpawnGroupData spawnGroupData, CallbackInfoReturnable<SpawnGroupData> cir
    ) {
        if (!WeaponsExpandedConfig
                .ENABLE_ENTITY_MELEE_EQUIPMENT
                .get()) {
            return;
        }

        Zombie self =
                (Zombie) (Object) this;

        ZombieWeaponSwapUtil.maybeSwapSword(
                self,
                self.getRandom()
        );
    }
}