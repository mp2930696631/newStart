package com.hz.tx;

import com.hz.tx.service.BookServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileNotFoundException;

/**
 * @Auther zehua
 * @Date 2020/11/6 19:51
 */
public class TxTest {
    ClassPathXmlApplicationContext applicationContext
            = new ClassPathXmlApplicationContext("tx/tx.xml");

    @Test
    public void test01()  {
        BookServiceImpl bookService = applicationContext.getBean(BookServiceImpl.class);
        bookService.checkout("zhangsan", 1);
    }

}
