package com.totemwarning.screen;

import com.totemwarning.config.TotemWarningConfig;
import com.totemwarning.hud.TotemWarningHud;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

/**
 * Mod ayar ekranı:
 * Tab 0: Sol El (Offhand) Uyarısı — Totem sol elde yokken tüm ekranı kaplar.
 * Tab 1: Hotbar Uyarısı — Hotbar'daki slotlarda totem yokken sadece seçilen alanı kaplar.
 */
public class TotemWarningConfigScreen extends Screen {

    private final Screen parent;
    private static int activeTab = 0; // 0 = Sol El, 1 = Hotbar

    public TotemWarningConfigScreen(Screen parent) {
        super(Text.literal("Totem Warning"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int centerX = this.width / 2;
        int colWidth = 145;
        int gap = 12;

        int leftX = centerX - colWidth - (gap / 2);
        int rightX = centerX + (gap / 2);

        // ── Üst Sekme Butonları (Tabs) ──
        int tabY = 32;
        this.addDrawableChild(ButtonWidget.builder(
                        Text.literal((activeTab == 0 ? "§a▶ §f" : "§7") + UiText.get("offhandTab")),
                        button -> {
                            activeTab = 0;
                            this.clearAndInit();
                        })
                .dimensions(leftX, tabY, 96, 20)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                        Text.literal((activeTab == 1 ? "§a▶ §f" : "§7") + UiText.get("hotbarTab")),
                        button -> {
                            activeTab = 1;
                            this.clearAndInit();
                        })
                .dimensions(leftX + 103, tabY, 96, 20)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                        Text.literal("§e" + UiText.get("language")),
                        button -> {
                            TotemWarningConfig.language = switch (TotemWarningConfig.language) {
                                case "tr" -> "en";
                                case "en" -> "es";
                                default -> "tr";
                            };
                            TotemWarningConfig.save();
                            this.clearAndInit();
                        })
                .dimensions(leftX + 206, tabY, 96, 20)
                .build());

        if (activeTab == 0) {
            initOffhandTab(leftX, rightX, colWidth, tabY + 28);
        } else {
            initHotbarTab(leftX, rightX, colWidth, tabY + 28);
        }
    }

