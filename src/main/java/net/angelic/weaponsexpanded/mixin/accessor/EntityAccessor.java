package net.angelic.weaponsexpanded.mixin.accessor;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(Entity.class)
public interface EntityAccessor {
    @Accessor("tags")
    Set<String> weaponsexpanded$getTags();
}
