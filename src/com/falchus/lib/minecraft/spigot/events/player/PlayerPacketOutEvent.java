package com.falchus.lib.minecraft.spigot.events.player;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.Getter;
import org.bukkit.entity.Player;

/**
 * Called when a player sends a packet.
 */
@Getter
public class PlayerPacketOutEvent extends PlayerPacketEvent {

    public PlayerPacketOutEvent(boolean async, Player player, PacketWrapper packet) {
        super(async, player, packet);
    }
}
