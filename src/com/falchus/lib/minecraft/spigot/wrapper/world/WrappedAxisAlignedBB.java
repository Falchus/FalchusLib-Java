package com.falchus.lib.minecraft.spigot.wrapper.world;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
public class WrappedAxisAlignedBB extends SpigotWrapper implements AxisAlignedBB {
	
	Field minX;
	Field minY;
	Field minZ;
	Field maxX;
	Field maxY;
	Field maxZ;

	private WrappedAxisAlignedBB(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "AxisAlignedBB",
			worldPhys + "AxisAlignedBB"
		));
		
		minX = getFirstField(
			"minX",
			"a"
		);
		minY = getFirstField(
			"minY",
			"b"
		);
		minZ = getFirstField(
			"minZ",
			"c"
		);
		maxX = getFirstField(
			"maxX",
			"d"
		);
		maxY = getFirstField(
			"maxY",
			"e"
		);
		maxZ = getFirstField(
			"maxZ",
			"f"
		);
	}
	
	@Override
	public double getMinX() {
		return getFieldValue(minX);
	}
	
	@Override
	public void setMinX(double minX) {
		setField(this.minX, minX);
	}
	
	@Override
	public double getMinY() {
		return getFieldValue(minY);
	}
	
	@Override
	public void setMinY(double minY) {
		setField(this.minY, minY);
	}
	
	@Override
	public double getMinZ() {
		return getFieldValue(minZ);
	}
	
	@Override
	public void setMinZ(double minZ) {
		setField(this.minZ, minZ);
	}
	
	@Override
	public double getMaxX() {
		return getFieldValue(maxX);
	}
	
	@Override
	public void setMaxX(double maxX) {
		setField(this.maxX, maxX);
	}
	
	@Override
	public double getMaxY() {
		return getFieldValue(maxY);
	}
	
	@Override
	public void setMaxY(double maxY) {
		setField(this.maxY, maxY);
	}
	
	@Override
	public double getMaxZ() {
		return getFieldValue(maxZ);
	}
	
	@Override
=======
public class WrappedAxisAlignedBB extends SpigotWrapper {
	
	Field minX;
	Field minY;
	Field minZ;
	Field maxX;
	Field maxY;
	Field maxZ;

	WrappedAxisAlignedBB(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "AxisAlignedBB",
			worldPhys + "AxisAlignedBB"
		));
		
		minX = getFirstField(
			"minX",
			"a"
		);
		minY = getFirstField(
			"minY",
			"b"
		);
		minZ = getFirstField(
			"minZ",
			"c"
		);
		maxX = getFirstField(
			"maxX",
			"d"
		);
		maxY = getFirstField(
			"maxY",
			"e"
		);
		maxZ = getFirstField(
			"maxZ",
			"f"
		);
	}
	
	public double getMinX() {
		return getFieldValue(minX);
	}
	
	public void setMinX(double minX) {
		setField(this.minX, minX);
	}
	
	public double getMinY() {
		return getFieldValue(minY);
	}
	
	public void setMinY(double minY) {
		setField(this.minY, minY);
	}
	
	public double getMinZ() {
		return getFieldValue(minZ);
	}
	
	public void setMinZ(double minZ) {
		setField(this.minZ, minZ);
	}
	
	public double getMaxX() {
		return getFieldValue(maxX);
	}
	
	public void setMaxX(double maxX) {
		setField(this.maxX, maxX);
	}
	
	public double getMaxY() {
		return getFieldValue(maxY);
	}
	
	public void setMaxY(double maxY) {
		setField(this.maxY, maxY);
	}
	
	public double getMaxZ() {
		return getFieldValue(maxZ);
	}
	
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	public void setMaxZ(double maxZ) {
		setField(this.maxZ, maxZ);
	}
}
