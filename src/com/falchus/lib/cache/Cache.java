package com.falchus.lib.cache;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.BiConsumer;
import java.util.function.Function;

import javax.cache.CacheException;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.CompleteConfiguration;
import javax.cache.configuration.Configuration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryExpiredListener;
import javax.cache.event.CacheEntryListener;
import javax.cache.event.CacheEntryRemovedListener;
import javax.cache.event.CacheEntryUpdatedListener;
import javax.cache.event.EventType;
import javax.cache.integration.CompletionListener;
import javax.cache.processor.EntryProcessor;
import javax.cache.processor.EntryProcessorException;
import javax.cache.processor.EntryProcessorResult;
import javax.cache.processor.MutableEntry;

import com.falchus.lib.manager.CacheManager;
import com.falchus.lib.task.Promise;
import com.falchus.lib.task.Task;

import lombok.AllArgsConstructor;
import lombok.Lombok;
import lombok.NonNull;

public class Cache<K, V> implements javax.cache.Cache<K, V> {

	private final CacheManager manager;
	private final String name;
	private final Configuration<K, V> configuration;
	private final boolean managed;
	private final Map<K, Node> map = new ConcurrentHashMap<>();
	private final Map<K, Promise<V>> loads = new ConcurrentHashMap<>();
	private final Set<K> refreshing = ConcurrentHashMap.newKeySet();
	private final List<ListenerReg<K, V>> listeners = new CopyOnWriteArrayList<>();

	private int maxSize;
	private long expireAfterAccess;
	private long expireAfterWrite;
	private long refreshAfterWrite;
	private boolean weak;
	private BiConsumer<? super K, ? super V> onEvict;
	private Function<? super K, ? extends V> loader;
	private volatile boolean closed;

	public Cache(CacheManager manager, String name, Configuration<K, V> configuration, boolean managed) {
		this.manager = manager;
		this.name = name;
		this.configuration = configuration;
		this.managed = managed;
		if (configuration instanceof CompleteConfiguration<K, V> complete) {
            complete.getCacheEntryListenerConfigurations().forEach(this::registerCacheEntryListener);
		}
	}

	public static <K, V> Cache<K, V> of() {
		return CachingProvider.defaultManager().createAnonymous();
	}

	@SuppressWarnings("unchecked")
	public <K1 extends K, V1 extends V> Cache<K1, V1> maxSize(int maxSize) {
		this.maxSize = maxSize;
		return (Cache<K1, V1>) this;
	}

	@SuppressWarnings("unchecked")
	public <K1 extends K, V1 extends V> Cache<K1, V1> expireAfterAccess(long duration, @NonNull TimeUnit unit) {
		expireAfterAccess = unit.toNanos(duration);
		map.forEach(this::scheduleExpire);
		return (Cache<K1, V1>) this;
	}

	@SuppressWarnings("unchecked")
	public <K1 extends K, V1 extends V> Cache<K1, V1> expireAfterWrite(long duration, @NonNull TimeUnit unit) {
		expireAfterWrite = unit.toNanos(duration);
		map.forEach(this::scheduleExpire);
		return (Cache<K1, V1>) this;
	}

	@SuppressWarnings("unchecked")
	public <K1 extends K, V1 extends V> Cache<K1, V1> refreshAfterWrite(long duration, @NonNull TimeUnit unit) {
		refreshAfterWrite = unit.toNanos(duration);
		return (Cache<K1, V1>) this;
	}

	@SuppressWarnings("unchecked")
	public <K1 extends K, V1 extends V> Cache<K1, V1> weakValues() {
		weak = true;
		return (Cache<K1, V1>) this;
	}

	@SuppressWarnings("unchecked")
	public <K1 extends K, V1 extends V> Cache<K1, V1> onEvict(@NonNull BiConsumer<? super K1, ? super V1> onEvict) {
		this.onEvict = (BiConsumer<? super K, ? super V>) onEvict;
		return (Cache<K1, V1>) this;
	}

