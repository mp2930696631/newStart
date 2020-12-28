package com.hz.microservicen01.controller;

import com.hz.userapi.UserApi;
import com.hz.userapi.entiry.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zehua
 * @date 2020/12/28 21:08
 */
@RestController
@RequestMapping("/prov")
@RefreshScope
public class HelloController implements UserApi {
    @Value("${server.port}")
    private String port;

    @Value("${myconf.info}")
    private String conf;

    @RequestMapping("/conf")
    public String getConf() {

        return port + "microservice-n conf: " + conf;
    }

    @RequestMapping("/port")
    public String getPort() {
        return "microservice-n2 port: " + port;
    }

    @RequestMapping("/testRibbon")
    public String testRibbon() {
        System.out.println("testRibbon invock.....");
        System.out.println("准备睡.....");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "testRibbon ok..........";
    }

    @RequestMapping("/testHystrix")
    public String testHystrix() {
        System.out.println("testHystrix invock.....");
        int i = 1 / 0;
        return "testHystrix ok..........";
    }

    @RequestMapping("/person")
    public Person getPerson() {
        Person p = new Person("zehua", 22);
        return p;
    }

    @RequestMapping("/testPost")
    public String testPost(@RequestBody Map<String, Person> map) {
        System.out.println(map);
        return "testPost ok....";
    }

}
