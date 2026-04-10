package com.falchus.lib.minecraft.spigot.events.player;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.Getter;
import org.bukkit.entity.Player;

/**
 * Called when a player receives a packet.
 */
@Getter
public class PlayerPacketInEvent extends PlayerPacketEvent {

    public PlayerPacketInEvent(boolean async, Player player, PacketWrapper packet) {
        super(async, player, packet);
    }
}
