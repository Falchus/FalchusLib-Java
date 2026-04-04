package com.falchus.lib.events;

import com.falchus.lib.manager.EventManager;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Event {
	
	final boolean async;
	
	public Event() {
		this(false);
	}
	
	public boolean callEvent() {
		EventManager.callEvent(this);
		if (this instanceof Cancellable cancellable) {
			return !cancellable.isCancelled();
		}
		return true;
	}
}
