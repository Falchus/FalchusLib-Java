package com.falchus.lib.events;

@FunctionalInterface
public interface EventExecutor {

    void execute(Event event);
}
