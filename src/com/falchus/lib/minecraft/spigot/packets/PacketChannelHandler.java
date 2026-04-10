package com.falchus.lib.minecraft.spigot.packets;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.events.player.PlayerPacketEvent;
import com.falchus.lib.minecraft.spigot.events.player.PlayerPacketInEvent;
import com.falchus.lib.minecraft.spigot.events.player.PlayerPacketOutEvent;
import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PacketChannelHandler extends ChannelDuplexHandler {

	private final Player player;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object packet) throws Exception {
		PlayerPacketEvent event = new PlayerPacketInEvent(!Bukkit.isPrimaryThread(), player, PacketWrapper.wrap(packet));
		Bukkit.getPluginManager().callEvent(event);
		if (event.isCancelled()) return;

		super.channelRead(ctx, packet);
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object packet, ChannelPromise promise) throws Exception {
		PlayerPacketEvent event = new PlayerPacketOutEvent(!Bukkit.isPrimaryThread(), player, PacketWrapper.wrap(packet));
		Bukkit.getPluginManager().callEvent(event);
		if (event.isCancelled()) return;

		super.write(ctx, packet, promise);
	}
}