	@SuppressWarnings("unchecked")
	public <K1 extends K, V1 extends V> Cache<K1, V1> loader(@NonNull Function<? super K1, ? extends V1> loader) {
		this.loader = (Function<? super K, ? extends V>) loader;
		return (Cache<K1, V1>) this;
	}
	
	@SuppressWarnings("unchecked")
	public V peek(@NonNull K key) {
		ensureOpen();
		Node node = map.get(key);
		if (node == null) return null;
		
		V value = (V) node.get();
		if (value == null || node.expired(expireAfterAccess, expireAfterWrite)) {
			evict(key, node, true);
			return null;
		}
		return value;
	}

	public V getIfPresent(@NonNull K key) {
		V value = peek(key);
		if (value == null) return null;
		
		Node node = map.get(key);
		if (node == null) return null;
		
		node.access = System.nanoTime();
		if (expireAfterAccess > 0) {
			scheduleExpire(key, node);
		}
		return value;
	}

	public Promise<V> getAsync(@NonNull K key, @NonNull Function<? super K, ? extends V> loader) {
		ensureOpen();
		V hit = getIfPresent(key);
		if (hit != null) {
			refresh(key, loader);
			return Promise.of(hit);
		}
		return loads.computeIfAbsent(key, k -> {
			Promise<V> promise = Promise.supplyAsync(() -> {
				V loaded = loader.apply(k);
				if (loaded != null) {
					put(k, loaded);
				}
				return loaded;
			});
			promise.whenComplete((v, t) -> loads.remove(k, promise));
			return promise;
		});
	}

	public Promise<V> getAsync(@NonNull K key) {
		if (loader == null) {
			return Promise.of(getIfPresent(key));
		}
		return getAsync(key, loader);
	}

	public V get(@NonNull K key, @NonNull Function<? super K, ? extends V> loader) {
		ensureOpen();
		V hit = getIfPresent(key);
		if (hit != null) {
			refresh(key, loader);
			return hit;
		}
		return loadSync(key, loader);
	}

	@Override
	public V get(@NonNull K key) {
		ensureOpen();
		V hit = getIfPresent(key);
		if (hit != null) {
			if (loader != null) {
				refresh(key, loader);
			}
			return hit;
		}
		if (loader == null) return null;

		return loadSync(key, loader);
	}

	private V loadSync(@NonNull K key, @NonNull Function<? super K, ? extends V> loader) {
		Promise<V> promise = loads.computeIfAbsent(key, k -> {
			Promise<V> created = new Promise<>();
			try {
				V loaded = loader.apply(k);
				if (loaded != null) {
					put(k, loaded);
				}
				created.complete(loaded);
			} catch (Throwable t) {
				created.completeExceptionally(t);
			}
			return created;
		});
		try {
			return promise.join();
		} catch (Throwable t) {
			throw Lombok.sneakyThrow(t.getCause() != null ? t.getCause() : t);
		} finally {
			loads.remove(key, promise);
		}
	}

	@Override
	public Map<K, V> getAll(@NonNull Set<? extends K> keys) {
		Map<K, V> result = new HashMap<>();
		for (K key : keys) {
			V value = get(key);
			if (value != null) {
				result.put(key, value);
			}
		}
		return result;
	}

