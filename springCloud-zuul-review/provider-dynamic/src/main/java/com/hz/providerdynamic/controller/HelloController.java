package com.hz.providerdynamic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zehua
 * @date 2021/1/14 10:14
 */
@RestController
@RequestMapping("/prov")
public class HelloController {
    @Value("${server.port}")
    private String serverPort;

    @Value("${instance.name}")
    private String instanceName;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/port")
    public String getPort() {
        return instanceName + " port: " + serverPort;
    }

    @GetMapping("/dynamic/port")
    public String getDynamicPort() {
        String url = "http://provider/prov/port";
        final String forObject = restTemplate.getForObject(url, String.class);
        return "dynamic>>>>" + instanceName + " port: " + serverPort + "-------------------" + forObject;
    }
}
