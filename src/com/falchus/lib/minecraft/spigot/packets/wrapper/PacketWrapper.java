package com.falchus.lib.minecraft.spigot.packets.wrapper;

import java.util.List;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.abilities.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.animation.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.armanimation.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.attachentity.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.block.action.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.block.breakanimation.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.block.change.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.block.dig.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.block.place.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.block.update.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.camera.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.chat.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.clientcommand.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.closewindow.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.collect.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.custompayload.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.enchantitem.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.entity.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.experience.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.explosion.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.flying.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.gamestatechange.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.helditemslot.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.kickdisconnect.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.login.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.map.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.multiblockchange.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.namedsoundeffect.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.open.signeditor.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.open.window.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.playerlistheaderfooter.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.position.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.removeentityeffect.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.resourcepackstatus.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.respawn.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.display.objective.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.objective.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.score.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.team.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.server.difficulty.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.server.info.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.set.creativeslot.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.set.slot.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.settings.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.spawn.entity.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.spawn.position.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.spectate.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.statistic.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.steervehicle.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.tabcomplete.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.tileentitydata.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.title.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.transaction.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.update.attributes.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.update.health.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.update.sign.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.update.time.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.use.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.window.*;
import com.falchus.lib.minecraft.spigot.packets.wrapper.world.event.*;
import com.falchus.lib.minecraft.spigot.utils.version.IVersionAdapter;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.utils.wrapper.WrapperRegistry;
import com.falchus.lib.utils.wrapper.impl.FirstClassWrapper;

import lombok.NonNull;

public class PacketWrapper extends FirstClassWrapper<Object> implements IPacketWrapper {

	private static final WrapperRegistry<PacketWrapper> registry = new WrapperRegistry<>(
		handle -> new PacketWrapper(handle, Set.of(handle.getClass().getName()))
	);

	protected static final IVersionAdapter version = VersionProvider.get();
	private static final String networkProtocol = version.getPackageNm() + "network.protocol.";
	protected static final String networkProtocolCommon = networkProtocol + "common.";
	protected static final String networkProtocolGame = networkProtocol + "game.";
	protected static final String networkProtocolStatus = networkProtocol + "status.";
	
	public PacketWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
	}

	static {
		registry.register(List.of(
			WrappedPacketInAbilities.class,
			WrappedPacketOutAbilities.class,
			
			WrappedPacketOutAnimation.class,
			
			WrappedPacketInArmAnimation.class,
			
			WrappedPacketOutAttachEntity.class,
			
			WrappedPacketOutBlockAction.class,
			
			WrappedPacketOutBlockBreakAnimation.class,
			
			WrappedPacketOutBlockChange.class,
			
			WrappedPacketInBlockDig.class,
			
			WrappedPacketInBlockPlace.class,
			
//			WrappedPacketOutBlockUpdate.class,
			
			WrappedPacketOutCamera.class,
			
			WrappedPacketInChat.class,
			WrappedPacketOutChat.class,
			
			WrappedPacketInClientCommand.class,
			
//			WrappedPacketInCloseWindow.class,
			WrappedPacketOutCloseWindow.class,
			
			WrappedPacketOutCollect.class,
			
			WrappedPacketInCustomPayload.class,
			WrappedPacketOutCustomPayload.class,
			
			WrappedPacketInEnchantItem.class,
			
			WrappedPacketInEntityAction.class,
			WrappedPacketOutEntity.class,
			WrappedPacketOutEntityDestroy.class,
			WrappedPacketOutEntityEffect.class,
			WrappedPacketOutEntityEquipment.class,
			WrappedPacketOutEntityHeadRotation.class,
			WrappedPacketOutEntityMetadata.class,
			WrappedPacketOutEntityStatus.class,
			WrappedPacketOutEntityTeleport.class,
			WrappedPacketOutEntityVelocity.class,
			
			WrappedPacketOutExperience.class,
			
			WrappedPacketOutExplosion.class,
			
			WrappedPacketInFlying.class,
			
			WrappedPacketOutGameStateChange.class,
			
//			WrappedPacketInHeldItemSlot.class,
			WrappedPacketOutHeldItemSlot.class,
			
			WrappedPacketOutKickDisconnect.class,
			
			WrappedPacketOutLogin.class,
			
			WrappedPacketOutMap.class,
			
			WrappedPacketOutMultiBlockChange.class,
			
			WrappedPacketOutNamedSoundEffect.class,
			
			WrappedPacketOutOpenSignEditor.class,
			
			WrappedPacketOutOpenWindow.class,
			
			WrappedPacketOutPlayerListHeaderFooter.class,
			
			WrappedPacketOutPosition.class,
			
			WrappedPacketOutRemoveEntityEffect.class,
			
			WrappedPacketInResourcePackStatus.class,
			
			WrappedPacketOutRespawn.class,
			
			WrappedPacketOutScoreboardDisplayObjective.class,
			
			WrappedPacketOutScoreboardObjective.class,
			
			WrappedPacketOutScoreboardScore.class,
			
			WrappedPacketOutScoreboardTeam.class,
			
			WrappedPacketOutServerDifficulty.class,
			
			WrappedPacketOutServerInfo.class,
			
			WrappedPacketInSetCreativeSlot.class,
			
			WrappedPacketOutSetSlot.class,
			
			WrappedPacketInSettings.class,
			
			WrappedPacketOutSpawnEntity.class,
			WrappedPacketOutSpawnEntityLiving.class,
			
			WrappedPacketOutSpawnPosition.class,
			
			WrappedPacketInSpectate.class,
			
			WrappedPacketOutStatistic.class,
			
			WrappedPacketInSteerVehicle.class,
			
			WrappedPacketInTabComplete.class,
			WrappedPacketOutTabComplete.class,
			
			WrappedPacketOutTileEntityData.class,
			
			WrappedPacketOutAnimationTitle.class,
			WrappedPacketOutSubtitleTitle.class,
			WrappedPacketOutTitleTitle.class,
			
			WrappedPacketInTransaction.class,
			WrappedPacketOutTransaction.class,
			
			WrappedPacketOutUpdateAttributes.class,
			
			WrappedPacketOutUpdateHealth.class,
			
			WrappedPacketInUpdateSign.class,
			
			WrappedPacketOutUpdateTime.class,
			
			WrappedPacketInUseEntity.class,
			
//			WrappedPacketInWindowClick.class,
			WrappedPacketOutWindowData.class,
			WrappedPacketOutWindowItems.class,
			
			WrappedPacketOutWorldEvent.class
		));
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends IPacketWrapper> T wrap(Object handle) {
		return (T) registry.wrap(handle);
	}
}
