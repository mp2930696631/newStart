package com.hz.zookeeper.baseCRUD;

import com.hz.encryption.EnDecrypt;
import com.hz.zookeeper.ZkUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/22 5:19
 */
public class Main {

    private static final String address = "192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181,192.168.41.100:2181/zehua";

    public static void main(String[] args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(1);
        CountDownLatch cdl2 = new CountDownLatch(1);
        CountDownLatch cdl3 = new CountDownLatch(1);
        CountDownLatch cdl4 = new CountDownLatch(1);
        CountDownLatch cdl5 = new CountDownLatch(1);
        CountDownLatch cdl6 = new CountDownLatch(1);
        CountDownLatch cdl7 = new CountDownLatch(1);
        CountDownLatch cdl8 = new CountDownLatch(1);
        CountDownLatch cdl9 = new CountDownLatch(1);
        ZooKeeper zk = new ZooKeeper(address, 2000, event -> {

            final Watcher.Event.KeeperState state = event.getState();
            final Watcher.Event.EventType type = event.getType();
            switch (state) {
                case Unknown:
                    break;
                case Disconnected:
                    break;
                case NoSyncConnected:
                    break;
                case SyncConnected:
                    System.out.println("connected.....");
                    break;
                case AuthFailed:
                    break;
                case ConnectedReadOnly:
                    break;
                case SaslAuthenticated:
                    break;
                case Expired:
                    break;
                case Closed:
                    break;
            }

            switch (type) {
                case None:
                    break;
                case NodeCreated:
                    System.out.println("node created.....");
                    break;
                case NodeDeleted:
                    System.out.println("node deleted.....");
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
            cdl.countDown();

        });

        cdl.await();

        final ZooKeeper.States state = zk.getState();
        switch (state) {
            case CONNECTING:
                System.out.println("ing.......");
                break;
            case ASSOCIATING:
                break;
            case CONNECTED:
                System.out.println("ed...........");
                break;
            case CONNECTEDREADONLY:
                break;
            case CLOSED:
                System.out.println("closed........");
                break;
            case AUTH_FAILED:
                System.out.println("auth failed................");
                break;
            case NOT_CONNECTED:
                System.out.println("not connected...........");
                break;
        }

        // r
        zk.getData("/", null, (rc, path, ctx, data, stat) -> {
            System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx + " data=" + new String(data));
            cdl2.countDown();
        }, "hello zehua");

        cdl2.await();

        // c1
        zk.create("/node02"
                , "hello zookeeper node02".getBytes()
                , ZooDefs.Ids.OPEN_ACL_UNSAFE
                , CreateMode.EPHEMERAL
                , (int rc, String path, Object ctx, String name) -> {
                    System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx + " name=" + name);
                    cdl3.countDown();
                }, "hello zookeeper");

        cdl3.await();

        // c2
        String str = "user1:123456";
        final String s = EnDecrypt.base64ThenSha1(str);
        String idStr = "user1:" + s;
        Id id = new Id("digest", idStr);
        ACL acl = new ACL(0b11111, id);
        List<ACL> acls = new ArrayList<>();
        acls.add(acl);
        ZkUtils.NodeObj nodeObj = new ZkUtils.NodeObj();
        zk.create("/node"
                , "hello zookeeper node011111, 泽华".getBytes(StandardCharsets.UTF_8)
                , acls
                , CreateMode.PERSISTENT_SEQUENTIAL
                , (int rc, String path, Object ctx, String name) -> {
                    nodeObj.setNodeName(name);
                    System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx + " name=" + name);
                    cdl4.countDown();
                }
                , "hello zehua");
        cdl4.await();

        // r
        zk.addAuthInfo("digest", str.getBytes());
        zk.getData(nodeObj.getNodeName(), null, (rc, path, ctx, data, stat) -> {
            System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx + " data=" + new String(data, StandardCharsets.UTF_8));
            cdl5.countDown();
        }, "hello zehua");

        cdl5.await();

        zk.create("/node01"
                , "hello zookeeper node011111, 泽华".getBytes(StandardCharsets.UTF_8)
                , ZooDefs.Ids.OPEN_ACL_UNSAFE
                , CreateMode.PERSISTENT
                , (int rc, String path, Object ctx, String name) -> {
                    nodeObj.setNodeName(name);
                    System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx + " name=" + name);
                    cdl8.countDown();
                }
                , "hello zehua");
        cdl8.await();

        // w
        ZkUtils.NodeObj nodeObj1 = new ZkUtils.NodeObj();
        zk.setData("/node01"
                , "new data".getBytes()
                , 0
                , new AsyncCallback.StatCallback() {
                    @Override
                    public void processResult(int rc, String path, Object ctx, Stat stat) {
                        nodeObj1.setStat(stat);
                        System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx);
                        cdl6.countDown();
                    }
                }, "hello zehua");

        cdl6.await();
        System.out.println(nodeObj1.getStat().getVersion());
        // a
        zk.setACL("/node01", acls, 0, new AsyncCallback.StatCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, Stat stat) {
                nodeObj1.setStat(stat);
                System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx);
                cdl9.countDown();
            }
        }, "hello zehua");
        cdl9.await();
        System.out.println("data version:"+nodeObj1.getStat().getVersion()+"acl version:"+nodeObj1.getStat().getAversion());

        // d
        zk.delete("/node01", nodeObj1.getStat().getVersion(), new AsyncCallback.VoidCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx) {
                System.out.println("rc=" + rc + " path=" + path + " ctx=" + ctx);
                cdl7.countDown();
            }
        }, "hello huazai");
        cdl7.await();

    }

}
