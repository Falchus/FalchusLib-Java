package com.falchus.lib.minecraft.spigot.utils.builder;

import com.falchus.lib.minecraft.spigot.utils.nms.NmsAdapter;
import com.falchus.lib.minecraft.spigot.utils.nms.NmsProvider;
import com.falchus.lib.utils.ReflectionUtils;

import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;

/**
 * Builder class for creating and customizing NMS packets.
 * to use with {@link NmsProvider}
 */
@Getter
public class NmsPacketBuilder {

	private final NmsAdapter nms = NmsProvider.get();
	private Class<?> packet;
	private Object[] args = new Object[0];
	
	/**
	 * Creates a new {@link NmsPacketBuilder} for the given packet.
	 */
	public NmsPacketBuilder(@NonNull Class<?> packet) {
		this.packet = packet;
	}
	
	/**
	 * Creates a new {@link NmsPacketBuilder} for the given packet by class name (full package).
	 */
	@SneakyThrows
	public NmsPacketBuilder(@NonNull String className) {
		this.packet = Class.forName(className);
	}
	
	/**
	 * Creates a new {@link NmsPacketBuilder} for the given packet by trying class names (full packages).
	 */
	public NmsPacketBuilder(@NonNull String... classNames) {
		this.packet = ReflectionUtils.getFirstAvailableClass(classNames);
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
		return nms.createPacket(packet, args);
	}
}
