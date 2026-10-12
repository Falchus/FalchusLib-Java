package com.falchus.lib.manager;

import java.net.URI;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.cache.CacheException;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;

import com.falchus.lib.FalchusLib;
import com.falchus.lib.cache.Cache;
import com.falchus.lib.cache.CachingProvider;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class CacheManager implements javax.cache.CacheManager {

	private static final AtomicLong anonymous = new AtomicLong();

	private final CachingProvider provider;
	private final URI uri;
	private final ClassLoader classLoader;
	private final Properties properties;
	private final Map<String, Cache<?, ?>> caches = new ConcurrentHashMap<>();
	private boolean closed;

	@Override
	public URI getURI() {
		return uri;
	}

	@Override
	public ClassLoader getClassLoader() {
		return classLoader;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	public <K, V> Cache<K, V> createAnonymous() {
		ensureOpen();
		return new Cache<>(this, FalchusLib.nameFull + "-" + anonymous.incrementAndGet(), new MutableConfiguration<K, V>().setStoreByValue(false), false);
	}

	@Override
	public CachingProvider getCachingProvider() {
		return provider;
	}

	@Override
	public <K, V, C extends Configuration<K, V>> Cache<K, V> createCache(@NonNull String cacheName, @NonNull C configuration) {
		ensureOpen();
		Cache<K, V> cache = new Cache<>(this, cacheName, configuration, true);
		if (caches.putIfAbsent(cacheName, cache) != null) {
			throw new CacheException("Cache already exists: " + cacheName);
		}
		return cache;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> Cache<K, V> getCache(@NonNull String cacheName, @NonNull Class<K> keyType, @NonNull Class<V> valueType) {
		Cache<?, ?> cache = getCache(cacheName);
		if (cache == null) return null;

		return (Cache<K, V>) cache;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K, V> Cache<K, V> getCache(@NonNull String cacheName) {
		ensureOpen();
		return (Cache<K, V>) caches.get(cacheName);
	}

	@Override
	public Iterable<String> getCacheNames() {
		ensureOpen();
		return Collections.unmodifiableSet(caches.keySet());
	}

	@Override
	public void destroyCache(@NonNull String cacheName) {
		ensureOpen();
		Cache<?, ?> cache = caches.remove(cacheName);
		if (cache != null) {
			cache.close();
		}
	}

	@Override
	public void enableManagement(String cacheName, boolean enabled) {
		ensureOpen();
	}

	@Override
	public void enableStatistics(String cacheName, boolean enabled) {
		ensureOpen();
	}

	@Override
	public synchronized void close() {
		if (closed) return;
		closed = true;
		caches.values().forEach(Cache::close);
		caches.clear();
		provider.release(this);
	}

	@Override
	public boolean isClosed() {
		return closed;
	}

	@Override
	public <T> T unwrap(@NonNull Class<T> clazz) {
		if (clazz.isInstance(this)) return clazz.cast(this);
		throw new IllegalArgumentException(clazz.getName());
	}

	public void released(@NonNull String name) {
		caches.remove(name);
	}

	private void ensureOpen() {
		if (closed) {
			throw new IllegalStateException("CacheManager closed");
		}
	}
}
