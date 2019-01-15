package com.gitee.ibyte.iot.tcp.connector.tcp;

import com.gitee.ibyte.iot.tcp.connector.Session;
import com.gitee.ibyte.iot.tcp.connector.api.ExchangeConnector;
import com.gitee.ibyte.iot.tcp.message.MessageWrapper;

import io.netty.channel.ChannelHandlerContext;

public abstract class ExchangeTcpConnector<T> extends ExchangeConnector<T> {

    protected TcpSessionManager tcpSessionManager = null;

    public abstract void connect(ChannelHandlerContext ctx, MessageWrapper wrapper);

    public abstract void close(MessageWrapper wrapper);

    /**
     * 会话心跳
     *
     * @param wrapper
     */
    public abstract void heartbeatClient(MessageWrapper wrapper);

    /**
     * 接收客户端消息通知响应
     *
     * @param wrapper
     */
    public abstract void responseSendMessage(MessageWrapper wrapper);

    public abstract void responseNoKeepAliveMessage(ChannelHandlerContext ctx, MessageWrapper wrapper);

    public void send(String sessionId, T message) throws Exception {
        super.send(tcpSessionManager, sessionId, message);
    }

    public boolean exist(String sessionId) throws Exception {
        Session session = tcpSessionManager.getSession(sessionId);
        return session != null ? true : false;
    }

    public void setTcpSessionManager(TcpSessionManager tcpSessionManager) {
        this.tcpSessionManager = tcpSessionManager;
    }
}
