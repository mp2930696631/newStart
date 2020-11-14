package com.hz.practice.controller;

import com.hz.practice.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: zehua
 * @date: 2020/11/14 10:24
 */
@Controller
public class ViewController {

    @RequestMapping("/view")
    public String view(String name, Model model) {
        System.out.println(name);
        model.addAttribute("user", new User("zehua", 22));
        model.addAttribute("key", "123456789");
        return "zehua:xxx";
    }

}