    // ─────────────────────────────────────────────────────────────────────
    //  TAB 0: Sol El (Offhand) Ayarları
    // ─────────────────────────────────────────────────────────────────────
    private void initOffhandTab(int leftX, int rightX, int colWidth, int startY) {
        // Sol Kolon: Renk ve Saydamlık Slider'ları
        this.addDrawableChild(new ColorSliderWidget(
                leftX, startY, colWidth, 20,
                TotemWarningConfig.red, "§c" + UiText.get("red"),
                v -> TotemWarningConfig.red = v
        ));

        this.addDrawableChild(new ColorSliderWidget(
                leftX, startY + 25, colWidth, 20,
                TotemWarningConfig.green, "§a" + UiText.get("green"),
                v -> TotemWarningConfig.green = v
        ));

        this.addDrawableChild(new ColorSliderWidget(
                leftX, startY + 50, colWidth, 20,
                TotemWarningConfig.blue, "§9" + UiText.get("blue"),
                v -> TotemWarningConfig.blue = v
        ));

        this.addDrawableChild(new ColorSliderWidget(
                leftX, startY + 75, colWidth, 20,
                TotemWarningConfig.alpha, "§e" + UiText.get("opacity"),
                v -> TotemWarningConfig.alpha = v
        ));

        this.addDrawableChild(ButtonWidget.builder(
                        Text.literal(warningLabel("offhandWarning", TotemWarningConfig.enabled)),
                        button -> {
                            TotemWarningConfig.enabled = !TotemWarningConfig.enabled;
                            button.setMessage(Text.literal(
                                    warningLabel("offhandWarning", TotemWarningConfig.enabled)
                            ));
                        })
                .dimensions(leftX, startY + 104, colWidth, 20)
                .build());

        // Sağ Kolon: Aksiyon Butonları (Önizleme kutusu render metodunda çizilir)
        this.addDrawableChild(ButtonWidget.builder(
                        Text.literal("§a✔ " + UiText.get("saveClose")),
                        button -> this.close())
                .dimensions(rightX, startY + 104, colWidth, 20)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                        Text.literal("§c⟳ " + UiText.get("reset")),
                        button -> {
                            TotemWarningConfig.resetDefaults();
                            this.clearAndInit();
                        })
                .dimensions(rightX, startY + 128, colWidth, 20)
                .build());
    }

    // ─────────────────────────────────────────────────────────────────────
    //  TAB 1: Hotbar (Seçili Alan) Ayarları
    // ─────────────────────────────────────────────────────────────────────
    private void initHotbarTab(int leftX, int rightX, int colWidth, int startY) {
        int centerX = this.width / 2;
        int fullWidth = colWidth * 2 + 12;

        // Alan Modu Seçici Buton
        this.addDrawableChild(ButtonWidget.builder(
                        Text.literal(getAreaModeLabel()),
                        button -> {
                            TotemWarningConfig.hotbarAreaMode = (TotemWarningConfig.hotbarAreaMode + 1) % 5;
                            this.clearAndInit();
                        })
                .dimensions(centerX - (fullWidth / 2), startY, fullWidth, 20)
                .build());

        int contentY = startY + 26;

        // Sol Kolon: Renk Slider'ları
        this.addDrawableChild(new ColorSliderWidget(
                leftX, contentY, colWidth, 20,
                TotemWarningConfig.hotbarRed, "§c" + UiText.get("red"),
                v -> TotemWarningConfig.hotbarRed = v
        ));

        this.addDrawableChild(new ColorSliderWidget(
                leftX, contentY + 24, colWidth, 20,
                TotemWarningConfig.hotbarGreen, "§a" + UiText.get("green"),
                v -> TotemWarningConfig.hotbarGreen = v
        ));

        this.addDrawableChild(new ColorSliderWidget(
                leftX, contentY + 48, colWidth, 20,
                TotemWarningConfig.hotbarBlue, "§9" + UiText.get("blue"),
                v -> TotemWarningConfig.hotbarBlue = v
        ));

        this.addDrawableChild(new ColorSliderWidget(
                leftX, contentY + 72, colWidth, 20,
                TotemWarningConfig.hotbarAlpha, "§e" + UiText.get("opacity"),
                v -> TotemWarningConfig.hotbarAlpha = v
        ));

        this.addDrawableChild(ButtonWidget.builder(
                        Text.literal(warningLabel("hotbarWarning", TotemWarningConfig.hotbarEnabled)),
                        button -> {
                            TotemWarningConfig.hotbarEnabled = !TotemWarningConfig.hotbarEnabled;
                            button.setMessage(Text.literal(
                                    warningLabel("hotbarWarning", TotemWarningConfig.hotbarEnabled)
                            ));
                        })
                .dimensions(leftX, contentY + 98, colWidth, 20)
                .build());

        // Sağ Kolon: Alan Konumu & Boyutu Slider'ları (Sadece Özel Alan modundaysa)
        if (TotemWarningConfig.hotbarAreaMode == 0) {
            this.addDrawableChild(new PercentSliderWidget(
                    rightX, contentY, colWidth, 20,
                    TotemWarningConfig.hotbarXPercent, 0, 100, "§b" + UiText.get("xPosition"),
                    v -> TotemWarningConfig.hotbarXPercent = v
            ));

            this.addDrawableChild(new PercentSliderWidget(
                    rightX, contentY + 24, colWidth, 20,
                    TotemWarningConfig.hotbarYPercent, 0, 100, "§b" + UiText.get("yPosition"),
                    v -> TotemWarningConfig.hotbarYPercent = v
            ));

            this.addDrawableChild(new PercentSliderWidget(
                    rightX, contentY + 48, colWidth, 20,
                    TotemWarningConfig.hotbarWPercent, 1, 100, "§d" + UiText.get("width"),
                    v -> TotemWarningConfig.hotbarWPercent = v
            ));

            this.addDrawableChild(new PercentSliderWidget(
                    rightX, contentY + 72, colWidth, 20,
                    TotemWarningConfig.hotbarHPercent, 1, 100, "§d" + UiText.get("height"),
                    v -> TotemWarningConfig.hotbarHPercent = v
            ));
        }

        // Alt Butonlar
        int bottomY = contentY + 124;
        this.addDrawableChild(ButtonWidget.builder(
                        Text.literal("§a✔ " + UiText.get("saveClose")),
                        button -> this.close())
                .dimensions(leftX, bottomY, colWidth, 20)
                .build());

        this.addDrawableChild(ButtonWidget.builder(
                        Text.literal("§c⟳ " + UiText.get("reset")),
                        button -> {
                            TotemWarningConfig.resetDefaults();
                            this.clearAndInit();
                        })
                .dimensions(rightX, bottomY, colWidth, 20)
                .build());
    }

    private String getAreaModeLabel() {
        String value = switch (TotemWarningConfig.hotbarAreaMode) {
            case 1 -> UiText.get("aboveHotbar");
            case 2 -> UiText.get("screenBorder");
            case 3 -> UiText.get("bottomStrip");
            case 4 -> UiText.get("topStrip");
            default -> UiText.get("customArea");
        };
        return "§6" + UiText.get("areaMode") + ": §f" + value;
    }

    private static String warningLabel(String key, boolean enabled) {
        return (enabled ? "§a✔ " : "§c✘ ") + UiText.get(key) + ": "
                + UiText.get(enabled ? "on" : "off");
    }
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // Iris / Shader uyumluluğu için güvenli arka plan
        try {
            this.renderInGameBackground(context);
        } catch (Throwable ignored) {
            context.fill(0, 0, this.width, this.height, 0x99000000);
        }

        // Başlık
        context.drawCenteredTextWithShadow(
                this.textRenderer, Text.literal("§6§lTotem Warning §7- §f" + UiText.get("title")),
                this.width / 2, 8, 0xFFFFFF
        );

        // Alt açıklama metni
        Text subText = activeTab == 0
                ? Text.literal("§7" + UiText.get("offhandHelp"))
                : Text.literal("§7" + UiText.get("hotbarHelp"));
        context.drawCenteredTextWithShadow(this.textRenderer, subText, this.width / 2, 20, 0xAAAAAA);

        int centerX = this.width / 2;
        int colWidth = 145;
        int gap = 12;
        int rightX = centerX + (gap / 2);

        if (activeTab == 0) {
            renderOffhandPreview(context, rightX, colWidth, 60);
        } else {
            renderHotbarMiniScreen(context, rightX, colWidth, 60);
        }

        super.render(context, mouseX, mouseY, delta);
    }

    /**
     * Sol el uyarısı için renk & saydamlık önizleme kutusu
     */
    private void renderOffhandPreview(DrawContext context, int x, int width, int startY) {
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("§f" + UiText.get("livePreview")), x + width / 2, startY, 0xFFFFFF);

        int previewY = startY + 12;
        int previewH = 64;

        // Dama tahtası
        int checkerSize = 6;
        for (int cy = 0; cy < previewH; cy += checkerSize) {
            for (int cx = 0; cx < width; cx += checkerSize) {
                boolean isDark = ((cx / checkerSize) + (cy / checkerSize)) % 2 == 0;
                int checkerColor = isDark ? 0xFF353535 : 0xFF656565;
                context.fill(x + cx, previewY + cy,
                        Math.min(x + cx + checkerSize, x + width),
                        Math.min(previewY + cy + checkerSize, previewY + previewH),
                        checkerColor);
            }
        }

        // Renk katmanı
        context.fill(x, previewY, x + width, previewY + previewH, TotemWarningConfig.getOffhandColor());

        // Çerçeve
        drawOutline(context, x, previewY, width, previewH, 0xFFFFFFFF);

        // HEX & Alpha metni
        String infoText = String.format("§7HEX: #%02X%02X%02X §8| §7%s: %d%%",
                TotemWarningConfig.red, TotemWarningConfig.green, TotemWarningConfig.blue, UiText.get("alpha"),
                (int) Math.round((TotemWarningConfig.alpha / 255.0) * 100));
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal(infoText), x + width / 2, previewY + previewH + 4, 0xCCCCCC);
    }

    /**
     * Hotbar uyarısı için minyatür monitör önizlemesi (Seçilen alan canlı görünür!)
     */
    private void renderHotbarMiniScreen(DrawContext context, int x, int width, int startY) {
        if (TotemWarningConfig.hotbarAreaMode != 0) {
            // Preset modunda daha büyük bir mini ekran çiz
            renderMiniMonitor(context, x, startY + 36, width, 76);
        } else {
            // Özel Alan modunda slider'ların altına kompakt mini ekran çiz
            renderMiniMonitor(context, x, startY + 122, width, 58);
        }
    }

    private void renderMiniMonitor(DrawContext context, int mx, int my, int mw, int mh) {
        // Monitör dış çerçevesi
        context.fill(mx - 2, my - 2, mx + mw + 2, my + mh + 2, 0xFF222222);
        drawOutline(context, mx - 2, my - 2, mw + 4, mh + 4, 0xFF555555);

        // Monitör ekranı (karanlık)
        context.fill(mx, my, mx + mw, my + mh, 0xFF141414);

        // Temsili küçük hotbar (ekranın alt ortasında)
        int miniHbW = (int) (mw * 0.45);
        int miniHbH = 5;
        int miniHbX = mx + (mw - miniHbW) / 2;
        int miniHbY = my + mh - miniHbH - 2;
        context.fill(miniHbX, miniHbY, miniHbX + miniHbW, miniHbY + miniHbH, 0xFF3D3D3D);
        drawOutline(context, miniHbX, miniHbY, miniHbW, miniHbH, 0xFF777777);

        // Seçilen alanın monitör üzerindeki temsili
        int color = TotemWarningConfig.getHotbarColor();
        switch (TotemWarningConfig.hotbarAreaMode) {
            case 1: {
                // Hotbar bölgesi
                context.fill(miniHbX - 1, miniHbY - 1, miniHbX + miniHbW + 1, my + mh, color);
                drawOutline(context, miniHbX - 1, miniHbY - 1, miniHbW + 2, miniHbH + 3, 0xFFFFAA00);
                break;
            }
            case 2: {
                // Çerçeve
                int b = 3;
                context.fill(mx, my, mx + mw, my + b, color);
                context.fill(mx, my + mh - b, mx + mw, my + mh, color);
                context.fill(mx, my + b, mx + b, my + mh - b, color);
                context.fill(mx + mw - b, my + b, mx + mw, my + mh - b, color);
                break;
            }
            case 3: {
                // Alt şerit
                int h = (int) (mh * 0.25);
                context.fill(mx, my + mh - h, mx + mw, my + mh, color);
                drawOutline(context, mx, my + mh - h, mw, h, 0xFFFFAA00);
                break;
            }
            case 4: {
                // Üst şerit
                int h = (int) (mh * 0.25);
                context.fill(mx, my, mx + mw, my + h, color);
                drawOutline(context, mx, my, mw, h, 0xFFFFAA00);
                break;
            }
            case 0:
            default: {
                // Özel Alan (X%, Y%, W%, H%)
                int bx = mx + (int) Math.round(mw * (TotemWarningConfig.hotbarXPercent / 100.0));
                int by = my + (int) Math.round(mh * (TotemWarningConfig.hotbarYPercent / 100.0));
                int bw = Math.max(3, (int) Math.round(mw * (TotemWarningConfig.hotbarWPercent / 100.0)));
                int bh = Math.max(3, (int) Math.round(mh * (TotemWarningConfig.hotbarHPercent / 100.0)));

                int bx2 = Math.min(mx + mw, bx + bw);
                int by2 = Math.min(my + mh, by + bh);

                context.fill(bx, by, bx2, by2, color);
                drawOutline(context, bx, by, bx2 - bx, by2 - by, 0xFF00FFCC);
                break;
            }
        }

        // Monitör etiketi
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("§8[" + UiText.get("screenPreview") + "]"), mx + mw / 2, my + 3, 0x888888);
    }

    private void drawOutline(DrawContext context, int x, int y, int w, int h, int color) {
        context.fill(x, y, x + w, y + 1, color); // Üst
        context.fill(x, y + h - 1, x + w, y + h, color); // Alt
        context.fill(x, y, x + 1, y + h, color); // Sol
        context.fill(x + w - 1, y, x + w, y + h, color); // Sağ
    }

    @Override
    public void close() {
        TotemWarningConfig.save();
        if (this.client != null) {
            this.client.setScreen(this.parent);
        }
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    // ─────────────────────────────────────────────────────────────────────
    //  0–255 arası RGB ve Alpha Değer Slider'ı
    // ─────────────────────────────────────────────────────────────────────
    private static class ColorSliderWidget extends SliderWidget {

        private final String label;
        private final java.util.function.IntConsumer onChange;

        public ColorSliderWidget(int x, int y, int width, int height,
                                 int initialVal, String label, java.util.function.IntConsumer onChange) {
            super(x, y, width, height, Text.empty(), initialVal / 255.0);
            this.label = label;
            this.onChange = onChange;
            this.updateMessage();
        }

        @Override
        protected void updateMessage() {
            int val = (int) Math.round(this.value * 255.0);
            String lbl = this.label != null ? this.label : "";
            this.setMessage(Text.literal(lbl + ": " + val));
        }

        @Override
        protected void applyValue() {
            int val = (int) Math.round(this.value * 255.0);
            if (this.onChange != null) {
                this.onChange.accept(val);
            }
        }
    }

    // ─────────────────────────────────────────────────────────────────────
    //  Yüzdelik (%0 - %100) Ayar Slider'ı
    // ─────────────────────────────────────────────────────────────────────
    private static class PercentSliderWidget extends SliderWidget {

        private final String label;
        private final java.util.function.IntConsumer onChange;
        private final int min;
        private final int max;

        public PercentSliderWidget(int x, int y, int width, int height,
                                   int value, int min, int max,
                                   String label, java.util.function.IntConsumer onChange) {
            super(x, y, width, height, Text.empty(), (double) (value - min) / (max - min));
            this.label = label;
            this.onChange = onChange;
            this.min = min;
            this.max = max;
            this.updateMessage();
        }

        @Override
        protected void updateMessage() {
            int val = min + (int) Math.round(this.value * (max - min));
            String lbl = this.label != null ? this.label : "";
            this.setMessage(Text.literal(lbl + ": %" + val));
        }

        @Override
        protected void applyValue() {
            int val = min + (int) Math.round(this.value * (max - min));
            if (this.onChange != null) {
                this.onChange.accept(val);
            }
        }
    }
}
