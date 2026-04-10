package com.falchus.lib.minecraft.spigot.utils.version.v26_1_R1;

import com.falchus.lib.minecraft.spigot.enums.Sound;
import com.falchus.lib.minecraft.spigot.utils.version.v1_21_R1.VersionAdapter_v1_21_R1;
import com.falchus.lib.minecraft.utils.AdventureUtils;
import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

/**
 * Adapter for Paper 26.1.1 (Minecraft 1.21.x, Paper API v5+).
 * <p>
 * Paper 26.1.1 deprecates most legacy {@code String}-based player API in favour
 * of Adventure {@link Component}s. This adapter replaces the affected methods
 * with their Adventure equivalents available directly on {@link Player}:
 * <ul>
 *     <li>{@code sendTitle(String, String)} → {@link Player#showTitle(Title)}</li>
 *     <li>Tab-list header/footer → {@link Player#sendPlayerListHeaderAndFooter(Component, Component)}</li>
 *     <li>Player list name → {@link Player#playerListName(Component)}</li>
 * </ul>
 * NMS packet sending is inherited unchanged from {@link VersionAdapter_v1_21_R1}.
 */
public class VersionAdapter_v26_1_R1 extends VersionAdapter_v1_21_R1 {

    /**
     * Uses the Adventure {@link Player#showTitle(Title)} API.
     */
    @Override
    public void sendTitle(@NonNull Player player, String title, String subtitle) {
        String t = title != null ? title : "";
        String st = subtitle != null ? subtitle : "";

        player.showTitle(Title.title(
                AdventureUtils.legacy(t),
                AdventureUtils.legacy(st)
        ));
    }

    /**
     * Uses {@link Player#sendPlayerListHeaderAndFooter(Component, Component)} and
     * {@link Player#playerListName(Component)}, replacing the deprecated
     * {@code setPlayerListHeaderFooter} / {@code setPlayerListName} String overloads.
     */
    @Override
    public void sendTablist(@NonNull Player player, List<String> header, List<String> footer, String name) {
        String headerText = header != null ? String.join("\n", header) : "";
        String footerText = footer != null ? String.join("\n", footer) : "";

        player.sendPlayerListHeaderAndFooter(
                AdventureUtils.legacy(headerText),
                AdventureUtils.legacy(footerText)
        );
        player.playerListName(name != null ? AdventureUtils.legacy(name) : null);
    }

    /**
     * In Paper 26.1.1 some legacy sound enum names differ; routing through
     * {@link Sound#getModernName()} covers this. Method body is identical
     * to the parent but kept explicit for traceability.
     */
    @Override
    public void playSound(@NonNull Player player, @NonNull Location location,
                          @NonNull Sound sound, float volume, float pitch) {
        player.playSound(location, org.bukkit.Sound.valueOf(sound.getModernName()), volume, pitch);
    }
}