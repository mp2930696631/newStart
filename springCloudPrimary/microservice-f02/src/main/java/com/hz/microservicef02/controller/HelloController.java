package com.hz.microservicef02.controller;

import com.hz.microservicef02.service.ApiService;
import com.hz.userapi.entiry.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zehua
 * @date 2020/12/28 21:08
 */
@RestController
@RequestMapping("/prov")
@RefreshScope
public class HelloController {
    @Value("${server.port}")
    private String port;

    @Autowired
    private ApiService apiService;

    @Value("${myconf.info}")
    private String conf;

    @RequestMapping("/conf")
        public String getConf() {
        final String forObject = apiService.getConf();
        return port + "microservice-f conf: " + this.conf + "------->>>>>>>" + forObject;
    }

    @RequestMapping("/port")
    public String getPort() {
        final String portStr = apiService.getPort();
        return "microservice-f02 port: " + this.port + "---------->>>>>>>>>>>>" + portStr;
    }

    @RequestMapping("/testRibbon")
    public String testRibbon() {
        return apiService.testRibbon();
    }

    @RequestMapping("/testHystrix")
    public String testHystrix() {
        return apiService.testHystrix();
    }

    @RequestMapping("/person")
    public Person getPerson() {
        return apiService.getPerson();
    }

    @RequestMapping(value = "/testPost", method = RequestMethod.POST)
    public String testPost(@RequestBody Map<String, Person> map) {
        return apiService.testPost(map);
    }

}
