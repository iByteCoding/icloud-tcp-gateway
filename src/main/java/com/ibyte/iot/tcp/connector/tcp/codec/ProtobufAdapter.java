package com.ibyte.iot.tcp.connector.tcp.codec;

import com.ibyte.iot.tcp.connector.tcp.config.ServerTransportConfig;

import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

public class ProtobufAdapter {

    private ProtobufDecoder decoder = new ProtobufDecoder(MessageBuf.JMTransfer.getDefaultInstance());
    private ProtobufEncoder encoder = new ProtobufEncoder();

    public ProtobufAdapter(ServerTransportConfig config) {
        // nothing to do
    }

    public ProtobufDecoder getDecoder() {
        return decoder;
    }

    public ProtobufEncoder getEncoder() {
        return encoder;
    }
}
