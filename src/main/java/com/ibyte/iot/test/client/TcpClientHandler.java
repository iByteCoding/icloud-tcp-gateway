package com.ibyte.iot.test.client;

import com.ibyte.iot.tcp.connector.tcp.codec.MessageBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//public class TcpClientHandler extends ChannelHandlerAdapter {
public class TcpClientHandler extends ChannelInboundHandlerAdapter {

    private final static Logger logger = LoggerFactory.getLogger(TcpClientHandler.class);

    public void channelRead(ChannelHandlerContext ctx, Object o) throws Exception {
        MessageBuf.JMTransfer message = (MessageBuf.JMTransfer) o;

        logger.info("Client Received Msg :" + message);
        System.out.println("Client Received Msg :" + message);
    }
}
