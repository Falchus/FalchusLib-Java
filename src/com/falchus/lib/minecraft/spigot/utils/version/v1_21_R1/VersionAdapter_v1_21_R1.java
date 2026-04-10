package com.falchus.lib.minecraft.spigot.utils.version.v1_21_R1;

import com.falchus.lib.minecraft.spigot.utils.SchedulerUtils;
import com.falchus.lib.minecraft.spigot.utils.version.VersionAdapterModern;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;
import com.falchus.lib.utils.reflection.ReflectionUtils;
import lombok.NonNull;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Adapter for Minecraft 1.21.11.
 * <p>
 * Changes vs. {@link VersionAdapterModern}:
 * <ul>
 *     <li>Uses {@link SchedulerUtils} for {@link #spawnEntityPlayer} so the
 *         delayed removal is safe on both Bukkit and Folia.</li>
 *     <li>Resolves the {@code recentTps} field with additional fallback names
 *         introduced in Paper 1.21.x.</li>
 *     <li>Handles the renamed {@code PlayerConnection} → {@code ServerGamePacketListenerImpl}
 *         path used by Mojang-mapped Paper builds.</li>
 * </ul>
 */
public class VersionAdapter_v1_21_R1 extends VersionAdapterModern {

    private static final String PACKET_LISTENER_CLASS =
            "net.minecraft.server.network.ServerGamePacketListenerImpl";

    /**
     * Returns recent TPS, resolving the field name used in 1.21.x Paper builds
     * ({@code recentTps} still present; {@code tickTimes} added as alternative).
     */
    @Override
    public double[] getRecentTps() {
        try {
            Object server = getMcServer();
            Field tpsField = ReflectionUtils.getFirstField(
                    server.getClass(),
                    "recentTps",
                    "tickTimes"
            );
            Object value = tpsField.get(server);
            // tickTimes stores nanoseconds per tick; convert to TPS approximation when needed
            if (value instanceof long[] times) {
                double[] tps = new double[times.length];
                for (int i = 0; i < times.length; i++) {
                    double mspt = times[i] / 1_000_000.0;
                    tps[i] = mspt == 0 ? 20.0 : Math.min(20.0, 1000.0 / mspt);
                }
                return tps;
            }
            return (double[]) value;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Resolves the send-packet method on 1.21.x where the connection class
     * may be at a Mojang-mapped path.
     */
    @Override
    public void sendPacket(@NonNull Player player, @NonNull Object packet) {
        try {
            Object entityPlayer = getEntityPlayer(player);
            Object connection = entityPlayer_playerConnection.get(entityPlayer);
            Method send = ReflectionUtils.getFirstMethod(
                    connection.getClass(),
                    List.of(this.packet),
                    "send",
                    "sendPacket"
            );
            send.invoke(connection, packet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Overrides {@link VersionAdapterModern#spawnEntityPlayer} to use
     * {@link SchedulerUtils#runTask} so the deferred player-info removal
     * is scheduled correctly on both Bukkit and Folia.
     */
    @Override
    public void spawnEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
        try {
            addEntityPlayer(player, entityPlayer);

            Object spawn = new ClassInstanceBuilder(
                    packageNms + "PacketPlayOutNamedEntitySpawn",
                    packageNm + "network.protocol.game.ClientboundAddPlayerPacket"
            ).withParams(
                    Map.of(entityHuman, entityPlayer)
            ).build();
            sendPacket(player, spawn);

            Object teleport = new ClassInstanceBuilder(
                    packageNms + "PacketPlayOutEntityTeleport",
                    packageNm + "network.protocol.game.ClientboundPlayerPositionPacket"
            ).withParams(
                    Map.of(entity, entityPlayer)
            ).build();
            sendPacket(player, teleport);

            SchedulerUtils.runTask(() -> removeEntityPlayer(player, entityPlayer));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}