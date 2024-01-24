package com.natem135.hibana.modules;

import com.natem135.hibana.Hibana;
import com.natem135.hibana.event.ClientPlayerDeathEvent;
import com.natem135.hibana.event.Listener;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import org.lwjgl.glfw.GLFW;

import java.util.function.Consumer;

public class AutoRespawnModule extends ToggleableModule {

    Consumer<ClientPlayerDeathEvent> playerDeathConsumer = AutoRespawnModule::onClientPlayerDeath;
    Listener onClientPlayerDeathListener = new Listener(playerDeathConsumer);

    public AutoRespawnModule() {
        super("AutoRespawn", GLFW.GLFW_KEY_C);
    }

    @Override void onEnable() {
        Hibana.getEventManager().addListener(ClientPlayerDeathEvent.class, onClientPlayerDeathListener);
    }

    @Override void onDisable() {
        Hibana.getEventManager().removeListener(ClientPlayerDeathEvent.class, onClientPlayerDeathListener);
    }

    public void onTick() {
    }

    public static void onClientPlayerDeath(ClientPlayerDeathEvent event) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player != null) {
            player.requestRespawn();
        }
    }
}
