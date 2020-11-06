package com.hz.spring_ioc_anno.service.impl;

import com.hz.spring_ioc_anno.dao.BaseDao;
import com.hz.spring_ioc_anno.entity.Person;
import com.hz.spring_ioc_anno.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther zehua
 * @Date 2020/11/6 12:40
 */
@Service
public class GenericService1Impl implements BaseService<Person> {
    @Autowired
    BaseDao<Person> baseDao;
    @Override
    public void service() {
        baseDao.print();
    }
}
