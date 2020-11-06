package com.hz.spring_ioc_anno.controller;

import com.hz.zehua_spring_ioc_anno.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Auther zehua
 * @Date 2020/11/6 9:00
 */
@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    public void save() {
        personService.save();
    }
}
