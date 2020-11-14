package com.hz.practice.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author: zehua
 * @date: 2020/11/14 11:57
 */
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler({ArithmeticException.class})
    public String exceptionHnadler() {
        System.out.println("...........");

        return "success";
    }

}
