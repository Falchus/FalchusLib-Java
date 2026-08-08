package com.falchus.lib.minecraft.spigot.utils;

import org.bukkit.util.Vector;

import com.falchus.lib.minecraft.spigot.wrapper.world.AxisAlignedBB;

public class BoundingBoxUtils {

	public static Vector calculateIntercept(AxisAlignedBB box, Vector vecA, Vector vecB) {
		Vector vec = getIntermediateWithXValue(vecA, vecB, box.getMinX());
		Vector vec1 = getIntermediateWithXValue(vecA, vecB, box.getMaxX());
		Vector vec2 = getIntermediateWithYValue(vecA, vecB, box.getMinY());
		Vector vec3 = getIntermediateWithYValue(vecA, vecB, box.getMaxY());
		Vector vec4 = getIntermediateWithZValue(vecA, vecB, box.getMinZ());
		Vector vec5 = getIntermediateWithZValue(vecA, vecB, box.getMaxZ());
		if (!isVecInYZ(box, vec)) vec = null;
		if (!isVecInYZ(box, vec1)) vec1 = null;
		if (!isVecInXZ(box, vec2)) vec2 = null;
		if (!isVecInXZ(box, vec3)) vec3 = null;
		if (!isVecInXY(box, vec4)) vec4 = null;
		if (!isVecInXY(box, vec5)) vec5 = null;
		
		Vector vec6 = null;
		if (vec != null) {
			vec6 = vec;
		}
		if (vec1 != null && (vec6 == null || vecA.distanceSquared(vec1) < vecA.distanceSquared(vec6))) {
			vec6 = vec1;
		}
		if (vec2 != null && (vec6 == null || vecA.distanceSquared(vec2) < vecA.distanceSquared(vec6))) {
			vec6 = vec2;
		}
		if (vec3 != null && (vec6 == null || vecA.distanceSquared(vec3) < vecA.distanceSquared(vec6))) {
			vec6 = vec3;
		}
		if (vec4 != null && (vec6 == null || vecA.distanceSquared(vec4) < vecA.distanceSquared(vec6))) {
			vec6 = vec4;
		}
		if (vec5 != null && (vec6 == null || vecA.distanceSquared(vec5) < vecA.distanceSquared(vec6))) {
			vec6 = vec5;
		}
		if (vec6 == null) return null;
		return vec6;
	}
	
	public static boolean isVecInside(AxisAlignedBB box, Vector vec) {
		return vec.getX() > box.getMinX() && vec.getX() < box.getMaxX() ? (vec.getY() > box.getMinY() && vec.getY() < box.getMaxY() ? vec.getZ() > box.getMinZ() && vec.getZ() < box.getMaxZ() : false) : false;
	}
	
	/**
	 * Returns a new vector with x value equal to the second parameter, along the line between this vector and the
	 * passed in vector, or null if not possible.
	 */
	private static Vector getIntermediateWithXValue(Vector self, Vector vec, double x) {
		double d0 = vec.getX() - self.getX();
		double d1 = vec.getY() - self.getY();
		double d2 = vec.getZ() - self.getZ();
		if (d0 * d0 < 1.0000000116860974E-7) return null;
		
		double d3 = (x - self.getX()) / d0;
		return d3 >= 0 && d3 <= 1 ? new Vector(self.getX() + d0 * d3, self.getY() + d1 * d3, self.getZ() + d2 * d3) : null;
	}
	
	/**
	 * Returns a new vector with y value equal to the second parameter, along the line between this vector and the
	 * passed in vector, or null if not possible.
	 */
	private static Vector getIntermediateWithYValue(Vector self, Vector vec, double y) {
		double d0 = vec.getX() - self.getX();
		double d1 = vec.getY() - self.getY();
		double d2 = vec.getZ() - self.getZ();
		if (d1 * d1 < 1.0000000116860974E-7) return null;
		
		double d3 = (y - self.getY()) / d1;
		return d3 >= 0 && d3 <= 1 ? new Vector(self.getX() + d0 * d3, self.getY() + d1 * d3, self.getZ() + d2 * d3) : null;
	}
	
	/**
	 * Returns a new vector with z value equal to the second parameter, along the line between this vector and the
	 * passed in vector, or null if not possible.
	 */
	private static Vector getIntermediateWithZValue(Vector self, Vector vec, double z) {
		double d0 = vec.getX() - self.getX();
		double d1 = vec.getY() - self.getY();
		double d2 = vec.getZ() - self.getZ();
		if (d2 * d2 < 1.0000000116860974E-7) return null;
		
		double d3 = (z - self.getZ()) / d2;
		return d3 >= 0 && d3 <= 1 ? new Vector(self.getX() + d0 * d3, self.getY() + d1 * d3, self.getZ() + d2 * d3) : null;
	}
	
	/**
	 * Checks if the specified vector is within the YZ dimensions of the bounding box.
	 */
	private static boolean isVecInYZ(AxisAlignedBB self, Vector vec) {
		return vec == null ? false : vec.getY() >= self.getMinY() && vec.getY() <= self.getMaxY() && vec.getZ() >= self.getMinZ() && vec.getZ() <= self.getMaxZ();
	}
	
	/**
	 * Checks if the specified vector is within the XZ dimensions of the bounding box.
	 */
	private static boolean isVecInXZ(AxisAlignedBB self, Vector vec) {
		return vec == null ? false : vec.getX() >= self.getMinX() && vec.getX() <= self.getMaxX() && vec.getZ() >= self.getMinZ() && vec.getZ() <= self.getMaxZ();
	}
	
	/**
	 * Checks if the specified vector is within the XY dimensions of the bounding box.
	 */
	private static boolean isVecInXY(AxisAlignedBB self, Vector vec) {
		return vec == null ? false : vec.getX() >= self.getMinX() && vec.getX() <= self.getMaxX() && vec.getY() >= self.getMinY() && vec.getY() <= self.getMaxY();
	}
}
