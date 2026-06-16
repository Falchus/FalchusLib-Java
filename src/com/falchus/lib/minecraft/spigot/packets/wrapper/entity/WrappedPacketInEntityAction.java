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
		));
		
		action = getFirstField(
			"action",
			"animation"
		);
		data = getFirstField(
			"data",
			"c"
		);
	}
	
	public enum Action {
		STOP_SLEEPING,
		START_SPRINTING,
		STOP_SPRINTING,
		OPEN_INVENTORY
	}

	public Action getAction() {
		return Action.valueOf(getFieldValue(action, Enum.class).name());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setAction(Action action) {
		setField(this.action, Enum.valueOf((Class<? extends Enum>) this.action.getType(), action.name()));
	}

	public int getData() {
		return getFieldValue(data);
	}
	
	public void setData(int data) {
		setField(this.data, data);
	}
}
