package com.hz.spring_ioc_anno.controller;

import com.hz.spring_ioc_anno.entity.Animal;
import com.hz.spring_ioc_anno.entity.Person;
import com.hz.spring_ioc_anno.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Auther zehua
 * @Date 2020/11/6 12:53
 */
@Controller
public class GenericController {
    @Autowired
    private BaseService<Person> baseService;

    public void print() {
        baseService.service();
    }
}
