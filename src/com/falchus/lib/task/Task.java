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
import lombok.NonNull;

public class Task implements Runnable {
	
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
	private static final ExecutorService worker = Executors.newVirtualThreadPerTaskExecutor();
	
	private static final Map<Integer, Task> tasks = new ConcurrentHashMap<>();
	private static final Map<Integer, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<>();
	private static final AtomicInteger ids = new AtomicInteger(0);
	
	@Getter private final int id = ids.incrementAndGet();
	@Getter private volatile boolean ended;
	@Getter private volatile int tick;
	
	@SuppressWarnings("unchecked")
	protected <T extends Task> T execute(@NonNull Runnable runnable) {
		runnable.run();
		return (T) this;
	}
	
	private final <T extends Task> T execute() {
		return execute(this);
	}
	
	@SuppressWarnings("unchecked")
	protected <T extends Task> T executeAsync(@NonNull Runnable runnable) {
		worker.submit(runnable);
		return (T) this;
	}
	
	private final <T extends Task> T executeAsync() {
		return executeAsync(this);
	}
	
	@Override
	public final void run() {
		if (ended) return;
		onRun(tick++);
	}
	
	@SuppressWarnings("unchecked")
	public static final <T extends Task> T run(@NonNull Runnable runnable) {
		Task task;
		if (runnable instanceof Task t) {
			task = t;
		} else {
			task = new Task() {
				@Override
				protected void onRun(int tick) {
					execute(runnable);
				}
			};
		}
		return (T) task;
	}
	
	public static final <T extends Task> T runAsync(@NonNull Runnable runnable) {
		return run(runnable).executeAsync();
	}
	
	public static final <T extends Task> T runTimer(@NonNull Runnable runnable, long period, @NonNull TimeUnit unit) {
		return runTimer(runnable, 0, period, unit);
	}
	
	public static final <T extends Task> T runTimerAsync(@NonNull Runnable runnable, long period, @NonNull TimeUnit unit) {
		return runTimerAsync(runnable, 0, period, unit);
	}
	
	public final <T extends Task> T runTimer(long period, @NonNull TimeUnit unit) {
		return runTimer(0, period, unit);
	}
	
	public final <T extends Task> T runTimerAsync(long period, @NonNull TimeUnit unit) {
		return runTimerAsync(0, period, unit);
	}
	
	public static final <T extends Task> T runTimer(@NonNull Runnable runnable, long delay, long period, @NonNull TimeUnit unit) {
		return run(runnable).runTimer(delay, period, unit);
	}
	
	public static final <T extends Task> T runTimerAsync(@NonNull Runnable runnable, long delay, long period, @NonNull TimeUnit unit) {
		return run(runnable).runTimerAsync(delay, period, unit);
	}
	
	@SuppressWarnings("unchecked")
	public final <T extends Task> T runTimer(long delay, long period, @NonNull TimeUnit unit) {
		ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(this::execute, delay, period, unit);
		tasks.put(id, this);
		taskFutures.put(id, future);
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	public final <T extends Task> T runTimerAsync(long delay, long period, @NonNull TimeUnit unit) {
		ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(this::executeAsync, delay, period, unit);
		tasks.put(id, this);
		taskFutures.put(id, future);
		return (T) this;
	}
	
	public static final <T extends Task> T runLater(@NonNull Runnable runnable, long delay, @NonNull TimeUnit unit) {
		return run(runnable).runLater(delay, unit);
	}
	
	public static final <T extends Task> T runLaterAsync(@NonNull Runnable runnable, long delay, @NonNull TimeUnit unit) {
		return run(runnable).runLaterAsync(delay, unit);
	}
	
	@SuppressWarnings("unchecked")
	public final <T extends Task> T runLater(long delay, @NonNull TimeUnit unit) {
		ScheduledFuture<?> future = scheduler.schedule(() -> execute(() -> {
			run();
			end();
		}), delay, unit);
		tasks.put(id, this);
		taskFutures.put(id, future);
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	public final <T extends Task> T runLaterAsync(long delay, @NonNull TimeUnit unit) {
		ScheduledFuture<?> future = scheduler.schedule(() -> executeAsync(() -> {
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
	
	public void end() {
		if (ended) return;
		ended = true;
		
		end(id);
		onEnd();
	}
	
	protected void onRun(int tick) {}
	protected void onEnd() {}
}
