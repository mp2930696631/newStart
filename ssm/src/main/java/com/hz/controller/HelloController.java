package com.hz.controller;

import com.hz.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zehua
 * @date 2020/11/17 5:56
 */
@Controller
public class HelloController {

    @RequestMapping("/hello")
    @ResponseBody
    public User getUser() {
        User user = new User("zehua", 22);
        return user;
    }

    @RequestMapping("/hello/ssm")
    public String helloSSM() {
        return "hello";
    }
}
