package com.icloud.iot.tcp.client;
import com.icloud.iot.tcp.connector.tcp.codec.MessageBuf;
import com.icloud.iot.tcp.data.Protocol;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class TcpClient {

    public static String host = "iot-open.icloud.com";
    public static int port = 2000;

    public static Bootstrap bootstrap = getBootstrap();
    public static Channel channel = getChannel(host, port);

    /**
     * Init Bootstrap
     */
    public static final Bootstrap getBootstrap() {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group);
        b.channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("frameDecoder", new ProtobufVarint32FrameDecoder());
                pipeline.addLast("decoder", new ProtobufDecoder(MessageBuf.JMTransfer.getDefaultInstance()));
                pipeline.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender());
                pipeline.addLast("encoder", new ProtobufEncoder());
                pipeline.addLast("handler", new TcpClientHandler());
            }
        });


        b.option(ChannelOption.SO_KEEPALIVE, true);
        return b;
    }

    public static final Channel getChannel(String host, int port) {
        Channel channel;
        try {
            channel = bootstrap.connect(host, port).sync().channel();
        } catch (Exception e) {
            System.out.println("Connect Server (host[" + host + "]:port[" + port + "]) Failure." + e);
            return null;
        }
        return channel;
    }

    public static void connect(Object msg) throws Exception {
        if (channel != null) {
            channel.writeAndFlush(msg).sync();
        }
    }


    public static void main(String[] args) throws Exception {
        try {
            TcpClient.connect(Protocol.generateConnect());
            for (; ; ) {
                TcpClient.connect(Protocol.generateHeartbeat());
                Thread.sleep(3000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

