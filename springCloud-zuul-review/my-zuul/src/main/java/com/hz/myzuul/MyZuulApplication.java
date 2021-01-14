package com.hz.myzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class MyZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyZuulApplication.class, args);
    }

}
