## icloud-tcp-gateway
<p align="left">
	<a target="_blank" href="https://search.maven.org/search?q=M-PasS">
		<img src="https://img.shields.io/badge/Maven Central-1.12.0-blue.svg" ></img>
	</a>
	<a target="_blank" href="https://gitee.com/ibyte/icloud-tcp-gateway/blob/master/LICENSE">
		<img src="https://img.shields.io/badge/License-Apache%202.0-blue.svg" ></img>
	</a>
</p>

#### 介绍
物联网设备网关技术架构设计（Session 管理、心跳管理、数据上行、数据下行）

![](https://images.gitee.com/uploads/images/2019/0116/150139_4377424a_1468963.png "屏幕截图.png")

#### 说明

NioEventLoop 是 Netty 的 Reactor 线程，其角色：

1. *Boss Group*：作为服务端 Acceptor 线程，用于 accept 客户端链接，并转发给 WorkerGroup 中的线程。
2. *Worker Group*：作为 IO 线程，负责 IO 的读写，从 SocketChannel 中读取报文或向 SocketChannel 写入报文。
3. *Task Queue／Delay Task Queue*：作为定时任务线程，执行定时任务，例如链路空闲检测和发送心跳消息等。

##### 概要说明
- TcpServer ：提供TCP连接服务
- TcpSessionManager: 你可以添加监听事件，用于监听TCP会话创建、销毁等
- LogSessionListener：一个日志监听器，它和tcpSessionManager关联，监听器必须事先 SessionListener
- TcpSender：TCP发送者，用户向客户端发送消息通知、实现下行逻辑
- ServerConfig： TCP 的配置管理类
- TcpConnector： TCP 容器,用于管理服务和客户端的连接
- NotifyProxy：  发送通知到代理类

> 以上都是默认配置,你可以不修改，但是你可能需要换个TCP端口

### .TCP网关的网络结构
基于Netty构建TCP网关的长连接容器，作为网关接入层提供服务API请求调用。

客户端通过域名+端口访问TCP网关，域名不同的运营商对应不同的VIP，VIP发布在LVS上，LVS将请求转发给后端的HAProxy，再由HAProxy把请求转发给后端的Netty的IP+Port。

LVS转发给后端的HAProxy，请求经过LVS，但是响应是HAProxy直接反馈给客户端的，这也就是LVS的DR模式。
![输入图片说明](https://images.gitee.com/uploads/images/2019/0926/103319_0f13ca35_1468963.png "c5da1236f6d6c151081a215ab3a3170c.png")
#### TCP网关执行时序图
![输入图片说明](https://images.gitee.com/uploads/images/2019/0116/150230_e846b0a7_1468963.png "屏幕截图.png")

其中步骤一至步骤九是 Netty 服务端的创建时序，步骤十至步骤十三是 TCP 网关容器创建的时序。
- **步骤一**：创建 ServerBootstrap 实例，ServerBootstrap 是 Netty 服务端的启动辅助类。
- **步骤二**：设置并绑定 Reactor 线程池，EventLoopGroup 是 Netty 的 Reactor 线程池，EventLoop 负责所有注册到本线程的 Channel。
- **步骤三**：设置并绑定服务器 Channel，Netty Server 需要创建 NioServerSocketChannel 对象。
- **步骤四**：TCP 链接建立时创建 ChannelPipeline，ChannelPipeline 本质上是一个负责和执行 ChannelHandler 的职责链。
- **步骤五**：添加并设置 ChannelHandler，ChannelHandler 串行的加入 ChannelPipeline 中。
- **步骤六**：绑定监听端口并启动服务端，将 NioServerSocketChannel 注册到 Selector 上。
- **步骤七**：Selector 轮训，由 EventLoop 负责调度和执行 Selector 轮询操作。
- **步骤八**：执行网络请求事件通知，轮询准备就绪的 Channel，由 EventLoop 执行 ChannelPipeline。
- **步骤九**：执行 Netty 系统和业务 ChannelHandler，依次调度并执行 ChannelPipeline 的 ChannelHandler。
- **步骤十**：通过 Proxy 代理调用后端服务，ChannelRead 事件后，通过发射调度后端 Service。
- **步骤十一**：创建 Session，Session 与 Connection 是相互依赖关系。
- **步骤十二**：创建 Connection，Connection 保存 ChannelHandlerContext。
- **步骤十三**：添加 SessionListener，SessionListener 监听 SessionCreate 和 SessionDestory 等事件。


### 程序运行案例步骤
> 测试案例，三秒心跳包上传数据包

![输入图片说明](https://images.gitee.com/uploads/images/2019/0925/185216_0aa0fe1c_1468963.jpeg "icloud-tcp-gateway.jpg")
#### 1.配置本地Host
Window 地址 // C:\Windows\System32\drivers\etc\hosts

添加
`` 127.0.0.1  iot-open.icloud.com ``

#### 2.启动Server
位置: ``com.ibyte.iot.test.server.TestTcpServer``

#### 2.启动Client
位置: ``com.ibyte.iot.test.client.TcpClient``