	@Override
	public boolean containsKey(@NonNull K key) {
		return peek(key) != null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void put(@NonNull K key, @NonNull V value) {
		ensureOpen();
		Node node = new Node(value, weak);
		Node previous = map.put(key, node);
		if (previous != null) previous.cancelExpire();
		scheduleExpire(key, node);
		trim();
		if (previous != null) {
			fireUpdated(key, value, (V) previous.get());
		} else {
			fireCreated(key, value);
		}
	}

	@Override
	public V getAndPut(@NonNull K key, @NonNull V value) {
		V previous = getIfPresent(key);
		put(key, value);
		return previous;
	}

	@Override
	public void putAll(@NonNull Map<? extends K, ? extends V> map) {
		map.forEach(this::put);
	}

	@Override
	public boolean putIfAbsent(@NonNull K key, @NonNull V value) {
		if (containsKey(key)) return false;

		put(key, value);
		return true;
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean remove(@NonNull K key) {
		ensureOpen();
		loads.remove(key);
		refreshing.remove(key);
		Node node = map.remove(key);
		if (node == null) return false;
		node.cancelExpire();

		V value = (V) node.get();
		if (value != null) {
			if (onEvict != null) {
				onEvict.accept(key, value);
			}
			fireRemoved(key, value);
		}
		return true;
	}

	@Override
	public boolean remove(@NonNull K key, @NonNull V oldValue) {
		V current = peek(key);
		if (current == null || !current.equals(oldValue)) return false;

		return remove(key);
	}

	@Override
	public V getAndRemove(@NonNull K key) {
		V previous = getIfPresent(key);
		if (previous != null) {
			remove(key);
		}
		return previous;
	}

	@Override
	public boolean replace(@NonNull K key, @NonNull V oldValue, @NonNull V newValue) {
		V current = peek(key);
		if (current == null || !current.equals(oldValue)) return false;

		put(key, newValue);
		return true;
	}

	@Override
	public boolean replace(@NonNull K key, @NonNull V value) {
		if (!containsKey(key)) return false;

		put(key, value);
		return true;
	}

	@Override
	public V getAndReplace(@NonNull K key, @NonNull V value) {
		V previous = getIfPresent(key);
		if (previous == null) return null;

		put(key, value);
		return previous;
	}

	@Override
	public void removeAll(@NonNull Set<? extends K> keys) {
		keys.forEach(this::remove);
	}

	@Override
	public void removeAll() {
		for (K key : new ArrayList<>(map.keySet())) {
			remove(key);
		}
	}

	@Override
	public void clear() {
		ensureOpen();
		map.values().forEach(Node::cancelExpire);
		map.clear();
		loads.clear();
		refreshing.clear();
	}

	@Override
	public <C extends Configuration<K, V>> C getConfiguration(@NonNull Class<C> clazz) {
		if (clazz.isInstance(configuration)) return clazz.cast(configuration);
		if (clazz.isAssignableFrom(MutableConfiguration.class)) {
			MutableConfiguration<K, V> copy = new MutableConfiguration<>();
			copy.setStoreByValue(configuration.isStoreByValue());
			if (configuration instanceof CompleteConfiguration<K, V> complete) {
                copy.setTypes(complete.getKeyType(), complete.getValueType());
			}
			return clazz.cast(copy);
		}
		throw new IllegalArgumentException(clazz.getName());
	}

	@Override
	public <T> T invoke(@NonNull K key, @NonNull EntryProcessor<K, V, T> entryProcessor, Object... args) throws EntryProcessorException {
		ensureOpen();
		try {
			return entryProcessor.process(new MutableEntryImpl(key), args);
		} catch (EntryProcessorException e) {
			throw e;
		} catch (Exception e) {
			throw new EntryProcessorException(e);
		}
	}

	@Override
	public <T> Map<K, EntryProcessorResult<T>> invokeAll(@NonNull Set<? extends K> keys, @NonNull EntryProcessor<K, V, T> entryProcessor, Object... args) {
		Map<K, EntryProcessorResult<T>> results = new HashMap<>();
		for (K key : keys) {
			try {
				T value = invoke(key, entryProcessor, args);
				results.put(key, () -> value);
			} catch (Exception e) {
				results.put(key, () -> {
					throw new EntryProcessorException(e);
				});
			}
		}
		return results;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public CacheManager getCacheManager() {
		return manager;
	}

	@Override
	public void close() {
		if (closed) return;
		closed = true;
		map.values().forEach(Node::cancelExpire);
		map.clear();
		loads.clear();
		refreshing.clear();
		listeners.clear();
		if (managed) {
			manager.released(name);
		}
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

	@Override
	public void registerCacheEntryListener(@NonNull CacheEntryListenerConfiguration<K, V> configuration) {
		ensureOpen();
		CacheEntryListener<? super K, ? super V> listener = configuration.getCacheEntryListenerFactory().create();
		listeners.add(new ListenerReg<>(configuration, listener));
	}

	@Override
	public void deregisterCacheEntryListener(@NonNull CacheEntryListenerConfiguration<K, V> configuration) {
		listeners.removeIf(reg -> reg.configuration.equals(configuration));
	}

	@Override
	public Iterator<Entry<K, V>> iterator() {
		ensureOpen();
		return asMap().entrySet().stream()
				.<Entry<K, V>>map(e -> new EntryImpl<>(e.getKey(), e.getValue()))
				.iterator();
	}

	@Override
	public void loadAll(@NonNull Set<? extends K> keys, boolean replaceExistingValues, CompletionListener completionListener) {
		try {
			for (K key : keys) {
				if (!replaceExistingValues && containsKey(key)) continue;
				if (loader != null) {
					get(key, loader);
				}
			}
			if (completionListener != null) {
				completionListener.onCompletion();
			}
		} catch (Exception e) {
			if (completionListener != null) {
				completionListener.onException(e);
			} else {
				throw new CacheException(e);
			}
		}
	}

	public int size() {
		trim();
		return map.size();
	}

	@SuppressWarnings("unchecked")
	public Map<K, V> asMap() {
		trim();
		Map<K, V> map = new HashMap<>();
		this.map.forEach((key, node) -> {
			V value = (V) node.get();
			if (value != null) {
				map.put(key, value);
			}
		});
		return map;
	}

	private void refresh(@NonNull K key, @NonNull Function<? super K, ? extends V> loader) {
		if (refreshAfterWrite <= 0) return;

		Node node = map.get(key);
		if (node == null || System.nanoTime() - node.write < refreshAfterWrite) return;
		if (!refreshing.add(key)) return;

		Promise.supplyAsync(() -> {
			try {
				V loaded = loader.apply(key);
				if (loaded != null) {
					put(key, loaded);
				}
				return loaded;
			} finally {
				refreshing.remove(key);
			}
		});
	}
	
	private void scheduleExpire(K key, Node node) {
		node.cancelExpire();
		if (map.get(key) != node) return;
		long now = System.nanoTime();
		long accessLeft = expireAfterAccess > 0 ? expireAfterAccess - (now - node.access) : Long.MAX_VALUE;
		long writeLeft = expireAfterWrite > 0 ? expireAfterWrite - (now - node.write) : Long.MAX_VALUE;
		long delay = Math.min(writeLeft, accessLeft);
		if (delay == Long.MAX_VALUE) return;
		if (delay <= 0) {
			evict(key, node, true);
			return;
		}
		Task task = Task.of(() -> {
			if (map.get(key) == node) {
				evict(key, node, true);
			}
		});
		node.expireTask = task;
		task.runLater(delay, TimeUnit.NANOSECONDS);
		if (map.get(key) != node) {
			node.cancelExpire();
		}
	}

	private void trim() {
		map.forEach((key, node) -> {
			if (node.get() == null || node.expired(expireAfterAccess, expireAfterWrite)) {
				evict(key, node, true);
			}
		});
		while (maxSize > 0 && map.size() > maxSize) {
			K victim = null;
			Node node = null;
			long oldest = Long.MAX_VALUE;
			for (Map.Entry<K, Node> entry : map.entrySet()) {
				if (entry.getValue().access < oldest) {
					oldest = entry.getValue().access;
					victim = entry.getKey();
					node = entry.getValue();
				}
			}
			if (victim == null || !evict(victim, node, false)) break;
		}
	}

	@SuppressWarnings("unchecked")
	private boolean evict(@NonNull K key, @NonNull Node node, boolean expired) {
		node.cancelExpire();
		if (!map.remove(key, node)) return false;
		V value = (V) node.get();
		if (value != null) {
			if (onEvict != null) {
				onEvict.accept(key, value);
			}
			if (expired) {
				fireExpired(key, value);
			} else {
				fireRemoved(key, value);
			}
		}
		return true;
	}

	private void ensureOpen() {
		if (closed) {
			throw new IllegalStateException("Cache closed");
		}
	}

	@SuppressWarnings("unchecked")
	private void fireCreated(K key, V value) {
		Event event = new Event(key, value, null, EventType.CREATED);
		for (ListenerReg<K, V> reg : listeners) {
			if (!accept(reg, event)) continue;
			if (reg.listener instanceof CacheEntryCreatedListener<?, ?> created) {
				((CacheEntryCreatedListener<K, V>) created).onCreated(List.of(event));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void fireUpdated(K key, V value, V oldValue) {
		Event event = new Event(key, value, oldValue, EventType.UPDATED);
		for (ListenerReg<K, V> reg : listeners) {
			if (!accept(reg, event)) continue;
			if (reg.listener instanceof CacheEntryUpdatedListener<?, ?> updated) {
				((CacheEntryUpdatedListener<K, V>) updated).onUpdated(List.of(event));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void fireRemoved(K key, V value) {
		Event event = new Event(key, value, value, EventType.REMOVED);
		for (ListenerReg<K, V> reg : listeners) {
			if (!accept(reg, event)) continue;
			if (reg.listener instanceof CacheEntryRemovedListener<?, ?> removed) {
				((CacheEntryRemovedListener<K, V>) removed).onRemoved(List.of(event));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void fireExpired(K key, V value) {
		Event event = new Event(key, value, value, EventType.EXPIRED);
		for (ListenerReg<K, V> reg : listeners) {
			if (!accept(reg, event)) continue;
			if (reg.listener instanceof CacheEntryExpiredListener<?, ?> expired) {
				((CacheEntryExpiredListener<K, V>) expired).onExpired(List.of(event));
			}
		}
	}

	private boolean accept(ListenerReg<K, V> reg, Event event) {
		if (reg.configuration.getCacheEntryEventFilterFactory() == null) return true;
		return reg.configuration.getCacheEntryEventFilterFactory().create().evaluate(event);
	}

	@AllArgsConstructor
	private final class MutableEntryImpl implements MutableEntry<K, V> {

		private final K key;

		@Override
		public boolean exists() {
			return containsKey(key);
		}

		@Override
		public void remove() {
			Cache.this.remove(key);
		}

		@Override
		public void setValue(V value) {
			put(key, value);
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return get(key);
		}

		@Override
		public <T> T unwrap(@NonNull Class<T> clazz) {
			if (clazz.isInstance(this)) return clazz.cast(this);
			throw new IllegalArgumentException(clazz.getName());
		}
	}

	private final class Event extends CacheEntryEvent<K, V> {

		private static final long serialVersionUID = 8794753666904712329L;
		
		private final K key;
		private final V value;
		private final V oldValue;

		private Event(K key, V value, V oldValue, EventType type) {
			super(Cache.this, type);
			this.key = key;
			this.value = value;
			this.oldValue = oldValue;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V getOldValue() {
			return oldValue;
		}

		@Override
		public boolean isOldValueAvailable() {
			return oldValue != null;
		}

		@Override
		public <T> T unwrap(@NonNull Class<T> clazz) {
			if (clazz.isInstance(this)) return clazz.cast(this);
			throw new IllegalArgumentException(clazz.getName());
		}
	}

	private record ListenerReg<K, V>(CacheEntryListenerConfiguration<K, V> configuration, CacheEntryListener<? super K, ? super V> listener) {}

	@AllArgsConstructor
	private static final class EntryImpl<K, V> implements Entry<K, V> {

		private final K key;
		private final V value;

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public <T> T unwrap(@NonNull Class<T> clazz) {
			if (clazz.isInstance(this)) return clazz.cast(this);
			throw new IllegalArgumentException(clazz.getName());
		}
	}

	private static class Node {

		private final Object value;
		private final long write;
		private volatile long access;
		private volatile Task expireTask;

		private Node(Object value, boolean weak) {
			long now = System.nanoTime();
			this.value = weak ? new WeakReference<>(value) : value;
			write = now;
			access = now;
		}

		private Object get() {
			return value instanceof WeakReference<?> ref ? ref.get() : value;
		}
		
		private void cancelExpire() {
			if (expireTask == null) return;
			expireTask.end();
			expireTask = null;
		}

		private boolean expired(long accessExpire, long writeExpire) {
			long now = System.nanoTime();
			return (accessExpire > 0 && now - access >= accessExpire)
					|| (writeExpire > 0 && now - write >= writeExpire);
		}
	}
}
