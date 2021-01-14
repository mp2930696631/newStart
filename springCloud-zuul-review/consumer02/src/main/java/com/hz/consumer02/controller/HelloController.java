package com.hz.consumer02.controller;

import com.hz.consumer02.service.GrayscaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zehua
 * @date 2021/1/14 8:34
 */
@RestController
@RequestMapping("/cons")
public class HelloController {

    @Autowired
    private GrayscaleService grayscaleService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private String serverPort;

    @Value("${instance.name}")
    private String instanceName;

    @GetMapping("/port")
    public String getPort(@RequestParam("userId") String userId) {
        String url = "http://provider/prov/port?userId=" + userId;
        final String forObject = restTemplate.getForObject(url, String.class);
        return instanceName + " port: " + serverPort + "--------------------" + forObject;
    }

    @RequestMapping("/test01")
    public String test01() {
        grayscaleService.getAllGrayscale();
        return "success";
    }

}
