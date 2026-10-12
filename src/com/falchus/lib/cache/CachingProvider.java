package com.falchus.lib.cache;

import java.net.URI;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.WeakHashMap;

import javax.cache.CacheException;
import javax.cache.configuration.OptionalFeature;

import com.falchus.lib.FalchusLib;
import com.falchus.lib.manager.CacheManager;
import lombok.NonNull;

public final class CachingProvider implements javax.cache.spi.CachingProvider {

	private static final URI uri = URI.create(FalchusLib.nameFull + "://cache");
	private static final CachingProvider instance = new CachingProvider();

	private final Map<ClassLoader, Map<URI, CacheManager>> managers = new WeakHashMap<>();
	private boolean closed;

	static CachingProvider instance() {
		return instance;
	}

	static CacheManager defaultManager() {
		return instance.getCacheManager();
	}

	@Override
	public synchronized CacheManager getCacheManager(URI uri, ClassLoader classLoader, Properties properties) {
		ensureOpen();
		URI u = uri != null ? uri : getDefaultURI();
		ClassLoader cl = classLoader != null ? classLoader : getDefaultClassLoader();
		Properties props = properties != null ? properties : getDefaultProperties();
		return managers
				.computeIfAbsent(cl, c -> new ConcurrentHashMap<>())
				.computeIfAbsent(u, key -> new CacheManager(this, key, cl, props));
	}

	@Override
	public CacheManager getCacheManager(URI uri, ClassLoader classLoader) {
		return getCacheManager(uri, classLoader, getDefaultProperties());
	}

	@Override
	public CacheManager getCacheManager() {
		return getCacheManager(getDefaultURI(), getDefaultClassLoader());
	}

	@Override
	public ClassLoader getDefaultClassLoader() {
		return CachingProvider.class.getClassLoader();
	}

	@Override
	public URI getDefaultURI() {
		return uri;
	}

	@Override
	public Properties getDefaultProperties() {
		return new Properties();
	}

	@Override
	public synchronized void close() {
		if (closed) return;
		closed = true;
		managers.values().forEach(map -> map.values().forEach(CacheManager::close));
		managers.clear();
	}

	@Override
	public synchronized void close(@NonNull ClassLoader classLoader) {
		Map<URI, CacheManager> map = managers.remove(classLoader);
		if (map != null) {
			map.values().forEach(CacheManager::close);
		}
	}

	@Override
	public synchronized void close(@NonNull URI uri, @NonNull ClassLoader classLoader) {
		Map<URI, CacheManager> map = managers.get(classLoader);
		if (map == null) return;

		CacheManager manager = map.remove(uri);
		if (manager != null) manager.close();
		if (map.isEmpty()) managers.remove(classLoader);
	}

	@Override
	public boolean isSupported(@NonNull OptionalFeature optionalFeature) {
		return optionalFeature == OptionalFeature.STORE_BY_REFERENCE;
	}

	public synchronized void release(@NonNull CacheManager manager) {
		Map<URI, CacheManager> map = managers.get(manager.getClassLoader());
		if (map != null) {
			map.remove(manager.getURI(), manager);
		}
	}

	private void ensureOpen() {
		if (closed) {
			throw new CacheException("CachingProvider closed");
		}
	}
}
