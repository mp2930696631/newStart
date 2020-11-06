package com.hz.spring_aop_xml;

import com.hz.spring_aop_anno.proxy.MyCalculator;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther zehua
 * @Date 2020/11/6 17:53
 */
public class AopTest {
    ClassPathXmlApplicationContext applicationContext
            = new ClassPathXmlApplicationContext("spring_aop_xml/aop.xml");

    @Test
    public void test01() {
        MyCalculator myCalculator = applicationContext.getBean(MyCalculator.class);
        try {
            System.out.println(myCalculator.divide(6, 3));
        } catch (Exception e) {
            System.out.println();
        }
    }

}
