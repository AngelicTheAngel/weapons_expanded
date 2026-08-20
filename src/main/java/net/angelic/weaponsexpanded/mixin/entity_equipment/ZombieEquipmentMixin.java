package net.angelic.weaponsexpanded.mixin.entity_equipment;

import net.angelic.weaponsexpanded.WeaponsExpandedConfig;
import net.angelic.weaponsexpanded.util.ZombieWeaponSwapUtil;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.ServerLevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Zombie.class)
public class ZombieEquipmentMixin {

    @Inject(
            method = "populateDefaultEquipmentSlots(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/DifficultyInstance;)V",
            at = @At("TAIL")
    )
    private void weaponsexpanded$swapSwordToSickleOrScythe(RandomSource random, DifficultyInstance localDifficulty, CallbackInfo ci) {
        if (WeaponsExpandedConfig.meleeEquip) {
            Zombie self = (Zombie) (Object) this;
            ZombieWeaponSwapUtil.maybeSwapSword(self, random);
        }
    }

    @Inject(
            method = "finalizeSpawn(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/DifficultyInstance;Lnet/minecraft/world/entity/EntitySpawnReason;Lnet/minecraft/world/entity/SpawnGroupData;)Lnet/minecraft/world/entity/SpawnGroupData;",
            at = @At("TAIL")
    )
    private void weaponsexpanded$swapSwordAfterInitialize(
            ServerLevelAccessor world,
            DifficultyInstance difficulty,
            EntitySpawnReason spawnReason,
            SpawnGroupData entityData,
            CallbackInfoReturnable<SpawnGroupData> cir
    ) {
        if (WeaponsExpandedConfig.meleeEquip) {
            Zombie self = (Zombie) (Object) this;
            ZombieWeaponSwapUtil.maybeSwapSword(self, self.getRandom());
        }
    }
}