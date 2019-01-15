# icloud-tcp-gateway

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![last-commit](https://img.shields.io/github/last-commit/google/skia.svg?style=plastic)](https://github.com/lishangzhi/icloud-tcp-gateway)
[![watchers](https://img.shields.io/github/watchers/badges/shields.svg?label=Watch&style=social)](https://github.com/lishangzhi/icloud-tcp-gateway)
[![code-size](https://img.shields.io/github/languages/code-size/badges/shields.svg)](https://github.com/lishangzhi/icloud-tcp-gateway.git)

#### 介绍
物联网设备网关技术架构设计（Session 管理、心跳管理、数据上行、数据下行）


#### 说明
- TcpServer ：提供TCP连接服务
- TcpSessionManager: 你可以添加监听事件，用于监听TCP会话创建、销毁等
- LogSessionListener：一个日志监听器，它和tcpSessionManager关联，监听器必须事先 SessionListener
- TcpSender：TCP发送者，用户向客户端发送消息通知、实现下行逻辑
- ServerConfig： TCP 的配置管理类
- TcpConnector： TCP 容器,用于管理服务和客户端的连接
- NotifyProxy：  发送通知到代理类

> 以上都是默认配置,你可以不修改，但是你可能需要换个TCP端口



