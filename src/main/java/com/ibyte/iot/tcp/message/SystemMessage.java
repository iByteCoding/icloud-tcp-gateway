package com.ibyte.iot.tcp.message;
import java.io.Serializable;
public class SystemMessage implements Serializable {

    private String remoteAddress;
    private String localAddress;

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }
}
