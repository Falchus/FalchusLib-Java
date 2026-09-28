package com.falchus.lib.task;

import com.falchus.lib.enums.TaskPriority;
import lombok.NonNull;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class TaskPending<T> extends Promise<T> implements Runnable {

    private final Supplier<T> supplier;
    private final PriorityQueues queues;
    private final Runnable wakeup;
    private final AtomicBoolean started = new AtomicBoolean();
    private final AtomicInteger queued = new AtomicInteger(-1);
    private final AtomicInteger priority;

    public TaskPending(@NonNull Supplier<T> supplier, @NonNull PriorityQueues queues, @NonNull Runnable wakeup, @NonNull TaskPriority priority) {
        this.supplier = supplier;
        this.queues = queues;
        this.wakeup = wakeup;
        this.priority = new AtomicInteger(priority.ordinal());
    }

    public TaskPending(@NonNull Supplier<T> supplier, @NonNull PriorityQueues queues, @NonNull Runnable wakeup) {
        this(supplier, queues, wakeup, TaskPriority.NORMAL);
    }

    @Override
    public void run() {
        if (!cancel()) return;
        try {
            complete(supplier.get());
        } catch (Throwable t) {
            completeExceptionally(t);
        }
    }

    public void bumpPriority(@NonNull TaskPriority priority) {
        for (;;) {
            int current = this.priority.get();
            int ordinal = priority.ordinal();
            if (current >= ordinal || this.priority.compareAndSet(current, ordinal)) {
                break;
            }
        }

        if (queued.get() == -1 || started.get()) return;
        submit();
    }

    public void bumpPriority() {
        bumpPriority(TaskPriority.HIGH);
    }

    public TaskPending<T> submit() {
        if (started.get()) {
            throw new RejectedExecutionException("Task has been shut down");
        }

        for (;;) {
            int submitted = this.queued.get();
            int priority = this.priority.get();
            if (submitted == priority) return this;

            if (this.queued.compareAndSet(submitted, priority)) {
                queues.queue(priority, this);
                wakeup.run();
                break;
            }
        }
        return this;
    }

    public boolean cancel() {
        return started.compareAndSet(false, true);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        cancel();
        return super.cancel(mayInterruptIfRunning);
    }
}
