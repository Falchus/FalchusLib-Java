package com.falchus.lib.task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;

public abstract class Task implements Runnable {
	
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
	private static final ExecutorService worker = Executors.newCachedThreadPool();
	
	private static final Map<Integer, Task> tasks = new ConcurrentHashMap<>();
	private static final Map<Integer, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<>();
	private static final AtomicInteger ids = new AtomicInteger(0);
	
	@Getter private final int id = ids.incrementAndGet();
	private boolean ended;
	private int tick;
	
	@Override
	public final void run() {
		if (ended) return;
		
		worker.submit(() -> onRun(tick++));
	}
	
	public static final Task runTaskTimer(Runnable runnable, long period, TimeUnit unit) {
		return runTaskTimer(runnable, 0, period, unit);
	}
	
	public final void runTaskTimer(long period, TimeUnit unit) {
		runTaskTimer(0, period, unit);
	}
	
	public static final Task runTaskTimer(Runnable runnable, long delay, long period, TimeUnit unit) {
		Task task;
		if (runnable instanceof Task t) {
			task = t;
		} else {
			task = new Task() {
				@Override
				public void onRun(int tick) {
					runnable.run();
				}
			};
		}
		task.runTaskTimer(delay, period, unit);
		return task;
	}
	
	public final void runTaskTimer(long delay, long period, TimeUnit unit) {
		ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(this, delay, period, unit);
		tasks.put(id, this);
		taskFutures.put(id, future);
	}
	
	public static final Task runTaskLater(Runnable runnable, long delay, TimeUnit unit) {
		Task task;
		if (runnable instanceof Task t) {
			task = t;
		} else {
			task = new Task() {
				@Override
				public void onRun(int tick) {
					runnable.run();
				}
			};
		}
		task.runTaskLater(delay, unit);
		return task;
	}
	
	public final void runTaskLater(long delay, TimeUnit unit) {
		ScheduledFuture<?> future = scheduler.schedule(() -> {
			run();
			end();
		}, delay, unit);
		tasks.put(id, this);
		taskFutures.put(id, future);
	}
	
	public static final void end(int id) {
		Task task = tasks.remove(id);
		if (task != null) {
			task.end();
			taskFutures.remove(id);
		}
	}
	
	public final void end() {
		if (ended) return;
		ended = true;
		
		end(id);
		onEnd();
	}
	
	protected void onRun(int tick) {}
	protected void onEnd() {}
}
