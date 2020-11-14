package com.hz.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: zehua
 * @date: 2020/11/14 11:45
 */
@Controller
public class ExceptionController {

    @RequestMapping("/exception")
    public String exceptionC() {

        int i = 120 / 0;
        return "success";
    }

    @ExceptionHandler(value = {ArithmeticException.class})
    public String exceptionHandler() {
        System.out.println("exception....");

        return "success";
    }

}
