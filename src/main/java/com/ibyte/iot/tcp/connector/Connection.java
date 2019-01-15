package com.ibyte.iot.tcp.connector;

/**
 * Created by Li.shangzhi on 17/1/10.
 */
public interface Connection<T> {

    void connect();

    void close();

    void send(T message);

    String getConnectionId();

    void setConnectionId(String connectionId);

    Session getSession();

    void setSession(Session session);

}
