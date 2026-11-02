package com.totemwarning.screen;

import com.totemwarning.config.TotemWarningConfig;
import java.util.Map;

final class UiText {
    private static final Map<String, String[]> LABELS = Map.ofEntries(
            Map.entry("title", new String[]{"Ayarlar", "Settings", "Ajustes"}),
            Map.entry("offhandTab", new String[]{"Sol El", "Offhand", "Mano secundaria"}),
            Map.entry("hotbarTab", new String[]{"Hotbar", "Hotbar", "Barra rápida"}),
            Map.entry("language", new String[]{"Dil: Türkçe", "English", "Español"}),
            Map.entry("red", new String[]{"Kırmızı", "Red", "Rojo"}),
            Map.entry("green", new String[]{"Yeşil", "Green", "Verde"}),
            Map.entry("blue", new String[]{"Mavi", "Blue", "Azul"}),
            Map.entry("opacity", new String[]{"Saydamlık", "Opacity", "Opacidad"}),
            Map.entry("offhandWarning", new String[]{"Sol El Uyarısı", "Offhand Warning", "Aviso 2.ª mano"}),
            Map.entry("hotbarWarning", new String[]{"Hotbar Uyarısı", "Hotbar Warning", "Aviso barra"}),
            Map.entry("on", new String[]{"Açık", "On", "Activo"}),
            Map.entry("off", new String[]{"Kapalı", "Off", "Inactivo"}),
            Map.entry("saveClose", new String[]{"Kaydet ve Kapat", "Save and Close", "Guardar y cerrar"}),
            Map.entry("reset", new String[]{"Sıfırla", "Reset", "Restablecer"}),
            Map.entry("areaMode", new String[]{"Alan Modu", "Area Mode", "Modo de área"}),
            Map.entry("customArea", new String[]{"Özel Alan (X, Y, G, Y)", "Custom Area (X, Y, W, H)", "Área personal. (X, Y, A, H)"}),
            Map.entry("aboveHotbar", new String[]{"Hotbar Üstü (Otomatik)", "Above Hotbar (Auto)", "Sobre barra (Auto)"}),
            Map.entry("screenBorder", new String[]{"Ekran Çerçevesi", "Screen Border", "Borde de pantalla"}),
            Map.entry("bottomStrip", new String[]{"Alt Şerit", "Bottom Strip", "Franja inferior"}),
            Map.entry("topStrip", new String[]{"Üst Şerit", "Top Strip", "Franja superior"}),
            Map.entry("xPosition", new String[]{"X Konumu", "X Position", "Posición X"}),
            Map.entry("yPosition", new String[]{"Y Konumu", "Y Position", "Posición Y"}),
            Map.entry("width", new String[]{"Genişlik", "Width", "Ancho"}),
            Map.entry("height", new String[]{"Yükseklik", "Height", "Alto"}),
            Map.entry("offhandHelp", new String[]{"Sol elde totem yokken tüm ekran seçilen renkle kaplanır", "The screen is tinted when no totem is in your offhand", "La pantalla se tiñe si no hay tótem en la otra mano"}),
            Map.entry("hotbarHelp", new String[]{"Hotbar slotlarında totem yokken seçtiğiniz alan kaplanır", "The chosen area is tinted when the hotbar has no totem", "El área elegida se tiñe si no hay tótem en la barra"}),
            Map.entry("livePreview", new String[]{"Canlı Önizleme", "Live Preview", "Vista previa"}),
            Map.entry("screenPreview", new String[]{"Ekran Önizlemesi", "Screen Preview", "Vista de pantalla"}),
            Map.entry("alpha", new String[]{"Saydamlık", "Opacity", "Opacidad"})
    );

    private UiText() { }

    static String get(String key) {
        String[] values = LABELS.get(key);
        if (values == null) throw new IllegalArgumentException("Unknown label: " + key);
        int index = switch (TotemWarningConfig.language) {
            case "en" -> 1;
            case "es" -> 2;
            default -> 0;
        };
        return values[index];
    }
}
