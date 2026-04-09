package com.falchus.lib.task.impl;

import com.falchus.lib.task.Task;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * to be used with {@link Task#runTaskTimer(long)}
 */
@Getter
@AllArgsConstructor
public abstract class CountdownTask extends Task {
	
	private volatile int remaining;
	
	@Override
	public final void onRun(int tick) {		
		if (remaining <= 0) {
			end();
			return;
		}
		onCountdown(remaining);
		remaining--;
	}
	
	protected void onCountdown(int remaining) {}
}
