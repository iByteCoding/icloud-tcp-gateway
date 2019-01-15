package com.ibyte.iot.tcp.connector.api.listener;

import com.ibyte.iot.tcp.connector.Session;

import java.util.EventObject;

/**
 * This is the class representing event notifications for changes to sessions
 * within a TSM application.
 */
public class SessionEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SessionEvent(Object source) {
        super(source);
    }

    /**
     * Return the session that changed.
     */
    public Session getSession() {
        return (Session) super.getSource();
    }
}
