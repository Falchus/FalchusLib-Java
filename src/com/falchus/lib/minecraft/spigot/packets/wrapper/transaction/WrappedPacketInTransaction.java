package com.falchus.lib.minecraft.spigot.packets.wrapper.transaction;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketInTransaction extends PacketTransactionWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayInTransaction",
		networkProtocolGame + "ServerboundPongPacket",
		networkProtocolCommon + "ServerboundPongPacket"
	);

	private WrappedPacketInTransaction(@NonNull Object handle) {
		super(handle, names);
	}
	
	@SuppressWarnings("unchecked")
	public WrappedPacketInTransaction(int id) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			switch (ServerUtils.getVersion()) {
				case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2, v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_4, v1_16_5 -> new Map[] {
					Map.of(
						int.class,
						0
					),
					Map.of(
						short.class,
						(short) id
					),
					Map.of(
						boolean.class,
						false
					)
				};
				
				default -> new Map[] {
					Map.of(
						int.class,
						id
					)
				};
			}
		).build());
	}
}
