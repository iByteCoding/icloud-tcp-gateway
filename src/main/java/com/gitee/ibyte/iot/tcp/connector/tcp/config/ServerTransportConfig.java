package com.gitee.ibyte.iot.tcp.connector.tcp.config;

import com.gitee.ibyte.iot.tcp.connector.tcp.TcpConnector;
import com.gitee.ibyte.iot.tcp.invoke.ApiProxy;
import com.gitee.ibyte.iot.tcp.notify.NotifyProxy;

import io.netty.handler.codec.protobuf.ProtobufDecoder;

public class ServerTransportConfig {

    // handler
    private TcpConnector tcpConnector = null;
    // codec
    private ProtobufDecoder decoder = null;
    // invoke
    private ApiProxy proxy = null;
    private NotifyProxy notify = null;

    public ServerTransportConfig(TcpConnector tcpConnector, ApiProxy proxy, NotifyProxy notify) {
        this.tcpConnector = tcpConnector;
        this.proxy = proxy;
        this.notify = notify;
    }

    public TcpConnector getTcpConnector() {
        return tcpConnector;
    }

    public ProtobufDecoder getDecoder() {
        return decoder;
    }

    public ApiProxy getProxy() {
        return proxy;
    }

    public NotifyProxy getNotify() {
        return notify;
    }
}
