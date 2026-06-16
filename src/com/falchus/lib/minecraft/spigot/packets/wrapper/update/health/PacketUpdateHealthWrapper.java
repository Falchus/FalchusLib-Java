package com.falchus.lib.minecraft.spigot.packets.wrapper.update.health;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketUpdateHealthWrapper extends PacketWrapper implements PacketUpdateHealth {
	
	Field health;
	Field food;
	Field saturation;
	
	PacketUpdateHealthWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		health = getFirstField(
			"health",
			"a"
		);
		food = getFirstField(
			"food",
			"b"
		);
		saturation = getFirstField(
			"saturation",
			"c"
		);
	}

	@Override
	public float getHealth() {
		return getFieldValue(health);
	}
	
	@Override
	public void setHealth(float health) {
		setField(this.health, health);
	}

	@Override
	public int getFood() {
		return getFieldValue(food);
	}
	
	@Override
	public void setFood(int food) {
		setField(this.food, food);
	}

	@Override
	public float getSaturation() {
		return getFieldValue(saturation);
	}
	
	@Override
	public void setSaturation(int saturation) {
		setField(this.saturation, saturation);
	}
}
