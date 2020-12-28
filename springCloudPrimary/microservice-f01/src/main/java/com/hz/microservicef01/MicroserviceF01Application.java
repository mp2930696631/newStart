package com.hz.microservicef01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
public class MicroserviceF01Application {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceF01Application.class, args);
    }

}
