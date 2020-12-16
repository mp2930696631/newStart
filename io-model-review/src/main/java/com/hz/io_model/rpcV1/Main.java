package com.hz.io_model.rpcV1;

import com.hz.io_model.rpcV1.jdkDynamicProxy.JdkProxy;
import com.hz.io_model.rpcV1.service.RpcLocalServiceI;

/**
 * @author zehua
 * @date 2020/12/12 9:37
 */
public class Main {

    public static void main(String[] args) {
        // for (int i = 0; i < 10; i++) {
        RpcLocalServiceI service = new JdkProxy<RpcLocalServiceI>().getInstance(Main.class.getClassLoader(), RpcLocalServiceI.class);
        final String test_rpc = service.getService("test RPC");
        System.out.println(test_rpc);

            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        // }
    }

}
