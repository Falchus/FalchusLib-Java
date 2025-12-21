package com.falchus.lib.minecraft.utils;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import eu.cloudnetservice.driver.inject.InjectionLayer;
import eu.cloudnetservice.driver.provider.CloudServiceFactory;
import eu.cloudnetservice.driver.provider.CloudServiceProvider;
import eu.cloudnetservice.driver.registry.ServiceRegistry;
import eu.cloudnetservice.driver.service.ServiceConfiguration;
import eu.cloudnetservice.driver.service.ServiceCreateResult;
import eu.cloudnetservice.driver.service.ServiceInfoSnapshot;
import eu.cloudnetservice.modules.bridge.BridgeDocProperties;
import eu.cloudnetservice.modules.bridge.BridgeServiceHelper;
import eu.cloudnetservice.modules.bridge.player.PlayerManager;
import eu.cloudnetservice.modules.bridge.player.executor.ServerSelectorType;
import eu.cloudnetservice.wrapper.holder.ServiceInfoHolder;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

/**
 * Utility class for CloudNet-related operations.
 */
@UtilityClass
public class CloudNet {

	public static final BridgeServiceHelper bridgeServiceHelper = InjectionLayer.ext().instance(BridgeServiceHelper.class);
	public static final PlayerManager playerManager = InjectionLayer.ext().instance(ServiceRegistry.class).defaultInstance(PlayerManager.class);
	public static final CloudServiceFactory cloudServiceFactory = InjectionLayer.ext().instance(CloudServiceFactory.class);
	public static final CloudServiceProvider cloudServiceProvider = InjectionLayer.ext().instance(CloudServiceProvider.class);
	public static final ServiceInfoHolder serviceInfoHolder = InjectionLayer.ext().instance(ServiceInfoHolder.class);

	/**
	 * Broadcasts a message to all players globally.
	 */
	public static void broadcastMessage(@NonNull List<String> message) {
		final String messageFinal = String.join("\n", message);
        Component component = LegacyComponentSerializer.legacySection().deserialize(messageFinal);
        playerManager.globalPlayerExecutor().sendChatMessage(component);
	}
	
	/**
	 * Published an update for the current service info snapshot.
	 */
	public static void publishServiceInfoUpdate() {
        serviceInfoHolder.publishServiceInfoUpdate();
	}
	
	/**
	 * Creates and starts a new CloudNet service.
	 */
	public static void createAndStartService(@NonNull ServiceConfiguration serviceConfig) {
		cloudServiceFactory.createCloudServiceAsync(serviceConfig)
			.thenAccept(result -> {
				if (result.state() == ServiceCreateResult.State.CREATED) {
					UUID uuid = result.serviceInfo().serviceId().uniqueId();
					ServiceInfoSnapshot service = cloudServiceProvider.service(uuid);
					service.provider().startAsync();
				}
			});
	}
	
	/**
	 * Gets the player count for the group.
	 */
	public static int getPlayerCountFromGroup(@NonNull String group) {
		return playerManager.groupOnlinePlayers(group).count();
	}
	
	/**
	 * Gets the player count for the task.
	 */
	public static int getPlayerCountFromTask(@NonNull String task) {
		return playerManager.taskOnlinePlayers(task).count();
	}
	
	/**
	 * Gets the player count for the service.
	 */
	public static int getPlayerCountFromService(@NonNull String service) {
		return getService(service).readProperty(BridgeDocProperties.ONLINE_COUNT);
	}
	
	/**
	 * Get service by name
	 */
	public static ServiceInfoSnapshot getService(@NonNull String service) {
		return cloudServiceProvider.serviceByName(service);
	}
	
	/**
	 * Get services by group
	 */
	public static Collection<ServiceInfoSnapshot> getServicesByGroup(@NonNull String group) {
		return cloudServiceProvider.servicesByGroup(group);
	}
	
	/**
	 * Get services by task
	 */
	public static Collection<ServiceInfoSnapshot> getServicesByTask(@NonNull String task) {
		return cloudServiceProvider.servicesByTask(task);
	}

    /**
     * Sets the "extra" field for the current service.
     */
    public static void setExtra(@NonNull String newExtra) {
        bridgeServiceHelper.extra().set(newExtra);
    }

    /**
     * Sets the MOTD for the current service.
     */
    public static void setMotd(@NonNull String newMotd) {
        bridgeServiceHelper.motd().set(newMotd);
    }
    
    /**
     * Gets the "extra" field of the current service.
     */
    public static String getExtra() {
    	return bridgeServiceHelper.extra().get();
    }
    
    /**
     * Gets the MOTD of the current service.
     */
    public static String getMotd() {
        return bridgeServiceHelper.motd().get();
    }
    
    /**
     * Gets the state of the current service.
     */
    public static String getState() {
    	return bridgeServiceHelper.state().get();
    }
    
    /**
     * Changes the service state to "ingame" and publishes the update.
     */
    public static void changeToIngame(boolean autoStartService) {
        bridgeServiceHelper.changeToIngame(autoStartService);
        publishServiceInfoUpdate();
    }

    /**
     * Changes the service state to "ingame" and publishes the update.
     */
    public static void changeToIngame() {
    	changeToIngame(true);
    }

    /**
     * Connects a player to a specified task using the given selector type.
     */
    public static void connectPlayerToTask(@NonNull UUID uuid, @NonNull String task, @NonNull ServerSelectorType serverSelectorType) {
        playerManager.playerExecutor(uuid).connectToTask(task, serverSelectorType);
    }

    /**
     * Connects a player to a specified service.
     */
    public static void connectPlayerToService(@NonNull UUID uuid, @NonNull String service) {
        playerManager.playerExecutor(uuid).connect(service);
    }
}
