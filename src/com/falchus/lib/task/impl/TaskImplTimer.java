package com.falchus.lib.task.impl;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import com.falchus.lib.task.Task;

import lombok.NonNull;

public abstract class TaskImplTimer extends TaskImpl {
	
	protected TaskImplTimer(Function<Runnable, Task> task) {
		super(task);
	}

	public final <T extends TaskImplTimer> T runTimer(long period, @NonNull TimeUnit unit) {
		return runTimer(0, period, unit);
	}
	
	@SuppressWarnings("unchecked")
	public final <T extends TaskImplTimer> T runTimer(long delay, long period, @NonNull TimeUnit unit) {
		handle.runTimer(delay, period, unit);
		return (T) this;
	}
	
	public final <T extends TaskImplTimer> T runTimerAsync(long period, @NonNull TimeUnit unit) {
		return runTimerAsync(0, period, unit);
	}
	
	@SuppressWarnings("unchecked")
	public final <T extends TaskImplTimer> T runTimerAsync(long delay, long period, @NonNull TimeUnit unit) {
		handle.runTimerAsync(delay, period, unit);
		return (T) this;
	}
}
