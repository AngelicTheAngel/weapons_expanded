package net.angelic.weaponsexpanded;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.jetbrains.annotations.NotNull;

public final class WeaponsExpandedConfigScreen extends Screen {
    private final Screen parent;

    public WeaponsExpandedConfigScreen(Screen parent) {
        super(Component.translatable(
                "config.weaponsexpanded.title"
        ));

        this.parent = parent;
    }

    public static void registerConfigScreen(ModContainer modContainer) {
        modContainer.registerExtensionPoint(
                IConfigScreenFactory.class,
                (minecraft, parent) ->
                        new WeaponsExpandedConfigScreen(parent)
        );
    }

    @Override
    protected void init() {
        int left = this.width / 2 - 100;
        int top = 40;
        int spacing = 24;

        addBooleanOption(
                left,
                top,
                "config.weaponsexpanded.option.enableCustomLootTables",
                WeaponsExpandedConfig.ENABLE_CUSTOM_LOOT_TABLES.get(),
                WeaponsExpandedConfig.ENABLE_CUSTOM_LOOT_TABLES::set
        );

        addBooleanOption(
                left,
                top + spacing,
                "config.weaponsexpanded.option.enableEntityMeleeEquipment",
                WeaponsExpandedConfig.ENABLE_ENTITY_MELEE_EQUIPMENT.get(),
                WeaponsExpandedConfig.ENABLE_ENTITY_MELEE_EQUIPMENT::set
        );

        addBooleanOption(
                left,
                top + spacing * 2,
                "config.weaponsexpanded.option.enableTrialChamberMeleeEquipment",
                WeaponsExpandedConfig.ENABLE_TRIAL_CHAMBER_MELEE_EQUIPMENT.get(),
                WeaponsExpandedConfig.ENABLE_TRIAL_CHAMBER_MELEE_EQUIPMENT::set
        );

        addBooleanOption(
                left,
                top + spacing * 3,
                "config.weaponsexpanded.option.enableWeaponsmithTrades",
                WeaponsExpandedConfig.ENABLE_WEAPONSMITH_TRADES.get(),
                WeaponsExpandedConfig.ENABLE_WEAPONSMITH_TRADES::set
        );

        addBooleanOption(
                left,
                top + spacing * 4,
                "config.weaponsexpanded.option.altTwoHandedSwordHandling",
                WeaponsExpandedConfig.ALT_TWO_HANDED_SWORD_HANDLING.get(),
                WeaponsExpandedConfig.ALT_TWO_HANDED_SWORD_HANDLING::set
        );

        addBooleanOption(
                left,
                top + spacing * 5,
                "config.weaponsexpanded.option.disableExtraDurabilityDamageForAxes",
                WeaponsExpandedConfig
                        .DISABLE_EXTRA_DURABILITY_DAMAGE_FOR_AXES
                        .get(),
                WeaponsExpandedConfig
                        .DISABLE_EXTRA_DURABILITY_DAMAGE_FOR_AXES::set
        );

        this.addRenderableWidget(
                Button.builder(
                        CommonComponents.GUI_DONE,
                        button -> this.onClose()
                ).bounds(
                        left,
                        top + spacing * 7,
                        200,
                        20
                ).build()
        );
    }

    private void addBooleanOption(
            int x,
            int y,
            String translationKey,
            boolean initialValue,
            java.util.function.Consumer<Boolean> setter
    ) {
        this.addRenderableWidget(
                CycleButton.onOffBuilder(initialValue).create(
                        x,
                        y,
                        200,
                        20,
                        Component.translatable(translationKey),
                        (button, value) -> setter.accept(value)
                )
        );
    }

    @Override
    public void onClose() {
        WeaponsExpandedConfig.SPEC.save();

        if (this.minecraft != null) {
            this.minecraft.setScreen(this.parent);
        }
    }

    @Override
    public void render(
            @NotNull GuiGraphics graphics,
            int mouseX,
            int mouseY,
            float partialTick
    ) {
        this.renderBackground(
                graphics,
                mouseX,
                mouseY,
                partialTick
        );

        super.render(
                graphics,
                mouseX,
                mouseY,
                partialTick
        );

        graphics.drawCenteredString(
                this.font,
                this.title,
                this.width / 2,
                20,
                0xFFFFFF
        );
    }
}