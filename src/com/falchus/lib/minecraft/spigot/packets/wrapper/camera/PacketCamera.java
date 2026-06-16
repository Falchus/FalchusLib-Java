package com.falchus.lib.minecraft.spigot.packets.wrapper.camera;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketCamera extends IPacketWrapper {

	int getCameraId();
	void setCameraId(int cameraId);
}
