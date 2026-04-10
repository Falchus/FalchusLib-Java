package com.falchus.lib.minecraft.spigot.utils.version;

import com.falchus.lib.minecraft.spigot.enums.Sound;
import com.falchus.lib.minecraft.spigot.utils.version.v_1_13_R1.VersionAdapter_v_1_13_R1;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;
import com.falchus.lib.utils.reflection.ReflectionUtils;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Default adapter for all versions 1.17 – 1.20.x. (tested with 1.21.11)
 * <p>
 * 1.21.11 and 26.1.1 are handled by dedicated subclasses.
 */
public class VersionAdapterModern extends VersionAdapter_v_1_13_R1 {

    private Class<?> itemMeta() {
        return ReflectionUtils.getClass(packageOb + "inventory.meta.ItemMeta");
    }

    private Method itemStack_getItemMeta() {
        return ReflectionUtils.getMethod(ItemStack.class, "getItemMeta");
    }

    private Method itemStack_setItemMeta() {
        return ReflectionUtils.getMethod(ItemStack.class, "setItemMeta",
                itemMeta()
        );
    }

    private Method itemMeta_getPDC() {
        return ReflectionUtils.getMethod(itemMeta(), "getPersistentDataContainer");
    }

    private Class<?> namespacedKey() {
        return ReflectionUtils.getClass(packageOb + "NamespacedKey");
    }

    private Class<?> persistentDataType() {
        return ReflectionUtils.getClass(packageOb + "persistence.PersistentDataType");
    }

    @SneakyThrows
    private Object persistentDataType_STRING() throws IllegalAccessException {
        return ReflectionUtils.getField(persistentDataType(), "STRING").get(null);
    }

    private Class<?> persistentDataContainer() {
        return ReflectionUtils.getClass(packageOb + "persistence.PersistentDataContainer");
    }

    private Method persistentDataContainer_set() {
        return ReflectionUtils.getMethod(persistentDataContainer(), "set",
                namespacedKey(),
                persistentDataType(),
                Object.class
        );
    }

    private Method persistentDataContainer_get() {
        return ReflectionUtils.getMethod(persistentDataContainer(), "get",
                namespacedKey(),
                persistentDataType()
        );
    }

    private Method persistentDataContainer_remove() {
        return ReflectionUtils.getMethod(persistentDataContainer(), "remove",
                namespacedKey()
        );
    }

    private Class<?> clientboundPlayerInfoRemovePacket() {
        return ReflectionUtils.getClass(packageNm + "network.protocol.game.ClientboundPlayerInfoRemovePacket");
    }

    private Method chatComponentText_literal() {
        return ReflectionUtils.getMethod(chatComponentText, "literal",
                String.class
        );
    }

    private Class<?> bossBar() {
        return ReflectionUtils.getClass(packageOb + "boss.BossBar");
    }

    private Method bossBar_setProgress() {
        return ReflectionUtils.getMethod(bossBar(), "setProgress",
                double.class
        );
    }

    private Method bossBar_addPlayer() {
        return ReflectionUtils.getMethod(bossBar(), "addPlayer",
                Player.class
        );
    }

    private Method bossBar_removeBossBar() {
        return ReflectionUtils.getMethod(bukkitServer, "removeBossBar",
                namespacedKey()
        );
    }

    private Class<?> barColor() {
        return ReflectionUtils.getClass(packageOb + "boss.BarColor");
    }

    @SneakyThrows
    private Object barColor_WHITE() throws IllegalAccessException {
        return ReflectionUtils.getField(barColor(), "WHITE").get(null);
    }

    private Class<?> barStyle() {
        return ReflectionUtils.getClass(packageOb + "boss.BarStyle");
    }

    @SneakyThrows
    private Object barStyle_SOLID() throws IllegalAccessException {
        return ReflectionUtils.getField(barStyle(), "SOLID").get(null);
    }

    private Class<?> bossFlag() {
        return ReflectionUtils.getClass(packageOb + "boss.BarFlag");
    }

    private Method bukkitServer_createBossBar() {
        return ReflectionUtils.getMethod(bukkitServer, "createBossBar",
                namespacedKey(),
                String.class,
                barColor(),
                barStyle(),
                Array.newInstance(bossFlag(), 0).getClass()
        );
    }

    private Method packetPlayOutScoreboardTeam_createAddOrModifyPacket() {
        return ReflectionUtils.getMethod(packetPlayOutScoreboardTeam, "createAddOrModifyPacket",
                scoreboardTeam,
                boolean.class
        );
    }

    private Method packetPlayOutScoreboardTeam_createRemovePacket() {
        return ReflectionUtils.getMethod(packetPlayOutScoreboardTeam, "createRemovePacket",
                scoreboardTeam
        );
    }

    private Class<?> clientboundSetTitleTextPacket() {
        return ReflectionUtils.getClass(packageNm + "network.protocol.game.ClientboundSetTitleTextPacket");
    }

    private Class<?> clientboundSetSubtitleTextPacket() {
        return ReflectionUtils.getClass(packageNm + "network.protocol.game.ClientboundSetSubtitleTextPacket");
    }

