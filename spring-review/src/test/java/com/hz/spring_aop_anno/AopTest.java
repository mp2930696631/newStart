package com.hz.spring_aop_anno;

import com.hz.spring_aop_anno.proxy.Calculator;
import com.hz.spring_aop_anno.proxy.MyCalculator;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther zehua
 * @Date 2020/11/6 15:43
 */
public class AopTest {
    ClassPathXmlApplicationContext applicationContext
            = new ClassPathXmlApplicationContext("spring_aop_anno/anno.xml");

    @Test
    public void test01() {
        MyCalculator myCalculator = applicationContext.getBean("myCalculator", MyCalculator.class);
        /*System.out.println(myCalculator.add(1, 2));
        System.out.println(myCalculator.minus(1, 2));*/
        try {
            System.out.println(myCalculator.divide(6, 2));
        } catch (Exception e) {
            System.out.println();
        }
    }

}
