package com.hz.controller;

import com.hz.aop.TestAop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zehua
 * @date 2020/11/17 9:20
 */
@Controller
public class AopController {
    @Autowired
    private TestAop aop;

    @RequestMapping("aop")
    public String aopTest() {
        aop.print();
        return "success";
    }

}
