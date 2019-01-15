package com.gitee.ibyte.iot.tcp.connector.api.listener;

import java.util.EventListener;

/**
 * Implementations of this interface are notified of changes to the list of
 * active sessions in a TSM application. To receive notification events, the
 * implementation class must be configured in the deployment descriptor for the
 * TSM application.
 */
public interface SessionListener extends EventListener {

    /**
     * Notification that a session was created.
     *
     * @param se the notification event
     */
    void sessionCreated(SessionEvent se);

    /**
     * Notification that a session is about to be invalidated.
     *
     * @param se the notification event
     */
    void sessionDestroyed(SessionEvent se);
}
