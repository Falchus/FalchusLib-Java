package com.falchus.lib.events.listener;

import com.falchus.lib.enums.EventPriority;
import com.falchus.lib.events.Event;
import com.falchus.lib.events.EventExecutor;
import com.falchus.lib.events.EventHandler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisteredListener {
	
	final Listener listener;
	final EventExecutor executor;
	final EventPriority priority;
	final boolean ignoreCancelled;
	
	public RegisteredListener(Listener listener, EventExecutor executor, EventHandler handler) {
		this.listener = listener;
		this.executor = executor;
		priority = handler.priority();
		ignoreCancelled = handler.ignoreCancelled();
	}
	
	public void execute(Event event) {
		executor.execute(event);
	}
}
