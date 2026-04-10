package com.falchus.lib.utils.builder;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.utils.reflection.ReflectionUtils;
import lombok.Getter;
import lombok.NonNull;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
public class ClassInstanceBuilder {

    private final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
    private Class<?> clazz;
    private Class<?>[] parents = new Class<?>[0];
    private Object[] children = new Object[0];

    /**
     * Creates a new {@link ClassInstanceBuilder} by class.
     */
    public ClassInstanceBuilder(@NonNull Class<?> clazz) {
        this.clazz = clazz;
    }

    /**
     * Creates a new {@link ClassInstanceBuilder} by class name.
     */
    public ClassInstanceBuilder(@NonNull String name) {
        this.clazz = ReflectionUtils.getClass(name);
    }

    /**
     * Creates a new {@link ClassInstanceBuilder} by first class.
     */
    public ClassInstanceBuilder(@NonNull String... names) {
        this.clazz = ReflectionUtils.getFirstClass(
                names
        );
    }

    /**
     * Sets constructor parameters for the class.
     */
    @SafeVarargs
    public final ClassInstanceBuilder withParams(@NonNull Map<Class<?>, Object>... params) {
        parents = new Class<?>[params.length];
        children = new Object[params.length];

        for (int i = 0; i < params.length; i++) {
            Map<Class<?>, Object> map = params[i];
            if (map.size() != 1) {
                throw new IllegalArgumentException("Each map must contain exactly one entry: {Class -> value}");
            }

            Map.Entry<Class<?>, Object> entry = map.entrySet().iterator().next();
            parents[i] = entry.getKey();
            children[i] = entry.getValue();
        }
        return this;
    }

    /**
     * Builds and returns the final class instance.
     */
    public Object build() {
        if (clazz == null) {
            throw new IllegalStateException("Class must be set");
        }

        try {
            Constructor<?> ctor = ReflectionUtils.getFirstConstructor(clazz,
                    Set.of(
                            List.of(parents)
                    )
            );
            return ctor.newInstance(
                    children
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to create class instance: " + clazz.getName(), e);
        }
    }
}
