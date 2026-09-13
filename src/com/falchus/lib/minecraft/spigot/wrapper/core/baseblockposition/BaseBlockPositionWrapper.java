package com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class BaseBlockPositionWrapper extends SpigotWrapper implements BaseBlockPosition {
	
	Field x;
	Field y;
	Field z;

	BaseBlockPositionWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		x = getFirstField(
			"x",
			"a"
		);
		y = getFirstField(
			"y",
			"c"
		);
		z = getFirstField(
			"z",
			"d"
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
=======
class BaseBlockPositionWrapper extends SpigotWrapper {
	
	Field x;
	Field y;
	Field z;

	BaseBlockPositionWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		x = getFirstField(
			"x",
			"a"
		);
		y = getFirstField(
			"y",
			"c"
		);
		z = getFirstField(
			"z",
			"d"
		);
	}
	
	public int getX() {
		return getFieldValue(x);
	}
	
	public void setX(int x) {
		setField(this.x, x);
	}
	
	public int getY() {
		return getFieldValue(y);
	}
	
	public void setY(int y) {
		setField(this.y, y);
	}
	
	public int getZ() {
		return getFieldValue(z);
	}
	
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	public void setZ(int z) {
		setField(this.z, z);
	}
}
