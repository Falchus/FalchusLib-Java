package com.falchus.lib.minecraft.spigot.wrapper;

import java.util.List;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.utils.version.IVersionAdapter;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition.*;
import com.falchus.lib.minecraft.spigot.wrapper.nbt.*;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.*;
import com.falchus.lib.minecraft.spigot.wrapper.network.protocol.status.*;
import com.falchus.lib.minecraft.spigot.wrapper.network.syncher.*;
import com.falchus.lib.minecraft.spigot.wrapper.world.*;
import com.falchus.lib.minecraft.spigot.wrapper.world.entity.*;
import com.falchus.lib.minecraft.spigot.wrapper.world.entity.player.*;
import com.falchus.lib.minecraft.spigot.wrapper.world.item.*;
import com.falchus.lib.minecraft.spigot.wrapper.world.level.*;
import com.falchus.lib.minecraft.spigot.wrapper.world.level.block.*;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.*;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.criteria.*;
import com.falchus.lib.utils.wrapper.WrapperRegistry;
import com.falchus.lib.utils.wrapper.impl.FirstClassWrapper;

import lombok.NonNull;

public class SpigotWrapper extends FirstClassWrapper<Object> implements ISpigotWrapper {

	private static final WrapperRegistry<SpigotWrapper> registry = new WrapperRegistry<>(
		handle -> new SpigotWrapper(handle, Set.of(handle.getClass().getName()))
	);

	protected static final IVersionAdapter version = VersionProvider.get();
	
	protected static final String core = version.getPackageNm() + "core.";
	
	protected static final String nbt = version.getPackageNm() + "nbt.";
	
	private static final String network = version.getPackageNm() + "network.";
	protected static final String networkChat = network + "chat.";
	protected static final String networkSynched = network + "synched.";
	private static final String networkProtocol = network + "protocol.";
	protected static final String networkProtocolStatus = networkProtocol + "status.";
	
	private static final String world = version.getPackageNm() + "world.";
	protected static final String worldEntity = version.getPackageNm() + "entity.";
	protected static final String worldEntityDecoration = worldEntity + "decoration.";
	protected static final String worldEntityPlayer = worldEntity + "player.";
	protected static final String worldItem = world + "item.";
	protected static final String worldLevel = world + "level.";
	protected static final String worldLevelBlock = worldLevel + "block.";
	protected static final String worldScores = world + "scores.";
	protected static final String worldScoresCriteria = worldScores + "criteria.";
	protected static final String worldPhys = world + "phys.";
	
	public SpigotWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}
	
	static {
		registry.register(List.of(
			WrappedBaseBlockPosition.class,
			WrappedBlockPosition.class,

			WrappedCompoundTag.class,

			WrappedComponent.class,

			WrappedServerPing.class,
			WrappedServerPing.WrappedPlayers.class,

			WrappedDataWatcher.class,

			WrappedPlayerAbilities.class,

			WrappedEntity.class,
			WrappedEntityArmorStand.class,
			WrappedEntityLiving.class,

			WrappedItemStack.class,

			WrappedBlock.class,

			WrappedWorld.class,

			WrappedScoreboardCriteria.class,

			WrappedScoreboard.class,
			WrappedScoreboardObjective.class,
			WrappedScoreboardScore.class,
			WrappedScoreboardTeam.class,

			WrappedAxisAlignedBB.class
		));
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends ISpigotWrapper> T wrap(Object obj) {
		return (T) registry.wrap(obj);
	}
}
