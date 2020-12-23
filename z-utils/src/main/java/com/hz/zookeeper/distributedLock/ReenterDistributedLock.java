package com.hz.zookeeper.distributedLock;

import com.hz.zookeeper.ZkUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 可重入和不可重入的区别只在于lock和unlock方法！！！
 *
 * @author zehua
 * @date 2020/12/23 14:50
 */
public class ReenterDistributedLock {
    private static final String ADDRESS = "192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181/distributedLock";
    private ZooKeeper zk;
    private String pathPrefix = "/lock";
    private byte[] data = "lock".getBytes();
    private WatcherAndCallback wacb;
    private String nodePath;

    // 重入的次数
    private int reenterCount = 0;


    public ReenterDistributedLock() {
        this.zk = ZkUtils.getZk(ADDRESS, 2000);
        this.wacb = new WatcherAndCallback();
    }

    // 不可重入版本
    /*public void lock() {
        String threadName = Thread.currentThread().getName();
        zk.create(pathPrefix, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, wacb, threadName);
        wacb.sync();
    }*/

    // 可重入版本,似乎只需要判断nodePath是否为空！！连getChildren方法都不需要
    public void lock() {
        String threadName = Thread.currentThread().getName();
        // 如果nodePath不为空
        if (nodePath != null) {
            reenterCount++;
            System.out.println(threadName + " reenter.....");
            return;
        }

        zk.create(pathPrefix, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL, wacb, threadName);
        wacb.sync();
    }

    // 不可重入版本
    /*public void unlock() {
        zk.delete(nodePath, 0, wacb, "hello");
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }*/

    // 可重入版本
    public void unlock() {
        if (reenterCount != 0) {
            reenterCount--;
            return;
        }

        zk.delete(nodePath, 0, wacb, "hello");
        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    private class WatcherAndCallback implements Watcher, AsyncCallback.StringCallback, AsyncCallback.ChildrenCallback, AsyncCallback.StatCallback, AsyncCallback.VoidCallback {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        public void sync() {
            try {
                countDownLatch.await();
                countDownLatch = new CountDownLatch(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void processResult(int rc, String path, Object ctx, String name) {
            nodePath = name;
            System.out.println(ctx + ": " + nodePath);
            zk.getChildren("/", null, this, "hello");
        }

        @Override
        public void processResult(int rc, String path, Object ctx, List<String> children) {
            String[] strs = new String[1];
            String nodePathSuffix = nodePath.substring(1);
            strs[0] = "0";
            // 大顶堆，获取前一个节点，保存在strs舒数组中！！！，时间复杂度O(n)
            for (String child : children) {
                if (child.compareTo(nodePathSuffix) < 0) {
                    if (child.compareTo(strs[0]) >= 0) {
                        strs[0] = child;
                    }
                }
            }

            // 是第一个节点
            if (strs[0].equals("0")) {
                countDownLatch.countDown();
            } else {
                zk.exists("/" + strs[0], this, this, "hello");
            }
        }

        @Override
        public void process(WatchedEvent event) {
            final Event.EventType type = event.getType();
            switch (type) {
                case NodeDeleted:
                    zk.getChildren("/", null, this, "hello");
                    break;
            }
        }

        @Override
        public void processResult(int rc, String path, Object ctx, Stat stat) {
            if (stat == null) {
                // 在设置监听的时候，可能前一个节点挂了，那么再查一次
                zk.getChildren("/", null, this, "hello");
            }
        }

        @Override
        public void processResult(int rc, String path, Object ctx) {
            // 删除节点后，记得将nodePath设置为空
            nodePath = null;
            if (rc == 0) {
                System.out.println("delete " + path + " node successfully.....");
            } else {
                System.out.println("delete " + path + " node failed.....");
            }
        }
    }

}
