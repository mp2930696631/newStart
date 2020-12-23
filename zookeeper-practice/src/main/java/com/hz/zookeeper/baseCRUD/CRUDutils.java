package com.hz.zookeeper.baseCRUD;

import com.hz.zookeeper.ZkUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/22 11:18
 */
public class CRUDutils {
    private static CountDownLatch countDownLatch = null;

    public static ZooKeeper getZk(String address, int timeout) {
        CountDownLatch cdl = new CountDownLatch(1);
        countDownLatch = cdl;
        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper(address, timeout, new Watcher() {
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
        return zooKeeper;
    }

    public static void setAuthInfo(ZooKeeper zk, String schema, String auth) {
        zk.addAuthInfo(schema, auth.getBytes());
    }

    public static void create(ZooKeeper zk, ZkUtils.NodeObj nodeObj, Object ctxObj) {
        CountDownLatch cdl = new CountDownLatch(1);
        countDownLatch = cdl;
        zk.create(nodeObj.getPath()
                , nodeObj.getData().getBytes(StandardCharsets.UTF_8)
                , nodeObj.getAclList()
                , nodeObj.getCreateMode()
                , (int rc, String path, Object ctx, String name) -> {
                    nodeObj.setNodeName(name);
                    System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx + " name=" + name);
                    cdl.countDown();
                }
                , ctxObj);
    }

    public static void create(ZooKeeper zk, String nodePath, byte[] data, List<ACL> aclList, CreateMode createMode, ZkUtils.NodeObj nodeObj, Object ctxObj) {
        CountDownLatch cdl = new CountDownLatch(1);
        countDownLatch = cdl;
        zk.create(nodePath
                , data
                , aclList
                , createMode
                , (int rc, String path, Object ctx, String name) -> {
                    nodeObj.setNodeName(name);
                    System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx + " name=" + name);
                    cdl.countDown();
                }
                , ctxObj);
    }

    public static void get(ZooKeeper zk, ZkUtils.NodeObj nodeObj, Object ctxObj) {
        CountDownLatch cdl = new CountDownLatch(1);
        countDownLatch = cdl;
        zk.getData(nodeObj.getPath()
                , nodeObj.getWatcher()
                , (rc, path, ctx, data, stat) -> {
                    nodeObj.setStat(stat);
                    System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx + " data=" + new String(data, StandardCharsets.UTF_8));
                    cdl.countDown();
                }, ctxObj);
    }

    public static void get(ZooKeeper zk, String nodePath, Watcher watcher, ZkUtils.NodeObj nodeObj, Object ctxObj) {
        CountDownLatch cdl = new CountDownLatch(1);
        countDownLatch = cdl;
        zk.getData(nodePath
                , watcher
                , (rc, path, ctx, data, stat) -> {
                    nodeObj.setStat(stat);
                    System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx + " data=" + new String(data, StandardCharsets.UTF_8));
                    cdl.countDown();
                }, ctxObj);
    }

    public static void set(ZooKeeper zk, ZkUtils.NodeObj nodeObj, Object ctxObj) {
        CountDownLatch cdl = new CountDownLatch(1);
        countDownLatch = cdl;
        zk.setData(nodeObj.getPath()
                , nodeObj.getData().getBytes(StandardCharsets.UTF_8)
                , nodeObj.getVersion()
                , (rc, path, ctx, stat) -> {
                    nodeObj.setStat(stat);
                    System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx);
                    cdl.countDown();
                }, ctxObj);
    }

    public static void set(ZooKeeper zk, String nodePath, byte[] data, int dataVersion, ZkUtils.NodeObj nodeObj, Object ctxObj) {
        CountDownLatch cdl = new CountDownLatch(1);
        countDownLatch = cdl;
        zk.setData(nodePath
                , data
                , dataVersion
                , (rc, path, ctx, stat) -> {
                    nodeObj.setStat(stat);
                    System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx+" stat="+stat);
                    cdl.countDown();
                }, ctxObj);
    }

    public static void delete(ZooKeeper zk, ZkUtils.NodeObj nodeObj, Object ctxObj) {
        CountDownLatch cdl = new CountDownLatch(1);
        countDownLatch = cdl;
        zk.delete(nodeObj.getPath()
                , nodeObj.getVersion()
                , (rc, path, ctx) -> {
                    System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx);
                    cdl.countDown();
                }
                , ctxObj);
    }

    public static void delete(ZooKeeper zk, String nodePath, int dataVersion, ZkUtils.NodeObj nodeObj, Object ctxObj) {
        CountDownLatch cdl = new CountDownLatch(1);
        countDownLatch = cdl;
        zk.delete(nodePath
                , dataVersion
                , (rc, path, ctx) -> {
                    System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx);
                    cdl.countDown();
                }
                , ctxObj);
    }

    public static void setAcl(ZooKeeper zk, ZkUtils.NodeObj nodeObj, Object ctxObj) {
        CountDownLatch cdl = new CountDownLatch(1);
        countDownLatch = cdl;
        zk.setACL(nodeObj.getPath(), nodeObj.getAclList(), nodeObj.getAclVersion(), (rc, path, ctx, stat) -> {
            nodeObj.setStat(stat);
            System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx);
            cdl.countDown();
        }, ctxObj);
    }

    public static void setAcl(ZooKeeper zk, String nodePath, List<ACL> aclList, int aclVersion, ZkUtils.NodeObj nodeObj, Object ctxObj) {
        CountDownLatch cdl = new CountDownLatch(1);
        countDownLatch = cdl;
        zk.setACL(nodePath, aclList, aclVersion, (rc, path, ctx, stat) -> {
            nodeObj.setStat(stat);
            System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx);
            cdl.countDown();
        }, ctxObj);
    }

    // 同步
    public static void sync() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
