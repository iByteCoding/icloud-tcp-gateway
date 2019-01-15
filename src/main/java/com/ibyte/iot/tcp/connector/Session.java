package com.ibyte.iot.tcp.connector;

import com.ibyte.iot.tcp.common.Endpoint;

/**
 * Created by Li.shangzhi on 17/1/10.
 */
public interface Session extends Endpoint {

    /**
     * Update the accessed time information for this session.  This method
     * should be called by the context when a request comes in for a particular
     * session, even if the application does not reference it.
     */
    void access();

    /**
     * Inform the listeners about the new session and open connection.
     */
    void connect();

    /**
     * Perform the internal processing required to invalidate this session,
     * without triggering an exception if the session has already expired.
     * then close the connection.
     */
    void close();

    /**
     * Release all object references, and initialize instance variables, in
     * preparation for reuse of this object.
     */
    void recycle();

    boolean expire();

}
