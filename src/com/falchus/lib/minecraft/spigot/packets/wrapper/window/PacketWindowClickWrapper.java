package com.falchus.lib.minecraft.spigot.packets.wrapper.window;

import java.lang.reflect.Field;
import java.util.Set;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketWindowClickWrapper extends PacketWindowWrapper implements PacketWindowClick {
	
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

	@Override
	public int getSlotNum() {
		return getFieldValue(slotNum);
	}
	
	@Override
	public void setSlotNum(int slotNum) {
		setField(this.slotNum, slotNum);
	}

	@Override
	public int getButtonNum() {
		return getFieldValue(buttonNum);
	}
	
	@Override
	public void setButtonNum(int buttonNum) {
		setField(this.buttonNum, buttonNum);
	}

	@Override
	public short getUID() {
		return getFieldValue(uid);
	}
	
	@Override
	public void setUID(short uid) {
		setField(this.uid, uid);
	}
}
