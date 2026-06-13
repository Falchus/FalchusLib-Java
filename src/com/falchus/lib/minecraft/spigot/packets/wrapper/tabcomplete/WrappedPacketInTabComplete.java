package com.falchus.lib.minecraft.spigot.packets.wrapper.tabcomplete;

import java.lang.reflect.Field;
import java.util.Set;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedPacketInTabComplete extends PacketTabCompleteWrapper {

	Field command;
	
	private WrappedPacketInTabComplete(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayInTabComplete",
			networkProtocolGame + "PacketPlayInTabComplete"
		));
		
		command = getFirstField(
			"command",
			"a"
		);
	}

	public String getCommand() {
		return getFieldValue(command);
	}
	
	public void setCommand(String command) {
		setField(this.command, command);
	}
}
