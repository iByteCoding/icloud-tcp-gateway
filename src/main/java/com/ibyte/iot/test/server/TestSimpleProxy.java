package com.ibyte.iot.test.server;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.ibyte.iot.tcp.connector.tcp.codec.MessageBuf;
import com.ibyte.iot.tcp.invoke.ApiProxy;
import com.ibyte.iot.tcp.message.MessageWrapper;
import com.ibyte.iot.tcp.message.SystemMessage;
import com.ibyte.iot.test.data.Login;
import com.ibyte.iot.test.data.Protocol;

public class TestSimpleProxy implements ApiProxy {

    public MessageWrapper invoke(SystemMessage sMsg, MessageBuf.JMTransfer message) {
        ByteString body = message.getBody();

        if (message.getCmd() == 1000) {
            try {
                Login.MessageBufPro.MessageReq messageReq = Login.MessageBufPro.MessageReq.parseFrom(body);
                if (messageReq.getCmd().equals(Login.MessageBufPro.CMD.CONNECT)) {
                    return new MessageWrapper(MessageWrapper.MessageProtocol.CONNECT, message.getToken(), null);
                }
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        } else if (message.getCmd() == 1002) {
            try {
                Login.MessageBufPro.MessageReq messageReq = Login.MessageBufPro.MessageReq.parseFrom(body);
                if (messageReq.getCmd().equals(Login.MessageBufPro.CMD.HEARTBEAT)) {
                    MessageBuf.JMTransfer.Builder resp = Protocol.generateHeartbeat();
                    return new MessageWrapper(MessageWrapper.MessageProtocol.HEART_BEAT, message.getToken(), resp);
                }
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
