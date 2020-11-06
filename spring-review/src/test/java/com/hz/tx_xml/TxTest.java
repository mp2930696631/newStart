package com.hz.tx_xml;

import com.hz.tx.service.BookServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther zehua
 * @Date 2020/11/6 22:20
 */
public class TxTest {
    ClassPathXmlApplicationContext applicationContext
            = new ClassPathXmlApplicationContext("tx_xml/tx.xml");

    @Test
    public void test01() {
        BookServiceImpl bookService = applicationContext.getBean(BookServiceImpl.class);
        bookService.checkout("zhangsan", 1);
    }

}
