package com.hz.spring_ioc_xml.factory;

import com.hz.spring_ioc_xml.entity.Address;
import com.hz.spring_ioc_xml.entity.Person;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Auther zehua
 * @Date 2020/11/6 7:46
 */
public class MyFactoryBean implements FactoryBean<Person> {
    @Override
    public Person getObject() throws Exception {
        return new Person("xiaoze", 22, new Address());
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }
}
