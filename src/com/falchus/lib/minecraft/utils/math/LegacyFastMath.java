package com.falchus.lib.minecraft.utils.math;

public class LegacyFastMath {
	
	private static final float[] sinTable = new float[4096];
	
	static {
		for (int i = 0; i < sinTable.length; i++) {
			sinTable[i] = (float) Math.sin((double) (((float) i + 0.5F) / 4096.0F * 6.2831855F));
		}
		
		for (int i = 0; i < 360; i += 90) {
			sinTable[(int) ((float) i * 11.377778F) & 4095] = (float) Math.sin((double) ((float) i * 0.017453292F));
		}
	}
	
	public static float sin(float radians) {
		return sinTable[(int) (radians * 651.8986F) & 4095];
	}
	
	public static float cos(float radians) {
		return sinTable[(int) ((radians + ((float) Math.PI / 2F)) * 651.8986F) & 4095];
	}
}
