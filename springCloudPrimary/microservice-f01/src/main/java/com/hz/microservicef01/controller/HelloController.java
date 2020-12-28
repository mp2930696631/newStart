package com.hz.microservicef01.controller;

import com.hz.microservicef01.service.HystrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

    @Value("${myconf.info}")
    private String conf;

    @Autowired
    private HystrixService hystrixService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/conf")
    public String getConf() {
        String url = "http://microservice-n/prov/conf";
        final String forObject = restTemplate.getForObject(url, String.class);
        return port + "microservice-f conf: " + conf + "------->>>>>>>" + forObject;
    }

    @RequestMapping("/port")
    public String getPort() {
        String url = "http://microservice-n/prov/port";
        final String forObject = restTemplate.getForObject(url, String.class);
        return "microservice-f01 port: " + port + "---------->>>>>>>>>>>>" + forObject;
    }

    @RequestMapping("/testRibbon")
    public String testRibbon() {
        return hystrixService.testRibbon();
    }

    @RequestMapping("/testHystrix")
    public String testHystrix() {
        return hystrixService.testHystrix();
    }
}
