/*
 * @author: AlphaConqueror
 * Copyright (c) 2020
 * All rights reserved.
 */

package de.alphaconqueror.alphaeventcore.eventhandling;

public interface Cancellable {

    /**
     * Gets the current state of the event.
     *
     * @return True, if the event has been cancelled, false, if otherwise.
     */
    public boolean isCancelled();

    /**
     * Sets the current state of the event.
     *
     * @param cancel True, if you want to cancel the event.
     */
    public void setCancelled(boolean cancel);
}
