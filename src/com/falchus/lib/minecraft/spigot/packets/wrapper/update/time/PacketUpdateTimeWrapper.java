package com.falchus.lib.minecraft.spigot.packets.wrapper.update.time;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketUpdateTimeWrapper extends PacketWrapper implements PacketUpdateTime {
	
	Field gameTime;
	Field dayTime;

	PacketUpdateTimeWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		gameTime = getFirstField(
			"gameTime",
			"a"
		);
		dayTime = getFirstField(
			"dayTime",
			"b"
		);
	}

	@Override
	public long getGameTime() {
		return getFieldValue(gameTime);
	}
	
	@Override
	public void setGameTime(long gameTime) {
		setField(this.gameTime, gameTime);
	}

	@Override
	public long getDayTime() {
		return getFieldValue(dayTime);
	}
	
	@Override
	public void setDayTime(long dayTime) {
		setField(this.dayTime, dayTime);
	}
}
