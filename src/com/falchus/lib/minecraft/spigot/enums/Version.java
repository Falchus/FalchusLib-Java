package com.falchus.lib.minecraft.spigot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Version {
	v1_8_8(1, 8, 8),
	
	v1_9(1, 9),
	v1_9_2(1, 9, 2),
	v1_9_4(1, 9, 4),
	
	v1_10(1, 10),
	v1_10_2(1, 10, 2),
	
	v1_11(1, 11),
	v1_11_1(1, 11, 1),
	v1_11_2(1, 11, 2),
	
	v1_12(1, 12),
	v1_12_1(1, 12, 1),
	v1_12_2(1, 12, 2),
	
	v1_13(1, 13),
	v1_13_1(1, 13, 1),
	v1_13_2(1, 13, 2),
	
	v1_14(1, 14),
	v1_14_1(1, 14, 1),
	v1_14_2(1, 14, 2),
	v1_14_3(1, 14, 3),
	v1_14_4(1, 14, 4),
	
	v1_15(1, 15),
	v1_15_1(1, 15, 1),
	v1_15_2(1, 15, 2),
	
	v1_16(1, 16),
	v1_16_1(1, 16, 1),
	v1_16_2(1, 16, 2),
	v1_16_3(1, 16, 3),
	v1_16_4(1, 16, 4),
	v1_16_5(1, 16, 5),
	
	v1_17(1, 17),
	v1_17_1(1, 17, 1),
	
	v1_18(1, 18),
	v1_18_1(1, 18, 1),
	v1_18_2(1, 18, 2),
	
	v1_19(1, 19),
	v1_19_1(1, 19, 1),
	v1_19_2(1, 19, 2),
	v1_19_3(1, 19, 3),
	v1_19_4(1, 19, 4),
	
	v1_20_1(1, 20, 1),
	v1_20_2(1, 20, 2),
	v1_20_4(1, 20, 4),
	v1_20_6(1, 20, 6),
	
	v1_21(1, 21),
	v1_21_1(1, 21, 1),
	v1_21_3(1, 21, 3),
	v1_21_4(1, 21, 4),
	v1_21_5(1, 21, 5),
	v1_21_6(1, 21, 6),
	v1_21_7(1, 21, 7),
	v1_21_8(1, 21, 8),
	v1_21_9(1, 21, 9),
	v1_21_10(1, 21, 10),
	v1_21_11(1, 21, 11),
	
	v26_1(26, 1),
	v26_1_1(26, 1, 1),
	v26_1_2(26, 1, 2);
	
	private final int major;
	private final int minor;
	private final int patch;
	
	Version(int major, int minor) {
		this(major, minor, 0);
	}
	
	public boolean isAfter(Version version) {
		if (major != version.major) {
			return major > version.major;
		}
		
		if (minor != version.minor) {
			return minor > version.minor;
		}
		return patch > version.patch;
	}
	
	public boolean isBefore(Version version) {
		if (major != version.major) {
			return major < version.major;
		}
		
		if (minor != version.minor) {
			return minor < version.minor;
		}
		return patch < version.patch;
	}
}
