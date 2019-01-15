package com.ibyte.iot.tcp.remoting;

import com.ibyte.iot.tcp.message.MessageWrapper;

public interface Sender {

    void sendMessage(MessageWrapper wrapper) throws RuntimeException;

    boolean existSession(MessageWrapper wrapper) throws RuntimeException;

}
