package net.angelic.weaponsexpanded.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Simple JSON config stored at: config/weaponsexpanded.json
 * Safe to call early (also from mixin plugin).
 */
public final class WeaponsExpandedConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final String FILE_NAME = "weaponsexpanded.json";

    private static volatile WeaponsExpandedConfig INSTANCE;

    public boolean enableCustomLootTables = true;                 // enabled by default
    public boolean enableEntityEquipmentMixins = true;            // enabled by default
    public boolean enableTrialChamberMeleeEquipment = true;   // enabled by default

    private WeaponsExpandedConfig() {}

    public static WeaponsExpandedConfig get() {
        WeaponsExpandedConfig local = INSTANCE;
        if (local != null) return local;

        synchronized (WeaponsExpandedConfig.class) {
            if (INSTANCE == null) {
                INSTANCE = loadOrCreate();
            }
            return INSTANCE;
        }
    }

    public static void reload() {
        synchronized (WeaponsExpandedConfig.class) {
            INSTANCE = loadOrCreate();
        }
    }

    private static WeaponsExpandedConfig loadOrCreate() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve(FILE_NAME);

        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                WeaponsExpandedConfig cfg = GSON.fromJson(reader, WeaponsExpandedConfig.class);
                if (cfg == null) cfg = new WeaponsExpandedConfig();
                // Write back to ensure new fields get added over time
                write(path, cfg);
                return cfg;
            } catch (Exception ignored) {
                // If parsing fails, fall back to defaults and overwrite file
                WeaponsExpandedConfig cfg = new WeaponsExpandedConfig();
                write(path, cfg);
                return cfg;
            }
        }

        WeaponsExpandedConfig cfg = new WeaponsExpandedConfig();
        write(path, cfg);
        return cfg;
    }

    private static void write(Path path, WeaponsExpandedConfig cfg) {
        try {
            Files.createDirectories(path.getParent());
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                GSON.toJson(cfg, writer);
            }
        } catch (IOException ignored) {
            // If we can't write config, we still run with defaults.
        }
    }
}
