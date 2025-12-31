package com.falchus.lib.minecraft.spigot.utils.nms;

import org.bukkit.Bukkit;

import lombok.Getter;

@Getter
public abstract class AbstractNmsAdapter implements NmsAdapter {

	protected String packageObc = "org.bukkit.craftbukkit.";
	protected String packageNm = "net.minecraft.";
	protected String packageNms = packageNm + "server.";
	
    protected Class<?> nmsItemStack;
	
	public AbstractNmsAdapter() {
		try {
    		String version;
            String packageName = Bukkit.getServer().getClass().getPackageName();
            String[] parts = packageName.split("\\.");
            version = parts.length >= 4 ? parts[3] : "Unknown";
            
    		packageObc = packageObc + (!version.equals("Unknown") ? version + "." : "");
    		packageNms = packageNms + (!version.equals("Unknown") ? version + "." : "");
		} catch (Exception e) {
    		throw new IllegalStateException("Failed to initialize " + getClass().getSimpleName(), e);
    	}
	}
}
