/*
 * @author: AlphaConqueror
 * Copyright (c) 2020
 * All rights reserved.
 */

package de.alphaconqueror.alphaeventcore;

import de.alphaconqueror.alphaeventcore.eventhandling.ListenerHandler;

public class AlphaEventCore {

    private final static ListenerHandler listenerHandler = new ListenerHandler();

    /**
     * Used to get the {@link ListenerHandler}.
     *
     * @return The listener handler.
     */
    public static ListenerHandler getListenerHandler() {
        return listenerHandler;
    }
}
