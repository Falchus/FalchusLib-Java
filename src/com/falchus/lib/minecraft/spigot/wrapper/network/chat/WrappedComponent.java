package com.falchus.lib.minecraft.spigot.wrapper.network.chat;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedComponent extends SpigotWrapper implements Component {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "IChatBaseComponent",
		networkChat + "IChatBaseComponent"
	);

	private WrappedComponent(@NonNull Object handle) {
		super(handle, names);
	}
	
	public WrappedComponent(@NonNull String text) {
		this(VersionProvider.get().createChatComponentText(text));
	}
}
