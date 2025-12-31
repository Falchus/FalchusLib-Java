package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.List;
import java.util.function.Supplier;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.enums.PacketType;
import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
import com.falchus.lib.minecraft.spigot.utils.builder.NmsPacketBuilder;
import com.falchus.lib.utils.ReflectionUtils;

import lombok.NonNull;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;

/**
 * Represents a per-player tablist.
 */
public class Tablist extends PlayerElement {
	
	private Tablist(@NonNull Player player) {
		super(player);
	}
	
	/**
	 * Sends a custom header and footer.
	 */
	public void send(List<String> header, List<String> footer, String name) {
	    String headerText = header != null ? String.join("\n", header) : "";
	    String footerText = footer != null ? String.join("\n", footer) : "";
		
		IChatBaseComponent headerComponent = new ChatComponentText(headerText);
        IChatBaseComponent footerComponent = new ChatComponentText(footerText);
        
        Object packet = new NmsPacketBuilder(PacketType.NMS)
        		.packet("PacketPlayOutPlayerListHeaderFooter")
        		.withArgs(headerComponent)
        		.build();

        ReflectionUtils.setField(packet, "b", footerComponent);

        PlayerUtils.sendPacket(player, packet);
        player.setPlayerListName(name);
	}
	
	/**
	 * Updates the tablist periodically with dynamic content.
	 */
	public void sendUpdating(long intervalTicks, Supplier<List<String>> headerSupplier, Supplier<List<String>> footerSupplier, Supplier<String> nameSupplier) {
		super.sendUpdating(intervalTicks, () -> {
			List<String> header = headerSupplier.get();
			List<String> footer = footerSupplier.get();
			String name = nameSupplier.get(); 
			send(header, footer, name);
		});
	}
	
	public void remove() {
		super.remove();
		
		send(null, null, null);
	}
}
