package com.falchus.lib.minecraft.spigot.utils.builder;

import com.falchus.lib.minecraft.spigot.enums.PacketType;
import com.falchus.lib.minecraft.spigot.utils.nms.NmsAdapter;
import com.falchus.lib.minecraft.spigot.utils.nms.NmsProvider;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

/**
 * Builder class for creating and customizing NMS packets.
 * to use with {@link NmsProvider}
 */
@Getter
@RequiredArgsConstructor
public class NmsPacketBuilder {

	private final NmsAdapter adapter = NmsProvider.get();
	private final PacketType type;
	private Class<?> packet;
	private Object[] args = new Object[0];
	
	/**
	 * Sets the NMS packet class to build.
	 */
	public NmsPacketBuilder packet(@NonNull Class<?> packet) {
		this.packet = packet;
		return this;
	}
	
	/**
	 * Sets the NMS packet class by class name (full package).
	 */
	@SneakyThrows
	public NmsPacketBuilder packet(@NonNull String className) {
		String packageName = type == PacketType.OBC ? adapter.getPackageObc() : adapter.getPackageNms();
		this.packet = Class.forName(packageName + className);
		return this;
	}
	
	/**
	 * Sets constructor arguments for the packet.
	 */
	public NmsPacketBuilder withArgs(Object... args) {
        if (args != null) {
            this.args = args;
        }
		return this;
	}
	
	/**
	 * Builds and returns the final packet instance.
	 */
	public Object build() {
		if (packet == null) {
			throw new IllegalStateException("Packet class must be set");
		}
		return adapter.createPacket(packet, args);
	}
}
