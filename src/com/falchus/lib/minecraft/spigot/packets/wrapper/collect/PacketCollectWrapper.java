package com.falchus.lib.minecraft.spigot.packets.wrapper.collect;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketCollectWrapper extends PacketWrapper implements PacketCollect {
=======
class PacketCollectWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	Field itemId;
	Field playerId;

	PacketCollectWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		itemId = getFirstField(
			"itemId",
			"a"
		);
		playerId = getFirstField(
			"playerId",
			"b"
		);
	}

	@Override
	public int getItemId() {
		return getFieldValue(itemId);
	}
	
	@Override
	public void setItemId(int itemId) {
		setField(this.itemId, itemId);
	}

	@Override
	public int getPlayerId() {
		return getFieldValue(playerId);
	}
	
	@Override
	public void setPlayerId(int playerId) {
		setField(this.playerId, playerId);
	}
}
