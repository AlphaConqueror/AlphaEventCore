/*
 * @author: AlphaConqueror
 * Copyright (c) 2020
 * All rights reserved.
 */

package de.alphaconqueror.alphaeventcore;

import de.alphaconqueror.alphaeventcore.eventhandling.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class AlphaEventCore {

    /**
     * A {@link List} containing all registered {@link EventListener}s.
     */
    private static final List<EventListener> eventListeners = new ArrayList<>();

    /**
     * Calls an {@link Event}.
     *
     * @param event The event to be called.
     *
     * @return true, if the event is executable, false, if the event has been cancelled.
     */
    public static boolean callEvent(Event event) {
        boolean isExecutable = true;

        for(EventListener eventListener : eventListeners) {
            List<Method> sortedMethods = new ArrayList<>();

            A: for(Method method : eventListener.getClass().getMethods()) {
                if(method.isAnnotationPresent(EventHandler.class)) {
                    int priority = method.getAnnotation(EventHandler.class).priority();

                    for(int i = 0; i < sortedMethods.size(); i++) {
                        int p = sortedMethods.get(i).getAnnotation(EventHandler.class).priority();

                        if(p > priority) {
                            sortedMethods.add(i, method);
                            continue A;
                        }
                    }

                    sortedMethods.add(method);
                }
            }

            for(Method method : sortedMethods) {
                try {
                    Class<?>[] parameterTypes = method.getParameterTypes();

                    if(parameterTypes.length == 1) {
                        Class<?> parameter = parameterTypes[0];

                        if(parameter.getCanonicalName().equalsIgnoreCase(event.getClass().getCanonicalName())) {
                            method.setAccessible(true);
                            method.invoke(eventListener, event);

                            if(Cancellable.class.isAssignableFrom(event.getClass()))
                                isExecutable = !((Cancellable) event).isCancelled();
                        }
                    }
                } catch(Exception ignored) {}
            }
        }

        return isExecutable;
    }

    /**
     * Gets a {@link List} containing all registered {@link EventListener}s.
     *
     * @return A list containing all registered event listeners.
     */
    public static List<EventListener> getEventListeners() {
        return eventListeners;
    }

    /**
     * Adds an {@link EventListener} to the {@link List} containing all registered event listeners.
     *
     * @param eventListener The event listener to be added.
     *
     * Note: Listener has to be added to be called by #callEvent().
     */
    public static void addListener(EventListener eventListener) {
        eventListeners.add(eventListener);
    }
}
