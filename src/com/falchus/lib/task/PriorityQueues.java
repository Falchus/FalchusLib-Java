package com.falchus.lib.task;

import com.falchus.lib.enums.TaskPriority;
import lombok.NonNull;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@SuppressWarnings("unchecked")
public class PriorityQueues {

    private static final int priorities = TaskPriority.values().length;

    private final Queue<Runnable>[] queues = new ConcurrentLinkedQueue[priorities];

    public PriorityQueues() {
        for (int i = 0; i < priorities; i++) {
            queues[i] = new ConcurrentLinkedQueue<>();
        }
    }

    public void queue(int priority, @NonNull Runnable runnable) {
        queues[priority].add(runnable);
    }

    public void queue(@NonNull TaskPriority priority, @NonNull Runnable runnable) {
        queue(priority.ordinal(), runnable);
    }

    public Runnable poll() {
        for (int i = priorities - 1; i >= 0; i--) {
            Runnable runnable = queues[i].poll();
            if (runnable != null) {
                return runnable;
            }
        }
        return null;
    }

    public Runnable pollUrgent() {
        return queues[TaskPriority.URGENT.ordinal()].poll();
    }

    public boolean isEmpty() {
        for (Queue<Runnable> queue : queues) {
            if (!queue.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
