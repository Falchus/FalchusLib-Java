package com.falchus.lib.minecraft.spigot.packets.wrapper.server.difficulty;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketServerDifficultyWrapper extends PacketWrapper implements PacketServerDifficulty {
	
	Field difficulty;
	Field locked;

	PacketServerDifficultyWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		difficulty = getFirstField(
			"difficulty",
			"a"
		);
		locked = getFirstField(
			"locked",
			"b"
		);
	}

	@Override
	public Difficulty getDifficulty() {
		return Difficulty.valueOf(getFieldValue(difficulty, Enum.class).name());
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void setDifficulty(Difficulty difficulty) {
		setField(this.difficulty, Enum.valueOf((Class<? extends Enum>) this.difficulty.getType(), difficulty.name()));
	}

	@Override
	public boolean isLocked() {
		return getFieldValue(locked);
	}
	
	@Override
	public void setLocked(boolean locked) {
		setField(this.locked, locked);
	}
}
