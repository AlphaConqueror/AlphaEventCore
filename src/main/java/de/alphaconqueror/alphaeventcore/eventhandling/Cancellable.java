/*
 * @author: AlphaConqueror
 * Copyright (c) 2020
 * All rights reserved.
 */

package de.alphaconqueror.alphaeventcore.eventhandling;

/**
 * Implement this to be able to cancel events when called.
 */
public interface Cancellable {

    /**
     * Gets the current state of the event.
     *
     * @return True, if the event has been cancelled, false, if otherwise.
     */
    boolean isCancelled();

    /**
     * Sets the current state of the event.
     *
     * @param cancel True, if you want to cancel the event.
     */
    void setCancelled(boolean cancel);
}
