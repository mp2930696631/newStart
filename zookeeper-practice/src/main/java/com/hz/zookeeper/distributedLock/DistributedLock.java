package com.hz.zookeeper.distributedLock;

import com.hz.zookeeper.ZkUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/22 18:36
 */
public class DistributedLock {
    private ZooKeeper zk;
    private final String address = "192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181/distributedLock";
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private WatchCallback watchCallback;
    private String nodePath;

    public DistributedLock() {
        this.zk = ZkUtils.getZk(address, 2000);
        watchCallback = new WatchCallback();
    }

    public DistributedLock(ZooKeeper zk) {
        this.zk = zk;
        watchCallback = new WatchCallback();
    }

    public void lock() {
        zk.create("/lock", "lock".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, watchCallback, Thread.currentThread().getName());
        watchCallback.sync();
    }

    public void unlock() {
        zk.delete(nodePath, 0, watchCallback, "hello");
        /*try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    private class WatchCallback implements Watcher, AsyncCallback.StringCallback, AsyncCallback.ChildrenCallback, AsyncCallback.VoidCallback, AsyncCallback.StatCallback {

        public WatchCallback() {
        }

        public void sync() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            nodePath = name;
            System.out.println(ctx + " 创建节点：" + name);
            zk.getChildren("/", null, this, "hello");
        }

        @Override
        public void process(WatchedEvent event) {
            final Event.EventType type = event.getType();
            switch (type) {
                case None:
                    break;
                case NodeCreated:
                    System.out.println("node created.....");
                    break;
                case NodeDeleted:
                    System.out.println("node deleted.....");
                    zk.getChildren("/", null, this, "hello");
                    break;
                case NodeDataChanged:
                    System.out.println("node data changed.........");
                    break;
                case NodeChildrenChanged:
                    System.out.println("node children changed.....");
                    break;
                case DataWatchRemoved:
                    System.out.println("node data watch removed.....");
                    break;
                case ChildWatchRemoved:
                    System.out.println("node child watch removed.....");
                    break;
                case PersistentWatchRemoved:
                    break;
            }
        }

        @Override
        public void processResult(int rc, String path, Object ctx, List<String> children) {

            Collections.sort(children);
            /*System.out.println("------------------------");
            System.out.println(nodePath + "===========");
            for (String child : children) {
                System.out.println(child);
            }
            System.out.println("----------------");*/
            final int i = children.indexOf(nodePath.substring(1));
            if (i != 0) {
                // 不是第一个，注册watch事件
                final String before = children.get(i - 1);
                // 注意，这里使用exist！！！，而且需要回调，因为有可能在这一瞬间挂了，所以这个watch事件可能失败
                // 所以在生产环境下是需要加上回调的
                zk.exists("/" + before, this, this, "hello");
            } else {
                // 是第一个！！获得锁
                countDownLatch.countDown();
            }
        }

        @Override
        public void processResult(int rc, String path, Object ctx) {
            // System.out.println(path + " node delete.....");
        }

        @Override
        public void processResult(int rc, String path, Object ctx, Stat stat) {
            if (stat == null) {
                // 说明在设置watch的时候前面一个节点挂了，需要重新获取子节点
                zk.getChildren("/", null, this, "hello");
            }
        }
    }
}
