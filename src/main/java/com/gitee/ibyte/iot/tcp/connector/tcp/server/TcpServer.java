package com.gitee.ibyte.iot.tcp.connector.tcp.server;
import com.gitee.ibyte.iot.tcp.connector.tcp.config.ServerTransportConfig;
import com.gitee.ibyte.iot.tcp.exception.InitErrorException;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class TcpServer {

    private final static Logger logger = LoggerFactory.getLogger(TcpServer.class);

    private ServerTransportConfig serverConfig;

    private int port;

    private static final int BIZ_GROUP_SIZE = Runtime.getRuntime().availableProcessors() * 2;
    private static final int BIZ_THREAD_SIZE = 4;

    private final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZ_GROUP_SIZE);
    private final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZ_THREAD_SIZE);

    public void init() throws Exception {
        boolean flag = Boolean.FALSE;
        
        
        
        logger.info("start tcp server ...");

        Class clazz = NioServerSocketChannel.class;
        // Server 服务启动
        ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(clazz);
        bootstrap.childHandler(new ServerChannelInitializer(serverConfig));
        // 可选参数
        bootstrap.childOption(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 32 * 1024);

        // 绑定接口，同步等待成功
        logger.info("start tcp server at port[" + port + "].");
        ChannelFuture future = bootstrap.bind(port).sync();
        ChannelFuture channelFuture = future.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    logger.info("Server have success bind to " + port);
                } else {
                    logger.error("Server fail bind to " + port);
                    throw new InitErrorException("Server start fail !", future.cause());
                }
            }
        });
    }

    public void shutdown() {
        logger.info("shutdown tcp server ...");
        // 释放线程池资源
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
        logger.info("shutdown tcp server end.");
    }

    //------------------ set && get --------------------


    public void setServerConfig(ServerTransportConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
