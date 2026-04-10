package com.falchus.lib.task;

import lombok.Getter;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Task implements Runnable {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    private static final ExecutorService worker = Executors.newCachedThreadPool();

    private static final Map<Integer, Task> tasks = new ConcurrentHashMap<>();
    private static final Map<Integer, ScheduledFuture<?>> taskFutures = new ConcurrentHashMap<>();
    private static final AtomicInteger ids = new AtomicInteger(0);

    @Getter
    private final int id = ids.incrementAndGet();
    @Getter
    private boolean ended;
    @Getter
    private int tick;

    public static final Task runTask(Runnable runnable) {
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

    public static final Task runTaskTimer(Runnable runnable, long period, TimeUnit unit) {
        return runTaskTimer(runnable, 0, period, unit);
    }

    public static final Task runTaskTimer(Runnable runnable, long delay, long period, TimeUnit unit) {
        return runTask(runnable).runTaskTimer(delay, period, unit);
    }

    public static final Task runTaskLater(Runnable runnable, long delay, TimeUnit unit) {
        return runTask(runnable).runTaskLater(delay, unit);
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

    @Override
    public final void run() {
        if (ended) return;

        worker.submit(() -> onRun(tick++));
    }

    public final <T extends Task> T runTaskTimer(long period, TimeUnit unit) {
        return runTaskTimer(0, period, unit);
    }

    @SuppressWarnings("unchecked")
    public final <T extends Task> T runTaskTimer(long delay, long period, TimeUnit unit) {
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(this, delay, period, unit);
        tasks.put(id, this);
        taskFutures.put(id, future);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public final <T extends Task> T runTaskLater(long delay, TimeUnit unit) {
        ScheduledFuture<?> future = scheduler.schedule(() -> {
            run();
            end();
        }, delay, unit);
        tasks.put(id, this);
        taskFutures.put(id, future);
        return (T) this;
    }

    public final void end() {
        if (ended) return;
        ended = true;

        end(id);
        onEnd();
    }

    protected void onRun(int tick) {
    }

    protected void onEnd() {
    }
}
