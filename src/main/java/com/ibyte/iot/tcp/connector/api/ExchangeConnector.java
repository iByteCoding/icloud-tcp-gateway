package com.ibyte.iot.tcp.connector.api;

import com.ibyte.iot.tcp.connector.Connector;
import com.ibyte.iot.tcp.connector.Session;
import com.ibyte.iot.tcp.connector.SessionManager;
import com.ibyte.iot.tcp.exception.DispatchException;
import com.ibyte.iot.tcp.exception.PushException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Li.shangzhi on 17/1/10.
 */
public abstract class ExchangeConnector<T> implements Connector<T> {

    private final static Logger logger = LoggerFactory.getLogger(ExchangeConnector.class);

    public void send(SessionManager sessionManager, String sessionId, T message) throws Exception {
        Session session = sessionManager.getSession(sessionId);
        if (session == null) {
            throw new Exception(String.format("session %s no exist.", sessionId));
        }
        try {
            session.getConnection().send(message);
            session.access();
        } catch (PushException e) {
            logger.error("ExchangeConnector send occur PushException.", e);
            session.close();
            throw new DispatchException(e);
        } catch (Exception e) {
            logger.error("ExchangeConnector send occur Exception.", e);
            session.close();
            throw new DispatchException(e);
        }
    }
}
