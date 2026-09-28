package com.falchus.lib.task.executor;

import com.falchus.lib.enums.TaskPriority;
import com.falchus.lib.task.PriorityQueues;
import com.falchus.lib.task.TaskPending;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Delegate;

import java.util.concurrent.*;
import java.util.function.Supplier;

@Getter
public class PriorityQueuedExecutor implements Executor {

    @Delegate private final PriorityQueues queues = new PriorityQueues();

    public PriorityQueuedExecutor(@NonNull String name, int threads, int threadPriority) {
        if (threads <= -1) {
            threads = Math.max(1, Runtime.getRuntime().availableProcessors() - 1);
        }
        for (int i = 0; i < threads; i++) {
            ExecutorThread thread = new ExecutorThread(this::process);
            thread.setDaemon(true);
            thread.setName(threads == 1
                ? name
                : name + "-" + (i + 1));
            thread.setPriority(threadPriority);
            thread.start();
        }
    }

    public PriorityQueuedExecutor(@NonNull String name, int threads) {
        this(name, threads, Thread.NORM_PRIORITY);
    }

    public PriorityQueuedExecutor(@NonNull String name) {
        this(name, -1);
    }

    public static PriorityQueuedExecutor getExecutor() {
        if (!(Thread.currentThread() instanceof ExecutorThread thread)) return null;
        return thread.executor;
    }

    public boolean isCurrentThread() {
        if (!(Thread.currentThread() instanceof ExecutorThread thread)) return false;
        return thread.executor == this;
    }

    public <T> TaskPending<T> createTask(@NonNull Supplier<T> supplier, @NonNull TaskPriority priority) {
        return new TaskPending<>(supplier, queues, this::wakeup, priority);
    }

    public <T> TaskPending<T> createTask(@NonNull Supplier<T> supplier) {
        return createTask(supplier, TaskPriority.NORMAL);
    }

    public TaskPending<Void> createTask(@NonNull Runnable runnable, @NonNull TaskPriority priority) {
        return createTask(() -> {
            runnable.run();
            return null;
        }, priority);
    }

    public TaskPending<Void> createTask(@NonNull Runnable runnable) {
        return createTask(runnable, TaskPriority.NORMAL);
    }

    public <T> TaskPending<T> submitTask(@NonNull Supplier<T> supplier, @NonNull TaskPriority priority) {
        return createTask(supplier, priority).submit();
    }

    public <T> TaskPending<T> submitTask(@NonNull Supplier<T> supplier) {
        return createTask(supplier).submit();
    }

    public TaskPending<Void> submitTask(@NonNull Runnable runnable, @NonNull TaskPriority priority) {
        return createTask(runnable, priority).submit();
    }

    public TaskPending<Void> submitTask(@NonNull Runnable runnable) {
        return createTask(runnable).submit();
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        submitTask(runnable);
    }

    private void process() {
        Runnable runnable = null;
        while (!Thread.currentThread().isInterrupted()) {
            if (runnable != null) {
                runnable.run();
            }
            if ((runnable = poll()) != null) continue;

            synchronized (this) {
                if ((runnable = poll()) != null) continue;

                try {
                    wait();
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public boolean processUrgent() {
        Runnable runnable;
        boolean had = false;
        while ((runnable = pollUrgent()) != null) {
            runnable.run();
            had = true;
        }
        return had;
    }

    private void wakeup() {
        synchronized (this) {
            notify();
        }
    }

    private final class ExecutorThread extends Thread {

        private final PriorityQueuedExecutor executor = PriorityQueuedExecutor.this;

        private ExecutorThread(@NonNull Runnable runnable) {
            super(runnable);
        }
    }
}
