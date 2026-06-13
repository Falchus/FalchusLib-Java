package com.falchus.lib.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import lombok.NonNull;

public class MathUtils {

	/**
	 * Finds the difference between two angles and clamps it to [-180, 180].
	 * @return {@link float}
	 */
	public static float angleDiff(float angle1, float angle2) {
		float diff = (angle1 - angle2) % 360;
		if (diff > 180) diff -= 360;
		if (diff < -180) diff += 360;
		return diff;
	}
	
	/**
	 * Calculate gcd of two floats using modified Euclidian Algorithm.
	 * @return {@link float}
	 */
	public static float gcd(float current, float previous) {
		if (current < previous) return gcd(Math.abs(previous), Math.abs(current));
		if (Math.abs(previous) <= 0.0001) return current;
		return gcd(previous, (float) (current - Math.floor(current / previous) * previous));
	}
	
	public static <T extends Number> T getMode(@NonNull Collection<T> collection) {
		Map<T, Integer> freq = new HashMap<>();
		collection.forEach(val -> {
			int number = freq.getOrDefault(val, 0) + 1;
			freq.put(val, number);
		});
		return Collections.max(freq.entrySet(), Map.Entry.comparingByValue()).getKey();
	}
}
