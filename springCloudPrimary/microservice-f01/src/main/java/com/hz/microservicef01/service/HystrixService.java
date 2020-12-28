package com.hz.microservicef01.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author zehua
 * @date 2020/12/29 6:43
 */
@Service
public class HystrixService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "testRibbonFallback")
    public String testRibbon() {
        String url = "http://microservice-n/prov/testRibbon";
        return restTemplate.getForObject(url, String.class);
    }

    @HystrixCommand(fallbackMethod = "testHystrixFallback")
    public String testHystrix() {
        String url = "http://microservice-n/prov/testHystrix";
        return restTemplate.getForObject(url, String.class);
    }

    private String testRibbonFallback() {
        return "microservice-f01 testRibbon error";
    }

    private String testHystrixFallback() {
        return "microservice-f01 testHystrix error";
    }


}
