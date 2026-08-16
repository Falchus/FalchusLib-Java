package com.falchus.lib.task.impl;

import java.util.function.Function;

import com.falchus.lib.task.Task;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class TaskImpl implements Runnable {
	
	@Setter private static Function<Runnable, Task> defaultTask = Task::of;
	private Function<Runnable, Task> task;
	
	protected final Task handle;
	
	protected TaskImpl() {
		this(null);
	}
	
	protected TaskImpl(Function<Runnable, Task> task) {
		this.task = task != null ? task : defaultTask;
		handle = this.task.apply(this);
	}
	
	protected void onEnd() {}
	
	public final void end() {
		if (handle.isEnded()) return;
		handle.end();
		onEnd();
	}
}
