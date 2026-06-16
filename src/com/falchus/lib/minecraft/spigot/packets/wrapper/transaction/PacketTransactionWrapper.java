package com.falchus.lib.minecraft.spigot.packets.wrapper.transaction;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketTransactionWrapper extends PacketWrapper implements PacketTransaction {
	
	Field id;

	PacketTransactionWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		id = getFirstField(
			"id",
			"uid",
			"b"
		);
	}
	
	@Override
	public int getId() {
		if (ServerUtils.getVersion().isBefore(Version.v1_17)) {
			return ((Short) getFieldValue(id)).intValue();
		}
		return getFieldValue(id);
	}
	
	@Override
	public void setId(int id) {
		if (ServerUtils.getVersion().isBefore(Version.v1_17)) {
			setField(this.id, (short) id);
		} else {
			setField(this.id, id);
		}
	}
}
