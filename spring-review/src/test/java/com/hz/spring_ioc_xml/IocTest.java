package com.hz.spring_ioc_xml;

import com.hz.spring_ioc_xml.entity.Address;
import com.hz.spring_ioc_xml.entity.Person;
import com.hz.spring_ioc_xml.entity.Student;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.alibaba.druid.pool.DruidDataSource;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * 测试spring ioc xml方式配置ioc容器
 *
 * @Auther zehua
 * @Date 2020/11/6 5:39
 */
public class IocTest {
    ClassPathXmlApplicationContext applicationContext
            = new ClassPathXmlApplicationContext("spring_ioc_xml/ioc.xml");


    /**
     * 演示了spring中通过构造函数来注入bean的场景
     * 有以下几点需要注意的
     * 1、参数类型必须匹配
     * 2、可以通过Parameters这个类来获取构造函数参数列表中的参数名称
     * 所以，constructor-arg标签中的name属性只与构造函数中参数名称相关，必须保持一致，而与其顺序无关，也与其他因素无关
     * 3、使用Parameters获取参数名称的前提是
     * （1）、使用的java编译器必须1.8以上
     * （2）、编译时需带上javac -parameters参数（好像不带也行）
     */
    @Test
    public void testX() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "泽华");
        map.put("age", 22);
        map.put("address", new Address("湖南", "shaoyang"));
        Set<String> set = new HashSet<>();
        set.add("name");
        set.add("age");
        set.add("address");
        Class<Person> personClass = Person.class;
        Constructor<?>[] declaredConstructors = personClass.getDeclaredConstructors();
        Object instance = null;
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            int count = declaredConstructor.getParameterCount();
            if (count != map.size()) {
                continue;
            }
            //declaredConstructor.getP
            Parameter[] parameters = declaredConstructor.getParameters();
            List<Object> list = new ArrayList<>();
            for (Parameter parameter : parameters) {
                String name = parameter.getName();
                if (set.contains(name)) {
                    list.add(map.get(name));
                }
            }
            try {
                instance = declaredConstructor.newInstance(list.toArray());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        System.out.println(instance);
    }

    @Test
    public void test01() {
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
    }

    @Test
    public void test02() {
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
    }

    @Test
    public void test03() {
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
    }

    @Test
    public void test04() {
        Person person = applicationContext.getBean("student", Student.class);
        System.out.println(person);
    }

    @Test
    public void test05() {
        Person person = applicationContext.getBean("student", Student.class);
        System.out.println(person);
    }

    @Test
    public void test06() {
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
    }

    @Test
    public void test07() {
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
    }

    @Test
    public void test08() {
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
    }

    @Test
    public void test09() {
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
    }

    @Test
    public void test10() {
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
        applicationContext.close();
    }

    @Test
    public void test11() {
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
    }

    @Test
    public void test12() throws SQLException {
        DruidDataSource dataSource = applicationContext.getBean("dataSource", DruidDataSource.class);
        Connection conn = dataSource.getConnection();
        System.out.println(conn);
        conn.close();
        dataSource.close();
    }

    @Test
    public void test13()  {
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
    }

}
