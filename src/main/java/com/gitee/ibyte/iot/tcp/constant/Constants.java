package com.gitee.ibyte.iot.tcp.constant;

import io.netty.util.AttributeKey;

public class Constants {

    /**
     * 默认启动端口，包括不配置或者随机，都从此端口开始计算
     */
    public static final int DEFAULT_SERVER_PORT = 22000;

    public static final int NOTIFY_SUCCESS = 1;
    public static final int NOTIFY_FAILURE = 0;
    public static final int NOTIFY_NO_SESSION = 2;

    public static final AttributeKey<String> SERVER_SESSION_HOOK = AttributeKey.valueOf("SERVER_SESSION_HOOK");

}
