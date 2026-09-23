package com.falchus.lib.utils.wrapper;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;
import com.falchus.lib.utils.reflection.Dummy;
import com.falchus.lib.utils.wrapper.impl.FirstClassWrapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

@AllArgsConstructor
public final class WrapperRegistry<T extends Wrapper<?>> {

    private final Map<Class<?>, Function<Object, T>> map = new HashMap<>();
    private final Function<Object, T> fallback;

    public void put(@NonNull Class<?> type, @NonNull Function<Object, T> factory) {
        map.putIfAbsent(type, factory);
    }

    public void putAll(@NonNull Set<Class<?>> types, @NonNull Function<Object, T> factory) {
        types.forEach(type -> put(type, factory));
    }

    @SuppressWarnings("unchecked")
    public void register(@NonNull List<Class<? extends T>> wrappers) {
        if (!map.isEmpty()) return;

        for (Class<? extends T> wrapper : wrappers) {
            T dummy = (T) new ClassInstanceBuilder(
                wrapper
            ).withParams(
                Map.of(
                    Object.class,
                    Dummy.instance
                )
            ).build();

            Function<Object, T> factory = handle -> (T) new ClassInstanceBuilder(
                wrapper
            ).withParams(
                Map.of(
                    Object.class,
                    handle
                )
            ).build();
            if (dummy instanceof FirstClassWrapper<?> w) {
                putAll(w.getClasses(), factory);
                continue;
            }

            Object handle = dummy.getHandle();
            if (!(handle instanceof Dummy)) {
                put(handle.getClass(), factory);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <U extends T> U wrap(Object handle) {
        if (handle == null) return null;

        Class<?> type = handle.getClass();
        while (type != null) {
            Function<Object, T> factory = map.get(type);
            if (factory != null) {
                return (U) factory.apply(handle);
            }
            type = type.getSuperclass();
        }
        return (U) fallback.apply(handle);
    }
}
