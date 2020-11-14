package com.hz.practice.interceptor;

import com.hz.practice.controller.InterceptorController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 第三个参数类型是HandlerMethod
 *
 * @author: zehua
 * @date: 2020/11/14 14:02
 */
public class MyInterveptor2 implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof InterceptorController){
            System.out.println("ccccccccooooooo22222222");
        }else if (handler instanceof HandlerMethod){
            System.out.println(((HandlerMethod)handler).getBeanType());
        }else {
            System.out.println(handler);
        }

        System.out.println("2222 pre handle");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("2222 post handle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("2222 after complete");
    }
}
