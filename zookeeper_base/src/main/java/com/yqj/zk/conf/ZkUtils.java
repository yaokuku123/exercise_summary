package com.yqj.zk.conf;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZkUtils {

    private static ZooKeeper zkClient;
    private static String connectString;
    private static int sessionTimeout;
    private static DefaultWatcher defaultWatcher;
    private static CountDownLatch cc = new CountDownLatch(1);

    public static String getConnectString() {
        return connectString;
    }

    public static void setConnectString(String connectString) {
        ZkUtils.connectString = connectString;
    }

    public static int getSessionTimeout() {
        return sessionTimeout;
    }

    public static void setSessionTimeout(int sessionTimeout) {
        ZkUtils.sessionTimeout = sessionTimeout;
    }

    public static DefaultWatcher getDefaultWatcher() {
        return defaultWatcher;
    }

    public static void setDefaultWatcher(DefaultWatcher defaultWatcher) {
        defaultWatcher.setCc(cc);
        ZkUtils.defaultWatcher = defaultWatcher;
    }

    // 获取zk对象
    public static ZooKeeper getZk() {
        try {
            zkClient = new ZooKeeper(connectString, sessionTimeout, defaultWatcher);
            cc.await(); // 等待建立完毕
            return zkClient;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("getZk error");
        }
    }

    // 关闭zk资源
    public static void close() {
        if (zkClient != null) {
            try {
                zkClient.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
