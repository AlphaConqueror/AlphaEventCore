/*
 * @author: AlphaConqueror
 * Copyright (c) 2020
 * All rights reserved.
 */

package de.alphaconqueror.alphaeventcore.eventhandling;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ListenerHandler {

    private final List<Listener> listeners = new ArrayList<Listener>();

    /**
     * Used to call an {@link Event}.
     *
     * @param event The event to be called.
     */
    public boolean callEvent(Event event) {
        boolean isExecutable = true;

        for(Listener listener : listeners)
            for(Method method : listener.getClass().getMethods()) {
                try {
                    Class<?>[] parameterTypes = method.getParameterTypes();

                    if (parameterTypes.length == 1) {
                        Class<?> parameter = parameterTypes[0];

                        if (parameter.getCanonicalName().equalsIgnoreCase(event.getClass().getCanonicalName())) {
                            method.setAccessible(true);
                            method.invoke(listener, new Object[]{event});

                            System.out.println("EVENT: Got event " + event.getEventName());
                            System.out.println("EVENT: Is cancellable?: " + Cancellable.class.isAssignableFrom(event.getClass()));

                            if (Cancellable.class.isAssignableFrom(event.getClass()))
                                isExecutable = !((Cancellable) event).isCancelled();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        return isExecutable;
    }

    /**
     * Used to get all listener classes.
     *
     * @return A list of all listener classes.
     */
    public List<Listener> getListeners() {
        return listeners;
    }

    /**
     * Used to add a listener class.
     *     Note: Listener has to be added to be considered by the handler.
     *
     * @param listener The listener class.
     */
    public void addListener(Listener listener) {
        listeners.add(listener);
    }
}