    @Override
    public Object createChatComponentText(@NonNull String text) {
        try {
            return chatComponentText_literal().invoke(null, text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendTitle(@NonNull Player player, String title, String subtitle) {
        try {
            title = title != null ? title : "";
            subtitle = subtitle != null ? subtitle : "";

            Object titlePacket = new ClassInstanceBuilder(
                    clientboundSetTitleTextPacket()
            ).withParams(
                    Map.of(iChatBaseComponent, createChatComponentText(title))
            ).build();
            sendPacket(player, titlePacket);

            Object subtitlePacket = new ClassInstanceBuilder(
                    clientboundSetSubtitleTextPacket()
            ).withParams(
                    Map.of(iChatBaseComponent, createChatComponentText(subtitle))
            ).build();
            sendPacket(player, subtitlePacket);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemStack setUUID(@NonNull ItemStack item, UUID uuid) {
        try {
            Object meta = itemStack_getItemMeta().invoke(item);
            if (meta == null) return item;

            Object pdc = itemMeta_getPDC().invoke(meta);
            Object key = new ClassInstanceBuilder(namespacedKey()).withParams(
                    Map.of(Plugin.class, plugin),
                    Map.of(String.class, "UUID")
            ).build();

            if (uuid == null) {
                persistentDataContainer_remove().invoke(pdc, key);
            } else {
                persistentDataContainer_set().invoke(pdc, key, persistentDataType_STRING(), uuid.toString());
            }
            itemStack_setItemMeta().invoke(item, meta);
            return item;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public UUID getUUID(@NonNull ItemStack item) {
        try {
            Object meta = itemStack_getItemMeta().invoke(item);
            if (meta == null) return null;

            Object pdc = itemMeta_getPDC().invoke(meta);
            Object key = new ClassInstanceBuilder(namespacedKey()).withParams(
                    Map.of(Plugin.class, plugin),
                    Map.of(String.class, "UUID")
            ).build();
            Object uuid = persistentDataContainer_get().invoke(pdc, key, persistentDataType_STRING());
            return uuid != null ? UUID.fromString((String) uuid) : null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemStack clearNBT(@NonNull ItemStack item) {
        try {
            Object meta = itemStack_getItemMeta().invoke(item);
            if (meta == null) return item;

            Object pdc = itemMeta_getPDC().invoke(meta);
            Object key = new ClassInstanceBuilder(namespacedKey()).withParams(
                    Map.of(Plugin.class, plugin),
                    Map.of(String.class, "UUID")
            ).build();
            persistentDataContainer_remove().invoke(pdc, key);
            itemStack_setItemMeta().invoke(item, meta);
            return item;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendBossbar(@NonNull Player player, @NonNull String title, double progress) {
        try {
            removeBossbar(player);

            Object key = new ClassInstanceBuilder(namespacedKey()).withParams(
                    Map.of(Plugin.class, plugin),
                    Map.of(String.class, "Bossbar_" + player.getUniqueId())
            ).build();

            Object bossBar = bukkitServer_createBossBar().invoke(getBukkitServer(),
                    key, title, barColor_WHITE(), barStyle_SOLID(), Array.newInstance(bossFlag(), 0)
            );
            bossBar_setProgress().invoke(bossBar, progress);
            bossBar_addPlayer().invoke(bossBar, player);
            bossBars.put(player, key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeBossbar(@NonNull Player player) {
        try {
            Object key = bossBars.remove(player);
            if (key == null) return;
            bossBar_removeBossBar().invoke(getBukkitServer(), key);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendNametag(@NonNull Player player, @NonNull String prefix, @NonNull String suffix) {
        try {
            Object team = new ClassInstanceBuilder(scoreboardTeam).withParams(
                    Map.of(scoreboard, scoreboardINST),
                    Map.of(String.class, player.getName())
            ).build();
            scoreboardTeam_setDisplayName().invoke(team, createChatComponentText(player.getName()));
            scoreboardTeam_setPrefix().invoke(team, createChatComponentText(prefix));
            scoreboardTeam_setSuffix().invoke(team, createChatComponentText(suffix));

            Object createPacket = packetPlayOutScoreboardTeam_createAddOrModifyPacket().invoke(null, team, false);
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                sendPacket(onlinePlayer, createPacket);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeNametag(@NonNull Player player) {
        try {
            Object team = new ClassInstanceBuilder(scoreboardTeam).withParams(
                    Map.of(scoreboard, scoreboardINST),
                    Map.of(String.class, player.getName())
            ).build();
            scoreboardTeam_setDisplayName().invoke(team, createChatComponentText(player.getName()));

            Object removePacket = packetPlayOutScoreboardTeam_createRemovePacket().invoke(null, team);
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                sendPacket(onlinePlayer, removePacket);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void playSound(@NonNull Player player, @NonNull Location location, @NonNull Sound sound, float volume, float pitch) {
        player.playSound(location, org.bukkit.Sound.valueOf(sound.getModernName()), volume, pitch);
    }

    @Override
    public void removeEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
        try {
            UUID uuid = getProfile(entityPlayer).getId();
            Object packet = new ClassInstanceBuilder(clientboundPlayerInfoRemovePacket()).withParams(
                    Map.of(List.class, List.of(uuid))
            ).build();
            sendPacket(player, packet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}