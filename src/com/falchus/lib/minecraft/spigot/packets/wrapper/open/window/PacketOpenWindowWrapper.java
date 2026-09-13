package com.falchus.lib.minecraft.spigot.packets.wrapper.open.window;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.Component;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketOpenWindowWrapper extends PacketWrapper implements PacketOpenWindow {
=======
class PacketOpenWindowWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	Field containerId;
	Field title;

	PacketOpenWindowWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		containerId = getFirstField(
			"containerId",
			"a"
		);
		title = getFirstField(
			"title",
			"c"
		);
	}

	@Override
	public int getContainerId() {
		return getFieldValue(containerId);
	}
	
	@Override
	public void setContainerId(int containerId) {
		setField(this.containerId, containerId);
	}

	@Override
	public Component getTitle() {
		return SpigotWrapper.wrap(getFieldValue(title));
	}
	
<<<<<<< HEAD
	@Override
	public void setTitle(Component title) {
		setField(this.title, title.getHandle());
=======
	/**
	 * @param title	IChatBaseComponent
	 */
	public void setTitle(Object title) {
		setField(this.title, title);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}
