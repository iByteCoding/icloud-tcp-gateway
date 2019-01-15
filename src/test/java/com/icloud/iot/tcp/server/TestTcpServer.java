package com.icloud.iot.tcp.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class TestTcpServer {

    public static void main(String[] args) {
        ApplicationContext context = new FileSystemXmlApplicationContext(new String[]{"classpath:spring-config.xml"});
        context.getApplicationName();
    }

}
