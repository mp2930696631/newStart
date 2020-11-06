package com.hz.spring_ioc_anno.dao.impl;

import com.hz.spring_ioc_anno.dao.BaseDao;
import com.hz.spring_ioc_anno.entity.Animal;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @Auther zehua
 * @Date 2020/11/6 12:47
 */
@Repository
public class AnimalDaoImpl implements BaseDao<Animal> {
    @Override
    public void print() {
        System.out.println("animal.....");
    }
}
