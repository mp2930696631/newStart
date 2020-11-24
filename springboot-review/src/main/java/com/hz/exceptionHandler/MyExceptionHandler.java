package com.hz.exceptionHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zehua
 * @date 2020/11/24 11:24
 */
@RestControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ErrorMsg exceptionHandle(Exception e) {
        ErrorMsg errorMsg = new ErrorMsg();
        errorMsg.setMsg(e.getMessage());
        errorMsg.setCode(400);

        return errorMsg;
    }
}
