package com.totemwarning;

import com.totemwarning.config.TotemWarningConfig;
import com.totemwarning.hud.TotemWarningHud;
import com.totemwarning.screen.TotemWarningConfigScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TotemWarningClient implements ClientModInitializer {

    public static final String MOD_ID = "totemwarning";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final KeyBinding.Category CATEGORY = KeyBinding.Category.create(
            Identifier.of(MOD_ID, "main")
    );

    private static KeyBinding configKeyBinding;

    @Override
    public void onInitializeClient() {
        LOGGER.info("Totem Warning mod yükleniyor...");

        // Config dosyasını yükle
        TotemWarningConfig.load();

        // Ayar ekranını açmak için tuş atama (varsayılan: K)
        configKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.totemwarning.config",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                CATEGORY
        ));

        // HUD render callback — her kare çiziminde çağrılır
        HudRenderCallback.EVENT.register(TotemWarningHud::render);

        // Her tick'te tuş kontrolü
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (configKeyBinding.wasPressed()) {
                if (client.player != null && client.currentScreen == null) {
                    client.setScreen(new TotemWarningConfigScreen(null));
                }
            }
        });

        LOGGER.info("Totem Warning mod yüklendi!");
    }
}
