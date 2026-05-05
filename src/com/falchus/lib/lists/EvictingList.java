package com.falchus.lib.lists;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EvictingList<T> extends LinkedList<T> {

	private final int maxSize;
	
	public EvictingList(Collection<? extends T> c, int maxSize) {
		super(c);
		this.maxSize = maxSize;
	}
	
	@Override
	public boolean add(T t) {
		if (size() >= maxSize) {
			removeFirst();
		}
		return super.add(t);
	}
	
	@Override
	public boolean addAll(Collection<? extends T> c) {
		return c.stream().anyMatch(this::add);
	}
	
	@Override
	public Stream<T> stream() {
		return new CopyOnWriteArrayList<>(this).stream();
	}
}
