package com.falchus.lib.minecraft.spigot.packets.wrapper.window;

import java.lang.reflect.Field;
import java.util.Set;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketWindowClickWrapper extends PacketUpdateWindow {
	
	Field slotNum;
	Field buttonNum;
	Field uid;

	PacketWindowClickWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		slotNum = getFirstField(
			"slotNum",
			"b"
		);
		buttonNum = getFirstField(
			"buttonNum",
			"c"
		);
		uid = getFirstField(
			"uid",
			"d"
		);
	}

	public int getSlotNum() {
		return getFieldValue(slotNum);
	}
	
	public void setSlotNum(int slotNum) {
		setField(this.slotNum, slotNum);
	}

	public int getButtonNum() {
		return getFieldValue(buttonNum);
	}
	
	public void setButtonNum(int buttonNum) {
		setField(this.buttonNum, buttonNum);
	}

	public short getUID() {
		return getFieldValue(uid);
	}
	
	public void setUID(short uid) {
		setField(this.uid, uid);
	}
}
