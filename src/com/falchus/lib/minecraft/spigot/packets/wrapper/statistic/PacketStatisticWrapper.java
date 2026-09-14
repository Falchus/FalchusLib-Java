package com.falchus.lib.minecraft.spigot.packets.wrapper.statistic;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

<<<<<<< HEAD
class PacketStatisticWrapper extends PacketWrapper implements PacketStatistic {
=======
class PacketStatisticWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git

	PacketStatisticWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
}
