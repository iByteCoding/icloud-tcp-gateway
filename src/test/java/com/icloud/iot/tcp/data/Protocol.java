package com.icloud.iot.tcp.data;
import com.icloud.iot.tcp.connector.tcp.codec.MessageBuf;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;

public class Protocol {

    public static MessageBuf.JMTransfer.Builder generateConnect() {
        MessageBuf.JMTransfer.Builder builder = MessageBuf.JMTransfer.newBuilder();
        builder.setVersion("1.0");
        builder.setDeviceId("test");
        builder.setCmd(1000);
        builder.setSeq(1234);
        builder.setFormat(1);
        builder.setFlag(1);
        builder.setPlatform("pc");
        builder.setPlatformVersion("1.0");
        builder.setToken("abc");
        builder.setAppKey("123");
        builder.setTimeStamp("123456");
        builder.setSign("123");

        Login.MessageBufPro.MessageReq.Builder logReq = Login.MessageBufPro.MessageReq.newBuilder();
        logReq.setMethod("connect");
        logReq.setToken("iosaaa");
        logReq.setParam("123");
        logReq.setSign("ios333");
        logReq.setTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        logReq.setV("1.0");
        logReq.setDevice("tcp test");
        logReq.setApp("server");
        logReq.setCmd(Login.MessageBufPro.CMD.CONNECT); // 连接

        builder.setBody(logReq.build().toByteString());

        return builder;
    }

    public static MessageBuf.JMTransfer.Builder generateHeartbeat() {
        MessageBuf.JMTransfer.Builder builder = MessageBuf.JMTransfer.newBuilder();
        builder.setVersion("1.0");
        builder.setDeviceId("test");
        builder.setCmd(1002);
        builder.setSeq(1234);
        builder.setFormat(1);
        builder.setFlag(1);
        builder.setPlatform("pc");
        builder.setPlatformVersion("1.0");
        builder.setToken("abc");
        builder.setAppKey("123");
        builder.setTimeStamp("123456");
        builder.setSign("123");

        Login.MessageBufPro.MessageReq.Builder heartbeatReq = Login.MessageBufPro.MessageReq.newBuilder();
        heartbeatReq.setMethod("123");
        heartbeatReq.setToken("iosaaa");
        heartbeatReq.setParam("123");
        heartbeatReq.setSign("ios333");
        heartbeatReq.setTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        heartbeatReq.setV("1.0");
        heartbeatReq.setDevice("tcp test");
        heartbeatReq.setApp("server");
        heartbeatReq.setCmd(Login.MessageBufPro.CMD.HEARTBEAT); // 心跳

        builder.setBody(heartbeatReq.build().toByteString());

        return builder;
    }
}
