package net.angelic.weaponsexpanded.mixin;

import net.angelic.weaponsexpanded.config.WeaponsExpandedConfig;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public final class WeaponsExpandedMixinPlugin implements IMixinConfigPlugin {
    private static final String MIXIN_BASE = "net.angelic.weaponsexpanded.mixin.";
    private static final String ENTITY_EQUIPMENT_PREFIX = MIXIN_BASE + "entity_equipment.";
    private static final String TRIAL_EQUIP_SWAP_MIXIN =
            MIXIN_BASE + "entity_equipment.LivingEntityEquipStackZombieSwapMixin";

    @Override
    public void onLoad(String mixinPackage) {
        WeaponsExpandedConfig.get();
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName == null) return true;

        // Specific toggle for trial spawner melee weapon swapping
        if (mixinClassName.equals(TRIAL_EQUIP_SWAP_MIXIN)) {
            return WeaponsExpandedConfig.get().enableTrialChamberMeleeEquipment;
        }

        // General toggle for entity equipment mixins
        if (mixinClassName.startsWith(ENTITY_EQUIPMENT_PREFIX)) {
            return WeaponsExpandedConfig.get().enableEntityMeleeEquipment;
        }

        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, org.objectweb.asm.tree.ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, org.objectweb.asm.tree.ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
