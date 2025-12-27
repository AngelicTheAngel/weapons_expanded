package net.angelic.weaponsexpanded.mixin.entity_equipment;

import net.angelic.weaponsexpanded.util.ZombieWeaponSwapUtil;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ZombieEntity.class)
public class ZombieEquipmentMixin {

    @Inject(
            method = "initEquipment(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/LocalDifficulty;)V",
            at = @At("TAIL")
    )
    private void weaponsexpanded$swapSwordToSickleOrScythe(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) {
        ZombieEntity self = (ZombieEntity) (Object) this;
        ZombieWeaponSwapUtil.maybeSwapSword(self, random);
    }

    @Inject(
            method = "initialize(Lnet/minecraft/world/ServerWorldAccess;Lnet/minecraft/world/LocalDifficulty;Lnet/minecraft/entity/SpawnReason;Lnet/minecraft/entity/EntityData;)Lnet/minecraft/entity/EntityData;",
            at = @At("TAIL")
    )
    private void weaponsexpanded$swapSwordAfterInitialize(
            ServerWorldAccess world,
            LocalDifficulty difficulty,
            SpawnReason spawnReason,
            EntityData entityData,
            CallbackInfoReturnable<EntityData> cir
    ) {
        ZombieEntity self = (ZombieEntity) (Object) this;
        ZombieWeaponSwapUtil.maybeSwapSword(self, self.getRandom());
    }
}