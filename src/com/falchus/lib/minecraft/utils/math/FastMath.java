package com.falchus.lib.minecraft.utils.math;

public class FastMath {
	
	private static final float[] sinTable = new float[4096];
	private static final float radToIndex = roundToFloat(651.8986469044033D);
	
	static {
		for (int i = 0; i < sinTable.length; i++) {
			sinTable[i] = roundToFloat(Math.sin((double) i * Math.PI * 2.0D / 4096.0D));
		}
	}
	
	public static float sin(float radians) {
		return sinTable[(int) (radians * radToIndex) & 4095];
	}
	
	public static float cos(float radians) {
		return sinTable[(int) (radians * radToIndex + 1024.0F) & 4095];
	}
	
	private static float roundToFloat(double d) {
		return (float) ((double) Math.round(d * 1.0E8D) / 1.0E8D);
	}
}
