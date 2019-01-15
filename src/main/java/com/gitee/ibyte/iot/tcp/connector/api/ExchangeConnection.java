package com.gitee.ibyte.iot.tcp.connector.api;

import com.gitee.ibyte.iot.tcp.connector.Connection;
import com.gitee.ibyte.iot.tcp.connector.Session;

/**
 * Created by Li.shangzhi on 17/1/10.
 */
public abstract class ExchangeConnection<T> implements Connection<T> {

    protected Session session = null;
    protected String connectionId = null;

    protected volatile boolean close = false;
    protected int connectTimeout = 60 * 60 * 1000; // ms

    public void fireError(RuntimeException e) {
        throw e;
    }

    public boolean isClosed() {
        return close;
    }

    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    public String getConnectionId() {
        return connectionId;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

}
