package com.hz.provider02.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/port")
    public String getPort() {
        return instanceName + " port: " + serverPort;
    }
}
