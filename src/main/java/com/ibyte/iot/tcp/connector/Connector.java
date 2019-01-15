package com.ibyte.iot.tcp.connector;
/**
 * @FileName Connector.java
 * @Description: 
 * @Date Jan 15, 2019 2:33:15 PM
 * @author Li.shangzhi
 * @version 1.0
 */
public interface Connector<T> {

    void init();

    void destroy();

    void send(String sessionId, T message) throws Exception;

    boolean exist(String sessionId) throws Exception;

}
