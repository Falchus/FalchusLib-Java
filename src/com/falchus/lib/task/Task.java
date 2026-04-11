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

public class Task implements Runnable {
	
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
	private static final ExecutorService worker = Executors.newCachedThreadPool();
	
	private static final Map<Integer, Task> tasks = new ConcurrentHashMap<>();
	private static final Map<Integer, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<>();
	private static final AtomicInteger ids = new AtomicInteger(0);
	
	@Getter private final int id = ids.incrementAndGet();
	@Getter private volatile boolean ended;
	@Getter private volatile int tick;
	
	@Override
	public final void run() {
		if (ended) return;
		onRun(tick++);
	}
	
	public static final Task run(Runnable runnable) {
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
		return task;
	}
	
	public static final Task runAsync(Runnable runnable) {
		Task task = run(runnable);
		worker.submit(task);
		return task;
	}
	
	public static final Task runTimer(Runnable runnable, long period, TimeUnit unit) {
		return runTimer(runnable, 0, period, unit);
	}
	
	public static final Task runTimerAsync(Runnable runnable, long period, TimeUnit unit) {
		return runTimerAsync(runnable, 0, period, unit);
	}
	
	public final <T extends Task> T runTimer(long period, TimeUnit unit) {
		return runTimer(0, period, unit);
	}
	
	public final <T extends Task> T runTimerAsync(long period, TimeUnit unit) {
		return runTimerAsync(0, period, unit);
	}
	
	public static final Task runTimer(Runnable runnable, long delay, long period, TimeUnit unit) {
		return run(runnable).runTimer(delay, period, unit);
	}
	
	public static final Task runTimerAsync(Runnable runnable, long delay, long period, TimeUnit unit) {
		return run(runnable).runTimerAsync(delay, period, unit);
	}
	
	@SuppressWarnings("unchecked")
	public final <T extends Task> T runTimer(long delay, long period, TimeUnit unit) {
		ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(this, delay, period, unit);
		tasks.put(id, this);
		taskFutures.put(id, future);
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	public final <T extends Task> T runTimerAsync(long delay, long period, TimeUnit unit) {
		ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(() -> worker.submit(this), delay, period, unit);
		tasks.put(id, this);
		taskFutures.put(id, future);
		return (T) this;
	}
	
	public static final Task runLater(Runnable runnable, long delay, TimeUnit unit) {
		return run(runnable).runLater(delay, unit);
	}
	
	public static final Task runLaterAsync(Runnable runnable, long delay, TimeUnit unit) {
		return run(runnable).runLaterAsync(delay, unit);
	}
	
	@SuppressWarnings("unchecked")
	public final <T extends Task> T runLater(long delay, TimeUnit unit) {
		ScheduledFuture<?> future = scheduler.schedule(() -> {
			run();
			end();
		}, delay, unit);
		tasks.put(id, this);
		taskFutures.put(id, future);
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	public final <T extends Task> T runLaterAsync(long delay, TimeUnit unit) {
		ScheduledFuture<?> future = scheduler.schedule(() -> worker.submit(() -> {
			run();
			end();
		}), delay, unit);
		tasks.put(id, this);
		taskFutures.put(id, future);
		return (T) this;
	}
	
	public static final void end(int id) {
		Task task = tasks.remove(id);
		if (task == null) return;
		
		ScheduledFuture<?> future = taskFutures.remove(id);
		if (future != null) {
			future.cancel(false);
		}
		task.end();
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
