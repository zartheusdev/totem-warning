package com.totemwarning.hud;

import com.totemwarning.config.TotemWarningConfig;
import com.totemwarning.screen.TotemWarningConfigScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;

/**
 * HUD overlay render edici:
 * 1. Sol El (Offhand): Sol elde totem yoksa tüm ekranı kaplar.
 * 2. Hotbar: Hotbar'daki 9 slotun hiçbirinde totem yoksa sadece seçilen alanı kaplar.
 */
public class TotemWarningHud {

    public static void render(DrawContext drawContext, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();

        // Oyuncu yoksa (dünyada değilse) çizme
        if (client.player == null) return;

        // Ayar ekranı açıkken HUD overlay'leri çizme
        if (client.currentScreen instanceof TotemWarningConfigScreen) return;

        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();

        // ── 1. Sol El (Offhand) Kontrolü ──
        if (TotemWarningConfig.enabled) {
            boolean hasOffhandTotem = client.player.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING);
            if (!hasOffhandTotem) {
                drawContext.fill(0, 0, screenWidth, screenHeight, TotemWarningConfig.getOffhandColor());
            }
        }

        // ── 2. Hotbar Kontrolü (9 slotun hiçbirinde totem yok mu?) ──
        if (TotemWarningConfig.hotbarEnabled) {
            boolean hasHotbarTotem = false;
            for (int i = 0; i < PlayerInventory.getHotbarSize(); i++) {
                if (client.player.getInventory().getStack(i).isOf(Items.TOTEM_OF_UNDYING)) {
                    hasHotbarTotem = true;
                    break;
                }
            }

            if (!hasHotbarTotem) {
                renderHotbarWarningArea(drawContext, screenWidth, screenHeight, TotemWarningConfig.getHotbarColor());
            }
        }
    }

    /**
     * Seçilen alana göre renkli uyarı katmanını çizer.
     */
    public static void renderHotbarWarningArea(DrawContext drawContext, int screenWidth, int screenHeight, int color) {
        switch (TotemWarningConfig.hotbarAreaMode) {
            case 1: {
                // Hotbar Bölgesi: Hotbar'ın üzerine tam oturan alan
                int hbW = 184;
                int hbH = 24;
                int hbX = (screenWidth / 2) - 92;
                int hbY = screenHeight - 23;
                drawContext.fill(hbX, hbY, hbX + hbW, screenHeight, color);
                break;
            }
            case 2: {
                // Ekran Çerçevesi (Border)
                int border = 8;
                drawContext.fill(0, 0, screenWidth, border, color); // Üst
                drawContext.fill(0, screenHeight - border, screenWidth, screenHeight, color); // Alt
                drawContext.fill(0, border, border, screenHeight - border, color); // Sol
                drawContext.fill(screenWidth - border, border, screenWidth, screenHeight - border, color); // Sağ
                break;
            }
            case 3: {
                // Alt Şerit
                int h = Math.max(16, (int) (screenHeight * 0.15));
                drawContext.fill(0, screenHeight - h, screenWidth, screenHeight, color);
                break;
            }
            case 4: {
                // Üst Şerit
                int h = Math.max(16, (int) (screenHeight * 0.15));
                drawContext.fill(0, 0, screenWidth, h, color);
                break;
            }
            case 0:
            default: {
                // Özel Alan (Yüzdeye göre ayarlanabilir kutu)
                int x = (int) Math.round(screenWidth * (TotemWarningConfig.hotbarXPercent / 100.0));
                int y = (int) Math.round(screenHeight * (TotemWarningConfig.hotbarYPercent / 100.0));
                int w = Math.max(4, (int) Math.round(screenWidth * (TotemWarningConfig.hotbarWPercent / 100.0)));
                int h = Math.max(4, (int) Math.round(screenHeight * (TotemWarningConfig.hotbarHPercent / 100.0)));

                int x2 = Math.min(screenWidth, x + w);
                int y2 = Math.min(screenHeight, y + h);
                drawContext.fill(x, y, x2, y2, color);
                break;
            }
        }
    }
}
