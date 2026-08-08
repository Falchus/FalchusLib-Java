package com.falchus.lib.minecraft.utils.math;

public class VanillaMath {

	private static final float[] sinTable = new float[65536];
	
	static {
		for (int i = 0; i < sinTable.length; i++) {
			sinTable[i] = (float) Math.sin((double) i * Math.PI * 2.0D / 65536.0D);
		}
	}
	
	public static float sin(float radians) {
		return sinTable[(int) (radians * 10430.378F) & 65535];
	}
	
	public static float cos(float radians) {
		return sinTable[(int) (radians * 10430.378F + 16384.0F) & 65535];
	}
}
