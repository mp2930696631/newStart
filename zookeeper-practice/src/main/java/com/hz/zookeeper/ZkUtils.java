package com.hz.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/22 5:25
 */
public class ZkUtils {
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

    public static class NodeObjBuilder {
        private NodeObj nodeObj;

        public NodeObjBuilder() {
            this.nodeObj = new NodeObj();
        }

        public NodeObjBuilder setNodeName(String nodeName) {
            this.nodeObj.setNodeName(nodeName);
            return this;
        }

        public NodeObjBuilder setStat(Stat stat) {
            this.nodeObj.setStat(stat);
            return this;
        }

        public NodeObjBuilder setPath(String path) {
            this.nodeObj.setPath(path);
            return this;
        }

        public NodeObjBuilder setData(String data) {
            this.nodeObj.setData(data);
            return this;
        }

        public NodeObjBuilder setAclList(List<ACL> aclList) {
            this.nodeObj.setAclList(aclList);
            return this;
        }

        public NodeObjBuilder setCreateMode(CreateMode createMode) {
            this.nodeObj.setCreateMode(createMode);
            return this;
        }

        public NodeObjBuilder setWatcher(Watcher watcher) {
            this.nodeObj.setWatcher(watcher);
            return this;
        }

        public NodeObjBuilder setVersion(int version) {
            this.nodeObj.setVersion(version);
            return this;
        }

        public NodeObjBuilder setAclVersion(int aclVersion) {
            this.nodeObj.setAclVersion(aclVersion);
            return this;
        }

        public NodeObj build() {
            return nodeObj;
        }

    }

    public static class NodeObj {
        private String nodeName;
        private Stat stat;
        private String path;
        private String data;
        private List<ACL> aclList;
        private CreateMode createMode;
        private Watcher watcher;
        private int version;
        private int aclVersion;

        public NodeObj() {
        }

        public NodeObj(String nodeName) {
            this.nodeName = nodeName;
        }

        public NodeObj(String nodeName, Stat stat) {
            this.nodeName = nodeName;
            this.stat = stat;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public Stat getStat() {
            return stat;
        }

        public void setStat(Stat stat) {
            this.stat = stat;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public List<ACL> getAclList() {
            return aclList;
        }

        public void setAclList(List<ACL> aclList) {
            this.aclList = aclList;
        }

        public CreateMode getCreateMode() {
            return createMode;
        }

        public void setCreateMode(CreateMode createMode) {
            this.createMode = createMode;
        }

        public Watcher getWatcher() {
            return watcher;
        }

        public void setWatcher(Watcher watcher) {
            this.watcher = watcher;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public int getAclVersion() {
            return aclVersion;
        }

        public void setAclVersion(int aclVersion) {
            this.aclVersion = aclVersion;
        }
    }

    public static class MyWatcher implements Watcher {
        private ZooKeeper zk;
        private NodeObj nodeObj;
        private final MyCallBack myCallBack;

        public MyWatcher(NodeObj nodeObj, ZooKeeper zk, MyCallBack myCallBack) {
            this.nodeObj = nodeObj;
            this.zk = zk;
            this.myCallBack = myCallBack;
        }


        @Override
        public void process(WatchedEvent event) {
            final Event.EventType type = event.getType();
            switch (type) {
                case None:
                    break;
                case NodeCreated:
                    System.out.println("node created.....");
                    zk.getData("/node01", this, myCallBack, "hello");
                    break;
                case NodeDeleted:
                    System.out.println("node deleted.....");
                    nodeObj.setData(null);
                    break;
                case NodeDataChanged:
                    System.out.println("node data changed.........");
                    zk.getData("/node01", this, myCallBack, "hello");
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
    }

    public static class MyCallBack implements AsyncCallback.StatCallback, AsyncCallback.DataCallback {
        private NodeObj nodeObj;
        private ZooKeeper zk;
        private CountDownLatch cdl = new CountDownLatch(1);

        public MyCallBack(NodeObj nodeObj, ZooKeeper zk) {
            this.nodeObj = nodeObj;
            this.zk = zk;
        }

        @Override
        public void processResult(int rc, String path, Object ctx, Stat stat) {
            if (rc != 0) {
                /*System.out.println("xxxxx");
                System.out.println("node does not exist....");*/
            } else {
                nodeObj.setStat(stat);
                zk.getData("/node01", null, this, "hello");
            }

        }

        public void sync() {
            try {
                cdl.await();
                cdl = new CountDownLatch(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
            if (rc != 0) {
                System.out.println("get data fiel。。。。");
            } else {
                try {
                    nodeObj.setStat(stat);
                    nodeObj.setData(new String(data));
                    cdl.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
