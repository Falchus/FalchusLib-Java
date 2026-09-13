package com.falchus.lib.minecraft.spigot.packets.wrapper.namedsoundeffect;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketNamedSoundEffectWrapper extends PacketWrapper implements PacketNamedSoundEffect {
=======
class PacketNamedSoundEffectWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	Field x;
	Field y;
	Field z;
	Field volume;

	PacketNamedSoundEffectWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		x = getFirstField(
			"x",
			"b"
		);
		y = getFirstField(
			"y",
			"c"
		);
		z = getFirstField(
			"z",
			"d"
		);
		volume = getFirstField(
			"volume",
			"e"
		);
	}

	@Override
	public int getX() {
		return getFieldValue(x);
	}
	
	@Override
	public void setX(int x) {
		setField(this.x, x);
	}

	@Override
	public int getY() {
		return getFieldValue(y);
	}
	
	@Override
	public void setY(int y) {
		setField(this.y, y);
	}

	@Override
	public int getZ() {
		return getFieldValue(z);
	}
	
	@Override
	public void setZ(int z) {
		setField(this.z, z);
	}

	@Override
	public float getVolume() {
		return getFieldValue(volume);
	}
	
	@Override
	public void setVolume(float volume) {
		setField(this.volume, volume);
	}
}
