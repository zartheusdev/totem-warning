package com.totemwarning.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.totemwarning.TotemWarningClient;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Mod ayarlarını JSON dosyasından yükler ve kaydeder.
 * 1. Sol El (Offhand) Uyarısı: Sol elde totem yokken tüm ekranı kaplar.
 * 2. Hotbar Uyarısı: Hotbar'da herhangi bir slotta totem yokken seçilen alanı kaplar.
 */
public class TotemWarningConfig {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance()
            .getConfigDir().resolve("totemwarning.json");

    // ── 1. Sol El (Offhand) Uyarısı — Tüm Ekran ──
    public static String language = "tr";
    public static boolean enabled = true;
    public static int red = 255;
    public static int green = 0;
    public static int blue = 0;
    public static int alpha = 80;

    // ── 2. Hotbar Uyarısı — Seçili Alan ──
    public static boolean hotbarEnabled = true;
    public static int hotbarRed = 255;
    public static int hotbarGreen = 140;
    public static int hotbarBlue = 0;
    public static int hotbarAlpha = 140;

    // 0 = Özel Alan (X%, Y%, W%, H%)
    // 1 = Hotbar Üstü (Oto)
    // 2 = Ekran Çerçevesi
    // 3 = Alt Şerit
    // 4 = Üst Şerit
    public static int hotbarAreaMode = 0;
    public static int hotbarXPercent = 25;
    public static int hotbarYPercent = 86;
    public static int hotbarWPercent = 50;
    public static int hotbarHPercent = 14;

    /**
     * Config dosyasını diskten yükler. Dosya yoksa varsayılan değerlerle oluşturur.
     */
    public static void load() {
        File file = CONFIG_PATH.toFile();
        if (file.exists()) {
            try (FileReader reader = new FileReader(file)) {
                ConfigData data = GSON.fromJson(reader, ConfigData.class);
                if (data != null) {
                    language = validLanguage(data.language);
                    red = clamp(data.red, 0, 255);
                    green = clamp(data.green, 0, 255);
                    blue = clamp(data.blue, 0, 255);
                    alpha = clamp(data.alpha, 0, 255);

                    hotbarEnabled = data.hotbarEnabled;
                    hotbarRed = clamp(data.hotbarRed, 0, 255);
                    hotbarGreen = clamp(data.hotbarGreen, 0, 255);
                    hotbarBlue = clamp(data.hotbarBlue, 0, 255);
                    hotbarAlpha = clamp(data.hotbarAlpha, 0, 255);

                    hotbarAreaMode = clamp(data.hotbarAreaMode, 0, 4);
                    hotbarXPercent = clamp(data.hotbarXPercent, 0, 100);
                    hotbarYPercent = clamp(data.hotbarYPercent, 0, 100);
                    hotbarWPercent = clamp(data.hotbarWPercent, 1, 100);
                    hotbarHPercent = clamp(data.hotbarHPercent, 1, 100);
                }
                TotemWarningClient.LOGGER.info("Totem Warning ayarları yüklendi.");
            } catch (Exception e) {
                TotemWarningClient.LOGGER.error("Config yüklenirken hata oluştu, varsayılanlar kullanılacak.", e);
                save();
            }
        } else {
            save();
        }
    }

    /**
     * Mevcut ayarları JSON dosyasına kaydeder.
     */
    public static void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            try (FileWriter writer = new FileWriter(CONFIG_PATH.toFile())) {
                ConfigData data = new ConfigData();
                data.language = language;
                data.red = red;
                data.green = green;
                data.blue = blue;
                data.alpha = alpha;

                data.hotbarEnabled = hotbarEnabled;
                data.hotbarRed = hotbarRed;
                data.hotbarGreen = hotbarGreen;
                data.hotbarBlue = hotbarBlue;
                data.hotbarAlpha = hotbarAlpha;

                data.hotbarAreaMode = hotbarAreaMode;
                data.hotbarXPercent = hotbarXPercent;
                data.hotbarYPercent = hotbarYPercent;
                data.hotbarWPercent = hotbarWPercent;
                data.hotbarHPercent = hotbarHPercent;

                GSON.toJson(data, writer);
            }
            TotemWarningClient.LOGGER.info("Totem Warning ayarları kaydedildi.");
        } catch (IOException e) {
            TotemWarningClient.LOGGER.error("Config kaydedilirken hata oluştu.", e);
        }
    }

    public static int getOffhandColor() {
        return (alpha << 24) | (red << 16) | (green << 8) | blue;
    }

    // Geriye dönük uyumluluk
    public static int getColor() {
        return getOffhandColor();
    }

    public static int getHotbarColor() {
        return (hotbarAlpha << 24) | (hotbarRed << 16) | (hotbarGreen << 8) | hotbarBlue;
    }

    private static String validLanguage(String value) {
        return "en".equals(value) || "es".equals(value) ? value : "tr";
    }

    public static void resetDefaults() {
        enabled = true;
        red = 255;
        green = 0;
        blue = 0;
        alpha = 80;

        hotbarEnabled = true;
        hotbarRed = 255;
        hotbarGreen = 140;
        hotbarBlue = 0;
        hotbarAlpha = 140;
        hotbarAreaMode = 0;
        hotbarXPercent = 25;
        hotbarYPercent = 86;
        hotbarWPercent = 50;
        hotbarHPercent = 14;
        save();
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }

    private static class ConfigData {
        String language = "tr";
        boolean enabled = true;
        int red = 255;
        int green = 0;
        int blue = 0;
        int alpha = 80;

        boolean hotbarEnabled = true;
        int hotbarRed = 255;
        int hotbarGreen = 140;
        int hotbarBlue = 0;
        int hotbarAlpha = 140;

        int hotbarAreaMode = 0;
        int hotbarXPercent = 25;
        int hotbarYPercent = 86;
        int hotbarWPercent = 50;
        int hotbarHPercent = 14;
    }
}
