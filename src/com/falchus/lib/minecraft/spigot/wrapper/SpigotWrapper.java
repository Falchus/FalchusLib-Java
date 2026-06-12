package com.falchus.lib.minecraft.spigot.wrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import com.falchus.lib.minecraft.spigot.utils.version.IVersionAdapter;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition.*;
import com.falchus.lib.minecraft.spigot.wrapper.world.*;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;
import com.falchus.lib.utils.reflection.Dummy;
import com.falchus.lib.utils.wrapper.impl.FirstClassWrapper;

import lombok.NonNull;

public class SpigotWrapper extends FirstClassWrapper<Object> {
	
	protected static final IVersionAdapter version = VersionProvider.get();
	
	protected static final String core = version.getPackageNm() + "core.";
	
	private static final String world = version.getPackageNm() + "world.";
	protected static final String worldPhys = world + "phys.";

	private static final Map<Class<?>, Function<Object, SpigotWrapper>> registry = new HashMap<>();
	
	public SpigotWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
	
	@SuppressWarnings("unchecked")
	private static <T extends SpigotWrapper> void register() {
		if (!registry.isEmpty()) return;
		Class<T>[] wrappers = new Class[] {
			WrappedBaseBlockPosition.class,
			WrappedBlockPosition.class,
			
			WrappedAxisAlignedBB.class
		};
		
		for (Class<T> wrapper : wrappers) {
			SpigotWrapper dummy = (T) new ClassInstanceBuilder(
				wrapper
			).withParams(
				Map.of(
					Object.class,
					Dummy.instance
				)
			).build();
			for (Class<?> clazz : dummy.getClasses()) {
				registry.put(clazz, obj -> {
					return (T) new ClassInstanceBuilder(
						wrapper
					).withParams(
						Map.of(
							Object.class,
							obj
						)
					).build();
				});
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends SpigotWrapper> T wrap(Object packet) {
		register();
		
		Function<Object, SpigotWrapper> wrapper = registry.get(packet.getClass());
		if (wrapper != null) {
			return (T) wrapper.apply(packet);
		}
		return (T) new SpigotWrapper(packet, Set.of(packet.getClass().getName()));
	}
}
