package com.falchus.lib.minecraft.spigot.player.elements.impl;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import io.papermc.paper.event.player.AsyncChatEvent;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Sets a custom chat prefix for a player.
 * <p>
 * On servers that expose the Paper 26.x {@code AsyncChatEvent},
 * that event is preferred because {@link AsyncPlayerChatEvent} is deprecated there.
 * The detection is done at runtime via reflection so the library can still compile
 * against plain Spigot.
 */
public class Chat extends PlayerElement implements Listener {

    private static final Map<UUID, Supplier<String>> prefixSuppliers = new HashMap<>();
    private static final Map<UUID, String> lastPrefixes = new HashMap<>();
    private static final List<Boolean> registered = new ArrayList<>();

    /**
     * {@code true} when Paper's new {@code AsyncChatEvent} is on the classpath.
     */
    private static final boolean USE_PAPER_CHAT_EVENT = detectPaperChatEvent();

    private Chat(@NonNull Player player) {
        super(player);
    }

    private static boolean detectPaperChatEvent() {
        try {
            Class.forName("io.papermc.paper.event.player.AsyncChatEvent");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * Applies a one-time prefix supplier.
     */
    public void send(@NonNull Supplier<String> prefix) {
        if (registered.isEmpty()) {
            Bukkit.getPluginManager().registerEvents(this, plugin);
            registered.add(true);
        }

        prefixSuppliers.put(player.getUniqueId(), prefix);

        updateRunnable = () -> {
            String lastPrefix = lastPrefixes.get(player.getUniqueId());
            String newPrefix = prefixSuppliers.get(player.getUniqueId()).get();
            if (lastPrefix == null || !newPrefix.equals(lastPrefix)) {
                lastPrefixes.put(player.getUniqueId(), newPrefix);
            }
        };
        update();
    }

    /**
     * Applies a prefix supplier that refreshes at the given interval (ms).
     */
    public void sendUpdating(long intervalTicks, @NonNull Supplier<String> prefix) {
        super.sendUpdating(intervalTicks, () -> send(prefix));
    }

    @Override
    public void remove() {
        super.remove();
        prefixSuppliers.remove(player.getUniqueId());
        lastPrefixes.remove(player.getUniqueId());

        if (!registered.isEmpty()) {
            HandlerList.unregisterAll(this);
            registered.clear();
        }
    }

    /**
     * Legacy handler — active on Spigot / Paper ≤ 25.x.
     * <p>
     * On Paper 26.x this event is deprecated but still fires, so the handler
     * remains as a fallback; the Paper-native handler below takes precedence
     * when {@link #USE_PAPER_CHAT_EVENT} is {@code true}.
     */
    @EventHandler(ignoreCancelled = true)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        if (USE_PAPER_CHAT_EVENT) return; // let the Paper handler deal with it

        applyPrefix(event.getPlayer(), prefix -> event.setFormat(prefix + "%2$s"));
    }

    /**
     * Paper 26.x handler — registered reflectively at runtime so the class
     * compiles against plain Spigot without the Paper dependency.
     * <p>
     * The method is package-private to allow reflective invocation from the
     * {@link PaperChatEventBridge} registered below.
     */
    void onPaperAsyncChat(Player player, AsyncChatEvent event) {
        applyPrefix(player, prefix ->
                event.renderer((source, displayName, message, viewer) ->
                        Component.text(prefix)
                                .append(message)
                )
        );
    }

    private void applyPrefix(@NonNull @NotNull Player player, @NonNull Consumer<String> apply) {
        Supplier<String> supplier = prefixSuppliers.get(player.getUniqueId());
        if (supplier == null) return;
        apply.accept(supplier.get());
    }

    /**
     * Separate listener class registered only when Paper's {@code AsyncChatEvent}
     * is present. Keeping it in a nested class avoids a {@link NoClassDefFoundError}
     * on plain Spigot where the Paper event class does not exist.
     */
    public static class PaperChatEventBridge implements Listener {

        private final Chat chat;

        public PaperChatEventBridge(@NonNull Chat chat) {
            this.chat = chat;
        }

        @EventHandler(ignoreCancelled = true)
        public void onAsyncChat(AsyncChatEvent event) {
            chat.onPaperAsyncChat(event.getPlayer(), event);
        }
    }
}