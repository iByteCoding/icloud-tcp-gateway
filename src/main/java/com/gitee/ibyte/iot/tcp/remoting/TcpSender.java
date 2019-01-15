package com.gitee.ibyte.iot.tcp.remoting;

import com.gitee.ibyte.iot.tcp.connector.Connector;
import com.gitee.ibyte.iot.tcp.message.MessageWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpSender implements Sender {

    private final static Logger logger = LoggerFactory.getLogger(TcpSender.class);

    private Connector tcpConnector;

    public TcpSender(Connector tcpConnector) {
        this.tcpConnector = tcpConnector;
    }

    public void sendMessage(MessageWrapper wrapper) throws RuntimeException {
        try {
            tcpConnector.send(wrapper.getSessionId(), wrapper.getBody());
        } catch (Exception e) {
            logger.error("TcpSender sendMessage occur Exception!", e);
            throw new RuntimeException(e.getCause());
        }
    }

    public boolean existSession(MessageWrapper wrapper) throws RuntimeException {
        try {
            return tcpConnector.exist(wrapper.getSessionId());
        } catch (Exception e) {
            logger.error("TcpSender sendMessage occur Exception!", e);
            throw new RuntimeException(e.getCause());
        }
    }

}
