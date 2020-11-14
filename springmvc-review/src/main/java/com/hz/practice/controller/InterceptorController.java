package com.hz.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: zehua
 * @date: 2020/11/14 14:10
 */
@Controller
public class InterceptorController {

    @RequestMapping("/interceptor")
    public String interceptor() {

        return "success";
    }

}
