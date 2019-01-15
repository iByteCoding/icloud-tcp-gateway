package com.icloud.iot.tcp.invoke;
import com.icloud.iot.tcp.connector.tcp.codec.MessageBuf;
import com.icloud.iot.tcp.message.MessageWrapper;
import com.icloud.iot.tcp.message.SystemMessage;

public interface ApiProxy {

    MessageWrapper invoke(SystemMessage sMsg, MessageBuf.JMTransfer message);

}

