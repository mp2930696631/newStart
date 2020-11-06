package com.hz.spring_ioc_anno.dao.impl;

import com.hz.spring_ioc_anno.dao.BaseDao;
import com.hz.spring_ioc_anno.entity.Person;
import com.hz.zehua_spring_ioc_anno.dao.PersonDao;
import org.springframework.stereotype.Repository;

/**
 * @Auther zehua
 * @Date 2020/11/6 8:59
 */
@Repository
public class PersonDaoImpl implements PersonDao, BaseDao<Person> {
    @Override
    public void save() {
        System.out.println("person save to db....");
    }

    @Override
    public void print() {
        System.out.println("person.....");
    }
}
