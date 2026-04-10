package com.falchus.lib.minecraft.spigot.packets.wrapper.tabcomplete;

import java.lang.reflect.Field;
import java.util.Set;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedPacketInTabComplete extends PacketTabCompleteWrapper {

	Field command;
	
	public WrappedPacketInTabComplete(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayInTabComplete",
				networkProtocolGame + "PacketPlayInTabComplete"
			)
		);
		
		command = getFirstField(
			"command",
			"b"
		);
	}

	public String getCommand() {
		return getFieldValue(command);
	}
	
	public void setCommand(int command) {
		setField(this.command, command);
	}
}
