package com.hz.practice.controller;

import com.hz.practice.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: zehua
 * @date: 2020/11/14 10:43
 */
@Controller
public class ConverterController {

    @RequestMapping("/converter")
    public String converter(User user, Model model) {
        model.addAttribute("user", user);

        return "zehua:xxxxx";
    }

}
