package com.ibyte.iot.tcp.connector;

import java.util.Set;

/**
 * Created by Li.shangzhi on 17/1/10.
 */
public interface SessionManager {

    /**
     * 添加指定session
     *
     * @param session
     */
    void addSession(Session session);

    void updateSession(String sessionId);

    /**
     * 删除指定session
     *
     * @param session
     */
    void removeSession(Session session);

    /**
     * 删除指定session
     *
     * @param sessionId
     */
    void removeSession(String sessionId);

    /**
     * 根据指定sessionId获取session
     *
     * @param sessionId
     * @return
     */
    Session getSession(String sessionId);

    /**
     * 获取所有的session
     *
     * @return
     */
    Session[] getSessions();

    /**
     * 获取所有的session的id集合
     *
     * @return
     */
    Set<String> getSessionKeys();

    /**
     * 获取所有的session数目
     *
     * @return
     */
    int getSessionCount();

    /**
     * Return the default maximum inactive interval (in seconds)
     * for Sessions created by this Manager.
     */
    int getMaxInactiveInterval();

    /**
     * Set the default maximum inactive interval (in seconds)
     * for Sessions created by this Manager.
     *
     * @param interval The new default value
     */
    void setMaxInactiveInterval(int interval);
}
