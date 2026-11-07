package com.falchus.lib.minecraft.utils;

import java.util.Optional;
import java.util.UUID;

import com.falchus.lib.task.Promise;

import lombok.NonNull;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;

public class LuckPerms {
	
	public static Optional<net.luckperms.api.LuckPerms> getApi() {
		try {
			return Optional.of(LuckPermsProvider.get());
		} catch (LinkageError e) {
			return Optional.empty();
		}
	}
	
	public static Promise<User> getUser(@NonNull UUID uuid) {
		try {
			return getApi()
					.map(api -> {
						User user = api.getUserManager().getUser(uuid);
						if (user != null) return Promise.of(user);
						
						return Promise.from(api.getUserManager().loadUser(uuid));
					})
					.orElseGet(() -> Promise.of(null));
		} catch (LinkageError e) {
			return Promise.of(null);
		}
	}
	
	public static Promise<CachedMetaData> getMetaData(@NonNull UUID uuid) {
		try {
			return getUser(uuid).thenApply(user -> user == null
				? null
				: user.getCachedData().getMetaData()
			);
		} catch (LinkageError e) {
			return Promise.of(null);
		}
	}

	public static Promise<String> getPrefix(@NonNull UUID uuid) {
		try {
			return getMetaData(uuid).thenApply(meta -> meta == null || meta.getPrefix() == null
				? ""
				: meta.getPrefix()
			);
		} catch (LinkageError e) {
			return Promise.of("");
		}
	}
	
	public static Promise<Integer> getWeight(@NonNull UUID uuid) {
		try {
			return getMetaData(uuid).thenApply(meta -> meta == null
				? null
				: meta.getWeight()
			);
		} catch (LinkageError e) {
			return Promise.of(null);
		}
	}
}
