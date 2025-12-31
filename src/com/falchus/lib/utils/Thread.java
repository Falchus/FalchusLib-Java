package com.falchus.lib.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * Utility class for running tasks.
 */
@UtilityClass
public class Thread {

	private static final int threads = Runtime.getRuntime().availableProcessors();
	private static final ExecutorService executor = Executors.newFixedThreadPool(threads);
	
	/**
	 * Runs the given task synchronously.
	 */
	public static void run(@NonNull Runnable task) {
        task.run();
	}
	
	/**
	 * Runs the given task asynchronously.
	 */
    public static void runAsync(@NonNull Runnable task) {
        executor.execute(task);
    }
    
    /**
     * Shutdown the executor.
     */
    public static void shutdown() {
    	executor.shutdown();
    }
}
