package com.falchus.lib.minecraft.utils;

import java.util.Optional;
import java.util.UUID;

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
	
	public static User getUser(@NonNull UUID uuid) {
		try {
			return getApi()
					.map(api -> api.getUserManager().getUser(uuid))
					.orElse(null);
		} catch (LinkageError e) {
			return null;
		}
	}
	
	public static Optional<CachedMetaData> getMetaData(@NonNull UUID uuid) {
		try {
			return Optional.ofNullable(getUser(uuid)).map(user -> user.getCachedData().getMetaData());
		} catch (LinkageError e) {
			return Optional.empty();
		}
	}

	public static String getPrefix(@NonNull UUID uuid) {
		try {
			return getMetaData(uuid)
					.map(CachedMetaData::getPrefix)
					.orElse("");
		} catch (LinkageError e) {
			return "";
		}
	}
	
	public static Integer getWeight(@NonNull UUID uuid) {
		try {
			return getMetaData(uuid)
					.map(CachedMetaData::getWeight)
					.orElse(null);
		} catch (LinkageError e) {
			return null;
		}
	}
}
