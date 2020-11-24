package com.hz.baseMVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import starter.com.hz.configurationBean.StarterConfiguration;
import starter.com.hz.service.StarterService;

/**
 * @author zehua
 * @date 2020/11/24 10:38
 */
@Controller
public class MVCController {
    @Autowired
    private StarterConfiguration configuration;

    @Autowired
    private StarterService starterService;

    @RequestMapping("/testStatic")
    public String hello() {
        System.out.println(configuration);
        return "hello.html";
    }

    @RequestMapping("/testRE/{id}")
    @ResponseBody
    public ResponseEntity<String> testRE(@PathVariable("id") String id, String username) {
        System.out.println(username);
        HttpHeaders headers = new HttpHeaders();
        headers.add("myHeader", "zehua");
        ResponseEntity<String> re = new ResponseEntity<>("test base mvc", headers, HttpStatus.OK);

        return re;
    }

}
