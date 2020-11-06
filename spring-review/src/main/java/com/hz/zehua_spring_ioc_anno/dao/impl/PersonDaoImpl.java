package com.hz.zehua_spring_ioc_anno.dao.impl;

import com.hz.zehua_spring_ioc_anno.dao.PersonDao;
import org.springframework.stereotype.Repository;

/**
 * @Auther zehua
 * @Date 2020/11/6 8:59
 */
@Repository
public class PersonDaoImpl implements PersonDao {
    @Override
    public void save() {
        System.out.println("person save to db....");
    }
}
