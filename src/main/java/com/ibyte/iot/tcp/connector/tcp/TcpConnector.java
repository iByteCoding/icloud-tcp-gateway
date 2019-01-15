package com.ibyte.iot.tcp.connector.tcp;

import com.ibyte.iot.tcp.connector.Session;
import com.ibyte.iot.tcp.connector.tcp.listener.TcpHeartbeatListener;
import com.ibyte.iot.tcp.message.MessageWrapper;

import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpConnector extends ExchangeTcpConnector {

    private final static Logger logger = LoggerFactory.getLogger(TcpConnector.class);

    private TcpHeartbeatListener tcpHeartbeatListener = null;

    public void init() {
        tcpHeartbeatListener = new TcpHeartbeatListener(tcpSessionManager);

        Thread heartbeatThread = new Thread(tcpHeartbeatListener, "tcpHeartbeatListener");
        heartbeatThread.setDaemon(true);
        heartbeatThread.start();
    }

    public void destroy() {
        tcpHeartbeatListener.stop();

        for (Session session : tcpSessionManager.getSessions()) {
            session.close();
        }
        tcpSessionManager = null;
    }

    @Override
    public void connect(ChannelHandlerContext ctx, MessageWrapper wrapper) {
        try {
            Session session = tcpSessionManager.createSession(wrapper.getSessionId(), ctx);
            session.addSessionListener(tcpHeartbeatListener);
            session.connect();

            tcpSessionManager.addSession(session);
            /** send **/
            session.getConnection().send(wrapper.getBody());
        } catch (Exception e) {
            logger.error("TcpConnector connect occur Exception.", e);
        }
    }

    @Override
    public void close(MessageWrapper wrapper) {
        Session session = tcpSessionManager.getSession(wrapper.getSessionId());
        session.getConnection().send(wrapper.getBody());
        session.close();
    }

    @Override
    public void heartbeatClient(MessageWrapper wrapper) {
        try {
            tcpSessionManager.updateSession(wrapper.getSessionId());
            Session session = tcpSessionManager.getSession(wrapper.getSessionId());
            session.getConnection().send(wrapper.getBody());
        } catch (Exception e) {
            logger.error("TcpConnector heartbeatClient occur Exception.", e);
        }
    }

    @Override
    public void responseSendMessage(MessageWrapper wrapper) {
        try {
            Session session = tcpSessionManager.getSession(wrapper.getSessionId());
            session.getConnection().send(wrapper.getBody());
        } catch (Exception e) {
            logger.error("TcpConnector responseSendMessage occur Exception.", e);
        }
    }

    @Override
    public void responseNoKeepAliveMessage(ChannelHandlerContext ctx, MessageWrapper wrapper) {
        try {
            NoKeepAliveTcpConnection connection = new NoKeepAliveTcpConnection(ctx);
            connection.send(wrapper.getBody());
        } catch (Exception e) {
            logger.error("TcpConnector responseNoKeepAliveMessage occur Exception.", e);
        }
    }
}
