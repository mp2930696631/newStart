package com.hz.springbootActivemq.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author zehua
 * @date 2021/1/3 7:10
 */
// @Service
public class Receiver {

    // 通过activemq界面发现，会在queues那一栏目创建一个userTopic的队列，而且有一个消费者，就是他自己
    // 说明，这个注解的背后，和我们单独使用activemq创建consumer的流程是一样的，只不过我现在还不清楚到底是怎么解析注解的，
    // 所以 containerFactory默认使用的是queue的listenerFactory
    @JmsListener(destination = "userTopic", containerFactory = "jmsListenerContainerTopic")
    public void receiver01(String msg) {
        System.out.println("ok........." + msg);
    }

}
