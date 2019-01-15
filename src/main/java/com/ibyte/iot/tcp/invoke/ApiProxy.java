package com.ibyte.iot.tcp.invoke;
import com.ibyte.iot.tcp.connector.tcp.codec.MessageBuf;
import com.ibyte.iot.tcp.message.MessageWrapper;
import com.ibyte.iot.tcp.message.SystemMessage;

public interface ApiProxy {

    MessageWrapper invoke(SystemMessage sMsg, MessageBuf.JMTransfer message);

}

