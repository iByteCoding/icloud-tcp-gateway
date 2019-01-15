package com.gitee.ibyte.iot.tcp.connector.tcp.listener;

import com.gitee.ibyte.iot.tcp.connector.Session;
import com.gitee.ibyte.iot.tcp.connector.api.listener.SessionEvent;
import com.gitee.ibyte.iot.tcp.connector.api.listener.SessionListener;
import com.gitee.ibyte.iot.tcp.connector.tcp.TcpSessionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TcpHeartbeatListener implements Runnable, SessionListener {

    private final static Logger logger = LoggerFactory.getLogger(TcpHeartbeatListener.class);

    private TcpSessionManager tcpSessionManager = null;

    private ReentrantLock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();

    private int checkPeriod = 30 * 1000;
    private volatile boolean stop = false;

    public TcpHeartbeatListener(TcpSessionManager tcpSessionManager) {
        this.tcpSessionManager = tcpSessionManager;
    }

    public void run() {
        while (!stop) {
            if (isEmpty()) {
                awaitQueue();
            }
            logger.info("TcpHeartbeatListener/online session count : " + tcpSessionManager.getSessionCount());
            // sleep period
            try {
                Thread.sleep(checkPeriod);
            } catch (InterruptedException e) {
                logger.error("TcpHeartbeatListener run occur InterruptedException!", e);
            }
            // is stop
            if (stop) {
                break;
            }
            // 检测在线用户，多久没有发送心跳，超过规定时间的删除掉
            checkHeartBeat();
        }
    }

    public void checkHeartBeat() {
        Session[] sessions = tcpSessionManager.getSessions();
        for (Session session : sessions) {
            if (session.expire()) {
                session.close();
                logger.info("heart is expire,clear sessionId:" + session.getSessionId());
            }
        }
    }

    private boolean isEmpty() {
        return tcpSessionManager.getSessionCount() == 0;
    }

    private void awaitQueue() {
        boolean flag = lock.tryLock();
        if (flag) {
            try {
                notEmpty.await();
            } catch (InterruptedException e) {
                logger.error("TcpHeartbeatListener awaitQueue occur InterruptedException!", e);
            } catch (Exception e) {
                logger.error("await Thread Queue error!", e);
            } finally {
                lock.unlock();
            }
        }
    }

    private void signalQueue() {
        boolean flag = false;
        try {
            flag = lock.tryLock(100, TimeUnit.MILLISECONDS);
            if (flag)
                notEmpty.signalAll();
        } catch (InterruptedException e) {
            logger.error("TcpHeartbeatListener signalQueue occur InterruptedException!", e);
        } catch (Exception e) {
            logger.error("signal Thread Queue error!", e);
        } finally {
            if (flag)
                lock.unlock();
        }
    }

    public void stop() {
        this.stop = true;
    }

    public void sessionCreated(SessionEvent se) {
        signalQueue();
    }

    public void sessionDestroyed(SessionEvent se) {
    }

}
