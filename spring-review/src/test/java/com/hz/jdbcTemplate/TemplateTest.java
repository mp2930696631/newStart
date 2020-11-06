package com.hz.jdbcTemplate;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.hz.jdbcTemplate.controller.EmpController;
import com.hz.jdbcTemplate.entity.Emp;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @Auther zehua
 * @Date 2020/11/6 19:03
 */
public class TemplateTest {
    ClassPathXmlApplicationContext applicationContext
            = new ClassPathXmlApplicationContext("jdbcTemplate/template.xml");

    @Test
    public void test01() {
        EmpController empController = applicationContext.getBean(EmpController.class);
        List<Emp> empList = empController.select("select * from emp", new Object[]{});
        empList.stream().forEach(System.out::println);
    }

    @Test
    public void test02() throws SQLException {
        JdbcTemplate template = applicationContext.getBean(JdbcTemplate.class);
    }

}
