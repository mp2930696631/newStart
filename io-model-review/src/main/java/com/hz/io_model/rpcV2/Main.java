package com.hz.io_model.rpcV2;

import com.hz.io_model.rpcV2.service.RpcLocalServiceI;
import com.rpc.hz.proxy.RpcProxy;

/**
 * @author zehua
 * @date 2020/12/12 16:00
 */
public class Main {
    public static void main(String[] args) {
        for (int i = 0;i<10;i++){
            final RpcProxy<RpcLocalServiceI> rpcLocalServiceIRpcProxy = new RpcProxy<>();
            rpcLocalServiceIRpcProxy.setServerInfo("192.168.41.1", 9090);
            RpcLocalServiceI service = rpcLocalServiceIRpcProxy.getInstance(Main.class.getClassLoader(), RpcLocalServiceI.class);
            final String test_rpc = service.getService("test RPC");
            System.out.println(test_rpc);
        }
    }
}
