package com.falchus.lib.enums;

/**
 * Enum representing in which order tasks will be executed.
 */
public enum TaskPriority {

    /**
     * Tasks will be executed in the order they were added.
     */
    NORMAL,

    /**
     * Tasks will be executed before NORMAL tasks.
     */
    HIGH,

    /**
     * Tasks will be executed before HIGH and NORMAL tasks.
     * Shouldn't be used often - only when you really need to execute task as soon as possible.
     */
    URGENT
}
