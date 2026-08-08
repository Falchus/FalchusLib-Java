package com.falchus.lib.minecraft.spigot.enums;

import org.bukkit.util.Vector;

import com.falchus.lib.minecraft.utils.math.FastMath;
import com.falchus.lib.minecraft.utils.math.LegacyFastMath;
import com.falchus.lib.minecraft.utils.math.VanillaMath;

public enum MCMath {
	VANILLA {
		@Override
		public float sin(float radians) {
			return VanillaMath.sin(radians);
		}

		@Override
		public float cos(float radians) {
			return VanillaMath.cos(radians);
		}
	},
	FAST {
		@Override
		public float sin(float radians) {
			return FastMath.sin(radians);
		}

		@Override
		public float cos(float radians) {
			return FastMath.cos(radians);
		}
	},
	LEGACY_FAST {
		@Override
		public float sin(float radians) {
			return LegacyFastMath.sin(radians);
		}

		@Override
		public float cos(float radians) {
			return LegacyFastMath.cos(radians);
		}
	};

	public abstract float sin(float radians);
	public abstract float cos(float radians);

	public Vector getVectorForRotation(float pitch, float yaw) {
		float f = cos(-yaw * 0.017453292F - (float) Math.PI);
		float f1 = sin(-yaw * 0.017453292F - (float) Math.PI);
		float f2 = -cos(-pitch * 0.017453292F);
		float y = sin(-pitch * 0.017453292F);
		return new Vector(f1 * f2, y, f * f2);
	}
}
