package com.hz.spring_ioc_anno;

import com.hz.spring_ioc_anno.controller.AddressController;
import com.hz.spring_ioc_anno.controller.GenericController;
import com.hz.spring_ioc_anno.controller.PersonController;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther zehua
 * @Date 2020/11/6 11:50
 */
public class IocTest {
    ClassPathXmlApplicationContext applicationContext
            = new ClassPathXmlApplicationContext("spring_ioc_anno/anno.xml");

    @Test
    public void test01() {
        PersonController personController
                = applicationContext.getBean("personController", PersonController.class);
        personController.save();
    }

    @Test
    public void test02() {
        AddressController addressController = applicationContext.getBean("addressController", AddressController.class);
        addressController.save();
    }

    /**
     * 泛型依赖注入，
     * https://github.com/bjmashibing/java/blob/master/javaframework/spring/04SpringIOC的注解使用/note/03SpringIOC的注解应用.md
     * 由上面的例子可以知道，spring在查找bean的时候要么根据type要么根据bean id
     * 其实我们还可以根据泛型来确定注入的bean
     * 这种情况适用于一个类或者接口有多个子类实现，而在注入的时候，写的是接口类型T和接口名称N
     * 这个时候，如果按照T来查找的话，会找到多个，
     * 而如果按照N来查找的话，又找不到（接口不能被实例化）
     *
     */
    @Test
    public void test03() {
        GenericController genericController = applicationContext.getBean("genericController", GenericController.class);
        genericController.print();
    }
}
