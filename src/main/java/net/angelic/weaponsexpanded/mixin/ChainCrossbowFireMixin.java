package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.entity.projectile.HeavyArrowEntity;
import net.angelic.weaponsexpanded.item.custom.ChainCrossbowItem;
import net.angelic.weaponsexpanded.mixin.accessor.CrossbowItemAccessor;
import net.angelic.weaponsexpanded.mixin.invoker.PersistentProjectileEntityPierceInvoker;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CrossbowItem.class)
public abstract class ChainCrossbowFireMixin {

    @Unique
    private static final String WEAPONSEXPANDED$AUTO_RELOAD_USED_KEY = "weaponsexpanded:auto_reload_used";

    /**
     * Auto-reload ONCE per manual load, using the *actual* crossbow stack that fired (no guessing).
     */
    @Inject(method = "shootAll", at = @At("TAIL"))
    private void weaponsexpanded$autoReloadOnce(
            World world,
            LivingEntity shooter,
            Hand hand,
            ItemStack stack,
            float speed,
            float divergence,
            @Nullable LivingEntity target,
            CallbackInfo ci
    ) {
        if (world.isClient()) return;
        if (!(stack.getItem() instanceof ChainCrossbowItem)) return;

        // Only allow ONE auto-reload per manual load
        NbtComponent custom = stack.get(DataComponentTypes.CUSTOM_DATA);
        NbtCompound nbt = (custom != null) ? custom.copyNbt() : new NbtCompound();
        boolean alreadyAutoReloaded = nbt.getBoolean(WEAPONSEXPANDED$AUTO_RELOAD_USED_KEY).orElse(false);

        if (alreadyAutoReloaded) return;

        boolean reloaded = ((CrossbowItemAccessor) (Object) this).weaponsexpanded$loadProjectiles(shooter, stack);
        if (!reloaded) return; // no ammo available -> no second shot

        nbt.putBoolean(WEAPONSEXPANDED$AUTO_RELOAD_USED_KEY, true);
        stack.set(DataComponentTypes.CUSTOM_DATA, NbtComponent.of(nbt));

        if (shooter instanceof PlayerEntity player) {
            player.getItemCooldownManager().set(stack, 15);
        }
    }

    /**
     * Keep this in shoot(): it's per-projectile.
     */
    @Inject(method = "shoot", at = @At("TAIL"))
    private void weaponsexpanded$noPierceOnHeavyArrow(LivingEntity shooter,
                                                      ProjectileEntity projectile,
                                                      int index,
                                                      float speed,
                                                      float divergence,
                                                      float yaw,
                                                      @Nullable LivingEntity target,
                                                      CallbackInfo ci) {
        if (shooter.getEntityWorld().isClient()) return;

        if (projectile instanceof HeavyArrowEntity && projectile instanceof PersistentProjectileEntity ppe) {
            ((PersistentProjectileEntityPierceInvoker) ppe).weaponsexpanded$invokeSetPierceLevel((byte) 0);
        }
    }
}
