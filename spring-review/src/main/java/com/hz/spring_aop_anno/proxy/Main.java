package com.hz.spring_aop_anno.proxy;

import com.hz.spring_aop_anno.proxy.cglib.CGlibProxy;
import com.hz.spring_aop_anno.proxy.jdkProxy.JDKProxy;
import net.sf.cglib.proxy.Enhancer;

/**
 * @Auther zehua
 * @Date 2020/11/6 13:42
 */
public class Main {

    public static void main(String[] args) {
        // System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // jdk
        JDKProxy proxy = new JDKProxy(new MyCalculator());
        Calculator calcultor = (Calculator) proxy.getProxy();
        System.out.println(calcultor.add(1, 2));
        System.out.println(calcultor);
        System.out.println(calcultor.getClass());

        // cglib

        /*Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyCalculator.class);
        enhancer.setCallback(new CGlibProxy());
        Calculator calculator = (Calculator) enhancer.create();
        System.out.println(calculator.add(1, 2));*/
    }

}
