package com.hz.practice.viewResolver;

import com.hz.practice.view.MyView;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * viewResolver可以设置优先级
 * 自定义的viewResolver的优先级尽量高一点
 *
 * @author: zehua
 * @date: 2020/11/14 10:22
 */
@Component
@Order(9)
public class MyViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(String viewName, Locale locale) throws Exception {
        if (viewName.startsWith("zehua:")) {
            return new MyView();
        }

        return null;
    }
}
