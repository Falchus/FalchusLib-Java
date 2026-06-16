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
class PacketPlayerListHeaderFooterWrapper extends PacketWrapper implements PacketPlayerListHeaderFooter {
	
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
	
	@Override
	public void setHeader(@NonNull String header) {
		setField(this.header, new WrappedComponent(header).getHandle());
	}

	@Override
	public Component getFooter() {
		return SpigotWrapper.wrap(getFieldValue(footer));
	}
	
	@Override
	public void setFooter(@NonNull String footer) {
		setField(this.footer, new WrappedComponent(footer).getHandle());
	}
}
