package com.falchus.lib.minecraft.spigot.packets.wrapper.playerlistheaderfooter;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.Component;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.WrappedComponent;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketPlayerListHeaderFooterWrapper extends PacketWrapper implements PacketPlayerListHeaderFooter {
=======
class PacketPlayerListHeaderFooterWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	Field header;
	Field footer;

	PacketPlayerListHeaderFooterWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		if (ServerUtils.getVersion().isBefore(Version.v1_20_6)) {
			header = getField("a");
			footer = getFirstField("b");
		} else {
			header = getField("header");
			footer = getFirstField("footer");
		}
	}

	@Override
	public Component getHeader() {
		return SpigotWrapper.wrap(getFieldValue(header));
	}
	
<<<<<<< HEAD
	@Override
	public void setHeader(@NonNull String header) {
		setField(this.header, new WrappedComponent(header).getHandle());
=======
	/**
	 * @param header	IChatBaseComponent
	 */
	public void setHeader(Object header) {
		setField(this.header, header);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}

	@Override
	public Component getFooter() {
		return SpigotWrapper.wrap(getFieldValue(footer));
	}
	
<<<<<<< HEAD
	@Override
	public void setFooter(@NonNull String footer) {
		setField(this.footer, new WrappedComponent(footer).getHandle());
=======
	/**
	 * @param footer	IChatBaseComponent
	 */
	public void setFooter(Object footer) {
		setField(this.footer, footer);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}
