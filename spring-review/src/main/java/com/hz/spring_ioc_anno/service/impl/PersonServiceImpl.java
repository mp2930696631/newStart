package com.hz.spring_ioc_anno.service.impl;

import com.hz.zehua_spring_ioc_anno.dao.PersonDao;
import com.hz.zehua_spring_ioc_anno.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther zehua
 * @Date 2020/11/6 9:01
 */
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDao personDao;

    @Override
    public void save() {
        this.personDao.save();
    }
}
