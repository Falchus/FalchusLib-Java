package com.falchus.lib.task;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.falchus.lib.task.impl.TaskImpl;

import lombok.NonNull;

public class Promise<T> extends CompletableFuture<T> {
	
	private static final Executor sync = runnable -> TaskImpl.getDefaultTask().apply(runnable).run();
	private static final Executor async = runnable -> TaskImpl.getDefaultTask().apply(runnable).runAsync();
	
	public static <T> Promise<T> of(T value) {
		Promise<T> promise = new Promise<>();
		promise.complete(value);
		return promise;
	}
	
	public static <T> Promise<T> completedFuture(T value) {
		return of(value);
	}
	
	public static <T> Promise<T> failedFuture(@NonNull Throwable t) {
		Promise<T> promise = new Promise<>();
		promise.completeExceptionally(t);
		return promise;
	}
	
	public static <T> Promise<T> supplyAsync(@NonNull Supplier<T> supplier) {
		return supplyAsync(supplier, async);
	}
	
	public static <T> Promise<T> supplyAsync(@NonNull Supplier<T> supplier, @NonNull Executor executor) {
		Promise<T> promise = new Promise<>();
		executor.execute(() -> {
			try {
				promise.complete(supplier.get());
			} catch (Throwable t) {
				promise.completeExceptionally(t);
			}
		});
		return promise;
	}
	
	public static Promise<Void> runAsync(@NonNull Runnable runnable, @NonNull Executor executor) {
		return supplyAsync(() -> {
			runnable.run();
			return null;
		}, executor);
	}
	
	public static Promise<Void> runAsync(@NonNull Runnable runnable) {
		return runAsync(runnable, async);
	}
	
	@Override
	public Promise<Void> thenAccept(@NonNull Consumer<? super T> action) {
		return (Promise<Void>) thenAcceptAsync(action, sync);
	}
	
	@Override
	public Promise<Void> thenRun(@NonNull Runnable action) {
		return (Promise<Void>) thenRunAsync(action, sync);
	}
	
	@Override
	public Promise<T> whenComplete(@NonNull BiConsumer<? super T, ? super Throwable> action) {
		return (Promise<T>) whenCompleteAsync(action, sync);
	}
	
	@Override
	public <U> Promise<U> newIncompleteFuture() {
		return new Promise<>();
	}
	
	@Override
	public Executor defaultExecutor() {
		return async;
	}
}
