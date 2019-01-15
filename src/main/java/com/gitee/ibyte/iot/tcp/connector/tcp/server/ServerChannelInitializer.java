package com.gitee.ibyte.iot.tcp.connector.tcp.server;

import com.gitee.ibyte.iot.tcp.connector.tcp.codec.ProtobufAdapter;
import com.gitee.ibyte.iot.tcp.connector.tcp.config.ServerTransportConfig;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

@ChannelHandler.Sharable
public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private ServerTransportConfig config;

    public ServerChannelInitializer(ServerTransportConfig config) {
        this.config = config;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ProtobufAdapter adapter = new ProtobufAdapter(config);

        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
        pipeline.addLast("decoder", adapter.getDecoder());
        pipeline.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast("encoder", adapter.getEncoder());
        pipeline.addLast("handler", new TcpServerHandler(config));
    }
}
