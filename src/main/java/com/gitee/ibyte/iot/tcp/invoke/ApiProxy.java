package com.gitee.ibyte.iot.tcp.invoke;
import com.gitee.ibyte.iot.tcp.connector.tcp.codec.MessageBuf;
import com.gitee.ibyte.iot.tcp.message.MessageWrapper;
import com.gitee.ibyte.iot.tcp.message.SystemMessage;

public interface ApiProxy {

    MessageWrapper invoke(SystemMessage sMsg, MessageBuf.JMTransfer message);

}

