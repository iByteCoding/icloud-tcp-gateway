package com.gitee.ibyte.iot.tcp.message;

import java.io.Serializable;

public class MessageWrapper implements Serializable {

    private MessageProtocol protocol;
    private String sessionId;
    private Object body;

    private MessageWrapper() {
    }

    public MessageWrapper(MessageProtocol protocol, String sessionId, Object body) {
        this.protocol = protocol;
        this.sessionId = sessionId;
        this.body = body;
    }

    public enum MessageProtocol {
        CONNECT, CLOSE, HEART_BEAT, SEND, RECEIVE, NOTIFY, REPLY, NO_CONNECT
    }

    public boolean isConnect() {
        return MessageProtocol.CONNECT.equals(this.protocol);
    }

    public boolean isClose() {
        return MessageProtocol.CLOSE.equals(this.protocol);
    }

    public boolean isHeartbeat() {
        return MessageProtocol.HEART_BEAT.equals(this.protocol);
    }

    public boolean isSend() {
        return MessageProtocol.SEND.equals(this.protocol);
    }

    public boolean isNotify() {
        return MessageProtocol.NOTIFY.equals(this.protocol);
    }

    public boolean isReply() {
        return MessageProtocol.REPLY.equals(this.protocol);
    }

    public boolean isNoKeepAliveMessage() {
        return MessageProtocol.NO_CONNECT.equals(this.protocol);
    }

    public void setProtocol(MessageProtocol protocol) {
        this.protocol = protocol;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

}
