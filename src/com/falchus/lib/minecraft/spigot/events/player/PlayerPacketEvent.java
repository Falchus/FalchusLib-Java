package com.falchus.lib.minecraft.spigot.events.player;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Called when a player receives/sends a packet.
 */
@Getter
public class PlayerPacketEvent extends Event implements Cancellable {

    @Getter
    private static final HandlerList handlerList = new HandlerList();
    private final Player player;
    private final PacketWrapper packet;
    @Setter
    private boolean cancelled;

    public PlayerPacketEvent(boolean async, Player player, PacketWrapper packet) {
        super(async);
        this.player = player;
        this.packet = packet;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
