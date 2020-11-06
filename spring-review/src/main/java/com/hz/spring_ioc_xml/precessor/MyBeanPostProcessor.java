package com.hz.spring_ioc_xml.precessor;

import com.hz.spring_ioc_xml.entity.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @Auther zehua
 * @Date 2020/11/6 7:53
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof Person) {
            System.out.println("set name before");
            ((Person) bean).setName("小泽华");
        } else {
            System.out.println("do nothing before");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof Person) {
            System.out.println("set age after");
            ((Person) bean).setAge(22);
        } else {
            System.out.println("do nothing after");
        }
        return bean;
    }
}
