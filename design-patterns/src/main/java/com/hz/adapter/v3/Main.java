package com.hz.adapter.v3;

/**
 * 适配器提供一个接口，可以使得任意实现了这个接口的需要适配的组件都可以连接上电脑的USB
 *
 * @Auther zehua
 * @Date 2020/11/8 5:28
 */
public class Main {

    public static void main(String[] args) {
        Computer computer = new Computer();
        NetLine netLine = new NetLine();
        // 上网
        USB adapter = new Adapter(netLine);
        computer.doWork(adapter);

        // 投影
        Projection projection = new Projection();
        USB adapter2  = new Adapter(projection);
        computer.doWork(adapter2);
    }

}
