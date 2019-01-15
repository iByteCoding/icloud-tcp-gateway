package com.gitee.ibyte.iot.tcp.connector.tcp;

import com.gitee.ibyte.iot.tcp.exception.LostConnectException;
import com.gitee.ibyte.iot.tcp.exception.PushException;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoKeepAliveTcpConnection<T> {

    private final static Logger logger = LoggerFactory.getLogger(NoKeepAliveTcpConnection.class);

    private ChannelHandlerContext cxt;

    public NoKeepAliveTcpConnection(ChannelHandlerContext cxt) {
        this.cxt = cxt;
    }

    public void close() {
        cxt.close();
        logger.info("the connection have been destroyed!");
    }

    public void send(T message) {
        sendMessage(message);
    }

    private void sendMessage(T message) {
        pushMessage(message);
    }

    private void pushMessage(T message) {
        boolean success = true;
        boolean sent = true;
        int timeout = 60;
        try {
            ChannelFuture cf = cxt.write(message);
            cxt.flush();
            if (sent) {
                success = cf.await(timeout);
            }
            if (cf.isSuccess()) {
                logger.info("send success.");
            }
            Throwable cause = cf.cause();
            if (cause != null) {
                throw new PushException(cause);
            }
        } catch (LostConnectException e) {
            logger.error("NoKeepAliveTcpConnection pushMessage occur LostConnectException.", e);
            throw new PushException(e);
        } catch (Exception e) {
            logger.error("NoKeepAliveTcpConnection pushMessage occur Exception.", e);
            throw new PushException(e);
        } catch (Throwable e) {
            logger.error("NoKeepAliveTcpConnection pushMessage occur Throwable.", e);
            throw new PushException("Failed to send message, cause: " + e.getMessage(), e);
        }
        if (!success) {
            throw new PushException("Failed to send message, in timeout(" + timeout + "ms) limit");
        }
    }
}
