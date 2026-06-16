package com.falchus.lib.minecraft.spigot.packets.wrapper.title;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.Component;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedPacketOutTitleTitle extends PacketTitleWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutTitle",
		networkProtocolGame + "ClientboundSetTitleTextPacket"
	);
	
	Field text;
	
	private WrappedPacketOutTitleTitle(@NonNull Object handle) {
		super(handle, names);
		
		text = getFirstField(
			"text",
			"b"
		);
	}
	
	public WrappedPacketOutTitleTitle(@NonNull Component text) {
		this(VersionProvider.get().createClientboundSetTitleTextPacket(names, text));
	}

	public Component getText() {
		return SpigotWrapper.wrap(getFieldValue(text));
	}
	
	public void setText(Component text) {
		setField(this.text, text.getHandle());
	}
}
