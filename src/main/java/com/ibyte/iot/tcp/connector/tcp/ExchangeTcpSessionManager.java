package com.ibyte.iot.tcp.connector.tcp;

import com.ibyte.iot.tcp.connector.Session;
import com.ibyte.iot.tcp.connector.api.ExchangeSessionManager;

import io.netty.channel.ChannelHandlerContext;

public abstract class ExchangeTcpSessionManager extends ExchangeSessionManager {

    public abstract Session createSession(String sessionId, ChannelHandlerContext ctx);

}
