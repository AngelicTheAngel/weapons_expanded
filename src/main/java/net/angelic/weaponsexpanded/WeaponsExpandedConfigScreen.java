package net.angelic.weaponsexpanded;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.fml.ModLoadingContext;
import org.jetbrains.annotations.NotNull;

public final class WeaponsExpandedConfigScreen extends Screen {
    private final Screen parent;

    public WeaponsExpandedConfigScreen(Screen parent) {
        super(Component.translatable(
                "config.weaponsexpanded.title"
        ));

        this.parent = parent;
    }

    public static void registerConfigScreen() {
        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory(
                        (minecraft, parent) ->
                                new WeaponsExpandedConfigScreen(parent)
                )
        );
    }

    @Override
    protected void init() {
        int left = this.width / 2 - 100;
        int top = 40;
        int spacing = 24;

        this.addRenderableWidget(
                CycleButton.onOffBuilder(
                        WeaponsExpandedConfig
                                .ENABLE_CUSTOM_LOOT_TABLES
                                .get()
                ).create(
                        left,
                        top,
                        200,
                        20,
                        Component.translatable(
                                "config.weaponsexpanded.option.enableCustomLootTables"
                        ),
                        (button, value) ->
                                WeaponsExpandedConfig
                                        .ENABLE_CUSTOM_LOOT_TABLES
                                        .set(value)
                )
        );

        this.addRenderableWidget(
                CycleButton.onOffBuilder(
                        WeaponsExpandedConfig
                                .ENABLE_ENTITY_MELEE_EQUIPMENT
                                .get()
                ).create(
                        left,
                        top + spacing,
                        200,
                        20,
                        Component.translatable(
                                "config.weaponsexpanded.option.enableEntityMeleeEquipment"
                        ),
                        (button, value) ->
                                WeaponsExpandedConfig
                                        .ENABLE_ENTITY_MELEE_EQUIPMENT
                                        .set(value)
                )
        );

        this.addRenderableWidget(
                CycleButton.onOffBuilder(
                        WeaponsExpandedConfig
                                .ENABLE_WEAPONSMITH_TRADES
                                .get()
                ).create(
                        left,
                        top + spacing * 2,
                        200,
                        20,
                        Component.translatable(
                                "config.weaponsexpanded.option.enableWeaponsmithTrades"
                        ),
                        (button, value) ->
                                WeaponsExpandedConfig
                                        .ENABLE_WEAPONSMITH_TRADES
                                        .set(value)
                )
        );

        this.addRenderableWidget(
                CycleButton.onOffBuilder(
                        WeaponsExpandedConfig
                                .ALT_TWO_HANDED_SWORD_HANDLING
                                .get()
                ).create(
                        left,
                        top + spacing * 3,
                        200,
                        20,
                        Component.translatable(
                                "config.weaponsexpanded.option.altTwoHandedSwordHandling"
                        ),
                        (button, value) ->
                                WeaponsExpandedConfig
                                        .ALT_TWO_HANDED_SWORD_HANDLING
                                        .set(value)
                )
        );

        this.addRenderableWidget(
                CycleButton.onOffBuilder(
                        WeaponsExpandedConfig
                                .DISABLE_EXTRA_DURABILITY_DAMAGE_FOR_AXES
                                .get()
                ).create(
                        left,
                        top + spacing * 4,
                        200,
                        20,
                        Component.translatable(
                                "config.weaponsexpanded.option.disableExtraDurabilityDamageForAxes"
                        ),
                        (button, value) ->
                                WeaponsExpandedConfig
                                        .DISABLE_EXTRA_DURABILITY_DAMAGE_FOR_AXES
                                        .set(value)
                )
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

    @Override
    public void onClose() {
        WeaponsExpandedConfig.SPEC.save();

        if (this.minecraft != null) {
            this.minecraft.setScreen(parent);
        }
    }

    @Override
    public void render(
            @NotNull GuiGraphics graphics,
            int mouseX,
            int mouseY,
            float partialTick
    ) {
        this.renderBackground(graphics);

        graphics.drawCenteredString(
                this.font,
                this.title,
                this.width / 2,
                20,
                0xFFFFFF
        );

        super.render(
                graphics,
                mouseX,
                mouseY,
                partialTick
        );
    }
}