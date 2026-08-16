package com.falchus.lib.task.impl;

import java.util.function.Function;

import com.falchus.lib.task.Task;

import lombok.Getter;

@Getter
public class CountdownTask extends TaskImplTimer {
	
	private volatile int remaining;
	
	public CountdownTask(int remaining) {
		this(remaining, null);
	}
	
	public CountdownTask(int remaining, Function<Runnable, Task> task) {
		super(task);
		this.remaining = remaining;
	}
	
	@Override
	public final void run() {		
		if (remaining <= 0) {
			end();
			return;
		}
		onCountdown(remaining);
		remaining--;
	}
	
	protected void onCountdown(int remaining) {}
}
