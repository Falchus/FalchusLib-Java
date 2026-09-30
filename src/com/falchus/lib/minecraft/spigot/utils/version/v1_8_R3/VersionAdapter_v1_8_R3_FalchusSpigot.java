package com.falchus.lib.minecraft.spigot.utils.version.v1_8_R3;

import com.falchus.lib.enums.TaskPriority;
import com.falchus.lib.minecraft.spigot.utils.version.VersionAdapter;
import com.falchus.lib.task.Promise;
import com.falchus.lib.utils.reflection.ReflectionUtils;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;

import java.lang.reflect.Method;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class VersionAdapter_v1_8_R3_FalchusSpigot extends VersionAdapter {

	Method world_falchusSpigot;
	Class<?> world$falchusSpigot;
	Method world$falchusSpigot_getChunkAtAsync;

	public VersionAdapter_v1_8_R3_FalchusSpigot() {
		try {
			world_falchusSpigot = ReflectionUtils.getMethod(World.class, "falchusSpigot");
			world$falchusSpigot = ReflectionUtils.getClass(packageOb + "World$FalchusSpigot");
			world$falchusSpigot_getChunkAtAsync = ReflectionUtils.getMethod(world$falchusSpigot, "getChunkAtAsync",
				Location.class,
				boolean.class,
				TaskPriority.class
			);
		} catch (Exception e) {
			throw new IllegalStateException("Failed to initialize " + getClass().getSimpleName(), e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Promise<Chunk> getChunkAtAsync(@NonNull World world, @NonNull Location location, boolean gen, @NonNull TaskPriority priority) {
		try {
			Object spigot = world_falchusSpigot.invoke(world);

			return (Promise<Chunk>) world$falchusSpigot_getChunkAtAsync.invoke(spigot,
				location,
				gen,
				priority
			);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
