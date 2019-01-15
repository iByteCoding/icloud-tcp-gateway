package com.gitee.ibyte.iot.tcp.connector.api;

import com.gitee.ibyte.iot.tcp.connector.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Li.shangzhi on 17/1/10.
 */
public abstract class SessionValid implements Session {

    /**
     * We are currently processing a session create, so bypass certain
     * IllegalStateException tests. NOTE: This value is not included in the
     * serialized version of this object.
     */
    protected transient volatile boolean connecting = false;

    /**
     * We are currently processing a session expiration, so bypass certain
     * IllegalStateException tests. NOTE: This value is not included in the
     * serialized version of this object.
     */
    protected transient volatile boolean closing = false;

    /**
     * The time this session was created, in milliseconds since midnight,
     * January 1, 1970 GMT.
     */
    protected long creationTime = 0L;

    /**
     * The last accessed time for this Session.
     */
    protected volatile long lastAccessedTime = creationTime;

    /**
     * Flag indicating whether this session is valid or not.
     */
    protected volatile boolean isValid = false;

    /**
     * The maximum time interval, in seconds, between client requests before the
     * container may invalidate this session. A negative time indicates that the
     * session should never time out.
     */
    protected int maxInactiveInterval = 5 * 60;

    /**
     * The collection of user data attributes associated with this Session.
     */
    protected Map<String, Object> attributes = new ConcurrentHashMap<String, Object>();

    public boolean isValid() {
        if (closing) {
            return true;
        }
        return (isValid);
    }

    public void setValid(boolean isValid) {
        this.isValid = isValid;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setLastAccessedTime(long lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    public long getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    public void setAttribute(String name, Object value) {
        if (!isValid()) {
            throw new IllegalStateException("[setAttribute]Session already invalidated");
        }

        if (name == null)
            return;

        attributes.put(name, value);
    }

    public Object getAttribute(String name) {
        if (!isValid()) {
            throw new IllegalStateException("[getAttribute]Session already invalidated");
        }

        if (name == null)
            return null;

        return (attributes.get(name));
    }
}
