package com.hz.practice.controller;

import com.hz.practice.entity.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zehua
 * @date: 2020/11/14 11:16
 */
@Controller
public class EntityController {

    @RequestMapping("/entity")
    public ResponseEntity<User> entity() {
        System.out.println("test responseEntity...");
        HttpHeaders headers = new HttpHeaders();
        headers.add("test", "responseEntity");

        return new ResponseEntity<>(new User("泽华", 22), headers, HttpStatus.OK);
    }

    @RequestMapping("/json")
    @ResponseBody
    public List<User> json() {
        List<User> list = new ArrayList<>();
        list.add(new User("zehua", 22));
        list.add(new User("xiaoze", 22));

        return list;
    }

    @RequestMapping("/reqBody1")
    public String reqBody1(@RequestBody String str) {
        System.out.println(str);

        return "success";
    }

    @RequestMapping("/reqBody2")
    public String reqBody2(@RequestBody User user) {
        System.out.println(user);

        return "success";
    }

    @RequestMapping("/httpEntity")
    public String httpEntity(HttpEntity entity) {
        System.out.println(entity);

        return "success";
    }

}
