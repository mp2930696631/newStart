package com.hz.spring_ioc_anno.service.impl;

import com.hz.spring_ioc_anno.dao.BaseDao;
import com.hz.spring_ioc_anno.entity.Animal;
import com.hz.spring_ioc_anno.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther zehua
 * @Date 2020/11/6 12:41
 */
@Service
public class GenericService2Impl implements BaseService<Animal> {
    @Autowired
    BaseDao<Animal> baseDao;

    @Override
    public void service() {
        baseDao.print();
    }
}
