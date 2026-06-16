package com.falchus.lib.minecraft.spigot.packets.wrapper.attachentity;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketAttachEntityWrapper extends PacketWrapper implements PacketAttachEntity {
	
	Field sourceId;
	Field destId;

	PacketAttachEntityWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		sourceId = getFirstField(
			"sourceId",
			"b"
		);
		destId = getFirstField(
			"destId",
			"c"
		);
	}

	@Override
	public int getSourceId() {
		return getFieldValue(sourceId);
	}
	
	@Override
	public void setSourceId(int sourceId) {
		setField(this.sourceId, sourceId);
	}

	@Override
	public int getDestId() {
		return getFieldValue(destId);
	}
	
	@Override
	public void setDestId(int destId) {
		setField(this.destId, destId);
	}
}
