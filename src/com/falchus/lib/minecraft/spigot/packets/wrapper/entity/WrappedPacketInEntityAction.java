package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.lang.reflect.Field;
import java.util.Set;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedPacketInEntityAction extends PacketEntityWrapper {

	Field action;
	Field data;
	
	private WrappedPacketInEntityAction(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayInEntityAction",
				networkProtocolGame + "PacketPlayInEntityAction"
			)
		);
		
		action = getFirstField(
			"action",
			"animation"
		);
		data = getFirstField(
			"data",
			"c"
		);
	}

	/**
	 * @return PacketPlayInEntityAction$EnumPlayerAction
	 */
	public Object getAction() {
		return getFieldValue(action);
	}
	
	/**
	 * @param action: PacketPlayInEntityAction$EnumPlayerAction
	 */
	public void setAction(Object action) {
		setField(this.action, action);
	}

	public int getData() {
		return getFieldValue(data);
	}
	
	public void setData(int data) {
		setField(this.data, data);
	}
}
