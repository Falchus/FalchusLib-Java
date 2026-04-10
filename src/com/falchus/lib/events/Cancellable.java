package com.falchus.lib.events;

public interface Cancellable {

    boolean isCancelled();

    void setCancelled(boolean cancelled);
}
