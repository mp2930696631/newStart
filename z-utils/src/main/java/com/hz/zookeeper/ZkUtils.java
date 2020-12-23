package com.hz.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/23 12:46
 */
public class ZkUtils {

    public static ZooKeeper getZk(String address, int timeout) {
        CountDownLatch cdl = new CountDownLatch(1);
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(address, timeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    final Event.KeeperState state = event.getState();
                    switch (state) {
                        case SyncConnected:
                            System.out.println("connected.....");
                            cdl.countDown();
                            break;
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return zk;
    }

    public static class NodeObj{
        private String data;
        private Stat stat;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Stat getStat() {
            return stat;
        }

        public void setStat(Stat stat) {
            this.stat = stat;
        }
    }

}
