zookeeper实现的配置中心，有如下特点
1、首先使用exist，如果数据存在，继续往下走
2、如果数据不存在，等着
3、如果数据修改，控制台可以打印最新数据
4、如果节点被删除。等着
5、回到第二点