package com.hz.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author: zehua
 * @date: 2020/11/14 8:33
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "/index.jsp";
    }

}
