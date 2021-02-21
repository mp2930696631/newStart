package com.hz.v4;

import com.hz.v4.advice.AdviceI;

import java.lang.reflect.Method;

/**
 * @author zehua
 * @date 2021/2/18 15:45
 */
public class MethodChain {
    private AdviceI[] adviceIS;

    private ProxyFactory proxyFactory;

    private int i = 0;

    public MethodChain() {

    }

    public MethodChain(AdviceI[] adviceIS, ProxyFactory proxyFactory) {
        this.adviceIS = adviceIS;
        this.proxyFactory = proxyFactory;
    }

    public Object process() throws Exception {
        if (i >= adviceIS.length) {
            return this.proxyFactory.invoke();
        }

        AdviceI adviceI = adviceIS[i++];
        return adviceI.invoke(this);
    }

    public AdviceI[] getAdviceIS() {
        return adviceIS;
    }

    public void setAdviceIS(AdviceI[] adviceIS) {
        this.adviceIS = adviceIS;
    }

    public ProxyFactory getProxyFactory() {
        return proxyFactory;
    }

    public void setProxyFactory(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }
}
