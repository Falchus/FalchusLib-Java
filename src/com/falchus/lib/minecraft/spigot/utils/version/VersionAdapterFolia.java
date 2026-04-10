package com.falchus.lib.minecraft.spigot.utils.version;

import com.falchus.lib.minecraft.spigot.utils.SchedulerUtils;
import com.falchus.lib.minecraft.spigot.utils.version.v26_1_R1.VersionAdapter_v26_1_R1;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;
import lombok.NonNull;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * Adapter for <b>Folia 1.21.11</b>.
 * <p>
 * Folia distributes chunk regions across multiple threads. Any task that
 * touches world state must run on the owning region's thread. This adapter
 * extends {@link VersionAdapter_v26_1_R1} and overrides every method that
 * previously relied on {@code Bukkit.getScheduler()}, replacing them with
 * the appropriate Folia scheduler via {@link SchedulerUtils}.
 * <p>
 * The {@link com.falchus.lib.task.Task} class already uses a plain
 * {@link java.util.concurrent.ScheduledExecutorService}, so it works on
 * Folia without changes.
 */
public class VersionAdapterFolia extends VersionAdapter_v26_1_R1 {

    /**
     * Spawns a fake {@code EntityPlayer} for {@code player} and schedules the
     * deferred player-info removal using Folia's global region scheduler so the
     * task lands on the correct thread.
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